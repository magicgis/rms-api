package com.rms.api.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rms.api.web.base.BaseController;
import com.rms.api.web.entity.ResponseData;
import com.rms.api.web.util.HttpClientUtil;
import com.rms.api.web.util.JsonUtil;

@Controller
@RequestMapping("self")
public class SelfController extends BaseController {
	Logger log = LoggerFactory.getLogger(SelfController.class);

	@Value("#{configProperties['pic.url']}")
	private String pic_url;

	@RequestMapping(value = "pwd")
	@ResponseBody
	public String changePwd(HttpServletRequest request) {
		return HttpClientUtil.doPost(getRmsUrl(), "system/self/pwd", request);
	}

	@RequestMapping(value = "message", method = RequestMethod.GET)
	@ResponseBody
	public String message() {
		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, Object> map = new HashMap<String, Object>();

		List<Map> list = new ArrayList<Map>();
		Map<String, String> msgMap = new HashMap<String, String>();
		msgMap.put("id", "1");
		msgMap.put("time", "2015-10-01 10:30");
		msgMap.put("msg", "尊敬的唐先生，您的报修已通过维修师傅确认，师傅将准时上门为您维修。");
		list.add(msgMap);
		Map<String, String> msgMap2 = new HashMap<String, String>();
		msgMap2.put("id", "2");
		msgMap2.put("time", "2015-10-02 11:30");
		msgMap2.put("msg", "尊敬的唐先生，您的10月份房租账单已出，请在10月15日前缴纳租金。");
		list.add(msgMap2);
		Map<String, String> msgMap3 = new HashMap<String, String>();
		msgMap3.put("id", "3");
		msgMap3.put("time", "2015-10-05 11:00");
		msgMap3.put("msg", "尊敬的唐先生，您的水费余额已不足，请及时缴纳以避免断水情况的发生。");
		list.add(msgMap3);

		result.put("code", "200");
		result.put("msg", "");
		result.put("data", list);
		return JsonUtil.object2Json(result);
	}

	@RequestMapping(value = "info", method = RequestMethod.GET)
	@ResponseBody
	public String info() {
		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, String> infoMap = new HashMap<String, String>();
		infoMap.put("name", "唐先生");
		infoMap.put("id", "430525198701011234");
		infoMap.put("sex", "男");
		infoMap.put("birth", "1987-01-01");
		infoMap.put("age", "28");
		infoMap.put("profession", "程序员");
		infoMap.put("corp", "IBM");

		result.put("code", "200");
		result.put("msg", "");
		result.put("data", infoMap);
		return JsonUtil.object2Json(result);
	}

	@RequestMapping(value = "info/change", method = RequestMethod.POST)
	@ResponseBody
	public String infoChange(String name, String id, String sex, String birth, String age, String profession,
			String corp) {
		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, String> infoMap = new HashMap<String, String>();
		infoMap.put("name", name);
		infoMap.put("id", id);
		infoMap.put("sex", sex);
		infoMap.put("birth", birth);
		infoMap.put("age", age);
		infoMap.put("profession", profession);
		infoMap.put("corp", corp);

		result.put("code", "200");
		result.put("msg", "修改成功");
		result.put("data", infoMap);
		return JsonUtil.object2Json(result);
	}

	@RequestMapping(value = "ic", method = RequestMethod.POST)
	@ResponseBody
	public String uploadIc(@RequestParam(value = "ic") MultipartFile filedata, HttpServletRequest request) {

		Map<String, Object> result = new HashMap<String, Object>();

		if (StringUtils.isEmpty(filedata)) {
			ResponseData data = new ResponseData();
			data.setCode("101");
			return JsonUtil.object2Json(data);
		}
		// 保存相对路径到数据库 图片写入服务器
		// 获取图片的文件名
		String fileName = filedata.getOriginalFilename();
		// 获取图片的扩展名
		String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
		// 新的图片文件名 = 获取时间戳+"."图片扩展名
		String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
		String realPathDir =request.getSession().getServletContext().getRealPath(pic_url);
		try {
			// TODO : 保存图片URL到用户表

			saveFile(realPathDir, newFileName, filedata);

		} catch (Exception e) {
			log.error("上传图片失败.", e);
			result.put("code", "102");
			result.put("msg", "上传图片失败");
			return JsonUtil.object2Json(result);
		}

		result.put("code", "200");
		result.put("msg", "上传成功");
		result.put("data", request.getContextPath()+pic_url  + newFileName);
		return JsonUtil.object2Json(result);
	}

	private void saveFile(String path, String newFileName, MultipartFile filedata) {
		// 根据配置文件获取服务器图片存放路径

		// 构建文件目录
		File fileDir = new File(path);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}

		try {
			FileOutputStream out = new FileOutputStream(pic_url  + newFileName);
			// 写入文件
			out.write(filedata.getBytes());
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
