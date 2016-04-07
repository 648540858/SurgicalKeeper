package com.rjkx.sk.itf.weixin.pojo;

public class Media {
	//文件类型
	private String type;
	//媒体id
	private String mediaId;
	//上传时间
	private int createAt;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public int getCreateAt() {
		return createAt;
	}
	public void setCreateAt(int createAt) {
		this.createAt = createAt;
	}
	
}
