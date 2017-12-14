package com.sys.volunteer.vo;

import java.util.Date;


public class CalPlayerChurnRateVo {

	private String countDate;
	private Integer count;
	private Integer countAll;
	private Double rate;
	private Integer level;
	private Date registerTime;
	
	public String getCountDate() {
		return countDate;
	}
	
	public void setCountDate(String countDate) {
		this.countDate = countDate;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getCountAll() {
		return countAll;
	}

	public void setCountAll(Integer countAll) {
		this.countAll = countAll;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

}
