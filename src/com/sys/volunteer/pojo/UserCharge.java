package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

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

/**
 * Useraccountdealdetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_charge", catalog = "mprs")
public class UserCharge implements java.io.Serializable {

	// Fields

	private Integer seqno;
	private Users users;
	private String userName;
	private String terminalNo;
	private Supply supply;
	private String supplyName;
	private Integer voucherType;
	private String voucherCode;
	private Mainorder mainorder;
	private Double balance;
	private Double dealMoney;
	private Double currentBalance;
	private Double commission;
	private Double dealCommission;
	private Double currentCommission;
	private Date createTime;
	private String remark;
	private Integer flag;
	private Integer logFor;
	private String mobile;

	private Users recodeUsers;
	private String recodeUserName;
	private String recodeOperator;
    private double itemPrice;
	// Constructors

	/** default constructor */
	public UserCharge() {
	}

	/** minimal constructor */
	public UserCharge(Users users, Double dealMoney, Integer flag) {
		this.users = users;
		this.dealMoney = dealMoney;
		this.flag = flag;
	}

	/** full constructor */
	public UserCharge(Users users, Double balance, Double dealMoney,
			Date createTime, String remark, Integer flag) {
		this.users = users;
		this.balance = balance;
		this.dealMoney = dealMoney;
		this.createTime = createTime;
		this.remark = remark;
		this.flag = flag;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "seqno", unique = true, nullable = false)
	public Integer getSeqno() {
		return this.seqno;
	}

	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "balance", precision = 20, scale = 2)
	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Column(name = "dealMoney", nullable = false, precision = 20, scale = 2)
	public Double getDealMoney() {
		return this.dealMoney;
	}

	public void setDealMoney(Double dealMoney) {
		this.dealMoney = dealMoney;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "remark", length = 100)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "flag", nullable = false)
	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mainOrderId")
	public Mainorder getMainorder() {
		return mainorder;
	}

	public void setMainorder(Mainorder mainorder) {
		this.mainorder = mainorder;
	}

	@Column(name = "current_balance", nullable = false, precision = 20, scale = 2)
	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supply_id")
	public Supply getSupply() {
		return supply;
	}

	public void setSupply(Supply supply) {
		this.supply = supply;
	}

	@Column(name="supply_name")
	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	@Column(name="voucher_type")
	public Integer getVoucherType() {
		return voucherType;
	}

	public void setVoucherType(Integer voucherType) {
		this.voucherType = voucherType;
	}

	@Column(name="voucher_code")
	public String getVoucherCode() {
		return voucherCode;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}

	@Column(name="log_for")
	public Integer getLogFor() {
		return logFor;
	}

	public void setLogFor(Integer logFor) {
		this.logFor = logFor;
	}

	@Column(name="terminal_no")
	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	@Column(name="mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "commission", precision = 10, scale = 2)
	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	@Column(name = "dealCommission", precision = 20, scale = 2)
	public Double getDealCommission() {
		return dealCommission;
	}

	public void setDealCommission(Double dealCommission) {
		this.dealCommission = dealCommission;
	}

	@Column(name = "current_commission", precision = 20, scale = 2)
	public Double getCurrentCommission() {
		return currentCommission;
	}

	public void setCurrentCommission(Double currentCommission) {
		this.currentCommission = currentCommission;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recode_user_id")
	public Users getRecodeUsers() {
		return recodeUsers;
	}

	public void setRecodeUsers(Users recodeUsers) {
		this.recodeUsers = recodeUsers;
	}

	@Column(name="recode_username")
	public String getRecodeUserName() {
		return recodeUserName;
	}

	public void setRecodeUserName(String recodeUserName) {
		this.recodeUserName = recodeUserName;
	}

	@Column(name="recode_Operator")
	public String getRecodeOperator() {
		return recodeOperator;
	}

	public void setRecodeOperator(String recodeOperator) {
		this.recodeOperator = recodeOperator;
	}

	@Column(name="item_price")
	public double getItemPrice() {
		double cb = 0;
		double cc = 0;
		if (currentBalance!=null) {
			cb = currentBalance;
		}
		if (currentCommission!=null) {
			cc = currentCommission;
		}
		return cb + cc;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
}