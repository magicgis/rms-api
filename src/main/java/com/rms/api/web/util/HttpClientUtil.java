package com.rms.api.web.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {
	private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
	
//	@Value("#{configProperties['rms.url']}")
//	private static String rms_url;
//	
//	public static String doPost(String url,String params) {
//		String json = "";
//		try {
//			DefaultHttpClient httpClient = new DefaultHttpClient();
//			HttpPost post = new HttpPost(rms_url+url);
//			log.debug(rms_url+url);
//			StringEntity entity = new StringEntity(params.toString(),"utf-8");
//			entity.setContentEncoding("UTF-8");    
//			entity.setContentType("application/json");
//			post.setEntity(entity);
//			HttpResponse result = httpClient.execute(post);
//			json = EntityUtils.toString(result.getEntity(),"UTF-8");
//		} catch (Exception e) {
//			log.error("call dopost error:",e);
//		}
//		return json;
//	}
	
	public static String doPost(String rmsUrl, String url,HttpServletRequest request) {
		String json = "";
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost(rmsUrl+url);
			log.debug(rmsUrl+url);
			log.debug("params:" + request.getParameterMap());
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		    Enumeration paramNames = request.getParameterNames();
		    while (paramNames.hasMoreElements()) {
		      String paramName = (String) paramNames.nextElement();
		      String[] paramValues = request.getParameterValues(paramName);
		      if (paramValues.length == 1) {
		        String paramValue = paramValues[0];
		        if (paramValue.length() != 0) {
		        	formparams.add(new BasicNameValuePair(paramName,paramValue));
		        }
		      }
		    };
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");;
			post.setEntity(entity);
			HttpResponse result = httpClient.execute(post);
			json = EntityUtils.toString(result.getEntity(),"UTF-8");
			
		} catch (Exception e) {
			log.error("call dopost error:",e);
		}
		return json;
	}
	
	public static void main(String[] args) {
		String json = "";
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost("http://127.0.0.1:8080/rms/"+"system/check_code");
			// 创建参数队列
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			  formparams.add(new BasicNameValuePair("mobile","15618820709"));
			  formparams.add(new BasicNameValuePair("password", "admin"));
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
			//StringEntity entity = new StringEntity("abc".toString(),"utf-8");
//			entity.setContentEncoding("UTF-8");    
//			entity.setContentType("application/x-www-form-urlencoded");
			post.setEntity(entity);
			System.out.println(post.getURI());
			HttpResponse result = httpClient.execute(post);
			System.out.println(result); 
			json = EntityUtils.toString(result.getEntity(),"UTF-8");
			log.debug(json);
		} catch (Exception e) {
			System.out.println(e);
			log.error("call dopost error:",e);
		}
		//return json;
		
	}
}
