package com.rjkx.sk.manager.common.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rjkx.sk.manager.base.ManagerBaseServiceImpl;
import com.rjkx.sk.manager.common.service.MsgPushService;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.utils.FredaUtils;

@Service(value="msgPushServiceImpl")
public class MsgPushServiceImpl extends ManagerBaseServiceImpl implements MsgPushService {

	@SuppressWarnings("unchecked")
	@Override
	public void pushMessage(Dto pDto) {
		if(FredaUtils.isNotEmpty(pDto.getAsString("clientid"))){//按clientid进行推送
			List<?> data=super.getFredaDao().queryForList("MsgPush.queryClientid", pDto);
			if(data.size()<1)
				return;
			pDto.remove("clientid");
			
			int c=data.size();
			for(int i=0;i<c;i++){
				Dto rdto=(Dto)data.get(i);
				pDto.put("clientid", rdto.getAsString("clientid"));
				pDto.put("clienttype", rdto.getAsInteger("clienttype"));
				super.getFredaDao().insert("MsgPush.addPushMsg",pDto);
			}
			return;
		}
		
		if(FredaUtils.isNotEmpty(pDto.get("toid"))){//按toid进行推送
			if(FredaUtils.isEmpty(pDto.get("totype"))){//无totype
				return;
			}
			List<?> data=super.getFredaDao().queryForList("MsgPush.queryUserClientid", pDto);
			if(data.size()<1)
				return;
			pDto.remove("clientid");
			
			int c=data.size();
			for(int i=0;i<c;i++){
				Dto rdto=(Dto)data.get(i);
				pDto.put("clientid", rdto.getAsString("clientid"));
				pDto.put("clienttype", rdto.getAsInteger("clienttype"));
				super.getFredaDao().insert("MsgPush.addPushMsg",pDto);
			}
			return;
		}
		
		if(FredaUtils.isNotEmpty(pDto.getAsString("tagname"))){//按tagname进行推送
			if(pDto.getAsString("tagname").indexOf("tag_usertype")>-1){//按用户类型推送
				pDto.put("clienttype", 0);
				super.getFredaDao().insert("MsgPush.addPushMsg",pDto);
			}
			return;
		}
	}

}
