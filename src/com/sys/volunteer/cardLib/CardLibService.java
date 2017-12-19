package com.sys.volunteer.cardLib;

import java.util.List;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.CardLib;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.Users;

public interface CardLibService extends IDao {

	public List<CardLib> addCardLib(CardLib cardLib,SupplyInterface supplyInterface,String content);
	
	public CardLib addCardLibForMobile(SupplyInterface supplyInterface,Users users);
	
	public CardLib addCardLibForStock(SupplyInterface supplyInterface, Users users, String cardCode,
			String cardPassword,long expireTime);
	
	public List<CardLib> findCardLibsBySupplyId(Integer supplyId,Integer goodsId,Integer state);
	
	public void sellCardLib(CardLib cardLib,Users users);
	
	public void updateExpireCardLib();
	
	public void updateCardLibState(String cardLibIds,int state);
}
