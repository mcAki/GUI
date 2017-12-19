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

import org.hibernate.annotations.GenericGenerator;

/**
 * Mainorder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "operationLog", catalog = "mprs")
public class OperationLog implements java.io.Serializable {

	// Fields

	private String operationId;
	private Supply supply;
	private String supplyName;
	private Mainorder mainorder;
	private Date createTime;
	private Integer amount;
	private String mobileNum;
	private Integer type;
	private Integer result;
	private Users users;
	private String userName;
	private String terminalNo;
	private Double subtotal;
	private Goods goods;
	private String goodsName;
	private Integer goodsValue;
	private Integer isTerminal;
	private String loginIp;

	// Constructors

	/** default constructor */
	public OperationLog() {
	}

	/** full constructor */
	public OperationLog(String operationId, Supply supply, String supplyName,
			Mainorder mainorder, Date createTime) {
		super();
		this.operationId = operationId;
		this.supply = supply;
		this.supplyName = supplyName;
		this.mainorder = mainorder;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "operation_id", unique = true, nullable = false, length = 32)
	public String getOperationId() {
		return this.operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supply_id")
	public Supply getSupply() {
		return this.supply;
	}

	public void setSupply(Supply supply) {
		this.supply = supply;
	}

	@Column(name = "supplyName", length = 100)
	public String getSupplyName() {
		return this.supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mainOrderId")
	public Mainorder getMainorder() {
		return this.mainorder;
	}

	public void setMainorder(Mainorder mainorder) {
		this.mainorder = mainorder;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime", nullable = false, length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "amount")
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Column(name = "mobileNum")
	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "userName", length = 32)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "terminal_no")
	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	@Column(name = "subtotal", precision = 10, scale = 2)
	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goods_id")
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@Column(name = "goods_name")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "goods_value")
	public Integer getGoodsValue() {
		return goodsValue;
	}

	public void setGoodsValue(Integer goodsValue) {
		this.goodsValue = goodsValue;
	}

	@Column(name = "is_terminal")
	public Integer getIsTerminal() {
		return isTerminal;
	}

	public void setIsTerminal(Integer isTerminal) {
		this.isTerminal = isTerminal;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "result")
	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	@Column(name="login_ip")
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

}