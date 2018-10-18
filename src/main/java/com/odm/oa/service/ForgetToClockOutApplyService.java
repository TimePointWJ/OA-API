package com.odm.oa.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.odm.oa.entity.ForgetToClockOutApply;
import com.odm.oa.mapper.ForgetToClockOutApplyMapper;
import com.odm.oa.model.request.PaginationBase;
import com.odm.oa.utils.Constants;

@Service
public class ForgetToClockOutApplyService {
	private static Logger logger = LogManager.getLogger(ForgetToClockOutApplyService.class);
	
	@Autowired
	private ForgetToClockOutApplyMapper forgetToClockOutApplyMapper;

	public PageInfo<ForgetToClockOutApply> getPage(PaginationBase request) {
		logger.info("method: getList param：" + JSONObject.toJSONString(request));
    	if(request.getPageNum() == null){
    		request.setPageNum(Constants.PAGE_NUM);
    	}
    	if(request.getPageSize() == null){
    		request.setPageSize(Constants.PAGE_SIZE);
    	}
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<ForgetToClockOutApply> list = forgetToClockOutApplyMapper.pageList(request);
        PageInfo<ForgetToClockOutApply> pageInfo = new PageInfo<ForgetToClockOutApply>(list);
        logger.info("method: getList result：" + JSONObject.toJSONString(pageInfo));
        return pageInfo;
	}
}
