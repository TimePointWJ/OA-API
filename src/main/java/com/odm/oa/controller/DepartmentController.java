package com.odm.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.odm.oa.entity.ex.DepartmentEx;
import com.odm.oa.model.request.DepartmentPagination;
import com.odm.oa.model.request.DepartmentRequest;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.service.DepartmentService;
import com.odm.oa.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "部门" })
@RestController
@RequestMapping("/service/dept")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	/**
	 * 查询部门信息列表
	 * @return
	 */
	@ApiOperation(value = "pagination", notes = "add user info")
	@RequestMapping(value = "/pagination", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<PageInfo<DepartmentEx>> pagination(@ApiParam("查询参数") @RequestBody DepartmentPagination request) {
		// 返回参数初始化
        BaseResponse<PageInfo<DepartmentEx>> response = new BaseResponse<>();
        boolean valid = true;
		if (valid) {
			PageInfo<DepartmentEx> responseData=departmentService.pagination(request);
			response.setResponseData(responseData);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
	}
	/**
	 * 添加部门信息
	 */
	@ApiOperation(value = "insertDept", notes = "add user info")
	@RequestMapping(value = "/insertDept", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<String> insertDept(@ApiParam("查询参数") @RequestBody DepartmentRequest request) {
		// 返回参数初始化
        BaseResponse<String> response = new BaseResponse<>();
        boolean valid = true;
		if (valid) {
			departmentService.insertDept(request,response);
        }else{
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
	}
	/**
	 * 修改部门信息
	 */
	@ApiOperation(value = "updateDept", notes = "add user info")
	@RequestMapping(value = "/updateDept", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<String> updateDept(@ApiParam("查询参数") @RequestBody DepartmentRequest request) {
		// 返回参数初始化
		BaseResponse<String> response = new BaseResponse<>();
        boolean valid = true;
		if (valid) {
			departmentService.updateDept(request,response);
        }else{
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
	}
	/**
	 * 删除部门信息
	 */
	@ApiOperation(value = "deleteDept", notes = "add user info")
	@RequestMapping(value = "/deleteDept", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<String> deleteDept(@ApiParam("查询参数") @RequestBody Long[] ids) {
		// 返回参数初始化
		BaseResponse<String> response = new BaseResponse<>();
        boolean valid = true;
		if (valid) {
			departmentService.deleteDept(ids);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
	}
}
