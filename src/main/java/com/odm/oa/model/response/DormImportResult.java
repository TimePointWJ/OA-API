package com.odm.oa.model.response;

public class DormImportResult {
    private String dormName;
    private String dromBuildingName;
    private String name;
    private String schoolName;
    private String address;
    private String dormKbn;
    private Integer holdNumber;
    private Integer checkInNumber;
    private Integer version;
    private String result;
	public String getDormName() {
		return dormName;
	}
	public void setDormName(String dormName) {
		this.dormName = dormName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDormKbn() {
		return dormKbn;
	}
	public void setDormKbn(String dormKbn) {
		this.dormKbn = dormKbn;
	}
	public Integer getHoldNumber() {
		return holdNumber;
	}
	public void setHoldNumber(Integer holdNumber) {
		this.holdNumber = holdNumber;
	}
	public Integer getCheckInNumber() {
		return checkInNumber;
	}
	public void setCheckInNumber(Integer checkInNumber) {
		this.checkInNumber = checkInNumber;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getDromBuildingName() {
		return dromBuildingName;
	}
	public void setDromBuildingName(String dromBuildingName) {
		this.dromBuildingName = dromBuildingName;
	}
    
    
}
