package com.rjkx.sk.itf.weixin.pojo;

public class BaseMenu {
	// 按钮菜单
	public static final String BUTTON_TYPE = "click";
	// 网页菜单
	public static final String VIEW_TYPE = "view";
	
	// 菜单名称
	private String name;
	// 类型
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
