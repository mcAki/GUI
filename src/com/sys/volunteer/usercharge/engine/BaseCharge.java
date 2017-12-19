package com.sys.volunteer.usercharge.engine;

import java.io.Serializable;

import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Users;

public abstract class BaseCharge implements Serializable,ICharge {
	
	private UserCharge userCharge;
	private Mainorder mainorder;
	private Double dealMoney;
	private Users user;
	private Supply supply;
	private Users recodeUsers;
	private String recodeOperator;
	private int flag;
	
	public BaseCharge(UserCharge userCharge, Mainorder mainorder,
			Double dealMoney, Users user,  Users recodeUsers,
			String recodeOperator, int flag) {
		super();
		this.userCharge = userCharge;
		this.mainorder = mainorder;
		this.dealMoney = dealMoney;
		this.user = user;
		this.recodeUsers = recodeUsers;
		this.recodeOperator = recodeOperator;
		this.flag = flag;
	}
	
	public BaseCharge(UserCharge userCharge, Mainorder mainorder,
			Double dealMoney, Supply supply, Users recodeUsers,
			String recodeOperator, int flag) {
		super();
		this.userCharge = userCharge;
		this.mainorder = mainorder;
		this.dealMoney = dealMoney;
		this.supply = supply;
		this.recodeUsers = recodeUsers;
		this.recodeOperator = recodeOperator;
		this.flag = flag;
	}

	public UserCharge getUserCharge() {
		return userCharge;
	}

	public void setUserCharge(UserCharge userCharge) {
		this.userCharge = userCharge;
	}

	public Mainorder getMainorder() {
		return mainorder;
	}

	public void setMainorder(Mainorder mainorder) {
		this.mainorder = mainorder;
	}

	public Double getDealMoney() {
		return dealMoney;
	}

	public void setDealMoney(Double dealMoney) {
		this.dealMoney = dealMoney;
	}

	public Users getRecodeUsers() {
		return recodeUsers;
	}

	public void setRecodeUsers(Users recodeUsers) {
		this.recodeUsers = recodeUsers;
	}

	public String getRecodeOperator() {
		return recodeOperator;
	}

	public void setRecodeOperator(String recodeOperator) {
		this.recodeOperator = recodeOperator;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Supply getSupply() {
		return supply;
	}

	public void setSupply(Supply supply) {
		this.supply = supply;
	}
	
	
}
