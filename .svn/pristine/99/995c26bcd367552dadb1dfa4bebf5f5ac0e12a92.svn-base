package com.rjkx.sk.manager.order.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.manager.base.ManagerBaseController;
import com.rjkx.sk.manager.index.vo.ManagerUserVo;
import com.rjkx.sk.manager.order.service.ApptOrderServiceItf;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.SystemCons;

/***
 * 精准预约订单管理
 * 
 * @ClassName: VipOrderController
 * @Description:
 * @author yiyuan-LiChun
 * @date 2016年2月20日 上午9:30:40
 * @version V2.0
 */
@Controller
@RequestMapping(value = "/apptOrder")
public class ApptOrderController extends ManagerBaseController {

	@Resource(name = "apptOrderServiceImpl")
	private ApptOrderServiceItf apptOrderService;

	/**
	 * LIST
	 * 
	 * @author panlinlin
	 * @date
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listApptOrder")
	public String listApptOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		List<?> data = super.getFredaReader().queryForList("ApptOrder.queryOrder", pDto);

		Integer count = (Integer) super.getFredaReader().queryForObject("ApptOrder.queryOrderCount", pDto);

		super.write(JsonHelper.encodeList2PageJson(data, count, SystemCons.DATE_TIME_FORMART), response);

		return null;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listCity")
	public String listCity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		List<?> data = super.getFredaReader().queryForList("ApptOrder.queryCity", pDto);
		for (int i = 0; i < data.size(); i++) {
			Dto dto = (Dto) data.get(i);

			if (dto.getAsInteger("leaf") == 0) {
				dto.put("isParent", true);
			} else if (dto.getAsInteger("leaf") == 1) {
				dto.put("isParent", false);
			}
		}
		super.write(JsonHelper.encodeObject2Json(data, SystemCons.DATE_TIME_FORMART), response);
		return null;
	}

	// 查询订单详情
	@RequestMapping(value = "/listApptOrderAll")
	public String listApptOrderAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		Dto data = (Dto) super.getFredaReader().queryForObject("ApptOrder.queryOrderAll", pDto);

		super.write(JsonHelper.encodeObject2Json(data, SystemCons.DATE_TIME_FORMART), response);
		return null;
	}

	// 更改预约信息
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateAppt")
	public String updateAppt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		// 判断传送内容
		Dto Dto = (Dto) super.getFredaReader().queryForObject("ApptOrder.queryOrderAll", pDto);

		if (pDto.getAsString("address") == null || pDto.getAsString("address") == "") {
			pDto.put("address", Dto.getAsString("address"));
		}
		if (pDto.getAsString("appt_remark") == null || pDto.getAsString("appt_remark") == "") {
			pDto.put("appt_remark", Dto.getAsString("msg"));
		}
		if (pDto.getAsString("doc_id") == null || pDto.getAsString("doc_id") == "") {
			pDto.put("doc_id", Dto.getAsInteger("doctorId"));
		}
		if (pDto.getAsString("appt_sTime") == null || pDto.getAsString("appt_sTime") == "") {
			pDto.put("appt_sTime", Dto.getAsDate("sTime"));
		}
		if (pDto.getAsString("appt_eTime") == null || pDto.getAsString("appt_eTime") == "") {
			pDto.put("appt_eTime", Dto.getAsDate("eTime"));
		}
		pDto.put(systemProperties.getValue("sys.var.user.id"), ((ManagerUserVo) super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuId());

		pDto.put(systemProperties.getValue("sys.var.user.name"), ((ManagerUserVo) super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuName());

		try {
			apptOrderService.updateAppt(pDto);
		} catch (Exception e) {
			e.printStackTrace();
			super.setOkTipMsg(SystemCons.TIPS_ERROR_MSG, response);
			return null;
		}
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		return null;
	}

	// 回显用户详情
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/detaillist")
	public String detaillist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		// 判断传送内容
		Dto DData = (Dto) super.getFredaReader().queryForObject("ApptOrder.queryDetail", pDto);
		Dto pData = (Dto) super.getFredaReader().queryForObject("ApptOrder.queryPatient", pDto);
		if (DData != null) {
			pData.put("source", DData.getAsString("source"));
			pData.put("surgety", DData.getAsString("surgety"));
			pData.put("diagnose", DData.getAsString("diagnose"));
			pData.put("history", DData.getAsString("history"));
			pData.put("category", DData.getAsString("category"));
			pData.put("pDesc", DData.getAsString("pDesc"));
		}
		super.write(JsonHelper.encodeObject2Json(pData, SystemCons.DATE_TIME_FORMART), response);
		return null;
	}

	// 提交用户详情
	@RequestMapping(value = "/detailSubmit")
	public String detailSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		try {
			apptOrderService.addDetail(pDto);
		} catch (Exception e) {

			e.printStackTrace();

			super.setOkTipMsg(SystemCons.TIPS_ERROR_MSG, response);

			return null;
		}
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	// 发送预约成功短信
	@RequestMapping(value = "/sendApptSms")
	public String sendApptSms(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		try {
			apptOrderService.sendApptSms(pDto);
		} catch (Exception e) {

			e.printStackTrace();

			super.setOkTipMsg(SystemCons.TIPS_ERROR_MSG, response);

			return null;
		}
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);

		return null;
	}

	// 审核订单通过
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/verifyOrder")
	public String verifyOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		pDto.put(systemProperties.getValue("sys.var.user.id"), ((ManagerUserVo) super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuId());

		pDto.put(systemProperties.getValue("sys.var.user.name"), ((ManagerUserVo) super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuName());

		try {
			apptOrderService.verifyOrder(pDto);
		} catch (Exception e) {
			e.printStackTrace();
			super.setOkTipMsg(SystemCons.TIPS_ERROR_MSG, response);
			return null;
		}
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
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
		Dto pDto = super.getParamsAsDto(request);
		Dto data = (Dto) super.getFredaReader().queryForObject("ApptOrder.getPayInfo", pDto);
		super.write(JsonHelper.encodeObject2Json(data, SystemCons.DATE_TIME_FORMART), response);
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
		Dto pDto = super.getParamsAsDto(request);
		try {
			apptOrderService.addBalance(pDto);
			// 修改订单状态
			cancelOrder(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			super.setOkTipMsg(SystemCons.TIPS_ERROR_MSG, response);
			return null;
		}
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		return null;
	}

	// 取消订单（修改订单状态）
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/cancelOrder")
	public String cancelOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		pDto.put(systemProperties.getValue("sys.var.user.id"), ((ManagerUserVo) super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuId());

		pDto.put(systemProperties.getValue("sys.var.user.name"), ((ManagerUserVo) super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuName());

		try {
			apptOrderService.cancelOrder(pDto);
		} catch (Exception e) {
			e.printStackTrace();
			super.setOkTipMsg(SystemCons.TIPS_ERROR_MSG, response);
			return null;
		}
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		return null;
	}

	// 查询评价信息
	@RequestMapping(value = "/listApptAppr")
	public String listApptAppr(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		Dto data = (Dto) super.getFredaReader().queryForObject("ApptOrder.queryApptAppr", pDto);

		super.write(JsonHelper.encodeObject2Json(data, SystemCons.DATE_TIME_FORMART), response);
		return null;
	}

	// 查询购物信息
	@RequestMapping(value = "/listApptGift")
	public String listApptGift(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto pDto = super.getParamsAsDto(request);

		Dto data = (Dto) super.getFredaReader().queryForObject("ApptOrder.queryApptGift", pDto);

		super.write(JsonHelper.encodeObject2Json(data, SystemCons.DATE_TIME_FORMART), response);
		return null;
	}
}