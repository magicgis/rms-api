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
	public void message(HttpServletRequest request,HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "self/message", request, response);
	}

	@RequestMapping(value = "info", method = RequestMethod.GET)
	@ResponseBody
	public void info(HttpServletRequest request,HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "self/info", request, response);
	}

	@RequestMapping(value = "info/change", method = RequestMethod.POST)
	@ResponseBody
	public void infoChange(HttpServletRequest request,HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "self/info/change", request, response);
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
			FileOutputStream out = new FileOutputStream(path+"\\"  + newFileName);
			// 写入文件
			out.write(filedata.getBytes());			
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
