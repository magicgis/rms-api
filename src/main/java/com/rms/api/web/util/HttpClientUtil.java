package com.rms.api.web.util;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class HttpClientUtil {
  private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

  @SuppressWarnings("rawtypes")
  public static void doPost(String rmsUrl, String url, HttpServletRequest request, HttpServletResponse response) {
    String json = "";
    PrintWriter writer = null;
    try {
      DefaultHttpClient httpClient = new DefaultHttpClient();
      HttpPost post = new HttpPost(rmsUrl + url);
      log.debug(rmsUrl + url);
      String params = "";
      List<NameValuePair> formparams = new ArrayList<NameValuePair>();
      Enumeration paramNames = request.getParameterNames();
      while (paramNames.hasMoreElements()) {
        String paramName = (String) paramNames.nextElement();
        String[] paramValues = request.getParameterValues(paramName);
        params += paramName + ":" + request.getParameter(paramName) + ",";
        if (paramValues.length == 1) {
          log.info("======User-Agent:" + request.getHeader("User-Agent"));
          log.info("======contenttype:" + request.getContentType());
          String paramValue = paramValues[0];
          log.info("paramValue:" + paramValue);
          if (!StringUtils.isEmpty(paramValue)) {
            // android 非multipart 参数要转换 ios multipart 参数要转换
            boolean isMultipart = !StringUtils.isEmpty(request.getContentType()) && request.getContentType().toLowerCase().contains("multipart");
            boolean isAndroid = !StringUtils.isEmpty(request.getHeader("User-Agent")) && request.getHeader("User-Agent").toLowerCase().contains("android");
            if ((isAndroid && !isMultipart) || (!isAndroid && isMultipart)) {
              paramValue = new String(paramValue.getBytes("ISO-8859-1"), "UTF-8");
            }
          }
          if (isMessyCode(paramValue)) {
            paramValue = "";
          }
          if (paramValue.length() != 0) {
            formparams.add(new BasicNameValuePair(paramName, paramValue));
          }
        }
      } ;
      log.debug("params:[" + params + "]");
      Enumeration attributeNames = request.getAttributeNames();
      while (attributeNames.hasMoreElements()) {
        String attributeName = (String) attributeNames.nextElement();
        if (attributeName.startsWith("param_")) {
          String attributeValue = (String) request.getAttribute(attributeName);
          formparams.add(new BasicNameValuePair(attributeName.substring(6), attributeValue));
        }
      } ;
      if (request.getAttribute("mobile") != null) {
        formparams.add(new BasicNameValuePair("mobile", (String) request.getAttribute("mobile")));
      }
      UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");;
      post.setEntity(entity);
      post.setHeader("token", request.getHeader("token"));
      HttpResponse result = httpClient.execute(post);
      // log.debug("returned data:" + EntityUtils.toString(result.getEntity(), "GBK"));
      json = EntityUtils.toString(result.getEntity(), "UTF-8");
      log.debug("returned data:" + json);
      response.setContentType("application/json;charset=UTF-8");
      response.setCharacterEncoding("UTF-8");
      writer = response.getWriter();
      String jsonpcallback = request.getParameter("callback");
      if (null != jsonpcallback && !jsonpcallback.isEmpty()) {
        writer.write("jsonpCallback(" + json + ")");
      } else {
        writer.write(json);
      }
    } catch (Exception e) {
      log.error("call dopost error:", e);
    } finally {
      try {
        writer.flush();
        writer.close();
      } catch (Exception e) {
        log.error("close writer error:", e);
      }
    }
  }

  @SuppressWarnings("rawtypes")
  public static String getData(String rmsUrl, String url, HttpServletRequest request) {
    String json = "";
    try {
      DefaultHttpClient httpClient = new DefaultHttpClient();
      HttpPost post = new HttpPost(rmsUrl + url);
      log.debug(rmsUrl + url);
      log.debug("params:" + request.getParameterMap());
      List<NameValuePair> formparams = new ArrayList<NameValuePair>();
      Enumeration paramNames = request.getParameterNames();
      while (paramNames.hasMoreElements()) {
        String paramName = (String) paramNames.nextElement();
        String[] paramValues = request.getParameterValues(paramName);
        if (paramValues.length == 1) {
          String paramValue = paramValues[0];
          if (paramValue.length() != 0) {
            formparams.add(new BasicNameValuePair(paramName, paramValue));
          }
        }
      } ;
      if (request.getAttribute("mobile") != null) {
        formparams.add(new BasicNameValuePair("mobile", (String) request.getAttribute("mobile")));
      }
      UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");;
      post.setEntity(entity);
      post.setHeader("token", request.getHeader("token"));
      HttpResponse result = httpClient.execute(post);
      // log.debug("returned data:" + EntityUtils.toString(result.getEntity(), "GBK"));
      json = EntityUtils.toString(result.getEntity(), "UTF-8");
      log.debug("returned data:" + json);
    } catch (Exception e) {
      log.error("call dopost error:", e);
    }
    return json;
  }

  public static void main(String[] args) {
    String json = "";
    try {
      DefaultHttpClient httpClient = new DefaultHttpClient();
      HttpPost post = new HttpPost("http://127.0.0.1:8080/rms/" + "system/check_code");
      // 创建参数队列
      List<NameValuePair> formparams = new ArrayList<NameValuePair>();
      formparams.add(new BasicNameValuePair("mobile", "15618820709"));
      formparams.add(new BasicNameValuePair("password", "admin"));
      UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
      // StringEntity entity = new StringEntity("abc".toString(),"utf-8");
      // entity.setContentEncoding("UTF-8");
      // entity.setContentType("application/x-www-form-urlencoded");
      post.setEntity(entity);
      System.out.println(post.getURI());
      HttpResponse result = httpClient.execute(post);
      System.out.println(result);
      json = EntityUtils.toString(result.getEntity(), "UTF-8");
      log.debug(json);
    } catch (Exception e) {
      System.out.println(e);
      log.error("call dopost error:", e);
    }
    // return json;
  }

  public static boolean isMessyCode(String strName) {
    Pattern p = Pattern.compile("\\s*|t*|r*|n*");
    Matcher m = p.matcher(strName);
    String after = m.replaceAll("");
    String temp = after.replaceAll("\\p{P}", "");
    char[] ch = temp.trim().toCharArray();
    float chLength = ch.length;
    float count = 0;
    for (int i = 0; i < ch.length; i++) {
      char c = ch[i];
      if (!Character.isLetterOrDigit(c)) {
        if (!isChinese(c)) {
          count = count + 1;
        }
      }
    }
    float result = count / chLength;
    if (result > 0.4) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean isChinese(char c) {
    Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
    if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
        || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
      return true;
    }
    return false;
  }
}
