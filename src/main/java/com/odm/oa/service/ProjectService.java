package com.odm.oa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.odm.oa.auth.JwtTokenUtil;
import com.odm.oa.entity.Project;
import com.odm.oa.entity.ProjectStaff;
import com.odm.oa.entity.ex.ProjectEx;
import com.odm.oa.entity.ex.ProjectStaffEx;
import com.odm.oa.mapper.ProjectMapper;
import com.odm.oa.mapper.ProjectStaffMapper;
import com.odm.oa.model.request.ProjectPagination;
import com.odm.oa.model.request.ProjectRequest;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.utils.Constants;

@Service
public class ProjectService {
	@Autowired
	private ProjectMapper projectMapper;
	@Autowired
	private ProjectStaffMapper projectStaffMapper;

	/**
	 * 查询项目信息列表
	 */
	public PageInfo<ProjectEx> pagination(ProjectPagination request) {
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		// 获取员工列表信息
		List<ProjectEx> list = projectMapper.pagination(request);
		// 查询项目的员工列表
		for (ProjectEx pEx : list) {
			pEx.setStaffs(projectStaffMapper.selectStaffsByProjectId(pEx.getId()));
		}
		PageInfo<ProjectEx> pageInfo = new PageInfo<ProjectEx>(list);
		return pageInfo;
	}

	/**
	 * 添加项目
	 */
	@Transactional(rollbackFor = Exception.class)
	public void insertProject(ProjectRequest request, BaseResponse<String> response) {
		if (checkProjectName(Constants.INSERT_FLG, request)) {
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
			response.setStatusMsg(Constants.PROJECT_NAME_ALREADY_EXIST);
		} else {
			request.setDelFlg(Constants.DEL_FLG);
			request.setVersion(Constants.VERSION);
			request.setCreateId(JwtTokenUtil.getUserIdFromContext());
			request.setCreateTime(new Date());
			projectMapper.insert(request);
			// 添加成功
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
		}
	}

	/**
	 * 修改项目
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateProject(ProjectRequest request, BaseResponse<String> response) {
		if (checkProjectName(Constants.UPDATE_FLG, request)) {
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
			response.setStatusMsg(Constants.PROJECT_NAME_ALREADY_EXIST);
		} else {
			request.setVersion(request.getVersion() + 1);
			request.setUpdateId(JwtTokenUtil.getUserIdFromContext());
			request.setUpdateTime(new Date());
			projectMapper.updateByPrimaryKey(request);
			// 修改成功
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
		}
	}

	/**
	 * 删除项目
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteProject(Long[] ids) {
		for (Long id : ids) {
			Project project = projectMapper.selectByPrimaryKey(id);
			project.setDelFlg(Constants.IS_DELETE);
			project.setVersion(project.getVersion() + 1);
			project.setUpdateId(JwtTokenUtil.getUserIdFromContext());
			project.setUpdateTime(new Date());
			projectMapper.updateByPrimaryKey(project);
			//删除关系表
			projectStaffMapper.deleteByProjectId(id);
		}
	}
	/**
	 * 验证项目名重复
	 * @param insertOrUpdateFlg
	 * @param request
	 * @return
	 */
	public boolean checkProjectName(String insertOrUpdateFlg, ProjectRequest request) {
		Project Project = projectMapper.selectByName(request.getName());
		if (Constants.INSERT_FLG.equals(insertOrUpdateFlg)) {
			// 如果是添加，只要存在相同就不行，
			if (Project != null) {
				return true;
			}
		} else if (Constants.UPDATE_FLG.equals(insertOrUpdateFlg)) {
			// 如果是修改，那就除了自己以外，如果查询的Project不是他本身，就是别人的重复
			if (Project != null && Project.getId() != request.getId()) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 给部门添加员工
	 */
	public void addStaffToProject(ProjectEx request){
		//遍历员工
		for(ProjectStaffEx pStaff:request.getAddStaffs()){
			ProjectStaff projectStaff=new ProjectStaff();
			projectStaff.setProjectId(request.getId());
			projectStaff.setStaffId(pStaff.getId());
			//员工职责
			projectStaff.setResponsibility(pStaff.getResponsibility());
			projectStaff.setDelFlg(Constants.DEL_FLG);
			projectStaff.setVersion(Constants.VERSION);
			projectStaff.setCreateId(JwtTokenUtil.getUserIdFromContext());
			projectStaff.setCreateTime(new Date());
			projectStaffMapper.insert(projectStaff);
		}
	}
}
