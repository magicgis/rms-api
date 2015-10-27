package com.rms.api.web;

import java.security.MessageDigest;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("sms")
public class SmsController {
	Logger log = LoggerFactory.getLogger(SmsController.class);
			
	@Value("#{configProperties['sms.url']}")
	private String sms_url;
	@Value("#{configProperties['sms.account']}")
	private String account;
	@Value("#{configProperties['sms.password']}")
	private String password;
	
	@RequestMapping(value="send")
	@ResponseBody
	public String test() {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost(this.sms_url);
			JSONObject jsonParam = getJsonParam();
			StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");
			entity.setContentEncoding("UTF-8");    
            entity.setContentType("application/json");
			post.setEntity(entity);
			HttpResponse result = httpClient.execute(post);
			String resData = EntityUtils.toString(result.getEntity(),"UTF-8");
			log.info("resData:"+resData);
		} catch (Exception e) {
			log.error("send sms error:",e);
		}
		return "test";
	}
	
	protected JSONObject getJsonParam() throws Exception {
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("account", this.account);
		jsonParam.put("password", md5(this.password));
		jsonParam.put("msgid", UUID.randomUUID().toString().replaceAll("-", ""));
		jsonParam.put("phones", "13817599025,13918547182,13621952122");
		jsonParam.put("content", "唐巢短信测试");
		jsonParam.put("sign", "【唐巢人才公寓】");
		return jsonParam;
	}
	
	private String md5(String password) throws Exception {
		String result = "";
		MessageDigest md = MessageDigest.getInstance("MD5");
        byte bytes[] = md.digest(password.getBytes());
		for (int i = 0; i < bytes.length; i++) {
            String str = Integer.toHexString(bytes[i] & 0xFF);
            if (str.length() == 1) {
                str += "F";
            }
            result += str;
        }
		return result;
	}
}
