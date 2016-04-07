package com.rjkx.sk.admin.cms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.admin.cms.service.SicknessAndAttrServiceItf;
import com.rjkx.sk.admin.core.web.AdminBaseController;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.SystemCons;
/**
 * 
  * @ClassName: SicknessSetAttrController
  * @Description:
  * @author yiyuan-Rally
  * @date 2015年10月13日 下午6:00:47 
  * @version V1.0
 */
@Controller
@RequestMapping(value="/sicknessSetAttr")
public class SicknessSetAttrController extends AdminBaseController
{
	
	private SicknessAndAttrServiceItf sicknessAndAttrService;
	
	/**
	 * LIST
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月13日 下午6:06:52
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/listAttrWithContentBySickness")
	public String listAttrWithContentBySickness(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = super.getFredaReader().queryForPage("SicknessSet.listSicknessSet", pDto);
		
		Integer count = (Integer)super.getFredaReader().queryForObject("SicknessSet.listSicknessSetCount", pDto);
		
		super.write(JsonHelper.encodeList2PageJson(data, count, SystemCons.DATE_TIME_FORMART), response);
		
		return null;
	}
	/**
	 * LOAD
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月13日 下午7:15:42
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/loadContent")
	public String loadContent(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		super.write(JsonHelper.encodeDto2FormLoadJson((Dto)super.getFredaReader().queryForObject("SicknessSet.loadContent", super.getParamsAsDto(request)), null), response);
		
		return null;
	}
	/**
	 * ADD
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月13日 下午6:06:57
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/addAttrWithContent")
	public String addAttrWithContent(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		sicknessAndAttrService.addSicknessAndAttr(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * EDIT
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月13日 下午6:07:06
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/editAttrContent")
	public String editAttrContent(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		sicknessAndAttrService.editSicknessAndAttr(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * DELETE
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月13日 下午6:07:14
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/delAttrContent")
	public String delAttrContent(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		sicknessAndAttrService.delSicknessAndAttr(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * 
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月13日 下午7:08:01
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/sicknessTreeInit")
	public String sicknessTreeInit(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		super.write(JsonHelper.encodeObject2Json(super.getFredaContext().getBasicDataService().sicknessTreeInit(super.getParamsAsDto(request))), response);
		
		return null;
	}
	/**
	 * 
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月13日 下午7:07:57
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/listAllAttrForCombox")
	public String listAllAttrForCombox(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		super.write(JsonHelper.encodeObject2Json(super.getFredaReader().queryForList("SicknessSet.listAllAttr")), response);
		
		return null;
	}

	public SicknessAndAttrServiceItf getSicknessAndAttrService() 
	{
		return sicknessAndAttrService;
	}
	@Resource(name="sicknessAndAttrServiceImpl")
	public void setSicknessAndAttrService(SicknessAndAttrServiceItf sicknessAndAttrService) 
	{
		this.sicknessAndAttrService = sicknessAndAttrService;
	}
	
	
}
