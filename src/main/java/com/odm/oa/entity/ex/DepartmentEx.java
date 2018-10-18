package com.odm.oa.entity.ex;

import java.util.List;

import com.odm.oa.entity.Department;

public class DepartmentEx extends Department{
	private List<StaffEx> staffs;
	private String managerName;
	private String assistantManagerName;
	
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

	public List<StaffEx> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<StaffEx> staffs) {
		this.staffs = staffs;
	}
	
}
