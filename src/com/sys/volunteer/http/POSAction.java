package com.sys.volunteer.http;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sun.xml.bind.v2.runtime.unmarshaller.LocatorEx.Snapshot;
import com.sys.volunteer.area.AreaCodeService;
import com.sys.volunteer.authority.AuthorityService;
import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.cardLib.CardLibService;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.DateUtil;
import com.sys.volunteer.common.DesUtil;
import com.sys.volunteer.common.GenRamdomUtil;
import com.sys.volunteer.common.KAUtil;
import com.sys.volunteer.common.LiandongUtil;
import com.sys.volunteer.common.SysUtil;
import com.sys.volunteer.common.Const.LogConst;
import com.sys.volunteer.goods.GoodsService;
import com.sys.volunteer.jdbc.dao.orderDao.IMainorderDao;
import com.sys.volunteer.muticharge.ChargeMutithreadManager;
import com.sys.volunteer.muticharge.engine.OrderVO;
import com.sys.volunteer.operationLog.OperationLogService;
import com.sys.volunteer.order.OrderService;
import com.sys.volunteer.pojo.AreaCode;
import com.sys.volunteer.pojo.CardLib;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Menutree;
import com.sys.volunteer.pojo.Orderdetail;
import com.sys.volunteer.pojo.PosMsg;
import com.sys.volunteer.pojo.PosSignIn;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Useraccountdealdetail;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.supply.usage.ISupply;
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
public class POSAction extends BaseAction {

	protected static final Log logger = LogFactory.getLog(POSAction.class);
	
	@Resource
	PosMsgService posMsgService;
	@Resource
	PosSignInService posSignInService;
	@Resource
	OrderService orderService;
	@Resource
	UserService userService;
	@Resource
	CardLibService cardLibService;
	@Resource
	GoodsService goodsService;
	@Resource
	UseraccountService useraccountService;
	@Resource
	AuthorityService authorityService;
	@Resource
	SupplyInterfaceService supplyInterfaceService;
	@Resource
	OperationLogService operationLogService;
	@Resource
	AreaCodeService areaCodeService;
	@Resource
	IMainorderDao mainorderDao;

	private String content;

	private String reContent;

	private String msg;

	private static final String DATE_FORMAT = "yyyyMMddHHmmss";

	/**
	 * 本地测试获取字符串页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String httpPage() throws Exception {
		content = "G001|01|00|13632290688|10086|3000||shande|12345678||||||||s8768bd46s02jk837";
		return "page";
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 本地测试获取字符串页面Ex
	 * 
	 * @return
	 * @throws Exception
	 */
	public String httpPageEx() throws Exception {
		content = "G001|01|00|13632290688|10086|3000|6222023602060362654|402882e72e6131aa012e6131f81d0001|12345678|23498|402880e83779717401377972cee90001|65464|0|abcdefg|1234567|982372|s8768bd46s02jk837";
		return "page";
	}

	/**
	 * 本地echo测试
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getHttpPage() throws Exception {
		String result = "获取到的字符串是:     ";
		result += LiandongUtil.getRotmePage(
				"http://localhost:8080/MPRS/http/pos!httpPage.do", "utf-8");
		reContent = result;
		return "show";
	}

	/**
	 * 接手数据demo
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getPOS() throws Exception {
		String msg = LiandongUtil
				.getRotmePage(
						"http://211.147.242.55:8080/MPRS/http/pos!httpPage.do",
						"utf-8");
		String[] msgs = msg.trim().split("\\|");
		if (msgs.length != 17) {
			reContent = "报文不符合长度!";
			System.out.println(reContent);
		} else {
			PosMsg posMsg = new PosMsg(msgs, 1);
			reContent = posMsg.toString();
		}
		return "show";
	}

	/**
	 * 签到报文(杉德登陆)
	 */
	public String signIn() throws Exception {
		// G001|03|0000000000|20120528150422|00000000000000|16|0|||
		String loginAddr = getHttpServletRequest().getRemoteAddr().toString();
		String[] msgs = msg.trim().split("\\|");
		String[] msgs9 = new String[9];
		if (msgs.length != 9) {
			// 强制增加到9位
			for (int i = 0; i < msgs9.length; i++) {
				if (i < 7) {
					msgs9[i] = msgs[i];
				} else {
					msgs9[i] = "";
				}
			}
		} else {
			msgs9 = msgs;
		}
		PosSignIn posSignIn = new PosSignIn(msgs9);
		// TODO: 返回密钥
		String encode = "";
		String ran = "";
		if (posSignIn.getKeyMethod().equals("0")) {
			// 0是des加密
			// 生成随即16位数
			ran = GenRamdomUtil.genRandomNum(8);
			System.out.println(ran);
			// des加密
			encode = DesUtil.desEncode(ran).toUpperCase();
		} else {
			// 3des加密
			// 生成随即32位数
			ran = GenRamdomUtil.genRandomNum(16);
			System.out.println(ran);
			encode = DesUtil.des3Encode(ran).toUpperCase();
		}
		posSignIn.setKey(encode);
		// 放入PosKey,方便以后
		PosKey.setKey(ran);
		// 生成时间戳
		String date = DateUtil.DateToString(new Date(), DATE_FORMAT);
		posSignIn.setRespTime(date);
		posSignInService.save(posSignIn);
		reContent = posSignIn.toString();

		return "show";
	}

	/**
	 * 查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String query() throws Exception {
		// String msg =
		// LiandongUtil.getRotmePage("http://211.147.242.55:8080/MPRS/http/pos!httpPage.do",
		// "utf-8");
		System.out.println("================================" + msg);
		logger.info("================query密文================" + msg);
		msg = DesUtil.desDecode(DesUtil.toHexString(PosKey.getKey()), msg
				.trim());
		System.out.println("--------------------------------" + msg);
		logger.info("----------------query明文----------------" + msg);
		String[] msgs = msg.trim().split("\\|");
		// 检测报文长度
		String[] msgs18 = new String[18];
		if (msgs.length != 18) {
			// 强制增加到17位
			for (int i = 0; i < msgs18.length; i++) {
				if (i < 16) {
					msgs18[i] = msgs[i];
				} else {
					msgs18[i] = "";
				}
			}
		} else {
			msgs18 = msgs;
		}
		PosMsg posMsg = new PosMsg(msgs, 1);
		posMsg = posMsgService.addPosMsg(posMsg);
		// 验证包
		String reCode = queryValidate(posMsg);
		// 保存记录
		posMsg.setRetCode(reCode);
		// 生成订单号
		StringBuffer sb = new StringBuffer();
		// 生成时间戳
		//String date = DateUtil.DateToString(new Date(), DATE_FORMAT);
		long time = System.currentTimeMillis();
		sb.append(time);
		String orderId = DesUtil.desEncode(DesUtil.toHexString(PosKey.getKey()), sb.toString());
		// 截取前20位作为订单号
		posMsg.setOrderId(orderId.substring(0,20));
		posMsgService.update(posMsg);
		reContent = DesUtil.desEncode(DesUtil.toHexString(PosKey.getKey()),
				posMsg.toString());
		System.out.println("===========pos ECHO包============" + reContent);
		logger.info("===========pos ECHO包query返回============" + reContent);
		return "show";
	}

	/**
	 * 通知
	 * 
	 * @return
	 * @throws Exception
	 */
	public String recharge() throws Exception {
		// String msg =
		// LiandongUtil.getRotmePage("http://211.147.242.55:8080/MPRS/http/pos!httpPageEx.do",
		// "utf-8");
		System.out.println("================================" + msg);
		logger.info("================recharge密文================" + msg);
		msg = DesUtil.desDecode(DesUtil.toHexString(PosKey.getKey()), msg
				.trim());
		System.out.println("--------------------------------" + msg);
		logger.info("----------------recharge明文----------------" + msg);
		String[] msgs = msg.trim().split("\\|");
		// 检测报文长度
		String[] msgs18 = new String[18];
		if (msgs.length != 18) {
			// 强制增加到17位
			for (int i = 0; i < msgs18.length; i++) {
				if (i < 16) {
					msgs18[i] = msgs[i];
				} else {
					msgs18[i] = "";
				}
			}
		} else {
			msgs18 = msgs;
		}
		PosMsg posMsg = new PosMsg(msgs, 2);
		posMsg = posMsgService.addPosMsg(posMsg);
		// 验证包
		String reCode = rechargeValidate(posMsg);
		if (!reCode.equals("0000")) {
			logger.error("验证错误,返回码"+reCode);
			posMsg.setRetCode(reCode);
			posMsgService.update(posMsg);
			reContent = posMsg.toString();
			logger.info("===========pos ECHO包recharge返回============" + reContent);
			return "show";
		}
		// 查询订单号,如果有则下发,没有则生成
		PosMsg posMsg2 = null;
		if (posMsg.getOrderId()!=null) {
			List<PosMsg> list = posMsgService.findByOrderId(posMsg.getOrderId(),"02");
			if (list!=null&&list.size()>0) {
				logger.error("已存在订单:");
				posMsg2 = list.get(0);
				posMsg.setCardCode(posMsg2.getCardCode());
				posMsg.setCardPwd(posMsg2.getCardPwd());
				posMsgService.update(posMsg);
				reContent = DesUtil.desEncode(DesUtil.toHexString(PosKey.getKey()),
						posMsg.toString());
				logger.info("===========pos ECHO包recharge返回============" + reContent);
				return "show";
			}
		}
		Mainorder mainorder = new Mainorder();// orderService.findOrderById(posMsg.getStoreCode());
		Orderdetail orderdetail = new Orderdetail();
		Goods goods = null;
		Double value = null;
		if (posMsg.getMoney()!=null&&!posMsg.getMoney().equals("")) {
			value = Double.valueOf(posMsg.getMoney());
		}
		int type = 0;
		ISupply supply = null;
		Users user = userService.findUserByTerminalNo(posMsg.getTerminalNo());
		Users s = userService.getAdminUser();
		// 扣费

		if (posMsg.getFuncCode().equals("00")) {
			// 空中充值
			logger.info("开始pos下单流程");
			String mobileNum = posMsg.getMobile().substring(0, 7);
			AreaCode areaCode = areaCodeService.findByMobile(mobileNum);
			goods = goodsService.findByAreaCodeAndValue(areaCode, value / 100);
			if (goods == null) {
				logger.error("没有该商品");
				// 没有这件商品
				reCode = "4001";
				// 保存posmsg
				posMsg.setRetCode(reCode);
				posMsgService.update(posMsg);
				reContent = DesUtil.desEncode(DesUtil.toHexString(PosKey
						.getKey()), posMsg.toString());
				logger.info("===========pos ECHO包recharge返回============" + reContent);
				return "show";
			}
			SupplyKernel supplyKernel = SupplyKernel.getInstance();
			supply = supplyKernel.getISupply(supplyKernel
					.getListBySellType(goods,3), 1, goods.getValue());
			if (supply == null) {
				// 没有这件商品
				logger.error("没有供货商");
				reCode = "3001";
				// 保存posmsg
				posMsg.setRetCode(reCode);
				posMsgService.update(posMsg);
				reContent = DesUtil.desEncode(DesUtil.toHexString(PosKey
						.getKey()), posMsg.toString());
				logger.info("===========pos ECHO包recharge返回============" + reContent);
				return "show";
			}
			SupplyInterface supplyInterface = supplyInterfaceService.findByUsergroupAndSupplyAndGoods(user.
					getUsergroup().getId(),
						supply.getId(), goods.getGoodsId());
			if (supplyInterface==null || supplyInterface.getState()==0) {
				// 没有这件商品
				logger.error("没有供货商");
				reCode = "3001";
				// 保存posmsg
				posMsg.setRetCode(reCode);
				posMsgService.update(posMsg);
				reContent = DesUtil.desEncode(DesUtil.toHexString(PosKey
						.getKey()), posMsg.toString());
				logger.info("===========pos ECHO包recharge返回============" + reContent);
				return "show";
			}
			// 先扣费
			logger.info("开始pos空中充值");
			mainorder = orderService.addMainOrder(mainorder,
					posMsg.getMobile(), user, supplyInterface, 1, 1, posMsg
							.getTerminalNo(), 1);
			orderService.addOrderDetail(orderdetail, mainorder, null);
			//从s划额到经销商
			//useraccountService.refreshAccount(null, s, new Useraccountdealdetail(), money, Const.UseraccountdealdetailFlag.REMIT);
			//自动增加经销商额度
			//记录为pos机划款
			logger.info("开始pos处理额度");
			UserCharge ud = new UserCharge();
			ud.setVoucherType(5);
			UserCharge ud2 = new UserCharge();
			ud2.setVoucherType(5);
			UserCharge ud3 = new UserCharge();
			ud3.setVoucherType(5);
			UserCharge ud4 = new UserCharge();
			ud4.setVoucherType(5);
			//useraccountService.refreshAccount(null, user,
			//		ud, money,
			//		Const.UseraccountdealdetailFlag.USER_DEPOSIT);
			Users parentUser = userService.findUserById(user.getParentUsers().getUserId());
			//ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), getSessionUser(), null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
			//ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), (Supply)supply, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
			//管理员--一级商户
			ICharge iCharge = new Charge4User(ud, null, mainorder.getTotalMoney(), s, parentUser, user.getUserName(), Const.UseraccountdealdetailFlag.REMIT);
			ICharge iCharge2 = new Charge4User(ud2, null, mainorder.getTotalMoney(), parentUser, s, user.getUserName(), Const.UseraccountdealdetailFlag.USER_DEPOSIT);
			//一级商户--二级商户
			ICharge iCharge3 = new Charge4User(ud3, null, mainorder.getTotalMoney(), parentUser, user, user.getUserName(), Const.UseraccountdealdetailFlag.REMIT);
			ICharge iCharge4 = new Charge4User(ud4, null, mainorder.getTotalMoney(), user, parentUser, user.getUserName(), Const.UseraccountdealdetailFlag.USER_DEPOSIT);
			UserChargeEngine.getInstance().addLast(iCharge);
		    UserChargeEngine.getInstance().addLast(iCharge2);
		    UserChargeEngine.getInstance().addLast(iCharge3);
		    UserChargeEngine.getInstance().addLast(iCharge4);
//			useraccountService.refreshAccountes(null,s,parentUser,null,ud, money,
//					Const.UseraccountdealdetailFlag.REMIT);
//			useraccountService.refreshAccountes(null,parentUser,s,null,ud2,money,
//					Const.UseraccountdealdetailFlag.USER_DEPOSIT);
			
			
//			useraccountService.refreshAccountes(null,parentUser,user,null,ud3, money,
//					Const.UseraccountdealdetailFlag.REMIT);
//			
//			useraccountService.refreshAccountes(null,user,parentUser,null,ud4,money,
//					Const.UseraccountdealdetailFlag.USER_DEPOSIT);
			//先扣费
//			supplyKernel.purchaseBalance(mainorder);
//			useraccountService.refreshAccountes(mainorder,user,null,user.getUserName(),new UserCharge(),
//					mainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
			ICharge iCharge5 = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), user, null, null, Const.UseraccountdealdetailFlag.PURCHASE);
			ICharge iCharge6 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), (Supply)supply, null, null, Const.UseraccountdealdetailFlag.PURCHASE);
			UserChargeEngine.getInstance().addLast(iCharge5);
		    UserChargeEngine.getInstance().addLast(iCharge6);
//			int rs = supply.recharge(mainorder, posMsg.getMobile(),
//					supplyInterface);
//			// 增加一条operationLog
//			// operationLogService.addOperationLog(getSessionUser(),
//			// mainorder, mobile
//			// ,Const.OperationLogType.MOBILE_RECHARGE,Const.OperationLogResult.PROCESSING);
//			// 增加一条商品入库记录
//			CardLib cardLib = cardLibService.addCardLibForMobile(
//					supplyInterface, user);
//			// 销售商品id记录到mainorder
//			List<CardLib> cardLibs = new ArrayList<CardLib>();
//			cardLibs.add(cardLib);
//			orderService.updateOrderCardLibs(mainorder, cardLibs);
			//充值
		    logger.info("pos额度处理完成");
		} else if (posMsg.getFuncCode().equals("02")) {
			// Q币直充
			//数量goodsNo就是金额value
			int goodsNo = value.intValue();
			goods = goodsService.findByFlagProvinceCode(Const.GoodsFlag.GAME_RECHARGE, 0);//goodsService.findByKACardId(5178);
			if (goods != null) {
				SupplyKernel supplyKernel = SupplyKernel.getInstance();
				supply=supplyKernel.getISupply(supplyKernel.getListBySellType(goods,2),goodsNo,goods.getValue());
				if (supply.getClazzName().equals("KASupply")) {
					supply=supplyKernel.getISupply(supplyKernel.getListBySellType(goods,supply.getId()+"",2),goodsNo,goods.getValue());
				}
				if ((goodsNo<10 || goodsNo>200) && supply.getClazzName().equals("ZhongrongSupply")) {
					supply=supplyKernel.getISupply(supplyKernel.getListBySellType(goods,supply.getId()+"",2),goodsNo,goods.getValue());
				}
				if (supply == null || supply.getIsUsed()==0) {
					// 没有这件商品
					logger.error("没有供货商");
					reCode = "3001";
					// 保存posmsg
					posMsg.setRetCode(reCode);
					posMsgService.update(posMsg);
					reContent = DesUtil.desEncode(DesUtil.toHexString(PosKey
							.getKey()), posMsg.toString());
					logger.info("===========pos ECHO包recharge返回============" + reContent);
					return "show";
				}
				SupplyInterface supplyInterface = supplyInterfaceService.findByUsergroupAndSupplyAndGoods(user.
						getUsergroup().getId(),
							supply.getId(), goods.getGoodsId());
				if (supplyInterface==null || supplyInterface.getState()==0) {
					// 没有这件商品
					logger.error("没有供货商");
					reCode = "3001";
					// 保存posmsg
					posMsg.setRetCode(reCode);
					posMsgService.update(posMsg);
					reContent = DesUtil.desEncode(DesUtil.toHexString(PosKey
							.getKey()), posMsg.toString());
					logger.info("===========pos ECHO包recharge返回============" + reContent);
					return "show";
				}
				
				
				//生成订单
				//mobile(qq号)前面补0,需要去除
				mainorder = orderService.addMainOrderForQQ(mainorder, posMsg.getMobile().replaceFirst("^0*", ""), (double)goods.getValue(), user,
						supplyInterface, goodsNo,0,"",1);
				orderService.addOrderDetail(orderdetail, mainorder, null);
				
				logger.info("开始pos处理额度");
				UserCharge ud = new UserCharge();
				ud.setVoucherType(5);
				UserCharge ud2 = new UserCharge();
				ud2.setVoucherType(5);
				UserCharge ud3 = new UserCharge();
				ud3.setVoucherType(5);
				UserCharge ud4 = new UserCharge();
				ud4.setVoucherType(5);
				//useraccountService.refreshAccount(null, user,
				//		ud, money,
				//		Const.UseraccountdealdetailFlag.USER_DEPOSIT);
				Users parentUser = userService.findUserById(user.getParentUsers().getUserId());
				//ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), getSessionUser(), null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
				//ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), (Supply)supply, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
				//管理员--一级商户
				ICharge iCharge = new Charge4User(ud, null, mainorder.getTotalMoney(), s, parentUser, user.getUserName(), Const.UseraccountdealdetailFlag.REMIT);
				ICharge iCharge2 = new Charge4User(ud2, null, mainorder.getTotalMoney(), parentUser, s, user.getUserName(), Const.UseraccountdealdetailFlag.USER_DEPOSIT);
				//一级商户--二级商户
				ICharge iCharge3 = new Charge4User(ud3, null, mainorder.getTotalMoney(), parentUser, user, user.getUserName(), Const.UseraccountdealdetailFlag.REMIT);
				ICharge iCharge4 = new Charge4User(ud4, null, mainorder.getTotalMoney(), user, parentUser, user.getUserName(), Const.UseraccountdealdetailFlag.USER_DEPOSIT);
				UserChargeEngine.getInstance().addLast(iCharge);
			    UserChargeEngine.getInstance().addLast(iCharge2);
			    UserChargeEngine.getInstance().addLast(iCharge3);
			    UserChargeEngine.getInstance().addLast(iCharge4);
//				useraccountService.refreshAccountes(null,s,parentUser,null,ud, money,
//						Const.UseraccountdealdetailFlag.REMIT);
//				useraccountService.refreshAccountes(null,parentUser,s,null,ud2,money,
//						Const.UseraccountdealdetailFlag.USER_DEPOSIT);
				
				
//				useraccountService.refreshAccountes(null,parentUser,user,null,ud3, money,
//						Const.UseraccountdealdetailFlag.REMIT);
//				
//				useraccountService.refreshAccountes(null,user,parentUser,null,ud4,money,
//						Const.UseraccountdealdetailFlag.USER_DEPOSIT);
				//先扣费
//				supplyKernel.purchaseBalance(mainorder);
//				useraccountService.refreshAccountes(mainorder,user,null,user.getUserName(),new UserCharge(),
//						mainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
				ICharge iCharge5 = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), user, null, null, Const.UseraccountdealdetailFlag.PURCHASE);
				ICharge iCharge6 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), (Supply)supply, null, null, Const.UseraccountdealdetailFlag.PURCHASE);
				UserChargeEngine.getInstance().addLast(iCharge5);
			    UserChargeEngine.getInstance().addLast(iCharge6);
//				int rs = supply.recharge(mainorder, posMsg.getMobile(),
//						supplyInterface);
//				// 增加一条operationLog
//				// operationLogService.addOperationLog(getSessionUser(),
//				// mainorder, mobile
//				// ,Const.OperationLogType.MOBILE_RECHARGE,Const.OperationLogResult.PROCESSING);
//				// 增加一条商品入库记录
//				CardLib cardLib = cardLibService.addCardLibForMobile(
//						supplyInterface, user);
//				// 销售商品id记录到mainorder
//				List<CardLib> cardLibs = new ArrayList<CardLib>();
//				cardLibs.add(cardLib);
//				orderService.updateOrderCardLibs(mainorder, cardLibs);
				//充值
			    logger.info("pos额度处理完成");
			} else {
				logger.error("没有该商品");
				// 没有这件商品
				reCode = "4001";
				// 保存posmsg
				posMsg.setRetCode(reCode);
				posMsgService.update(posMsg);
				reContent = DesUtil.desEncode(DesUtil.toHexString(PosKey
						.getKey()), posMsg.toString());
				logger.info("===========pos ECHO包recharge返回============" + reContent);
				return "show";
			}
		} else if (posMsg.getFuncCode().equals("01")) {
			// TODO: 购买充值卡,游戏卡
			logger.info("开始pos卡密流程");
			if (posMsg.getBusinessCode().equals("51963")) {
				supply = SupplyKernel.getInstance().getSupplyMapKeyId().get(16);
				// 购买数量,一次只能买
				// TODO:暂时写死天下通测试卡
				goods = goodsService.findById(63);
			} else {
				if (posMsg.getBusinessCode().equals("10086")) {
					type = 34;
				} else if (posMsg.getBusinessCode().equals("10000")) {
					type = 36;
				} else if (posMsg.getBusinessCode().equals("10010")) {
					type = 35;
				}
				goods = goodsService.findByTypeAndValue(type, value / 100);
				SupplyKernel supplyKernel = SupplyKernel.getInstance();
				supply = supplyKernel.getISupplyForCard(supplyKernel
						.getListBySellType(goods,3), goods, 1, 1);
			}
			SupplyInterface supplyInterface = supplyInterfaceService.findByUsergroupAndSupplyAndGoods(user.
					getUsergroup().getId(),
						supply.getId(), goods.getGoodsId());
			
			logger.info("增加pos卡密订单");
			mainorder = orderService.addMainOrder(mainorder, null, user,
					supplyInterface, 1, 1, posMsg.getTerminalNo(), 2);
			//记录为pos机划款
			logger.info("开始pos增加额度");
			UserCharge ud = new UserCharge();
			ud.setVoucherType(7);
			UserCharge ud2 = new UserCharge();
			ud2.setVoucherType(7);
			UserCharge ud3 = new UserCharge();
			ud3.setVoucherType(7);
			UserCharge ud4 = new UserCharge();
			ud4.setVoucherType(7);
			//useraccountService.refreshAccount(null, user,
			//		ud, money,
			//		Const.UseraccountdealdetailFlag.USER_DEPOSIT);
			Users parentUser = userService.findUserById(user.getParentUsers().getUserId());
			
			//管理员--一级商户
			ICharge iCharge = new Charge4User(ud, null, mainorder.getTotalMoney(), s, parentUser, user.getUserName(), Const.UseraccountdealdetailFlag.REMIT);
			ICharge iCharge2 = new Charge4User(ud2, null, mainorder.getTotalMoney(), parentUser, s, user.getUserName(), Const.UseraccountdealdetailFlag.USER_DEPOSIT);
			//一级商户--二级商户
			ICharge iCharge3 = new Charge4User(ud3, null, mainorder.getTotalMoney(), parentUser, user, user.getUserName(), Const.UseraccountdealdetailFlag.REMIT);
			ICharge iCharge4 = new Charge4User(ud4, null, mainorder.getTotalMoney(), user, parentUser, user.getUserName(), Const.UseraccountdealdetailFlag.USER_DEPOSIT);
			UserChargeEngine.getInstance().addLast(iCharge);
		    UserChargeEngine.getInstance().addLast(iCharge2);
		    UserChargeEngine.getInstance().addLast(iCharge3);
		    UserChargeEngine.getInstance().addLast(iCharge4);
			
//			useraccountService.refreshAccountes(null,s,parentUser,null,ud, mainorder.getTotalMoney(),
//					Const.UseraccountdealdetailFlag.REMIT);
//			useraccountService.refreshAccountes(null,parentUser,s,null,ud2,mainorder.getTotalMoney(),
//					Const.UseraccountdealdetailFlag.USER_DEPOSIT);
//			
//			//一级商户--二级商户
//			useraccountService.refreshAccountes(null,parentUser,user,null,ud3, mainorder.getTotalMoney(),
//					Const.UseraccountdealdetailFlag.REMIT);
//			useraccountService.refreshAccountes(null,user,parentUser,null,ud4,mainorder.getTotalMoney(),
//					Const.UseraccountdealdetailFlag.USER_DEPOSIT);
			List<CardLib> sellLibs = supply.buyCard(mainorder, orderdetail,
					supplyInterface, user, 1);
			orderService.addOrderDetail(orderdetail, mainorder, sellLibs);
			operationLogService.addOperationLog(user, mainorder, null,
					Const.OperationLogType.CARDLIB_BUY,
					Const.OperationLogResult.SUCCESS,null);
			ICharge iCharge5 = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), user, null, user.getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
			ICharge iCharge6 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), (Supply)supply, null, user.getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
			UserChargeEngine.getInstance().addLast(iCharge5);
			UserChargeEngine.getInstance().addLast(iCharge6);
//			SupplyKernel.getInstance().purchaseBalance(mainorder);
//			
//			useraccountService.refreshAccountes(mainorder,user,null,user.getUserName(),new UserCharge(),
//					mainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
			orderService.updateOrderCardLibs(mainorder, sellLibs);
			// 卡密信息用des加密
			// TODO:取出来的卡密要解密得到明文
			String desCard = "";
			StringBuffer sbuf = new StringBuffer();
			for (CardLib cardLib : sellLibs) {
				String cardInfo = cardLib.getCardCode() + "="
						+ cardLib.getCardPassword();
				desCard = DesUtil.desEncodeFill(DesUtil.toHexString(posMsg
						.getTerminalNo()), cardInfo);
				System.out.println("descard::::::::::::::::::::::" + desCard);
				sbuf.append(goods.getGoodsName());
				if (supply.getId()==16) {
					sbuf.append(",请到http://www.txtong.com.cn充值,有效期至"
							+ DateUtil.DateToString(cardLib.getExpireTime(),
									DateUtil.CM_SHORT_DATE_FORMAT)+",客服电话:020–86000440");
				} else {
					sbuf.append(",有效期至"
							+ DateUtil.DateToString(cardLib.getExpireTime(),
									DateUtil.CM_SHORT_DATE_FORMAT));
				}
						
			}
			posMsg.setCardCode(sbuf.toString());
			posMsg.setCardPwd(desCard);
//			// 生成订单号
//			StringBuffer sb = new StringBuffer();
//			sb.append("ryd");
//			sb.append(supplyInterface.getId());
//			// 生成时间戳
//			String date = DateUtil.DateToString(new Date(), DATE_FORMAT);
//			sb.append(date);
//			posMsg.setOrderId(sb.toString());
		} else {
			logger.error("pos参数错误");
			reCode = "6001";
		}

		// 保存posmsg
		posMsg.setRetCode(reCode);
		posMsg.setMainorder(mainorder.getMainOrderId());
		posMsgService.update(posMsg);
		reContent = DesUtil.desEncode(DesUtil.toHexString(PosKey.getKey()),
				posMsg.toString());
		logger.info("===========pos ECHO包recharge返回============" + reContent);
		return "show";
	}
	
	/**
	 * 卡密信息重新下发
	 * @return
	 * @throws Exception
	 */
	public String rePrint() throws Exception{
		System.out.println("================================" + msg);
		logger.info("================rePrint密文================" + msg);
		msg = DesUtil.desDecode(DesUtil.toHexString(PosKey.getKey()), msg
				.trim());
		System.out.println("--------------------------------" + msg);
		logger.info("----------------rePrint明文----------------" + msg);
		String[] msgs = msg.trim().split("\\|");
		// 检测报文长度
		String[] msgs18 = new String[18];
		if (msgs.length != 18) {
			// 强制增加到17位
			for (int i = 0; i < msgs18.length; i++) {
				if (i < 16) {
					msgs18[i] = msgs[i];
				} else {
					msgs18[i] = "";
				}
			}
		} else {
			msgs18 = msgs;
		}
		PosMsg posMsg = new PosMsg(msgs, 1);
		posMsg = posMsgService.addPosMsg(posMsg);
		String retCode = "0000";
		retCode = rePrintValidate(posMsg);
		// 验证正确
		PosMsg posMsg2 = null;
		if (retCode.equals("0000")) {
			List<PosMsg> list = posMsgService.findByOrderId(posMsg.getOrderId(),"02");
			List<PosMsg> list2 = posMsgService.findByOrderId(posMsg.getOrderId(),"03");
			if (list==null || list.isEmpty()) {
				// 还没有通知记录
				logger.error("pos rePrint返回找到没有通知记录");
				retCode = "7001";
				// 保存posmsg
				posMsg.setRetCode(retCode);
				posMsgService.update(posMsg);
				reContent = DesUtil.desEncode(DesUtil.toHexString(PosKey.getKey()),
						posMsg.toString());
				logger.info("===========pos ECHO包rePrint返回============" + reContent);
				return "show";
			}
			if (list2.size()>2) {
				// 超过2次,不能再下发
				retCode = "9001";
				// 保存posmsg
				logger.error("pos rePrint返回已下发超过2次");
				posMsg.setRetCode(retCode);
				posMsgService.update(posMsg);
				reContent = DesUtil.desEncode(DesUtil.toHexString(PosKey.getKey()),
						posMsg.toString());
				logger.info("===========pos ECHO包rePrint返回============" + reContent);
				return "show";
			}
			posMsg2 = list.get(0);
			posMsg.setCardCode(posMsg2.getCardCode());
			posMsg.setCardPwd(posMsg2.getCardPwd());
		}
		// 保存posmsg
		posMsg.setRetCode(retCode);
		posMsgService.update(posMsg);
		reContent = DesUtil.desEncode(DesUtil.toHexString(PosKey.getKey()),
				posMsg.toString());
		logger.info("===========pos ECHO包rePrint返回============" + reContent);
		return "show";
	}

	/**
	 * 查询验证
	 * 
	 * @param posMsg
	 * @return
	 * @throws Exception
	 */
	private String queryValidate(PosMsg posMsg) throws Exception {
		String code = "0000";
		if (!posMsg.getMsgType().equals("01")) {
			// 返回代码1001,消息类型错误
			code = "1001";
			posMsg.setRetCode(code);
			return code;
		}
		if (!macValidate()) {
			// 核对密钥,如错误,则返回1003
			code = "1003";
			posMsg.setRetCode(code);
			return code;
		}
		
		Double value = null;
		try {
			value = Double.valueOf(posMsg.getValue());
		} catch (Exception e) {
			code = "2002";
			posMsg.setRetCode(code);
			return code;
		}

		// TODO:验证手机号码所属的运营商,区域,暂时只有广东

		Users user = userService.findUserByTerminalNo(posMsg.getTerminalNo());
//		Users user = userService.getAdminUser();
		if (user == null) {
			// 没有这个终端
			code = "8001";
			// 保存posmsg
			posMsg.setRetCode(code);
			return code;
		}
		int type = 0;
		Goods goods = null;
		if (posMsg.getFuncCode().equals("00")) {
			if (posMsg.getMobile().length() != 11) {
				// 返回手机号不正确
				code = "2001";
				posMsg.setRetCode(code);
				return code;
			}
			String mobileNum = posMsg.getMobile().substring(0, 7);
			AreaCode areaCode = areaCodeService.findByMobile(mobileNum);
			if (areaCode == null) {
				code = "2001";
				posMsg.setRetCode(code);
				return code;
			}
			// 空中充值
			if (posMsg.getBusinessCode().equals("10086")
					&& areaCode.getBusinessType() != 1) {
				code = "2003";
				posMsg.setRetCode(code);
				return code;
			} else if (posMsg.getBusinessCode().equals("10000")
					&& areaCode.getBusinessType() != 2) {
				code = "2003";
				posMsg.setRetCode(code);
				return code;
			} else if (posMsg.getBusinessCode().equals("10010")
					&& areaCode.getBusinessType() != 3) {
				code = "2003";
				posMsg.setRetCode(code);
				return code;
			}
			goods = goodsService.findByAreaCodeAndValue(areaCode, value / 100);
			if (goods != null) {
				SupplyKernel supplyKernel = SupplyKernel.getInstance();
				ISupply supply = supplyKernel.getISupply(supplyKernel
						.getListBySellType(goods,3), 1, goods.getValue());
				if (supply == null) {
					code = "3001";
				}else {
					SupplyInterface supplyInterface = supplyInterfaceService.findByUsergroupAndSupplyAndGoods(user.
							getUsergroup().getId(),
								supply.getId(), goods.getGoodsId());
					if (supplyInterface==null || supplyInterface.getState()==0) {
						code = "3001";
					}else {
						posMsg.setMoney(SysUtil.formatDouble(supplyInterface.getRetailPrice()*100, "000000000000"));
					}
				}
			} else {
				// 没有这件商品
				code = "4001";
			}
		} else if (posMsg.getFuncCode().equals("01")) {
			if (posMsg.getBusinessCode().equals("51963")) {
				ISupply supply = SupplyKernel.getInstance().getSupplyMapKeyId().get(16);
				// 购买数量,一次只能买
				// goods = goodsService.findByTypeAndValue(2, value / 100);
				// TODO:暂时写死天下通测试卡
				goods = goodsService.findById(63);
				if (goods == null) {
					code = "4001";
				} else if (!supply.isLeftCard(goods, 1)) {
					code = "3001";
				}
			} else {
				//TODO:要根据具体goodsType查询(现在是test服务器的)
				if (posMsg.getBusinessCode().equals("10086")) {
					type = 34;
				} else if (posMsg.getBusinessCode().equals("10000")) {
					type = 36;
				} else if (posMsg.getBusinessCode().equals("10010")) {
					type = 35;
				}
				goods = goodsService.findByTypeAndValue(type, value / 100);
				if (goods != null) {
					SupplyKernel supplyKernel = SupplyKernel.getInstance();
					ISupply supply = supplyKernel.getISupplyForCard(
							supplyKernel.getListBySellType(goods,3), goods, 1, 1);
					if (supply == null) {
						code = "3001";
					}else {
						SupplyInterface supplyInterface = supplyInterfaceService.findByUsergroupAndSupplyAndGoods(user.
								getUsergroup().getId(),
									supply.getId(), goods.getGoodsId());
						if (supplyInterface==null || supplyInterface.getState()==0) {
							code = "3001";
						}else {
							posMsg.setMoney(SysUtil.formatDouble(supplyInterface.getRetailPrice()*100, "000000000000"));
						}
					}
				} else {
					// 没有这件商品
					code = "4001";
				}
			}
		} else if (posMsg.getFuncCode().equals("02")) {
			//Q币直充
			//数量goodsNo就是金额value
			int goodsNo = value.intValue();
			goods = goodsService.findByFlagProvinceCode(Const.GoodsFlag.GAME_RECHARGE, 0);//goodsService.findByKACardId(5178);
			if (goods != null) {
				SupplyKernel supplyKernel = SupplyKernel.getInstance();
				ISupply supply=supplyKernel.getISupply(supplyKernel.getListBySellType(goods,2),goodsNo,goods.getValue());
				if (supply == null) {
					code = "3001";
				}else {
					if (supply.getClazzName().equals("KASupply")) {
						supply=supplyKernel.getISupply(supplyKernel.getListBySellType(goods,supply.getId()+"",2),goodsNo,goods.getValue());
					}
					if ((goodsNo<10 || goodsNo>200) && supply.getClazzName().equals("ZhongrongSupply")) {
						supply=supplyKernel.getISupply(supplyKernel.getListBySellType(goods,supply.getId()+"",2),goodsNo,goods.getValue());
					}
					if (supply.getIsUsed()==0) {
						code = "3001";
					}else {
						SupplyInterface supplyInterface = supplyInterfaceService.findByUsergroupAndSupplyAndGoods(user.
								getUsergroup().getId(),
									supply.getId(), goods.getGoodsId());
						if (supplyInterface==null || supplyInterface.getState()==0) {
							code = "3001";
						}else {
							posMsg.setMoney(SysUtil.formatDouble(supplyInterface.getRetailPrice()*goodsNo*100, "000000000000"));
						}
					}
				}
			} else {
				// 没有这件商品
				code = "4001";
			}
		} else {
			// 没有定义该功能
			code = "5001";
		}
		posMsg.setRetCode(code);
		return code;
	}

	private String rechargeValidate(PosMsg posMsg) throws Exception {
		String code = "0000";
		if (!posMsg.getMsgType().equals("02")) {
			// 返回代码1001,消息类型错误
			code = "1001";
		}
		// if (!macValidate()) {
		// //核对密钥,如错误,则返回1003
		// code = "1003";
		// }
//		if (posMsg.getBusinessCode()==null||posMsg.getBusinessCode().equals("")) {
//			code = "1006";
//		}
//		if (posMsg.getBankCardcode()==null||posMsg.getBankCardcode().equals("")) {
//			code = "1007";
//		}
		posMsg.setRetCode(code);
		return code;
	}
	
	private String rePrintValidate(PosMsg posMsg) throws Exception {
		String code = "0000";
		if (!posMsg.getMsgType().equals("03")) {
			// 返回代码1001,消息类型错误
			code = "1001";
		}
		// if (!macValidate()) {
		// //核对密钥,如错误,则返回1003
		// code = "1003";
		// }
		posMsg.setRetCode(code);
		return code;
	}

	/**
	 * 核对mac
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean macValidate() throws Exception {
		// 获取加密key
		String key = PosKey.getKey();
		// 截取报文的MAC
		String msgmac = msg.substring(msg.lastIndexOf("|") + 1, msg.length())
				.trim();
		// 截取主报文
		String msgMain = msg.substring(0, msg.lastIndexOf("|") + 1).trim();
		String desedmac = DesUtil.calcMac(DesUtil.toHexString(key), msgMain);
		// des(主报文+key)?报文MAC
		return msgmac.equals(desedmac.toUpperCase().substring(0, 8));
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReContent() {
		return reContent;
	}

	public void setReContent(String reContent) {
		this.reContent = reContent;
	}

}
