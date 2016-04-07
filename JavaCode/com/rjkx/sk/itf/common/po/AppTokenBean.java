package com.rjkx.sk.itf.common.po;

import java.io.Serializable;

/**
 * TOKEN BEAN
 * 
 * @ClassName: AppTokenBean
 * @Description:
 * @author yiyuan-Rally
 * @date 2015年11月24日 下午3:37:43
 * @version V1.0
 */
public class AppTokenBean implements Serializable {

	 /**
	  * @Fields serialVersionUID :
	  */
	private static final long serialVersionUID = 1L;

	public AppTokenBean(String appToken, String timeStamp, String ipAddr, int type, String imeiStr) {
		this.appToken = appToken;
		this.timeStamp = timeStamp;
		this.ipAddr = ipAddr;
		this.type = type;
		this.imeiStr = imeiStr;
	}

	/**
	 * TOKEN
	 */
	private String appToken;
	/**
	 * 时间戳
	 */
	private String timeStamp;
	/**
	 * IP
	 */
	private String ipAddr;
	/**
	 * 类型
	 */
	private int type;
	/**
	 * Imei
	 */
	private String imeiStr;

	public String getAppToken() {
		return appToken;
	}

	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getImeiStr() {
		return imeiStr;
	}

	public void setImeiStr(String imeiStr) {
		this.imeiStr = imeiStr;
	}

}
