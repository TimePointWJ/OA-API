package com.odm.oa.entity;

import java.util.Date;

public class SystemRole {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column system_role.id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	private Long id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column system_role.role_name
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	private String roleName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column system_role.role_type
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	private Long roleType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column system_role.description
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column system_role.del_flg
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	private Boolean delFlg;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column system_role.version
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	private Integer version;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column system_role.create_id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	private String createId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column system_role.create_time
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column system_role.update_id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	private String updateId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column system_role.update_time
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	private Date updateTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column system_role.id
	 * @return  the value of system_role.id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column system_role.id
	 * @param id  the value for system_role.id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column system_role.role_name
	 * @return  the value of system_role.role_name
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column system_role.role_name
	 * @param roleName  the value for system_role.role_name
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column system_role.role_type
	 * @return  the value of system_role.role_type
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public Long getRoleType() {
		return roleType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column system_role.role_type
	 * @param roleType  the value for system_role.role_type
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public void setRoleType(Long roleType) {
		this.roleType = roleType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column system_role.description
	 * @return  the value of system_role.description
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column system_role.description
	 * @param description  the value for system_role.description
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column system_role.del_flg
	 * @return  the value of system_role.del_flg
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public Boolean getDelFlg() {
		return delFlg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column system_role.del_flg
	 * @param delFlg  the value for system_role.del_flg
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public void setDelFlg(Boolean delFlg) {
		this.delFlg = delFlg;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column system_role.version
	 * @return  the value of system_role.version
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column system_role.version
	 * @param version  the value for system_role.version
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column system_role.create_id
	 * @return  the value of system_role.create_id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column system_role.create_id
	 * @param createId  the value for system_role.create_id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public void setCreateId(String createId) {
		this.createId = createId == null ? null : createId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column system_role.create_time
	 * @return  the value of system_role.create_time
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column system_role.create_time
	 * @param createTime  the value for system_role.create_time
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column system_role.update_id
	 * @return  the value of system_role.update_id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column system_role.update_id
	 * @param updateId  the value for system_role.update_id
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId == null ? null : updateId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column system_role.update_time
	 * @return  the value of system_role.update_time
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column system_role.update_time
	 * @param updateTime  the value for system_role.update_time
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}