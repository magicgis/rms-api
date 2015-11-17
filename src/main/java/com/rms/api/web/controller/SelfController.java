package com.rms.api.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rms.api.web.base.BaseController;
import com.rms.api.web.util.HttpClientUtil;
import com.rms.api.web.util.JsonUtil;

@Controller
@RequestMapping("self")
public class SelfController extends BaseController {
	Logger log = LoggerFactory.getLogger(SelfController.class);

	@RequestMapping(value = "pwd")
	@ResponseBody
	public String changePwd(HttpServletRequest request) {
		return HttpClientUtil.doPost(getRmsUrl(), "system/self/pwd", request);
	}
	
	@RequestMapping(value="message",method=RequestMethod.GET)
	@ResponseBody
	public String message() {
		Map<String,Object> result = new HashMap<String,Object>();
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<Map> list = new ArrayList<Map>();
		Map<String,String> msgMap = new HashMap<String,String>();
		msgMap.put("id", "1");
		msgMap.put("time", "2015-10-01 10:30");
		msgMap.put("msg", "尊敬的唐先生，您的报修已通过维修师傅确认，师傅将准时上门为您维修。");
		list.add(msgMap);
		Map<String,String> msgMap2 = new HashMap<String,String>();
		msgMap2.put("id", "2");
		msgMap2.put("time", "2015-10-02 11:30");
		msgMap2.put("msg", "尊敬的唐先生，您的10月份房租账单已出，请在10月15日前缴纳租金。");
		list.add(msgMap2);
		Map<String,String> msgMap3 = new HashMap<String,String>();
		msgMap3.put("id", "3");
		msgMap3.put("time", "2015-10-05 11:00");
		msgMap3.put("msg", "尊敬的唐先生，您的水费余额已不足，请及时缴纳以避免断水情况的发生。");
		list.add(msgMap3);
		
		result.put("code", "200");
		result.put("msg", "");
		result.put("data", list);
		return JsonUtil.object2Json(result);
	}
	
	@RequestMapping(value="info",method=RequestMethod.GET)
	@ResponseBody
	public String info() {
		Map<String,Object> result = new HashMap<String,Object>();
		

		Map<String,String> infoMap = new HashMap<String,String>();
		infoMap.put("name", "唐先生");
		infoMap.put("id", "430525198701011234");
		infoMap.put("sex", "男");
		infoMap.put("birth", "1987-01-01");
		infoMap.put("age", "28");
		infoMap.put("profession", "程序员");
		infoMap.put("corp", "IBM");
		
		result.put("code", "200");
		result.put("msg", "");
		result.put("data", infoMap);
		return JsonUtil.object2Json(result);
	}
}
