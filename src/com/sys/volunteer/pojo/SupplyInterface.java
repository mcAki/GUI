package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Interfacetype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "supplyinterface", catalog = "mprs")
public class SupplyInterface implements java.io.Serializable {

	// Fields

	private Integer id;
	private Supply supply;
	private String supplyName;
	private Goods goods;
	private String goodsName;
	private Double stockPrice;
	private Double retailPrice;
	private Double value;
	private Integer defaultAlarm;
	private Integer state;
	private Integer canReverse;
	private Integer failedCount;
	private Integer clientType;
	private Integer kaCardId;
	private Integer kaBaseNum;
	private Set<Usergroup> usergroups = new HashSet<Usergroup>(0);

	// Constructors

	/** default constructor */
	public SupplyInterface() {
	}

	/** full constructor */
	public SupplyInterface(Integer id, Supply supply, String supplyName,
			Goods goods, String goodsName, Double stockPrice,
			Double retailPrice, Double value, Integer defaultAlarm,
			Integer state, Integer canReverse) {
		super();
		this.id = id;
		this.supply = supply;
		this.supplyName = supplyName;
		this.goods = goods;
		this.goodsName = goodsName;
		this.stockPrice = stockPrice;
		this.retailPrice = retailPrice;
		this.value = value;
		this.defaultAlarm = defaultAlarm;
		this.state = state;
		this.canReverse = canReverse;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supply_id", nullable = false)
	public Supply getSupply() {
		return supply;
	}

	public void setSupply(Supply supply) {
		this.supply = supply;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goods_id", nullable = false)
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@Column(name = "supply_name")
	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	@Column(name = "goods_name")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "stock_price", nullable = false, precision = 10, scale = 2)
	public Double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}

	@Column(name = "retail_price", nullable = false, precision = 10, scale = 2)
	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	@Column(name = "[value]")
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Column(name = "default_alarm")
	public Integer getDefaultAlarm() {
		return defaultAlarm;
	}

	public void setDefaultAlarm(Integer defaultAlarm) {
		this.defaultAlarm = defaultAlarm;
	}

	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "can_reverse")
	public Integer getCanReverse() {
		return canReverse;
	}

	public void setCanReverse(Integer canReverse) {
		this.canReverse = canReverse;
	}

	@Column(name = "failed_count")
	public Integer getFailedCount() {
		return failedCount;
	}

	public void setFailedCount(Integer failedCount) {
		this.failedCount = failedCount;
	}

	@Column(name = "client_type")
	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="supplyInterfaces")
	public Set<Usergroup> getUsergroups() {
		return usergroups;
	}

	public void setUsergroups(Set<Usergroup> usergroups) {
		this.usergroups = usergroups;
	}

	@Column(name="91ka_card_id")
	public Integer getKaCardId() {
		return kaCardId;
	}

	public void setKaCardId(Integer kaCardId) {
		this.kaCardId = kaCardId;
	}

	@Column(name="91ka_base_num")
	public Integer getKaBaseNum() {
		return kaBaseNum;
	}

	public void setKaBaseNum(Integer kaBaseNum) {
		this.kaBaseNum = kaBaseNum;
	}

}