package com.rjkx.sk.itf.common.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.itf.common.service.AppAuthTokenServiceItf;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.web.base.BaseController;

/**
 * 权限控制
 * 
 * @ClassName: AppAuthTokenController
 * @Description:
 * @author yiyuan-Rally
 * @date 2015年11月23日 下午3:31:08
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/appAuth")
public class AppAuthTokenController extends BaseController {
	@Autowired
	@Resource(name = "appAuthTokenServiceImpl")
	private AppAuthTokenServiceItf appTokenService;

	/**
	 * GET APP TOKEN
	 * 
	 * @author yiyuan-Rally
	 * @date 2015年11月23日 下午3:33:27
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAppToken")
	public String getAppToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String outMsg=JsonHelper.encodeObject2Json(appTokenService.getToken(request));
		System.out.println(outMsg);
		super.write(outMsg, response);
		return null;
	}
}
