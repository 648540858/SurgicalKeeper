package com.rjkx.sk.manager.basedata.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.manager.base.ManagerBaseController;
import com.rjkx.sk.manager.basedata.service.InterrelationService;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.SystemCons;

/**
 * 关联关系
 * 
 * @ClassName: CorrelationController
 * @Description:
 * @author yiyuan-lichun
 * @date 2015年10月16日 上午10:10:39
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/interrelation")
public class InterrelationController extends ManagerBaseController {
	@Autowired
	@Resource(name="interrelationServiceImpl")
	private InterrelationService interrelationService;

	/**
	 * 疾病选择 For 医院科室
	 * 
	 * @author yiyuan-panlinlin
	 * @date 2015年10月16日 上午10:17:19
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sicknessTreeWithHosDeptForComb")
	public String sicknessTreeWithHosDeptForComb(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.write(JsonHelper.encodeObject2Json(interrelationService.sicknessTreeWithHosDeptForComb(super.getParamsAsDto(request))), response);

		return null;
	}

	/**
	 * 
	 * 医院科室选择 For 医院
	 * 
	 * @author yiyuan-panlinlin
	 * @date 2015年10月16日 上午10:20:38
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hosDeptTreeWithHosForComb")
	public String hosDeptTreeWithHosForComb(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.write(JsonHelper.encodeObject2Json(interrelationService.hosDeptTreeWithHosForCombo(super.getParamsAsDto(request))), response);

		return null;
	}

	/***
	 * 疾病选择 for 医生
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年2月11日 上午10:51:43
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sicknessTreeWithDocForComb")
	public String sicknessTreeWithDocForComb(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.write(JsonHelper.encodeObject2Json(interrelationService.sicknessTreeWithDoctorForCombo(super.getParamsAsDto(request))), response);

		return null;
	}

	/**
	 * 添加疾病与科室的关联
	 * 
	 * @author yiyuan-lichun
	 * @date 2015年10月16日 上午10:23:39
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sicknessToHosDept")
	public String sicknessToHosDept(HttpServletRequest request, HttpServletResponse response) throws Exception {
		interrelationService.addSicknessToHosDept(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * 添加医院科室与医院的关联
	 * 
	 * @author yiyuan-lichun
	 * @date 2015年10月16日 上午10:23:58
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hosDeptToHos")
	public String hosDeptToHos(HttpServletRequest request, HttpServletResponse response) throws Exception {
		interrelationService.addHosDeptToHos(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	/**
	 * 添加疾病与医生的关联
	 * 
	 * @author yiyuan-lichun
	 * @date 2015年10月20日 上午8:48:24
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sicknessToDoctor")
	public String sicknessToDoctor(HttpServletRequest request, HttpServletResponse response) throws Exception {
		interrelationService.addSicknessToDoctor(super.getParamsAsDto(request));

		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

}
