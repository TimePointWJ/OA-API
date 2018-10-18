package com.odm.oa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.odm.oa.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.odm.oa.model.request.AreaPagination;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.model.response.DictionaryListResponse;
import com.odm.oa.service.CommonService;
import com.odm.oa.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags={"共通"})
@RequestMapping("/common/")
@RestController(value = "CommonController") 
public class CommonController {
	@Autowired
    private CommonService commonService;
	/**
     * 查询数据字典列表
     * 
     * @param httpSession
     * @param loginForm
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "dictionaryList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "数据字典列表", httpMethod = "POST", response = BaseResponse.class, notes = "查询所有数据字典的列表")
    public BaseResponse<List<DictionaryListResponse>> dictionaryInfo(HttpSession httpSession, @RequestParam String codeType) {
        return commonService.getDictionaryList(codeType);
    }
    
    /**
     * 查询部门列表
     * 
     * @param httpSession
     * @param loginForm
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "departmentList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "列表", httpMethod = "POST", response = BaseResponse.class, notes = "查询所有部门列表")
    public BaseResponse<List<Department>> departmentList() {
        // 返回参数初始化
        BaseResponse<List<Department>> response = new BaseResponse<>();
		try{
			// 获取学校数据
			List<Department> responseData = commonService.getDepartmentList();
            response.setResponseData(responseData);
        }catch (Exception e){
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
    }
    /**
     * 查询部门列表
     * 
     * @param httpSession
     * @param loginForm
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "projectList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "列表", httpMethod = "POST", response = BaseResponse.class, notes = "查询所有部门列表")
    public BaseResponse<List<Project>> projectList() {
        // 返回参数初始化
        BaseResponse<List<Project>> response = new BaseResponse<>();
		try{
			// 获取学校数据
			List<Project> responseData = commonService.getProjectList();
            response.setResponseData(responseData);
        }catch (Exception e){
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
    }
    
    /**
     * 查询职位列表
     * 
     * @param httpSession
     * @param loginForm
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "positionList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "列表", httpMethod = "POST", response = BaseResponse.class, notes = "查询所有职位列表")
    public BaseResponse<List<Position>> positionList() {
        // 返回参数初始化
        BaseResponse<List<Position>> response = new BaseResponse<>();
		try{
			// 获取学校数据
			List<Position> responseData = commonService.getPositionList();
            response.setResponseData(responseData);
        }catch (Exception e){
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
    }
    /**
     * 获取省市区列表
     * @return
     */
    @ApiOperation(value="listAreDate", notes="get list of area")
    @RequestMapping(value = "/listAreDate", method = RequestMethod.POST, produces = {"application/json"})
    public BaseResponse<?> listStudent(@ApiParam() @RequestBody Area areaData) {
        // 返回参数初始化
    	BaseResponse<List<Area>> response = new BaseResponse<List<Area>>();
        try{
        	AreaPagination areaPagination = new AreaPagination();
        	areaPagination.setId(areaData.getId());
        	areaPagination.setAreaKbn(areaData.getAreaKbn());
        	areaPagination.setFollowAreaId(areaData.getFollowAreaId());
        	List<Area> studentList = commonService.areaList(areaPagination);
        	response.setResponseData(studentList);
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }catch(Exception e){
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        return response;
    }
    /**
     * 查询员工列表
     *
     * @param httpSession
     * @param loginForm
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "listStaff", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "列表", httpMethod = "POST", response = BaseResponse.class, notes = "查询所有员工列表")
    public BaseResponse<List<Staff>> staffList() {
        // 返回参数初始化
        BaseResponse<List<Staff>> response = new BaseResponse<>();
        try{
            // 获取学校数据
            List<Staff> responseData = commonService.staffList();
            response.setResponseData(responseData);
        }catch (Exception e){
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        return response;
    }
}
