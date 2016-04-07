package com.rjkx.sk.manager.cms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.manager.base.ManagerBaseController;
import com.rjkx.sk.manager.cms.service.SickAttrContentService;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.SystemCons;

/***
 * 疾病属性内容管理
  * @ClassName: SickAttrContentController
  * @Description: 
  * @author yiyuan-LiChun
  * @date 2016年2月11日 上午9:30:40 
  * @version V2.0
 */
@Controller
@RequestMapping(value="/sickAttrContent")
public class SickAttrContentController extends ManagerBaseController {
	@Autowired
	@Resource(name="sickAttrContentServiceImpl")
	private SickAttrContentService sickAttrContentService;

	/**
	 * LIST 属性内容
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listSickContent")
	public String listSickContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		List<?> data = super.getFredaReader().queryForList("SickAttrContent.listSickAttrContent", pDto);

		super.write("{\"ROOT\":"+JsonHelper.encodeObject2Json(data, SystemCons.DATE_TIME_FORMART)+"}", response);

		return null;
	}

	/**
	 * 添加 属性内容
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addSickContent")
	public String addSickContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.sickAttrContentService.addSickAttrContent(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * EDIT 属性内容
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editSickContent")
	public String editSickContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.sickAttrContentService.editSickAttrContent(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * DELETE 属性内容
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delSickContent")
	public String delSickContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.sickAttrContentService.delSickAttrContent(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * 查询单条属性内容信息
	 * 
	 * @author lichun
	 * @date 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSickContent")
	public String getSickContent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Dto pDto = super.getParamsAsDto(request);

		Dto data = (Dto) super.getFredaReader().queryForObject("SickAttrContent.getSickAttrContent", pDto);

		super.write(JsonHelper.encodeObject2Json(data, SystemCons.DATE_TIME_FORMART), response);

		return null;
	}

}
