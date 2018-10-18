package com.odm.oa.entity.ex;

import java.util.List;

import com.odm.oa.entity.Project;

public class ProjectEx extends Project{
	private List<ProjectStaffEx> staffs;
	private String managerName;
	private String assistantManagerName;
	private List<ProjectStaffEx> addStaffs;
	
	public List<ProjectStaffEx> getAddStaffs() {
		return addStaffs;
	}

	public void setAddStaffs(List<ProjectStaffEx> addStaffs) {
		this.addStaffs = addStaffs;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getAssistantManagerName() {
		return assistantManagerName;
	}

	public void setAssistantManagerName(String assistantManagerName) {
		this.assistantManagerName = assistantManagerName;
	}

	public List<ProjectStaffEx> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<ProjectStaffEx> staffs) {
		this.staffs = staffs;
	}

}
