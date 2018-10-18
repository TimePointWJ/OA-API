package com.odm.oa.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.odm.oa.entity.Goods;
import com.odm.oa.entity.Position;
import com.odm.oa.entity.SystemRole;
import com.odm.oa.entity.ex.DepartmentEx;
import com.odm.oa.entity.ex.GoodsEx_WJ;
import com.odm.oa.entity.ex.SystemRoleEx;
import com.odm.oa.model.request.DepartmentPagination;
import com.odm.oa.model.request.DepartmentRequest;
import com.odm.oa.model.request.GoodsPagination_WJ;
import com.odm.oa.model.request.GoodsRequest_WJ;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.service.GoodsService_WJ;
import com.odm.oa.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "商品" })
@RestController
@RequestMapping("/service/goods")
public class GoodsController_WJ extends BasicController{
	 @Autowired
	 private GoodsService_WJ goodsService;
	 /**
		 * sp一览
		 * 
		 * @param param
		 * @param request
		 * @return
		 */
		@ResponseBody
	    @RequestMapping( value = "goodsList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	    @ApiOperation(value = "商品信息一览", httpMethod = "POST", response = BaseResponse.class, notes = "查询商品列表")
		public BaseResponse<PageInfo<GoodsEx_WJ>> roleList(@ApiParam("输入 查询条件") 
		@RequestBody GoodsPagination_WJ goodsPagination, HttpServletRequest request) {
			logger.info("url:/service/goods/goodsList" + JSONObject.toJSONString(goodsPagination));
	        // 返回结果
			return goodsService.selectRoleList(goodsPagination);
		}
		/**
		 * 后台sp详情
		 * 
		 * @param param
		 * @param request
		 * @return
		 */
		@ResponseBody
	    @RequestMapping( value = "goodsDetail", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	    @ApiOperation(value = "商品详情", httpMethod = "POST", response = BaseResponse.class, notes = "查询商品详情")
		public BaseResponse<Goods> roleDetail(@ApiParam("输入 主键Id") 
		@RequestBody GoodsEx_WJ goodsEx, HttpServletRequest request) {
			logger.info("url:/service/role/roleDetail" + JSONObject.toJSONString(goodsEx));
	        // 返回结果
			return goodsService.roleDetail(goodsEx);
		}
		/**
		 * 查询sp信息列表
		 * @return
		 */
		@ApiOperation(value = "pagination", notes = "add user info")
		@RequestMapping(value = "/pagination", method = RequestMethod.POST, produces = { "application/json" })
		public BaseResponse<PageInfo<GoodsEx_WJ>> pagination(@ApiParam("查询参数") @RequestBody GoodsPagination_WJ request) {
			// 返回参数初始化
	        BaseResponse<PageInfo<GoodsEx_WJ>> response = new BaseResponse<>();
	        boolean valid = true;
			if (valid) {
				PageInfo<GoodsEx_WJ> responseData=goodsService.pagination(request);
				response.setResponseData(responseData);
	            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
	        }else{
	        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
	        }  
	        return response;
		}
		/**
		 * 添加sp信息
		 */
		@ApiOperation(value = "addGoods", notes = "add user info")
		@RequestMapping(value = "/addGoods", method = RequestMethod.POST, produces = { "application/json" })
		public BaseResponse<String> insertGoods(@ApiParam("查询参数") @RequestBody Goods request) {
			// 返回参数初始化
	        BaseResponse<String> response = new BaseResponse<>();
	        boolean valid = true;
			if (valid) {
				goodsService.insertGoods(request,response);
	        }else{
	        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
	        }  
	        return response;
		}
		/**
		 * 修改sp信息
		 */
		@ApiOperation(value = "updateGoods", notes = "add user info")
		@RequestMapping(value = "/updateGoods", method = RequestMethod.POST, produces = { "application/json" })
		public BaseResponse<String> updateGoods(@ApiParam("查询参数") @RequestBody GoodsRequest_WJ request) {
			// 返回参数初始化
			BaseResponse<String> response = new BaseResponse<>();
	        boolean valid = true;
			if (valid) {
				goodsService.updateGoods(request,response);
	        }else{
	        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
	        }  
	        return response;
		}
		/**
		 * 删除部门信息
		 */
		@ApiOperation(value = "deleteDept", notes = "add user info")
		@RequestMapping(value = "/deleteGoods", method = RequestMethod.POST, produces = { "application/json" })
		public BaseResponse<String> deleteDept(@ApiParam("查询参数") @RequestBody Long[] ids) {
			// 返回参数初始化
			BaseResponse<String> response = new BaseResponse<>();
	        boolean valid = true;
			if (valid) {
				goodsService.deleteGoods(ids);
	            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
	        }else{
	        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
	        }  
	        return response;
		}
//	    /**
//		 * 修改sp信息
//		 */
//		@ApiOperation(value = "updateGoods", notes = "add user info")
//		@RequestMapping(value = "/updateGoods", method = RequestMethod.POST, produces = { "application/json" })
//		public BaseResponse<String> updateGoods(@ApiParam("查询参数") @RequestBody GoodsRequest_WJ request) {
//			// 返回参数初始化
//			BaseResponse<String> response = new BaseResponse<>();
//	        boolean valid = true;
//			if (valid) {
//				goodsService.updateGoods(request,response);
//	        }else{
//	        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
//	        }  
//	        return response;
//		}
//		/**
//		 * 删除商品信息
//		 */
//		@ApiOperation(value = "deleteGoods", notes = "add user info")
//		@RequestMapping(value = "/deleteGoods", method = RequestMethod.POST, produces = { "application/json" })
//		public BaseResponse<String> deleteStaff(@ApiParam("商品id") @RequestBody Long[] ids) {
//			// 返回参数初始化
//	        BaseResponse<String> response = new BaseResponse<>();
//	        boolean valid = true;
//			if (valid) {
//				goodsService.deleteGoods(ids);
//	            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
//	        }else{
//	        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
//	        }  
//	        return response;
//		}
//		/**
//		 * 删除sp信息
//		 */
//		@ApiOperation(value = "deleteGoods", notes = "add user info")
//		@RequestMapping(value = "/deleteGoods", method = RequestMethod.POST, produces = { "application/json" })
//		public BaseResponse<String> deleteGoods(@ApiParam("查询参数") @RequestBody Long[] ids) {
//			// 返回参数初始化
//			BaseResponse<String> response = new BaseResponse<>();
//	        boolean valid = true;
//			if (valid) {
//				goodsService.deleteGoods(ids);
//	            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
//	        }else{
//	        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
//	        }  
//	        return response;
//		}


	    /**
	     * 删除,支持批量删除
	     * @param id
	     * @return
	     * @throws SQLException
	     */
	    @ApiOperation(value = "delList", notes = "delete leave info")
	    @RequestMapping(value = "/delList", method = RequestMethod.POST, produces = {"application/json"})
	    public BaseResponse<Integer> delList(@ApiParam("批量删除的id") @RequestBody Long[] id) throws SQLException {
	        logger.info("--------------------------------批量删除商品信息开始--------------------------------");
	        // 返回参数初始化
	        BaseResponse<Integer> response = new BaseResponse<>();
	        // 删除数据
	        response.setResponseData(goodsService.delList(id));
	        logger.info("--------------------------------批量商品职位信息结束--------------------------------");
	        return response;
	    }
	    /**
	     * 商品条件查询
	     * @param request
	     * @return
	     * @throws SQLException
	     */
	    @ResponseBody
	    @RequestMapping( value = "/selectByConditions", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	    @ApiOperation(value = "", httpMethod = "POST", response = BaseResponse.class, notes = "tiao查询")
	    public BaseResponse<PageInfo<GoodsEx_WJ>> selectCondition(@RequestBody GoodsPagination_WJ  request) {
	             // 返回参数初始化
	        BaseResponse<PageInfo<GoodsEx_WJ>> response = new BaseResponse<>();
			try{
				// 获取学校数据
				PageInfo<GoodsEx_WJ> responseData= goodsService.selectByConditions(request);
				response.setResponseData(responseData);
	        }catch (Exception e){
	        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
	        }  
	        return response;
	    }
		
}


