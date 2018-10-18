package com.odm.oa.model.request;

public class MessagePagination extends PaginationBase{
	private Long userId;//登录的用户id
	private Long staffId;//聊天的员工id
	private String staffNo;//登录的用户的staffNo
	private String clickMessageFlg;//点击消息按钮后
	
	public String getClickMessageFlg() {
		return clickMessageFlg;
	}
	public void setClickMessageFlg(String clickMessageFlg) {
		this.clickMessageFlg = clickMessageFlg;
	}
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

}
