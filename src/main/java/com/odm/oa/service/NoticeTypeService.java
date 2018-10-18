package com.odm.oa.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.odm.oa.entity.Notice;
import com.odm.oa.entity.NoticeType;
import com.odm.oa.mapper.NoticeTypeMapper;
import com.odm.oa.model.request.PaginationBase;
import com.odm.oa.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class NoticeTypeService {
    @Autowired
    private NoticeTypeMapper noticeTypeMapper;
    private static Logger logger = LogManager.getLogger(NoticeTypeService.class);
    /**
     * 分页查询公告类型信息
     * @param request
     * @return
     */
    public PageInfo<NoticeType> getPage(PaginationBase request) {
        logger.info("method: getList param：" + JSONObject.toJSONString(request));
        if(request.getPageNum() == null){
            request.setPageNum(Constants.PAGE_NUM);
        }
        if(request.getPageSize() == null){
            request.setPageSize(Constants.PAGE_SIZE);
        }
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<NoticeType> list = noticeTypeMapper.pageList(request);
        PageInfo<NoticeType> pageInfo = new PageInfo<NoticeType>(list);
        logger.info("method: getList result：" + JSONObject.toJSONString(pageInfo));
        return pageInfo;
    }
    /**
     * 添加公告类型信息
     * @param request
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public boolean insertOrUpdate(NoticeType request) {
        if(request.getId() == null){
            //先查询下有没有存在的类型名称
            if(noticeTypeMapper.getByName(request.getName()) > 0){
                //存在类别名称相同的
                logger.info("添加失败，存在相同的请假名称");
                return false;
            }
            //再添加
            request.setCreateTime(new Date());
            request.setDelFlg(false);
            request.setVersion(0);
            if(noticeTypeMapper.insert(request) > 0){
                logger.info("添加成功");
                return true;
            }else{
                logger.info("添加失败");
                return false;
            }
        }else{
            request.setVersion(request.getVersion()+1);
            request.setUpdateTime(new Date());
            if(noticeTypeMapper.updateByPrimaryKey(request) > 0){
                logger.info("更新成功");
                return true;
            }else{
                logger.info("更新失败");
                return false;
            }
        }

    }
    /**
     * 删除
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean del(NoticeType request) {
        NoticeType noticeType = noticeTypeMapper.selectByPrimaryKey(request.getId());
        if(noticeType != null){
            noticeType.setVersion((noticeType.getVersion() == null ? 0 : noticeType.getVersion()) +1);
            noticeType.setUpdateTime(new Date());
            noticeType.setDelFlg(Constants.IS_DELETE);
            noticeTypeMapper.updateByPrimaryKey(noticeType);
            return true;
        }
        return false;
    }
    /**
     * 批量删除
     * @param Id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer delList(Long[] Id) {
        int count = 0;
        for(Long id : Id){
            NoticeType noticeType = noticeTypeMapper.selectByPrimaryKey(id);
            if(noticeType != null){
                noticeType.setVersion((noticeType.getVersion() == null ? 0 : noticeType.getVersion()) +1);
                noticeType.setUpdateTime(new Date());
                noticeType.setDelFlg(Constants.IS_DELETE);
                noticeTypeMapper.updateByPrimaryKey(noticeType);
                count++;
            }
        }
        return count;
    }
    public List<NoticeType> typeList(){
        return noticeTypeMapper.selectList();
    }
}
