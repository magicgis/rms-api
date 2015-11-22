package com.rms.api.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rms.api.web.base.BaseController;
import com.rms.api.web.util.HttpClientUtil;

@Controller
@RequestMapping("system")
public class SystemController extends BaseController{
	Logger log = LoggerFactory.getLogger(SystemController.class);

	@RequestMapping(value="register")
	@ResponseBody
	public void register(HttpServletRequest request,HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "system/register", request, response);		
	}
	
	@RequestMapping(value="check_code")
	@ResponseBody
	public void check_code(HttpServletRequest request,HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "system/check_code", request, response);
	}
	
	@RequestMapping(value="login/pwd")
	@ResponseBody
	public void loginWithPwd(HttpServletRequest request,HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "system/login/pwd", request, response);
	}
	
	@RequestMapping(value="login/code")
	@ResponseBody
	public void loginWithCode(HttpServletRequest request,HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "system/login/code", request,response);
		
	}
	
	@RequestMapping(value="pwd/reset")
	@ResponseBody
	public void pwdReset(HttpServletRequest request,HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "system/pwd/reset", request,response);
		
	}

}
