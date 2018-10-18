package com.odm.oa.model.request.app;

import java.io.Serializable;

/**
 * create StuRegisterRequest by huc
 * 2017/12/18  下午7:47
 */
public class StuRegisterRequest implements Serializable{

	private static final long serialVersionUID = 6118281986594744292L;
	
	private String parentsName;
    private String openId; // 家长微信ID
    private String parentsPhone;
    private String studentName;
    private String studentNumber;
    private String provence;
    private String city;
    private String area;
    private String schoolName;
    private String schoolId;
    private String grade;
    private String gradeId;
    private String clazz;
    private String clazzCn;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getParentsName() {
        return parentsName;
    }

    public void setParentsName(String parentsName) {
        this.parentsName = parentsName;
    }

    public String getParentsPhone() {
        return parentsPhone;
    }

    public void setParentsPhone(String parentsPhone) {
        this.parentsPhone = parentsPhone;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getProvence() {
        return provence;
    }

    public void setProvence(String provence) {
        this.provence = provence;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getClazzCn() {
        return clazzCn;
    }

    public void setClazzCn(String clazzCn) {
        this.clazzCn = clazzCn;
    }

    @Override
    public String toString() {
        return "StuRegisterRequest{" +
                "parentsName='" + parentsName + '\'' +
                ", openId='" + openId + '\'' +
                ", parentsPhone='" + parentsPhone + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", provence='" + provence + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", grade='" + grade + '\'' +
                ", gradeId='" + gradeId + '\'' +
                ", clazz='" + clazz + '\'' +
                ", clazzCn='" + clazzCn + '\'' +
                '}';
    }

    public boolean validate(){
        boolean flag = true;
        if (this.studentName.isEmpty()) flag = false;
        if (this.studentNumber.isEmpty()) flag = false;
        if (this.parentsName.isEmpty()) flag = false;
        if (this.parentsPhone.isEmpty()) flag = false;
        if (this.openId.isEmpty()) flag = false;
        return flag;
    }
}
