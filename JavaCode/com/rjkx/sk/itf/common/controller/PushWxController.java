package com.rjkx.sk.itf.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.itf.weixin.utils.WeiXinTokenThread;
import com.rjkx.sk.itf.weixin.utils.WeiXinUtils;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.web.base.BaseController;

/**
 * 微信消息推送
 * 
 * @ClassName: PingppController
 * @Description:
 * @author yiyuan-lichun
 * @date 2015年11月10日 上午9:39:42
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/pushwx")
public class PushWxController extends BaseController {
	/**
	 * 推送微信模板消息
	 * 
	 * @author yiyuan-lichun
	 * @date 2015年11月10日 上午9:48:09
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/push")
	public String sendPush(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (pDto.getAsString("content").length() < 10) {
			super.write("Error", response);
			return null;
		}
		WeiXinUtils.sendTemplateMsg(WeiXinTokenThread.ACCESS_TOKEN, pDto.getAsString("content").replaceAll("QQQQQ", "&"));

//		WeiXinHttpUtils.httpsRequest(WeiXinUtils.TEMPLATEMESSAGE_SEND_URL.replace("ACCESS_TOKEN", WeiXinTokenThread.ACCESS_TOKEN), pDto.getAsString("content").replaceAll("QQQQQ", "&"));

		super.write("OK", response);

		return null;
	}
	
	/***
	 * 推送微信客服消息
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年2月29日 下午1:49:26
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value = "/pushKfMsg")
	public String pushKfMsg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		if (pDto.getAsString("content").length() < 10) {
			super.write("Error", response);
			return null;
		}
		WeiXinUtils.sendKfMsg(WeiXinTokenThread.ACCESS_TOKEN, pDto.getAsString("content").replaceAll("QQQQQ", "&"));

		super.write("OK", response);

		return null;
	}
}
