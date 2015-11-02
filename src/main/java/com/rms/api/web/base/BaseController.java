package com.rms.api.web.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rms.api.web.entity.HeaderData;

public class BaseController {
	public HeaderData getHeaderData(HttpServletRequest request,HttpServletResponse response) {
		HeaderData headerData = new HeaderData();
		headerData.setToken(request.getHeader("token"));
		return headerData;
	}
}
