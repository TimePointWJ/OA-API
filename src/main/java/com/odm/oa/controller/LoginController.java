package com.odm.oa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.odm.oa.entity.Staff;
import com.odm.oa.model.request.LoginForm;
import com.odm.oa.model.response.BaseResponse;
import com.odm.oa.model.response.LoginToken;
import com.odm.oa.service.StaffService;
import com.odm.oa.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * create LoginController by HJR
 * 
 * 2017/12/9  下午23:30
 */
@Api(tags={"Login"})
@Controller(value = "LoginController") 
@RestController
public class LoginController extends BasicController{

    @Autowired
    private StaffService userService;
    @Autowired
	private StaffService staffService;
    @ResponseBody
    @RequestMapping( value = "/login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "登录", httpMethod = "POST", response = LoginForm.class, notes = "Login controller")
    public BaseResponse<LoginToken> login(HttpSession httpSession, @ApiParam(name = "loginForm", value = "login user info", required = true) @RequestBody LoginForm loginForm) {
    	logger.info("login start");
    	BaseResponse<LoginToken> result = new BaseResponse<>();
    	if (StringUtils.isEmpty(loginForm.getUsername())) {
    		result.setStatusCode(201);
    		result.setStatusMsg("用户名不得为空");
			return result;
		}
		if (StringUtils.isEmpty(loginForm.getPassword())) {
			result.setStatusCode(201);
    		result.setStatusMsg("密码不得为空");
			return result;
		}
		try {
			Staff param = new Staff();
			param.setStaffNo(loginForm.getUsername());
			param.setPassword(loginForm.getPassword());
			result = userService.validateLogin(param);
		} catch (Exception e) {
			logger.error("login end", e);
		}
		logger.info("login end");
        return result;
    }
    /**
	 * 添加员工信息(员工自己注册)
	 * @return
	 */
	@ApiOperation(value = "register", notes = "add user info")
	@RequestMapping( value = "/register", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public BaseResponse<String> insertStaff(@ApiParam("员工信息") @RequestBody Staff request) {
		// 返回参数初始化
        BaseResponse<String> response = new BaseResponse<>();
        boolean valid = true;
		if (valid) {
			staffService.insertStaff(request,response);
        }else{
        	response.setStatusCode(Constants.RESPONSE_STATUS_CODE_FAILED);
        }  
        return response;
	}
}
