package com.odm.oa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.odm.oa.entity.SystemRole;
import com.odm.oa.entity.ex.SystemRoleEx;
import com.odm.oa.model.request.SystemRolePagination;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.service.SystemRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags={"后台角色"})
@RestController
@RequestMapping("/service/role")
public class SystemRoleController extends BasicController{

    @Autowired
    private SystemRoleService roleService;

	/**
	 * 后台角色一览
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping( value = "roleList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "后台角色一览", httpMethod = "POST", response = BaseResponse.class, notes = "查询后台角色列表")
	public BaseResponse<PageInfo<SystemRoleEx>> roleList(@ApiParam("输入 查询条件") 
	@RequestBody SystemRolePagination systemRolePagination, HttpServletRequest request) {
		logger.info("url:/service/role/roleList" + JSONObject.toJSONString(systemRolePagination));
        // 返回结果
		return roleService.selectRoleList(systemRolePagination);
	}
    
	/**
	 * 后台角色详情
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping( value = "roleDetail", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "后台角色详情", httpMethod = "POST", response = BaseResponse.class, notes = "查询后台角色详情")
	public BaseResponse<SystemRole> roleDetail(@ApiParam("输入 主键Id") 
	@RequestBody SystemRoleEx systemRoleEx, HttpServletRequest request) {
		logger.info("url:/service/role/roleDetail" + JSONObject.toJSONString(systemRoleEx));
        // 返回结果
		return roleService.roleDetail(systemRoleEx);
	}
    
	/**
	 * 后台角色添加
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping( value = "roleAdd", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "后台角色添加", httpMethod = "POST", response = BaseResponse.class, notes = "后台角色添加")
	public BaseResponse<String> roleAdd(@ApiParam("输入 添加内容") 
	@RequestBody SystemRoleEx param, HttpServletRequest request) {
		logger.info("url:/service/role/roleAdd" + JSONObject.toJSONString(param));
		// 返回结果
		return roleService.roleAdd(param);
	}

	/**
	 * 后台角色修改
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping( value = "roleUpdate", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "后台角色修改", httpMethod = "POST", response = BaseResponse.class, notes = "后台角色修改")
	public BaseResponse<String> roleUpdate(@ApiParam("输入 修改内容") 
	@RequestBody SystemRoleEx param, HttpServletRequest request) {
		logger.info("url:/service/role/roleUpdate" + JSONObject.toJSONString(param));
        // 返回结果
		return roleService.roleUpdate(param);
	}
	
	/**
	 * 后台角色删除
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping( value = "roleDelete", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "后台角色删除", httpMethod = "POST", response = BaseResponse.class, notes = "后台角色删除")
	public BaseResponse<String> roleDelete(@ApiParam("输入 删除条件") 
	@RequestBody SystemRoleEx param, HttpServletRequest request) {
		logger.info("url:/service/role/roleDelete" + param);
        // 返回结果
		return roleService.deleteByPrimaryKey(param);
	}
	
	/**
	 * 后台角色批量删除
	 * 
	 * @param param
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping( value = "deleteList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "后台角色批量删除", httpMethod = "POST", response = BaseResponse.class, notes = "后台角色批量删除")
	public BaseResponse<String> deleteList(@ApiParam("输入 批量删除条件") 
	@RequestBody List<String> param, HttpServletRequest request) {
		logger.info("url:/service/role/roleList" + JSONObject.toJSONString(param));
        // 返回结果
		return roleService.deleteList(param);
	}
	
	/**
	 * 通过角色名称获取菜单一览，包括对按钮的检索
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping( value = "menuList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "菜单一览", httpMethod = "POST", response = BaseResponse.class, notes = "菜单一览")
	public BaseResponse<Object> menuList(@ApiParam("角色ID") @RequestBody String id,HttpServletRequest request){
		logger.info("url:/service/role/menuList");
		
		return roleService.menuList(id);
	}
	
	/**
	 * 通过角色名称获取菜单一览，包括对按钮的检索
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping( value = "saveAuth", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "保存权限", httpMethod = "POST", response = BaseResponse.class, notes = "保存权限")
	public BaseResponse<Object> saveAuth(@ApiParam("角色ID") @RequestBody String[] moduleIdList,HttpServletRequest request){
		logger.info("url:/service/role/menuList");
		
		return roleService.saveAuth(moduleIdList);
	}
	/**
	 * 获取角色下拉框
	 *
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping( value = "getRole", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "getRole", httpMethod = "POST", response = BaseResponse.class, notes = "getRole")
	public BaseResponse<List<SystemRole>> getRole(){
		logger.info("url:/service/role/getRole");

		return roleService.getRole();
	}
	
}
