package com.sys.volunteer.vo;

import java.util.Date;

public class UserInfo {

	/**
	 * 用户id
	 */
	private String id;
	/**
	 * 用户名称
	 */
	private String name;
	/**
	 * 玩家名字
	 */
	private String roleName;
	/**
	 * 玩家状态
	 */
	private Integer state;

	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 等级
	 */
	private Integer level;
	/**
	 * 性别 2男1女
	 */
	private Integer gender;
	/**
	 * 拥有石币(石币)
	 */
	private Integer balanceA;
	/**
	 * 拥有钻石(钻石)
	 */
	private Integer balanceB;
	/**
	 * 拥有绑定钻石
	 */
	private Integer balanceC;
	/**
	 * 是否ai
	 */
	private Integer isAi;
	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;
	/**
	 * vip经验
	 */
	private int vipExp;

	/**
	 * vip等级
	 */
	private int vipLv;

	private int combatPower;// 战斗力(基础属性+装备+远古之魂)
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getBalanceA() {
		return balanceA;
	}

	public void setBalanceA(Integer balanceA) {
		this.balanceA = balanceA;
	}

	public Integer getBalanceB() {
		return balanceB;
	}

	public void setBalanceB(Integer balanceB) {
		this.balanceB = balanceB;
	}

	public Integer getBalanceC() {
		return balanceC;
	}

	public void setBalanceC(Integer balanceC) {
		this.balanceC = balanceC;
	}

	public Integer getIsAi() {
		return isAi;
	}

	public void setIsAi(Integer isAi) {
		this.isAi = isAi;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getVipExp() {
		return vipExp;
	}

	public void setVipExp(int vipExp) {
		this.vipExp = vipExp;
	}

	public int getVipLv() {
		return vipLv;
	}

	public void setVipLv(int vipLv) {
		this.vipLv = vipLv;
	}

	public int getCombatPower() {
		return combatPower;
	}

	public void setCombatPower(int combatPower) {
		this.combatPower = combatPower;
	}

}
