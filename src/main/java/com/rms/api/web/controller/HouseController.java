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
	public String list(Integer p_n,Integer p_s) {
		if(p_n == null || p_s == null) {
			ResponseData data = new ResponseData();
			data.setCode("101");
			return JsonUtil.object2Json(data);
		}
		
		Map<String,Object> result = new HashMap<String,Object>();
		
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
		
		result.put("code", "200");
		result.put("msg", "");
		result.put("data", map);
		
		return JsonUtil.object2Json(result);
	}
	
	@RequestMapping(value="ad",method=RequestMethod.GET)
	@ResponseBody
	public String ad() {
		Map<String,Object> result = new HashMap<String,Object>();
		
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
		
		result.put("code", "200");
		result.put("msg", "");
		result.put("data", map);
		return JsonUtil.object2Json(result);
	}
	
	@RequestMapping(value="info",method=RequestMethod.GET)
	@ResponseBody
	public String info(String house_id) {
		if(StringUtils.isEmpty(house_id)) {
			ResponseData data = new ResponseData();
			data.setCode("101");
			return JsonUtil.object2Json(data);
		}
		
		Map<String,Object> result = new HashMap<String,Object>();
		
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
		
		result.put("code", "200");
		result.put("msg", "");
		result.put("data", map);
		
		return JsonUtil.object2Json(result);
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
		Map<String,Object> result = new HashMap<String,Object>();
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<Map> list = new ArrayList<Map>();
		Map mp = new HashMap();
		mp.put("id", "1");
		mp.put("time", "2015-12-01 14:30");
		mp.put("progress", 1);
		mp.put("cover", "http://218.80.0.218:12301/201409121457331406.JPG");
		list.add(mp);
		
		map.put("book", list);
		
		result.put("code", "200");
		result.put("msg", "");
		result.put("data", map);
		
		return JsonUtil.object2Json(result);
	}
	
	@RequestMapping(value="booking/order",method=RequestMethod.GET)
	@ResponseBody
	public String bookingOrder(String house_id) {
		if(StringUtils.isEmpty(house_id)) {
			ResponseData data = new ResponseData();
			data.setCode("101");
			return JsonUtil.object2Json(data);
		}
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("order_id", "201512011430000001");
		map.put("price", 2400);
		
		result.put("code", "200");
		result.put("msg", "");
		result.put("data", map);
		
		return JsonUtil.object2Json(result);
	}
	
	@RequestMapping(value="booking/info",method=RequestMethod.GET)
	@ResponseBody
	public String bookingInfo(String house_id) {
		if(StringUtils.isEmpty(house_id)) {
			ResponseData data = new ResponseData();
			data.setCode("101");
			return JsonUtil.object2Json(data);
		}
		
		Map<String,Object> result = new HashMap<String,Object>();
		
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
		
		result.put("code", "200");
		result.put("msg", "");
		result.put("data", map);
		
		return JsonUtil.object2Json(result);
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
	
	@RequestMapping(value="booked",method=RequestMethod.POST)
	@ResponseBody
	public String bookingBooked(String house_id,String sign_date,String end_date) {
		ResponseData data = new ResponseData();
		if(StringUtils.isEmpty(house_id) || StringUtils.isEmpty(sign_date) || StringUtils.isEmpty(end_date)) {
			data.setCode("101");
			return JsonUtil.object2Json(data);
		}
		
		data.setCode("200");
		return JsonUtil.object2Json(data);
	}
	
	@RequestMapping(value="booked/list",method=RequestMethod.GET)
	@ResponseBody
	public String bookingBooked(String house_id) {
		if(StringUtils.isEmpty(house_id)) {
			ResponseData data = new ResponseData();
			data.setCode("101");
			return JsonUtil.object2Json(data);
		}
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<Map> list = new ArrayList<Map>();
		Map mp = new HashMap();
		mp.put("house_id", "1");
		mp.put("desc", "唐丰苑 地铁二号线");
		mp.put("status", "0");
		mp.put("cover", "http://218.80.0.218:12301/201409121457331406.JPG");
		list.add(mp);
		
		mp = new HashMap();
		mp.put("house_id", "2");
		mp.put("desc", "唐丰苑 地铁二号线");
		mp.put("status", "1");
		mp.put("cover", "http://218.80.0.218:12301/201409121457331406.JPG");
		list.add(mp);
		
		map.put("houses", list);
		
		result.put("code", "200");
		result.put("msg", "");
		result.put("data", map);
		
		return JsonUtil.object2Json(result);
	}
	
	@RequestMapping(value="booked/protocol",method=RequestMethod.GET)
	@ResponseBody
	public String bookingProtocol(String house_id) {
		if(StringUtils.isEmpty(house_id)) {
			ResponseData data = new ResponseData();
			data.setCode("101");
			return JsonUtil.object2Json(data);
		}
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("book_name", "欧阳慕容");
		map.put("book_phone", "13896327852");
		map.put("book_certid", "310123190001011234");
		map.put("house_addr", "浦东唐丰苑");
		map.put("start_date", "2016-01-01");
		map.put("end_date", "2016-09-30");
		map.put("deposit_amount", 500);
		map.put("rent_amount", 2400);
		
		result.put("code", "200");
		result.put("msg", "");
		result.put("data", map);
		
		return JsonUtil.object2Json(result);
	}
	
	@RequestMapping(value="booked/order",method=RequestMethod.POST)
	@ResponseBody
	public String bookedOrder(String house_id) {
		if(StringUtils.isEmpty(house_id)) {
			ResponseData data = new ResponseData();
			data.setCode("101");
			return JsonUtil.object2Json(data);
		}
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		result.put("code", "200");
		result.put("msg", "");
		return JsonUtil.object2Json(result);
	}
	
	@RequestMapping(value="booked/info",method=RequestMethod.GET)
	@ResponseBody
	public String bookedInfo(String house_id) {
		if(StringUtils.isEmpty(house_id)) {
			ResponseData data = new ResponseData();
			data.setCode("101");
			return JsonUtil.object2Json(data);
		}
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("house_id", "1");
		map.put("desc", "唐丰苑 地铁站5分钟");
		map.put("cover", "http://218.80.0.218:12301/201409121457331406.JPG");
		map.put("rent_amount", 2400);
		map.put("start_date", "2015-10-01");
		map.put("end_date", "2016-09-30");
		map.put("deposit_amount", 500);
		map.put("rent_name", "欧阳慕容");
		map.put("id_no", "310123190001011234");
		map.put("rent_gender", "1");
		map.put("rent_phone", "13811114444");
		map.put("note", "房子我定了");
		
		result.put("code", "200");
		result.put("msg", "");
		result.put("data", map);
		
		return JsonUtil.object2Json(result);
	}
	
	@RequestMapping(value="sign",method=RequestMethod.POST)
	@ResponseBody
	public String sign(String house_id,String end_date,String msg) {
		if(StringUtils.isEmpty(house_id) || StringUtils.isEmpty(end_date)) {
			ResponseData data = new ResponseData();
			data.setCode("101");
			return JsonUtil.object2Json(data);
		}
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		result.put("code", "200");
		result.put("msg", "");
		
		return JsonUtil.object2Json(result);
	}
	
	@RequestMapping(value="sign/order",method=RequestMethod.POST)
	@ResponseBody
	public String signOrder(String house_id) {
		if(StringUtils.isEmpty(house_id)) {
			ResponseData data = new ResponseData();
			data.setCode("101");
			return JsonUtil.object2Json(data);
		}
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		result.put("code", "200");
		result.put("msg", "");
		
		return JsonUtil.object2Json(result);
	}
	
	@RequestMapping(value="contract/continue",method=RequestMethod.POST)
	@ResponseBody
	public String contractContinue(String contract_id,String end_date,String msg) {
		if(StringUtils.isEmpty(contract_id) || StringUtils.isEmpty(end_date)) {
			ResponseData data = new ResponseData();
			data.setCode("101");
			return JsonUtil.object2Json(data);
		}
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		result.put("code", "200");
		result.put("msg", "");
		
		return JsonUtil.object2Json(result);
	}
	
	@RequestMapping(value="return",method=RequestMethod.POST)
	@ResponseBody
	public String returnBack(String contract_id,String rent_date,String msg) {
		if(StringUtils.isEmpty(contract_id) || StringUtils.isEmpty(rent_date)) {
			ResponseData data = new ResponseData();
			data.setCode("101");
			return JsonUtil.object2Json(data);
		}
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		result.put("code", "200");
		result.put("msg", "");
		
		return JsonUtil.object2Json(result);
	}
}
