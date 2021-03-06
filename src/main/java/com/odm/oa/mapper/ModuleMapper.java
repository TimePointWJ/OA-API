package com.odm.oa.mapper;

import java.util.List;

import com.odm.oa.entity.Module;

public interface ModuleMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table module
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table module
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	int insert(Module record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table module
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	Module selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table module
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	List<Module> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table module
	 * @mbggenerated  Mon Feb 12 15:37:41 CST 2018
	 */
	int updateByPrimaryKey(Module record);
}