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
@Table(name = "pos_msg", catalog = "mprs")
public class PosMsg implements java.io.Serializable {

	// Fields

	private Integer id;
	private String versionCode;
	private String msgType;
	private String funcCode;
	private String mobile;
	private String businessCode;
	private String value;
	private String money;
	private String bankCardcode;
	private String userId;
	private String terminalNo;
	private String batch;
	private String store;
	private String authCode;
	private String retCode;
	private String cardCode;
	private String cardPwd;
	private String storeCode;
	private String orderId;
	private String msgMAC;
	private Date createTime;
	private String srcMsg;
	private String srcMsgEx;
	private String mainorder;
	private String[] msgs;

	// Constructors

	/** default constructor */
	public PosMsg() {
	}

	public PosMsg(Integer id, String versionCode, String msgType,
			String funcCode, String mobile, String businessCode, String money,
			String bankCardcode, String userId, String terminalNo,
			String batch, String store, String authCode, String retCode,
			String cardCode, String cardPwd, String storeCode, String orderId, String msgMAC,
			Date createTime, String srcMsg) {
		super();
		this.id = id;
		this.versionCode = versionCode;
		this.msgType = msgType;
		this.funcCode = funcCode;
		this.mobile = mobile;
		this.businessCode = businessCode;
		this.money = money;
		this.bankCardcode = bankCardcode;
		this.userId = userId;
		this.terminalNo = terminalNo;
		this.batch = batch;
		this.store = store;
		this.authCode = authCode;
		this.retCode = retCode;
		this.cardCode = cardCode;
		this.cardPwd = cardPwd;
		this.storeCode = storeCode;
		this.orderId = orderId;
		this.msgMAC = msgMAC;
		this.createTime = createTime;
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
	
	@Column(name="func_code")
	public String getFuncCode() {
		return funcCode;
	}

	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}

	@Column(name="mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name="business_code")
	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	@Column(name="[value]")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Column(name="money")
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	@Column(name="bank_cardcode")
	public String getBankCardcode() {
		return bankCardcode;
	}

	public void setBankCardcode(String bankCardcode) {
		this.bankCardcode = bankCardcode;
	}

	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name="terminal_no")
	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	@Column(name="batch")
	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	@Column(name="store")
	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	@Column(name="auth_code")
	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	@Column(name="ret_code")
	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	@Column(name="card_code")
	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	@Column(name="card_pwd")
	public String getCardPwd() {
		return cardPwd;
	}

	public void setCardPwd(String cardPwd) {
		this.cardPwd = cardPwd;
	}

	@Column(name="store_code")
	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	
	@Column(name="order_id")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name="msg_mac")
	public String getMsgMAC() {
		return msgMAC;
	}

	public void setMsgMAC(String msgMAC) {
		this.msgMAC = msgMAC;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 0)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="mainorder")
	public String getMainorder() {
		return mainorder;
	}

	public void setMainorder(String mainorder) {
		this.mainorder = mainorder;
	}

	@Column(name="src_msg")
	public String getSrcMsg() {
		return srcMsg;
	}

	public void setSrcMsg(String srcMsg) {
		this.srcMsg = srcMsg;
	}

	@Column(name="src_msg_ex")
	public String getSrcMsgEx() {
		return srcMsgEx;
	}

	public void setSrcMsgEx(String srcMsgEx) {
		this.srcMsgEx = srcMsgEx;
	}
	
	public PosMsg(String[] msgs,int count){
		this.versionCode = msgs[0];
		this.msgType = msgs[1];
		this.funcCode = msgs[2];
		this.mobile = msgs[3];
		this.orderId = msgs[4];
		this.businessCode = msgs[5];
		this.money = msgs[6];
		this.bankCardcode = msgs[7];
		this.userId = msgs[8];
		this.terminalNo = msgs[9];
		this.batch = msgs[10];
		this.store = msgs[11];
		this.authCode = msgs[12];
		this.retCode = msgs[13];
		this.cardCode = msgs[14];
		this.cardPwd = msgs[15];
		this.storeCode = msgs[16];
		this.msgMAC = msgs[17];
		this.createTime = new Date();
		switch (count) {
		case 1:
			this.value = money;
			this.srcMsg = StringUtils.join(msgs,"|");
			break;
		case 2:
			this.srcMsgEx = StringUtils.join(msgs,"|");
		}
		
		this.msgs = msgs;
	}
	
	@Override
	public String toString() {
		msgs[0] = this.versionCode ;
		msgs[1] = this.msgType;
		msgs[2] = this.funcCode;
		msgs[3] = this.mobile;
		msgs[4] = this.orderId;
		msgs[5] = this.businessCode;
		msgs[6] = this.money;
		msgs[7] = this.bankCardcode;
		msgs[8] = this.userId;
		msgs[9] = this.terminalNo;
		msgs[10] = this.batch;
		msgs[11] = this.store;
		msgs[12] = this.authCode;
		msgs[13] = this.retCode;
		msgs[14] = this.cardCode;
		msgs[15] = this.cardPwd;
		msgs[16] = this.storeCode;
		msgs[17] = this.msgMAC;
		System.out.println(StringUtils.join(msgs,"|"));
		return StringUtils.join(msgs,"|");
	}


}