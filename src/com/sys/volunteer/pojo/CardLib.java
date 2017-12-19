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
 * Useraccount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "card_lib", catalog = "mprs")
public class CardLib implements java.io.Serializable {

	// Fields

	private String cardId;
	private String cardCode;
	private String cardName;
	private String cardPassword;
	private Supply supply;
	private String supplyName;
	private Integer supplyType;
	private Double cardValue;
	private Date indate;
	private Date expireTime;
	private String remark;
	private Integer autoChange;
	private Double stockPrice;
	private Double retailPrice;
	private Integer state;
	private Users users;
	private String userName;
	private Date buyTime;
	private Goods goods;
	private String goodsName;
	private Integer sellType;

	// Constructors

	/** default constructor */
	public CardLib() {
	}
	
	public CardLib(String cardId, String cardCode, String cardName,
			String cardPassword, Supply supply,
			String supplyName, Double cardValue, Date indate, String remark,
			Integer autoChange, Double stockPrice) {
		super();
		this.cardId = cardId;
		this.cardCode = cardCode;
		this.cardName = cardName;
		this.cardPassword = cardPassword;
		this.supply = supply;
		this.supplyName = supplyName;
		this.cardValue = cardValue;
		this.indate = indate;
		this.remark = remark;
		this.autoChange = autoChange;
		this.stockPrice = stockPrice;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "card_id", unique = true, nullable = false, length = 50)
	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supply_id")
	public Supply getSupply() {
		return this.supply;
	}

	public void setSupply(Supply supply) {
		this.supply = supply;
	}

	@Column(name = "card_name")
	public String getCardName() {
		return this.cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	@Column(name = "card_password" ,length=50)
	public String getCardPassword() {
		return this.cardPassword;
	}

	public void setCardPassword(String cardPassword) {
		this.cardPassword = cardPassword;
	}

	@Column(name = "card_code")
	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	@Column(name = "supply_name")
	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	@Column(name = "card_value", precision = 10, scale = 2)
	public Double getCardValue() {
		return cardValue;
	}

	public void setCardValue(Double cardValue) {
		this.cardValue = cardValue;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "indate",  length = 0)
	public Date getIndate() {
		return indate;
	}

	public void setIndate(Date indate) {
		this.indate = indate;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "auto_change")
	public Integer getAutoChange() {
		return autoChange;
	}

	public void setAutoChange(Integer autoChange) {
		this.autoChange = autoChange;
	}

	@Column(name = "stock_price", precision = 10, scale = 2)
	public Double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}

	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "buy_time",  length = 0)
	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "retail_price", nullable = false, precision = 10, scale = 2)
	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goods_id")
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@Column(name="goods_name")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expire_time",  length = 0)
	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	@Column(name="supply_type")
	public Integer getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(Integer supplyType) {
		this.supplyType = supplyType;
	}

	@Column(name="sell_type")
	public Integer getSellType() {
		return sellType;
	}

	public void setSellType(Integer sellType) {
		this.sellType = sellType;
	}


}