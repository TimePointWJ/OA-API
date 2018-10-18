package com.odm.oa.entity.ex;

public class ProvenceCityArea {
	/**
	 * 地区区分
	 */
	private String areaKbn;
	
	/**
	 * 从属地区
	 */
	private String followAreaId;

	public String getAreaKbn() {
		return areaKbn;
	}

	public void setAreaKbn(String areaKbn) {
		this.areaKbn = areaKbn;
	}

	public String getFollowAreaId() {
		return followAreaId;
	}

	public void setFollowAreaId(String followAreaId) {
		this.followAreaId = followAreaId;
	}
}
