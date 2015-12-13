package com.rms.api.web.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rms.api.web.base.BaseController;
import com.rms.api.web.entity.ResponseData;
import com.rms.api.web.util.HttpClientUtil;

@Controller
@RequestMapping("house")
public class HouseController extends BaseController {
	
	Logger log = LoggerFactory.getLogger(HouseController.class);
	
	@Value("#{configProperties['pic.url']}")
	private String pic_url;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	@ResponseBody
	public void list(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/findFeatureList", request, response);
	}
	
	@RequestMapping(value="ad",method=RequestMethod.GET)
	@ResponseBody
	public void ad(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/ad", request, response);
	}
	
	@RequestMapping(value="info",method=RequestMethod.GET)
	@ResponseBody
	public void info(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/getFeatureInfo", request, response);
	}
	
	@RequestMapping(value="booking",method=RequestMethod.POST)
	@ResponseBody
	public void booking(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/booking", request, response);
	}
	
	@RequestMapping(value="booking/list",method=RequestMethod.GET)
	@ResponseBody
	public void bookingList(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/booking_list", request, response);
	}
	
	@RequestMapping(value="booking/info",method=RequestMethod.GET)
	@ResponseBody
	public void bookingInfo(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/booking_info", request, response);
	}
	
	@RequestMapping(value="booking/cancel",method=RequestMethod.POST)
	@ResponseBody
	public void bookingCancel(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/booking_cancel", request, response);
	}
	
	@RequestMapping(value="booked",method=RequestMethod.POST)
	@ResponseBody
	public void bookingBooked(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/booked", request, response);
	}
	
	@RequestMapping(value="booked/list",method=RequestMethod.GET)
	@ResponseBody
	public void bookedList(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/booked_list", request, response);
	}
	
	@RequestMapping(value="booked/protocol",method=RequestMethod.GET)
	@ResponseBody
	public void bookingProtocol(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/booked_protocol", request, response);
	}
	
	@RequestMapping(value="booked/order",method=RequestMethod.POST)
	@ResponseBody
	public void bookedOrder(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/booked_order", request, response);
	}
	
	@RequestMapping(value="booked/info",method=RequestMethod.GET)
	@ResponseBody
	public void bookedInfo(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/booked_info", request, response);
	}
	
	@RequestMapping(value="sign",method=RequestMethod.POST)
	@ResponseBody
	public void sign(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/sign", request, response);
	}
	
	@RequestMapping(value="sign/order",method=RequestMethod.POST)
	@ResponseBody
	public void signOrder(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/sign_order", request, response);
	}
	
	@RequestMapping(value="contract/continue",method=RequestMethod.POST)
	@ResponseBody
	public ResponseData contractContinue(String contract_id,String end_date,String msg) {
		ResponseData data = new ResponseData();
		if(StringUtils.isEmpty(contract_id) || StringUtils.isEmpty(end_date)) {
			data.setCode("101");
			return data;
		}
		
		data.setCode("200");
		data.setMsg("");
		return data;
	}
	
	@RequestMapping(value="return",method=RequestMethod.POST)
	@ResponseBody
	public ResponseData returnBack(String contract_id,String rent_date,String msg) {
		ResponseData data = new ResponseData();
		if(StringUtils.isEmpty(contract_id) || StringUtils.isEmpty(rent_date)) {
			data.setCode("101");
			return data;
		}
		
		data.setCode("200");
		data.setMsg("");
		return data;
	}
	
	@RequestMapping(value="bill",method=RequestMethod.GET)
	@ResponseBody
	public ResponseData bill(Integer p_n,Integer p_s) {
		ResponseData data = new ResponseData();
		if(p_n == null || p_s == null) {
			data.setCode("101");
			return data;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> mp = new HashMap<String,Object>();
		mp.put("bill_id", "1");
		mp.put("bill_month", "2015-09");
		mp.put("bill_amount", 2950);
		mp.put("bill_start", "2015-09-01");
		mp.put("bill_end", "2015-09-30");
		mp.put("rent_amount", 2400);
		mp.put("water_amount", 50);
		mp.put("electric_amount", 500);
		mp.put("electric_balance", 110);
		mp.put("bill_state", "1");
		list.add(mp);
		
		mp = new HashMap<String,Object>();
		mp.put("bill_id", "2");
		mp.put("bill_month", "2015-10");
		mp.put("bill_amount", 2950);
		mp.put("bill_start", "2015-10-01");
		mp.put("bill_end", "2015-10-31");
		mp.put("rent_amount", 2400);
		mp.put("water_amount", 50);
		mp.put("electric_amount", 500);
		mp.put("electric_balance", 110);
		mp.put("bill_state", "0");
		list.add(mp);
		
		map.put("bill", list);
		map.put("p_t", 2);
		
		data.setCode("200");
		data.setMsg("");
		data.setData(map);
		
		return data;
	}
	
	@RequestMapping(value="keeper/info",method=RequestMethod.GET)
	@ResponseBody
	public ResponseData keeperInfo() {
		ResponseData data = new ResponseData();
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("id", "1");
		map.put("name", "欧阳夏利");
		map.put("phone", "13800001111");
		map.put("photo", "http://218.80.0.218:12301/photo.jpg");
		map.put("level", "");
		map.put("agency", "上海房天下有限公司");
		
		data.setCode("200");
		data.setMsg("");
		data.setData(map);
		return data;
	}
	
	@RequestMapping(value="complain",method=RequestMethod.POST)
	@ResponseBody
	public ResponseData complain(String id,String desc) {
		ResponseData data = new ResponseData();
		
		if(id == null || desc == null) {
			data.setCode("101");
			return data;
		}
		
		data.setCode("200");
		data.setMsg("");
		return data;
	}
	
	@RequestMapping(value="contract/list",method=RequestMethod.GET)
	@ResponseBody
	public void contractList(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/contract_list", request, response);
	}
	
	@RequestMapping(value="contract/info",method=RequestMethod.GET)
	@ResponseBody
	public void contractInfo(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/contract_info", request, response);
	}
	
	@RequestMapping(value="notice",method=RequestMethod.GET)
	@ResponseBody
	public void notice(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/notice", request, response);
	}
	
	@RequestMapping(value="contract",method=RequestMethod.GET)
	@ResponseBody
	public void contract(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/contract", request, response);
	}
	
	@RequestMapping(value="pay_sign/booked",method=RequestMethod.POST)
	@ResponseBody
	public void paysignBooked(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/pay_sign_booked", request, response);
	}
	
	@RequestMapping(value="repair",method=RequestMethod.POST)
	@ResponseBody
	public ResponseData repair(@RequestParam(value = "pic1") MultipartFile pic1, 
			@RequestParam(value = "pic2") MultipartFile pic2,
			@RequestParam(value = "pic3") MultipartFile pic3,	
			HttpServletRequest request,
			HttpServletResponse response) {
		ResponseData data = new ResponseData();
		String realPathDir =request.getSession().getServletContext().getRealPath(pic_url);
		try {
			//保存图片并传给后台附件地址
			StringBuilder attachPath = new StringBuilder();			
			if(pic1!=null){
				attachPath.append(saveFile(realPathDir, pic1));
			}
			if(pic2!=null){
				attachPath.append("|");
				attachPath.append(saveFile(realPathDir, pic2));
			}
			if(pic3!=null){
				attachPath.append("|");
				attachPath.append(saveFile(realPathDir, pic3));
			}
			request.setAttribute("param_attach_path", attachPath.toString());
			
			HttpClientUtil.doPost(getRmsUrl(), "house/repair", request, response);

		} catch (Exception e) {
			log.error("上传图片失败.", e);
			
			data.setCode("102");
			data.setMsg("上传图片失败");
			return data;
		}	
		
		data.setCode("200");
		data.setMsg("上传成功");
		
		return data;
	}
	
	
}
