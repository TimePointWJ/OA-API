package com.odm.oa.model.response;

import com.odm.oa.entity.LeaveType;

public class LeaveTypeResponse extends LeaveType{
	private String totalCount;

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	
}
