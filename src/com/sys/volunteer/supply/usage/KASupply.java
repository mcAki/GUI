package com.sys.volunteer.supply.usage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.opensymphony.xwork2.Result;
import com.sys.volunteer.cardLib.CardLibService;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.KAUtil;
import com.sys.volunteer.exception.SystemException;
import com.sys.volunteer.goods.GoodsService;
import com.sys.volunteer.ka.KaService;
import com.sys.volunteer.ka.Order91KA;
import com.sys.volunteer.ka.Query91KA;
import com.sys.volunteer.pojo.CardInfo;
import com.sys.volunteer.pojo.CardLib;
import com.sys.volunteer.pojo.GameRechargeRequest;
import com.sys.volunteer.pojo.GameRechargeResponse;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Orderdetail;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.springContext.SpringContextUtil;

public class KASupply extends Supply implements ISupply {
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
	private static CardLibService cardLibService;
	private static GoodsService goodsService;
	private static KaService kaService;
	
	@Override
	public int recharge(Mainorder mainorder, String mobile, SupplyInterface supplyInterface) {
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		kaService = (KaService)act.getBean("kaServiceBean");
		GameRechargeRequest request = kaService.findRequestByOrderId(mainorder.getMainOrderId());
		if (request != null) {
			logger.info("该订单已经进行了一次充值,请检查线程重复下单....");
			return Const.OperationLogResult.FAILED;
		}else {
			request = new GameRechargeRequest();
			request = kaService.addGameRechargeRequest(request, mainorder, supplyInterface, mainorder.getAt(), 
					mainorder.getGameId(), mainorder.getAutoGameId(), mainorder.getAtVerify(), mainorder.getClientIp());
			logger.info("91ka直充下了一条订单,订单号"+request.getCpOrderNo()+",金额"+request.getAmount()+"分");
		}
		GameRechargeResponse response = new GameRechargeResponse();
		String backUrl = KAUtil.gameRecharge(request);
		logger.info("91ka直充返回参数"+backUrl);
		if (backUrl == null || backUrl.equals("")) {
			logger.error("91ka返回了空参数!");
		}
		response.init(backUrl);
		response.setOrderId(mainorder.getMainOrderId());
		kaService.save(response);
		if (response.getRetResult().equals("9001")) {
			return Const.OperationLogResult.SUCCESS;
		}
		if (response.getRetResult().equals("9000")) {
			kaService.initQueryOrder(request);
			return Const.OperationLogResult.PROCESSING;
		}
		return Const.OperationLogResult.FAILED;
	}
	
	@Override
	public int refreshState(Mainorder mainorder) throws Exception{
		int result = 0;
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		kaService = (KaService)act.getBean("kaServiceBean");
		result = kaService.refreshGameRechargeOrder(mainorder);
		return result;
	}

	@Override
	public List<CardLib> buyCard(Mainorder mainorder,Orderdetail orderdetail,SupplyInterface supplyInterface,Users user,int goodsNo) {
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		cardLibService = (CardLibService) act.getBean("cardLibServiceBean");
		goodsService = (GoodsService) act.getBean("goodsServiceBean");
		Order91KA order91ka = new Order91KA();
		
		Goods goods = goodsService.findById(mainorder.getGoods().getGoodsId());
		order91ka.init(KAUtil.order(mainorder,goods));
		if (order91ka.getRetCode()!=0) {
			return null;
		}
		List<CardInfo> cardInfos = order91ka.getInfos();
		List<CardLib> sellLibs = new ArrayList<CardLib>();
		for (CardInfo cardInfo : cardInfos) {
			CardLib cardLib = cardLibService.addCardLibForStock(supplyInterface, user, cardInfo.getCardCode(), cardInfo.getPassword(),cardInfo.getTime());
			sellLibs.add(cardLib);
		}
		return sellLibs;
	}

	@Override
	public boolean isLeftCard(Goods goods,int goodsNo) {
		Query91KA query91ka = new Query91KA();
		query91ka.init(KAUtil.testQueryInventory(goods));
		if (query91ka.getRetCode()!=0) {
			throw new SystemException("请检查对应商品id,或联系管理员");
		}else {
			return !(query91ka.getLeftCardNum() < goodsNo || this.getBalance() == null || this.getBalance() < query91ka.getPrice()*goodsNo/10000);
		}
	}
	
}
