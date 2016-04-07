package com.rjkx.sk.itf.app.service;

import com.rjkx.sk.system.datastructure.Dto;

public interface AppItfService {
	//注册AppClinet
	public void appRegClient(Dto pDto);
	//注销AppClient
	public void appUnRegClient(Dto pDto);
	//更新头像
	public void updateHeadPhoto(Dto pDto);
	//添加意见反馈
	public void addFeedBack(Dto pDto);
	//删除收藏
	public int delFavNews(Dto pDto);
	//删除圈子内容
	public int delNews(Dto pDto);
	//圈子点赞
	public void addNewsZan(Dto pDto);	
	//圈子点收藏
	public void addNewsFav(Dto pDto);
	//修改圈子内容
	public void updateNews(Dto pDto);
	//添加圈子内容
	public void addNews(Dto pDto);
	//添加圈子阅读量
	public long addNewsRead(Dto pDto);
	//添加圈子回复
	public void addNewsReply(Dto pDto);
	//删除圈子回复
	public int delNewsReply(Dto pDto);
}
