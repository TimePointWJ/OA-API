package com.odm.oa.mapper;

import java.util.List;

import com.odm.oa.entity.ProjectStaff;
import com.odm.oa.entity.ex.ProjectStaffEx;

public interface ProjectStaffMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table project_staff
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table project_staff
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	int insert(ProjectStaff record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table project_staff
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	ProjectStaff selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table project_staff
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	List<ProjectStaff> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table project_staff
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	int updateByPrimaryKey(ProjectStaff record);

	List<ProjectStaffEx> selectStaffsByProjectId(Long projectId);
	List<ProjectStaffEx> selectExByPositionId(Long projectId);
	int deleteByProjectId(Long id);
	ProjectStaff selectById(Long id);
}