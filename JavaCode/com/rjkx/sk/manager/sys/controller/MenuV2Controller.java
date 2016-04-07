package com.rjkx.sk.manager.sys.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.manager.base.ManagerBaseController;
import com.rjkx.sk.manager.sys.service.MenuServiceItf;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.SystemCons;

@Controller
@RequestMapping(value = "/menuv2")
public class MenuV2Controller extends ManagerBaseController {
	@Autowired
	@Resource(name = "menuV2ServiceImpl")
	private MenuServiceItf menuService;

	/**
	 * LIST
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月22日 下午2:32:10
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listMenu")
	public String listMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		List<?> data = super.getFredaReader().queryForList("MenuV2.listMenu", pDto);

		super.write("{\"ROOT\":" + JsonHelper.encodeObject2Json(data) + "}", response);

		return null;
	}

	/**
	 * ADD
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月22日 下午2:32:15
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addMenu")
	public String addMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		menuService.addMenu(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * EDIT
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月22日 下午2:32:22
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editMenu")
	public String editMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		menuService.editMenu(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * DELETE
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月22日 下午2:32:31
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delMenu")
	public String delMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		menuService.delMenu(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * GET
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月22日 下午2:32:10
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMenu")
	public String getMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		Dto data = (Dto) super.getFredaReader().queryForObject("MenuV2.getMenu", pDto);

		super.write(JsonHelper.encodeObject2Json(data), response);

		return null;
	}

}
