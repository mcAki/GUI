package com.sys.volunteer.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "users", catalog = "mprs")
public class Users implements java.io.Serializable {

	private String userId;
	private Users parentUsers;
	private Usergroup usergroup;
	private String loginName;
	private String userPassword;
	private String userCode;
	private String userName;
	private String idcardCode;
	/**
	 * 手机验证码
	 * 验证码的有效时间
	 */
	private String verificationCode;
	private Date validTime;	
	/**
	 * 联系人
	 */
	private String contactPeople;
	/**
	 * 法人代表
	 */
	private String corporateRepresentative;
	private String email;
	private String address;
	private String department;
	private String position;
	private UserLevel userLevel;
	private Integer defaultAlarm;
	private Integer state;
	private String remark;
	private Integer gender;
	private String mobile;
	private Date createDate;
	private Integer userType;
	private Integer tradeType;
	private String terminalNo;
	private Useraccount useraccount;
	private Integer terminalCount;
	private OTP otp;
	private Integer reversalCount;
	private Integer maxReversalCount;
	
	private String recodeOperator;//记录操作员
	
	private Integer readProtocolResult;
	private Integer isShowRecharge;
 
	private String businessPassword;
	
	private String qq;
	
	private Integer isShowSupply;

	// Constructors

	



	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(String userId, String loginName) {
		this.userId = userId;
		this.loginName = loginName;
	}

	public Users(String userId) {
		super();
		this.userId = userId;
	}

	
	public Users(Date createDate, String department, String email, Integer gender,
			String loginName,
			String mobile, String remark, Integer state, String userCode,
			String userId, String userName, String userPassword,
			Usergroup usergroup) {
		super();
		this.createDate = createDate;
		this.department = department;
		this.email = email;
		this.gender = gender;
		this.loginName = loginName;
		this.mobile = mobile;
		this.remark = remark;
		this.state = state;
		this.userCode = userCode;
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.usergroup = usergroup;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "user_id", unique = true, nullable = false, length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	public Usergroup getUsergroup() {
		return this.usergroup;
	}

	public void setUsergroup(Usergroup usergroup) {
		this.usergroup = usergroup;
	}

	@Column(name = "login_name", length = 50)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "user_password", length = 50)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "user_code", length = 20)
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "user_name", length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "department", length = 32)
	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "remark", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "gender")
	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@Column(name = "mobile", length = 50)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", length = 0)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "userType")
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_user_id")
	public Users getParentUsers() {
		return parentUsers;
	}

	public void setParentUsers(Users parentUsers) {
		this.parentUsers = parentUsers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_level")
	public UserLevel getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(UserLevel userLevel) {
		this.userLevel = userLevel;
	}

	@Column(name="default_alarm")
	public Integer getDefaultAlarm() {
		return defaultAlarm;
	}

	public void setDefaultAlarm(Integer defaultAlarm) {
		this.defaultAlarm = defaultAlarm;
	}

	@Column(name="contact_people")
	public String getContactPeople() {
		return contactPeople;
	}

	public void setContactPeople(String contactPeople) {
		this.contactPeople = contactPeople;
	}

	@Column(name="corporate_representative")
	public String getCorporateRepresentative() {
		return corporateRepresentative;
	}

	public void setCorporateRepresentative(String corporateRepresentative) {
		this.corporateRepresentative = corporateRepresentative;
	}

	@Column(name="trade_type")
	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	@Column(name="terminal_no")
	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	@Column(name="idcard_code")
	public String getIdcardCode() {
		return idcardCode;
	}

	public void setIdcardCode(String idcardCode) {
		this.idcardCode = idcardCode;
	}

	@Column(name="address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="useraccount_id")
	public Useraccount getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(Useraccount useraccount) {
		this.useraccount = useraccount;
	}

	@Column(name="terminalCount")
	public Integer getTerminalCount() {
		return terminalCount;
	}

	public void setTerminalCount(Integer terminalCount) {
		this.terminalCount = terminalCount;
	}

	@Column(name="position")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="key_id")
	public OTP getOtp() {
		return otp;
	}

	public void setOtp(OTP otp) {
		this.otp = otp;
	}

	@Column(name="reversal_count")
	public Integer getReversalCount() {
		return reversalCount;
	}

	public void setReversalCount(Integer reversalCount) {
		this.reversalCount = reversalCount;
	}

	@Column(name="max_reversal_count")
	public Integer getMaxReversalCount() {
		return maxReversalCount;
	}

	public void setMaxReversalCount(Integer maxReversalCount) {
		this.maxReversalCount = maxReversalCount;
	}

	@Column(name="verificationCode")
	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "validTime", length = 0)
	public Date getValidTime() {
		return validTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}

	@Column(name="recode_Operator")
	public String getRecodeOperator() {
		return recodeOperator;
	}

	public void setRecodeOperator(String recodeOperator) {
		this.recodeOperator = recodeOperator;
	}

	@Column(name="read_protocol_result")
	public Integer getReadProtocolResult() {
		return readProtocolResult;
	}

	public void setReadProtocolResult(Integer readProtocolResult) {
		this.readProtocolResult = readProtocolResult;
	}

	@Column(name="is_show_recharge")
	public Integer getIsShowRecharge() {
		return isShowRecharge;
	}

	public void setIsShowRecharge(Integer isShowRecharge) {
		this.isShowRecharge = isShowRecharge;
	}
	
	@Column(name = "business_password", length = 50)
	public String getBusinessPassword() {
		return businessPassword;
	}

	public void setBusinessPassword(String businessPassword) {
		this.businessPassword = businessPassword;
	}
	
	@Column(name = "QQ")
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
	@Column(name="isShowSupply")
	public Integer getIsShowSupply() {
		return isShowSupply;
	}

	public void setIsShowSupply(Integer isShowSupply) {
		this.isShowSupply = isShowSupply;
	}
}