package com.sys.volunteer.pojo;

import java.util.Date;

public class Games {
	
	private String gamesId;//游戏的编号
	
	private String gamesName;
	
	private int count;//购买的数量
	
	private double price;//面值
	
	private Date tradeTimeDate;//交易的时间
	
	private String gamesNum;//充值账户
	
	private int state;//状态: 1:成功   0:失败

	private String gamesType;//游戏类别
	
  //  private Useraccount useraccount;
    
	public Games() {
	}

	public Games(String gamesId, String gamesName, int count, double price,
			Date tradeTimeDate, String gamesNum, int state,
			String gamesType) {
		super();
		this.gamesId = gamesId;
		this.gamesName = gamesName;
		this.count = count;
		this.price = price;
		this.tradeTimeDate = tradeTimeDate;
		this.gamesNum = gamesNum;
		this.state = state;
		this.gamesType = gamesType;
	//	this.useraccount = useraccount;
	}
	
	
	public String getGamesId() {
		return gamesId;
	}

	public void setGamesId(String gamesId) {
		this.gamesId = gamesId;
	}

	public String getGamesName() {
		return gamesName;
	}

	public void setGamesName(String gamesName) {
		this.gamesName = gamesName;
	}

	public String getGamesType() {
		return gamesType;
	}

	public void setGamesType(String gamesType) {
		this.gamesType = gamesType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getGamesNum() {
		return gamesNum;
	}

	public void setGamesNum(String gamesNum) {
		this.gamesNum = gamesNum;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getTradeTimeDate() {
		return tradeTimeDate;
	}

	public void setTradeTimeDate(Date tradeTimeDate) {
		this.tradeTimeDate = tradeTimeDate;
	}

//	public Useraccount getUseraccount() {
//		return useraccount;
//	}
//
//	public void setUseraccount(Useraccount useraccount) {
//		this.useraccount = useraccount;
//	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	/*
	public static void main(String[] args) {
		System.out.println(GamesType.HOT);
		System.out.println("111111111111111111111111111111");
		System.out.println(GamesType.M2P);
	}*/
}
