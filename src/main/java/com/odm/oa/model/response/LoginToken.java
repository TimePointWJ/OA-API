package com.odm.oa.model.response;

import com.odm.oa.entity.Staff;

/**
 * create LoginToken by HJR
 * 
 * 2017/12/9  下午23:30
 */
public class LoginToken extends Staff{
    
	private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
