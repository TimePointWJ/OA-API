package com.odm.oa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.odm.oa.entity.ProjectStaff;
import com.odm.oa.entity.ex.ProjectEx;
import com.odm.oa.entity.ex.ProjectStaffEx;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.service.ProjectStaffService;
import com.odm.oa.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "后台用户" })
@RestController
@RequestMapping("/service/projectStaff")
public class ProjectStaffController {
	@Autowired
	private ProjectStaffService projectStaffService;

	/**
	 * 查询部门经理，部门副经理
	 */
	@ApiOperation(value = "getProjectStaffByPositionId", notes = "add user info")
	@RequestMapping(value = "/getProjectStaffByPositionId", method = RequestMethod.POST, produces = {
			"application/json" })
	public BaseResponse<List<ProjectStaffEx>> getProjectStaffByPositionId(
			@ApiParam("员工编号") @RequestParam Long positionId) {
		// 返回参数初始化
		BaseResponse<List<ProjectStaffEx>> response = new BaseResponse<>();
		boolean valid = true;
		if (valid) {
			List<ProjectStaffEx> responseData = projectStaffService.getProjectStaffByPositionId(positionId);
			response.setResponseData(responseData);
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
		} else {
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
		}
		return response;
	}

	/**
	 * 修改项目员工到其他项目
	 * 
	 * @return
	 */
	@ApiOperation(value = "updateProjectStaff", notes = "add user info")
	@RequestMapping(value = "/updateProjectStaff", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<String> updateProjectStaff(@ApiParam("员工信息") @RequestBody ProjectStaff request) {
		// 返回参数初始化
		BaseResponse<String> response = new BaseResponse<>();
		boolean valid = true;
		if (valid) {
			projectStaffService.updateProjectStaff(request, response);
		} else {
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
		}
		return response;
	}

	/**
	 * 删除员工从项目中
	 */
	@ApiOperation(value = "deleteProjectStaff", notes = "add user info")
	@RequestMapping(value = "/deleteProjectStaff", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<String> deleteProjectStaff(@ApiParam("ids") @RequestBody Long[] ids) {
		// 返回参数初始化
		BaseResponse<String> response = new BaseResponse<>();
		boolean valid = true;
		if (valid) {
			projectStaffService.deleteProjectStaff(ids);
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
		} else {
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
		}
		return response;
	}
	
}
