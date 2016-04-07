package com.rjkx.sk.itf.app.pojo;

public class ErrorMsg {
	private int code;// 错误代码
	private String msg;// 错误消息

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
		
		if (code == 0) {
			this.msg = "成功";
		}
		if (code == -4) {
			this.msg = "参数传送错误";
		}
		if (code == -5) {
			this.msg = "用户名或密码错误";
		}
		if (code == -6) {
			this.msg = "鉴权失败";
		}
		if (code == -10) {
			this.msg = "无数据";
		}
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString(){
		return "code:"+code+",msg:"+msg;
	}
}
