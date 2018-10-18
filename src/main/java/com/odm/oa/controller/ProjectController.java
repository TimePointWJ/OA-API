package com.odm.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.odm.oa.entity.ex.ProjectEx;
import com.odm.oa.model.request.ProjectPagination;
import com.odm.oa.model.request.ProjectRequest;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.service.ProjectService;
import com.odm.oa.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "项目" })
@RestController
@RequestMapping("/service/project")
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	/**
	 * 查询项目信息列表
	 * @return
	 */
	@ApiOperation(value = "pagination", notes = "add user info")
	@RequestMapping(value = "/pagination", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<PageInfo<ProjectEx>> pagination(@ApiParam("查询参数") @RequestBody ProjectPagination request) {
		// 返回参数初始化
        BaseResponse<PageInfo<ProjectEx>> response = new BaseResponse<>();
        boolean valid = true;
		if (valid) {
			PageInfo<ProjectEx> responseData=projectService.pagination(request);
			response.setResponseData(responseData);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
	}
	/**
	 * 添加项目信息
	 */
	@ApiOperation(value = "insertProject", notes = "add user info")
	@RequestMapping(value = "/insertProject", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<String> insertProject(@ApiParam("查询参数") @RequestBody ProjectRequest request) {
		// 返回参数初始化
        BaseResponse<String> response = new BaseResponse<>();
        boolean valid = true;
		if (valid) {
			projectService.insertProject(request,response);
        }else{
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
	}
	/**
	 * 修改项目信息
	 */
	@ApiOperation(value = "updateProject", notes = "add user info")
	@RequestMapping(value = "/updateProject", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<String> updateProject(@ApiParam("查询参数") @RequestBody ProjectRequest request) {
		// 返回参数初始化
		BaseResponse<String> response = new BaseResponse<>();
        boolean valid = true;
		if (valid) {
			projectService.updateProject(request,response);
        }else{
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
	}
	/**
	 * 删除项目信息
	 */
	@ApiOperation(value = "deleteProject", notes = "add user info")
	@RequestMapping(value = "/deleteProject", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<String> deleteProject(@ApiParam("ids") @RequestBody Long[] ids) {
		// 返回参数初始化
		BaseResponse<String> response = new BaseResponse<>();
        boolean valid = true;
		if (valid) {
			projectService.deleteProject(ids);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
	}
	/**
	 * 给部门添加员工
	 */
	@ApiOperation(value = "addStaffToProject", notes = "add user info")
	@RequestMapping(value = "/addStaffToProject", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<String> addStaffToProject(@ApiParam("ids") @RequestBody ProjectEx request) {
		// 返回参数初始化
		BaseResponse<String> response = new BaseResponse<>();
		boolean valid = true;
		if (valid) {
			projectService.addStaffToProject(request);
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
		} else {
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
		}
		return response;
	}
}
