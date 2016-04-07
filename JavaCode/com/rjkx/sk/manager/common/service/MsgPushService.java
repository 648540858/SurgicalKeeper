package com.rjkx.sk.manager.common.service;

import com.rjkx.sk.system.datastructure.Dto;

public interface MsgPushService {
	/***
	 * 推送消息
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年3月15日 上午9:21:41
	  * @param pDto(toid,clientid,tagname,title,msg)
	 */
	void pushMessage(Dto pDto);
}
