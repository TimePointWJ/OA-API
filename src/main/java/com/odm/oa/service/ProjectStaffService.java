package com.odm.oa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odm.oa.auth.JwtTokenUtil;
import com.odm.oa.entity.Project;
import com.odm.oa.entity.ProjectStaff;
import com.odm.oa.entity.ex.ProjectEx;
import com.odm.oa.entity.ex.ProjectStaffEx;
import com.odm.oa.mapper.ProjectMapper;
import com.odm.oa.mapper.ProjectStaffMapper;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.utils.Constants;

@Service
public class ProjectStaffService {
	@Autowired
	private ProjectStaffMapper projectStaffMapper;
	@Autowired
	private ProjectMapper projectMapper;
	/**
	 * 查询项目经理，副经理
	 */			
	public List<ProjectStaffEx> getProjectStaffByPositionId(Long positionId){
		return projectStaffMapper.selectExByPositionId(positionId);
	}
	/**
	 * 修改项目员工
	 * @param request
	 * @param response
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateProjectStaff(ProjectStaff request,BaseResponse<String> response){
		request.setVersion(request.getVersion()+1);
		request.setUpdateId(JwtTokenUtil.getUserIdFromContext());
		request.setUpdateTime(new Date());
		projectStaffMapper.updateByPrimaryKey(request);
		//如果修改的是部门经理，和部门副经理，维护关系表
		updateProject(request);
	}
	/**
	 * 从项目删除员工
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteProjectStaff(Long[] ids){
		for (Long id : ids) {
			ProjectStaff pStaff = projectStaffMapper.selectById(id);
			pStaff.setDelFlg(Constants.IS_DELETE);
			pStaff.setVersion(pStaff.getVersion() + 1);
			pStaff.setUpdateId(JwtTokenUtil.getUserIdFromContext());
			pStaff.setUpdateTime(new Date());
			projectStaffMapper.updateByPrimaryKey(pStaff);
			//如果删除的是部门经理，和部门副经理，维护关系表
			updateProject(pStaff);
		}
	}
	/**
	 * 如果修改或删除的是部门经理，和部门副经理，维护关系表
	 */
	public void updateProject(ProjectStaff request){
		if(request.getPositionId()!=null&&Constants.DEPT_MANAGER.equals(request.getPositionId())){
			//根据项目经理id查询项目
			Project project=projectMapper.selectByManagerId(request.getId());
			//如果修改后的departmentId  不等于查询出来的项目的id 那么清掉项目的经理
			if(project!=null&&project.getId()!=request.getDepartmentId()){
				project.setManagerId(null);
				project.setVersion(project.getVersion()+1);
				project.setUpdateId(JwtTokenUtil.getUserIdFromContext());
				project.setUpdateTime(new Date());
				projectMapper.updateByPrimaryKey(project);
			}
		}else if(request.getPositionId()!=null&&Constants.DEPT_ASSISTANT_MANAGER.equals(request.getPositionId())){
			//根据项目副经理id查询项目
			Project project=projectMapper.selectByAssistantManagerId(request.getId());
			//如果修改后的departmentId  不等于查询出来的项目的id 那么清掉项目的副经理
			if(project!=null&&project.getId()!=request.getDepartmentId()){
				project.setAssistantManagerId(null);
				project.setVersion(project.getVersion()+1);
				project.setUpdateId(JwtTokenUtil.getUserIdFromContext());
				project.setUpdateTime(new Date());
				projectMapper.updateByPrimaryKey(project);
			}
		}
	}
	
}
