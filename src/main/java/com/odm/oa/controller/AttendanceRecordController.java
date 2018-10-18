package com.odm.oa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.odm.oa.entity.AttendanceRecord;
import com.odm.oa.entity.AttendanceRecordExcelImport;
import com.odm.oa.model.request.AttendanceRecordExcelImportRequest;
import com.odm.oa.model.request.AttendanceRecordRequest;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.service.AttendanceRecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags={"考勤记录"})
@RestController
@RequestMapping("/service/attendanceRecord")
public class AttendanceRecordController extends BasicController{
	
	@Autowired
	private AttendanceRecordService attendanceRecordService;
	
	/**
	 * 分页查询
	 * @param httpSession
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping( value = "/pageList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "获取考勤记录", httpMethod = "POST", response = AttendanceRecord.class, notes = "获取考勤记录")
    public BaseResponse<PageInfo<AttendanceRecord>> list(HttpSession httpSession, @ApiParam(name = "AttendanceRecordRequest",
    	value = "AttendanceRecord list info", required = true) @RequestBody AttendanceRecordRequest request) {
        // 返回参数初始化
        logger.info("AttendanceRecord > getList" + JSONObject.toJSONString(request));
        BaseResponse<PageInfo<AttendanceRecord>> response = new BaseResponse<>();
        logger.info("--------------------------------获取考勤记录开始--------------------------------");
        PageInfo<AttendanceRecord> responseData = attendanceRecordService.getPage(request);
        response.setResponseData(responseData);
        //response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        logger.info("--------------------------------获取考勤记录结束--------------------------------");
        return response;
    }
	/**
     * 批量信息导入
     *
     * @param request
     * @return
     */
	@ResponseBody
    @RequestMapping( value = "/excelImport", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "Excel导入", httpMethod = "POST", response = BaseResponse.class, notes = "Excel导入")
    public BaseResponse<List<AttendanceRecordExcelImportRequest>> excelImport(@ApiParam("") @RequestBody AttendanceRecordExcelImport[] request){
        logger.info("url:/service/attendanceRecord/excelImport" + JSONObject.toJSONString(request));
        logger.info("--------------------------------批量导入年级信息开始--------------------------------");
        BaseResponse<List<AttendanceRecordExcelImportRequest>> response = new BaseResponse<>();
        List<AttendanceRecordExcelImportRequest> responseData=attendanceRecordService.excelImport(request);
        response.setResponseData(responseData);
        logger.info(responseData);
        logger.info("--------------------------------批量导入年级信息结束--------------------------------");
        return response;
    }
}
