package com.rjkx.sk.itf.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pingplusplus.Pingpp;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.RedEnvelope;
import com.pingplusplus.model.Refund;
import com.pingplusplus.model.Transfer;
import com.rjkx.sk.itf.common.web.WeiXinItfBaseController;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.utils.FredaUtils;
import com.rjkx.sk.system.utils.SystemCons;
import com.rjkx.sk.system.utils.WebUtil;

/***
 * PING++相关接口
 * 
 * @ClassName: PingppController
 * @Description:
 * @author yiyuan-LiChun
 * @date 2016年2月29日 下午1:44:28
 * @version V2.0
 */
@Controller
@RequestMapping(value = "/pingpp")
public class PingppController extends WeiXinItfBaseController {

	static {
		Pingpp.apiKey = appProperties.getValue("pay.pingpp.apiKey");
	}

	/**
	 * 获取CHARGE
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年11月10日 上午9:48:09
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCharge")
	public String getCharge(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("order_no", pDto.getAsString("order_no"));
		chargeParams.put("amount", ((int) (Double.parseDouble(pDto.getAsString("amount")) * 100)));
		chargeParams.put("channel", pDto.getAsString("channel"));
		chargeParams.put("currency", "cny");
		chargeParams.put("client_ip", request.getRemoteAddr());
		chargeParams.put("subject", pDto.getAsString("subject"));
		chargeParams.put("body", pDto.getAsString("body"));

		if (FredaUtils.isNotEmpty(pDto.getAsString("open_id"))) {
			Map<String, String> extra = new HashMap<String, String>();
			extra.put("open_id", pDto.getAsString("open_id"));
			chargeParams.put("extra", extra);
		}

		Map<String, String> app = new HashMap<String, String>();
		app.put("id", appProperties.getValue("pay.pingpp.appid"));
		chargeParams.put("app", app);

		Charge charge = Charge.create(chargeParams);

		super.write(charge.toString(), response);

		return null;
	}

	/**
	 * 跨域输出CHARGE
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年12月14日 下午2:18:53
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getChargeV2")
	public String getChargeV2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = WebUtil.getPraramsAsDto(request);

		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("order_no", pDto.getAsString("order_no"));
		chargeParams.put("amount", ((int) (Double.parseDouble(pDto.getAsString("amount")) * 100)));
		chargeParams.put("channel", pDto.getAsString("channel"));
		chargeParams.put("currency", "cny");
		chargeParams.put("client_ip", request.getRemoteAddr());
		chargeParams.put("subject", pDto.getAsString("subject"));
		chargeParams.put("body", pDto.getAsString("body"));

		if (FredaUtils.isNotEmpty(pDto.getAsString("open_id"))) {
			Map<String, String> extra = new HashMap<String, String>();
			extra.put("open_id", pDto.getAsString("open_id"));
			chargeParams.put("extra", extra);
		}

		Map<String, String> app = new HashMap<String, String>();
		app.put("id", appProperties.getValue("pay.pingpp.appid"));
		chargeParams.put("app", app);

		Charge charge = Charge.create(chargeParams);

		super.writeForZepto(charge.toString(), request, response);

		return null;
	}

	/**
	 * 退款请求
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年11月10日 上午11:13:02
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/refundOrder")
	public String refundOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = WebUtil.getPraramsAsDto(request);

		Charge ch = Charge.retrieve(pDto.getAsString("ch_id"));

		Map<String, Object> refundMap = new HashMap<String, Object>();
		refundMap.put("amount", ((int) (Double.parseDouble(pDto.getAsString("amount")) * 100)));
		refundMap.put("description", pDto.getAsString("description"));

		ch.getRefunds().create(refundMap);

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * 退款请求
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年11月10日 上午11:13:02
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/refundOrderV2")
	public String refundOrderV2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = WebUtil.getPraramsAsDto(request);

		Charge ch = Charge.retrieve(pDto.getAsString("ch_id"));
		Map<String, Object> refundMap = new HashMap<String, Object>();
		refundMap.put("amount", ((int) (Double.parseDouble(pDto.getAsString("amount")) * 100)));
		refundMap.put("description", pDto.getAsString("description"));

		Refund re = ch.getRefunds().create(refundMap);
		re.getSucceed();

		super.setTipsForZepto(true, "success", request, response);

		return null;
	}

	/**
	 * 提现
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年11月11日 上午9:31:59
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/withdrawCash")
	public String withdrawCash(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		Map<String, Object> transferMap = new HashMap<String, Object>();

		transferMap.put("amount", ((int) (Double.parseDouble(pDto.getAsString("amount")) * 100)));
		transferMap.put("currency", "cny");
		transferMap.put("type", "b2c");
		transferMap.put("order_no", pDto.getAsString("order_no"));
		transferMap.put("channel", "wx_pub");
		transferMap.put("recipient", pDto.getAsString("open_id"));
		transferMap.put("description", pDto.getAsString("description"));

		Map<String, String> app = new HashMap<String, String>();
		app.put("id", appProperties.getValue("pay.pingpp.appid"));
		transferMap.put("app", app);

		Transfer.create(transferMap);

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * 跨域请求.提现..
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年11月11日 上午9:31:59
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/withdrawCashV2")
	public String withdrawCashV2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = WebUtil.getPraramsAsDto(request);

		Map<String, Object> transferMap = new HashMap<String, Object>();

		transferMap.put("amount", ((int) (Double.parseDouble(pDto.getAsString("amount")) * 100)));
		transferMap.put("currency", "cny");
		transferMap.put("type", "b2c");
		transferMap.put("order_no", pDto.getAsString("order_no"));
		transferMap.put("channel", "wx_pub");
		transferMap.put("recipient", pDto.getAsString("open_id"));
		transferMap.put("description", pDto.getAsString("description"));

		Map<String, String> app = new HashMap<String, String>();
		app.put("id", appProperties.getValue("pay.pingpp.appid"));
		transferMap.put("app", app);

		Transfer transfer = Transfer.create(transferMap);

		super.setTipsForZepto(true, transfer.getStatus(), request, response);

		return null;
	}

	/***
	 * 发送红包
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年2月25日 下午8:47:22
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/redSendOrder")
	public String redSendOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = WebUtil.getPraramsAsDto(request);

		Map<String, Object> redenvelope = new HashMap<String, Object>();
		redenvelope.put("amount", ((int) (Double.parseDouble(pDto.getAsString("amount")) * 100)));
		redenvelope.put("currency", "cny");
		redenvelope.put("subject", "关注送礼包");
		redenvelope.put("body", "万事如意");
		redenvelope.put("order_no", System.nanoTime() + "");
		redenvelope.put("channel", "wx_pub");
		redenvelope.put("recipient", pDto.getAsString("open_id"));
		redenvelope.put("description", pDto.getAsString("description"));
		Map<String, String> app = new HashMap<String, String>();
		app.put("id", appProperties.getValue("pay.pingpp.appid"));
		redenvelope.put("app", app);
		Map<String, String> extra = new HashMap<String, String>();
		extra.put("nick_name", "瑞吉康星");
		extra.put("send_name", "医愿");
		extra.put("logo", "http://www.yiyuan591.com/favicon.ico");

		redenvelope.put("extra", extra);
		RedEnvelope red = RedEnvelope.create(redenvelope);

		super.write(red.toString(), response);

		return null;
	}

	/***
	 * 企业转帐给微信用户
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年2月25日 下午9:22:28
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/transfer")
	public String transfer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = WebUtil.getPraramsAsDto(request);

		Map<String, Object> transfer = new HashMap<String, Object>();
		transfer.put("amount", ((int) (Double.parseDouble(pDto.getAsString("amount")) * 100)));
		transfer.put("currency", "cny");
		transfer.put("type", "b2c");
		transfer.put("order_no", System.nanoTime() + "");
		transfer.put("channel", "wx_pub");
		transfer.put("recipient", pDto.getAsString("open_id"));
		transfer.put("description", "转账...");
		Map<String, String> app = new HashMap<String, String>();
		app.put("id", appProperties.getValue("pay.pingpp.appid"));
		transfer.put("app", app);
		Transfer transferResult = Transfer.create(transfer);

		super.write(transferResult.toString(), response);

		return null;
	}
}
