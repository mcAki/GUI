package com.sys.volunteer.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Mainorder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mainorder", catalog = "mprs")
public class Mainorder implements java.io.Serializable {

	// Fields

	private String mainOrderId;
	private Users users;
	private String userName;
	private String desc;
	private GoodsType goodsType;
	private String goodsTypeName;
	private Goods goods;
	private String goodsName;
	private Integer goodsFlag;
	private Integer goodsNo;
	private double goodsValue;
	private String cardLibIds;
	private Double stockPrice;
	private Double retailPrice;
	private Double totalMoney;
	private Double commission;
	private Date createTime;
	private Integer state;
	private Supply supply;
	private String supplyName;
	private SupplyInterface supplyInterface;
	private String sign;
	private String mobile;
	private String terminalNo;
	private Integer isTerminal;
	private Integer orderType;
	private Integer canReverse;
	private Integer reversalState;
	private Integer respTime;
	private String lastOrderId;
	private String lastSupplyIds;
	private Integer isNeedManual;
	private String cardNo;
	private Users manualUsers;
	private String manualUserName;
	private long refreshTime;
	private Date reversalTime;
	private Integer moneyBack;
	private Double cBalance;
	private Integer IsLocal;
	private Long sumAll;
	
	private String batchOrderId;
	private Integer isBatch;
	private Integer currentProcessNum;
	private Integer restProcessNum;
	private Integer batchOrderState;
	private String commentsOn;
	private String at;
	private String gameId;
	private String autoGameId;
	private String atVerify;
	private String clientIp;
	

	public Long getSumAll() {
		return sumAll;
	}

	public void setSumAll(Long sumAll) {
		this.sumAll = sumAll;
	}

	private Set<Orderdetail> orderdetails = new HashSet<Orderdetail>(0);

	// Constructors

	/** default constructor */
	public Mainorder() {
	}

	/** minimal constructor */
	public Mainorder(String mainOrderId, Users users, Double totalMoney,
			Date createTime) {
		this.mainOrderId = mainOrderId;
		this.users = users;
		this.totalMoney = totalMoney;
		this.createTime = createTime;
	}

	/** full constructor */
	public Mainorder(String mainOrderId, Users users, String desc,
			Double totalMoney, Date createTime, Integer state,
			Set<Orderdetail> orderdetails) {
		this.mainOrderId = mainOrderId;
		this.users = users;
		this.desc = desc;
		this.totalMoney = totalMoney;
		this.createTime = createTime;
		this.state = state;
		this.orderdetails = orderdetails;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "mainOrderId", unique = true, nullable = false, length = 32)
	public String getMainOrderId() {
		return this.mainOrderId;
	}

	public void setMainOrderId(String mainOrderId) {
		this.mainOrderId = mainOrderId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "[desc]", length = 100)
	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(name = "totalMoney", nullable = false, precision = 10, scale = 2)
	public Double getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime", nullable = false, length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mainorder")
	public Set<Orderdetail> getOrderdetails() {
		return this.orderdetails;
	}

	public void setOrderdetails(Set<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goods_type_id")
	public GoodsType getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(GoodsType goodsType) {
		this.goodsType = goodsType;
	}

	@Column(name="goods_type_name")
	public String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
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

	@Column(name="goods_no")
	public Integer getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(Integer goodsNo) {
		this.goodsNo = goodsNo;
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

	@Column(name="terminal_no")
	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	@Column(name="is_terminal")
	public Integer getIsTerminal() {
		return isTerminal;
	}

	public void setIsTerminal(Integer isTerminal) {
		this.isTerminal = isTerminal;
	}

	@Column(name="order_type")
	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="card_lib_ids")
	public String getCardLibIds() {
		return cardLibIds;
	}

	public void setCardLibIds(String cardLibIds) {
		this.cardLibIds = cardLibIds;
	}

	@Column(name="goods_value")
	public double getGoodsValue() {
		return goodsValue;
	}

	public void setGoodsValue(double goodsValue) {
		this.goodsValue = goodsValue;
	}

	@Column(name="can_reverse")
	public Integer getCanReverse() {
		return canReverse;
	}

	public void setCanReverse(Integer canReverse) {
		this.canReverse = canReverse;
	}

	@Column(name="reversal_state")
	public Integer getReversalState() {
		return reversalState;
	}

	public void setReversalState(Integer reversalState) {
		this.reversalState = reversalState;
	}

	@Column(name="commission", precision = 10, scale = 2)
	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplyinterface_id")
	public SupplyInterface getSupplyInterface() {
		return supplyInterface;
	}

	public void setSupplyInterface(SupplyInterface supplyInterface) {
		this.supplyInterface = supplyInterface;
	}

	@Column(name="resp_time")
	public Integer getRespTime() {
		return respTime;
	}

	public void setRespTime(Integer respTime) {
		this.respTime = respTime;
	}

	@Column(name="last_order_id")
	public String getLastOrderId() {
		return lastOrderId;
	}

	public void setLastOrderId(String lastOrderId) {
		this.lastOrderId = lastOrderId;
	}

	@Column(name="last_supply_ids")
	public String getLastSupplyIds() {
		return lastSupplyIds;
	}

	public void setLastSupplyIds(String lastSupplyIds) {
		this.lastSupplyIds = lastSupplyIds;
	}

	@Column(name="is_need_manual")
	public Integer getIsNeedManual() {
		return isNeedManual;
	}

	public void setIsNeedManual(Integer isNeedManual) {
		this.isNeedManual = isNeedManual;
	}

	@Column(name="card_no")
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Column(name="refresh_time")
	public long getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(long refreshTime) {
		this.refreshTime = refreshTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reversalTime", nullable = true, length = 0)
	public Date getReversalTime() {
		return reversalTime;
	}

	public void setReversalTime(Date reversalTime) {
		this.reversalTime = reversalTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manual_user_id")
	public Users getManualUsers() {
		return manualUsers;
	}

	public void setManualUsers(Users manualUsers) {
		this.manualUsers = manualUsers;
	}

	@Column(name="manual_user_name")
	public String getManualUserName() {
		return manualUserName;
	}

	public void setManualUserName(String manualUserName) {
		this.manualUserName = manualUserName;
	}

	@Column(name="money_back")
	public Integer getMoneyBack() {
		return moneyBack;
	}

	public void setMoneyBack(Integer moneyBack) {
		this.moneyBack = moneyBack;
	}

	@Column(name = "c_balance", precision = 10, scale = 2)
	public Double getcBalance() {
		return cBalance;
	}

	public void setcBalance(Double cBalance) {
		this.cBalance = cBalance;
	}

	@Column(name="is_local")
	public Integer getIsLocal() {
		return IsLocal;
	}

	public void setIsLocal(Integer isLocal) {
		IsLocal = isLocal;
	}

	@Column(name="batch_order_id")
	public String getBatchOrderId() {
		return batchOrderId;
	}

	public void setBatchOrderId(String batchOrderId) {
		this.batchOrderId = batchOrderId;
	}

	@Column(name="is_batch")
	public Integer getIsBatch() {
		return isBatch;
	}

	public void setIsBatch(Integer isBatch) {
		this.isBatch = isBatch;
	}

	@Column(name="current_process_num")
	public Integer getCurrentProcessNum() {
		return currentProcessNum;
	}

	public void setCurrentProcessNum(Integer currentProcessNum) {
		this.currentProcessNum = currentProcessNum;
	}

	@Column(name="rest_process_num")
	public Integer getRestProcessNum() {
		return restProcessNum;
	}

	public void setRestProcessNum(Integer restProcessNum) {
		this.restProcessNum = restProcessNum;
	}

	@Column(name="batch_order_state")
	public Integer getBatchOrderState() {
		return batchOrderState;
	}

	public void setBatchOrderState(Integer batchOrderState) {
		this.batchOrderState = batchOrderState;
	}

	@Column(name="comments_on")
	public String getCommentsOn() {
		return commentsOn;
	}

	public void setCommentsOn(String commentsOn) {
		this.commentsOn = commentsOn;
	}

	@Column(name = "at")
	public String getAt() {
		return at;
	}

	public void setAt(String at) {
		this.at = at;
	}

	@Column(name="gameId")
	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	@Column(name="autoGameId")
	public String getAutoGameId() {
		return autoGameId;
	}

	public void setAutoGameId(String autoGameId) {
		this.autoGameId = autoGameId;
	}

	@Column(name="atVerify")
	public String getAtVerify() {
		return atVerify;
	}

	public void setAtVerify(String atVerify) {
		this.atVerify = atVerify;
	}

	@Column(name="clientIp")
	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	@Column(name="goods_flag")
	public Integer getGoodsFlag() {
		return goodsFlag;
	}

	public void setGoodsFlag(Integer goodsFlag) {
		this.goodsFlag = goodsFlag;
	}

}