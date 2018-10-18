package com.odm.oa.model.response;

public class ClassImportResult {
	private String className;
	private String gradeName;
    private String schoolName;
    private String classHeadmaster;
    private Integer version;
    private Integer studentNumber;
    private Integer maleNumber;
    private Integer femaleNumber;
    private Integer courseNumber;
    private Integer teacherNumber;
    private String status;
    private String result;
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	public String getClassHeadmaster() {
		return classHeadmaster;
	}
	public void setClassHeadmaster(String classHeadmaster) {
		this.classHeadmaster = classHeadmaster;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Integer getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(Integer studentNumber) {
		this.studentNumber = studentNumber;
	}
	public Integer getMaleNumber() {
		return maleNumber;
	}
	public void setMaleNumber(Integer maleNumber) {
		this.maleNumber = maleNumber;
	}
	public Integer getFemaleNumber() {
		return femaleNumber;
	}
	public void setFemaleNumber(Integer femaleNumber) {
		this.femaleNumber = femaleNumber;
	}
	public Integer getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(Integer courseNumber) {
		this.courseNumber = courseNumber;
	}
	public Integer getTeacherNumber() {
		return teacherNumber;
	}
	public void setTeacherNumber(Integer teacherNumber) {
		this.teacherNumber = teacherNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
