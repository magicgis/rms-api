package com.rms.api.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rms.api.web.util.JsonUtil;

@Controller
@RequestMapping("house")
public class HouseController {
	@RequestMapping(value="list",method=RequestMethod.GET)
	@ResponseBody
	public String list(int p_n,int p_c) {
		return "";
	}
	
	@RequestMapping(value="ad",method=RequestMethod.GET)
	@ResponseBody
	public String ad() {
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<Map> list = new ArrayList<Map>();
		Map<String,String> adMap = new HashMap<String,String>();
		adMap.put("id", "1");
		adMap.put("type", "0");
		adMap.put("value", "http://218.80.0.218:12301/test.html");
		adMap.put("url", "http://218.80.0.218:12301/banner1.jpg");
		list.add(adMap);
		adMap = new HashMap<String,String>();
		adMap.put("id", "2");
		adMap.put("type", "0");
		adMap.put("value", "http://218.80.0.218:12301/test.html");
		adMap.put("url", "http://218.80.0.218:12301/banner2.jpg");
		list.add(adMap);
		adMap = new HashMap<String,String>();
		adMap.put("id", "3");
		adMap.put("type", "1");
		adMap.put("value", "1001");
		adMap.put("url", "http://218.80.0.218:12301/banner3.jpg");
		list.add(adMap);
		
		map.put("ad", list);
		return JsonUtil.object2Json(map);
	}
}
