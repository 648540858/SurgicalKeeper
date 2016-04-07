package com.rjkx.sk.admin.setting.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.admin.core.web.AdminBaseController;
import com.rjkx.sk.admin.setting.service.OrganizationServiceItf;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.SystemCons;

/**
 * 组织机构的增删改查
 * @author Rally
 *
 */
@Controller
@RequestMapping(value="/organization")
public class OrganizationController extends AdminBaseController
{
	OrganizationServiceItf organizationService;
	
	/**
	 * LIST DEPT
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月21日 下午1:44:51
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/listDept")
	public String listDept(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = super.getFredaReader().queryForPage("Organization.listDept", pDto);
		
		Integer count = (Integer) super.getFredaReader().queryForObject("Organization.listDeptCount", pDto);
		
		super.write(JsonHelper.encodeList2PageJson(data, count), response);
		
		return null;
	}
	/**
	 * LIST ROLE
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月21日 下午1:45:02
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/listRole")
	public String listRole(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = super.getFredaReader().queryForPage("Organization.listRole", pDto);
		
		Integer count = (Integer)super.getFredaReader().queryForObject("Organization.listRoleCount", pDto);
		
		super.write(JsonHelper.encodeList2PageJson(data, count,SystemCons.DATE_TIME_FORMART), response);
		
		return null;
	}
	/**
	 * LIST USER
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月21日 下午1:45:10
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/listUser")
	public String listUser(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = super.getFredaReader().queryForPage("Organization.listUser", pDto);
		
		Integer count = (Integer)super.getFredaReader().queryForObject("Organization.listUserCount", pDto);
		
		super.write(JsonHelper.encodeList2PageJson(data, count), response);
		
		return null;
	}
	/**
	 * ADD DEPT
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月21日 下午1:45:18
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/addDept")
	public String addDept(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		organizationService.addDept(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * ADD ROLE
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月21日 下午1:45:28
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/addRole")
	public String addRole(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		organizationService.addRole(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * ADD USER
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月21日 下午1:45:37
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/addUser")
	public String addUser(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		organizationService.addUser(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * EDIT DEPT
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月21日 下午1:45:46
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/editDept")
	public String editDept(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		organizationService.editDept(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * EDIT ROLE
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月21日 下午1:45:54
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/editRole")
	public String editRole(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		organizationService.editRole(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * EDIT USER
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月21日 下午1:46:01
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/editUser")
	public String editUser(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		organizationService.editUser(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * DELETE DEPT
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月21日 下午1:46:09
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/delDept")
	public String delDept(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		organizationService.delDept(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * DELETE ROLE
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月21日 下午1:46:24
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/delRole")
	public String delRole(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		organizationService.delRole(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * DELETE USER
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月21日 下午1:46:32
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/delUser")
	public String delUser(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		organizationService.delUser(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}

	
	
	public OrganizationServiceItf getOrganizationService() 
	{
		return organizationService;
	}
	@Resource(name="organizationServiceImpl")
	public void setOrganizationService(OrganizationServiceItf organizationService) 
	{
		this.organizationService = organizationService;
	}
	
	
}
