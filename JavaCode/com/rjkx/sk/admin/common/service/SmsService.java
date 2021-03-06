package com.rjkx.sk.admin.common.service;

public interface SmsService {

	/**
	 * 统一发送短信接口
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年11月9日 下午2:02:42
	  * @param sendInfo 发送者信息
	  * @param rcvInfo 接收者信息
	  * @param rcvAddr 接收地址
	  * @param smsType 模板类型..根据模板类型将分流为SMS 微信 PC提示
	  * @param keyWords 模板TEXT替换关键字
	  * @param level 短信级别
	 */
	void sendSMS(String sendInfo, String rcvInfo, String rcvAddr, int smsType, String keyWords, int level);

	/**
	 * 发送短信验证码
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年11月9日 下午2:07:36
	  * @param rcvAddr
	  * @param keyWords
	 */
	void sendVerCodeSMS(String rcvAddr, String keyWords);

	/**
	 * 系统信息发送
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年11月9日 下午2:11:44
	  * @param rcvInfo
	  * @param rcvAddr
	  * @param smsType
	  * @param keyWords
	  * @param level
	 */
	void sendSystemSMS(String rcvInfo, String rcvAddr, int smsType, String keyWords, int level);

}