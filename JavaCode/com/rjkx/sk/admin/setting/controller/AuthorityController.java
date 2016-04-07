package com.rjkx.sk.admin.setting.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.admin.core.web.AdminBaseController;
import com.rjkx.sk.admin.setting.service.AuthorityServiceItf;
import com.rjkx.sk.system.utils.SystemCons;

/**
 * 
 * @author Rally
 *
 */
@Controller
@RequestMapping(value="/authority")
public class AuthorityController extends AdminBaseController 
{
	private AuthorityServiceItf authService;
	
	/**
	 * 为角色设置菜单
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月24日 上午10:16:38
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/setMenuForRole")
	public String setMenuForRole(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		authService.setMenuForRole(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * 为角色设置用户
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月24日 上午10:17:03
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/setUserForRole")
	public String setUserForRole(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		authService.setUserForRole(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * 为用户设置角色
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月24日 上午10:17:23
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/setRoleForUser")
	public String setRoleForUser(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		authService.setRoleForUser(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	
	
	public AuthorityServiceItf getAuthService() 
	{
		return authService;
	}
	@Resource(name="authorityServiceImpl")
	public void setAuthService(AuthorityServiceItf authService)
	{
		this.authService = authService;
	}
	
	
}
