package com.rjkx.sk.manager.order.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rjkx.sk.itf.weixin.utils.WeiXinUtils;
import com.rjkx.sk.manager.base.ManagerBaseServiceImpl;
import com.rjkx.sk.manager.common.context.FredaContext;
import com.rjkx.sk.manager.order.service.VipOrderServiceItf;
import com.rjkx.sk.manager.order.utils.VipCons;
import com.rjkx.sk.manager.order.utils.VipConsUtils;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.datastructure.impl.BaseDto;
import com.rjkx.sk.system.json.JsonHelper;
import com.rjkx.sk.system.utils.FredaUtils;

/**
 * 专家会诊订单管理
 * 
 * @ClassName: VipOrderServiceImpl
 * @Description:
 * @author yiyuan-LiChun
 * @date 2015年10月26日 上午9:39:53
 * @version V1.0
 */
@Service(value = "vipOrderServiceImpl")
public class VipOrderServiceImpl extends ManagerBaseServiceImpl implements VipOrderServiceItf {
	private static final double[] BILI = { 0.4, 0.15, 0.6 };

	@Override
	public void setConsultationStatus(Dto pDto) {
		super.getFredaDao().update("VipOrder.setConsultationStatus", pDto);

		super.getFredaContext().getLogService().addOpLogForC(pDto.getAsInteger("cId"), pDto.getAsInteger("cStatus"), pDto.getAsInteger("opId"), pDto.getAsString("opName"), pDto.getAsString("opInfo"));
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean verifyVip(Dto pDto) {
		super.getFredaDao().update("VipOrder.setConsultationStatus", pDto);
		
		pDto.put("orderNum", VipConsUtils.getOrderNum());// 生成单号

		super.getFredaDao().insert("VipOrder.addVipOrder", pDto);

		super.getFredaContext().getLogService().addOpLogForC(pDto.getAsInteger("cId"), 0, pDto.getAsInteger("opId"), pDto.getAsString("opName"), pDto.getAsString("opInfo")); // 添加操作日志

		return true;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean setSchInfo(Dto pDto) {
		this.getPutInfo(pDto);

		if (super.getFredaDao().update("VipOrder.editOrderInfo", pDto) > 0) {
			this.addSch(pDto);// 设置旧的预约信息作废..设置新的预约信息

			super.getFredaContext().getLogService().addOpLogForC(pDto.getAsInteger("cId"), pDto.getAsInteger("oStatus"), pDto.getAsInteger("opId"), pDto.getAsString("opName"), pDto.getAsString("opInfo")); // 添加操作日志

			this.sendMessageForSetSch(pDto);// 发信息告知双方(包括给预约人发的支付消息)

			return true;
		}
		return false;
	}

	/**
	 * 根据不同的需求加入需要的参数
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年1月14日 下午3:50:26
	 * @param pDto
	 */
	@SuppressWarnings("unchecked")
	private void getPutInfo(Dto pDto) {
		int schType = pDto.getAsInteger("schTypeForEdit");

		if (schType == 1) {
			// pDto.put("needPayTotal", TOTAL_NEED_PAY);//需支付总额

			pDto.put("nextStatus", 2);// 下一状态

			pDto.put("isNeedPay", 1);// 需要支付

			pDto.put("payMode", 1);// 支付类目

			pDto.put("payAmount", pDto.getAsInteger("needPayTotal") * BILI[0]);// 支付金额

			pDto.put("opInfo", "沟通预约下单");
		} else if (schType == 2) {
			pDto.put("isNeedPay", 1);// 需要支付

			pDto.put("payMode", 2);// 支付类目

			pDto.put("payAmount", BILI[1]);// 支付金额

			pDto.put("opInfo", "患者需求.改预约.");
		} else {
			pDto.put("isNeedPay", 0);// 无需要支付

			pDto.put("payMode", 0);// 支付类目

			pDto.put("payAmount", 0);// 支付金额

			pDto.put("opInfo", "医生需求.改预约.");
		}
	}

	/**
	 * 修改预约信息
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年1月14日 下午3:05:24
	 * @param pDto
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	private void addSch(Dto pDto) {
		super.getFredaDao().update("VipOrder.editSchStatusForOrder", pDto);

		super.getFredaDao().insert("VipOrder.addSch", pDto);

		// this.sendSmsForDocCancel(pDto);
	}

	/**
	 * 发消息通知..与发支付
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年1月14日 下午3:11:54
	 * @param pDto
	 */
	@SuppressWarnings("unchecked")
	private void sendMessageForSetSch(Dto pDto) {
		this.sendPayMsg(pDto);

		this.sendSmsForDocSuccess(pDto);
		//添加推送消息
		Dto data=new BaseDto();
		data.put("msgtype", 1);
		data.put("title", "新的患者VIP会诊预约");
		
		Dto rDto=(Dto)super.getFredaDao().queryForObject("VipOrder.getOrderDocid",pDto);
		if(FredaUtils.isNotEmpty(rDto)){
			Dto tDto=new BaseDto();
			tDto.put("toid",rDto.getAsLong("docId"));//用户id
			tDto.put("totype", 3);//医生，如为患者：4
			tDto.put("clientid", "");
			tDto.put("tagname", "tag_userid_"+rDto.getAsLong("docId"));//医生
			tDto.put("title", "新预约");
			tDto.put("msg", JsonHelper.encodeObject2Json(data));
			FredaContext.getInstance().getMsgPushService().pushMessage(tDto);
		}
		this.sendSmsForPatSuccess(pDto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean sendPayMsg(Dto pDto) {
		Dto rDto = (Dto) super.getFredaDao().queryForObject("VipOrder.getOrderNeedPayInfo", pDto);

		if (FredaUtils.isNotEmpty(rDto)) {
			double amount = Double.parseDouble(rDto.getAsString("payAmount"));

			String mode = rDto.getAsString("payMode"), openId = rDto.getAsString("mainUserOpenId"), orderNum = rDto.getAsString("orderNum");

			rDto.clear();
			/*
			 * 设置参数
			 */
			rDto.put("touser", openId);
			rDto.put("template_id", WeiXinUtils.TEMPLATEMESSAGE_ID_PAY);
			rDto.put("url", VipCons.WX_PAY_PAGE + "?oId=" + pDto.getAsString("oId"));

			Dto first = new BaseDto("color", "#173177");// first
			first.put("value", "请您支付订单相关费用 :" + mode);

			Dto keyword1 = new BaseDto("color", "#173177");// keyword1
			keyword1.put("value", orderNum);

			Dto keyword2 = new BaseDto("color", "#173177");// keyword2
			keyword2.put("value", mode);

			Dto keyword3 = new BaseDto("color", "#173177");// keyword3
			keyword3.put("value", amount);

			Dto remark = new BaseDto("color", "#173177");// remark
			remark.put("value", "请您尽快支付.支付成功后.我们将为您做好下一步的安排");

			Dto data = new BaseDto();// data 将上面的装入
			data.put("first", first);
			data.put("keyword1", keyword1);
			data.put("keyword2", keyword2);
			data.put("keyword3", keyword3);
			data.put("remark", remark);

			rDto.put("data", data);

			super.getFredaContext().getSmsService().sendSystemSMS("VIP(" + mode + ")", openId, 4, JsonHelper.encodeObject2Json(rDto), 5);

			return true;
		}
		return false;

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean sendConfirmMsg(Dto pDto) {
		pDto.put("isNeedPay", 1);// 需要支付

		pDto.put("payMode", 3);// 支付类目

		pDto.put("payAmount", BILI[2]);// 支付金额

		pDto.put("opInfo", "确认并发送尾款支付请求..");

		if (super.getFredaDao().update("VipOrder.editOrderInfo", pDto) > 0) {
			super.getFredaContext().getLogService().addOpLogForC(pDto.getAsInteger("cId"), pDto.getAsInteger("oStatus"), pDto.getAsInteger("opId"), pDto.getAsString("opName"), pDto.getAsString("opInfo")); // 添加操作日志

			return this.sendPayMsg(pDto);
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean abrogateVipOrder(Dto pDto) {
		pDto.put("nextStatus", 5);// 走向废除状态

		pDto.put("isNeedPay", 0);// 无需要支付

		pDto.put("payMode", 0);// 支付类目

		pDto.put("payAmount", 0);// 支付金额

		if (FredaUtils.isNotEmpty("refund")) {
			pDto.put("opInfo", "定金退款..订单废除..");
		} else {
			pDto.put("opInfo", "订单废除..");
		}
		if (super.getFredaDao().update("VipOrder.editOrderInfo", pDto) > 0) {
			this.sendSmsForDocCancel(pDto);

			super.getFredaContext().getLogService().addOpLogForC(pDto.getAsInteger("cId"), pDto.getAsInteger("oStatus"), pDto.getAsInteger("opId"), pDto.getAsString("opName"), pDto.getAsString("opInfo")); // 添加操作日志

			return true;
		}
		return false;
	}

	@Override
	public boolean addBalance(Dto pDto) {
		if (super.getFredaDao().update("VipOrder.addUserBalance", pDto) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 发送短信 用于Doc取消成功.
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年1月22日 上午8:46:15
	 * @param pDto
	 */
	public void sendSmsForDocCancel(Dto pDto) {
		Dto smsDto = (Dto) super.getFredaDao().queryForObject("VipOrder.getDocSmsInfo", pDto);

		super.getFredaContext().getSmsService().sendSystemSMS(smsDto.getAsString("smsName"), smsDto.getAsString("smsAddr"), 7, smsDto.getAsString("smsKeyWord"), 5);
	}

	/**
	 * 发送短信 用于Doc成功预约
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年1月22日 上午8:48:02
	 * @param pDto
	 */
	public void sendSmsForDocSuccess(Dto pDto) {
		Dto smsDto = (Dto) super.getFredaDao().queryForObject("VipOrder.getDocSmsInfo", pDto);

		super.getFredaContext().getSmsService().sendSystemSMS(smsDto.getAsString("smsName"), smsDto.getAsString("smsAddr"), 6, smsDto.getAsString("smsKeyWord"), 5);
	}

	/**
	 * 发送短信 用于Pat预约成功..
	 * 
	 * @author yiyuan-LiChun
	 * @date 2016年1月22日 上午8:48:20
	 * @param pDto
	 */
	public void sendSmsForPatSuccess(Dto pDto) {
		Object rDto=super.getFredaDao().queryForObject("VipOrder.getPatSmsInfo", pDto);
		if(rDto!=null){
			Dto smsDto = (Dto) rDto;
			super.getFredaContext().getSmsService().sendSystemSMS(smsDto.getAsString("smsName"), smsDto.getAsString("smsAddr"), 5, smsDto.getAsString("smsKeyWord"), 5);
		}
	}
}
