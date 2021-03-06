package com.odm.oa.entity;

import java.util.Date;

public class Notice {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.id
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.title
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.review_flg
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    private Boolean reviewFlg;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.del_flg
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    private Boolean delFlg;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.version
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    private Integer version;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.create_id
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    private String createId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.create_time
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.update_id
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    private String updateId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.update_time
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.type
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    private Long type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.want_review_id
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    private Long reviewIdTwo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.review_id
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    private Long reviewIdOne;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.status
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    private int status;
    private int reviewOneStatus;
    private int reviewTwoStatus;

    public int getReviewOneStatus() {
        return reviewOneStatus;
    }

    public void setReviewOneStatus(int reviewOneStatus) {
        this.reviewOneStatus = reviewOneStatus;
    }

    public int getReviewTwoStatus() {
        return reviewTwoStatus;
    }

    public void setReviewTwoStatus(int reviewTwoStatus) {
        this.reviewTwoStatus = reviewTwoStatus;
    }

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.content
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.id
     *
     * @return the value of notice.id
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.id
     *
     * @param id the value for notice.id
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.title
     *
     * @return the value of notice.title
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.title
     *
     * @param title the value for notice.title
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.review_flg
     *
     * @return the value of notice.review_flg
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public Boolean getReviewFlg() {
        return reviewFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.review_flg
     *
     * @param reviewFlg the value for notice.review_flg
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public void setReviewFlg(Boolean reviewFlg) {
        this.reviewFlg = reviewFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.del_flg
     *
     * @return the value of notice.del_flg
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public Boolean getDelFlg() {
        return delFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.del_flg
     *
     * @param delFlg the value for notice.del_flg
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public void setDelFlg(Boolean delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.version
     *
     * @return the value of notice.version
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.version
     *
     * @param version the value for notice.version
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.create_id
     *
     * @return the value of notice.create_id
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.create_id
     *
     * @param createId the value for notice.create_id
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.create_time
     *
     * @return the value of notice.create_time
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.create_time
     *
     * @param createTime the value for notice.create_time
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.update_id
     *
     * @return the value of notice.update_id
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.update_id
     *
     * @param updateId the value for notice.update_id
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.update_time
     *
     * @return the value of notice.update_time
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.update_time
     *
     * @param updateTime the value for notice.update_time
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.type
     *
     * @return the value of notice.type
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public Long getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.type
     *
     * @param type the value for notice.type
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public void setType(Long type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.want_review_id
     *
     * @return the value of notice.want_review_id
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public Long getReviewIdTwo() {
        return reviewIdTwo;
    }

    public void setReviewIdTwo(Long reviewIdTwo) {
        this.reviewIdTwo = reviewIdTwo;
    }

    public Long getReviewIdOne() {
        return reviewIdOne;
    }

    public void setReviewIdOne(Long reviewIdOne) {
        this.reviewIdOne = reviewIdOne;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.status
     *
     * @return the value of notice.status
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public int getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.status
     *
     * @param status the value for notice.status
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.content
     *
     * @return the value of notice.content
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.content
     *
     * @param content the value for notice.content
     *
     * @mbg.generated Mon Apr 09 19:05:23 CST 2018
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}