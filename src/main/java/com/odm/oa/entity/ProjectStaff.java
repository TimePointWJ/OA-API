package com.odm.oa.entity;

import java.util.Date;

public class ProjectStaff extends Staff{

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column project_staff.id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	private Long id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column project_staff.project_id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	private Long projectId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column project_staff.staff_id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	private Long staffId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column project_staff.responsibility
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	private String responsibility;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column project_staff.del_flg
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	private Boolean delFlg;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column project_staff.version
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	private Integer version;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column project_staff.create_id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	private String createId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column project_staff.create_time
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column project_staff.update_id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	private String updateId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column project_staff.update_time
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	private Date updateTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column project_staff.id
	 * @return  the value of project_staff.id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column project_staff.id
	 * @param id  the value for project_staff.id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column project_staff.project_id
	 * @return  the value of project_staff.project_id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public Long getProjectId() {
		return projectId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column project_staff.project_id
	 * @param projectId  the value for project_staff.project_id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column project_staff.staff_id
	 * @return  the value of project_staff.staff_id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public Long getStaffId() {
		return staffId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column project_staff.staff_id
	 * @param staffId  the value for project_staff.staff_id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column project_staff.responsibility
	 * @return  the value of project_staff.responsibility
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public String getResponsibility() {
		return responsibility;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column project_staff.responsibility
	 * @param responsibility  the value for project_staff.responsibility
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility == null ? null : responsibility.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column project_staff.del_flg
	 * @return  the value of project_staff.del_flg
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public Boolean getDelFlg() {
		return delFlg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column project_staff.del_flg
	 * @param delFlg  the value for project_staff.del_flg
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public void setDelFlg(Boolean delFlg) {
		this.delFlg = delFlg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column project_staff.version
	 * @return  the value of project_staff.version
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column project_staff.version
	 * @param version  the value for project_staff.version
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column project_staff.create_id
	 * @return  the value of project_staff.create_id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column project_staff.create_id
	 * @param createId  the value for project_staff.create_id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public void setCreateId(String createId) {
		this.createId = createId == null ? null : createId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column project_staff.create_time
	 * @return  the value of project_staff.create_time
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column project_staff.create_time
	 * @param createTime  the value for project_staff.create_time
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column project_staff.update_id
	 * @return  the value of project_staff.update_id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column project_staff.update_id
	 * @param updateId  the value for project_staff.update_id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId == null ? null : updateId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column project_staff.update_time
	 * @return  the value of project_staff.update_time
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column project_staff.update_time
	 * @param updateTime  the value for project_staff.update_time
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}