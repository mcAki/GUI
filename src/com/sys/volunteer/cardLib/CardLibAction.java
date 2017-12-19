package com.sys.volunteer.cardLib;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.common.Const;
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
import com.sys.volunteer.supplyinterface.SupplyInterfaceService;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.usercharge.UserChargeEngine;
import com.sys.volunteer.usercharge.engine.ICharge;
import com.sys.volunteer.usercharge.engine.charge.Charge4Supply;
import com.sys.volunteer.usercharge.engine.charge.Charge4User;
import com.sys.volunteer.users.UserService;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class CardLibAction extends BaseAction implements Preparable{

	@Resource
	GoodsService goodsService;
	@Resource
	SupplyService supplyService;
	@Resource
	CardLibService cardLibService;
	@Resource
	SupplyInterfaceService supplyInterfaceService;
	@Resource
	UserService userService;
	@Resource
	UseraccountService useraccountService;
	
	private List<Goods> goodsList;
	
	private CardLib cardLib;
	
	private Double stockPrice;
	
	private Double retailPrice;
	
	private String content;
	
	@Override
	public void prepare() throws Exception {
		goodsList= goodsService.getGoodsListByFlag(Const.GoodsFlag.GAME_CARD);
		//goodsList.addAll(goodsService.getGoodsListByFlag(3));
	}
	
	public String add() throws Exception{
		cardLib=new CardLib();
		return "do";
	}
	
	public String doAdd() throws Exception{
		if (content==null||content.equals("")) {
			return ShowMessage(MSG_TYPE_STOP, "请填写卡密内容", "点击返回", "page!add.do", 0);
		}
		SupplyInterface supplyInterface = supplyInterfaceService.findBySupplyAndGoods(cardLib.getSupply().getId(), cardLib.getGoods().getGoodsId());
		if (supplyInterface==null) {
			return ShowMessage(MSG_TYPE_STOP, "该供货商没有对应商品,请先添加", "", "", 0);
		}
		if (cardLib.getExpireTime().getTime()<=System.currentTimeMillis()) {
			return ShowMessage(MSG_TYPE_STOP, "日期不能小于现在", "点击返回", "page!add.do", 0);
		}
		List<CardLib> cardLibs=cardLibService.addCardLib(cardLib,supplyInterface, content);
		//自动增加供货商额度
		SupplyKernel supplyKernel=SupplyKernel.getInstance();
		Supply supply=(Supply)supplyKernel.getSupplyMapKeyId().get(supplyInterface.getSupply().getId());
//		supplyKernel.depositBalance(supply, supplyInterface.getStockPrice()*cardLibs.size(), new UserCharge());
		ICharge iCharge2 = new Charge4Supply(new UserCharge(), null, supplyInterface.getStockPrice()*cardLibs.size(), (Supply)supply, null, null, Const.UseraccountdealdetailFlag.SUPPLY_DEPOSIT);
		//自动增加管理员额度
		Users users = userService.getAdminUser();
		ICharge iCharge = new Charge4User(new UserCharge(), null, supplyInterface.getStockPrice()*cardLibs.size(), users, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.USER_DEPOSIT);
		UserChargeEngine.getInstance().addLast(iCharge);
		UserChargeEngine.getInstance().addLast(iCharge2);
//		useraccountService.refreshAccountes(null,users,null, getSessionUser().getUserName(), new UserCharge(),supplyInterface.getStockPrice()*cardLibs.size(),Const.UseraccountdealdetailFlag.USER_DEPOSIT);
		return ShowMessage(MSG_TYPE_NORMAL, "卡密增加成功", "", "", 0);
	}

	public CardLib getCardLib() {
		return cardLib;
	}

	public void setCardLib(CardLib cardLib) {
		this.cardLib = cardLib;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}

	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

}
