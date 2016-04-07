package com.rjkx.sk.manager.cms.service.impl;

import org.springframework.stereotype.Service;

import com.rjkx.sk.manager.base.ManagerBaseServiceImpl;
import com.rjkx.sk.manager.cms.service.GroupService;
import com.rjkx.sk.manager.common.context.FredaContext;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.datastructure.impl.BaseDto;
import com.rjkx.sk.system.json.JsonHelper;

@Service(value = "groupServiceImpl")
public class GroupServiceImpl extends ManagerBaseServiceImpl implements GroupService {

	@SuppressWarnings("unchecked")
	@Override
	public void addGroup(Dto pDto) throws Exception {
		if(super.getFredaDao().insert("group.addGroup", pDto)>=1){
			//添加推送消息
			Dto data=new BaseDto();
			if(pDto.getAsInteger("msgtype")==1){//话题
				data.put("msgtype", 2);
			}
			if(pDto.getAsInteger("msgtype")==2){//文章
				data.put("msgtype", 3);
			}
			if(pDto.getAsInteger("msgtype")==3){//视频
				data.put("msgtype", 4);
			}
			if(pDto.getAsInteger("msgtype")==4){//学术
				data.put("msgtype", 5);
			}
			int nId = pDto.getAsInteger("id");
			data.put("title", nId);
			
			Dto tDto=new BaseDto();
//			tDto.put("toid",0);//用户id
//			tDto.put("totype", 3);//医生，如为患者：4
			tDto.put("clientid", "");
			tDto.put("tagname", "tag_usertype_3");//医生群推
			tDto.put("title", "新圈子内容");
			tDto.put("msg", JsonHelper.encodeObject2Json(data));
			FredaContext.getInstance().getMsgPushService().pushMessage(tDto);
		} else{
			throw new Exception();
		};
		
		
	}

	@Override
	public void editAd(Dto pDto) throws Exception {
		super.getFredaDao().update("group.editGroup", pDto);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deletAd(Dto pDto) throws Exception {
	
		String[] ids = pDto.getAsString("ids").split(",");
		
		for(String id : ids){
			pDto.put("nId", id);
			if(super.getFredaDao().delete("group.deleteGroup", pDto)>0){
				super.getFredaDao().delete("group.deleteReply", pDto);
				super.getFredaDao().delete("group.deleteFav", pDto);
				super.getFredaDao().delete("group.deleteZan", pDto);
			}else{
				throw new Exception();
			};
		}
	}

	@Override
	public void editReply(Dto pDto) throws Exception {
		
		if(super.getFredaDao().update("group.editReply", pDto)<1){
			throw new Exception();
		};
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteReplyById(Dto pDto) throws Exception {
		String[] ids_reply = pDto.getAsString("ids_reply").split(",");
		
		for(String id : ids_reply){
			pDto.put("rId", id);
			if(super.getFredaDao().delete("group.deleteReplyById", pDto)<0){
				throw new Exception();
			};
		}
		
	}

}
