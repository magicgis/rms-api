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
	public ResponseData uploadIc(@RequestParam(value = "front") MultipartFile filedata,@RequestParam(value = "back") MultipartFile filedataBack, HttpServletRequest request,HttpServletResponse response) {
		ResponseData data = new ResponseData();

		if (StringUtils.isEmpty(filedata) ||StringUtils.isEmpty(filedataBack)) {
			data.setCode("101");
			return data;
		}	
		String realPathDir =request.getSession().getServletContext().getRealPath(pic_url);
		try {
			// TODO : 保存图片URL到用户表

			String file = saveFile(realPathDir, filedata);
			//HttpClientUtil.doPost(getRmsUrl(), "self/info/change", request, response);
			String attachPath1 = saveFile(realPathDir, filedata);
			log.debug(attachPath1);
			request.setAttribute("param_front", attachPath1);
			String attachPath2 = saveFile(realPathDir, filedataBack);
			log.debug(attachPath2);
			request.setAttribute("param_back", attachPath2);
			HttpClientUtil.doPost(getRmsUrl(), "self/ic", request, response);

		} catch (Exception e) {
			log.error("上传图片失败.", e);
			data.setCode("500");
			data.setMsg("上传图片失败");
			return data;
		}

		data.setCode("200");
		data.setMsg("上传成功");
		//data.setData(request.getContextPath()+pic_url  + newFileName);
		
		return data;
	}
	
	@RequestMapping(value = "avatar", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData uploadAvatar(@RequestParam(value = "avatar") MultipartFile filedata, HttpServletRequest request,HttpServletResponse response) {
		ResponseData data = new ResponseData();

		if (StringUtils.isEmpty(filedata) ) {
			data.setCode("101");
			data.setMsg("缺少图片参数");
			return data;
		}
		String realPathDir =request.getSession().getServletContext().getRealPath(pic_url);
		try {
			// TODO : 保存图片URL到用户表

			String attachPath = saveFile(realPathDir, filedata);
			log.debug(attachPath);
			request.setAttribute("param_attach_path", attachPath);
			HttpClientUtil.doPost(getRmsUrl(), "self/avatar", request, response);

		} catch (Exception e) {
			log.error("上传图片失败.", e);
			data.setCode("102");
			data.setMsg("上传图片失败");
			return data;
		}

		data.setCode("200");
		data.setMsg("上传成功");
		//data.setData(request.getContextPath()+pic_url  + newFileName);
		
		return data;
	}


}
