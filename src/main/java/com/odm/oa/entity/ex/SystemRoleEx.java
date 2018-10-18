package com.odm.oa.entity.ex;

import com.odm.oa.entity.SystemRole;

/**
 * create by HJR
 * 2017/12/13  下午20:08
 */
public class SystemRoleEx extends SystemRole{

	private String userId;
	private String roleTypeName;

	public String getRoleTypeName() {
		return roleTypeName;
	}

	public void setRoleTypeName(String roleTypeName) {
		this.roleTypeName = roleTypeName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
