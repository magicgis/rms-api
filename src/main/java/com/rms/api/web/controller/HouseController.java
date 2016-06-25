package com.rms.api.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

@Controller
@RequestMapping("house")
public class HouseController extends BaseController {

	Logger log = LoggerFactory.getLogger(HouseController.class);

	@Value("#{configProperties['pic.url']}")
	private String pic_url;

	/**
	 * 精选房源列表
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public void list(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/findFeatureList", request, response);
	}

	@RequestMapping(value = "ad", method = RequestMethod.GET)
	@ResponseBody
	public void ad(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/ad", request, response);
	}

	/**
	 * 房屋详情
	 */
	@RequestMapping(value = "info", method = RequestMethod.GET)
	@ResponseBody
	public void info(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/getFeatureInfo", request, response);
	}

	@RequestMapping(value = "booking", method = RequestMethod.POST)
	@ResponseBody
	public void booking(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/booking", request, response);
	}

	@RequestMapping(value = "booking/list", method = RequestMethod.GET)
	@ResponseBody
	public void bookingList(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/booking_list", request, response);
	}

	@RequestMapping(value = "booking/info", method = RequestMethod.GET)
	@ResponseBody
	public void bookingInfo(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/booking_info", request, response);
	}

	@RequestMapping(value = "booking/cancel", method = RequestMethod.POST)
	@ResponseBody
	public void bookingCancel(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/booking_cancel", request, response);
	}

	@RequestMapping(value = "booked", method = RequestMethod.POST)
	@ResponseBody
	public void bookingBooked(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/booked", request, response);
	}

	@RequestMapping(value = "booked/list", method = RequestMethod.GET)
	@ResponseBody
	public void bookedList(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/booked_list", request, response);
	}

	@RequestMapping(value = "booked/protocol", method = RequestMethod.GET)
	@ResponseBody
	public void bookingProtocol(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/booked_protocol", request, response);
	}
	
	@RequestMapping(value = "booked/protocolById", method = RequestMethod.GET)
	@ResponseBody
	public void protocolById(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/booked_protocol_byid", request, response);
	}

	@RequestMapping(value = "booked/order", method = RequestMethod.POST)
	@ResponseBody
	public void bookedOrder(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/booked_order", request, response);
	}

	@RequestMapping(value = "booked/info", method = RequestMethod.GET)
	@ResponseBody
	public void bookedInfo(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/booked_info", request, response);
	}

	@RequestMapping(value = "booked/cancel", method = RequestMethod.POST)
	@ResponseBody
	public void bookedCancel(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/booked_cancel", request, response);
	}

	@RequestMapping(value = "sign", method = RequestMethod.POST)
	@ResponseBody
	public void sign(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/sign", request, response);
	}
	
	@RequestMapping(value = "signed/cancel", method = RequestMethod.POST)
	@ResponseBody
	public void signedCancel(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/signed_cancel", request, response);
	}

	@RequestMapping(value = "sign/order", method = RequestMethod.POST)
	@ResponseBody
	public void signOrder(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/sign_order", request, response);
	}

	@RequestMapping(value = "contract/continue", method = RequestMethod.POST)
	@ResponseBody
	public void contractContinue(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/contract_continue", request, response);
	}

	@RequestMapping(value = "return", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData returnBack(String contract_id, String rent_date, String msg) {
		ResponseData data = new ResponseData();
		if (StringUtils.isEmpty(contract_id) || StringUtils.isEmpty(rent_date)) {
			data.setCode("101");
			return data;
		}

		data.setCode("200");
		data.setMsg("");
		return data;
	}

	@RequestMapping(value = "bill", method = RequestMethod.GET)
	@ResponseBody
	public void bill(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/bill", request, response);
	}

	@RequestMapping(value = "keeper/info", method = RequestMethod.GET)
	@ResponseBody
	public void keeperInfo(HttpServletRequest request, HttpServletResponse response) {
        HttpClientUtil.doPost(getRmsUrl(), "house/keeper", request, response);
//		ResponseData data = new ResponseData();
//
//		Map<String, Object> map = new HashMap<String, Object>();
//
//		map.put("id", "1");
//		map.put("name", "欧阳夏利");
//		map.put("phone", "13800001111");
//		map.put("photo", "http://218.80.0.218:12301/photo.jpg");
//		map.put("level", "");
//		map.put("agency", "上海房天下有限公司");
//
//		data.setCode("200");
//		data.setMsg("");
//		data.setData(map);
//		return data;
	}

	@RequestMapping(value = "complain", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData complain(String id, String desc) {
		ResponseData data = new ResponseData();

		if (id == null || desc == null) {
			data.setCode("101");
			return data;
		}

		data.setCode("200");
		data.setMsg("");
		return data;
	}

	@RequestMapping(value = "contract/list", method = RequestMethod.GET)
	@ResponseBody
	public void contractList(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/contract_list", request, response);
	}

	@RequestMapping(value = "contract/info", method = RequestMethod.GET)
	@ResponseBody
	public void contractInfo(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/contract_info", request, response);
	}

	@RequestMapping(value = "notice", method = RequestMethod.GET)
	@ResponseBody
	public void notice(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/notice", request, response);
	}

	@RequestMapping(value = "contract", method = RequestMethod.GET)
	@ResponseBody
	public void contract(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/contract", request, response);
	}
	
	@RequestMapping(value = "contractById", method = RequestMethod.GET)
	@ResponseBody
	public void contractById(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/contractById", request, response);
	}

	@RequestMapping(value = "pay_sign/booked", method = RequestMethod.POST)
	@ResponseBody
	public void paysignBooked(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/pay_sign_booked", request, response);
	}
	
	@RequestMapping(value = "pay_sign/sign", method = RequestMethod.POST)
	@ResponseBody
	public void paysignSign(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/pay_sign_contract", request, response);
	}
	
	@RequestMapping(value = "recharge", method = RequestMethod.POST)
	@ResponseBody
	public void recharge(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/recharge", request, response);
	}
	
	@RequestMapping(value = "checkin_bill", method = RequestMethod.POST)
	@ResponseBody
	public void checkinBill(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/checkin_bill", request, response);
	}
	
	@RequestMapping(value = "pay_bill", method = RequestMethod.POST)
	@ResponseBody
	public void payBill(HttpServletRequest request, HttpServletResponse response) {
		HttpClientUtil.doPost(getRmsUrl(), "house/pay_bill", request, response);
	}

	@RequestMapping(value = "alipaynNotify", method = RequestMethod.POST)
	public void alipayNotify(HttpServletRequest request, HttpServletResponse response) {
		this.log.info("start alipay notify......");
		PrintWriter writer = null;
		try {
			HttpClientUtil.doPost(getRmsUrl(), "house/alipaynNotify", request, response);
			writer = response.getWriter();
			writer.write("success");
		} catch (IOException e) {
			log.error("alipay notify error:", e);
		} finally {
			writer.close();
		}
		this.log.info("end alipay notify......");
	}

	@RequestMapping(value = "repair", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData repair(@RequestParam(required = false, value = "pic1") MultipartFile pic1,
			@RequestParam(required = false, value = "pic2") MultipartFile pic2,
			@RequestParam(required = false, value = "pic3") MultipartFile pic3, HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("in repair");
		ResponseData data = new ResponseData();
		String realPathDir = request.getSession().getServletContext().getRealPath(pic_url);
		log.debug(realPathDir);
		try {
			// 保存图片并传给后台附件地址
			StringBuilder attachPath = new StringBuilder();
			if (pic1 != null) {
				attachPath.append(saveFile(realPathDir, pic1));
			}
			if (pic2 != null) {
				attachPath.append("|");
				attachPath.append(saveFile(realPathDir, pic2));
			}
			if (pic3 != null) {
				attachPath.append("|");
				attachPath.append(saveFile(realPathDir, pic3));
			}
			request.setAttribute("param_attach_path", attachPath.toString());
			log.debug("param_attach_path:" + attachPath.toString());
			HttpClientUtil.doPost(getRmsUrl(), "house/repair", request, response);

		} catch (Exception e) {
			log.error("上传图片失败.", e);

			data.setCode("102");
			data.setMsg("上传图片失败");
			return data;
		}

		data.setCode("200");
		data.setMsg("上传成功");

		return data;
	}

}
