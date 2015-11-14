package com.rms.api.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
	public String register(HttpServletRequest request) {
		return HttpClientUtil.doPost(getRmsUrl(), "system/register", request);		
	}
	
	@RequestMapping(value="check_code")
	@ResponseBody
	public String check_code(HttpServletRequest request) {
		String res = HttpClientUtil.doPost(getRmsUrl(), "system/check_code", request);
		return res;
	}
	
	@RequestMapping(value="login/pwd")
	@ResponseBody
	public String loginWithPwd(HttpServletRequest request) {
		return HttpClientUtil.doPost(getRmsUrl(), "system/login/pwd", request);
	}
	
	@RequestMapping(value="login/code")
	@ResponseBody
	public String loginWithCode(HttpServletRequest request) {
		return HttpClientUtil.doPost(getRmsUrl(), "system/login/code", request);
		
	}

}
