package com.rms.api.web.base;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.rms.api.web.controller.SelfController;
import com.rms.api.web.entity.HeaderData;

public class BaseController {
	
	Logger log = LoggerFactory.getLogger(SelfController.class);
	
	@Value("#{configProperties['rms.url']}")
	private  String rms_url;
	@Value("#{configProperties['pic.url']}")
	private String pic_url;
	
	public HeaderData getHeaderData(HttpServletRequest request,HttpServletResponse response) {
		HeaderData headerData = new HeaderData();
		headerData.setToken(request.getHeader("token"));
		return headerData;
	}
	
	public String getRmsUrl(){
		return rms_url;
	}
	
	public String saveFile(String dir, MultipartFile filedata) {
		// 保存相对路径到数据库 图片写入服务器
		// 获取图片的文件名
		String fileName = filedata.getOriginalFilename();
		// 获取图片的扩展名
		String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
		// 新的图片文件名 = 获取时间戳+"."图片扩展名
		String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
		//String realPathDir =request.getSession().getServletContext().getRealPath(pic_url);
	
		
		log.debug("path:" + dir);
		log.debug("newFileName:" + newFileName);
		log.debug("filedata:" + filedata);
		
		// 根据配置文件获取服务器图片存放路径

		// 构建文件目录
		File fileDir = new File(dir);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}

		try {
			FileOutputStream out = new FileOutputStream(dir+"\\"  + newFileName);
			// 写入文件
			out.write(filedata.getBytes());			
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pic_url + newFileName;
	}
}
