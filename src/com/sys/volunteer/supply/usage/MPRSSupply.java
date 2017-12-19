package com.sys.volunteer.supply.usage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.sys.volunteer.cardLib.CardLibService;
import com.sys.volunteer.pojo.CardLib;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Orderdetail;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.springContext.SpringContextUtil;

public class MPRSSupply extends Supply implements ISupply {
	
	private static CardLibService cardLibService;

	@Override
	public List<CardLib> buyCard(Mainorder mainorder,Orderdetail orderdetail,SupplyInterface supplyInterface,Users user,int goodsNo) {
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		cardLibService = (CardLibService) act.getBean("cardLibServiceBean");
		
		List<CardLib> cardLibs = cardLibService.findCardLibsBySupplyId(supplyInterface.getSupply()
				.getId(), supplyInterface.getGoods().getGoodsId(), 1);
		List<CardLib> sellLibs = new ArrayList<CardLib>();
		for (int i = 0; i < goodsNo; i++) {
			sellLibs.add(cardLibs.get(i));
			//标记卡密状态为已提取
			cardLibService.sellCardLib(cardLibs.get(i), user);
		}
		return sellLibs;
	}


	@Override
	public boolean isLeftCard(Goods goods,int goodsNo) {
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		cardLibService = (CardLibService) act.getBean("cardLibServiceBean");
		List<CardLib> cardLibs = cardLibService.findCardLibsBySupplyId(this.getId(), goods.getGoodsId(), 1);
		return !(cardLibs.size()<goodsNo || this.getBalance() == null || this.getBalance() < cardLibs.get(0).getStockPrice()*goodsNo);
	}
}
