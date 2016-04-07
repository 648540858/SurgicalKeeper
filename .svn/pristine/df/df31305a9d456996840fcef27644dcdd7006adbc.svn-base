package com.rjkx.sk.manager.common.service;

public interface LogService {

	/**
	 * 操作日志添加
	 * @param opId
	 * @param opName
	 * @param type
	 * @param desc
	 */
	public abstract void addOpLog(int opId, String opName, int type, String desc);
	/**
	 * 
	  * 充值消费日志明细
	  * @author yiyuan-Rally
	  * @date 2015年10月13日 上午11:12:40
	  * @param opId
	  * @param opName
	  * @param type(用途)
	  * @param amount
	 */
	public abstract void addCapitalLog(int opId, String opName, int type, double amount,String orderNo);
	
	public abstract void addOpLogForC(int id,int status,int opId,String opName,String desc);
	
	public abstract void addOpLogForO(int id,int status,int opId,String opName,String desc);
}