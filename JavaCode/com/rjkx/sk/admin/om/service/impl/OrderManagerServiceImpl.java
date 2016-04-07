package com.rjkx.sk.admin.om.service.impl;

import org.springframework.stereotype.Service;

import com.rjkx.sk.admin.core.web.AdminBaseServiceImpl;
import com.rjkx.sk.admin.om.service.OrderManagerServiceItf;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.datastructure.ResultDataBean;
/**
 * ORDER MANAGER
  * @ClassName: OrderManagerServiceImpl
  * @Description:
  * @author yiyuan-Rally
  * @date 2015年10月28日 上午11:00:19 
  * @version V1.0
 */
@Service(value="orderManagerServiceImpl")
public class OrderManagerServiceImpl extends AdminBaseServiceImpl implements OrderManagerServiceItf
{
	
	/*
	  * <p>Title: queryOrder</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.om.service.impl.OrderManagerServiceItf#queryOrder(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	@SuppressWarnings("unchecked")
	public ResultDataBean queryOrder(Dto pDto) throws Exception
	{	
		pDto.put("type", 1);
		
		pDto.put("isNeedPatient", 1);
		
		pDto.put("isNeedSch", 1);
		
		return super.getFredaContext().getOrderService().orderQuery(pDto);
	}
	
	/*
	  * <p>Title: loadOrderInfo</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.om.service.impl.OrderManagerServiceItf#loadOrderInfo(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	@SuppressWarnings("unchecked")
	public Dto loadOrderInfo(Dto pDto)
	{
		Dto result = (Dto)super.getFredaDao().queryForObject("OrderManager.LoadOrderInfo", pDto);//查询出订单信息
		
		result.putAll((Dto)super.getFredaDao().queryForObject("OrderManager.loadDocInfo", pDto));//装入医生信息
		
		result.putAll((Dto)super.getFredaDao().queryForObject("OrderManager.loadPatientInfo", pDto));//装入患者信息
		
		result.putAll((Dto)super.getFredaDao().queryForObject("OrderManager.loadSchInfo", pDto));//装入预约信息
		
		return result;
	}
	
	/*
	  * <p>Title: setOrderInfo</p>
	  * <p>Description: </p>
	  * @param pDto
	  * @see com.rjkx.sk.admin.om.service.impl.OrderManagerServiceItf#setOrderInfo(com.rjkx.sk.system.datastructure.Dto)
	  */
	
	
	@Override
	public void setOrderInfo(Dto pDto)
	{
		super.getFredaDao().update("OrderManager.editSchInfo", pDto);
	}
}
