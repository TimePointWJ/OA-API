package com.odm.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.odm.oa.entity.ex.AddressPrivateEx;
import com.odm.oa.entity.ex.StaffEx;
import com.odm.oa.model.request.StaffPagination;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.service.AddressService;
import com.odm.oa.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "通讯录" })
@RestController
@RequestMapping("/service/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	/**
	 * 查询员工信息列表
	 * @return
	 */
	@ApiOperation(value = "paginationPublic", notes = "add user info")
	@RequestMapping(value = "/paginationPublic", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<PageInfo<StaffEx>> paginationPublic(@ApiParam("查询参数") @RequestBody StaffPagination request) {
		// 返回参数初始化
        BaseResponse<PageInfo<StaffEx>> response = new BaseResponse<>();
        boolean valid = true;
		if (valid) {
			PageInfo<StaffEx> responseData=addressService.paginationPublic(request);
			response.setResponseData(responseData);
            response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
        }else{
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
	}
	/**
	 * 查询员工信息列表
	 * @return
	 */
	@ApiOperation(value = "paginationPrivate", notes = "add user info")
	@RequestMapping(value = "/paginationPrivate", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<PageInfo<StaffEx>> paginationPrivate(@ApiParam("查询参数") @RequestBody StaffPagination request) {
		// 返回参数初始化
		BaseResponse<PageInfo<StaffEx>> response = new BaseResponse<>();
		boolean valid = true;
		if (valid) {
			PageInfo<StaffEx> responseData=addressService.paginationPrivate(request);
			response.setResponseData(responseData);
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_SUCCESS);
		}else{
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
		}  
		return response;
	}
	/**
	 * 添加到我的私有通讯录
	 */
	@ApiOperation(value = "addToPrivate", notes = "add user info")
	@RequestMapping(value = "/addToPrivate", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<String> addToPrivate(@ApiParam("查询参数") @RequestBody AddressPrivateEx request) {
		// 返回参数初始化
		BaseResponse<String> response = new BaseResponse<>();
		boolean valid = true;
		if (valid) {
			addressService.addToPrivate(request,response);
		}else{
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
		}  
		return response;
	}
	/**
	 * 从我的私有通讯录移出
	 */
	@ApiOperation(value = "moveFormPrivate", notes = "add user info")
	@RequestMapping(value = "/moveFormPrivate", method = RequestMethod.POST, produces = { "application/json" })
	public BaseResponse<String> moveFormPrivate(@ApiParam("查询参数") @RequestBody AddressPrivateEx request) {
		// 返回参数初始化
		BaseResponse<String> response = new BaseResponse<>();
		boolean valid = true;
		if (valid) {
			addressService.moveFormPrivate(request,response);
		}else{
			response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
		}  
		return response;
	}
}
