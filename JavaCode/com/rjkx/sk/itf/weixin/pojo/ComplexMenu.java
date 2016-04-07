package com.rjkx.sk.itf.weixin.pojo;

public class ComplexMenu extends BaseMenu {
	private BaseMenu[] sub_button;

	public BaseMenu[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(BaseMenu[] sub_button) {
		this.sub_button = sub_button;
	}
	
}
