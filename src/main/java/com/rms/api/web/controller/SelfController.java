package com.rms.api.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rms.api.web.base.BaseController;
import com.rms.api.web.util.HttpClientUtil;

@Controller
@RequestMapping("self")
public class SelfController extends BaseController {
	Logger log = LoggerFactory.getLogger(SelfController.class);

	@RequestMapping(value = "pwd")
	@ResponseBody
	public String changePwd(HttpServletRequest request) {
		return HttpClientUtil.doPost(getRmsUrl(), "system/self/pwd", request);
	}
}
