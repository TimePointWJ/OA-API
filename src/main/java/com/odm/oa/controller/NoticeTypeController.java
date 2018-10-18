package com.odm.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.odm.oa.entity.LeaveType;
import com.odm.oa.entity.NoticeType;
import com.odm.oa.model.request.PaginationBase;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.service.NoticeTypeService;
import com.odm.oa.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Api(tags={"公告类型信息"})
@RestController
@RequestMapping("/service/noticeType")
public class NoticeTypeController extends BasicController{
    @Autowired
    private NoticeTypeService noticeTypeService;
    /**
     * 分页查询
     * @param httpSession
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "/pageList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "分页查询", httpMethod = "POST", response = LeaveType.class, notes = "获取公告类型信息")
    public BaseResponse<PageInfo<NoticeType>> list(HttpSession httpSession, @ApiParam(name = "PaginationBase",
            value = "noticeType list info", required = true) @RequestBody PaginationBase request) {
        // 返回参数初始化
        logger.info("NoticeTypeController > getList" + JSONObject.toJSONString(request));
        BaseResponse<PageInfo<NoticeType>> response = new BaseResponse<>();
        logger.info("--------------------------------获取公告类型信息开始--------------------------------");
        PageInfo<NoticeType> responseData = noticeTypeService.getPage(request);
        response.setResponseData(responseData);
        //response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        logger.info("--------------------------------获取公告类型信息结束--------------------------------");
        return response;
    }

    /**
     * 添加
     * @param httpSession
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "/add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "添加请假类型信息", httpMethod = "POST", response = Integer.class, notes = "添加公告类型信息")
    public BaseResponse<Integer> add(HttpSession httpSession, @ApiParam(name = "LeaveType", value = "add info",
            required = true) @RequestBody NoticeType request) {
        // 返回参数初始化
        logger.info("NoticeTypeController > add" + JSONObject.toJSONString(request));
        BaseResponse<Integer> response = new BaseResponse<>();
        logger.info("--------------------------------获取公告类型信息开始--------------------------------");
        Integer code=1;
        if(noticeTypeService.insertOrUpdate(request)){
            logger.info("公告类型信息添加或更新成功");
            code=0;
            response.setResponseData(code);
        }else{
            logger.info("公告类型信息添加或更新失败");
            response.setResponseData(code);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        logger.info("--------------------------------获取公告类型信息结束--------------------------------");
        return response;
    }

    /**
     * 删除
     * @param httpSession
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "/delete", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "删除公告类型信息", httpMethod = "POST", response = Integer.class, notes = "删除公告类型信息")
    public BaseResponse<Integer> delete(HttpSession httpSession, @ApiParam(name = "noticeType", value = "delete info",
            required = true) @RequestBody NoticeType request) {
        // 返回参数初始化
        logger.info("NoticeTypeController > add" + JSONObject.toJSONString(request));
        BaseResponse<Integer> response = new BaseResponse<>();
        logger.info("--------------------------------删除公告类型信息开始--------------------------------");
        Integer code=0;
        if(noticeTypeService.del(request)){
            logger.info("公告类型信息删除成功");
            code=1;
            response.setResponseData(code);
        }else{
            logger.info("公告类型信息删除失败");
            response.setResponseData(code);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        logger.info("--------------------------------删除公告类型信息结束--------------------------------");
        return response;
    }

    /**
     * 删除,支持批量删除
     * @param id
     * @return
     * @throws SQLException
     */
    @ApiOperation(value = "delList", notes = "delete leave info")
    @RequestMapping(value = "/delList", method = RequestMethod.POST, produces = {"application/json"})
    public BaseResponse<Integer> delList(@ApiParam("批量删除的id") @RequestBody Long[] id) throws SQLException {
        logger.info("--------------------------------批量删除公告类型信息开始--------------------------------");
        // 返回参数初始化
        BaseResponse<Integer> response = new BaseResponse<>();
        // 删除数据
        response.setResponseData(noticeTypeService.delList(id));
        logger.info("--------------------------------批量删除公告类型信息结束--------------------------------");
        return response;
    }
    /**
     * 查询部门列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "/listType", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "列表", httpMethod = "POST", response = BaseResponse.class, notes = "查询所有公告类别")
    public BaseResponse<List<NoticeType>> typeList() {
        // 返回参数初始化
        BaseResponse<List<NoticeType>> response = new BaseResponse<>();
        try{
            // 获取学校数据
            List<NoticeType> responseData = noticeTypeService.typeList();
            response.setResponseData(responseData);
        }catch (Exception e){
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        return response;
    }
}
