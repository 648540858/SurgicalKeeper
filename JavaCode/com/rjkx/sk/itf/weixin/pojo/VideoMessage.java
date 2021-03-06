package com.rjkx.sk.itf.weixin.pojo;

public class VideoMessage {
	//接收方OpenID
	private String ToUserName;
	//开发者微信号
	private String FromUserName;
	//消息创建时间
	private long CreateTime;
	//消息类型
	private String MsgType;
	//视频
	private Video Video;
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public Video getVideo() {
		return Video;
	}
	public void setVideo(Video video) {
		Video = video;
	}
	
}
