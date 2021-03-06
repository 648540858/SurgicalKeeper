package com.rjkx.sk.manager.index.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.manager.base.ManagerBaseController;
import com.rjkx.sk.manager.index.service.MindexService;
import com.rjkx.sk.manager.index.utils.IndexCons;
import com.rjkx.sk.manager.index.vo.ManagerUserVo;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.FredaUtils;
import com.rjkx.sk.system.utils.SystemCons;
import com.rjkx.sk.system.utils.WebUtil;

@Controller
@RequestMapping(value = "/manager")
public class ManagerIndexController extends ManagerBaseController {
	@Autowired
	@Resource(name = "mindexServiceImpl")
	private MindexService mindexService;

	@RequestMapping(value = "/loginIn")
	public String loginIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto dto = super.getParamsAsDto(request);
		ManagerUserVo user = mindexService.queryUser(dto);

		if (FredaUtils.isEmpty(user)) {
			super.setErrTipMsg(IndexCons.TIPS_LOGIN_USER_EMPTY, response);
		} else if (FredaUtils.isNotEmpty(user) && user.getuState() == 0) {
			super.setErrTipMsg(IndexCons.TIPS_LOGIN_STATE_OFF, response);
		} else if (FredaUtils.isNotEmpty(user) && user.getuLocked() == 0) {
			super.setErrTipMsg(IndexCons.TIPS_LOGIN_LOCKED_OFF, response);
		} else {
			user.setIpAddr(WebUtil.getIpAddr(request));
			user.setExplorerType(FredaUtils.getClientExplorerType(request));
			super.setSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR, user);
			super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		}
		return null;
	}

	@RequestMapping(value = "/loginOut")
	public String loginOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.removeSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR);
		return "redirect:" + SystemCons.MANAGER_INDEX_URL;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getMenuItem")
	public String getMenuItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		int uId = ((ManagerUserVo) super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuId();
		int uType = ((ManagerUserVo) super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuType();
		pDto.put("uId", uId);
		pDto.put("opType", uType);

		List<?> data = super.getFredaReader().queryForList("IndexV2.queryMenuItems", pDto);
		super.write("{\"ROOT\":" + JsonHelper.encodeObject2Json(data) + "}", response);
		return null;
	}
}
