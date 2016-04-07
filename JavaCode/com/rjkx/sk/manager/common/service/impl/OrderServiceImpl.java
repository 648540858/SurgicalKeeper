package com.rjkx.sk.manager.common.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rjkx.sk.manager.base.ManagerBaseServiceImpl;
import com.rjkx.sk.manager.common.service.OrderService;
import com.rjkx.sk.manager.common.utils.OrderCons;
import com.rjkx.sk.manager.common.utils.OrderUtils;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.datastructure.ResultDataBean;
import com.rjkx.sk.system.datastructure.impl.BaseResultDataBean;
/**
 * ORDER
 * @author rally
 *
 */
@Service(value="orderV2ServiceImpl")
public class OrderServiceImpl extends ManagerBaseServiceImpl implements OrderService
{
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean orderCreate(Dto pDto)
	{
		pDto.put(OrderCons.ORDER_PARAMS_VAR_STATE, 1);
		
//		super.getFredaDao().insert("Order.addOrderSchedule", pDto);//添加订单预约记录
		
		pDto.put("orderNum", OrderUtils.getOrderNum(pDto.getAsBoolean("isVip")));
		
		super.getFredaDao().insert("OrderV2.addOrder", pDto);//添加订单
		
		super.getFredaDao().insert("OrderV2.addOrderLog", pDto);//添加订单历史
		
		super.getFredaDao().insert("OrderV2.addOrderFundLog", pDto);//添加未支付金额日志
		
		return true;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean orderToNext(Dto pDto)
	{
		boolean falg = false;
		
		switch (pDto.getAsInteger(OrderCons.ORDER_PARAMS_VAR_STATE)) //根据订单下一状态判断
		{
			case OrderCons.ORDER_STATE_WAITING ://支付成功..修改资金日志
				
				falg = this.editPayLog(pDto);
				
				break;
				
			case OrderCons.ORDER_STATE_COMPLETED : //就诊完成..或时间已到..发信息提醒并走向完成
				break;
				
			case OrderCons.ORDER_STATE_END : //评价
				
				falg = this.addEval(pDto);
				
				break;
				
			case OrderCons.ORDER_STATE_PAYBACK : //退款日志
				
				falg = this.addPayBackLog(pDto);
				
				break;
				
			case OrderCons.ORDER_STATE_END2 : //退款确认.金额退还用户.操作人:客服 更新日志状态.
				
				falg = this.editPayBackLogState(pDto);
				
				break;
				
			default:
				break;
		}
		this.editOrderState(pDto);
		
		return falg;
	}
	/**
	 * 更新订单状态与记录路线日志
	 * @param pDto
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	private void editOrderState(Dto pDto)
	{
		super.getFredaDao().update("OrderV2.editOrderLogFTime", pDto);//修改日志完成时间
		
		super.getFredaDao().update("OrderV2.editOrderState", pDto);//修改订单状态
		
		super.getFredaDao().insert("OrderV2.addOrderLog", pDto);//添加订单路线日志
	}
	
	/**
	 * 更新订单信息
	 * @param pDto
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	private void editOrderInfo(Dto pDto)
	{
		super.getFredaDao().update("OrderV2.editOrderInfo", pDto);//修改订单信息
		
	}
	/**
	 * 修改支付日志
	 * @param pDto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	private boolean editPayLog(Dto pDto)
	{
		pDto.put("fundLogStatus",OrderCons.FUND_LOG_STATE_YES_PAY);
		
		super.getFredaDao().update("OrderV2.editOrderFundLogStatus", pDto);
		
		//super.getFredaDao().update("OrderV2.editOrderPayInfo", pDto);
		
		this.editOrderInfo(pDto);
		
		return true;
	}
	/**
	 * 添加评价
	 * @param pDto
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	private boolean addEval(Dto pDto)
	{
		super.getFredaDao().insert("OrderV2.addDoctorReview", pDto);
		
		this.editOrderInfo(pDto);
		
		return true;
	}
	/**
	 * 添加退款日志
	 * @param pDto
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	private boolean addPayBackLog(Dto pDto)
	{
		//super.getFredaDao().insert("OrderV2.addCallBackLog", pDto);
		
		return true;
	}
	/**
	 * 确认退款.并更新退款日志状态
	 * @param pDto
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	private boolean editPayBackLogState(Dto pDto)
	{
		super.getFredaDao().insert("OrderV2.addCallBackLog", pDto);
		
		super.getFredaDao().delete("OrderV2.delOrderSch", pDto);
		
		//更新订单退款相关信息
		
		return true;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean orderCancel(Dto pDto)
	{
		super.getFredaDao().delete("OrderV2.delOrder", pDto);
		
		super.getFredaDao().delete("OrderV2.delOrderLog", pDto);
		
		super.getFredaDao().delete("OrderV2.delOrderSch", pDto);
		
		super.getFredaDao().delete("OrderV2.delOrderFund", pDto);
		
		return true;
	}

	@Override
	public ResultDataBean orderQuery(Dto pDto) throws Exception
	{
		switch (pDto.getAsInteger("type")) {
		case 1://订单信息
			return super.getFredaDao().queryForPage("OrderV2.queryOrderInfo", "OrderV2.queryOrderInfoCount", pDto);
		case 2://订单历史		
			return super.getFredaDao().queryForPage("OrderV2.queryOrderLog", "OrderV2.queryOrderLogCount", pDto);
		case 3://订单量
			ResultDataBean rdb = new BaseResultDataBean();
			
			rdb.setData(super.getFredaDao().queryForList("OrderV2.queryOrderStatusCount", pDto));
			return rdb;
		case 4://订单集合List
			ResultDataBean rdbList = new BaseResultDataBean();
			
			rdbList.setData(super.getFredaDao().queryForList("OrderV2.queryOrderInfo", pDto));
			return rdbList;
		default:
			break;
		}
		return null;
	}
	
}
