package com.rjkx.sk.itf.common.web;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import com.rjkx.sk.itf.common.po.AppTokenBean;
import com.rjkx.sk.itf.common.util.CommonItfUtils;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.utils.FredaUtils;
import com.rjkx.sk.system.utils.WebUtil;
import com.rjkx.sk.system.web.base.BaseController;


public class AppBaseController extends BaseController {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Dto getParamsAsDto(HttpServletRequest request) {
		Dto pDto = WebUtil.getPraramsAsDto(request);
		
//		Iterator it = pDto.keySet().iterator();
//		while (it.hasNext()) { String key = (String)it.next();
//			String value = pDto.getAsString(key);
//			try {
//				pDto.put(key, new String(value.getBytes("ISO-8859-1"),"UTF-8"));
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//		}
		
		if (FredaUtils.isEmpty(pDto) || FredaUtils.isEmpty(pDto.getAsString("appToken")) || FredaUtils.isEmpty(pDto.getAsString("imeiStr"))) {
			return null;
		} else {
			AppTokenBean tokenBean=CommonItfUtils.getEhcache(pDto.getAsString("imeiStr"));
			if(FredaUtils.isEmpty(tokenBean))
				return null;
			pDto.put("clientType", tokenBean.getType());
			return pDto;
		}
	}

}
