package com.sys.volunteer.vo;

import java.sql.Timestamp;
import java.util.Date;

public class UsersBean implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int missionId;
	private String uuid;
	private String realname;
	private String idcardCode;
	private int gender;
	private String mobile;
	private Date createDate;
	private Timestamp selectedDatetime;
	public int getMissionId() {
		return missionId;
	}

	public void setMissionId(int missionId) {
		this.missionId = missionId;
	}

	private int selection;

	public Timestamp getSelectedDatetime() {
		return selectedDatetime;
	}

	public void setSelectedDatetime(Timestamp selectedDatetime) {
		this.selectedDatetime = selectedDatetime;
	}

	public int getSelection() {
		return selection;
	}

	public void setSelection(int selection) {
		this.selection = selection;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getIdcardCode() {
		return idcardCode;
	}

	public void setIdcardCode(String idcardCode) {
		this.idcardCode = idcardCode;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
