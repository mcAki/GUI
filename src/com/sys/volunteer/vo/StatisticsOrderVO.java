package com.sys.volunteer.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class StatisticsOrderVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private BigInteger sumAll;
	private Double amountAll;
	private Double stockPriceSum;
	
	private Integer telSum;
	private Long telAmount;
	private Integer uniSum;
	private Long uniAmount;
	private Integer mobSum;
	private Long mobAmount;
	private Integer qqSum;
	private Long qqAmout;
	private Integer nationSum;
	private Long nationAmount;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public BigInteger getSumAll() {
		return sumAll;
	}
	public void setSumAll(BigInteger sumAll) {
		this.sumAll = sumAll;
	}
	public Double getAmountAll() {
		return amountAll;
	}
	public void setAmountAll(Double amountAll) {
		this.amountAll = amountAll;
	}
	public Integer getTelSum() {
		return telSum;
	}
	public void setTelSum(Integer telSum) {
		this.telSum = telSum;
	}
	public Long getTelAmount() {
		return telAmount;
	}
	public void setTelAmount(Long telAmount) {
		this.telAmount = telAmount;
	}
	public Integer getUniSum() {
		return uniSum;
	}
	public void setUniSum(Integer uniSum) {
		this.uniSum = uniSum;
	}
	public Long getUniAmount() {
		return uniAmount;
	}
	public void setUniAmount(Long uniAmount) {
		this.uniAmount = uniAmount;
	}
	public Integer getMobSum() {
		return mobSum;
	}
	public void setMobSum(Integer mobSum) {
		this.mobSum = mobSum;
	}
	public Long getMobAmount() {
		return mobAmount;
	}
	public void setMobAmount(Long mobAmount) {
		this.mobAmount = mobAmount;
	}
	public Integer getQqSum() {
		return qqSum;
	}
	public void setQqSum(Integer qqSum) {
		this.qqSum = qqSum;
	}
	public Long getQqAmout() {
		return qqAmout;
	}
	public void setQqAmout(Long qqAmout) {
		this.qqAmout = qqAmout;
	}
	public Integer getNationSum() {
		return nationSum;
	}
	public void setNationSum(Integer nationSum) {
		this.nationSum = nationSum;
	}
	public Long getNationAmount() {
		return nationAmount;
	}
	public void setNationAmount(Long nationAmount) {
		this.nationAmount = nationAmount;
	}
	public Double getStockPriceSum() {
		return stockPriceSum;
	}
	public void setStockPriceSum(Double stockPriceSum) {
		this.stockPriceSum = stockPriceSum;
	}
	
	
}
