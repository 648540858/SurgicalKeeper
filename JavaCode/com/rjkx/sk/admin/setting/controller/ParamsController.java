package com.rjkx.sk.admin.setting.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.admin.core.service.SystemInitServiceItf;
import com.rjkx.sk.admin.core.web.AdminBaseController;
import com.rjkx.sk.admin.setting.service.ParamsServiceItf;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.SpringBeanLoader;
import com.rjkx.sk.system.utils.SystemCons;

/**
 * 
 * @author Rally
 *
 */
@Controller
@RequestMapping(value="/params")
public class ParamsController extends AdminBaseController
{
	
	private ParamsServiceItf paramsService;
	
	/**
	 * LIST
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月21日 下午3:38:12
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/listParams")
	public String listParams(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = super.getFredaReader().queryForPage("Params.listParams", pDto);
		
		Integer count = (Integer)super.getFredaReader().queryForObject("Params.listParamsCount", pDto);
		
		super.write(JsonHelper.encodeList2PageJson(data, count), response);
		
		return null;
	}
	/**
	 * ADD
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月21日 下午3:38:25
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/addParams")
	public String addParams(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		paramsService.addParams(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * EDIT
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月21日 下午3:38:33
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/editParams")
	public String editParams(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		paramsService.editParams(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * DELETE
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月21日 下午3:38:42
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/delParams")
	public String delParams(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		paramsService.delParams(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * 内存同步
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年9月21日 下午3:38:54
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/synMoney")
	public String synMoney(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		SystemInitServiceItf initService = (SystemInitServiceItf)SpringBeanLoader.getSpringBean("systemInitServiceImpl");
		List<?> codeList = initService.queryAllCode();
		List<?> paramsList = initService.queryAllParams();
		request.getSession().getServletContext().setAttribute(SystemCons.APPLICATION_SYSTEM_CODE_VAR, JsonHelper.encodeObject2Json(codeList));
		request.getSession().getServletContext().setAttribute(SystemCons.APPLICATION_SYSTEM_PARAMS_VAR, JsonHelper.encodeObject2Json(paramsList));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	
	public ParamsServiceItf getParamsService() 
	{
		return paramsService;
	}
	
	@Resource(name="paramsServiceImpl")
	public void setParamsService(ParamsServiceItf paramsService) 
	{
		this.paramsService = paramsService;
	}
}
