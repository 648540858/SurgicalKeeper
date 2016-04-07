package com.rjkx.sk.itf.common.util;

public enum ClientType {
	WX(1),ANDROID(2),IOS(3);
	
	private int value;
	
	private ClientType(int value){
		this.value=value;
	}
	
	public int getValue(){
		return value;
	}
}
