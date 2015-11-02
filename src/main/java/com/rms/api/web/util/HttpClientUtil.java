package com.rms.api.web.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class HttpClientUtil {
	private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
	
	@Value("#{configProperties['rms.url']}")
	private static String rms_url;
	
	public static String doPost(String url,String params) {
		String json = "";
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost(rms_url+url);
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
}
