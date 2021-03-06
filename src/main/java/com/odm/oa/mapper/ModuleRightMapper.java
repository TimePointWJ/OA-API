package com.odm.oa.mapper;

import java.util.List;

import com.odm.oa.entity.ModuleRight;

public interface ModuleRightMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table module_right
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table module_right
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	int insert(ModuleRight record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table module_right
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	ModuleRight selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table module_right
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	List<ModuleRight> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table module_right
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	int updateByPrimaryKey(ModuleRight record);
	
	List<ModuleRight> selectRoleByRoleId(Long roleId);

	/**
	 * 通过roleId删除下面所有的模块权限
	 * @param userId
	 * @return
	 */
	int deleteByRoleId(long userId);
	/**
	 * 往权限表里面插入数据
	 */
	int insertModuleRight(ModuleRight moduleRight);
}