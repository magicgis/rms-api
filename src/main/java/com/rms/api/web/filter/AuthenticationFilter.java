package com.rms.api.web.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rms.api.web.entity.ResponseData;
import com.rms.api.web.util.JsonUtil;

public class AuthenticationFilter implements Filter {
	Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/*校验token*/
		log.info("start authentic......");
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResonse = (HttpServletResponse)response;
		httpResonse.setCharacterEncoding("utf-8");
		String token = httpRequest.getHeader("token");
		//TODO:
		if(false) {
			
			chain.doFilter(request, response);
		} else {
			PrintWriter writer = httpResonse.getWriter();
			ResponseData data = new ResponseData(); 
			data.setCode("401");
			data.setMsg("token无效");
			writer.write(JsonUtil.object2Json(data));
			try {
				writer.flush();
				writer.close();
			} catch (Exception e) {
				log.error("",e);
			}
		}
		log.info("end authentic......");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
