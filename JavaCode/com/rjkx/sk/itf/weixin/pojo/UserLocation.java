package com.rjkx.sk.itf.weixin.pojo;

public class UserLocation {
	//纬度
	private String latitude;
	//经度
	private String longitude;
	//精度
	private String precision;
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getPrecision() {
		return precision;
	}
	public void setPrecision(String precision) {
		this.precision = precision;
	}
	
}
