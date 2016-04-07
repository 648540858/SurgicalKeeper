package com.rjkx.sk.manager.order.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.manager.base.ManagerBaseController;
import com.rjkx.sk.manager.index.vo.ManagerUserVo;
import com.rjkx.sk.manager.order.service.VipOrderServiceItf;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.SystemCons;

/***
 * VIP订单管理
 * 
 * @ClassName: VipOrderController
 * @Description:
 * @author yiyuan-LiChun
 * @date 2016年2月11日 上午9:30:40
 * @version V2.0
 */
@Controller
@RequestMapping(value = "/viporder")
public class VipOrderController extends ManagerBaseController {
	@Autowired
	@Resource(name = "vipOrderServiceImpl")
	private VipOrderServiceItf conService;

	/**
	 * LIST
	 * 
	 * @author lichun
	 * @date
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listVipOrder")
	public String listVipOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		List<?> data = super.getFredaReader().queryForList("VipOrder.queryOrder", pDto);

		Integer count = (Integer) super.getFredaReader().queryForObject("VipOrder.queryOrderCount", pDto);

		super.write(JsonHelper.encodeList2PageJson(data, count, SystemCons.DATE_TIME_FORMART), response);

		return null;
	}

	/**
	 * Load
	 * 
	 * @author lichun
	 * @date
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loadOrder")
	public String loadOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		Dto data = (Dto) super.getFredaReader().queryForObject("VipOrder.loadOrder", pDto);

		super.write(JsonHelper.encodeObject2Json(data, SystemCons.DATE_TIME_FORMART), response);

		return null;
	}

	/**
	 * 审核通过与不通过..
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年1月13日 上午10:51:26
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/verifyVip")
	public String verifyVip(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		pDto.put(systemProperties.getValue("sys.var.user.id"), ((ManagerUserVo) super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuId());

		pDto.put(systemProperties.getValue("sys.var.user.name"), ((ManagerUserVo) super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuName());

		if (conService.verifyVip(pDto)) {
			super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		} else {
			super.setErrTipMsg(SystemCons.TIPS_ERROR_MSG, response);
		}
		return null;
	}

	/**
	 * 设置预约信息
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年1月14日 下午1:49:11
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/setSch")
	public String setSch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		pDto.put(systemProperties.getValue("sys.var.user.id"), ((ManagerUserVo) super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuId());

		pDto.put(systemProperties.getValue("sys.var.user.name"), ((ManagerUserVo) super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuName());

		if (conService.setSchInfo(pDto)) {
			super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		} else {
			super.setErrTipMsg(SystemCons.TIPS_ERROR_MSG, response);
		}
		return null;
	}

	/**
	 * 发送支付
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年1月14日 下午5:01:51
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendPay")
	public String sendPay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (conService.sendPayMsg(super.getParamsAsDto(request)))
			super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		else
			super.setErrTipMsg(SystemCons.TIPS_ERROR_MSG, response);
		return null;
	}

	/**
	 * 发送确认请求
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年1月14日 下午5:02:23
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/sendConfirm")
	public String sendConfirm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		pDto.put(systemProperties.getValue("sys.var.user.id"), ((ManagerUserVo) super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuId());

		pDto.put(systemProperties.getValue("sys.var.user.name"), ((ManagerUserVo) super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuName());

		if (conService.sendConfirmMsg(pDto))
			super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		else
			super.setErrTipMsg(SystemCons.TIPS_ERROR_MSG, response);
		return null;
	}

	/**
	 * 废除订单
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年1月14日 下午5:02:23
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/abrogateVipOrder")
	public String abrogateVipOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		pDto.put(systemProperties.getValue("sys.var.user.id"), ((ManagerUserVo) super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuId());

		pDto.put(systemProperties.getValue("sys.var.user.name"), ((ManagerUserVo) super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuName());

		if (conService.abrogateVipOrder(pDto))
			super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		else
			super.setErrTipMsg(SystemCons.TIPS_ERROR_MSG, response);

		return null;
	}

	/**
	 * 获取退款数据准备退定金
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年1月14日 下午4:54:34
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getRefundInfo")
	public String getRefundInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.write(JsonHelper.encodeObject2Json(super.getFredaReader().queryForObject("VipOrder.getPayInfo", super.getParamsAsDto(request))), response);

		return null;
	}

	/**
	 * 如果是余额支付..加余额
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年1月20日 下午3:32:23
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addRefundToBalance")
	public String addRefundToBalance(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (conService.addBalance(super.getParamsAsDto(request))) {
			abrogateVipOrder(request,response);
			super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		} else {
			super.setOkTipMsg(SystemCons.TIPS_ERROR_MSG, response);
		}
		return null;
	}
}
