package com.odm.oa.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.odm.oa.entity.Position;
import com.odm.oa.mapper.PositionMapper;
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
public class PositionService {
    @Autowired
    private PositionMapper positionMapper;
    private static Logger logger = LogManager.getLogger(PositionService.class);
    /**
     * 分页查询公告类型信息
     * @param request
     * @return
     */
    public PageInfo<Position> getPage(PaginationBase request) {
        logger.info("method: getList param：" + JSONObject.toJSONString(request));
        if(request.getPageNum() == null){
            request.setPageNum(Constants.PAGE_NUM);
        }
        if(request.getPageSize() == null){
            request.setPageSize(Constants.PAGE_SIZE);
        }
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Position> list = positionMapper.pageList(request);
        PageInfo<Position> pageInfo = new PageInfo<Position>(list);
        logger.info("method: getList result：" + JSONObject.toJSONString(pageInfo));
        return pageInfo;
    }
    /**
     * 添加公告类型信息
     * @param request
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public boolean insertOrUpdate(Position request) {
        if(request.getId() == null){
            //先查询下有没有存在的职位名称
            if(positionMapper.getByName(request.getPositionName()) > 0){
                //存在职位名称相同的
                logger.info("添加失败，存在相同的请假名称");
                return false;
            }
            //再添加
            request.setCreateTime(new Date());
            request.setDelFlg(false);
            request.setVersion(0);
            if(positionMapper.insert(request) > 0){
                logger.info("添加成功");
                return true;
            }else{
                logger.info("添加失败");
                return false;
            }
        }else{
            request.setVersion(request.getVersion()+1);
            request.setUpdateTime(new Date());
            if(positionMapper.updateByPrimaryKey(request) > 0){
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
    public boolean del(Position request) {
        Position position = positionMapper.selectByPrimaryKey(request.getId());
        if(position != null){
            position.setVersion((position.getVersion() == null ? 0 : position.getVersion()) +1);
            position.setUpdateTime(new Date());
            position.setDelFlg(Constants.IS_DELETE);
            positionMapper.updateByPrimaryKey(position);
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
            Position position = positionMapper.selectByPrimaryKey(id);
            if(position != null){
                position.setVersion((position.getVersion() == null ? 0 : position.getVersion()) +1);
                position.setUpdateTime(new Date());
                position.setDelFlg(Constants.IS_DELETE);
                positionMapper.updateByPrimaryKey(position);
                count++;
            }
        }
        return count;
    }
    /*public List<NoticeType> typeList(){
        return noticeTypeMapper.selectList();
    }*/
}
