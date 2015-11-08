package com.rms.api.web.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;

import com.rms.api.web.entity.HeaderData;

public class BaseController {
	
	@Value("#{configProperties['rms.url']}")
	private  String rms_url;
	
	public HeaderData getHeaderData(HttpServletRequest request,HttpServletResponse response) {
		HeaderData headerData = new HeaderData();
		headerData.setToken(request.getHeader("token"));
		return headerData;
	}
	
	public String getRmsUrl(){
		return rms_url;
	}
}
