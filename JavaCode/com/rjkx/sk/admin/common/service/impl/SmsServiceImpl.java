package com.rjkx.sk.admin.common.service.impl;

import org.springframework.stereotype.Service;

import com.rjkx.sk.admin.common.service.SmsService;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.datastructure.impl.BaseDto;
import com.rjkx.sk.system.utils.SmsCons;
import com.rjkx.sk.system.utils.SmsSendUtils;
import com.rjkx.sk.system.web.base.BaseServiceImpl;

/**
 * 消息服务接口
  * @ClassName: SmsServiceImpl
  * @Description:
  * @author yiyuan-Rally
  * @date 2015年11月9日 下午1:59:25 
  * @version V1.0
 */
@Service(value="SmsService")
public class SmsServiceImpl extends BaseServiceImpl implements SmsService
{
	/*
	  * <p>Title: sendSMS</p>
	  * <p>Description: </p>
	  * @param sendInfo
	  * @param rcvInfo
	  * @param rcvAddr
	  * @param smsType
	  * @param keyWords
	  * @param level
	  * @see com.rjkx.sk.admin.common.service.impl.SmsService#sendSMS(java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, int)
	  */
	
	
	@Override
	@SuppressWarnings("unchecked")
	public void sendSMS(String sendInfo,String rcvInfo,String rcvAddr,int smsType,String keyWords,int level)
	{
		if(smsType!=4&&rcvAddr.length()!=11)
			return;
		Dto pDto = new BaseDto();
		
		pDto.put("sendInfo", sendInfo);
		pDto.put("rcvInfo", rcvInfo);
		pDto.put("rcvAddr", rcvAddr);
		pDto.put("smsType", smsType);
		pDto.put("keyWords", keyWords);
		pDto.put("level", level);
		
		super.getFredaDao().insert("SMS.addSMS", pDto);
	}
	/*
	  * <p>Title: sendVerCodeSMS</p>
	  * <p>Description: </p>
	  * @param rcvAddr
	  * @param keyWords
	  * @see com.rjkx.sk.admin.common.service.impl.SmsService#sendVerCodeSMS(java.lang.String, java.lang.String)
	  */
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void sendVerCodeSMS(String rcvAddr,String keyWords)
	{
		if(rcvAddr.length()!=11)
			return;
		//this.sendSMS("系统", "绑定用户", rcvAddr, 1, keyWords, 9);
		Dto rDto=new BaseDto();
		rDto.put("smsType", 1);
		rDto=((Dto)super.getFredaDao().queryForList("SMS.querySmsTemplete",rDto).get(0));
		String smsContent=rDto.getAsString("_text").replace("[text]", keyWords);
//		smsContent="欢迎注册，您的验证码为"+keyWords+"，请注意保密，如有疑问请联系客服4009958591";
		SmsSendUtils.getInstance().batchSend(SmsCons.CL_SMS_ACCOUNT, SmsCons.CL_SMS_PWD, rcvAddr, smsContent, false, null, null);

		Dto pDto = new BaseDto();
		pDto.put("sendInfo", "系统");
		pDto.put("rcvInfo", "绑定用户");
		pDto.put("rcvAddr", rcvAddr);
		pDto.put("smsType", 1);
		pDto.put("keyWords", smsContent);
		pDto.put("level", 9);
		super.getFredaDao().insert("SMS.addSmsLog", pDto);
	}
	/*
	  * <p>Title: sendSystemSMS</p>
	  * <p>Description: </p>
	  * @param rcvInfo
	  * @param rcvAddr
	  * @param smsType
	  * @param keyWords
	  * @param level
	  * @see com.rjkx.sk.admin.common.service.impl.SmsService#sendSystemSMS(java.lang.String, java.lang.String, int, java.lang.String, int)
	  */
	
	
	@Override
	public void sendSystemSMS(String rcvInfo,String rcvAddr,int smsType,String keyWords,int level)
	{
		this.sendSMS("系统", rcvInfo, rcvAddr, smsType, keyWords, level);
	}
}
