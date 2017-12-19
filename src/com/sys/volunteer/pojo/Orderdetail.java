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
 * Orderdetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "orderdetail", catalog = "mprs")
public class Orderdetail implements java.io.Serializable {

	// Fields

	private String orderDetailId;
	private Mainorder mainorder;
	private Users users;
	private Integer state;
	private Double money;
	private String desc;
	private Date createTime;
	private String dealResultDesc;
	private Supply supply;
	private String supplyName;
	private String sign;
	private String mobile;
	private String cardIds;
	private Integer cardNo;
	private Goods goods;
	private String terminalNo;
	private Integer reversalState;

	// Constructors

	/** default constructor */
	public Orderdetail() {
	}

	/** minimal constructor */
	public Orderdetail(String orderDetailId, Mainorder mainorder, Users users,
			 Integer state, Date createTime) {
		this.orderDetailId = orderDetailId;
		this.mainorder = mainorder;
		this.users = users;
		this.state = state;
		this.createTime = createTime;
	}

	/** full constructor */
	public Orderdetail(String orderDetailId, Mainorder mainorder, Users users,
			Integer state, String desc, Date createTime,
			String dealResultDesc) {
		this.orderDetailId = orderDetailId;
		this.mainorder = mainorder;
		this.users = users;
		this.state = state;
		this.desc = desc;
		this.createTime = createTime;
		this.dealResultDesc = dealResultDesc;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "orderDetailId", unique = true, nullable = false, length = 32)
	public String getOrderDetailId() {
		return this.orderDetailId;
	}

	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mainOrderId", nullable = false)
	public Mainorder getMainorder() {
		return this.mainorder;
	}

	public void setMainorder(Mainorder mainorder) {
		this.mainorder = mainorder;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}


	@Column(name = "state", nullable = false)
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "[desc]", length = 32)
	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime", nullable = false, length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "dealResultDesc", length = 100)
	public String getDealResultDesc() {
		return this.dealResultDesc;
	}

	public void setDealResultDesc(String dealResultDesc) {
		this.dealResultDesc = dealResultDesc;
	}

	@Column(name = "money", nullable = false, precision = 10, scale = 2)
	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supply_id")
	public Supply getSupply() {
		return supply;
	}

	public void setSupply(Supply supply) {
		this.supply = supply;
	}

	@Column(name = "supply_name")
	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
	
	@Column(name = "sign" ,length=50)
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Column(name="mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name="card_no")
	public Integer getCardNo() {
		return cardNo;
	}

	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}

	@Column(name="card_ids")
	public String getCardIds() {
		return cardIds;
	}

	public void setCardIds(String cardIds) {
		this.cardIds = cardIds;
	}

	@Column(name="goods_id")
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@Column(name="terminal_no")
	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	@Column(name="reversal_state")
	public Integer getReversalState() {
		return reversalState;
	}

	public void setReversalState(Integer reversalState) {
		this.reversalState = reversalState;
	}

}