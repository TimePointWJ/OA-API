package com.odm.oa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.odm.oa.entity.ForgetToClockOutApply;
import com.odm.oa.model.request.PaginationBase;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.service.ForgetToClockOutApplyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags={"忘打卡申请"})
@RestController
@RequestMapping("/service/forget")
public class ForgetToClockOutApplyController extends BasicController{
	
	@Autowired
	private ForgetToClockOutApplyService forgetToClockOutApplyService;
	
	/**
	 * 分页查询
	 * @param httpSession
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping( value = "/pageList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "获取忘打卡申请记录", httpMethod = "POST", response = ForgetToClockOutApply.class, notes = "获取忘打卡申请记录")
    public BaseResponse<PageInfo<ForgetToClockOutApply>> list(HttpSession httpSession, @ApiParam(name = "PaginationBase",
    	value = "ForgetToClockOutApply list info", required = true) @RequestBody PaginationBase request) {
        // 返回参数初始化
        logger.info("ForgetToClockOutApply > getList" + JSONObject.toJSONString(request));
        BaseResponse<PageInfo<ForgetToClockOutApply>> response = new BaseResponse<>();
        logger.info("--------------------------------获取考勤记录开始--------------------------------");
        PageInfo<ForgetToClockOutApply> responseData = forgetToClockOutApplyService.getPage(request);
        response.setResponseData(responseData);
        //response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        logger.info("--------------------------------获取考勤记录结束--------------------------------");
        return response;
    }
}
