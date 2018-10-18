package com.odm.oa.model.request;

public class LeaveListRequest  extends PaginationBase{

	private String totalCount;

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	
}
