package com.odm.oa.entity.ex;

import com.odm.oa.entity.Notice;

public class NoticeEx extends Notice{
    private String author;
    private String typeName;
    private String staffNo;//发布人工号
    private String reviewOneName;
    private String reviewTwoName;
    private Boolean reviewPeopleFlg;//当前审核人是否已审核

    public Boolean getReviewPeopleFlg() {
        return reviewPeopleFlg;
    }

    public void setReviewPeopleFlg(Boolean reviewPeopleFlg) {
        this.reviewPeopleFlg = reviewPeopleFlg;
    }

    public String getReviewOneName() {
        return reviewOneName;
    }

    public void setReviewOneName(String reviewOneName) {
        this.reviewOneName = reviewOneName;
    }

    public String getReviewTwoName() {
        return reviewTwoName;
    }

    public void setReviewTwoName(String reviewTwoName) {
        this.reviewTwoName = reviewTwoName;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
