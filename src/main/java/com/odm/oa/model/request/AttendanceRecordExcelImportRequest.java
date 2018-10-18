package com.odm.oa.model.request;

import com.odm.oa.entity.AttendanceRecordExcelImport;

public class AttendanceRecordExcelImportRequest extends AttendanceRecordExcelImport{
	
    private String result;
    
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
    
}
