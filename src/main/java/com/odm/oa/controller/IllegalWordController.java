package com.odm.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.odm.oa.entity.Staff;
import com.odm.oa.entity.ex.IllegalWordEx;
import com.odm.oa.entity.ex.NoticeEx;
import com.odm.oa.model.request.*;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.service.IllegalWordService;
import com.odm.oa.service.NoticeService;
import com.odm.oa.service.StaffService;
import com.odm.oa.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Api(tags = { "非法关键词管理" })
@RestController
@RequestMapping("/service/illegalWord")
public class IllegalWordController {
    private static Logger logger = LogManager.getLogger(IllegalWordController.class);
    @Autowired
    private IllegalWordService illegalWordService;

    /**
     * 添加关键字
     * @return
     */
    @ApiOperation(value = "添加关键字", notes = "添加关键字")
    @RequestMapping(value = "/addWord", method = RequestMethod.POST, produces = { "application/json" })
    public BaseResponse<String> addWord(@ApiParam("添加关键字") @RequestBody SubmitIllegalWordRequest request) {
        logger.info(JSONObject.toJSONString(request));
        BaseResponse<String> response = new BaseResponse<>();
        boolean valid=true;
        if(valid){
            response.setResponseData(illegalWordService.addIllegalWord(request));
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        return response;
    }
    /**
     * 查询关键字列表
     * @return
     */
    @ApiOperation(value = "查询关键字列表", notes = "查询关键字列表")
    @RequestMapping(value = "/pagination", method = RequestMethod.POST, produces = { "application/json" })
    public BaseResponse<PageInfo<IllegalWordEx>> pagination(@ApiParam("分页信息") @RequestBody IllegalWordPagination request) {
        logger.info(JSONObject.toJSONString(request));
        BaseResponse<PageInfo<IllegalWordEx>> response = new BaseResponse<>();
        boolean valid=true;
        if(valid){
            PageInfo<IllegalWordEx> responseData=illegalWordService.pagination(request);
            response.setResponseData(responseData);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        return response;
    }
    /**
     * 删除关键字
     * @return
     */
    @ApiOperation(value = "删除关键字", notes = "删除关键字")
    @RequestMapping(value = "/deleteWord", method = RequestMethod.POST, produces = { "application/json" })
    public BaseResponse<String> deleteNotice(@ApiParam("删除id") @RequestBody Long[] request) {
        logger.info(JSONObject.toJSONString(request));
        BaseResponse<String> response = new BaseResponse<>();
        boolean valid=true;
        if(valid){
            illegalWordService.deleteIllegalWord(request);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        return response;
    }
    /**
     * 编辑关键字
     * @return
     */
    @ApiOperation(value = "编辑关键字", notes = "编辑关键字")
    @RequestMapping(value = "/editWord", method = RequestMethod.POST, produces = { "application/json" })
    public BaseResponse<String> editWord(@ApiParam("编辑关键字") @RequestBody SubmitIllegalWordRequest request) {
        logger.info(JSONObject.toJSONString(request));
        BaseResponse<String> response = new BaseResponse<>();
        boolean valid=true;
        if(valid){
            response.setResponseData(illegalWordService.editIllegalWord(request));
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        return response;
    }
    /**
     * 关键字创建人列表
     * @return
     */
    @ApiOperation(value="关键字创建人列表", notes="关键字创建人列表")
    @RequestMapping(value = "/listPerson", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public BaseResponse<List<Staff>> listPerson() {
        BaseResponse<List<Staff>> response = new BaseResponse<>();
        try{
            List<Staff> responseData = illegalWordService.listPerson();
            response.setResponseData(responseData);
        }catch (Exception e){
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        return response;
    }
    /**
     * 非法关键字列表
     * @return
     */
    @ApiOperation(value="非法关键字列表", notes="非法关键字列表")
    @RequestMapping(value = "/listIllegalWord", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public BaseResponse<Set<String>> listIllegalWord() {
        BaseResponse<Set<String>> response = new BaseResponse<>();
        try{
            Set<String> responseData = illegalWordService.listIllegalWord();
            response.setResponseData(responseData);
        }catch (Exception e){
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        return response;
    }
    /**
     * 判断、返回非法关键字
     * @return
     */
    @ApiOperation(value="判断、返回非法关键字", notes="判断、返回非法关键字")
    @RequestMapping(value = "/getIllegalWord", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public BaseResponse<List<String>> getIllegalWord(@ApiParam("内容") @RequestBody String request) {
        BaseResponse<List<String>> response = new BaseResponse<>();
        logger.info(JSONObject.toJSONString(request));
        try{
            List<String> responseData = illegalWordService.getIllegalWord(java.net.URLDecoder.decode(request,"utf-8"));
            response.setResponseData(responseData);
            logger.info(response.getResponseData().toString());
        }catch (Exception e){
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }

        return response;
    }
}
