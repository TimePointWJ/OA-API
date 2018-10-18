package com.odm.oa.model.request;


public class AreaPagination extends PaginationBase {
	
	private Long id;
	
	private String areaKbn;
	
	private Long followAreaId; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAreaKbn() {
		return areaKbn;
	}

	public void setAreaKbn(String areaKbn) {
		this.areaKbn = areaKbn;
	}

	public Long getFollowAreaId() {
		return followAreaId;
	}

	public void setFollowAreaId(Long followAreaId) {
		this.followAreaId = followAreaId;
	}

}
