package com.odm.oa.model.response;

import com.odm.oa.utils.Constants;

/**
 * Response 基础类
 * 
 * @author HJR
 * UPDATRE DATE 2017/12/11
 * @param <T>
 */
public class BaseResponse<T> {

    private Integer statusCode;
    private String statusMsg;
    private T responseData;

    public BaseResponse() {
        this.statusCode = Constants.RESPONSE_STATUS_CODE_SUCCESS;
        this.statusMsg = Constants.RESPONSE_STATUS_MSG_SUCCESS;
    }
    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

	public T getResponseData() {
		return responseData;
	}

	public void setResponseData(T responseData) {
		this.responseData = responseData;
	}

}
