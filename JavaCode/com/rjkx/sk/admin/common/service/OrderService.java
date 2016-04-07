package com.rjkx.sk.admin.common.service;

import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.datastructure.ResultDataBean;

public interface OrderService {

	/**
	 * Create
	 * @param pDto
	 * @return
	 */
	public abstract boolean orderCreate(Dto pDto);

	/**
	 * Next
	 * @param pDto
	 * @return
	 */
	public abstract boolean orderToNext(Dto pDto);

	/**
	 * 订单取消..用于为支付时直接删除订单与订单路线日志
	 * @param pDto
	 * @return
	 */
	public abstract boolean orderCancel(Dto pDto);

	/**
	 * 用于订单查询(仅应用于微信): 
	 * 1,查询个人订单(根据状态)分页
	 * 2,查询某个状态所有订单分页
	 * 3,查询某个订单的订单路线
	 * @param pDto
	 * @return
	 * @throws Exception 
	 */
	public abstract ResultDataBean orderQuery(Dto pDto) throws Exception;

}