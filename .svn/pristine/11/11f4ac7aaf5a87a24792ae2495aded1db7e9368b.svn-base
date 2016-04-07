package com.rjkx.sk.itf.common.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rjkx.sk.itf.weixin.utils.SignUtil;
import com.rjkx.sk.itf.weixin.utils.WeiXinCons;
import com.rjkx.sk.itf.weixin.utils.WeiXinTokenThread;
import com.rjkx.sk.itf.weixin.utils.WeiXinUtils;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.datastructure.impl.BaseDto;
import com.rjkx.sk.system.utils.WebUtil;
import com.rjkx.sk.system.web.base.BaseController;

/**
 * 
 * @author rally
 *
 */
public class WeiXinItfBaseController extends BaseController {
	private static final String ZEPTO_AJAX_CALLBACK = "callback";

	/**
	 * 将所有前台参数封装成DTO
	 * 
	 * @param request
	 * @return
	 */
	protected Dto getParamsAsDto(HttpServletRequest request) {
		String userAgent=request.getHeader("User-Agent");
		if(!userAgent.contains("MicroMessenger")){
			return null;
		}
		return WebUtil.getPraramsAsDto(request);
	}

	/**
	 * 获取微信TOKEN
	 * 
	 * @return
	 */
	protected String getWeiXinTokenStr() {
		if (WeiXinTokenThread.ACCESS_TOKEN != null) {
			return WeiXinTokenThread.ACCESS_TOKEN;
		}
		return null;
	}

	/**
	 * 获取JS SDK的必须参数MAP..用于JS SDK初始化
	 * 
	 * @param url
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Dto getWeiXinJsApiMap(String url) {
		if (WeiXinTokenThread.JS_TICKET != null) {
			Dto rDto = new BaseDto();

			rDto.putAll(WeiXinUtils.getJsSignMap(WeiXinTokenThread.JS_TICKET, url));

			rDto.put("appId", WeiXinCons.WX_APP_ID);

			return rDto;
		}
		return null;

	}

	/**
	 * 获取JS_TICKET
	 * 
	 * @return
	 */
	protected String getWeiXinJsTicketStr() {
		if (WeiXinTokenThread.JS_TICKET != null) {
			return WeiXinTokenThread.JS_TICKET;
		}
		return null;
	}

	/**
	 * 判断微信接入是否合法
	 * 
	 * @param request
	 * @return
	 */
	protected boolean isLegal(HttpServletRequest request) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		return SignUtil.checkSignature(WeiXinCons.WX_TOKEN, signature, timestamp, nonce);
	}

	/**
	 * V2
	 * 
	 * @author yiyuan-Rally
	 * @date 2015年12月3日 下午4:05:23
	 * @param str
	 * @param response
	 * @throws IOException
	 */
	protected void writeForZepto(String str, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().write(request.getParameter(ZEPTO_AJAX_CALLBACK) + "({\"result\":" + str + "})");

		response.getWriter().flush();

		response.getWriter().close();
	}

	/**
	 * 设置成功Tips
	 * 
	 * @author yiyuan-Rally
	 * @date 2015年12月4日 下午1:40:22
	 * @param msg
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void setTipsForZepto(boolean falg, String msg, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Dto outDto = new BaseDto(falg, msg);

		this.writeForZepto(outDto.toJson(), request, response);
	}
}
