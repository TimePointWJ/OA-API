package com.odm.oa.model.request.app;

import java.io.Serializable;

/**
 * create LeaverRequest by huc
 * 2017/12/18  下午9:31
 */
public class LeaverRequest implements Serializable{

	private static final long serialVersionUID = 2258811281673185337L;
	
	private String id;
    private String studentId;
    private String studentName;
    private String startTime;
    private String endTime;
    private String remark;
    private String statusId;
    private String statusCn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusCn() {
        return statusCn;
    }

    public void setStatusCn(String statusCn) {
        this.statusCn = statusCn;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    @Override
    public String toString() {
        return "LeaverRequest{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", remark='" + remark + '\'' +
                ", status='" + statusCn + '\'' +
                '}';
    }
}
