package com.odm.oa.service;

import java.util.ArrayList;
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
import com.odm.oa.auth.JwtTokenUtil;
import com.odm.oa.entity.Department;
import com.odm.oa.entity.Staff;
import com.odm.oa.entity.SystemUserRole;
import com.odm.oa.entity.ex.StaffEx;
import com.odm.oa.mapper.DepartmentMapper;
import com.odm.oa.mapper.StaffMapper;
import com.odm.oa.mapper.SystemRoleMapper;
import com.odm.oa.mapper.SystemUserRoleMapper;
import com.odm.oa.model.request.StaffPagination;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.model.response.LoginToken;
import com.odm.oa.model.response.UserInfo;
import com.odm.oa.utils.Constants;
import com.odm.oa.utils.DateUtils;
import com.odm.oa.utils.WebTool;

/**
 * create UserService by HJR 用户信息管理service
 * 
 * 2017/12/9 下午23:30
 */
@Service
public class StaffService {

	private static Logger logger = LogManager.getLogger(StaffService.class);

	@Autowired
	private StaffMapper staffMapper;
	@Autowired
	private SystemUserRoleMapper userRoleMapper;
	@Autowired
	private SystemUserRoleMapper systemUserRoleMapper;
	@Autowired
	private SystemRoleMapper systemRoleMapper;
	@Autowired
	private DepartmentMapper departmentMapper;

	/**
	 * 获取用户信息，并判断用户是否存在，一家密码是否正确
	 * 
	 * @param param
	 * @return
	 */
	public BaseResponse<LoginToken> validateLogin(Staff param) {

		logger.info("method: validateLogin param：" + JSONObject.toJSONString(param));

		// 返回参数初始化
		BaseResponse<LoginToken> res = new BaseResponse<>();
		try {
			// 通过用户名查询用户信息
			Staff user = staffMapper.selectStaffByStaffNo(param);
			// 判断渠道的用户信息是否为空
			if (user == null) {
				// 返回错误信息
				res.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
				res.setStatusMsg("用户名或密码不正确");
			} else if ("1".equals(user.getCheckStatus())) {
				// 返回错误信息
				res.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED_203);
				res.setStatusMsg(Constants.STAFF_NO_CHECK_STATUS);
			} else {
				logger.info("通过用户名查询用户信息：" + JSONObject.toJSONString(user));
				// 将用户信息转换成JSON
				String userStr = JSONObject.toJSONString(user);
				// 将JSON转换成返回参数中的对象
				LoginToken token = JSONObject.parseObject(userStr, LoginToken.class);
				String pwd = user.getPassword();
				// 判断用户密码是否正确
				if (!pwd.equals(param.getPassword())) {
					// 返回错误信息
					res.setStatusCode(201);
					res.setStatusMsg("用户名或密码不正确");
				} else {
					// 获取并设置token信息到返回参数中
					// String tokenStr =
					// JwtTokenUtil.generateToken(param.getAccount());
					String tokenStr = JwtTokenUtil.generateToken(param.getStaffNo());
					logger.info(" 获取并设置tokentokenStr:" + tokenStr);
					token.setToken(tokenStr);
					res.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
					res.setResponseData(token);
				}
			}
		} catch (Exception e) {
			logger.error("通过用户名查询用户信息异常", e);
		}
		// 返回最终结果
		return res;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserInfo getUserInfoById(String userId) {
		logger.info("用户ID：" + userId);
		UserInfo info = new UserInfo();
		info.setName(userId);
		// 第一，现根据用户取角色，然后根据角色取权限，把全部权限拿到之后，权限去重
		// 第二，在代码中直接一步实现，去重也实现
		List<String> privilegeList = staffMapper.selectPrivilegeByUsrId(userId);
		// List<String> privilegeList = new ArrayList<String>();
		// privilegeList.add("admin");
		String[] roleList = privilegeList.toArray(new String[privilegeList.size()]);
		info.setRoles(roleList);
		// 获取用户数据权限
		// SystemAccount user=userMapper.selectByUserId(userId);
		// getUserDataRole(user,info);
		return info;
	}

	// /**
	// * 用户登录成功，获取用户data权限
	// */
	// public void getUserDataRole(SystemAccount user,UserInfo info) {
	// // 1:代理人 2：学校教职工 3：系统管理员
	// String accountType = user.getAccountType();
	// if (Constants.ACCOUNT_TYPE_AGENT.equals(accountType)) {// 代理人
	// agentLogin(user,info);
	// } else if (Constants.ACCOUNT_TYPE_SCHOOL_STAFF.equals(accountType)) {//
	// 学校教职工
	// schoolStaffLogin(user,info);
	// } else if (Constants.ACCOUNT_TYPE_SYSTEM_ADMIN.equals(accountType)) {//
	// 系统管理员
	//
	// }
	// }
	/**
	 * 查询员工信息列表
	 */
	public PageInfo<StaffEx> pagination(StaffPagination request) {
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		// 获取员工列表信息
		List<StaffEx> list = staffMapper.pagination(request);
		PageInfo<StaffEx> pageInfo = new PageInfo<StaffEx>(list);
		return pageInfo;
	}

	/**
	 * 添加员工信息(员工自己注册)
	 */
	@Transactional(rollbackFor = Exception.class)
	public void insertStaff(Staff request, BaseResponse<String> response) {
		if (checkStaffMobilePhone(Constants.INSERT_FLG, request)) {
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
			response.setStatusMsg(Constants.MOBILE_PHONE_ALREADY_EXIST);
		} else {
			request.setPassword(Constants.DEFAULT_USER_PASSWORD);
			request.setCheckStatus(Constants.NO_CKECK);// 未审核
			request.setDelFlg(Constants.DEL_FLG);
			request.setVersion(Constants.VERSION);
			request.setCreateId("0");// 新员工自己添加的
			request.setCreateTime(new Date());
			staffMapper.insert(request);
			// 添加成功
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
		}
	}

	/**
	 * 添加员工信息(管理员直接添加)
	 */
	@Transactional(rollbackFor = Exception.class)
	public void addStaff(Staff request, BaseResponse<String> response) {
		if (checkStaffMobilePhone(Constants.UPDATE_FLG, request)) {
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
			response.setStatusMsg(Constants.MOBILE_PHONE_ALREADY_EXIST);
		} else {
			//生成工号
			request.setStaffNo(getNewStaffNo());
			request.setPassword(Constants.DEFAULT_USER_PASSWORD);
			request.setCheckStatus(Constants.IS_CKECK);// 已审核
			request.setDelFlg(Constants.DEL_FLG);
			request.setVersion(Constants.VERSION);
			request.setCreateId(JwtTokenUtil.getUserIdFromContext());// 新员工自己添加的
			request.setCreateTime(new Date());
			staffMapper.insert(request);
			// 添加用户角色
			addOrUpdateRole(request);
			// 修改成功
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
		}
	}

	/**
	 * 修改员工信息
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateStaff(Staff request, BaseResponse<String> response) {
		if (checkStaffMobilePhone(Constants.UPDATE_FLG, request)) {
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
			response.setStatusMsg(Constants.MOBILE_PHONE_ALREADY_EXIST);
		} else {
			//如果是审核的话，添加工号，
			if(request.getStaffNo()==null||"".equals(request.getStaffNo())){
				request.setStaffNo(getNewStaffNo());
			}
			//check工号
			if(checkStaffNo(Constants.UPDATE_FLG,request)){
				response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
				response.setStatusMsg(Constants.STAFF_NO_ALREADY_EXIST);
			}else{
				request.setCheckStatus(Constants.IS_CKECK);
				request.setVersion(request.getVersion() + 1);
				request.setUpdateId(JwtTokenUtil.getUserIdFromContext());
				request.setUpdateTime(new Date());
				staffMapper.updateByPrimaryKey(request);
				//如果修改的是部门经理或者部门副经理，维护部门表，
				updateDept(request);
				// 修改用户角色
				addOrUpdateRole(request);
				// 修改成功
				response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
			}
		}
	}

	/**
	 * 根据职位id添加用户角色
	 */
	public void addOrUpdateRole(Staff request) {
		SystemUserRole systemUserRole = systemUserRoleMapper.selectAllByStaffId(request.getId());
		if (systemUserRole != null) {
			long roleId = systemRoleMapper.selectRoleDetailByRoleType(request.getPositionId()).get(0).getId();
			systemUserRole.setRoleId(roleId);
			systemUserRole.setVersion(systemUserRole.getVersion() + 1);
			systemUserRole.setUpdateId(JwtTokenUtil.getUserIdFromContext());
			systemUserRole.setUpdateTime(new Date());
			systemUserRoleMapper.updateByPrimaryKey(systemUserRole);
		} else {
			systemUserRole = new SystemUserRole();
			systemUserRole.setSystemAccountId(request.getId());
			long roleId = systemRoleMapper.selectRoleDetailByRoleType(request.getPositionId()).get(0).getId();
			systemUserRole.setRoleId(roleId);
			systemUserRole.setDelFlg(Constants.DEL_FLG);
			systemUserRole.setVersion(Constants.VERSION);
			systemUserRole.setCreateId(JwtTokenUtil.getUserIdFromContext());
			systemUserRole.setCreateTime(new Date());
			systemUserRoleMapper.insert(systemUserRole);
		}
	}

	/**
	 * 删除员工信息
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteStaff(Long[] ids) {
		for (Long id : ids) {
			Staff staff = staffMapper.selectByPrimaryKey(id);
			staff.setDelFlg(Constants.IS_DELETE);
			staff.setVersion(staff.getVersion() + 1);
			staff.setUpdateId(JwtTokenUtil.getUserIdFromContext());
			staff.setUpdateTime(new Date());
			staffMapper.updateByPrimaryKey(staff);
			//如果删除的是部门经理或者部门副经理，维护部门表，
			updateDept(staff);
		}
	}
	/**
	 * 如果删除的是部门经理或者部门副经理，维护部门表，
	 */
	public void updateDept(Staff request){
		//如果是部门经理或副经理的话，如果移动部门，则去掉部门的部门经理
		if(request.getPositionId()!=null&&Constants.DEPT_MANAGER.equals(request.getPositionId())){
			//根据部门经理id查询部门
			Department department=departmentMapper.selectByManagerId(request.getId());
			//如果修改后的departmentId  不等于查询出来的部门的id 那么清掉部门的经理
			if(department!=null&&department.getId()!=request.getDepartmentId()){
				department.setManagerId(null);
				department.setVersion(department.getVersion()+1);
				department.setUpdateId(JwtTokenUtil.getUserIdFromContext());
				department.setUpdateTime(new Date());
				departmentMapper.updateByPrimaryKey(department);
			}
		}else if(request.getPositionId()!=null&&Constants.DEPT_ASSISTANT_MANAGER.equals(request.getPositionId())){
			//根据部门副经理id查询部门
			Department department=departmentMapper.selectByAssistantManagerId(request.getId());
			//如果修改后的departmentId  不等于查询出来的部门的id 那么清掉部门的副经理
			if(department!=null&&department.getId()!=request.getDepartmentId()){
				department.setAssistantManagerId(null);
				department.setVersion(department.getVersion()+1);
				department.setUpdateId(JwtTokenUtil.getUserIdFromContext());
				department.setUpdateTime(new Date());
				departmentMapper.updateByPrimaryKey(department);
			}
		}
	}
	/**
	 * 根据id查询员工信息
	 */
	public StaffEx selectStaffExById(Long id) {
		return staffMapper.selectExByPrimaryKey(id);
	}
	/**
	 * 根据query查询员工信息
	 */
	public List<StaffEx> selectStaffByQuery(StaffEx request) {
		return staffMapper.selectStaffByQuery(request);
	}

	/**
	 * 查询部门经理，部门副经理
	 */
	public List<StaffEx> getStaffByPositionId(Long positionId) {
		return staffMapper.selectExByPositionId(positionId);
	}

	/**
	 * 修改员工密码
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateUserPwd(Staff request) {
		Staff staff = staffMapper.selectStaffByStaffNo(request);
		staff.setUpdatePasswordFlg(Constants.UPDATE_PASSWORD_FLG);
		staff.setPassword(request.getPassword());
		staff.setCheckStatus(Constants.IS_CKECK);
		staff.setVersion(staff.getVersion() + 1);
		staff.setUpdateId(JwtTokenUtil.getUserIdFromContext());
		staff.setUpdateTime(new Date());
		staffMapper.updateByPrimaryKey(staff);
	}
	/**
	 * 添加，修改员工的时候，手机号check,
	 * 
	 * @param mobilePhone
	 * @param insertOrUpdateFlg
	 *            添加修改标识
	 * @return false：正常，true:已存在
	 */
	public boolean checkStaffMobilePhone(String insertOrUpdateFlg, Staff request) {
		Staff staff = staffMapper.selectByMobilePhone(request.getMobilePhone());
		if (Constants.INSERT_FLG.equals(insertOrUpdateFlg)) {
			// 如果是添加，只要存在相同就不行，
			if (staff != null) {
				return true;
			}
		} else if (Constants.UPDATE_FLG.equals(insertOrUpdateFlg)) {
			// 如果是修改，那就除了自己以外，如果查询的staff不是他本身，就是别人的重复
			if (staff != null && staff.getId() != request.getId()) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 验证工号存在，
	 * @param insertOrUpdateFlg
	 * @param request
	 * @return
	 */
	public boolean checkStaffNo(String insertOrUpdateFlg,Staff request){
		Staff staff = staffMapper.selectStaffByStaffNo(request);
		if (Constants.INSERT_FLG.equals(insertOrUpdateFlg)) {
			// 如果是添加，只要存在相同就不行，
			if (staff != null) {
				return true;
			}
		} else if (Constants.UPDATE_FLG.equals(insertOrUpdateFlg)) {
			// 如果是修改，那就除了自己以外，如果查询的staff不是他本身，就是别人的重复
			if (staff != null && staff.getId() != request.getId()) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 生成新的工号，自增,TODO：目前只支持纯数字的工号
	 * @return
	 */
	public String getNewStaffNo(){
		String staffNo=staffMapper.selectMaxStaffNo();
		int sl=Integer.parseInt(staffNo);
		int len=staffNo.length()-(sl+"").length();
		String result="";
		for(int i=0;i<len;i++){
			result+="0";
		}
		result+=++sl;
		return result;
	}
	/**
	 * 查询所有后台账号的信息
	 * 
	 * @param padinatioBase
	 * @return pageInfo
	 */
	public BaseResponse<PageInfo<StaffEx>> selectUserList(StaffPagination staffPagination) {
		logger.info("method: selectUserList param：" + JSONObject.toJSONString(staffPagination));
		PageHelper.startPage(staffPagination.getPageNum(), staffPagination.getPageSize());
		// 方法返回对象初始化
		BaseResponse<PageInfo<StaffEx>> result = new BaseResponse<>();
		PageInfo<StaffEx> pageInfo = new PageInfo<>();

		try {
			// 翻页信息获取
			PageHelper.startPage(staffPagination.getPageNum(), staffPagination.getPageSize());
			// 根据查询条件查询用户信息
			List<StaffEx> list = new ArrayList<>();
			if (WebTool.isNull(staffPagination)) {
				result.setResponseData(null);
				return result;
			}
			list = staffMapper.selectStaffInfo(staffPagination);
			logger.info("method: selectUserList result：" + JSONObject.toJSONString(list));
			// 将查询结果设置到返回对象中
			pageInfo = new PageInfo<>(list);

		} catch (Exception e) {
			logger.error("查询所有后台账号的信息异常", e);
		}
		result.setResponseData(pageInfo);
		return result;
	}

	/**
	 * 查询后台账号的详细信息
	 * 
	 * @param systemAccountEx
	 * @return
	 */
	public BaseResponse<StaffEx> userDetail(String staffId) {
		logger.info("method: userDetail param：" + JSONObject.toJSONString(staffId));
		// 方法返回对象初始化
		BaseResponse<StaffEx> result = new BaseResponse<>();
		if (WebTool.isNull(staffId)) {
			return result;
		}
		staffId = staffId.replaceAll("=", "");
		try {
			// 根据查询条件查询用户信息
			StaffEx detail = staffMapper.selectStaffByPrimaryKey(Long.valueOf(staffId));
			logger.info("method: userDetail result：" + JSONObject.toJSONString(detail));
			if (detail == null) {
				logger.warn("查询后台账号的详细信息为空");
				result.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
			} else {
				result.setResponseData(detail);
				result.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
			}

		} catch (Exception e) {
			logger.error("查询后台账号的详细信息异常", e);
			result.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
		}
		// 将查询结果设置到返回对象中
		return result;
	}

	/**
	 * 修改用户角色，从而控制权限问题 由于前端问题，目前先用accountType来代替角色类型
	 * 用户修改角色的实质是通过修改用户角色表来达到，通过该用户对应不同的角色，从而也就拥有不同的权限（权限是分配给角色的）
	 * 
	 * @param param
	 * @return deleteCount
	 */
	public BaseResponse<String> updateAccountByPrimaryKey(StaffEx param) {
		logger.info("method: updateByPrimaryKey param：" + JSONObject.toJSONString(param));
		BaseResponse<String> result = new BaseResponse<>();
		int updateCount = 0;
		if (WebTool.isNull(param) || WebTool.isNull(param.getId()) || WebTool.isNull(param.getRoleCode())) {
			result.setResponseData(String.valueOf(updateCount));
			return result;
		}
		try {
			// 首先判断该用户是否关联到角色，如果没有关联角色，则插入一条用户和角色关联的信息
			// 如果有用户权限表，则更新用户权限表
			StaffEx detail = staffMapper.selectStaffByPrimaryKey(param.getId());
			if (WebTool.isNull(detail)) {
				result.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
				return result;
			}
			// 如果有该用户的角色，则需要更新，无则需要插入一条数据
			SystemUserRole usrRole = userRoleMapper.selectAllByStaffId(param.getId());
			SystemUserRole userRole = new SystemUserRole();
			if (WebTool.isNull(usrRole) || WebTool.isNull(usrRole.getId())) {
				userRole.setRoleId(Long.valueOf(param.getRoleCode()));
				userRole.setSystemAccountId(param.getId());
				userRole.setDelFlg(false);
				userRole.setCreateId(JwtTokenUtil.getUserIdFromContext());
				userRole.setCreateTime(DateUtils.smartFormat(DateUtils.getCurrentDateTime()));
				userRole.setUpdateId(JwtTokenUtil.getUserIdFromContext());
				userRole.setUpdateTime(DateUtils.smartFormat(DateUtils.getCurrentDateTime()));
				updateCount = userRoleMapper.insertUserRole(userRole);
			} else {
				// 更新
				userRole.setRoleId(Long.valueOf(param.getRoleCode()));
				userRole.setSystemAccountId(param.getId());
				userRole.setCreateId(JwtTokenUtil.getUserIdFromContext());
				userRole.setCreateTime(DateUtils.smartFormat(DateUtils.getCurrentDateTime()));
				userRole.setUpdateId(JwtTokenUtil.getUserIdFromContext());
				userRole.setUpdateTime(DateUtils.smartFormat(DateUtils.getCurrentDateTime()));
				updateCount = userRoleMapper.updateByRoleId(userRole);
			}

			// 修改用户信息
			result.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
			logger.info("updateCount：" + updateCount);
		} catch (Exception e) {
			logger.error("修改用户信息异常", e);
			result.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
		}
		result.setResponseData(String.valueOf(updateCount));
		return result;
	}
}
