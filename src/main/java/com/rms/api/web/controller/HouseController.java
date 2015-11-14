package com.rms.api.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rms.api.web.entity.ResponseData;
import com.rms.api.web.util.JsonUtil;

@Controller
@RequestMapping("house")
public class HouseController {
	@RequestMapping(value="list",method=RequestMethod.GET)
	@ResponseBody
	public String list(Integer p_n,Integer p_c) {
		if(p_n == null || p_c == null) {
			ResponseData data = new ResponseData();
			data.setCode("101");
			return JsonUtil.object2Json(data);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map> list = new ArrayList<Map>();
		Map mp = new HashMap();
		mp.put("id", "1");
		mp.put("price", new Double(2400));
		mp.put("short_desc", "独卫、阳台");
		mp.put("short_location", "地铁二号线唐镇");
		mp.put("pay_way", 0);
		mp.put("cover", "http://218.80.0.218:12301/201409121457331406.JPG");
		list.add(mp);
		
		mp = new HashMap();
		mp.put("id", "2");
		mp.put("price", new Double(1800));
		mp.put("short_desc", "独卫、阳台");
		mp.put("short_location", "地铁二号线唐镇");
		mp.put("pay_way", 1);
		mp.put("cover", "http://218.80.0.218:12301/201409121504380781.JPG");
		list.add(mp);
		
		map.put("houses", list);
		map.put("p_t", 2);
		
		return JsonUtil.object2Json(map);
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
	
	@RequestMapping(value="info",method=RequestMethod.GET)
	@ResponseBody
	public String info(String house_id) {
		if(StringUtils.isEmpty(house_id)) {
			ResponseData data = new ResponseData();
			data.setCode("101");
			return JsonUtil.object2Json(data);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("house_num", "TFY1001");
		map.put("id", "1");
		map.put("title", "浦东唐镇唐丰苑、独卫带阳台");
		map.put("price", new Double(2400));
		map.put("pay_way", 0);
		map.put("previews", "http://218.80.0.218:12301/201409121504380781.JPG,http://218.80.0.218:12301/201409121457331406.JPG");
		map.put("layout", "2室1厅1万卫");
		map.put("area", 45.6);
		map.put("decorate", 1);
		map.put("summary", "独卫带阳台");
		map.put("floor", "3");
		map.put("orientate", "北");
		map.put("address", "唐镇唐丰苑");
		map.put("equipment", "0000011111");
		map.put("contact_phone", "400-883-1184");
		
		return JsonUtil.object2Json(map);
	}
	
	@RequestMapping(value="booking",method=RequestMethod.POST)
	@ResponseBody
	public String booking(String house_id,String b_time,String b_name,String phone,
			@RequestParam(required=false)Integer sex,@RequestParam(required=false)String msg) {
		ResponseData data = new ResponseData();
		
		if(StringUtils.isEmpty(house_id) || StringUtils.isEmpty(b_time) || StringUtils.isEmpty(b_name)
				|| StringUtils.isEmpty(phone)) {
			data.setCode("101");
			return JsonUtil.object2Json(data);
		}
		
		data.setCode("200");
		return JsonUtil.object2Json(data);
	}
	
	@RequestMapping(value="booking/list",method=RequestMethod.GET)
	@ResponseBody
	public String bookingList() {
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<Map> list = new ArrayList<Map>();
		Map mp = new HashMap();
		mp.put("id", "1");
		mp.put("time", "2015-12-01 14:30");
		mp.put("progress", 1);
		mp.put("cover", "http://218.80.0.218:12301/201409121457331406.JPG");
		list.add(mp);
		
		map.put("book", list);
		
		return JsonUtil.object2Json(map);
	}
	
	@RequestMapping(value="booking/order",method=RequestMethod.GET)
	@ResponseBody
	public String bookingOrder(String house_id) {
		if(StringUtils.isEmpty(house_id)) {
			ResponseData data = new ResponseData();
			data.setCode("101");
			return JsonUtil.object2Json(data);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("order_id", "201512011430000001");
		map.put("price", 2400);
		return JsonUtil.object2Json(map);
	}
	
	@RequestMapping(value="booking/info",method=RequestMethod.GET)
	@ResponseBody
	public String bookingInfo(String house_id) {
		if(StringUtils.isEmpty(house_id)) {
			ResponseData data = new ResponseData();
			data.setCode("101");
			return JsonUtil.object2Json(data);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("progress", 1);
		map.put("cover", "http://218.80.0.218:12301/201409121457331406.JPG");
		map.put("short_desc", "独卫、阳台");
		map.put("short_location", "地铁二号线唐镇");
		map.put("house_num", "TFY1001");
		map.put("b_time", "2015-12-01 14:30");
		map.put("b_name", "欧阳慕容");
		map.put("phone", "13812348765");
		map.put("sex", 1);
		map.put("msg", "房子我要了，不差钱！");
		return JsonUtil.object2Json(map);
	}
	
	@RequestMapping(value="booking/cancel",method=RequestMethod.POST)
	@ResponseBody
	public String bookingCancel(String house_id) {
		ResponseData data = new ResponseData();
		if(StringUtils.isEmpty(house_id)) {
			data.setCode("101");
			return JsonUtil.object2Json(data);
		}
		
		data.setCode("200");
		return JsonUtil.object2Json(data);
	}
	
	@RequestMapping(value="booking/booked",method=RequestMethod.POST)
	@ResponseBody
	public String bookingBooked(String house_id) {
		ResponseData data = new ResponseData();
		if(StringUtils.isEmpty(house_id)) {
			data.setCode("101");
			return JsonUtil.object2Json(data);
		}
		
		data.setCode("200");
		return JsonUtil.object2Json(data);
	}
}
