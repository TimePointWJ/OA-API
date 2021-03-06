package com.odm.oa.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.odm.oa.entity.AttendanceRecordExcelImport;

public interface AttendanceRecordExcelImportMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table attendance_record_excel_import
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	int insert(AttendanceRecordExcelImport record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table attendance_record_excel_import
	 * @mbg.generated  Wed Feb 28 15:02:56 CST 2018
	 */
	List<AttendanceRecordExcelImport> selectAll();

	AttendanceRecordExcelImport selectAttendanceRecordExcel(@Param("staffno") String staffno, @Param("myDate")Date myDate,@Param("one")String one);
}