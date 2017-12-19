package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringUtils;

/**
 * Useraccount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pos_sign_in", catalog = "mprs")
public class PosSignIn implements java.io.Serializable {

	// Fields

	private Integer id;
	private String versionCode;
	private String msgType;
	private String userId;
	private String signInTime;
	private String lastSignInTime;
	private String keyLength;
	private String keyMethod;
	private String key;
	private String respTime;
	private String srcMsg;
	private String[] msgs;

	// Constructors

	/** default constructor */
	public PosSignIn() {
	}
	
	/** full constructor */
	public PosSignIn(Integer id, String versionCode, String msgType,
			String userId, String signInTime, String lastSignInTime,
			String keyLength, String keyMethod, String key, String respTime,
			String srcMsg) {
		super();
		this.id = id;
		this.versionCode = versionCode;
		this.msgType = msgType;
		this.userId = userId;
		this.signInTime = signInTime;
		this.lastSignInTime = lastSignInTime;
		this.keyLength = keyLength;
		this.keyMethod = keyMethod;
		this.key = key;
		this.respTime = respTime;
		this.srcMsg = srcMsg;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false, length = 11)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="version_code")
	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	@Column(name="msg_type")
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name="src_msg")
	public String getSrcMsg() {
		return srcMsg;
	}

	public void setSrcMsg(String srcMsg) {
		this.srcMsg = srcMsg;
	}
	
	@Column(name="sign_in_time")
	public String getSignInTime() {
		return signInTime;
	}

	public void setSignInTime(String signInTime) {
		this.signInTime = signInTime;
	}

	@Column(name="last_sign_in_time")
	public String getLastSignInTime() {
		return lastSignInTime;
	}

	public void setLastSignInTime(String lastSignInTime) {
		this.lastSignInTime = lastSignInTime;
	}

	@Column(name="key_length")
	public String getKeyLength() {
		return keyLength;
	}

	public void setKeyLength(String keyLength) {
		this.keyLength = keyLength;
	}

	@Column(name="key_method")
	public String getKeyMethod() {
		return keyMethod;
	}

	public void setKeyMethod(String keyMethod) {
		this.keyMethod = keyMethod;
	}

	@Column(name="[key]")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name="resp_time")
	public String getRespTime() {
		return respTime;
	}

	public void setRespTime(String respTime) {
		this.respTime = respTime;
	}

	public PosSignIn(String[] msgs){
		this.versionCode = msgs[0];
		this.msgType = msgs[1];
		this.userId = msgs[2];
		this.signInTime = msgs[3];
		this.lastSignInTime = msgs[4];
		this.keyLength = msgs[5];
		this.keyMethod = msgs[6];
		this.key = msgs[7];
		this.respTime = msgs[8];
		this.srcMsg = StringUtils.join(msgs,"|");
		this.msgs = msgs;
	}
	
	@Override
	public String toString() {
		msgs[0] = this.versionCode ;
		msgs[1] = this.msgType;
		msgs[2] = this.userId;
		msgs[3] = this.signInTime;
		msgs[4] = this.lastSignInTime;
		msgs[5] = this.keyLength;
		msgs[6] = this.keyMethod;
		msgs[7] = this.key;
		msgs[8] = this.respTime;
		System.out.println(StringUtils.join(msgs,"|"));
		return StringUtils.join(msgs,"|");
	}

	
}