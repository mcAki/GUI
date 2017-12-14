package com.sys.volunteer.vo;

import java.util.Date;

public class GmUserLevelVo {

	private String id;
	private String playerId;
	private String username;
	private Date createTime;
	private Integer currentLevel;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPlayerId() {
		return playerId;
	}
	
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getCurrentLevel() {
		return currentLevel;
	}
	
	public void setCurrentLevel(Integer currentLevel) {
		this.currentLevel = currentLevel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
