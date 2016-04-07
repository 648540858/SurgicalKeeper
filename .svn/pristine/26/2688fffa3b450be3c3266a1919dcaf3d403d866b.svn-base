package com.rjkx.sk.manager.common.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rjkx.sk.manager.base.ManagerBaseServiceImpl;
import com.rjkx.sk.manager.common.service.LogService;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.datastructure.impl.BaseDto;
/**
 * LOG SERVICE
 * @author rally
 *
 */
@Service(value="logV2ServiceImpl")
public class LogServiceImpl extends ManagerBaseServiceImpl implements LogService
{
	/**
	 * 添加操作日志
	 * @param pDto
	 */
	private void addOpLog(Dto pDto)
	{
		
	}
	/* (non-Javadoc)
	 * @see com.rjkx.sk.admin.common.service.impl.LogService#addOpLog(int, java.lang.String, int, java.lang.String)
	 */
	@Override
	public void addOpLog(int opId,String opName,int type,String desc)
	{
		addOpLog(null);
	}
	/* (non-Javadoc)
	 * @see com.rjkx.sk.admin.common.service.impl.LogService#addOpLog(int, java.lang.String, int, double)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addCapitalLog(int opId, String opName, int type, double amount,String orderNo) 
	{
		Dto pDto = new BaseDto();
		
		pDto.put("opId", opId);
		pDto.put("opName", opName);
		pDto.put("capType", type);
		pDto.put("amount", amount);
		
		pDto.put("orderNo", orderNo);
		
		addCapitalLog(pDto);
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	private void addCapitalLog(Dto pDto)
	{
		super.getFredaDao().insert("LogV2.addCapitalLog", pDto);
	}
	/**
	 * 
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月27日 上午10:37:20
	  * @param pDto
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	private void addOpLogForOrder(Dto pDto)
	{
		super.getFredaDao().insert("LogV2.addOpLogForOrder", pDto);
	}
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void addOpLogForC(int id,int status,int opId,String opName,String desc)
	{
		Dto pDto = new BaseDto();
		
		pDto.put("id", id);
		pDto.put("type", 2);
		pDto.put("status", status);
		pDto.put("opId", opId);
		pDto.put("opName", opName);
		pDto.put("opInfo", desc);
		
		this.addOpLogForOrder(pDto);
	}
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void addOpLogForO(int id,int status,int opId,String opName,String desc)
	{
		Dto pDto = new BaseDto();
		
		pDto.put("id", id);
		pDto.put("type", 1);
		pDto.put("status", status);
		pDto.put("opId", opId);
		pDto.put("opName", opName);
		pDto.put("opInfo", desc);
		
		this.addOpLogForOrder(pDto);
	}
}
