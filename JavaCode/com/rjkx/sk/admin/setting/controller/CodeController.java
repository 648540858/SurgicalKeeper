package com.rjkx.sk.admin.setting.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.admin.core.web.AdminBaseController;
import com.rjkx.sk.admin.setting.service.CodeServiceItf;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.SystemCons;

/**
 * CODE
 * @author Rally
 *
 */
@Controller
@RequestMapping(value="/code")
public class CodeController extends AdminBaseController
{
	private CodeServiceItf codeService;
	
	/**
	 * LIST
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/listCode")
	public String listCode(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		pDto.put("init", 0);
		
		List<?> data = super.getFredaReader().queryForPage("Code.listCode", pDto);
		
		Integer count = (Integer)super.getFredaReader().queryForObject("Code.listCodeCount", pDto);
		
		super.write(JsonHelper.encodeList2PageJson(data, count), response);
		
		return null;
	}
	/**
	 * ADD
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addCode")
	public String addCode(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		codeService.addCode(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * UPDATE
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/editCode")
	public String editCode(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		codeService.editCode(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * DELETE
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delCode")
	public String delCode(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		codeService.editCode(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * 同步内存
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/synMoney")
	public String synMoney(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		request.getSession().getServletContext().setAttribute(SystemCons.APPLICATION_SYSTEM_CODE_VAR, JsonHelper.encodeObject2Json(codeService.listAllCode()));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	
	
	public CodeServiceItf getCodeService() 
	{
		return codeService;
	}
	@Resource(name="codeServiceImpl")
	public void setCodeService(CodeServiceItf codeService) 
	{
		this.codeService = codeService;
	}
	
	
}
