package com.rms.api.web.controller;

import java.io.File;
import java.io.FileOutputStream;
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
@RequestMapping("self")
public class SelfController extends BaseController {
	Logger log = LoggerFactory.getLogger(SelfController.class);

	@Value("#{configProperties['pic.url']}")
	private String pic_url;

	@RequestMapping(value = "pwd")
	@ResponseBody
	public void changePwd(HttpServletRequest request,HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "system/self/pwd", request, response);
	}

	@RequestMapping(value = "message", method = RequestMethod.GET)
	@ResponseBody
	public ResponseData message() {
		ResponseData data = new ResponseData();
		
		List<Map> list = new ArrayList<Map>();
		Map<String, String> msgMap = new HashMap<String, String>();
		msgMap.put("id", "1");
		msgMap.put("time", "2015-10-01 10:30");
		msgMap.put("msg", "尊敬的唐先生，您的报修已通过维修师傅确认，师傅将准时上门为您维修。");
		list.add(msgMap);
		Map<String, String> msgMap2 = new HashMap<String, String>();
		msgMap2.put("id", "2");
		msgMap2.put("time", "2015-10-02 11:30");
		msgMap2.put("msg", "尊敬的唐先生，您的10月份房租账单已出，请在10月15日前缴纳租金。");
		list.add(msgMap2);
		Map<String, String> msgMap3 = new HashMap<String, String>();
		msgMap3.put("id", "3");
		msgMap3.put("time", "2015-10-05 11:00");
		msgMap3.put("msg", "尊敬的唐先生，您的水费余额已不足，请及时缴纳以避免断水情况的发生。");
		list.add(msgMap3);

		data.setCode("200");
		data.setMsg("");
		data.setData(list);
		
		return data;
	}

	@RequestMapping(value = "info", method = RequestMethod.GET)
	@ResponseBody
	public ResponseData info() {
		ResponseData data = new ResponseData();
		
		Map<String, String> infoMap = new HashMap<String, String>();
		infoMap.put("name", "唐先生");
		infoMap.put("id", "430525198701011234");
		infoMap.put("sex", "1");
		infoMap.put("birth", "1987-01-01");
		infoMap.put("age", "28");
		infoMap.put("profession", "程序员");
		infoMap.put("corp", "IBM");
		infoMap.put("avatar", "http://218.80.0.218:12301/avatar.jpg");
		infoMap.put("id_photo_front", "http://218.80.0.218:12301/id_photo_front.jpg");
		infoMap.put("id_photo_back", "http://218.80.0.218:12301/id_photo_back.jpg");

		data.setCode("200");
		data.setMsg("");
		data.setData(infoMap);
		
		return data;
	}

	@RequestMapping(value = "info/change", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData infoChange(String name, String id, String sex, String birth, String age, String profession,
			String corp) {
		ResponseData data = new ResponseData();
		
		Map<String, String> infoMap = new HashMap<String, String>();
		infoMap.put("name", name);
		infoMap.put("id", id);
		infoMap.put("sex", sex);
		infoMap.put("birth", birth);
		infoMap.put("age", age);
		infoMap.put("profession", profession);
		infoMap.put("corp", corp);

		data.setCode("200");
		data.setMsg("修改成功");
		data.setData(infoMap);
		
		return data;
	}

	@RequestMapping(value = "ic", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData uploadIc(@RequestParam(value = "front") MultipartFile filedata,@RequestParam(value = "back") MultipartFile filedataBack, HttpServletRequest request) {
		ResponseData data = new ResponseData();

		if (StringUtils.isEmpty(filedata) ||StringUtils.isEmpty(filedataBack)) {
			data.setCode("101");
			return data;
		}
		// 保存相对路径到数据库 图片写入服务器
		// 获取图片的文件名
		String fileName = filedata.getOriginalFilename();
		// 获取图片的扩展名
		String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
		// 新的图片文件名 = 获取时间戳+"."图片扩展名
		String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
		String realPathDir =request.getSession().getServletContext().getRealPath(pic_url);
		try {
			// TODO : 保存图片URL到用户表

			saveFile(realPathDir, newFileName, filedata);

		} catch (Exception e) {
			log.error("上传图片失败.", e);
			data.setCode("102");
			data.setMsg("上传图片失败");
			return data;
		}

		data.setCode("200");
		data.setMsg("上传成功");
		data.setData(request.getContextPath()+pic_url  + newFileName);
		
		return data;
	}
	
	@RequestMapping(value = "avatar", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData uploadAvatar(@RequestParam(value = "avatar") MultipartFile filedata, HttpServletRequest request) {
		ResponseData data = new ResponseData();

		if (StringUtils.isEmpty(filedata) ) {
			data.setCode("101");
			return data;
		}
		// 保存相对路径到数据库 图片写入服务器
		// 获取图片的文件名
		String fileName = filedata.getOriginalFilename();
		// 获取图片的扩展名
		String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
		// 新的图片文件名 = 获取时间戳+"."图片扩展名
		String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
		String realPathDir =request.getSession().getServletContext().getRealPath(pic_url);
		try {
			// TODO : 保存图片URL到用户表

			saveFile(realPathDir, newFileName, filedata);

		} catch (Exception e) {
			log.error("上传图片失败.", e);
			data.setCode("102");
			data.setMsg("上传图片失败");
			return data;
		}

		data.setCode("200");
		data.setMsg("上传成功");
		data.setData(request.getContextPath()+pic_url  + newFileName);
		
		return data;
	}

	private void saveFile(String path, String newFileName, MultipartFile filedata) {
		log.debug("path:" + path);
		log.debug("newFileName:" + newFileName);
		log.debug("filedata:" + filedata);
		
		// 根据配置文件获取服务器图片存放路径

		// 构建文件目录
		File fileDir = new File(path);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}

		try {
			FileOutputStream out = new FileOutputStream(pic_url  + newFileName);
			// 写入文件
			out.write(filedata.getBytes());			
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
