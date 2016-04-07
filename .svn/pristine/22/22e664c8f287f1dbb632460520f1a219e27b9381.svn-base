package com.rjkx.sk.manager.common.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.manager.base.ManagerBaseController;
import com.rjkx.sk.manager.common.service.CommonTreeServiceItf;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;

@Controller
@RequestMapping(value = "/commonAdminTree")
public class CommonAdminTreeContorller extends ManagerBaseController {
	@Autowired
	@Resource(name="commonAdminTreeServiceImpl")
	private CommonTreeServiceItf	treeService;
	
	/**
	 * 城市树:全部区域
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cityTree")
	public String cityTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = treeService.getSubCityTree(pDto);
		super.write(JsonHelper.encodeObject2Json(data), response);
		return null;
	}
	
	/**
	 * 疾病子类树
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sickSubTree")
	public String sickSubTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = treeService.getSickSubTree(pDto);
		super.write(JsonHelper.encodeObject2Json(data), response);
		return null;
	}
	
	/***
	 * 获取医院科室列表
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年2月10日 下午10:11:27
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value = "/listHospDept")
	public String listHospDept(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = super.getFredaReader().queryForList("CommonAdminTree.getHospDeptList",pDto);
		super.write(JsonHelper.encodeObject2Json(data), response);
		return null;
	}
	
	/***
	 * 获取城市医院树
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年2月11日 下午2:57:55
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value = "/cityHospTree")
	public String cityHospTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = treeService.getCityHospTree(pDto);
		super.write(JsonHelper.encodeObject2Json(data), response);
		return null;
	}

	/***
	 * 获取疾病属性列表
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年2月10日 下午10:11:27
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value = "/listSickAttr")
	public String listSickAttr(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = super.getFredaReader().queryForList("CommonAdminTree.getSickAttrList",pDto);
		super.write(JsonHelper.encodeObject2Json(data), response);
		return null;
	}

	/***
	 * 获取疾病分类树
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年2月11日 下午2:57:55
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value = "/sickTree")
	public String sickTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = treeService.getSickTree(pDto);
		super.write(JsonHelper.encodeObject2Json(data), response);
		return null;
	}

	/**
	 * 部门树:全部
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deptTree")
	public String deptTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = treeService.getDeptTree(pDto);
		super.write(JsonHelper.encodeObject2Json(data), response);
		return null;
	}
	
	/**
	 * 菜单树:全部
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/menuTree")
	public String menuTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = treeService.getMenuTree(pDto);
		super.write(JsonHelper.encodeObject2Json(data), response);
		return null;
	}

	/***
	 * 获取城市医院医生树
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年2月11日 下午2:57:55
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value = "/doctorTree")
	public String doctorTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = treeService.getDoctorTree(pDto);
		super.write(JsonHelper.encodeObject2Json(data), response);
		return null;
	}
}
