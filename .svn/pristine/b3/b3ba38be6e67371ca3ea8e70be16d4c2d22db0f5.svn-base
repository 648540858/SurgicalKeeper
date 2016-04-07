package com.rjkx.sk.manager.sys.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.manager.base.ManagerBaseController;
import com.rjkx.sk.manager.sys.service.AuthorityServiceItf;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.SystemCons;

/**
 * 
 * @author LiChun
 *
 */
@Controller
@RequestMapping(value = "/authorityv2")
public class AuthorityV2Controller extends ManagerBaseController {
	@Autowired
	@Resource(name = "authorityV2ServiceImpl")
	private AuthorityServiceItf authService;

	/**
	 * 为角色设置菜单
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月24日 上午10:16:38
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/setMenuForRole")
	public String setMenuForRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		authService.setMenuForRole(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * 为用户设置角色
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月24日 上午10:17:23
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/setUserForRole")
	public String setUserForRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		authService.setUserForRole(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/***
	 * 角色选择 for 用户
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年2月11日 上午10:51:43
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/roleTreeWithUserForCombo")
	public String roleTreeWithUserForCombo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.write(JsonHelper.encodeObject2Json(authService.roleTreeWithUserForCombo(super.getParamsAsDto(request))), response);

		return null;
	}
	
	/***
	 * 菜单选择 for 角色
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年2月11日 上午10:51:43
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/menuTreeWithUserForCombo")
	public String menuTreeWithUserForCombo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.write(JsonHelper.encodeObject2Json(authService.menuTreeWithUserForCombo(super.getParamsAsDto(request))), response);
		
		return null;
	}
}
