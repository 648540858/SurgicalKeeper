package com.rjkx.sk.manager.sys.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.manager.base.ManagerBaseController;
import com.rjkx.sk.manager.sys.service.OrganizationServiceItf;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.SystemCons;

/**
 * 组织机构的增删改查
 * 
 * @author Rally
 *
 */
@Controller
@RequestMapping(value = "/organizationv2")
public class OrganizationV2Controller extends ManagerBaseController {
	@Autowired
	@Resource(name = "organizationV2ServiceImpl")
	private OrganizationServiceItf organizationService;

	/**
	 * LIST DEPT
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月21日 下午1:44:51
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listDept")
	public String listDept(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		List<?> data = super.getFredaReader().queryForList("OrganizationV2.listDept", pDto);
		super.write("{\"ROOT\":"+JsonHelper.encodeObject2Json(data)+"}", response);
		return null;
	}

	/**
	 * LIST ROLE
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月21日 下午1:45:02
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listRole")
	public String listRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		List<?> data = super.getFredaReader().queryForList("OrganizationV2.listRole", pDto);
		super.write("{\"ROOT\":"+JsonHelper.encodeObject2Json(data, SystemCons.DATE_TIME_FORMART)+"}", response);
		return null;
	}

	/**
	 * LIST USER
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月21日 下午1:45:10
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listUser")
	public String listUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		List<?> data = super.getFredaReader().queryForList("OrganizationV2.listUser", pDto);

		super.write("{\"ROOT\":"+JsonHelper.encodeObject2Json(data)+"}", response);

		return null;
	}

	/**
	 * ADD DEPT
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月21日 下午1:45:18
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addDept")
	public String addDept(HttpServletRequest request, HttpServletResponse response) throws Exception {
		organizationService.addDept(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * ADD ROLE
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月21日 下午1:45:28
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addRole")
	public String addRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		organizationService.addRole(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * ADD USER
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月21日 下午1:45:37
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addUser")
	public String addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		organizationService.addUser(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * EDIT DEPT
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月21日 下午1:45:46
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editDept")
	public String editDept(HttpServletRequest request, HttpServletResponse response) throws Exception {
		organizationService.editDept(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * EDIT ROLE
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月21日 下午1:45:54
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editRole")
	public String editRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		organizationService.editRole(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * EDIT USER
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月21日 下午1:46:01
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editUser")
	public String editUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		organizationService.editUser(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * DELETE DEPT
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月21日 下午1:46:09
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delDept")
	public String delDept(HttpServletRequest request, HttpServletResponse response) throws Exception {
		organizationService.delDept(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * DELETE ROLE
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月21日 下午1:46:24
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delRole")
	public String delRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		organizationService.delRole(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * DELETE USER
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月21日 下午1:46:32
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delUser")
	public String delUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		organizationService.delUser(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * GET DEPT
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月21日 下午1:44:51
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getDept")
	public String getDept(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		Dto data = (Dto)super.getFredaReader().queryForObject("OrganizationV2.getDept", pDto);
		super.write(JsonHelper.encodeObject2Json(data), response);
		return null;
	}
	
	/**
	 * GET Role
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月21日 下午1:44:51
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getRole")
	public String getRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		Dto data = (Dto)super.getFredaReader().queryForObject("OrganizationV2.getRole", pDto);
		super.write(JsonHelper.encodeObject2Json(data), response);
		return null;
	}
	
	/**
	 * GET User
	 * 
	 * @author yiyuan-LiChun
	 * @date 2015年9月21日 下午1:44:51
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUser")
	public String getUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		Dto data = (Dto)super.getFredaReader().queryForObject("OrganizationV2.getUser", pDto);
		super.write(JsonHelper.encodeObject2Json(data), response);
		return null;
	}
}
