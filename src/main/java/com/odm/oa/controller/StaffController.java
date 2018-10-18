package com.odm.oa.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.odm.oa.auth.JwtTokenUtil;
import com.odm.oa.entity.Staff;
import com.odm.oa.entity.ex.StaffEx;
import com.odm.oa.model.request.StaffPagination;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.model.response.UserInfo;
import com.odm.oa.service.StaffService;
import com.odm.oa.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "后台用户" })
@RestController
@RequestMapping("/service/user")
public class StaffController extends BasicController {

	@Autowired
	private StaffService staffService;

	@ApiOperation(value = "info", notes = "get user info")
	@RequestMapping(value = "/info", method = RequestMethod.GET, produces = { "application/json" })
	public BaseResponse<UserInfo> getUserInfo(HttpServletRequest request,
			@ApiParam("") @RequestParam(name = "token", required = true) String token) {
		logger.info("url:/service/user/info" + token);
		String name = JwtTokenUtil.getUsernameFromToken(token);
		logger.info("token" + token);
		// 初始化分业数据
		BaseResponse<UserInfo> result = new BaseResponse<>();
		// 一览数据设置
		result.setResponseData(staffService.getUserInfoById(name));

		logger.info("Response data" + JSONObject.toJSONString(result));
		// 返回结果
		return result;
	}
	/**
	 * 添加员工信息(员工自己注册)
	 * @return
	 */
	@ApiOperation(value = "insertStaff", notes = "add user info")
	@RequestMapping(value = "/insertStaff", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<String> insertStaff(@ApiParam("员工信息") @RequestBody Staff request) {
		// 返回参数初始化
        BaseResponse<String> response = new BaseResponse<>();
        boolean valid = true;
		if (valid) {
			staffService.insertStaff(request,response);
        }else{
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
	}
	/**
	 * 添加员工信息(管理员直接添加)
	 * @return
	 */
	@ApiOperation(value = "addStaff", notes = "add user info")
	@RequestMapping(value = "/addStaff", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<String> addStaff(@ApiParam("员工信息") @RequestBody Staff request) {
		// 返回参数初始化
        BaseResponse<String> response = new BaseResponse<>();
        boolean valid = true;
		if (valid) {
			staffService.addStaff(request,response);
        }else{
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
	}
	/**
	 * 修改员工信息
	 * @return
	 */
	@ApiOperation(value = "updateStaff", notes = "add user info")
	@RequestMapping(value = "/updateStaff", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<String> updateStaff(@ApiParam("员工信息") @RequestBody Staff request) {
		// 返回参数初始化
        BaseResponse<String> response = new BaseResponse<>();
        boolean valid = true;
		if (valid) {
			staffService.updateStaff(request,response);
        }else{
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
	}
	/**
	 * 删除员工信息
	 */
	@ApiOperation(value = "deleteStaff", notes = "add user info")
	@RequestMapping(value = "/deleteStaff", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<String> deleteStaff(@ApiParam("员工id") @RequestBody Long[] ids) {
		// 返回参数初始化
        BaseResponse<String> response = new BaseResponse<>();
        boolean valid = true;
		if (valid) {
			staffService.deleteStaff(ids);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
	}
	/**
	 * 查询员工信息列表
	 * @return
	 */
	@ApiOperation(value = "pagination", notes = "add user info")
	@RequestMapping(value = "/pagination", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<PageInfo<StaffEx>> pagination(@ApiParam("查询参数") @RequestBody StaffPagination request) {
		// 返回参数初始化
        BaseResponse<PageInfo<StaffEx>> response = new BaseResponse<>();
        boolean valid = true;
		if (valid) {
			PageInfo<StaffEx> responseData=staffService.pagination(request);
			response.setResponseData(responseData);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
	}
	/**
	 * 查询员工信息
	 * @return
	 */
	@ApiOperation(value = "selectStaffById", notes = "add user info")
	@RequestMapping(value = "/selectStaffById", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<StaffEx> selectStaffById(@ApiParam("员工编号") @RequestParam Long id) {
		// 返回参数初始化
		BaseResponse<StaffEx> response = new BaseResponse<>();
        boolean valid = true;
		if (valid) {
			StaffEx responseData=staffService.selectStaffExById(id);
			response.setResponseData(responseData);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
	}
	/**
	 * 查询员工信息
	 * @return
	 */
	@ApiOperation(value = "selectStaffByQuery", notes = "add user info")
	@RequestMapping(value = "/selectStaffByQuery", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<List<StaffEx>> selectStaffByQuery(@ApiParam("员工编号") @RequestBody StaffEx request) {
		// 返回参数初始化
		BaseResponse<List<StaffEx>> response = new BaseResponse<>();
        boolean valid = true;
		if (valid) {
			List<StaffEx> responseData=staffService.selectStaffByQuery(request);
			response.setResponseData(responseData);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
	}
	/**
	 * 查询部门经理，部门副经理
	 */
	@ApiOperation(value = "getStaffByPositionId", notes = "add user info")
	@RequestMapping(value = "/getStaffByPositionId", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<List<StaffEx>> getStaffByPositionId(@ApiParam("员工编号") @RequestParam Long positionId) {
		// 返回参数初始化
		BaseResponse<List<StaffEx>> response = new BaseResponse<>();
        boolean valid = true;
		if (valid) {
			List<StaffEx> responseData=staffService.getStaffByPositionId(positionId);
			response.setResponseData(responseData);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
	}
	/**
	 * 修改密码
	 * @return
	 */
	@ApiOperation(value = "updateUserPwd", notes = "add user info")
	@RequestMapping(value = "/updateUserPwd", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<Staff> updateUserPwd(@ApiParam("员工用户名和密码") @RequestBody Staff request) {
		// 返回参数初始化
		BaseResponse<Staff> response = new BaseResponse<>();
        boolean valid = true;
		if (valid) {
			staffService.updateUserPwd(request);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
	}
	
	/**
	 * 后台用户一览
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "userList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "后台用户一览", httpMethod = "POST", response = BaseResponse.class, notes = "查询后台用户列表")
	public BaseResponse<PageInfo<StaffEx>> userList(
			@ApiParam("输入 主键Id") @RequestBody StaffPagination staffPagination,
			HttpServletRequest request) {
		logger.info("url:/service/user/userList" + JSONObject.toJSONString(staffPagination));
		// 返回结果
		return staffService.selectUserList(staffPagination);
	}
	
	/**
	 * 后台用户详情
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "userDetail", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "后台用户详情", httpMethod = "POST", response = BaseResponse.class, notes = "查询后台用户详情")
	public BaseResponse<StaffEx> userDetail(@ApiParam("输入 查询条件") @RequestBody String staffId,
			HttpServletRequest request) {
		logger.info("url:/service/user/userDetail" + JSONObject.toJSONString(staffId));
		// 返回结果
		return staffService.userDetail(staffId);
	}
	
	@RequestMapping(value = "userUpdate", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "后台用户修改", httpMethod = "POST", response = BaseResponse.class, notes = "后台用户修改")
	public BaseResponse<String> userUpdate(@ApiParam("输入 修改内容") @RequestBody StaffEx param,
			HttpServletRequest request) {
		logger.info("url:/service/user/userUpdate" + JSONObject.toJSONString(param));
		// 返回结果
		return staffService.updateAccountByPrimaryKey(param);
	}
	
}
