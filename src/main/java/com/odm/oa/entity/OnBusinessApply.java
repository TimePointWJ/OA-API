package com.odm.oa.entity;

import java.util.Date;

public class OnBusinessApply {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column on_business_apply.ID
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	private Long id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column on_business_apply.PERSON_ID
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	private String personId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column on_business_apply.APPLY_START_DATE
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	private Date applyStartDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column on_business_apply.APPLY_END_DATE
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	private Date applyEndDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column on_business_apply.SITE
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	private String site;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column on_business_apply.EXPLAINS
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	private String explains;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column on_business_apply.del_flg
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	private Boolean delFlg;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column on_business_apply.version
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	private Integer version;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column on_business_apply.create_id
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	private String createId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column on_business_apply.create_time
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column on_business_apply.update_id
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	private String updateId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column on_business_apply.update_time
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	private Date updateTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column on_business_apply.ID
	 * @return  the value of on_business_apply.ID
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column on_business_apply.ID
	 * @param id  the value for on_business_apply.ID
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column on_business_apply.PERSON_ID
	 * @return  the value of on_business_apply.PERSON_ID
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public String getPersonId() {
		return personId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column on_business_apply.PERSON_ID
	 * @param personId  the value for on_business_apply.PERSON_ID
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public void setPersonId(String personId) {
		this.personId = personId == null ? null : personId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column on_business_apply.APPLY_START_DATE
	 * @return  the value of on_business_apply.APPLY_START_DATE
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public Date getApplyStartDate() {
		return applyStartDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column on_business_apply.APPLY_START_DATE
	 * @param applyStartDate  the value for on_business_apply.APPLY_START_DATE
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public void setApplyStartDate(Date applyStartDate) {
		this.applyStartDate = applyStartDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column on_business_apply.APPLY_END_DATE
	 * @return  the value of on_business_apply.APPLY_END_DATE
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public Date getApplyEndDate() {
		return applyEndDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column on_business_apply.APPLY_END_DATE
	 * @param applyEndDate  the value for on_business_apply.APPLY_END_DATE
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public void setApplyEndDate(Date applyEndDate) {
		this.applyEndDate = applyEndDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column on_business_apply.SITE
	 * @return  the value of on_business_apply.SITE
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public String getSite() {
		return site;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column on_business_apply.SITE
	 * @param site  the value for on_business_apply.SITE
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public void setSite(String site) {
		this.site = site == null ? null : site.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column on_business_apply.EXPLAINS
	 * @return  the value of on_business_apply.EXPLAINS
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public String getExplains() {
		return explains;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column on_business_apply.EXPLAINS
	 * @param explains  the value for on_business_apply.EXPLAINS
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public void setExplains(String explains) {
		this.explains = explains == null ? null : explains.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column on_business_apply.del_flg
	 * @return  the value of on_business_apply.del_flg
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public Boolean getDelFlg() {
		return delFlg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column on_business_apply.del_flg
	 * @param delFlg  the value for on_business_apply.del_flg
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public void setDelFlg(Boolean delFlg) {
		this.delFlg = delFlg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column on_business_apply.version
	 * @return  the value of on_business_apply.version
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column on_business_apply.version
	 * @param version  the value for on_business_apply.version
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column on_business_apply.create_id
	 * @return  the value of on_business_apply.create_id
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column on_business_apply.create_id
	 * @param createId  the value for on_business_apply.create_id
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public void setCreateId(String createId) {
		this.createId = createId == null ? null : createId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column on_business_apply.create_time
	 * @return  the value of on_business_apply.create_time
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column on_business_apply.create_time
	 * @param createTime  the value for on_business_apply.create_time
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column on_business_apply.update_id
	 * @return  the value of on_business_apply.update_id
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column on_business_apply.update_id
	 * @param updateId  the value for on_business_apply.update_id
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId == null ? null : updateId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column on_business_apply.update_time
	 * @return  the value of on_business_apply.update_time
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column on_business_apply.update_time
	 * @param updateTime  the value for on_business_apply.update_time
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}