package com.odm.oa.model.response;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Date;

public class AppStudent {
    private String id;
    private String schoolName;
    private String studentNo;
    private String studentName;
    private String featurePalmRight;
    private String featurePalmLeft;
    private Integer isServiceBooked;
    private Date createTime;
    private Date lastUpdatedTime;
    private byte[] studentPhoto;

    private String operateType;

    public AppStudent() {
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getStudentNo() {
        return this.studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFeaturePalmRight() {
        return this.featurePalmRight;
    }

    public void setFeaturePalmRight(String featurePalmRight) {
        this.featurePalmRight = featurePalmRight;
    }

    public String getFeaturePalmLeft() {
        return this.featurePalmLeft;
    }

    public void setFeaturePalmLeft(String featurePalmLeft) {
        this.featurePalmLeft = featurePalmLeft;
    }

    public Integer getIsServiceBooked() {
        return this.isServiceBooked;
    }

    public void setIsServiceBooked(Integer isServiceBooked) {
        this.isServiceBooked = isServiceBooked;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdatedTime() {
        return this.lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public byte[] getStudentPhoto() {
        return this.studentPhoto;
    }

    public void setStudentPhoto(byte[] studentPhoto) {
        this.studentPhoto = studentPhoto;
    }
}

