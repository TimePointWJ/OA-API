package com.odm.oa.controller;

import javax.servlet.http.HttpServletRequest;

import com.odm.oa.entity.Notice;
import com.odm.oa.entity.NoticeResponse;
import com.odm.oa.entity.Staff;
import com.odm.oa.entity.ex.NoticeEx;
import com.odm.oa.entity.ex.ResponseEx;
import com.odm.oa.model.request.*;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.model.response.ChartCount;
import com.odm.oa.model.response.LookOneNoticeResponse;
import com.odm.oa.model.response.PersonNotice;
import com.odm.oa.service.NoticeService;
import com.odm.oa.service.StaffService;
import com.odm.oa.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.odm.oa.auth.JwtTokenUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = { "公告管理" })
@RestController
@RequestMapping("/service/notice")
public class NoticeController {
    private static Logger logger = LogManager.getLogger(NoticeController.class);
    @Autowired
    private NoticeService noticeService;

    /**
     * 提交公告
     * @return
     */
    @ApiOperation(value = "提交公告", notes = "提交公告")
    @RequestMapping(value = "/submitNotice", method = RequestMethod.POST, produces = { "application/json" })
    public BaseResponse<Long> addNotice(@ApiParam("公告信息") @RequestBody SubmitNoticeRequest request) {
        logger.info(JSONObject.toJSONString(request));
        BaseResponse<Long> response = new BaseResponse<>();
        boolean valid=true;
        if(valid){
            response.setResponseData(noticeService.addNotice(request));
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        return response;
    }
    /**
     * 查询公告列表
     * @return
     */
    @ApiOperation(value = "查询公告列表", notes = "查询公告列表")
    @RequestMapping(value = "/pagination", method = RequestMethod.POST, produces = { "application/json" })
    public BaseResponse<PageInfo<NoticeEx>> pagination(@ApiParam("分页信息") @RequestBody NoticePagination request) {
        logger.info(JSONObject.toJSONString(request));
        BaseResponse<PageInfo<NoticeEx>> response = new BaseResponse<>();
        boolean valid=true;
        if(valid){
            PageInfo<NoticeEx> responseData=noticeService.pagination(request);
            response.setResponseData(responseData);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        return response;
    }
    /**
     * 删除公告
     * @return
     */
    @ApiOperation(value = "删除公告", notes = "删除公告")
    @RequestMapping(value = "/deleteNotice", method = RequestMethod.POST, produces = { "application/json" })
    public BaseResponse<String> deleteNotice(@ApiParam("删除id") @RequestBody Long[] request) {
        logger.info(JSONObject.toJSONString(request));
        BaseResponse<String> response = new BaseResponse<>();
        boolean valid=true;
        if(valid){
            noticeService.deleteNotice(request);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        return response;
    }
    /**
     * 查看公告
     * @return
     */
    @ApiOperation(value = "查看公告", notes = "查看公告")
    @RequestMapping(value = "/lookOneNotice", method = RequestMethod.POST, produces = { "application/json" })
    public BaseResponse<LookOneNoticeResponse> lookOneNotice(@ApiParam("公告id") @RequestBody NoticeDetialRequest request) {
        logger.info(JSONObject.toJSONString(request));
        BaseResponse<LookOneNoticeResponse> response = new BaseResponse<>();
        boolean valid=true;
        if(valid){
            response.setResponseData(noticeService.lookOneNotice(request));
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        logger.info(JSONObject.toJSONString(response));
        return response;
    }
    /**
     * 个人公告
     * @return
     */
    @ApiOperation(value = "个人公告", notes = "个人公告")
    @RequestMapping(value = "/personalNotice", method = RequestMethod.POST, produces = { "application/json" })
    public BaseResponse<List<PersonNotice>> personalNotice(@ApiParam("员工工号") @RequestBody String[] request) {
        logger.info(JSONObject.toJSONString(request));
        BaseResponse<List<PersonNotice>> response = new BaseResponse<>();
        boolean valid=true;
        if(valid){
            response.setResponseData(noticeService.getPersonalNotice(request[0]));
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        logger.info(JSONObject.toJSONString(response));
        return response;
    }
    /**
     * 上传文件
     * @return
     */
    @ApiOperation(value="上传文件", notes="上传文件")
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public BaseResponse<List<String>> upload(@RequestParam("file") MultipartFile[] file) {
        // 返回参数初始化
        BaseResponse<List<String>> response = new BaseResponse<List<String>>();
        noticeService.writeToService(file,response);
        return response;
    }
    /**
     * 审核公告
     * @return
     */
    @ApiOperation(value="审核公告", notes="审核公告")
    @RequestMapping(value = "/reviewNotice", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public BaseResponse<String> reviewNotice(@ApiParam("公告ID") @RequestBody Long[] request) {
        logger.info(JSONObject.toJSONString(request));
        BaseResponse<String> response = new BaseResponse<String>();
        boolean valid =true;
        if(valid){
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
            noticeService.reviewNotice(request);
        }
        else{
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        return response;
    }
    /**
     * 发布人列表
     * @return
     */
    @ApiOperation(value="发布人列表", notes="发布人列表")
    @RequestMapping(value = "/listAuthor", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public BaseResponse<List<Staff>> staffList() {
        BaseResponse<List<Staff>> response = new BaseResponse<>();
        try{
            List<Staff> responseData = noticeService.listAuthor();
            response.setResponseData(responseData);
        }catch (Exception e){
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        return response;
    }
    /**
     * 提交回复
     * @return
     */
    @ApiOperation(value="提交回复", notes="提交回复")
    @RequestMapping(value = "/submitResponse", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public BaseResponse<String> submitResponse(@ApiParam("回复信息") @RequestBody SubmitResponseRequest request) {
        logger.info(JSONObject.toJSONString(request));
        BaseResponse<String> response = new BaseResponse<String>();
        boolean valid =true;
        if(valid){
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
            noticeService.addResponse(request);
        }
        else{
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        return response;
    }
    /**
     * 删除回复
     * @return
     */
    @ApiOperation(value = "删除回复", notes = "删除回复")
    @RequestMapping(value = "/deleteResponse", method = RequestMethod.POST, produces = { "application/json" })
    public BaseResponse<String> deleteResponse(@ApiParam("删除id") @RequestBody Long[] request) {
        logger.info(JSONObject.toJSONString(request));
        BaseResponse<String> response = new BaseResponse<>();
        boolean valid=true;
        if(valid){
            noticeService.deleteResponse(request);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        return response;
    }
    /**
     * 查询公告回复列表
     * @return
     */
    @ApiOperation(value = "查询公告回复列表", notes = "查询公告回复列表")
    @RequestMapping(value = "/pageResponse", method = RequestMethod.POST, produces = { "application/json" })
    public BaseResponse<PageInfo<ResponseEx>> pagination(@ApiParam("分页信息") @RequestBody NoticeResponsePagination request) {
        logger.info(JSONObject.toJSONString(request));
        BaseResponse<PageInfo<ResponseEx>> response = new BaseResponse<>();
        boolean valid=true;
        if(valid){
            PageInfo<ResponseEx> responseData=noticeService.pageResponse(request);
            response.setResponseData(responseData);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        return response;
    }
    /**
     * 审核人列表
     * @return
     */
    @ApiOperation(value="审核人列表", notes="审核人列表")
    @RequestMapping(value = "/listReviewPerson", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public BaseResponse<List<Staff>> reviewPersonList() {
        BaseResponse<List<Staff>> response = new BaseResponse<>();
        try{
            List<Staff> responseData = noticeService.listReviewPerson();
            response.setResponseData(responseData);
        }catch (Exception e){
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        return response;
    }
    /**
     * 获取编辑公告信息
     * @return
     */
    @ApiOperation(value="获取编辑公告信息", notes="获取编辑公告信息")
    @RequestMapping(value = "/getEditData", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public BaseResponse<SubmitNoticeRequest> getEditData(@ApiParam("公告ID") @RequestBody Long[] request) {
        logger.info(JSONObject.toJSONString(request));
        BaseResponse<SubmitNoticeRequest> response = new BaseResponse<SubmitNoticeRequest>();
        boolean valid =true;
        if(valid){
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
            response.setResponseData(noticeService.getEditData(request[0]));
        }
        else{
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        return response;
    }
    /**
     * 获取图形数据
     * @return
     */
    @ApiOperation(value="获取图形数据", notes="获取图形数据")
    @RequestMapping(value = "/getNoticeCount", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public BaseResponse<ChartCount> getNoticeCount() {
        BaseResponse<ChartCount> response = new BaseResponse<>();
        try{
            ChartCount responseData = noticeService.getNoticeCount();
            response.setResponseData(responseData);
        }catch (Exception e){
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        return response;
    }
}
