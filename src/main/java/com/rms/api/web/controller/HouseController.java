package com.rms.api.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("house")
public class HouseController {
	@RequestMapping(value="list",method=RequestMethod.GET)
	@ResponseBody
	public String list(int p_n,int p_c) {
		return "";
	}
}
