package com.rjkx.sk.admin.basic.controller;

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
 * 病情
  * @ClassName: SicknessController
  * @Description:
  * @author yiyuan-Rally
  * @date 2015年10月13日 下午5:53:53 
  * @version V1.0
 */
@Controller
@RequestMapping(value="/sickness")
public class SicknessController extends AdminBaseController
{
	
	private SicknessAndAttrServiceItf sicknessAndAttrService;
	
	/**
	 * LIST
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月13日 下午5:54:03
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/listSickness")
	public String listSickness(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = super.getFredaReader().queryForPage("Sickness.listSickness", pDto);
		
		Integer count = (Integer) super.getFredaReader().queryForObject("Sickness.listSicknessCount", pDto);
		
		super.write(JsonHelper.encodeList2PageJson(data, count), response);
		
		return null;
	}
	/**
	 * ADD
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月13日 下午5:54:08
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/addSickness")
	public String addSickness(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		sicknessAndAttrService.addSickness(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * EDIT
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月13日 下午5:54:15
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/editSickness")
	public String editSickness(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		sicknessAndAttrService.editSickness(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * DELETE
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月13日 下午5:54:25
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/delSickness")
	public String delSickness(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		sicknessAndAttrService.delSickness(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * LIST
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月13日 下午5:57:35
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/listSicknessAttr")
	public String listSicknessAttr(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = super.getFredaReader().queryForPage("Sickness.listAttr", pDto);
		
		Integer count = (Integer)super.getFredaReader().queryForObject("Sickness.listAttrCount", pDto);
		
		super.write(JsonHelper.encodeList2PageJson(data, count, SystemCons.DATE_TIME_FORMART), response);
		
		return null;
	}
	/**
	 * ADD
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月13日 下午5:57:41
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/addSicknessAttr")
	public String addSicknessAttr(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		sicknessAndAttrService.addAttr(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * EDIT
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月13日 下午5:57:48
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/editSicknessAttr")
	public String editSicknessAttr(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		sicknessAndAttrService.editAttr(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * DELETE
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月13日 下午5:57:54
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/delSicknessAttr")
	public String delSicknessAttr(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		sicknessAndAttrService.delAttr(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * LIST SICKNESS SUB
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月1日 下午3:45:48
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/listSicknessSub")
	public String listSicknessSub(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = super.getFredaReader().queryForPage("Sickness.listSicknessSub", pDto);
		
		Integer count = (Integer)super.getFredaReader().queryForObject("Sickness.listSicknessSubCount", pDto);
		
		super.write(JsonHelper.encodeList2PageJson(data, count), response);
		
		return null;
	}
	/**
	 * ADD SICKNESS SUB
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月1日 下午3:46:03
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/addSicknessSub")
	public String addSicknessSub(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		sicknessAndAttrService.addSicknessSub(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * EDIT SICKNESS SUB
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月1日 下午4:26:44
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/editSicknessSub")
	public String editSicknessSub(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		sicknessAndAttrService.editSicknessSub(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * DELETE SICKNESS SUB
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月1日 下午4:27:00
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/delSicknessSub")
	public String delSicknessSub(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		sicknessAndAttrService.delSicknessSub(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * 所有总分类 FOR COMBO
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月13日 下午8:16:15
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/listAllSicknessTypeForCombox")
	public String listAllSicknessTypeForCombox(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		super.write(JsonHelper.encodeObject2Json(super.getFredaReader().queryForList("Sickness.listAllSicknessTypeForCombox")), response);
		
		return null;
	}
	/**
	 * SICKNESS SUB TREE INIT
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月1日 下午3:40:28
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/sicknessSubTreeInit")
	public String sicknessSubTreeInit(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		super.write(JsonHelper.encodeObject2Json(super.getFredaContext().getBasicDataService().sicknessSubTreeInit(super.getParamsAsDto(request))), response);
		
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
