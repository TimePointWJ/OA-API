package com.odm.oa.model.request;

public class StaffPagination extends PaginationBase{
	private String checkStatus;
	private Long departmentId;
	private Long positionId;
	private String userId;
	private String userIdSearch;
	private String accountKbnSearch;
	private String name;
	private String staffNo;
	
	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getUserIdSearch() {
		return userIdSearch;
	}

	public void setUserIdSearch(String userIdSearch) {
		this.userIdSearch = userIdSearch;
	}

	public String getAccountKbnSearch() {
		return accountKbnSearch;
	}

	public void setAccountKbnSearch(String accountKbnSearch) {
		this.accountKbnSearch = accountKbnSearch;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
