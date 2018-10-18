package com.odm.oa.model.response;

import java.util.List;

public class IdentifyRecordResponse {
	private List<Integer> workCount;//实际出勤人数，一年的十二个月，或者月30天
	private List<Integer> noWorkCount;//应该未出勤人数，一年的十二个月，或者月30天
	private int count;
	private String identifyTime;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getIdentifyTime() {
		return identifyTime;
	}
	public void setIdentifyTime(String identifyTime) {
		this.identifyTime = identifyTime;
	}
	public List<Integer> getWorkCount() {
		return workCount;
	}
	public void setWorkCount(List<Integer> workCount) {
		this.workCount = workCount;
	}
	public List<Integer> getNoWorkCount() {
		return noWorkCount;
	}
	public void setNoWorkCount(List<Integer> noWorkCount) {
		this.noWorkCount = noWorkCount;
	}
	
	
}
