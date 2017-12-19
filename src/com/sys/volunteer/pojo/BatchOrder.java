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
 * BatchOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "batch_order", catalog = "mprs")
public class BatchOrder implements java.io.Serializable {

	// Fields

	private String batchOrderId;
	private String mainorderId;
	private Users users;
	private String userName;
	private String desc;
	private Integer goodsNo;
	private Double totalMoney;
	private Date createTime;
	private String mobile;
	private Integer currentProcessNum;
	private Integer restProcessNum;
	private Integer batchOrderState;
	
	


	// Property accessors
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "batch_order_id", unique = true, nullable = false, length = 32)
	public String getBatchOrderId() {
		return this.batchOrderId;
	}

	public void setBatchOrderId(String batchOrderId) {
		this.batchOrderId = batchOrderId;
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

	@Column(name="mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Column(name="goods_no")
	public Integer getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(Integer goodsNo) {
		this.goodsNo = goodsNo;
	}

	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	@Column(name="mainorder_id")
	public String getMainorderId() {
		return mainorderId;
	}

	public void setMainorderId(String mainorderId) {
		this.mainorderId = mainorderId;
	}

}