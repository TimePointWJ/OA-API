package com.odm.oa.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.odm.oa.auth.JwtTokenUtil;
import com.odm.oa.entity.Module;
import com.odm.oa.entity.ModuleRight;
import com.odm.oa.entity.Staff;
import com.odm.oa.entity.SystemRole;
import com.odm.oa.entity.SystemUserRole;
import com.odm.oa.entity.ex.SystemRoleEx;
import com.odm.oa.mapper.ModuleMapper;
import com.odm.oa.mapper.ModuleRightMapper;
import com.odm.oa.mapper.StaffMapper;
import com.odm.oa.mapper.SystemRoleMapper;
import com.odm.oa.mapper.SystemUserRoleMapper;
import com.odm.oa.model.authManager.MenuTree;
import com.odm.oa.model.request.SystemRolePagination;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.utils.Constants;
import com.odm.oa.utils.DateUtils;
import com.odm.oa.utils.WebTool;

/**
 * create UserService by HJR
 * 用户信息管理service
 * 
 * 2017/12/9  下午23:30
 */
@Service
public class SystemRoleService {
	
	
	private static Logger logger = LogManager.getLogger(SystemRoleService.class);

    @Autowired
    private SystemRoleMapper roleMapper;

	@Autowired
	private StaffMapper staffMapper;
    
    @Autowired
    private ModuleMapper moduleMapper;
    
    @Autowired
    private ModuleRightMapper moduleRightMapper;

    @Autowired
    private SystemUserRoleMapper userRoleMapper;
    
    /**
	 * 查询所有后台角色的信息
	 * 
	 * @param padinatioBase
	 * @return pageInfo
	 */
	public  BaseResponse<PageInfo<SystemRoleEx>> selectRoleList(SystemRolePagination systemRolePagination) {
        PageHelper.startPage(systemRolePagination.getPageNum(), systemRolePagination.getPageSize());
		// 方法返回对象初始化
		BaseResponse<PageInfo<SystemRoleEx>> result = new BaseResponse<>();
		PageInfo<SystemRoleEx> pageInfo = new PageInfo<>();
		
    	logger.info("method: selectRoleList param：" + JSONObject.toJSONString(systemRolePagination));
    	try {
    		// 翻页信息获取
    		PageHelper.startPage(systemRolePagination.getPageNum(), systemRolePagination.getPageSize());
    		// 根据查询条件查询角色信息
    		List<SystemRoleEx> list = roleMapper.selectRoleList(systemRolePagination);
    		logger.info("method: selectRoleList result：" + JSONObject.toJSONString(list));
    		// 将查询结果设置到返回对象中
    		pageInfo.setList(list);
    		pageInfo = new PageInfo<>(list);
		} catch (Exception e) {
	    	logger.error("查询所有后台角色的信息异常", e);
		}
    	result.setResponseData(pageInfo);
		return result;
	}

	/**
	 * 查询后台角色的详细信息
	 * 
	 * @param systemRoleEx
	 * @return
	 */
	public BaseResponse<SystemRole> roleDetail(SystemRoleEx systemRoleEx) {
		logger.info("method: roleDetail param：" + JSONObject.toJSONString(systemRoleEx));
		// 方法返回对象初始化
		BaseResponse<SystemRole> result = new BaseResponse<>();
		try {
			// 根据查询条件查询角色信息
			SystemRole detail = roleMapper.selectRoleDetailByPrimaryKey(systemRoleEx.getId());
			logger.info("method: roleDetail result：" + JSONObject.toJSONString(detail));
			if(detail == null){
				logger.warn("查询后台角色的详细信息为空");
	        	result.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
			}else{
				result.setResponseData(detail);
	        	result.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);				
			}
			
		} catch (Exception e) {
			logger.error("查询后台角色的详细信息异常", e);
        	result.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
		}
		// 将查询结果设置到返回对象中
		return result;
	}
    
    /**
	 * 根据主键删除角色信息
	 * 
	 * @param param
	 * @return deleteCount
	 */
	public BaseResponse<String> deleteByPrimaryKey(SystemRoleEx param) {
    	logger.info("method: deleteByPrimaryKey param：" + JSONObject.toJSONString(param));
    	BaseResponse<String> result = new BaseResponse<>();
    	// 如果该角色上面有用户的话，不可以直接删除角色，需要改变上面用户的角色之后才能删除该角色，即删除的角色上面没有用户才可以删除
    	// 判断该角色上面是否有用户，有用户则提示显示修改用户的角色
		Staff staff=staffMapper.selectByPrimaryKey(param.getId());
		if(WebTool.isNull(staff) || WebTool.isNull(staff.getId())){
			result.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
			result.setStatusMsg("删除失败.");
			return result;
		}
    	List<SystemUserRole> userRoleList = userRoleMapper.selectAllByRoleId(staff.getId());
    	if(!WebTool.isNull(userRoleList)){
    		result.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
			result.setStatusMsg("角色上面有用户，请先处理用户再删除角色！");
    		logger.info("角色上面有用户，不能删除！");
			return result;
    	}
    	int deleteCount = 0;
    	try {
    		if(param.getId() == null){
        		logger.warn("参数为空！");
    		}else{
    			// 根据主键删除角色信息
    			deleteCount = roleMapper.deleteByPrimaryKey(param.getId());
    			result.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
    		}
    		logger.info("deleteCount：" + deleteCount);
		} catch (Exception e) {
			result.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
			result.setStatusMsg("删除失败.");
    		logger.error("根据主键删除角色信息异常", e);
		}
    	result.setResponseData(String.valueOf(deleteCount));
		return result;
	}
	
    /**
	 * 根据删除条件批量删除角色信息
	 * 
	 * @param param
	 * @return deleteCount
	 */
	public BaseResponse<String> deleteList(List<String> param) {
    	logger.info("method: deleteList param：" + JSONObject.toJSONString(param));
    	BaseResponse<String> result = new BaseResponse<>();
    	int deleteListCount = 0;
    	try {
    		// 根据删除条件批量删除角色信息
    		deleteListCount = roleMapper.deleteList(param);
    		result.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
    		logger.info("deleteListCount：" + deleteListCount);
		} catch (Exception e) {
			result.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
    		logger.error("根据删除条件批量删除角色信息异常", e);
		}
    	result.setResponseData(String.valueOf(deleteListCount));
		return result;
	}
	
    /**
	 * 添加角色信息
	 * 
	 * @param param
	 * @return deleteCount
	 */
	public BaseResponse<String> roleAdd(SystemRoleEx param) {
    	logger.info("method: addRole param：" + JSONObject.toJSONString(param));
    	BaseResponse<String> result = new BaseResponse<>();
    	int addCount = 0;
    	try {
    		param.setCreateId(JwtTokenUtil.getUserIdFromContext());
    		param.setCreateTime(DateUtils.smartFormat(DateUtils.getCurrentDateTime()));
    		//param.setUpdateId("");
    		//param.setUpdateTime(DateUtils.smartFormat(DateUtils.getCurrentDateTime()));
    		param.setDelFlg(Constants.DEL_FLG);
    		param.setVersion(Constants.VERSION);
    		// 添加角色信息
    		addCount = roleMapper.insert(param);
    		result.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
    		logger.info("addCount：" + addCount);
		} catch (Exception e) {
			result.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
    		logger.error("添加角色信息异常", e);
		}
    	result.setResponseData(String.valueOf(addCount));
		return result ;
	}
	
    /**
	 * 修改角色信息
	 * 
	 * @param param
	 * @return deleteCount
	 */
	public BaseResponse<String> roleUpdate(SystemRoleEx param) {
    	logger.info("method: updateByPrimaryKey param：" + JSONObject.toJSONString(param));
    	BaseResponse<String> result = new BaseResponse<>();
    	int updateCount = 0;
    	try {
    		// 修改角色信息
			param.setUpdateId(JwtTokenUtil.getUserIdFromContext());
			param.setUpdateTime(DateUtils.smartFormat(DateUtils.getCurrentDateTime()));
    		updateCount = roleMapper.updateRoleByPrimaryKey(param);
    		result.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
    		logger.info("updateCount：" + updateCount);
		} catch (Exception e) {
			result.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
    		logger.error("修改角色信息异常", e);
		}
    	result.setResponseData(String.valueOf(updateCount));
		return result;
	}
	
	/**
	 * 通过角色名称检索出所有的菜单信息，包括按钮
	 * 检索出来的结果全部都是可以进行操作的
	 * 只要选中叶子节点就可以了，vue tree只要叶子节点选中则根节点也会选中
	 * 菜单和一级菜单先用searched=true来标记不是叶子节点，等到赋值的时候再重新赋值回来
	 * @param roleName 角色名称
	 * @return
	 */
	public BaseResponse<Object> menuList(String roleId){
		BaseResponse<Object> result = new BaseResponse<>();
		if(WebTool.isNull(roleId)){
			result.setResponseData(null);
			return result;
		}
		roleId = roleId.substring(0,roleId.length() - 1);
		// 首先检索出所有数据然后对数据进行分开
		List<Module> moduleList = moduleMapper.selectAll();

		// 检索该角色编号下面所有的权限，然后赋值,读取该用户下所有的权限，应该把菜单和一级菜单全部去掉
		List<ModuleRight> rightList = moduleRightMapper.selectRoleByRoleId(Long.valueOf(roleId));
		// 首先检索出菜单栏，分开为菜单和一级菜单
		// 检索出按钮
		List<Module> menuList = new ArrayList<>();
		List<Module> buttonList = new ArrayList<>();
		if(WebTool.isNull(moduleList)){
			result.setResponseData(null);
			return result;
		}
		for(Module module:moduleList){
			if(!WebTool.isNull(module) && Constants.STRING_1.equals(module.getIsMenu())){
				menuList.add(module);
			}else if(!WebTool.isNull(module) && Constants.STRING_0.equals(module.getIsMenu())){
				buttonList.add(module);
			}
		}
		if(WebTool.isNull(menuList)){
			result.setResponseData(null);
			return result;
		}
		List<MenuTree> menuTreeList = new ArrayList<>();
		// 搜索菜单项
		for(Module menu:menuList){
			if(!WebTool.isNull(menu) && !WebTool.isNull(menu.getParentMenuId())
					&& menu.getParentMenuId() == 0){
				MenuTree menuTree = new MenuTree(); 
				menuTree.setId(String.valueOf(menu.getId()));
				menuTree.setLabel(menu.getModuleName());
				menuTree.setParentId(null);
				menuTree.setOpen(false);
				menuTree.setChecked(false);
				menuTree.setVisible(true);
				menuTree.setSearched(true);
				menuTreeList.add(menuTree);
			}
		}
		// 搜索一级目录
		if(!WebTool.isNull(menuTreeList)){
			for(MenuTree mainMenu:menuTreeList){
				for(Module menu:menuList){
					if(!WebTool.isNull(menu) && !WebTool.isNull(menu.getParentMenuId())
							&& String.valueOf(menu.getParentMenuId()).equals(mainMenu.getId())){
						MenuTree menuTree = new MenuTree(); 
						menuTree.setId(String.valueOf(menu.getId()));
						menuTree.setLabel(menu.getModuleName());
						menuTree.setParentId(String.valueOf(menu.getParentMenuId()));
						menuTree.setOpen(false);
						menuTree.setChecked(false);
						menuTree.setVisible(true);
						menuTree.setSearched(true);
						mainMenu.getChildren().add(menuTree);
						// 按钮处理，把按钮放在一级菜单上面
						buttonHandle(menuTree,buttonList);
					}
				}
			}
		}
		
		loopMenuTree(menuTreeList,rightList);
		List<Long> rightArray = new ArrayList<>();
		// 有权限集合
		if(!WebTool.isNull(rightList)){
			for(ModuleRight right:rightList){
				rightArray.add(right.getModuleId());
			}
		}

    	Map<String,Object> resultMap = new HashMap<>();
    	resultMap.put("right", rightArray);
    	resultMap.put("menu", JSON.toJSON(menuTreeList));
		// 循环菜单转JSON格式输出
		result.setResponseData(resultMap);
		return result;
	}
	
	
	/**
	 * 给角色添加新的权限
	 * ☆☆☆☆☆该方法必须是事务级别，否则就会出现问题
	 * @param roleName 角色名称
	 * @return
	 */
	public BaseResponse<Object> saveAuth(String[] moduleIdList){
		BaseResponse<Object> result = new BaseResponse<>();
		// 最后一位是角色ID，前面是该角色拥有的权限
		if(WebTool.isNull(moduleIdList)){
			return result;
		}
		// 需要事务分配，否则会有问题
		// 把之前的拥有的权限全部删除，然后在赋予新的权限
		String roleId = moduleIdList[moduleIdList.length - 1];
		ArrayList<String> newModuleIdList = new ArrayList<>(Arrays.asList(moduleIdList));
		newModuleIdList.remove(newModuleIdList.size() - 1);
		// 如果有必要，判断该角色的权限是否修改，如果没有修改则直接返回结果，修改则进行下面的操作，比较浪费时间，不建议使用，直接走下面逻辑
		// 首先删除该角色下面的所有权限
		moduleRightMapper.deleteByRoleId(Long.valueOf(roleId));
		
		List<ModuleRight> moduleRightList = new ArrayList<>();
		// 然后在给该角色赋值新的权限
		if(!WebTool.isNull(newModuleIdList)){
			for(String newModuleId:newModuleIdList){
				ModuleRight right = new ModuleRight();
				right.setRoleId(Long.valueOf(roleId));
				right.setModuleId(Long.valueOf(newModuleId));
				right.setRightKbn("2");
				right.setDelFlg(Constants.DEL_FLG);
				right.setVersion(1);
				right.setCreateId(JwtTokenUtil.getUserIdFromContext());
				right.setCreateTime(DateUtils.smartFormat(DateUtils.getCurrentDateTime()));
				right.setUpdateId(JwtTokenUtil.getUserIdFromContext());
				right.setUpdateTime(DateUtils.smartFormat(DateUtils.getCurrentDateTime()));
				moduleRightList.add(right);
			}
		}
		if(!WebTool.isNull(moduleRightList)){
			for(ModuleRight module:moduleRightList){
				int insertNo = moduleRightMapper.insertModuleRight(module);
				// 方案一：当插入失败的时候，这个时候会不停往数据库里面插入
				while(insertNo <= 0){
					moduleRightMapper.insertModuleRight(module);
				}
				// 方案二：当有任何一个插入失败的时候，就报插入失败错误
			}
		}
		
		result.setResponseData("数据库权限分配成功");
		return result;
	}
	
	
	// 循环菜单，对菜单已有权限赋值
	private void loopMenuTree(List<MenuTree> menuTreeList,List<ModuleRight> rightList){
		if(!WebTool.isNull(menuTreeList)){
			for(MenuTree mainMenu:menuTreeList){
				distributionAuth(mainMenu,rightList);
			}
		}
	}
	
	// 判断该菜单是否为选中状态
	// 
	private void distributionAuth(MenuTree mainMenu,List<ModuleRight> rightList){
		if(!WebTool.isNull(rightList) && !WebTool.isNull(mainMenu)){
			for(ModuleRight right:rightList){
				if(!WebTool.isNull(right) && !WebTool.isNull(mainMenu.getId())
						&& mainMenu.getId().equals(String.valueOf(right.getModuleId()))){
					if(!mainMenu.isSearched()){
						mainMenu.setChecked(true);
					}else{
						mainMenu.setSearched(false);
					}
					if(WebTool.isNull(mainMenu.getChildren())){
						return ;
					}else{
						loopMenuTree(mainMenu.getChildren(),rightList);
					}
				}
			}
		}
	}
	
	/**
	 * 把按钮放在一级目录里面
	 * @param menuTree
	 * @param buttonList
	 */
	private void buttonHandle(MenuTree menuTree,List<Module> buttonList){
		for(Module button:buttonList){
			if(!WebTool.isNull(button) && !WebTool.isNull(button.getParentMenuId())
					&& String.valueOf(button.getParentMenuId()).equals(menuTree.getId())){
				MenuTree buttonTree = new MenuTree(); 
				buttonTree.setId(String.valueOf(button.getId()));
				buttonTree.setLabel(button.getModuleName());
				buttonTree.setParentId(String.valueOf(button.getParentMenuId()));
				buttonTree.setOpen(false);
				buttonTree.setChecked(false);
				buttonTree.setVisible(true);
				buttonTree.setSearched(false);
				menuTree.getChildren().add(buttonTree);
			}
		}
	}
	public BaseResponse<List<SystemRole>> getRole(){
		BaseResponse<List<SystemRole>> response=new BaseResponse<>();
		response.setResponseData(roleMapper.selectAll());
		return response;
	}
    
}
