package com.odm.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.odm.oa.entity.ex.MessageEx;
import com.odm.oa.model.request.MessagePagination;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.service.MessageService;
import com.odm.oa.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "消息" })
@RestController
@RequestMapping("/service/message")
public class MessageController {
	@Autowired
	private MessageService messageService;

	/**
	 * 查询员工信息列表
	 * 
	 * @return
	 */
	@ApiOperation(value = "pagination", notes = "add user info")
	@RequestMapping(value = "/pagination", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<PageInfo<MessageEx>> pagination(@ApiParam("查询参数") @RequestBody MessagePagination request) {
		// 返回参数初始化
		BaseResponse<PageInfo<MessageEx>> response = new BaseResponse<>();
		boolean valid = true;
		if (valid) {
			PageInfo<MessageEx> responseData = messageService.pagination(request);
			response.setResponseData(responseData);
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
		} else {
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
		}
		return response;
	}

	/**
	 * 发送消息
	 */
	@ApiOperation(value = "sendMessage", notes = "add user info")
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<String> sendMessage(@ApiParam("查询参数") @RequestBody MessageEx request) {
		// 返回参数初始化
		BaseResponse<String> response = new BaseResponse<>();
		boolean valid = true;
		if (valid) {
			messageService.sendMessage(request, response);
		} else {
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
		}
		return response;
	}
	/**
	 * 删除此条消息
	 */
	@ApiOperation(value = "deleteMessage", notes = "add user info")
	@RequestMapping(value = "/deleteMessage", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<String> deleteMessage(@ApiParam("查询参数") @RequestBody MessageEx request) {
		// 返回参数初始化
		BaseResponse<String> response = new BaseResponse<>();
		boolean valid = true;
		if (valid) {
			messageService.deleteMessage(request, response);
		} else {
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
		}
		return response;
	}
	/**
	 * 删除全部消息
	 */
	@ApiOperation(value = "deleteAllMessage", notes = "add user info")
	@RequestMapping(value = "/deleteAllMessage", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<String> deleteAllMessage(@ApiParam("查询参数") @RequestBody MessageEx request) {
		// 返回参数初始化
		BaseResponse<String> response = new BaseResponse<>();
		boolean valid = true;
		if (valid) {
			messageService.deleteAllMessage(request, response);
		} else {
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
		}
		return response;
	}
}
