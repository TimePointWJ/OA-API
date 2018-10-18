package com.odm.oa.service;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.odm.oa.entity.LeaveType;
import com.odm.oa.mapper.LeaveTypeMapper;
import com.odm.oa.model.request.PaginationBase;
import com.odm.oa.utils.Constants;

@Service
public class LeaveTypeService {

	private static Logger logger = LogManager.getLogger(LeaveTypeService.class);
	
	@Autowired
	private LeaveTypeMapper leaveTypeMapper;
	
	/**
	 * 分页查询请假类型信息
	 * @param request
	 * @return
	 */
    public PageInfo<LeaveType> getPage(PaginationBase request) {
    	logger.info("method: getList param：" + JSONObject.toJSONString(request));
    	if(request.getPageNum() == null){
    		request.setPageNum(Constants.PAGE_NUM);
    	}
    	if(request.getPageSize() == null){
    		request.setPageSize(Constants.PAGE_SIZE);
    	}
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<LeaveType> list = leaveTypeMapper.pageList(request);
        PageInfo<LeaveType> pageInfo = new PageInfo<LeaveType>(list);
        logger.info("method: getList result：" + JSONObject.toJSONString(pageInfo));
        return pageInfo;
    }
    /**
     * 添加请假类型信息
     * @param request
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
	public boolean insertOrUpdate(LeaveType request) {
    	if(request.getId() == null){
    		//先查询下有没有存在的请假类型名称
    		if(leaveTypeMapper.getByName(request.getName()) > 0){
    			//存在请假名称相同的
    			logger.info("添加失败，存在相同的请假名称");
    			return false;
    		}
        	//再添加
    		request.setCreateTime(new Date());
    		request.setDelFlg(false);
    		request.setVersion(0);
        	if(leaveTypeMapper.insert(request) > 0){
        		logger.info("添加成功");
        		return true;
        	}else{
        		logger.info("添加失败");
        		return false;
        	}
    	}else{
    		request.setVersion(request.getVersion()+1);
    		request.setUpdateTime(new Date());
    		if(leaveTypeMapper.updateByPrimaryKey(request) > 0){
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
	public boolean del(LeaveType request) {
    	LeaveType leaveType = leaveTypeMapper.selectByPrimaryKey(request.getId());
    	if(leaveType != null){
    		leaveType.setVersion((leaveType.getVersion() == null ? 0 : leaveType.getVersion()) +1);
    		leaveType.setUpdateTime(new Date());
    		leaveType.setDelFlg(Constants.IS_DELETE);
    		leaveTypeMapper.updateByPrimaryKey(leaveType);
    		return true;
    	}
		return false;
	}
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
	public Integer delList(Long[] ids) {
    	int count = 0;
		for(Long id : ids){
			LeaveType leaveType = leaveTypeMapper.selectByPrimaryKey(id);
	    	if(leaveType != null){
	    		leaveType.setVersion((leaveType.getVersion() == null ? 0 : leaveType.getVersion()) +1);
	    		leaveType.setUpdateTime(new Date());
	    		leaveType.setDelFlg(Constants.IS_DELETE);
	    		leaveTypeMapper.updateByPrimaryKey(leaveType);
	    		count++;
	    	}
		}
		return count;
	}
}
