package com.rjkx.sk.itf.weixin.pojo;

public class QRCodeEvent extends BaseEvent {
	//事件key值
	private String EventKey;
	//用于换取二维码的ticket
	private String Ticket;
	
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	public String getTicket() {
		return Ticket;
	}
	public void setTicket(String ticket) {
		Ticket = ticket;
	}
	
}
