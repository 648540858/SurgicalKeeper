package com.rjkx.sk.manager.basedata.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rjkx.sk.manager.base.ManagerBaseServiceImpl;
import com.rjkx.sk.manager.basedata.service.DoctorService;
import com.rjkx.sk.manager.common.context.FredaContext;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.datastructure.impl.BaseDto;
import com.rjkx.sk.system.json.JsonHelper;

@Service(value = "doctorV2ServiceImpl")
public class DoctorServiceImpl extends ManagerBaseServiceImpl implements DoctorService {

	@Override
	public void addDoctor(Dto pDto) {
		super.getFredaDao().insert("DoctorV2.addDoctor", pDto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void editDoctor(Dto pDto) {
		super.getFredaDao().update("DoctorV2.editDoctor", pDto);
		//添加推送消息
		Dto data=new BaseDto();
		data.put("msgtype", 99);
		data.put("title", pDto.getAsString("docIcon"));//头像地址
		
		Dto tDto=new BaseDto();
		tDto.put("toid",pDto.getAsLong("docId"));//用户id
		tDto.put("totype", 3);//医生，如为患者：4
		tDto.put("clientid", "");
		tDto.put("tagname", "tag_userid_"+pDto.getAsLong("docId"));//医生
		tDto.put("title", "新头像");
		tDto.put("msg", JsonHelper.encodeObject2Json(data));
		FredaContext.getInstance().getMsgPushService().pushMessage(tDto);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delDoctor(Dto pDto) {
		String[] ids = pDto.getAsString("ids").split(",");

		for (String id : ids) {
			pDto.put("docId", id);

			super.getFredaDao().delete("DoctorV2.delDoctor", pDto);
			super.getFredaDao().delete("DoctorV2.delDoctorSickness", pDto);
			
			//添加推送消息
			Dto data=new BaseDto();
			data.put("msgtype", 0);
			data.put("title", "exit");
			
			Dto tDto=new BaseDto();
			tDto.put("toid",pDto.getAsLong("docId"));//用户id
			tDto.put("totype", 3);//医生，如为患者：4
			tDto.put("clientid", "");
			tDto.put("tagname", "tag_userid_"+pDto.getAsLong("docId"));//医生
			tDto.put("title", "强制退出");
			tDto.put("msg", JsonHelper.encodeObject2Json(data));
			FredaContext.getInstance().getMsgPushService().pushMessage(tDto);
		}
	}

	@Override
	public void editDoctorQrcode(Dto pDto) {
		super.getFredaDao().update("DoctorV2.editDoctorQrcode", pDto);
	}

}
