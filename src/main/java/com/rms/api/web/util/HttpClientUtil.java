package com.rms.api.web.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

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
	
	public static String doPost(String rmsUrl, String url,String params) {
		String json = "";
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost(rmsUrl+url);
			log.debug(rmsUrl+url);
			StringEntity entity = new StringEntity(params.toString(),"utf-8");
			entity.setContentEncoding("UTF-8");    
			entity.setContentType("application/json");
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
			HttpPost post = new HttpPost("http://127.0.0.1:8080/rms/"+"app/tAppCheckCode/generateCheckCode");
			StringEntity entity = new StringEntity("abc".toString(),"utf-8");
			entity.setContentEncoding("UTF-8");    
			entity.setContentType("application/json");
			post.setEntity(entity);
			System.out.println(post.getURI());
			HttpResponse result = httpClient.execute(post);
			System.out.println(result);
			json = EntityUtils.toString(result.getEntity(),"UTF-8");
		} catch (Exception e) {
			System.out.println(e);
			log.error("call dopost error:",e);
		}
		//return json;
	}
}
