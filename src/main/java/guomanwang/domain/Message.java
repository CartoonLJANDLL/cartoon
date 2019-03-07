package guomanwang.domain;

import java.util.Date;

public class Message {
	private int id;
	private String type;
	private int senderid;
	private String username;
	private int receiverid;
	private String content;
	//发送方状态
	private int sender_status;
	//接收方状态
	private int receiver_status;
	private Date time;
	public int getId() {
		return id;
	} 
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSenderid() {
		return senderid;
	}
	public void setSenderid(int senderid) {
		this.senderid = senderid;
	}
	public int getReceiverid() {
		return receiverid;
	}
	public void setReceiverid(int receiverid) {
		this.receiverid = receiverid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus_status() {
		return sender_status;
	}
	public void setSender_status(int sender_status) {
		this.sender_status = sender_status;
	}
	public String getTime() {
		if(time!=null) {
			return TimeTransformUtil.timetransform(time);
		}
		return null;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getReceiver_status() {
		return receiver_status;
	}
	public void setReceiver_status(int receiver_status) {
		this.receiver_status = receiver_status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
