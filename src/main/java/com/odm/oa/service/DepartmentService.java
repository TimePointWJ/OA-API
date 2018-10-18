package com.odm.oa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.odm.oa.auth.JwtTokenUtil;
import com.odm.oa.entity.Department;
import com.odm.oa.entity.ex.DepartmentEx;
import com.odm.oa.mapper.DepartmentMapper;
import com.odm.oa.mapper.StaffMapper;
import com.odm.oa.model.request.DepartmentPagination;
import com.odm.oa.model.request.DepartmentRequest;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.utils.Constants;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private StaffMapper staffMapper;

	/**
	 * 查询部门信息列表
	 */
	public PageInfo<DepartmentEx> pagination(DepartmentPagination request) {
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		// 获取员工列表信息
		List<DepartmentEx> list = departmentMapper.pagination(request);
		// 查询部门的员工列表
		for (DepartmentEx dEx : list) {
			dEx.setStaffs(staffMapper.selectStaffsByDepartmentId(dEx.getId()));
		}
		PageInfo<DepartmentEx> pageInfo = new PageInfo<DepartmentEx>(list);
		return pageInfo;
	}

	/**
	 * 添加部门
	 */
	@Transactional(rollbackFor = Exception.class)
	public void insertDept(DepartmentRequest request, BaseResponse<String> response) {
		if (checkDeptName(Constants.INSERT_FLG, request)) {
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
			response.setStatusMsg(Constants.DEPT_NAME_ALREADY_EXIST);
		} else {
			request.setDelFlg(Constants.DEL_FLG);
			request.setVersion(Constants.VERSION);
			request.setCreateId(JwtTokenUtil.getUserIdFromContext());// 新员工自己添加的
			request.setCreateTime(new Date());
			departmentMapper.insert(request);
			// 添加成功
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
		}
	}

	/**
	 * 修改部门
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateDept(DepartmentRequest request, BaseResponse<String> response) {
		if (checkDeptName(Constants.UPDATE_FLG, request)) {
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
			response.setStatusMsg(Constants.DEPT_NAME_ALREADY_EXIST);
		} else {
			request.setVersion(request.getVersion() + 1);
			request.setUpdateId(JwtTokenUtil.getUserIdFromContext());
			request.setUpdateTime(new Date());
			departmentMapper.updateByPrimaryKey(request);
			// 修改成功
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
		}
	}

	/**
	 * 删除部门
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteDept(Long[] ids) {
		for (Long id : ids) {
			Department dept = departmentMapper.selectByPrimaryKey(id);
			dept.setDelFlg(Constants.IS_DELETE);
			dept.setVersion(dept.getVersion() + 1);
			dept.setUpdateId(JwtTokenUtil.getUserIdFromContext());
			dept.setUpdateTime(new Date());
			departmentMapper.updateByPrimaryKey(dept);
		}
	}

	public boolean checkDeptName(String insertOrUpdateFlg, DepartmentRequest request) {
		Department dept = departmentMapper.selectByName(request.getName());
		if (Constants.INSERT_FLG.equals(insertOrUpdateFlg)) {
			// 如果是添加，只要存在相同就不行，
			if (dept != null) {
				return true;
			}
		} else if (Constants.UPDATE_FLG.equals(insertOrUpdateFlg)) {
			// 如果是修改，那就除了自己以外，如果查询的dept不是他本身，就是别人的重复
			if (dept != null && dept.getId() != request.getId()) {
				return true;
			}
		}
		return false;
	}
}
