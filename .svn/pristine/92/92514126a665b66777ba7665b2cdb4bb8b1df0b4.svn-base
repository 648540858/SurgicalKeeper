package com.rjkx.sk.manager.sys.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.itf.weixin.utils.WeiXinTokenThread;
import com.rjkx.sk.itf.weixin.utils.WeiXinUtils;
import com.rjkx.sk.manager.base.ManagerBaseController;
import com.rjkx.sk.manager.index.vo.ManagerUserVo;
import com.rjkx.sk.manager.sys.service.ParamsServiceItf;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.SystemCons;

/**
 * 
 * @author LiChun
 *
 */
@Controller
@RequestMapping(value = "/paramsv2")
public class ParamsV2Controller extends ManagerBaseController {
	@Autowired
	@Resource(name = "paramsV2ServiceImpl")
	private ParamsServiceItf paramsService;

	/**
	 * LIST
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月21日 下午3:38:12
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listParams")
	public String listParams(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		List<?> data = super.getFredaReader().queryForList("ParamsV2.listParams", pDto);
		super.write("{\"ROOT\":" + JsonHelper.encodeObject2Json(data) + "}", response);
		return null;
	}
	
	/**
	 * GET
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月21日 下午3:38:12
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getParams")
	public String getParams(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		Dto data = (Dto)super.getFredaReader().queryForObject("ParamsV2.getParams", pDto);
		super.write(JsonHelper.encodeObject2Json(data), response);
		return null;
	}

	/**
	 * ADD
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月21日 下午3:38:25
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addParams")
	public String addParams(HttpServletRequest request, HttpServletResponse response) throws Exception {
		paramsService.addParams(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * EDIT
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月21日 下午3:38:33
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editParams")
	public String editParams(HttpServletRequest request, HttpServletResponse response) throws Exception {
		paramsService.editParams(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * DELETE
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月21日 下午3:38:42
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delParams")
	public String delParams(HttpServletRequest request, HttpServletResponse response) throws Exception {
		paramsService.delParams(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * 内存同步
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月21日 下午3:38:54
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/syncMemory")
	public String syncMemory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<?> paramsList = this.paramsService.listAllParams();

		request.getServletContext().setAttribute(SystemCons.APPLICATION_SYSTEM_PARAMS_VAR, JsonHelper.encodeObject2Json(paramsList));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}
	
	@RequestMapping(value = "/createWxMenu")
	public String createWxMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(((ManagerUserVo) super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuType()!=1){//非管理员禁操作
			super.setErrTipMsg(SystemCons.TIPS_ERROR_MSG, response);
			return null;
		}
		if(WeiXinUtils.createMenu(WeiXinTokenThread.ACCESS_TOKEN)){
			super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		}else{
			super.setErrTipMsg(SystemCons.TIPS_ERROR_MSG, response);
		}
		return null;
	}
}
