package com.rjkx.sk.manager.common.service.impl;

import org.springframework.stereotype.Service;

import com.rjkx.sk.manager.base.ManagerBaseServiceImpl;
import com.rjkx.sk.manager.common.service.SmsService;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.datastructure.impl.BaseDto;
import com.rjkx.sk.system.utils.SmsCons;
import com.rjkx.sk.system.utils.SmsSendUtils;

/**
 * 消息服务接口
 * 
 * @ClassName: SmsServiceImpl
 * @Description:
 * @author yiyuan-Rally
 * @date 2015年11月9日 下午1:59:25
 * @version V1.0
 */
@Service(value = "smsV2ServiceImpl")
public class SmsServiceImpl extends ManagerBaseServiceImpl implements SmsService {

	@Override
	@SuppressWarnings("unchecked")
	public void sendSMS(String sendInfo, String rcvInfo, String rcvAddr, int smsType, String keyWords, int level) {
		if (smsType != 4 && rcvAddr.length() != 11)
			return;
		Dto pDto = new BaseDto();

		pDto.put("sendInfo", sendInfo);
		pDto.put("rcvInfo", rcvInfo);
		pDto.put("rcvAddr", rcvAddr);
		pDto.put("smsType", smsType);
		pDto.put("keyWords", keyWords);
		pDto.put("level", level);

		super.getFredaDao().insert("SMSV2.addSMS", pDto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sendVerCodeSMS(String rcvAddr, String keyWords) {
		if (rcvAddr.length() != 11)
			return;
		// this.sendSMS("系统", "绑定用户", rcvAddr, 1, keyWords, 9);
		Dto rDto = new BaseDto();
		rDto.put("smsType", 1);
		rDto = ((Dto) super.getFredaDao().queryForList("SMS.querySmsTemplete", rDto).get(0));
		String smsContent = rDto.getAsString("_text").replace("[text]", keyWords);
		// smsContent="欢迎注册，您的验证码为"+keyWords+"，请注意保密，如有疑问请联系客服4009958591";
		SmsSendUtils.getInstance().batchSend(SmsCons.CL_SMS_ACCOUNT, SmsCons.CL_SMS_PWD, rcvAddr, smsContent, false, null, null);

		Dto pDto = new BaseDto();
		pDto.put("sendInfo", "系统");
		pDto.put("rcvInfo", "绑定用户");
		pDto.put("rcvAddr", rcvAddr);
		pDto.put("smsType", 1);
		pDto.put("keyWords", smsContent);
		pDto.put("level", 9);
		super.getFredaDao().insert("SMSV2.addSmsLog", pDto);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void sendPwdSMS(String rcvAddr, String keyWords) {
		if (rcvAddr.length() != 11)
			return;
		Dto rDto = new BaseDto();
		rDto.put("smsType", 8);
		rDto = ((Dto) super.getFredaDao().queryForList("SMS.querySmsTemplete", rDto).get(0));
		String smsContent = rDto.getAsString("_text").replace("[text]", keyWords);
		SmsSendUtils.getInstance().batchSend(SmsCons.CL_SMS_ACCOUNT, SmsCons.CL_SMS_PWD, rcvAddr, smsContent, false, null, null);

		Dto pDto = new BaseDto();
		pDto.put("sendInfo", "系统");
		pDto.put("rcvInfo", "找回密码");
		pDto.put("rcvAddr", rcvAddr);
		pDto.put("smsType", 8);
		pDto.put("keyWords", smsContent);
		pDto.put("level", 9);
		super.getFredaDao().insert("SMSV2.addSmsLog", pDto);
	}

	@Override
	public void sendSystemSMS(String rcvInfo, String rcvAddr, int smsType, String keyWords, int level) {
		this.sendSMS("系统", rcvInfo, rcvAddr, smsType, keyWords, level);
	}
}
