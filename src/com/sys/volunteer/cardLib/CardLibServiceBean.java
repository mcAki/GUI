package com.sys.volunteer.cardLib;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.cxf.binding.corba.wsdl.Array;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.common.Const;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.goods.GoodsService;
import com.sys.volunteer.pojo.CardLib;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Useraccountdealdetail;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.supply.usage.ISupply;
import com.sys.volunteer.supplyinterface.SupplyInterfaceService;
import com.sys.volunteer.usercharge.UserChargeEngine;
import com.sys.volunteer.usercharge.engine.ICharge;
import com.sys.volunteer.usercharge.engine.charge.Charge4Supply;

@Service
@Transactional
public class CardLibServiceBean extends CommonDao implements CardLibService {

	@Resource
	GoodsService goodsService;
	@Resource
	SupplyService supplyService;
	@Resource
	SupplyInterfaceService supplyInterfaceService;
	
	@Override
	public List<CardLib> addCardLib(CardLib cardLib,SupplyInterface supplyInterface,String content) {
		List<CardLib> cardLibs=new ArrayList<CardLib>();
		content=content.replace("\r\n", "");
		String[] contents=content.trim().split(",");
		for (String cardLibContent : contents) {
			CardLib cLib=new CardLib();
			String[] cardStrings=cardLibContent.trim().split(" ");
			if (cardStrings.length==1) {
				String password=cardStrings[0];
				cLib.setCardPassword(password);
			}else if (cardStrings.length==2) {
				String cardCode=cardStrings[0];
				cLib.setCardCode(cardCode);
				String password=cardStrings[1];
				cLib.setCardPassword(password);
			}else if (cardStrings.length==3) {
				String cardCode=cardStrings[0];
				cLib.setCardCode(cardCode);
				String password=cardStrings[1];
				cLib.setCardPassword(password);
				String remark=cardStrings[2];
				cLib.setRemark(remark);
			}
			cLib.setSupply(cardLib.getSupply());
			cLib.setSupplyName(cardLib.getSupplyName());
			ISupply iSupply=SupplyKernel.getInstance().getSupplyMapKeyId().get(cardLib.getSupply().getId());
			cLib.setSupplyType(iSupply.getSupplyType());
			cLib.setSellType(iSupply.getSellType());
			cLib.setState(1);
			cLib.setGoods(supplyInterface.getGoods());
			cLib.setGoodsName(supplyInterface.getGoodsName());
			cLib.setCardValue(supplyInterface.getValue());
			cLib.setStockPrice(supplyInterface.getStockPrice());
			cLib.setRetailPrice(supplyInterface.getRetailPrice());
			cLib.setIndate(new Date());
			cLib.setExpireTime(cardLib.getExpireTime());
			this.save(cLib);
			cardLibs.add(cLib);
		}
		return cardLibs;
	}
	
	@Override
	public CardLib addCardLibForMobile(SupplyInterface supplyInterface,Users users) {
		CardLib cLib=new CardLib();
		Supply supply=(Supply)SupplyKernel.getInstance().getSupplyMapKeyId().get(supplyInterface.getSupply().getId());
		cLib.setSupply(supply);
		cLib.setSupplyName(supply.getSupplyName());
		cLib.setGoods(supplyInterface.getGoods());
		cLib.setGoodsName(supplyInterface.getGoodsName());
		cLib.setCardValue(supplyInterface.getValue());
		cLib.setStockPrice(supplyInterface.getStockPrice());
		cLib.setRetailPrice(supplyInterface.getRetailPrice());
		cLib.setIndate(new Date());
		//cLib.setExpireTime(cardLib.getExpireTime());
		cLib.setBuyTime(new Date());
		cLib.setUsers(users);
		cLib.setUserName(users.getUserName());
		cLib.setState(2);
		this.save(cLib);
		return cLib;
	}

	@Override
	public List<CardLib> findCardLibsBySupplyId(Integer supplyId,Integer goodsId,Integer state) {
		this.updateExpireCardLib();
		DetachedCriteria dec=DetachedCriteria.forClass(CardLib.class);
		dec.add(Restrictions.eq("supply.id", supplyId));
		dec.add(Restrictions.eq("goods.id", goodsId));
		dec.add(Restrictions.eq("state", state));
		dec.addOrder(Order.asc("expireTime"));
		List<CardLib> list=this.findByDetachedCriteria(dec);
		return list;
	}

	@Override
	public void sellCardLib(CardLib cardLib,Users users) {
		//TODO:这里要返回cardLib
		cardLib.setBuyTime(new Date());
		cardLib.setUsers(users);
		cardLib.setUserName(users.getUserName());
		cardLib.setState(2);
		this.update(cardLib);
	}

	@Override
	public void updateExpireCardLib(){
		DetachedCriteria dec=DetachedCriteria.forClass(CardLib.class);
		dec.add(Restrictions.eq("state", 1));
		dec.add(Restrictions.lt("expireTime", new Date()));
		List<CardLib> list=this.findByDetachedCriteria(dec);
		for (CardLib cardLib : list) {
			cardLib.setState(3);
			this.update(cardLib);
			//自动扣减供货商额度
			SupplyKernel supplyKernel=SupplyKernel.getInstance();
			Supply supply=(Supply)supplyKernel.getSupplyMapKeyId().get(cardLib.getSupply().getId());
//			supplyKernel.drawBalance(supply, cardLib.getStockPrice(), new UserCharge());
			ICharge iCharge2 = new Charge4Supply(new UserCharge(), null, cardLib.getStockPrice(), (Supply)supply, null, null, Const.UseraccountdealdetailFlag.DRAWN);
			UserChargeEngine.getInstance().addLast(iCharge2);
		}
	}

	@Override
	public CardLib addCardLibForStock(SupplyInterface supplyInterface, Users users, String cardCode,
			String cardPassword,long expireTime) {
		//TODO:这里要返回cardLib
		CardLib cLib=new CardLib();
		Supply supply=(Supply)SupplyKernel.getInstance().getSupplyMapKeyId().get(supplyInterface.getSupply().getId());
		cLib.setCardCode(cardCode);
		cLib.setCardPassword(cardPassword);
		cLib.setSupply(supply);
		cLib.setSupplyName(supply.getSupplyName());
		cLib.setGoodsName(supplyInterface.getGoodsName());
		cLib.setCardValue(supplyInterface.getValue());
		cLib.setStockPrice(supplyInterface.getStockPrice());
		cLib.setRetailPrice(supplyInterface.getRetailPrice());
		cLib.setIndate(new Date());
		cLib.setExpireTime(new Date(expireTime));
		cLib.setBuyTime(new Date());
		cLib.setUsers(users);
		cLib.setUserName(users.getUserName());
		cLib.setState(2);
		this.save(cLib);
		return cLib;
	}

	@Override
	public void updateCardLibState(String cardLibIds,int state) {
		String[] cardLibId = cardLibIds.split(",");
		for (int i = 0; i < cardLibId.length; i++) {
			CardLib cardLib = (CardLib)this.findById(CardLib.class, cardLibId[i]);
			cardLib.setState(state);
			this.update(cardLib);
		}
		
	}
	
}
