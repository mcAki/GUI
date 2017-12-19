package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sys.volunteer.supply.usage.ISupply;

/**
 * Supply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "supply", catalog = "mprs")
public class Supply implements java.io.Serializable,ISupply {

	/**
	 * 销售类型:空中充值
	 */
	public static final int SELL_TYPE_RECHARGE=1;
	
	/**
	 * 销售类型:卡密
	 */
	public static final int SELL_TYPE_CARD=2;
	
	
	// Fields

	private Integer id;
	private String supplyName;
	private String desc;
	private Date createTime;
	private String reserve1;
	private String reserve2;
	private Long avgScore;
	private Double balance;
	private Integer supplyType;
	private String contactPeople;
	private String address;
	private String mobile;
	private Integer isUsed;
	private String clazzName;
	private Integer sellType;

	// Constructors

	/** default constructor */
	public Supply() {
	}

	/** minimal constructor */
	public Supply( Long avgScore) {
		this.avgScore = avgScore;
	}

	/** full constructor */
	public Supply( String supplyName,
			String desc, Date createTime, 
			String reserve1, String reserve2, Long avgScore) {
		this.supplyName = supplyName;
		this.desc = desc;
		this.createTime = createTime;
		this.reserve1 = reserve1;
		this.reserve2 = reserve2;
		this.avgScore = avgScore;
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

	@Column(name = "supplyName", length = 32)
	public String getSupplyName() {
		return this.supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	@Column(name = "[desc]", length = 100)
	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "reserve1", length = 32)
	public String getReserve1() {
		return this.reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	@Column(name = "reserve2", length = 32)
	public String getReserve2() {
		return this.reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	@Column(name = "avgScore", nullable = false, precision = 10, scale = 0)
	public Long getAvgScore() {
		return this.avgScore;
	}

	public void setAvgScore(Long avgScore) {
		this.avgScore = avgScore;
	}

	@Column(name="supply_type")
	public Integer getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(Integer supplyType) {
		this.supplyType = supplyType;
	}

	@Column(name="contact_people")
	public String getContactPeople() {
		return contactPeople;
	}

	public void setContactPeople(String contactPeople) {
		this.contactPeople = contactPeople;
	}

	@Column(name="address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name="balance")
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Column(name="is_used")
	public Integer getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}

	@Column(name="clazz_name")
	public String getClazzName() {
		return clazzName;
	}

	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}

	@Column(name="sell_type")
	public Integer getSellType() {
		return sellType;
	}

	public void setSellType(Integer sellType) {
		this.sellType = sellType;
	}
	
	public void assign(Supply supply){
		id=supply.id;
		supplyName=supply.supplyName;
		desc=supply.desc;
		createTime=supply.createTime;
		reserve1=supply.reserve1;
		reserve2=supply.reserve2;
		avgScore=supply.avgScore;
		balance=supply.balance;
		supplyType=supply.supplyType;
		contactPeople=supply.contactPeople;
		address=supply.address;
		mobile=supply.mobile;
		isUsed=supply.isUsed;
		clazzName=supply.clazzName;
		sellType=supply.sellType;
	}

	@Override
	public List<CardLib> buyCard(Mainorder mainorder,Orderdetail orderdetail,SupplyInterface supplyInterface,Users user,int goodsNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int recharge(Mainorder mainorder, String mobile, SupplyInterface supplyInterface) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int reverse(Mainorder mainorder) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isLeftCard(Goods goods,int goodsNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int refreshState(Mainorder mainorder) throws Exception {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public void cancelOrder(Mainorder mainorder) {
		// TODO Auto-generated method stub
		
	}

}