package com.rjkx.sk.itf.common.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.rjkx.sk.itf.common.service.AppAuthTokenServiceItf;
import com.rjkx.sk.itf.common.util.CommonItfUtils;
import com.rjkx.sk.itf.common.web.WeiXinItfBaseServiceImpl;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.utils.FredaUtils;
import com.rjkx.sk.system.utils.WebUtil;

/**
 * 
 * @ClassName: AppAuthTokenServiceImpl
 * @Description:
 * @author yiyuan-Rally
 * @date 2015年11月24日 下午3:44:18
 * @version V1.0
 */
@Service(value = "appAuthTokenServiceImpl")
public class AppAuthTokenServiceImpl extends WeiXinItfBaseServiceImpl implements AppAuthTokenServiceItf {

	@SuppressWarnings("unchecked")
	@Override
	public Dto getToken(HttpServletRequest request) {
		Dto pDto = WebUtil.getPraramsAsDto(request);
		
		String appId = pDto.getAsString("appId"), appPwd = pDto.getAsString("appPwd"), imeiStr = pDto.getAsString("imeiStr");

		if (FredaUtils.isEmpty(appId) || !appId.equals(appProperties.getValue("auth.app.id"))) {// 判断APPID准确性
			this.setErrorMsg(pDto, "appId is Error!");
			return pDto;
		}
		if (FredaUtils.isEmpty(appPwd) || !appPwd.equals(appProperties.getValue("auth.app.pwd"))) {// 判断APPPWD准确性
			this.setErrorMsg(pDto, "appPwd is Error!");
			return pDto;
		}
		if (FredaUtils.isEmpty(imeiStr)) {// 判断IMEIStr是否存在
			this.setErrorMsg(pDto, "imeiStr is Error!");
			return pDto;
		}
		pDto.clear();

		pDto.put("timestamp", FredaUtils.getCurrentTime("yyyyMMddHHmmss"));
		pDto.put("app_token", CommonItfUtils.getToken(imeiStr, pDto.getAsString("timestamp")));
		pDto.put("imeiStr", imeiStr);
		
		CommonItfUtils.setEhcache(pDto, request);

		pDto.put("resultcode", 0);
		pDto.remove("timestamp");
		pDto.remove("imeiStr");
		return pDto;
	}

	/**
	 * SET ERROR
	 * 
	 * @author yiyuan-Rally
	 * @date 2015年11月24日 下午5:15:47
	 * @param pDto
	 * @param errorMsg
	 */
	@SuppressWarnings("unchecked")
	private void setErrorMsg(Dto pDto, String errorMsg) {
		pDto.clear();
		pDto.put("code", -4);
		pDto.setMsg(errorMsg);
	}

}
