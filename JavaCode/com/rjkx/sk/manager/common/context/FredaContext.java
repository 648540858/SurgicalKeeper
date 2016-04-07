package com.rjkx.sk.manager.common.context;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.rjkx.sk.manager.common.service.LogService;
import com.rjkx.sk.manager.common.service.MsgPushService;
import com.rjkx.sk.manager.common.service.OrderService;
import com.rjkx.sk.manager.common.service.SmsService;
import com.rjkx.sk.system.utils.SpringBeanLoader;

/***
 * 模块间耦合
 * 
 * @ClassName: FredaContext
 * @Description:
 * @author yiyuan-LiChun
 * @date 2016年2月12日 上午9:50:20
 * @version V1.0
 */
@Component(value="fredaContextV2")
public class FredaContext {
	/**
	 * 日志支持
	 */
	private LogService logService;
	/**
	 * 订单服务支持
	 */
	private OrderService orderService;
	/**
	 * 信息服务支持
	 */
	private SmsService smsService;
	/***
	 * 消息推送支持
	 */
	private MsgPushService msgPushService;
	
	/**
	 * 加载获取实例
	 * 
	 * @return
	 */
	public static FredaContext getInstance() {
		return (FredaContext) SpringBeanLoader.getSpringBean("fredaContextV2");
	}

	/************* GETTER AND SETTER **************/

	public LogService getLogService() {
		return logService;
	}

	@Resource(name = "logV2ServiceImpl")
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	@Resource(name = "orderV2ServiceImpl")
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public SmsService getSmsService() {
		return smsService;
	}

	@Resource(name = "smsV2ServiceImpl")
	public void setSmsService(SmsService smsService) {
		this.smsService = smsService;
	}


	public MsgPushService getMsgPushService() {
		return msgPushService;
	}
	
	@Resource(name = "msgPushServiceImpl")
	public void setMsgPushService(MsgPushService msgPushService) {
		this.msgPushService = msgPushService;
	}

}
