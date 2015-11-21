package com.rms.api.web.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;

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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

import com.rms.api.web.entity.ResponseData;
import com.rms.api.web.util.HttpClientUtil;
import com.rms.api.web.util.JsonUtil;

public class AuthenticationFilter implements Filter{
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
		//httpResonse.setCharacterEncoding("utf-8");
		String token = httpRequest.getHeader("token");
		String url = httpRequest.getRequestURI();
		
		boolean authorized = true;

		Resource resource = new ClassPathResource("/application.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		//取需要验证TOKEN的功能
		String[] tokenApis =  props.getProperty("token.api").split(",");
		HashSet tokenAPISet = new HashSet();		
		tokenAPISet.addAll(Arrays.asList(tokenApis));
		//判读是否需要验证TOKEN
		boolean needToken = tokenAPISet.contains(httpRequest.getServletPath());
		
		if(needToken){
			log.debug("checking token");
			//验证TOKEN，通过返回手机号，否则为null
			if(token== null){
				PrintWriter writer = httpResonse.getWriter();
				ResponseData data = new ResponseData(); 
				data.setCode("400");
				data.setMsg("no token");
				writer.write(JsonUtil.object2Json(data));
				try {
					writer.flush();
					writer.close();
				} catch (Exception e) {
					log.error("",e);
				}
				authorized = false;
			}else{
				String rms_url = props.getProperty("rms.url");
				String phone = HttpClientUtil.doPost(rms_url, "system/checkToken", httpRequest);
				log.debug("fetched phone by token:" + phone);
				if(phone == null || phone.length()==0){
					authorized = false;
				}else{
					authorized = true;
					request.setAttribute("mobile", phone);	
				}
			}
		}
		
		if(authorized){
			//response.setContentType("application/x-www-form-urlencoded; charset=utf-8");
			chain.doFilter(request, response);
		}else{
			PrintWriter writer = httpResonse.getWriter();
			ResponseData data = new ResponseData(); 
			data.setCode("401");
			data.setMsg("请登陆");
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
