package com.rjkx.sk.admin.om.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.admin.core.vo.AdminUserVo;
import com.rjkx.sk.admin.core.web.AdminBaseController;
import com.rjkx.sk.admin.om.service.ConsultationServiceItf;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.SystemCons;
/**
 * 专家会诊
  * @ClassName: ConsultationController
  * @Description:
  * @author yiyuan-Rally
  * @date 2015年10月26日 上午9:39:06 
  * @version V1.0
 */
@Controller
@RequestMapping(value="/consultation")
public class ConsultationController extends AdminBaseController
{
	private ConsultationServiceItf conService;
	
	/**
	 * 查询
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月26日 下午1:56:35
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/queryConsultation")
	public String queryConsultation(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = super.getFredaReader().queryForPage("Consultation.queryConsultation", pDto);
		
		Integer count = (Integer) super.getFredaReader().queryForObject("Consultation.queryConsultationCount", pDto);
		
		super.write(JsonHelper.encodeList2PageJson(data, count, SystemCons.DATE_TIME_FORMART_1), response);
		
		return null;
	}
	/**
	 * 加载
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月26日 下午1:56:47
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/loadConsultation")
	public String loadConsultation(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		super.write(JsonHelper.encodeDto2FormLoadJson((Dto)super.getFredaReader().queryForObject("Consultation.loadConsultation", super.getParamsAsDto(request)), SystemCons.DATE_TIME_FORMART), response);
		
		return null;
	}
	/**
	 * 处理
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月26日 下午1:56:57
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/setConsultationStatus")
	public String setConsultationStatus(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		pDto.put(systemProperties.getValue("sys.var.user.id"), ((AdminUserVo)super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuId());
		
		pDto.put(systemProperties.getValue("sys.var.user.name"), ((AdminUserVo)super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuName());
		
		conService.setConsultationStatus(pDto);
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * 操作日志查询
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月27日 下午3:22:13
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/listOpLog")
	public String listOpLog(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = super.getFredaReader().queryForPage("Consultation.listOpLog", pDto);
		
		Integer count = (Integer)super.getFredaReader().queryForObject("Consultation.listOpLogCount", pDto);
		
		super.write(JsonHelper.encodeList2PageJson(data, count, SystemCons.DATE_TIME_FORMART), response);
		
		return null;
	}
	/**
	 * 医生树形结构
	  * 
	  * @author yiyuan-Rally
	  * @date 2016年1月13日 下午4:49:28
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/cityHosDocTreeInit")
	public String cityHosDocTreeInit(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		super.write(JsonHelper.encodeObject2Json(super.getFredaContext().getBasicDataService().cityAndHosAndDocTreeInit(super.getParamsAsDto(request))), response);
		
		return null;
	}
	/**
	 * 审核通过与不通过..
	  * 
	  * @author yiyuan-Rally
	  * @date 2016年1月13日 上午10:51:26
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/verifyVip")
	public String verifyVip(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		pDto.put(systemProperties.getValue("sys.var.user.id"), ((AdminUserVo)super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuId());
		
		pDto.put(systemProperties.getValue("sys.var.user.name"), ((AdminUserVo)super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuName());
		
		if(conService.verifyVip(pDto))
		{
			super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		}
		else
		{
			super.setErrTipMsg(SystemCons.TIPS_ERROR_MSG, response);
		}
		return null;
	}
	/**
	 * 设置预约信息
	  * 
	  * @author yiyuan-Rally
	  * @date 2016年1月14日 下午1:49:11
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/setSch")
	public String setSch(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		pDto.put(systemProperties.getValue("sys.var.user.id"), ((AdminUserVo)super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuId());
		
		pDto.put(systemProperties.getValue("sys.var.user.name"), ((AdminUserVo)super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuName());
		
		if(conService.setSchInfo(pDto))
		{
			super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		}
		else
		{
			super.setErrTipMsg(SystemCons.TIPS_ERROR_MSG, response);
		}
		return null;
	}
	/**
	 * 获取退款数据准备退定金
	  * 
	  * @author yiyuan-Rally
	  * @date 2016年1月14日 下午4:54:34
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/getRefundInfo")
	public String getRefundInfo(HttpServletRequest request,HttpServletResponse response)throws Exception
	{	
		super.write(JsonHelper.encodeObject2Json(super.getFredaReader().queryForObject("Consultation.getPayInfo", super.getParamsAsDto(request))), response);
		
		return null;
	}
	/**
	 * 发送支付
	  * 
	  * @author yiyuan-Rally
	  * @date 2016年1月14日 下午5:01:51
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/sendPay")
	public String sendPay(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		if(conService.sendPayMsg(super.getParamsAsDto(request)))
		
			super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		else
			
			super.setErrTipMsg(SystemCons.TIPS_ERROR_MSG, response);
		
		return null;
	}
	/**
	 * 发送确认请求
	  * 
	  * @author yiyuan-Rally
	  * @date 2016年1月14日 下午5:02:23
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/sendConfirm")
	public String sendConfirm(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		pDto.put(systemProperties.getValue("sys.var.user.id"), ((AdminUserVo)super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuId());
		
		pDto.put(systemProperties.getValue("sys.var.user.name"), ((AdminUserVo)super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuName());
		
		if(conService.sendConfirmMsg(pDto))
		
			super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		else
			
			super.setErrTipMsg(SystemCons.TIPS_ERROR_MSG, response);
		
		return null;
	}
	/**
	 * 废除订单
	  * 
	  * @author yiyuan-Rally
	  * @date 2016年1月14日 下午5:02:23
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/abrogateVipOrder")
	public String abrogateVipOrder(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		pDto.put(systemProperties.getValue("sys.var.user.id"), ((AdminUserVo)super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuId());
		
		pDto.put(systemProperties.getValue("sys.var.user.name"), ((AdminUserVo)super.getSessionAttribute(request, SystemCons.ADMIN_SESSION_USER_VAR)).getuName());
		
		if(conService.abrogateVipOrder(pDto))
			
			super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		else
			
			super.setErrTipMsg(SystemCons.TIPS_ERROR_MSG, response);
		
		return null;
	}
	/**
	 * 如果是余额支付..加余额
	  * 
	  * @author yiyuan-Rally
	  * @date 2016年1月20日 下午3:32:23
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/addRefundToBalance")
	public String addRefundToBalance(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		if(conService.addBalance(super.getParamsAsDto(request)))
		{
			super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		}
		else
		{
			super.setOkTipMsg(SystemCons.TIPS_ERROR_MSG, response);
		}
		return null;
	}
	
	public ConsultationServiceItf getConService() 
	{
		return conService;
	}
	@Resource(name="consultationServiceImpl")
	public void setConService(ConsultationServiceItf conService) 
	{
		this.conService = conService;
	}
}
