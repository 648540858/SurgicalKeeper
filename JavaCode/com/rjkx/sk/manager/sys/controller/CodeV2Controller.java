package com.rjkx.sk.manager.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.manager.base.ManagerBaseController;
import com.rjkx.sk.manager.sys.service.CodeServiceItf;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.SystemCons;

/**
 * CODE
 * 
 * @author LiChun
 *
 */
@Controller
@RequestMapping(value = "/codev2")
public class CodeV2Controller extends ManagerBaseController {
	@Autowired
	@Qualifier("codeV2ServiceImpl")
	private CodeServiceItf codeService;

	/**
	 * LIST
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listCode")
	public String listCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		List<?> data = super.getFredaReader().queryForList("CodeV2.listCode", pDto);

		super.write("{\"ROOT\":"+JsonHelper.encodeObject2Json(data)+"}", response);

		return null;
	}
	
	/***
	 * GET
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年2月12日 上午9:19:15
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value = "/getCode")
	public String getCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		Dto data = (Dto)super.getFredaReader().queryForObject("CodeV2.getCode", pDto);

		super.write(JsonHelper.encodeObject2Json(data), response);

		return null;
	}

	/**
	 * ADD
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addCode")
	public String addCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		codeService.addCode(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * UPDATE
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editCode")
	public String editCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		codeService.editCode(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * DELETE
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delCode")
	public String delCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		codeService.editCode(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * 同步内存
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/syncMemory")
	public String syncMemory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request.getSession().getServletContext().setAttribute(SystemCons.APPLICATION_SYSTEM_CODE_VAR, codeService.listAllCode());
		request.getServletContext().setAttribute(SystemCons.APPLICATION_SYSTEM_CODE_VAR, JsonHelper.encodeObject2Json(codeService.listAllCode()));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

}
