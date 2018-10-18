package com.odm.oa.entity.ex;

import com.odm.oa.entity.Staff;

public class StaffEx extends Staff{
	private String departmentName;
	private String positionName;
	private String role;// 该用户拥有的角色
	private Long roleCode;
	private String provinceName;
	private String cityName;
	private String districtName;
	private Long projectId;
	private String privateFlg;//是否添加到私人通讯录，0：不是私有，其它，是私有, 存放的是addressPrivate的id
	private Long countMessage;//统计信息数量
	
	public String getPrivateFlg() {
		return privateFlg;
	}
	public void setPrivateFlg(String privateFlg) {
		this.privateFlg = privateFlg;
	}
	public Long getCountMessage() {
		return countMessage;
	}
	public void setCountMessage(Long countMessage) {
		this.countMessage = countMessage;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Long getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(Long roleCode) {
		this.roleCode = roleCode;
	}
	
}
