package com.rjkx.sk.admin.common.context;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.rjkx.sk.admin.common.service.BasicDataService;
import com.rjkx.sk.admin.common.service.LogService;
import com.rjkx.sk.admin.common.service.OrderService;
import com.rjkx.sk.admin.common.service.SmsService;
import com.rjkx.sk.system.utils.SpringBeanLoader;

/**
 * 模块间耦合接口
  * @ClassName: FredaContext
  * @Description: 
  * @author yiyuan-Rally
  * @date 2015年9月25日 下午2:07:49 
  * @version V1.0
 */
@Component(value="fredaContext")
public class FredaContext 
{
	private static final Log log = LogFactory.getLog(FredaContext.class);
	
	/**
	 * 日志支持
	 */
	private LogService logService;
	/**
	 * 基础数据支持
	 */
	private BasicDataService basicDataService;
	/**
	 * 订单服务支持
	 */
	private OrderService orderService;
	/**
	 * 信息服务支持
	 */
	private SmsService smsService;
	
	/**
	 * 加载获取实例 
	 * @return
	 */
	public static FredaContext getInstance()
	{
		log.info("加载接耦合口模块....");
		
		return (FredaContext) SpringBeanLoader.getSpringBean("fredaContext");
	}

	/************* GETTER AND SETTER **************/
	
	public LogService getLogService() 
	{
		return logService;
	}
	@Resource(name="logService")
	public void setLogService(LogService logService) 
	{
		this.logService = logService;
	}

	public BasicDataService getBasicDataService() 
	{
		return basicDataService;
	}
	@Resource(name="basicData")
	public void setBasicDataService(BasicDataService basicDataService) 
	{
		this.basicDataService = basicDataService;
	}

	public OrderService getOrderService() 
	{
		return orderService;
	}
	@Resource(name="orderService")
	public void setOrderService(OrderService orderService) 
	{
		this.orderService = orderService;
	}

	public SmsService getSmsService() 
	{
		return smsService;
	}
	@Resource(name="SmsService")
	public void setSmsService(SmsService smsService) 
	{
		this.smsService = smsService;
	}

}
