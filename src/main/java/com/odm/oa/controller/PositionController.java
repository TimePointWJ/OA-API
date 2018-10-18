package com.odm.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.odm.oa.entity.LeaveType;
import com.odm.oa.entity.Position;
import com.odm.oa.model.request.PaginationBase;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.service.PositionService;
import com.odm.oa.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Api(tags={"职位信息"})
@RestController
@RequestMapping("/service/position")
public class PositionController extends BasicController{
    @Autowired
    private PositionService positionService;
    /**
     * 分页查询
     * @param httpSession
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "/pageList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "分页查询", httpMethod = "POST", response = LeaveType.class, notes = "获取职位信息")
    public BaseResponse<PageInfo<Position>> list(HttpSession httpSession, @ApiParam(name = "PaginationBase",
            value = "分页信息", required = true) @RequestBody PaginationBase request) {
        // 返回参数初始化
        logger.info("PositionController > getList" + JSONObject.toJSONString(request));
        BaseResponse<PageInfo<Position>> response = new BaseResponse<>();
        logger.info("--------------------------------获取职位信息开始--------------------------------");
        PageInfo<Position> responseData = positionService.getPage(request);
        response.setResponseData(responseData);
        //response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        logger.info("--------------------------------获取职位信息结束--------------------------------");
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
    @ApiOperation(value = "添加职位信息", httpMethod = "POST", response = Integer.class, notes = "添加职位信息")
    public BaseResponse<Integer> add(HttpSession httpSession, @ApiParam(name = "Position", value = "add info",
            required = true) @RequestBody Position request) {
        // 返回参数初始化
        logger.info("PositionController > add" + JSONObject.toJSONString(request));
        BaseResponse<Integer> response = new BaseResponse<>();
        logger.info("--------------------------------添加职位信息开始--------------------------------");
        Integer code=1;
        if(positionService.insertOrUpdate(request)){
            logger.info("职位信息添加或更新成功");
            code=0;
            response.setResponseData(code);
        }else{
            logger.info("职位信息添加或更新失败");
            response.setResponseData(code);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        logger.info("--------------------------------添加职位信息结束--------------------------------");
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
    @ApiOperation(value = "删除职位信息", httpMethod = "POST", response = Integer.class, notes = "删除职位信息")
    public BaseResponse<Integer> delete(HttpSession httpSession, @ApiParam(name = "position", value = "delete info",
            required = true) @RequestBody Position request) {
        // 返回参数初始化
        logger.info("PositionController > add" + JSONObject.toJSONString(request));
        BaseResponse<Integer> response = new BaseResponse<>();
        logger.info("--------------------------------删除职位信息开始--------------------------------");
        Integer code=0;
        if(positionService.del(request)){
            logger.info("职位信息删除成功");
            code=1;
            response.setResponseData(code);
        }else{
            logger.info("职位信息删除失败");
            response.setResponseData(code);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }
        logger.info("--------------------------------删除职位信息结束--------------------------------");
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
        logger.info("--------------------------------批量删除职位信息开始--------------------------------");
        // 返回参数初始化
        BaseResponse<Integer> response = new BaseResponse<>();
        // 删除数据
        response.setResponseData(positionService.delList(id));
        logger.info("--------------------------------批量删除职位信息结束--------------------------------");
        return response;
    }
}
