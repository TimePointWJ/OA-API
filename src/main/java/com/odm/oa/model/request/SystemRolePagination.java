package com.odm.oa.model.request;

public class SystemRolePagination extends PaginationBase {

    private String roleNameSearch;//查询条件角色名称
    
    private String roleTypeSearch;//查询条件角色类型

	public String getRoleNameSearch() {
		return roleNameSearch;
	}

	public void setRoleNameSearch(String roleNameSearch) {
		this.roleNameSearch = roleNameSearch;
	}

	public String getRoleTypeSearch() {
		return roleTypeSearch;
	}

	public void setRoleTypeSearch(String roleTypeSearch) {
		this.roleTypeSearch = roleTypeSearch;
	}

}
