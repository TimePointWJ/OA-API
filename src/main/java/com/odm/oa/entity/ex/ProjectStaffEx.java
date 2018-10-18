package com.odm.oa.entity.ex;

import com.odm.oa.entity.ProjectStaff;

public class ProjectStaffEx extends ProjectStaff{
	private String departmentName;
	private String positionName;
	private String role;// 该用户拥有的角色
	private Long roleCode;
	private String provinceName;
	private String cityName;
	private String districtName;
	
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
