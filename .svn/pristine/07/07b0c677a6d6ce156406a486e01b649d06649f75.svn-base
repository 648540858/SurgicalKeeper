package com.rjkx.sk.admin.om.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjkx.sk.admin.core.web.AdminBaseController;
import com.rjkx.sk.admin.om.service.OrderManagerServiceItf;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.datastructure.ResultDataBean;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.SystemCons;
/**
 * 订单管理
  * @ClassName: OrderManagerController
  * @Description:
  * @author yiyuan-Rally
  * @date 2015年10月28日 上午10:43:09 
  * @version V1.0
 */
@Controller
@RequestMapping(value="/orderManager")
public class OrderManagerController extends AdminBaseController
{
	
	private OrderManagerServiceItf omService;
	
	/**
	 * QUERY
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月28日 上午10:58:57
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/queryOrder")
	public String queryOrder(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		ResultDataBean rdb = omService.queryOrder(super.getParamsAsDto(request));
		
		super.write(JsonHelper.encodeList2PageJson(rdb.getData(), rdb.getTotalCount(), SystemCons.DATE_TIME_FORMART), response);
		
		return null;
	}
	/**
	 * QUERY LOG
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月29日 上午9:52:29
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/queryOrderLog")
	public String queryOrderLog(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		ResultDataBean rdb = super.getFredaContext().getOrderService().orderQuery(super.getParamsAsDto(request));
		
		super.write(JsonHelper.encodeList2PageJson(rdb.getData(), rdb.getTotalCount(), SystemCons.DATE_TIME_FORMART), response);
		
		return null;
	}
	
	/**
	 * LOAD
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月28日 上午10:59:06
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/loadOrderInfo")
	public String loadOrderInfo(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		super.write(JsonHelper.encodeDto2FormLoadJson(omService.loadOrderInfo(super.getParamsAsDto(request)), SystemCons.DATE_TIME_FORMART), response);
		
		return null;
	}
	/**
	 * SET
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月28日 上午10:59:14
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/setOrderInfo")
	public String setOrderInfo(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		omService.setOrderInfo(super.getParamsAsDto(request));
		
		super.setOkTipMsg(SystemCons.TIPS_SUCCESS_MSG, response);
		
		return null;
	}
	/**
	 * QUERY DOCTOR
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月30日 下午6:20:56
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value="/queryDoc")
	public String queryDoc(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Dto pDto = super.getParamsAsDto(request);
		
		List<?> data = super.getFredaReader().queryForPage("OrderManager.queryDoc", pDto);
		
		Integer count = (Integer)super.getFredaReader().queryForObject("OrderManager.queryDocCount", pDto);
		
		super.write(JsonHelper.encodeList2PageJson(data, count, SystemCons.DATE_TIME_FORMART), response);
		
		return null;
	}
	
	
	public OrderManagerServiceItf getOmService() 
	{
		return omService;
	}
	@Resource(name="orderManagerServiceImpl")
	public void setOmService(OrderManagerServiceItf omService) 
	{
		this.omService = omService;
	}
	
	
}
