package com.odm.oa.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.odm.oa.entity.LeaveType;
import com.odm.oa.model.request.PaginationBase;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.service.LeaveTypeService;
import com.odm.oa.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags={"请假类型信息"})
@RestController
@RequestMapping("/service/leave")
public class LeaveTypeController  extends BasicController{
	
	@Autowired
	private LeaveTypeService leaveTypeService;

	/**
	 * 分页查询
	 * @param httpSession
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping( value = "/pageList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "获取请假类型信息", httpMethod = "POST", response = LeaveType.class, notes = "获取请假类型信息")
    public BaseResponse<PageInfo<LeaveType>> list(HttpSession httpSession, @ApiParam(name = "PaginationBase",
    	value = "leave list info", required = true) @RequestBody PaginationBase request) {
        // 返回参数初始化
        logger.info("LeaveController > getList" + JSONObject.toJSONString(request));
        BaseResponse<PageInfo<LeaveType>> response = new BaseResponse<>();
        logger.info("--------------------------------获取请假类型信息开始--------------------------------");
        PageInfo<LeaveType> responseData = leaveTypeService.getPage(request);
        response.setResponseData(responseData);
        //response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        logger.info("--------------------------------获取请假类型信息结束--------------------------------");
        return response;
    }
	
	/**
	 * 添加
	 * @param httpSession
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping( value = "/add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "添加请假类型信息", httpMethod = "POST", response = Integer.class, notes = "添加请假类型信息")
    public BaseResponse<Integer> add(HttpSession httpSession, @ApiParam(name = "LeaveType", value = "add info",
    	required = true) @RequestBody LeaveType request) {
        // 返回参数初始化
        logger.info("LeaveController > add" + JSONObject.toJSONString(request));
        BaseResponse<Integer> response = new BaseResponse<>();
        logger.info("--------------------------------获取请假类型信息开始--------------------------------");
        Integer code=1;
        if(leaveTypeService.insertOrUpdate(request)){
            logger.info("请假类型信息添加或更新成功");
            code=0;
            response.setResponseData(code);
        }else{
        	logger.info("请假类型信息添加或更新失败");
        	response.setResponseData(code);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        logger.info("--------------------------------获取请假类型信息结束--------------------------------");
        return response;
    }
	
	/**
	 * 删除
	 * @param httpSession
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping( value = "/delete", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "删除请假类型信息", httpMethod = "POST", response = Integer.class, notes = "删除请假类型信息")
    public BaseResponse<Integer> delete(HttpSession httpSession, @ApiParam(name = "LeaveType", value = "delete info",
    	required = true) @RequestBody LeaveType request) {
        // 返回参数初始化
        logger.info("LeaveController > add" + JSONObject.toJSONString(request));
        BaseResponse<Integer> response = new BaseResponse<>();
        logger.info("--------------------------------删除请假类型信息开始--------------------------------");
        Integer code=0;
        if(leaveTypeService.del(request)){
            logger.info("请假类型信息删除成功");
            code=1;
            response.setResponseData(code);
        }else{
        	logger.info("请假类型信息删除失败");
        	response.setResponseData(code);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        logger.info("--------------------------------删除请假类型信息结束--------------------------------");
        return response;
    }
	
	/**
     * 删除,支持批量删除
     * @param ids
     * @return
     * @throws SQLException 
     */
    @ApiOperation(value = "delList", notes = "delete leave info")
    @RequestMapping(value = "/delList", method = RequestMethod.POST, produces = {"application/json"})
    public BaseResponse<Integer> delList(@ApiParam("批量删除的id") @RequestBody Long[] ids) throws SQLException {
        logger.info("--------------------------------批量删除请假类型信息开始--------------------------------");
        // 返回参数初始化
        BaseResponse<Integer> response = new BaseResponse<>();
		// 删除数据
        response.setResponseData(leaveTypeService.delList(ids));
        logger.info("--------------------------------批量删除请假类型信息结束--------------------------------");
        return response;
    }
}
