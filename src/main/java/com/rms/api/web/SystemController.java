package com.rms.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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
	public String register(@RequestBody  String params) {
		return HttpClientUtil.doPost(getRmsUrl(), "system/register", params);		
	}
	
	@RequestMapping(value="check_code")
	@ResponseBody
	public String check_code(@RequestBody  String params) {
		return HttpClientUtil.doPost(getRmsUrl(), "system/check_code", params);
	}
	
	@RequestMapping(value="login/pwd")
	@ResponseBody
	public String loginWithPwd(@RequestBody  String params) {
		return HttpClientUtil.doPost(getRmsUrl(), "system/login/pwd", params);
	}
	
	@RequestMapping(value="login/code")
	@ResponseBody
	public String loginWithCode(@RequestBody  String params) {
		return HttpClientUtil.doPost(getRmsUrl(), "system/login/code", params);
		
	}

}
