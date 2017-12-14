package com.sys.volunteer.vo;

import java.util.Date;

public class GMMail {

	private String id;
	private String topic;
	private Integer type;
	private String sendUserId;
	private String sendUserName;
	private String content;
	private Date sendDatetime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getSendUserId() {
		return sendUserId;
	}
	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}
	public String getSendUserName() {
		return sendUserName;
	}
	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSendDatetime() {
		return sendDatetime;
	}
	public void setSendDatetime(Date sendDatetime) {
		this.sendDatetime = sendDatetime;
	}
}
