package com.sys.volunteer.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mprs.util.MD5;
import com.sys.volunteer.area.AreaCodeService;
import com.sys.volunteer.area.AreaService;
import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.cardLib.CardLibService;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.KAUtil;
import com.sys.volunteer.common.Privilege;
import com.sys.volunteer.common.ResponseUtils;
import com.sys.volunteer.common.StringUtil;
import com.sys.volunteer.common.SysUtil;
import com.sys.volunteer.goods.GoodsService;
import com.sys.volunteer.goods.GoodsTypeService;
import com.sys.volunteer.ka.KaService;
import com.sys.volunteer.ka.Query91KAProduct;
import com.sys.volunteer.muticharge.engine.OrderVO;
import com.sys.volunteer.operationLog.OperationLogService;
import com.sys.volunteer.orderQuery.OrderQueryResponseNewService;
import com.sys.volunteer.pojo.Area;
import com.sys.volunteer.pojo.AreaCode;
import com.sys.volunteer.pojo.BatchOrder;
import com.sys.volunteer.pojo.CardLib;
import com.sys.volunteer.pojo.GameRechargeRequest;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.GoodsType;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Orderdetail;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Useraccount;
import com.sys.volunteer.pojo.Useraccountdealdetail;
import com.sys.volunteer.pojo.Usergroup;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.pojo.XunyuanQueryBalanceReq;
import com.sys.volunteer.pojo.XunyuanQueryBalanceResp;
import com.sys.volunteer.pojo.Zhongrong;
import com.sys.volunteer.pojo.orderUtils.ModifyOrderManual;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.supply.usage.ISupply;
import com.sys.volunteer.supplyinterface.SupplyInterfaceService;
import com.sys.volunteer.terminalAirDepositResponseNew.DepositResponseService;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.useraccountdealdetail.UseraccountDealDetailService;
import com.sys.volunteer.usercharge.UserChargeEngine;
import com.sys.volunteer.usercharge.engine.ICharge;
import com.sys.volunteer.usercharge.engine.charge.Charge4Supply;
import com.sys.volunteer.usercharge.engine.charge.Charge4User;
import com.sys.volunteer.users.UserService;
import com.sys.volunteer.vo.UrlLinks;
import com.sys.volunteer.xunyuan.XunYuanService;
import com.sys.volunteer.xunyuan.netty.core.XunyuanKernel;
import com.sys.volunteer.zhongrong.ZhongrongService;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class OrderAction extends BaseAction {

	@Resource
	UserService userService;
	@Resource
	OrderService orderService;
	@Resource
	SupplyService supplyService;
	@Resource
	UseraccountService useraccountService;
	@Resource
	OperationLogService operationLogService;
	@Resource
	UseraccountDealDetailService useraccountDealDetailService;
	@Resource
	DepositResponseService depositResponseService;
	@Resource
	OrderQueryResponseNewService orderQueryResponseNewService;
	@Resource
	GoodsService goodsService;
	@Resource
	GoodsTypeService goodsTypeService;
	@Resource
	CardLibService cardLibService;
	@Resource
	SupplyInterfaceService supplyInterfaceService;
	@Resource
	AreaCodeService areaCodeService;
	@Resource
	XunYuanService xunYuanService;
	@Resource
	AreaService areaService;
	@Resource
	KaService kaService;
	@Resource
	ZhongrongService zhongrongService;

	private Mainorder mainorder;

	private Orderdetail orderdetail;

	private Integer supplyId;
	
	private Integer supplyInterfaceId;

	private String batchOrderId;
	
	private String orderId;
	
	private String orderIds;

	private Integer money;

	private Double useableAccount;

	private String mobile;
	
	private Integer goodsFlag;

	private Integer goodsType;

	private String creditPassword;

	private String confirmMobile;

	private Integer goodsId;

	private List<Goods> goodsList;
	
	private Integer goodsTypeId;
	
	private List<GoodsType> goodsTypeList;

	private Integer goodsNo;

	private Integer value;
	
	private String userPassword;
	
	private String businessPassword;
	

	private Double priceShow;
	
	private ModifyOrderManual modifyOrderManual;
	
	private Integer tradeType;
	
	private XunyuanQueryBalanceResp resp;
	
	private String storeSeq;
	
	private Integer result;
	
	private Double amount;
	
	private String commentsOn;

	List<OrderVO> listLimitFiveRefresh;
	
	private String formUrl;
	private String JsUrl;
	
	private Query91KAProduct queryProduct;
	
	private String gameId;
	private String autoGameId;
	private Integer kaBaseNum;
	private Integer kaCardId;
	
	public String getBusinessPassword() {
		return businessPassword;
	}

	public void setBusinessPassword(String businessPassword) {
		this.businessPassword = businessPassword;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public String goodsIdStr;

	/**
	 * 移动号码
	 */
	private String[] mobileList = {"134","135","136","137","138","139","147","150","151","152","157","158","159","181","182","183","187","188"};
	/**
	 * 联通号码
	 */
	private String[] unicomList = {"130","131","132","145","155","156","186"};
	/**
	 * 电信号码
	 */
	private String[] telecomList = {"133","153","180","189"};
	
	
	/**
	 * 显示电话号码归属地
	 * @return
	 * @throws Exception
	 */
	public String showMobileGSD() throws Exception {
		
		Useraccount useraccount = useraccountService
		.findUseraccountByUseraccountId(getSessionUser());
         useableAccount = useraccount.getBalance()
		 + useraccount.getCreditLimit() - useraccount.getFreezeMoney();
		
		String subMobile = mobile.substring(0, 7);
		AreaCode areaCode = areaCodeService.findByMobile(subMobile);
//		ServletActionContext.getContext().put("areaCode", areaCode);
		JSONObject json = new JSONObject();
		if(areaCode!=null){
			json.put("province", areaCode.getProvince());
			json.put("city", areaCode.getCity());
			json.put("flag", 1);
			int temp = areaCode.getBusinessType();
			switch(temp){
			case 1:
				json.put("businessType", "移动");
				break;
			case 2:
				json.put("businessType", "电信");
				break;
			case 3:
				json.put("businessType", "联通");
				break;
			}
		}else{
			json.put("province", "未知");
			json.put("city", "未知");
			json.put("businessType", "未知");
			json.put("flag", 0);
		}
		
		ResponseUtils.renderJson(getHttpServletResponse(), json.toString());
		return null;
	}
	
	/**
	 * 选择充值接口
	 * 
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "order", privilegeAccess = Const.PrivilegeAccessConst.ADD)
	public String selectInterface() throws Exception {
		Users users=getSessionUser();
		if (users.getUsergroup().getGroupType()==Const.GroupType.ADMIN||users.getUsergroup().getGroupType()==Const.GroupType.GRADE_TWO) {
			return "do";
		}else {
			return ShowMessage(MSG_TYPE_STOP, "请登陆二级商户进行交易", "", "", 0);
		}
		
	}
	
	/**
	 * 批量增加空充订单页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "batchMobileOrder", privilegeAccess = Const.PrivilegeAccessConst.ADD)
	public String batchMobileOrder() throws Exception {
		newOrder();
		return "batchMobileOrder";
	}

	/**
	 * 全国直冲查询前五条数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "mobileOrder", privilegeAccess = Const.PrivilegeAccessConst.ADD)
	public String addMobileOrder() throws Exception {
		newOrder();
		Users user = this.getSessionUser();
		List<OrderVO> listLimitFive = orderService.findLimitFive(user.getUserId());
		Useraccount ua = useraccountService.findUseraccountByUseraccountId(user);
		this.getSession().put("useraccount", ua);
		this.getSession().put("listLimitFive", listLimitFive);
		return "mobileOrder";
	}
	
	/**
	 * 增加卡密订单页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "gameCardOrder", privilegeAccess = Const.PrivilegeAccessConst.ADD)
	public String addGameCardOrder() throws Exception {
		newOrder();
		// 游戏卡密页面
		goodsList = goodsService.getGoodsListByFlag(Const.GoodsFlag.GAME_CARD);
		return "gameCardOrder";
	}
	
	/**
	 * 批量增加卡密订单页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doBatchMobileOrderExPage() throws Exception {
		newOrder();
		// 游戏卡密页面
		goodsList = goodsService.getGoodsListByFlag(Const.GoodsFlag.GAME_CARD);
		return "doBatchMobileOrderEx";
	}
	
	/**
	 * 增加订单备注
	 */
	public String addOrderComments(){
		mainorder = orderService.findOrderById(orderId);
		if(mainorder!=null){
			mainorder.setCommentsOn(commentsOn);
			orderService.updateOrderComments(mainorder);
			ResponseUtils.renderText(getHttpServletResponse(), "操作成功！");
		}else{
			ResponseUtils.renderText(getHttpServletResponse(), "操作失败！");
		}
		return null;
	}
	
	private void newOrder() {
		mainorder = new Mainorder();
		orderdetail = new Orderdetail();
		Useraccount useraccount = useraccountService
				.findUseraccountByUseraccountId(getSessionUser());
		useableAccount = useraccount.getBalance()
				+ useraccount.getCreditLimit() - useraccount.getFreezeMoney();
	}

	public String checkSupplyCanreverse(){
		String mobileNum = mobile.substring(0,7);
		AreaCode areaCode = areaCodeService.findByMobile(mobileNum);
		Goods goods = goodsService.findByAreaCodeAndValue(areaCode, priceShow);
		if (goods == null) {
			ResponseUtils.renderText(getHttpServletResponse(), "由于该地区供应商系统维护，暂时不能为该号码进行充值，不便之处，敬请原谅!");
			return null;
		}
		SupplyKernel supplyKernel=SupplyKernel.getInstance();
		ISupply supply=supplyKernel.getISupply(supplyKernel.getListBySellType(goods,2),1,goods.getValue());
		
		if (supply==null) {
			ResponseUtils.renderJson(getHttpServletResponse(), "由于该地区供应商系统维护，暂时不能为该号码进行充值，不便之处，敬请原谅!");
			return null;
		}
		SupplyInterface supplyInterface = supplyInterfaceService.findBySupplyAndGoods(supply.getId(), goods.getGoodsId());
		if(supplyInterface.getCanReverse()==0){
			ResponseUtils.renderJson(getHttpServletResponse(), "此号码不能冲正，请确认手机号码正确！");
			return null;
		}
		return null;
	}
	
	/**
	 * 增加手机充值订单提交
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doAddMobileOrder() throws Exception {
		if (SysUtil.isNull(mobile)) {
			return ShowMessage(MSG_TYPE_STOP, "请填写手机", "点击返回", "page!addMobileOrder.do", 0);
		}
		if (mobile.length()!=11) {
			return ShowMessage(MSG_TYPE_STOP, "请输入正确的手机号码", "点击返回", "page!addMobileOrder.do", 0);
		}
		if (SysUtil.isNull(priceShow)||priceShow<=0) {
			return ShowMessage(MSG_TYPE_STOP, "请填写充值金额", "点击返回",
					"page!addMobileOrder.do?mobile="+mobile, 0);
		}
		if (SysUtil.isNull(businessPassword)||businessPassword.equals("")) {
			return ShowMessage(MSG_TYPE_STOP, "请填写密码", "点击返回",
					"page!addMobileOrder.do?mobile="+mobile+"&priceShow="+priceShow, 0);
		}
//		String password = getSessionUser().getUserPassword();
		String password = getSessionUser().getBusinessPassword();
		if (!MD5.getiInstance().getMD5ofStr(businessPassword).equals(password)) {
			return ShowMessage(MSG_TYPE_STOP, "密码错误", "点击返回",
					"page!addMobileOrder.do?mobile="+mobile+"&priceShow="+priceShow, 0);
		}
		// Users user=userService.findUserByMobile(mobile);
		// TODO:取消了账户密码
		/*Goods goods = goodsService.findById(goodsId);
		Double money=Double.valueOf(goods.getValue());
		Integer result = useraccountService.validatePurchase(getSessionUser(), money);
		switch (result) {
		// 返回1表示空账户,返回0表示充值成功,2表示充值失败,3表示密码错误
		case Const.UseraccountResult.NULL:
			return ShowMessage(MSG_TYPE_STOP, "该账户没注册", "点击返回",
					"page!selectInterface.do", 0);
		case Const.UseraccountResult.FAIL:
			return ShowMessage(MSG_TYPE_STOP, "充值失败", "点击返回",
					"page!selectInterface.do", 0);
		case Const.UseraccountResult.WRONG_PASSWORD:
			return ShowMessage(MSG_TYPE_STOP, "账户密码错误", "点击返回",
					"page!selectInterface.do", 0);
		}*/
		//TODO: 这里默认买一件
		//GoodsType goodsType=(GoodsType)goodsTypeService.findById(GoodsType.class, goodsTypeId);
		String mobileNum = mobile.substring(0,7);
		AreaCode areaCode = areaCodeService.findByMobile(mobileNum);
		if (areaCode==null) {
			return ShowMessage(MSG_TYPE_STOP, "请输入正确的手机号码", "点击返回", "page!addMobileOrder.do", 0);
		}
		Goods goods = goodsService.findByAreaCodeAndValue(areaCode, priceShow);
		if (goods == null) {
			return ShowMessage(MSG_TYPE_STOP, "很抱歉，您不能为该号码充值", "重新添加订单", "page!addMobileOrder.do", 0);
		}
		SupplyKernel supplyKernel=SupplyKernel.getInstance();
		ISupply supply=supplyKernel.getISupply(supplyKernel.getListBySellType(goods,2),1,goods.getValue());
		if (supply==null) {
			return ShowMessage(MSG_TYPE_STOP, "由于该地区供应商系统维护，暂时不能为该号码进行充值，不便之处，敬请原谅!", "", "", 0);
		}
		Users users = userService.findUserById(getSessionUser().getUserId());
		SupplyInterface supplyInterface = supplyInterfaceService.findByUsergroupAndSupplyAndGoods(users.
				getUsergroup().getId(),
					supply.getId(), goods.getGoodsId());
		if (supplyInterface==null || supplyInterface.getState()==0) {
			return ShowMessage(MSG_TYPE_STOP, "由于该地区供应商系统维护，暂时不能为该号码进行充值，不便之处，敬请原谅!", "", "", 0);
		}
		Double money=new Double(supplyInterface.getValue());
		Integer result = useraccountService.validatePurchase(getSessionUser(), money);
		switch (result) {
		// 返回1表示空账户,返回0表示充值成功,2表示充值失败,3表示密码错误
		case Const.UseraccountResult.NULL:
			return ShowMessage(MSG_TYPE_STOP, "该账户没注册", "点击返回",
					"page!addMobileOrder.do", 0);
		case Const.UseraccountResult.FAIL:
			return ShowMessage(MSG_TYPE_STOP, "余额不足,请充值", "点击返回",
					"page!addMobileOrder.do", 0);
		case Const.UseraccountResult.WRONG_PASSWORD:
			return ShowMessage(MSG_TYPE_STOP, "账户密码错误", "点击返回",
					"page!addMobileOrder.do", 0);
		}
		
		//生成订单
		mainorder = orderService.addMainOrder(mainorder, mobile, getSessionUser(),
				supplyInterface, 1,0,"",1);
		orderService.addOrderDetail(orderdetail, mainorder, null);
		//先扣费
//		supplyKernel.purchaseBalance(mainorder);
		ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), getSessionUser(), null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
		ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), (Supply)supply, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
		UserChargeEngine.getInstance().addLast(iCharge);
	    UserChargeEngine.getInstance().addLast(iCharge2);
//		useraccountService.refreshAccountes(mainorder,mainorder.getUsers(),null,null,new UserCharge(),
//				mainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
		//充值
		//TODO: 这里创建线程
		int rs=supply.recharge(mainorder, mobile, supplyInterface);
		if (rs == Const.OperationLogResult.SUCCESS || rs == Const.OperationLogResult.PROCESSING) {
			//增加一条operationLog
			//operationLogService.addOperationLog(getSessionUser(), mainorder,  mobile ,Const.OperationLogType.MOBILE_RECHARGE,Const.OperationLogResult.PROCESSING);
			//增加一条商品入库记录
			CardLib cardLib=cardLibService.addCardLibForMobile(supplyInterface, getSessionUser());
			//销售商品id记录到mainorder
			List<CardLib> cardLibs=new ArrayList<CardLib>();
			cardLibs.add(cardLib);
			orderService.updateOrderCardLibs(mainorder, cardLibs);
			UrlLinks links = new UrlLinks("继续添加订单", "page!addMobileOrder.do");
			UrlLinks links2 = new UrlLinks("返回列表", "list.do");
			return ShowMessage(MSG_TYPE_NORMAL, "操作已成功,话费会在5分钟内充值到账户", 3, links,links2);
		}
		//在查询订单状态时转行订单状态,这里不需updateOrderState为FAILED
		return ShowMessage(MSG_TYPE_STOP, "操作失败,请联系客服", "重新添加订单", "page!addMobileOrder.do", 0);
	}
	
	
	/**
	 * 增加手机充值订单提交(线程用)记录操作员
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doAddMobileOrderExe() throws Exception {
		if (SysUtil.isNull(mobile)) {
			return ShowMessage(MSG_TYPE_STOP, "请填写手机", "点击返回", "page!addMobileOrder.do", 0);
		}
		if (mobile.length()!=11) {
			return ShowMessage(MSG_TYPE_STOP, "请输入正确的手机号码", "点击返回", "page!addMobileOrder.do", 0);
		}
		if (SysUtil.isNull(priceShow)||priceShow<=0d) {
			return ShowMessage(MSG_TYPE_STOP, "请填写充值金额", "点击返回",
					"page!addMobileOrder.do?mobile="+mobile, 0);
		}
		if (SysUtil.isNull(businessPassword)||businessPassword.equals("")) {
			return ShowMessage(MSG_TYPE_STOP, "请填写密码", "点击返回",
					"page!addMobileOrder.do?mobile="+mobile+"&priceShow="+priceShow, 0);
		}
//		String password = getSessionUser().getUserPassword();
		String password = getSessionUser().getBusinessPassword();
		if (!MD5.getiInstance().getMD5ofStr(businessPassword).equals(password)) {
			return ShowMessage(MSG_TYPE_STOP, "密码错误", "点击返回",
					"page!addMobileOrder.do?mobile="+mobile+"&priceShow="+priceShow, 0);
		}
		// Users user=userService.findUserByMobile(mobile);
		// TODO:取消了账户密码
		/*Goods goods = goodsService.findById(goodsId);
		Double money=Double.valueOf(goods.getValue());
		Integer result = useraccountService.validatePurchase(getSessionUser(), money);
		switch (result) {
		// 返回1表示空账户,返回0表示充值成功,2表示充值失败,3表示密码错误
		case Const.UseraccountResult.NULL:
			return ShowMessage(MSG_TYPE_STOP, "该账户没注册", "点击返回",
					"page!selectInterface.do", 0);
		case Const.UseraccountResult.FAIL:
			return ShowMessage(MSG_TYPE_STOP, "充值失败", "点击返回",
					"page!selectInterface.do", 0);
		case Const.UseraccountResult.WRONG_PASSWORD:
			return ShowMessage(MSG_TYPE_STOP, "账户密码错误", "点击返回",
					"page!selectInterface.do", 0);
		}*/
		//TODO: 这里默认买一件
		//GoodsType goodsType=(GoodsType)goodsTypeService.findById(GoodsType.class, goodsTypeId);
		String mobileNum = mobile.substring(0,7);
		AreaCode areaCode = areaCodeService.findByMobile(mobileNum);
		if (areaCode==null) {
			return ShowMessage(MSG_TYPE_STOP, "请输入正确的手机号码", "点击返回", "page!addMobileOrder.do", 0);
		}
		Goods goods = goodsService.findByAreaCodeAndValue(areaCode, priceShow);
		if (goods == null) {
			return ShowMessage(MSG_TYPE_STOP, "很抱歉，您不能为该号码充值", "重新添加订单", "page!addMobileOrder.do", 0);
		}
		SupplyKernel supplyKernel=SupplyKernel.getInstance();
		ISupply supply=supplyKernel.getISupply(supplyKernel.getListBySellType(goods,2),1,goods.getValue());
		if (supply==null) {
			return ShowMessage(MSG_TYPE_STOP, "由于该地区供应商系统维护，暂时不能为该号码进行充值，不便之处，敬请原谅!", "", "", 0);
		}
		Users users = userService.findUserById(getSessionUser().getUserId());
		SupplyInterface supplyInterface = supplyInterfaceService.findByUsergroupAndSupplyAndGoods(users.
				getUsergroup().getId(),
					supply.getId(), goods.getGoodsId());
		if (supplyInterface==null || supplyInterface.getState()==0) {
			return ShowMessage(MSG_TYPE_STOP, "由于该地区供应商系统维护，暂时不能为该号码进行充值，不便之处，敬请原谅!", "", "", 0);
		}
		Double money=new Double(supplyInterface.getValue());
		Integer result = useraccountService.validatePurchase(getSessionUser(), money);
		switch (result) {
		// 返回1表示空账户,返回0表示充值成功,2表示充值失败,3表示密码错误
		case Const.UseraccountResult.NULL:
			return ShowMessage(MSG_TYPE_STOP, "该账户没注册", "点击返回",
					"page!addMobileOrder.do", 0);
		case Const.UseraccountResult.FAIL:
			return ShowMessage(MSG_TYPE_STOP, "余额不足,请充值", "点击返回",
					"page!addMobileOrder.do", 0);
		case Const.UseraccountResult.WRONG_PASSWORD:
			return ShowMessage(MSG_TYPE_STOP, "账户密码错误", "点击返回",
					"page!addMobileOrder.do", 0);
		}
		
		
		//生成订单
		mainorder = orderService.addMainOrder(mainorder, mobile, getSessionUser(),
				supplyInterface, 1,0,"",1);
		orderService.addOrderDetail(orderdetail, mainorder, null);
		//先扣费
		ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), getSessionUser(), null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
		ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), (Supply)supply, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
		UserChargeEngine.getInstance().addLast(iCharge);
	    UserChargeEngine.getInstance().addLast(iCharge2);
//		supplyKernel.purchaseBalance(mainorder);
//		useraccountService.refreshAccount(mainorder,mainorder.getUsers(),new Useraccountdealdetail(),
//				mainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
		//记录操作员(2012.10.24)
//		useraccountService.refreshAccountes(mainorder,mainorder.getUsers(),null,getSessionUser().getUserName(),new UserCharge(),
//				mainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
		//充值
		//在查询订单状态时转行订单状态,这里不需updateOrderState为FAILED
		UrlLinks links = new UrlLinks("继续添加订单", "page!addMobileOrder.do");
		UrlLinks links2 = new UrlLinks("返回列表", "list.do");
		return ShowMessage(MSG_TYPE_NORMAL, "系统已受理", 3, links,links2);
	}
	
	
	/**
	 * 增加电信订单提交(线程用)记录操作员
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doChargeTelecom() throws Exception {
		if (SysUtil.isNull(mobile)) {
			return ShowMessage(MSG_TYPE_STOP, "请填写手机", "点击返回", "page!queryBalance.do", 0);
		}
		if (mobile.length()>13) {
			return ShowMessage(MSG_TYPE_STOP, "号码长度不合法!", "点击返回", "page!queryBalance.do", 0);
		}
		if (SysUtil.isNull(businessPassword)||businessPassword.equals("")) {
			return ShowMessage(MSG_TYPE_STOP, "请填写密码", "点击返回",
					"page!queryBalance.do?mobile="+mobile+"&amount ="+amount, 0);
		}
		String businessPassword2 = getSessionUser().getBusinessPassword();
		if (!MD5.getiInstance().getMD5ofStr(businessPassword).equals(businessPassword2)) {
			return ShowMessage(MSG_TYPE_STOP, "密码错误", "点击返回",
					"page!queryBalance.do?mobile="+mobile+"&amount ="+amount, 0);
		}
		// Users user=userService.findUserByMobile(mobile);
		// TODO:取消了账户密码
		/*Goods goods = goodsService.findById(goodsId);
		Double money=Double.valueOf(goods.getValue());
		Integer result = useraccountService.validatePurchase(getSessionUser(), money);
		switch (result) {
		// 返回1表示空账户,返回0表示充值成功,2表示充值失败,3表示密码错误
		case Const.UseraccountResult.NULL:
			return ShowMessage(MSG_TYPE_STOP, "该账户没注册", "点击返回",
					"page!selectInterface.do", 0);
		case Const.UseraccountResult.FAIL:
			return ShowMessage(MSG_TYPE_STOP, "充值失败", "点击返回",
					"page!selectInterface.do", 0);
		case Const.UseraccountResult.WRONG_PASSWORD:
			return ShowMessage(MSG_TYPE_STOP, "账户密码错误", "点击返回",
					"page!selectInterface.do", 0);
		}*/
		//TODO: 这里默认买一件
		//GoodsType goodsType=(GoodsType)goodsTypeService.findById(GoodsType.class, goodsTypeId);
//		String mobileNum = mobile.substring(0,7);
//		AreaCode areaCode = areaCodeService.findByMobile(mobileNum);
//		if (areaCode==null) {
//			return ShowMessage(MSG_TYPE_STOP, "请输入正确的手机号码", "点击返回", "page!addMobileOrder.do", 0);
//		}
		//TODO:这里写死
		String mobileNum = mobile.substring(0,3);
		if (!mobileNum.equals("020")) {
			mobileNum = mobile.substring(0,4);
		}
		Area area = areaService.findByAreaCode(mobileNum);
		if (area==null) {
			return ShowMessage(MSG_TYPE_STOP, "请输入正确的固定电话号码,要求区号+号码", "点击返回", "page!queryBalance.do", 0);
		}
		Goods goods = goodsService.findByFlagProvinceCode(tradeType, area.getProvinceCode());
		if (goods == null) {
			return ShowMessage(MSG_TYPE_STOP, "很抱歉，您不能为该号码充值", "重新添加订单", "page!queryBalance.do", 0);
		}
		SupplyKernel supplyKernel=SupplyKernel.getInstance();
		ISupply supply=supplyKernel.getSupplyMapKeyId().get(25);//supplyKernel.getISupply(supplyKernel.getListBySellType(goods,2),1,amount);
		if (supply==null||supply.getIsUsed()==0) {
			return ShowMessage(MSG_TYPE_STOP, "由于该地区供应商系统维护，暂时不能为该号码进行充值，不便之处，敬请原谅!", "", "", 0);
		}
		Users users = userService.findUserById(getSessionUser().getUserId());
		SupplyInterface supplyInterface = supplyInterfaceService.findByUsergroupAndSupplyAndGoods(users.
				getUsergroup().getId(),
					supply.getId(), goods.getGoodsId());
		if (supplyInterface==null || supplyInterface.getState()==0) {
			return ShowMessage(MSG_TYPE_STOP, "由于该地区供应商系统维护，暂时不能为该号码进行充值，不便之处，敬请原谅!", "", "", 0);
		}
		Double money=amount;
		Integer result = useraccountService.validatePurchase(getSessionUser(), money);
		switch (result) {
		// 返回1表示空账户,返回0表示充值成功,2表示充值失败,3表示密码错误
		case Const.UseraccountResult.NULL:
			return ShowMessage(MSG_TYPE_STOP, "该账户没注册", "点击返回",
					"page!queryBalance.do", 0);
		case Const.UseraccountResult.FAIL:
			return ShowMessage(MSG_TYPE_STOP, "余额不足,请充值", "点击返回",
					"page!queryBalance.do", 0);
		case Const.UseraccountResult.WRONG_PASSWORD:
			return ShowMessage(MSG_TYPE_STOP, "账户密码错误", "点击返回",
					"page!queryBalance.do", 0);
		}
		//生成订单
		mainorder = orderService.addMainOrderForTelecom(mainorder, mobile, money, getSessionUser(),
				supplyInterface, 1,0,"",1);
		orderService.addOrderDetail(orderdetail, mainorder, null);
		//先扣费
		ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), getSessionUser(), null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
		ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), (Supply)supply, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
		UserChargeEngine.getInstance().addLast(iCharge);
	    UserChargeEngine.getInstance().addLast(iCharge2);
//		supplyKernel.purchaseBalance(mainorder);
//		useraccountService.refreshAccount(mainorder,mainorder.getUsers(),new Useraccountdealdetail(),
//				mainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
		//记录操作员(2012.10.24)
//		useraccountService.refreshAccountes(mainorder,mainorder.getUsers(),null,getSessionUser().getUserName(),new UserCharge(),
//				mainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
		//充值
		//在查询订单状态时转行订单状态,这里不需updateOrderState为FAILED
		UrlLinks links = new UrlLinks("继续添加订单", "page!queryBalance.do");
		UrlLinks links2 = new UrlLinks("返回列表", "list.do");
		return ShowMessage(MSG_TYPE_NORMAL, "系统已受理", 3, links,links2);
	}
	
	/**
	 * 游戏直充页面
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "gameRechargeOrder", privilegeAccess = Const.PrivilegeAccessConst.ADD)
	public String addGameRechargeOrder() throws Exception {
		Goods goods = goodsService.findByKACardId(kaCardId);
		queryProduct = new Query91KAProduct();
		String qp = KAUtil.queryProduct(goods.getKaCardId()+"");
		log.info("91ka queryProduct返回:"+qp);
		queryProduct.init(qp);
		log.info(queryProduct.getAutogameId());
		autoGameId = queryProduct.getAutogameId();
		gameId = queryProduct.getGameId();
		formUrl = KAUtil.askForm(autoGameId);
		JsUrl = KAUtil.askJS(autoGameId);
		kaBaseNum = goods.getKaBaseNum();
		newOrder();
		return "gameRecharge";
	}
	
	/**
	 * 游戏订单提交
	 * @return
	 * @throws Exception
	 */
	public String doAddGameRechargeOrder() throws Exception {
		if (SysUtil.isNull(businessPassword)||businessPassword.equals("")) {
			return ShowMessage(MSG_TYPE_STOP, "请填写密码", "点击返回",
					"page!addGameRechargeOrder.do?kaCardId=5178", 0);
		}
		Goods goods = goodsService.findByKACardId(Integer.valueOf(gameId));
		if (goods==null || goods.getIsUsed()==0) {
			return ShowMessage(MSG_TYPE_STOP, "由于该地区供应商系统维护，暂时不能为该号码进行充值，不便之处，敬请原谅!", "", "", 0);
		}
		Map<String, String[]> paraMap = getHttpServletRequest().getParameterMap();
		Iterator iterator = paraMap.entrySet().iterator();
		int goodsNo = 0;
		String mobile = "";
		String at = "";
		while (iterator.hasNext()) {
			Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) iterator.next();
			String key = entry.getKey();
			String keySub = key.substring(0,3);
			if (keySub.equals("at_")) {
				at += key+"="+entry.getValue()[0]+"&";
			}
			if (key.equals("at_num")) {
				goodsNo = Integer.valueOf(entry.getValue()[0]);
			}
			if (key.equals("at_ele1")) {
				mobile = entry.getValue()[0];
			}
		}
		SupplyKernel supplyKernel=SupplyKernel.getInstance();
		ISupply supply=supplyKernel.getISupply(supplyKernel.getListBySellType(goods,2),goodsNo,goods.getValue());
		if ((goodsNo<10 || goodsNo>200) && supply.getClazzName().equals("ZhongrongSupply")) {
			supply=supplyKernel.getISupply(supplyKernel.getListBySellType(goods,supply.getId()+"",2),goodsNo,goods.getValue());
		}
		if (supply==null || supply.getIsUsed()==0) {
			return ShowMessage(MSG_TYPE_STOP, "由于该地区供应商系统维护，暂时不能为该号码进行充值，不便之处，敬请原谅!", "", "", 0);
		}
		Users users = userService.findUserById(getSessionUser().getUserId());
		SupplyInterface supplyInterface = supplyInterfaceService.findByUsergroupAndSupplyAndGoods(users.
				getUsergroup().getId(),
					supply.getId(), goods.getGoodsId());
		if (supplyInterface==null || supplyInterface.getState()==0) {
			return ShowMessage(MSG_TYPE_STOP, "由于该地区供应商系统维护，暂时不能为该号码进行充值，不便之处，敬请原谅!", "", "", 0);
		}
		Double money=(double)goods.getValue()*goodsNo;
		Integer result = useraccountService.validatePurchase(getSessionUser(), money);
		switch (result) {
		// 返回1表示空账户,返回0表示充值成功,2表示充值失败,3表示密码错误
		case Const.UseraccountResult.NULL:
			return ShowMessage(MSG_TYPE_STOP, "该账户没注册", "点击返回",
					"page!addGameRechargeOrder.do?kaCardId=5178", 0);
		case Const.UseraccountResult.FAIL:
			return ShowMessage(MSG_TYPE_STOP, "余额不足,请充值", "点击返回",
					"page!addGameRechargeOrder.do?kaCardId=5178", 0);
		case Const.UseraccountResult.WRONG_PASSWORD:
			return ShowMessage(MSG_TYPE_STOP, "账户密码错误", "点击返回",
					"page!addGameRechargeOrder.do?kaCardId=5178", 0);
		}
		//生成订单
		mainorder = orderService.addMainOrderForGameRecharge(mainorder, mobile, (double)goods.getValue(), getSessionUser(),
				supplyInterface, goodsNo,0,"",1,at, gameId, autoGameId, "", ""); 
		orderService.addOrderDetail(orderdetail, mainorder, null);
		
		//先扣费
		ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), getSessionUser(), null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
		ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder,supplyInterface.getStockPrice()*goodsNo, (Supply)supply, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
		UserChargeEngine.getInstance().addLast(iCharge);
	    UserChargeEngine.getInstance().addLast(iCharge2);
	    UrlLinks links = new UrlLinks("继续添加订单", "page!addGameRechargeOrder.do?kaCardId=5178");
		UrlLinks links2 = new UrlLinks("返回列表", "list.do");
		return ShowMessage(MSG_TYPE_NORMAL, "系统已受理", 3, links,links2);
	}
	
	/**
	 * QB直充页面
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "QQRechargeOrder", privilegeAccess = Const.PrivilegeAccessConst.ADD)
	public String addQQRechargeOrder() throws Exception {
		newOrder();
		return "QQRecharge";
	}
	
	
	/**
	 * QB订单提交
	 * @return
	 * @throws Exception
	 */
	public String doAddQQRechargeOrder() throws Exception {
		if (SysUtil.isNull(businessPassword)||businessPassword.equals("")) {
			return ShowMessage(MSG_TYPE_STOP, "请填写密码", "点击返回",
					"page!addQQRechargeOrder.do", 0);
		}
		Goods goods = goodsService.findByFlagProvinceCode(Const.GoodsFlag.GAME_RECHARGE, 0);
		if (goods==null || goods.getIsUsed()==0) {
			return ShowMessage(MSG_TYPE_STOP, "由于该地区供应商系统维护，暂时不能为该号码进行充值，不便之处，敬请原谅!", "", "", 0);
		}
//		Map<String, String[]> paraMap = getHttpServletRequest().getParameterMap();
//		Iterator iterator = paraMap.entrySet().iterator();
//		int goodsNo = 0;
//		String mobile = "";
//		String at = "";
//		while (iterator.hasNext()) {
//			Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) iterator.next();
//			String key = entry.getKey();
//			String keySub = key.substring(0,3);
//			if (keySub.equals("at_")) {
//				at += key+"="+entry.getValue()[0]+"&";
//			}
//			if (key.equals("at_num")) {
//				goodsNo = Integer.valueOf(entry.getValue()[0]);
//			}
//			if (key.equals("at_ele1")) {
//				mobile = entry.getValue()[0];
//			}
//		}
		SupplyKernel supplyKernel=SupplyKernel.getInstance();
		ISupply supply=supplyKernel.getISupply(supplyKernel.getListBySellType(goods,2),goodsNo,goods.getValue());
		if (supply==null) {
			return ShowMessage(MSG_TYPE_STOP, "由于该地区供应商系统维护，暂时不能为该号码进行充值，不便之处，敬请原谅!", "", "", 0);
		}
		if (supply.getClazzName().equals("KASupply")) {
			supply=supplyKernel.getISupply(supplyKernel.getListBySellType(goods,supply.getId()+"",2),goodsNo,goods.getValue());
		}
		if ((goodsNo<10 || goodsNo>200) && supply.getClazzName().equals("ZhongrongSupply")) {
			supply=supplyKernel.getISupply(supplyKernel.getListBySellType(goods,supply.getId()+"",2),goodsNo,goods.getValue());
		}
		if (supply.getIsUsed()==0) {
			return ShowMessage(MSG_TYPE_STOP, "由于该地区供应商系统维护，暂时不能为该号码进行充值，不便之处，敬请原谅!", "", "", 0);
		}
		Users users = userService.findUserById(getSessionUser().getUserId());
		SupplyInterface supplyInterface = supplyInterfaceService.findByUsergroupAndSupplyAndGoods(users.
				getUsergroup().getId(),
					supply.getId(), goods.getGoodsId());
		if (supplyInterface==null || supplyInterface.getState()==0) {
			return ShowMessage(MSG_TYPE_STOP, "由于该地区供应商系统维护，暂时不能为该号码进行充值，不便之处，敬请原谅!", "", "", 0);
		}
		Double money=(double)goods.getValue()*goodsNo;
		Integer result = useraccountService.validatePurchase(getSessionUser(), money);
		switch (result) {
		// 返回1表示空账户,返回0表示充值成功,2表示充值失败,3表示密码错误
		case Const.UseraccountResult.NULL:
			return ShowMessage(MSG_TYPE_STOP, "该账户没注册", "点击返回",
					"page!addQQRechargeOrder.do", 0);
		case Const.UseraccountResult.FAIL:
			return ShowMessage(MSG_TYPE_STOP, "余额不足,请充值", "点击返回",
					"page!addQQRechargeOrder.do", 0);
		case Const.UseraccountResult.WRONG_PASSWORD:
			return ShowMessage(MSG_TYPE_STOP, "账户密码错误", "点击返回",
					"page!addQQRechargeOrder.do", 0);
		}
		//生成订单
		mainorder = orderService.addMainOrderForQQ(mainorder, mobile, (double)goods.getValue(), getSessionUser(),
				supplyInterface, goodsNo,0,"",1); 
		orderService.addOrderDetail(orderdetail, mainorder, null);
		
		//先扣费
		ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), getSessionUser(), null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
		ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder,supplyInterface.getStockPrice()*goodsNo, (Supply)supply, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
		UserChargeEngine.getInstance().addLast(iCharge);
	    UserChargeEngine.getInstance().addLast(iCharge2);
	    UrlLinks links = new UrlLinks("继续添加订单", "page!addQQRechargeOrder.do");
		UrlLinks links2 = new UrlLinks("返回列表", "list.do");
		return ShowMessage(MSG_TYPE_NORMAL, "系统已受理", 3, links,links2);
	}
	
	/**
	 * 手机批量充值订单提交(线程用)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doBatchMobileOrderEx() throws Exception {
		
		if (SysUtil.isNull(mobile)) {
			return ShowMessage(MSG_TYPE_STOP, "请填写手机", "点击返回", "page!batchMobileOrder.do", 0);
		}
		if (SysUtil.isNull(businessPassword)||businessPassword.equals("")) {
			return ShowMessage(MSG_TYPE_STOP, "请填写密码", "点击返回",
					"page!batchMobileOrder.do", 0);
		}
		String password = getSessionUser().getBusinessPassword();
		System.out.println(password);
		if (!MD5.getiInstance().getMD5ofStr(businessPassword).equals(password)) {
			return ShowMessage(MSG_TYPE_STOP, "密码错误", "点击返回",
					"page!batchMobileOrder.do", 0);
		}
		// 检测电话号码是否可以正常充值
		//List<String> mobileList = StringUtil.strToList(mobile, "\r\n");
		//保存充值map<号码,对应商品>
		Map<String, SupplyInterface> map = new IdentityHashMap<String, SupplyInterface>();
		//保存错误格式list
		List<String> errorMobileList = new ArrayList<String>();
		//保存mobile的顺序
		LinkedList<String> linkedMobileList = new LinkedList<String>();
		//保存各供货商所需金额,如余额不足需跳出
		//Map<Integer, Double> map2 = new HashMap<Integer, Double>();
		double totalMoney = 0;
		//预处理号码&金额
		mobile = pretreatMobiles(mobile);
		String[] mobiles = mobile.split(";");
		if (mobiles.length > 500) {
			return ShowMessage(MSG_TYPE_STOP, "批量充值号码数量请小于500", "点击返回",
					"page!batchMobileOrder.do", 0);
		}
		for (String mobileAndValue : mobiles) {
			String[] mobileAndValues = mobileAndValue.split(",");
			if (mobileAndValues.length == 2) {
				try {
					String mobile = mobileAndValues[0].trim();
					double value = Double.valueOf(mobileAndValues[1].trim());
					if (mobile.trim().length() < 11) {
						//return ShowMessage(MSG_TYPE_STOP, "请输入正确的手机号码", "点击返回", "page!batchMobileOrder.do", 0);
						errorMobileList.add(mobileAndValue);
						continue;
					}
					String mobileNum = mobile.trim().substring(0, 7);
					System.out.println(mobileNum);
					AreaCode areaCode = areaCodeService.findByMobile(mobileNum);
					if (areaCode==null) {
						//return ShowMessage(MSG_TYPE_STOP, "请输入正确的手机号码", "点击返回", "page!batchMobileOrder.do", 0);
						errorMobileList.add(mobileAndValue);
						continue;
					}
					Goods goods = goodsService.findByAreaCodeAndValue(areaCode, value);
					if (goods == null) {
						//return ShowMessage(MSG_TYPE_STOP, "很抱歉，您不能为该号码充值", "重新添加订单", "page!batchMobileOrder.do", 0);
						errorMobileList.add(mobileAndValue);
						continue;
					}
					SupplyKernel supplyKernel=SupplyKernel.getInstance();
					ISupply supply=supplyKernel.getISupply(supplyKernel.getListBySellType(goods,2),1,goods.getValue());
					if (supply==null) {
						//return ShowMessage(MSG_TYPE_STOP, "没有可用的供货商,请联系客服", "", "", 0);
						errorMobileList.add(mobileAndValue);
						continue;
					}
					Users users = userService.findUserById(getSessionUser().getUserId());
					SupplyInterface supplyInterface = supplyInterfaceService.findByUsergroupAndSupplyAndGoods(users.
							getUsergroup().getId(),
								supply.getId(), goods.getGoodsId());
//					if (map2.get(supplyInterface.getId())==null) {
//						map2.put(supplyInterface.getId(), Double.parseDouble(supplyInterface.getValue()+""));
//					}else {
//						Double supplyMoney = map2.get(supplyInterface.getId());
//						map2.put(supplyInterface.getId(), supplyMoney+Double.parseDouble(supplyInterface.getValue()+""));
//					}
					//map2.put(supplyInterface.getId(), Double.parseDouble(supplyInterface.getValue()+""));
					//double supplyMoney = map2.get(supplyInterface.getId());
					//map2.put(supplyInterface, Double.parseDouble(supplyInterface.getValue()+""));
					//if (!validateSupplyBalance(map2)) {
						//return ShowMessage(MSG_TYPE_STOP, supplyInterface.getSupplyName()+"余额不足,请通知管理员充值", "点击返回", "page!batchMobileOrder.do", 0);
					//}
					linkedMobileList.addLast(mobile);
					map.put(mobile, supplyInterface);
					totalMoney += Double.valueOf(mobileAndValues[1]);
				} catch (NumberFormatException e) {
					errorMobileList.add(mobileAndValue);
					continue;
//					return ShowMessage(MSG_TYPE_STOP, mobileAndValue+"格式不正确,请检查", "点击返回",
//							"page!batchMobileOrder.do", 0);
				}
			}else {
				errorMobileList.add(mobileAndValue);
				continue;
//				return ShowMessage(MSG_TYPE_STOP, mobileAndValue+"格式不正确,请检查", "点击返回",
//						"page!batchMobileOrder.do", 0);
			}
		}
		//充值列表
		
//		if (mobileList.size() <= 5) {
//			return ShowMessage(MSG_TYPE_STOP, "批量充值号码数量请大于5", "点击返回",
//					"page!batchMobileOrder.do", 0);
//		}
		
		//Double totalMoney = new Double(priceShow*mobileList.size());
		Integer result = useraccountService.validatePurchase(getSessionUser(), totalMoney);
		switch (result) {
		// 返回1表示空账户,返回0表示充值成功,2表示充值失败,3表示密码错误
		case Const.UseraccountResult.NULL:
			return ShowMessage(MSG_TYPE_STOP, "该账户没注册", "点击返回",
					"page!batchMobileOrder.do", 0);
		case Const.UseraccountResult.FAIL:
			return ShowMessage(MSG_TYPE_STOP, "余额不足,请充值", "点击返回",
					"page!batchMobileOrder.do", 0);
		case Const.UseraccountResult.WRONG_PASSWORD:
			return ShowMessage(MSG_TYPE_STOP, "账户密码错误", "点击返回",
					"page!batchMobileOrder.do", 0);
		}
		
		//验证供货商余额
//		if (!validateSupplyBalance(map2)) {
//			return ShowMessage(MSG_TYPE_STOP, "供货商余额不足,请联系管理员", "点击返回", "page!batchMobileOrder.do", 0);
//		}
//		
		//充值
		BatchOrder batchOrder = new BatchOrder(); 
		batchOrder = orderService.addBatchOrder(batchOrder, "", "", 0, getSessionUser(), getSessionUser().getUserName(), 0);
		for (String mobile : linkedMobileList) {
			SupplyInterface supplyInterface = map.get(mobile);
			//防止重复提取,有可能同一号码不同金额
			map.remove(mobile);
			ISupply supply = SupplyKernel.getInstance().getSupplyMapKeyId().get(supplyInterface.getSupply().getId());
			//生成订单
			Mainorder mainorder = new Mainorder();
			mainorder = orderService.addMainOrderForBatch(mainorder, mobile, getSessionUser(),
					supplyInterface, 1,0,"",1,batchOrder.getBatchOrderId(),totalMoney);
			orderService.addOrderDetail(orderdetail, mainorder, null);
			//先扣费
			ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), mainorder.getUsers(), null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
			ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), (Supply)supply, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
			UserChargeEngine.getInstance().addLast(iCharge);
		    UserChargeEngine.getInstance().addLast(iCharge2);
		    //更新批量订单的电话和对应orderId
		    batchOrder = orderService.updateBatchOrderMobileAndMainorderId(batchOrder, mobile, mainorder.getMainOrderId(),mainorder.getGoodsValue());
		    if (batchOrder.getGoodsNo() > 500) {
				batchOrder = new BatchOrder();
				batchOrder = orderService.addBatchOrder(batchOrder, "", "", 0, getSessionUser(), getSessionUser().getUserName(), 0);
			}
		}
		
//		for (String reMobile : mobile.split("\n")) {
//			String mobileNum = reMobile.substring(0, 7);
//			System.out.println(mobileNum);
//			AreaCode areaCode = areaCodeService.findByMobile(mobileNum);
//			if (areaCode==null) {
//				return ShowMessage(MSG_TYPE_STOP, "请输入正确的手机号码", "点击返回", "page!batchMobileOrder.do", 0);
//			}
//			Goods goods = goodsService.findByAreaCodeAndValue(areaCode, priceShow);
//			if (goods == null) {
//				return ShowMessage(MSG_TYPE_STOP, "很抱歉，您不能为该号码充值", "重新添加订单", "page!batchMobileOrder.do", 0);
//			}
//			SupplyKernel supplyKernel=SupplyKernel.getInstance();
//			ISupply supply=supplyKernel.getISupply(supplyKernel.getListBySellType(goods,2),1,goods.getValue());
//			if (supply==null) {
//				return ShowMessage(MSG_TYPE_STOP, "没有可用的供货商,请联系客服", "", "", 0);
//			}
//			SupplyInterface supplyInterface = supplyInterfaceService.findBySupplyAndGoods(supply.getId(), goods.getGoodsId());
//			Double money=new Double(supplyInterface.getValue());
//			Integer result2 = useraccountService.validatePurchase(getSessionUser(), money);
//			switch (result2) {
//			// 返回1表示空账户,返回0表示充值成功,2表示充值失败,3表示密码错误
//			case Const.UseraccountResult.NULL:
//				return ShowMessage(MSG_TYPE_STOP, "该账户没注册", "点击返回",
//						"page!batchMobileOrder.do", 0);
//			case Const.UseraccountResult.FAIL:
//				return ShowMessage(MSG_TYPE_STOP, "余额不足,请充值", "点击返回",
//						"page!batchMobileOrder.do", 0);
//			case Const.UseraccountResult.WRONG_PASSWORD:
//				return ShowMessage(MSG_TYPE_STOP, "账户密码错误", "点击返回",
//						"page!batchMobileOrder.do", 0);
//			}
//			
//			/**
//			 *TODO 手机批量充值需注意
//			 *
//			 * mainorder跟前面定义的不一样  
//			 * 前面创建的mainorder对象,是处理一个对象类型的(意思:不管页面传多少参数,他都当成一个对象来处理)
//			 * 所以只能处理一个手机号码充值
//			 * 
//			 * 这里的mainorder对象是创建一个mainorder对象然后把页面传过来的参数分别一个个封装到这个对象中
//			 * 所以说可以处理多个手机号码充值的
//			 * 
//			 */
//			
//			
//			
//			//生成订单
//			Mainorder mainorder = new Mainorder();
//			mainorder = orderService.addMainOrder(mainorder, reMobile, getSessionUser(),
//					supplyInterface, 1,0,"",1);
//			orderService.addOrderDetail(orderdetail, mainorder, null);
//			//先扣费
//			ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), mainorder.getUsers(), null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
//			ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), (Supply)supply, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
//			UserChargeEngine.getInstance().addLast(iCharge);
//		    UserChargeEngine.getInstance().addLast(iCharge2);
////			supplyKernel.purchaseBalance(mainorder);
////			useraccountService.refreshAccountes(mainorder,mainorder.getUsers(),null,null,new UserCharge(),
////					mainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
//		}
		//充值
		//在查询订单状态时转行订单状态,这里不需updateOrderState为FAILED
		if (errorMobileList.size() > 0) {
			String errorMobile = StringUtil.ListToStr(errorMobileList, " ");
			UrlLinks links = new UrlLinks("继续添加订单", "page!batchMobileOrder.do");
			UrlLinks links2 = new UrlLinks("返回列表", "list.do");
			return ShowMessage(MSG_TYPE_NORMAL, "系统已受理,其中"+errorMobile+"未能提交,请检查号码或联系管理员", 0, links,links2);
		}
		UrlLinks links = new UrlLinks("继续添加订单", "page!batchMobileOrder.do");
		UrlLinks links2 = new UrlLinks("返回列表", "list.do");
		return ShowMessage(MSG_TYPE_NORMAL, "系统已受理", 3, links,links2);
		
	}
	
	private boolean validateSupplyBalance(Map<Integer,Double> map) {
		boolean result = true;
		Iterator iterator=map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, Double> entry = (Map.Entry<Integer, Double>) iterator.next();
			SupplyKernel supplyKernel = SupplyKernel.getInstance();
			SupplyInterface supplyInterface = supplyKernel.getSupplyInterfaceMapKeyId().get(entry.getKey());
			ISupply supply = supplyKernel.getSupplyMapKeyId().get(supplyInterface.getSupply().getId());
			Double money = entry.getValue();
			if (supply.getBalance()<money) {
				result = false;
				break;
			}
		}
		return result;
	}
	
	
	/**
	 * 预处理批量电话(金额)
	 * @param mobileText
	 * @return
	 */
	private String pretreatMobiles(String mobileText) {
		if (mobileText.contains(" ")) {
			mobileText = mobileText.replaceAll(" ", ",");
			return pretreatMobiles(mobileText);
		}
		if (mobileText.contains("\r\n")) {
			mobileText = mobileText.replaceAll("\r\n", ";");
			return pretreatMobiles(mobileText);
		}
		if (mobileText.contains("\t")) {
			mobileText = mobileText.replaceAll("\t", ",");
			return pretreatMobiles(mobileText);
		}
		if (mobileText.contains(",,")) {
			mobileText = mobileText.replaceAll(",,", ",");
			return pretreatMobiles(mobileText);
		}
		if (mobileText.contains(";;")) {
			mobileText = mobileText.replaceAll(";;", ";");
			return pretreatMobiles(mobileText);
		}
		return mobileText;
	}
	
	/**
	 * 开始批量订单
	 * @return
	 * @throws Exception
	 */
	public String startBatchOrder() throws Exception {
		BatchOrder batchOrder = orderService.findBatchOrderById(batchOrderId);
		if (batchOrder.getBatchOrderState()!=Const.BatchOrderState.WAIT_COMMIT) {
			return ShowMessage(MSG_TYPE_STOP, "当前状态不允许开始", "点击返回", "list!viewBatchOrder.do?batchOrderId="+batchOrderId, 0);
		}
		orderService.updateBatchOrderState(batchOrder, Const.BatchOrderState.PROCESSING);
		List<Mainorder> mainorders = orderService.findBatchOrderByOrderIdAndState(batchOrderId,Const.BatchOrderState.WAIT_COMMIT);
		orderService.updateBatchOrderState(mainorders, Const.BatchOrderState.PROCESSING);
		return ShowMessage(MSG_TYPE_NORMAL, "开始成功", "点击返回", "list!viewBatchOrder.do?batchOrderId="+batchOrderId, 0);
	}
	
	/**
	 * 暂停批量订单
	 * @return
	 * @throws Exception
	 */
	public String pauseBatchOrder() throws Exception {
		BatchOrder batchOrder = orderService.findBatchOrderById(batchOrderId);
		if (batchOrder.getBatchOrderState()!=Const.BatchOrderState.PROCESSING) {
			return ShowMessage(MSG_TYPE_STOP, "当前状态不允许开始", "点击返回", "list!viewBatchOrder.do?batchOrderId="+batchOrderId, 0);
		}
		orderService.updateBatchOrderState(batchOrder, Const.BatchOrderState.PAUSE);
		List<Mainorder> mainorders = orderService.findBatchOrderByOrderIdAndState(batchOrderId,Const.BatchOrderState.PROCESSING);
		orderService.updateBatchOrderState(mainorders, Const.BatchOrderState.PAUSE);
		return ShowMessage(MSG_TYPE_NORMAL, "暂停成功", "点击返回", "list!viewBatchOrder.do?batchOrderId="+batchOrderId, 0);
	}
	
	/**
	 * 恢复批量订单
	 * @return
	 * @throws Exception
	 */
	public String restoreBatchOrder() throws Exception {
		BatchOrder batchOrder = orderService.findBatchOrderById(batchOrderId);
		if (batchOrder.getBatchOrderState()!=Const.BatchOrderState.PAUSE) {
			return ShowMessage(MSG_TYPE_STOP, "当前状态不允许开始", "点击返回", "list!viewBatchOrder.do?batchOrderId="+batchOrderId, 0);
		}
		orderService.updateBatchOrderState(batchOrder, Const.BatchOrderState.PROCESSING);
		List<Mainorder> mainorders = orderService.findBatchOrderByOrderIdAndState(batchOrderId,Const.BatchOrderState.PAUSE);
		orderService.updateBatchOrderState(mainorders, Const.BatchOrderState.PROCESSING);
		return ShowMessage(MSG_TYPE_NORMAL, "恢复成功", "点击返回", "list!viewBatchOrder.do?batchOrderId="+batchOrderId, 0);
	}

	/**
	 * 异步获取商品价格
	 */
	public void doAddCardOrderCheckOutPrice(){
		HttpServletResponse response = this.getHttpServletResponse();
		goodsId = Integer.parseInt(goodsIdStr.trim());
		Goods goods = goodsService.findById(goodsId);
		SupplyKernel supplyKernel=SupplyKernel.getInstance();
		ISupply supply=supplyKernel.getISupplyForCard(supplyKernel.getListBySellType(goods,2),goods,goodsNo);
		if (supply==null) {
			ResponseUtils.renderText(response, "error#由于该地区供应商系统维护，暂时不能为该号码进行充值，不便之处，敬请原谅!");
			return;
		}
		SupplyInterface supplyInterface = supplyInterfaceService.findBySupplyAndGoods(supply.getId(), goodsId);
		ResponseUtils.renderText(response, "success#"+supplyInterface.getRetailPrice()+"#"+supplyInterface.getId()+"#"+supply.getId());
	}
	
	/**
	 * 增加卡密订单提交
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doAddCardOrder() throws Exception {
		if (SysUtil.isNull(goodsIdStr.trim())||"".equals(goodsIdStr.trim())) {
			return ShowMessage(MSG_TYPE_STOP, "请选择商品", "点击返回", "page!addGameCardOrder.do", 0);
		}
		goodsId = Integer.parseInt(goodsIdStr.trim());
//		if (SysUtil.isNull(goodsId)) {
//			return ShowMessage(MSG_TYPE_STOP, "请选择商品", "点击返回", "page!addGameCardOrder.do", 0);
//		}
		// Users user=userService.findUserByMobile(mobile);
		// TODO:取消了账户密码
		
//		Goods goods = goodsService.findById(goodsId);
//		SupplyKernel supplyKernel=SupplyKernel.getInstance();
//		ISupply supply=supplyKernel.getISupplyForCard(supplyKernel.getListBySellType(goods,2),goods,goodsNo);
//		if (supply==null) {
//			return ShowMessage(MSG_TYPE_STOP, "没有可用的供货商,请联系客服", "", "", 0);
//		}
//		SupplyInterface supplyInterface = supplyInterfaceService.findBySupplyAndGoods(supply.getId(), goodsId);
		SupplyKernel supplyKernel=SupplyKernel.getInstance();
		ISupply supply= supplyService.findSupplyById(supplyId);
		SupplyInterface supplyInterface = supplyInterfaceService.findById(supplyInterfaceId);
		
		Double money=new Double(supplyInterface.getValue());
		Integer result = useraccountService.validatePurchase(getSessionUser(), money);
		switch (result) {
		// 返回1表示空账户,返回0表示充值成功,2表示充值失败,3表示密码错误
		case Const.UseraccountResult.NULL:
			return ShowMessage(MSG_TYPE_STOP, "该账户没注册", "点击返回",
					"page!addGameCardOrder.do", 0);
		case Const.UseraccountResult.FAIL:
			return ShowMessage(MSG_TYPE_STOP, "余额不足,请充值", "点击返回",
					"page!addGameCardOrder.do", 0);
		case Const.UseraccountResult.WRONG_PASSWORD:
			return ShowMessage(MSG_TYPE_STOP, "账户密码错误", "点击返回",
					"page!addGameCardOrder.do", 0);
		}
		
		
		mainorder = orderService.addMainOrder(mainorder, null, getSessionUser(),
				supplyInterface, goodsNo,0,"",2);
		List<CardLib> sellLibs = supply.buyCard(mainorder, orderdetail, supplyInterface, getSessionUser(), goodsNo);
		orderService.addOrderDetail(orderdetail, mainorder, sellLibs);
		operationLogService.addOperationLog(getSessionUser(),mainorder, null,Const.OperationLogType.CARDLIB_BUY,Const.OperationLogResult.SUCCESS,null);
		ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), (Supply)supply, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
//		supplyKernel.purchaseBalance(mainorder);
		ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), getSessionUser(), null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
		UserChargeEngine.getInstance().addLast(iCharge);
		UserChargeEngine.getInstance().addLast(iCharge2);
//		useraccountService.refreshAccountes(mainorder,mainorder.getUsers(),null,null,new UserCharge(),
//				mainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
		orderService.updateOrderCardLibs(mainorder, sellLibs);
		String showResult="";
		if (sellLibs!=null&&!sellLibs.isEmpty()) {
			for (CardLib cardLib : sellLibs) {
				String cardCode=cardLib.getCardCode();//sellLibs.get(0).getCardCode();
				String cardPwd=cardLib.getCardPassword();//sellLibs.get(0).getCardPassword();
				showResult+="你的卡号为:"+cardCode+","+"密码为:"+cardPwd+"<br/>";
			}
			UrlLinks links = new UrlLinks("继续添加订单", "page!addGameCardOrder.do");
			UrlLinks links2 = new UrlLinks("返回列表", "list.do");
			return ShowMessage(MSG_TYPE_NORMAL, "操作已成功"+showResult, 3, links,links2);
		}else {
			//商户自动返还额度
			ICharge iCharge3 = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), getSessionUser(), null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.USER_CANCEL);
//			useraccountService.refreshAccountes(mainorder,mainorder.getUsers(),null,null,new UserCharge(), mainorder.getTotalMoney(),Const.UseraccountdealdetailFlag.USER_CANCEL);
			//转换冲正的卡密状态
			//cardLibService.reverseCardLib(mainorder.getCardLibIds());
			ICharge iCharge4 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), (Supply)supply, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.USER_CANCEL);
			UserChargeEngine.getInstance().addLast(iCharge3);
			UserChargeEngine.getInstance().addLast(iCharge4);
//			supplyKernel.cancelBalance(mainorder,(Supply)supply, mainorder.getStockPrice()*mainorder.getGoodsNo(), new UserCharge());
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESS_FAILED);
		}
		return ShowMessage(MSG_TYPE_STOP, "没有可用卡密,请联系供货商", "", "", 0);
	}

	/**
	 * 取消直冲订单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cancelOrder() throws Exception {
		mainorder = orderService.findOrderById(orderId);
		// 判断权限
		if (getSessionUser().getUsergroup().getGroupType() != Const.GroupType.ADMIN) {
			return ShowMessage(MSG_TYPE_STOP, "你不是管理员,不能取消订单", "点击返回",
					"list.do", 0);
		}
		if (!mainorder.getState().equals(Const.MainOrderState.PROCESSING)||!mainorder.getState().equals(Const.MainOrderState.APPLY_PROCESS)
				||!mainorder.getState().equals(Const.MainOrderState.THREAD_EXECUTING)) {
			return ShowMessage(MSG_TYPE_STOP, "当前状态不允许取消订单", "点击返回", "list.do",
					0);
		}
		//
		Users users = userService.findUserById(mainorder.getUsers().getUserId());
		orderService.cancelOrder(mainorder);
		if (mainorder.getMoneyBack().equals(1)) {
			operationLogService.addOperationLog(users, mainorder, mainorder.getMobile(), Const.OperationLogType.USER_CANCEL, Const.OperationLogResult.SUCCESS,null);
			//商户返还额度
			ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), users, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.USER_CANCEL);
//			useraccountService.refreshAccountes(mainorder,users,null,getSessionUser().getUserName(),new UserCharge(), mainorder.getTotalMoney(),Const.UseraccountdealdetailFlag.USER_CANCEL);
		   	//供货商返还额度
			Supply supply = (Supply)SupplyKernel.getInstance().getSupplyMapKeyId().get(mainorder.getSupply().getId());
			//供货商取消订单刷新
			supply.cancelOrder(mainorder);
			SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
			ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice(), supply, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.USER_CANCEL);
			UserChargeEngine.getInstance().addLast(iCharge);
		    UserChargeEngine.getInstance().addLast(iCharge2);
//			SupplyKernel.getInstance().cancelBalance(mainorder,supply, mainorder.getStockPrice()*mainorder.getGoodsNo(), new UserCharge());
			//订单回水标记0
			orderService.updateMoneyBack(mainorder, 0);
		}
		
		return ShowMessage(MSG_TYPE_NORMAL, "取消成功", "点击返回", "list.do", 0);
	}
	
	/**
	 * 取消直冲订单(不返还额度)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cancelOrderNoReturn() throws Exception {
		mainorder = orderService.findOrderById(orderId);
		// 判断权限
		if (getSessionUser().getUsergroup().getGroupType() != Const.GroupType.ADMIN) {
			return ShowMessage(MSG_TYPE_STOP, "你不是管理员,不能取消订单", "点击返回",
					"list.do", 0);
		}
		if (!mainorder.getState().equals(Const.MainOrderState.PROCESSING)&&!mainorder.getState().equals(Const.MainOrderState.APPLY_PROCESS)) {
			return ShowMessage(MSG_TYPE_STOP, "当前状态不允许取消订单", "点击返回", "list.do",
					0);
		}
		//
		Users users = userService.findUserById(mainorder.getUsers().getUserId());
		orderService.cancelOrder(mainorder);
		operationLogService.addOperationLog(users, mainorder, mainorder.getMobile(), Const.OperationLogType.USER_CANCEL, Const.OperationLogResult.SUCCESS,null);
		Supply supply = (Supply)SupplyKernel.getInstance().getSupplyMapKeyId().get(mainorder.getSupply().getId());
		//供货商取消订单刷新
		supply.cancelOrder(mainorder);
		return ShowMessage(MSG_TYPE_NORMAL, "取消成功", "点击返回", "list.do", 0);
	}
	

	/**
	 * 取消直冲订单ex
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cancelOrderEx() throws Exception {
		mainorder = orderService.findOrderById(orderId);
		// 判断权限
		if (getSessionUser().getUsergroup().getGroupType() != Const.GroupType.ADMIN) {
			return ShowMessage(MSG_TYPE_STOP, "你不是管理员,不能取消订单", "点击返回",
					"list.do", 0);
		}
		if (!mainorder.getState().equals(Const.MainOrderState.PROCESSING)&&!mainorder.getState().equals(Const.MainOrderState.APPLY_PROCESS)) {
			return ShowMessage(MSG_TYPE_STOP, "当前状态不允许取消订单", "点击返回", "list.do",
					0);
		}
		
		
		Users users = userService.findUserById(mainorder.getUsers().getUserId());
		//设置状态为申请取消
		orderService.cancelOrderApply(mainorder);
		if (mainorder.getMoneyBack().equals(1)) {
			operationLogService.addOperationLog(users, mainorder, mainorder.getMobile(), Const.OperationLogType.USER_CANCEL, Const.OperationLogResult.SUCCESS,null);
			//商户返还额度
			ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), users, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.USER_CANCEL);
//			useraccountService.refreshAccountes(mainorder,users,null,getSessionUser().getUserName(),new UserCharge(), mainorder.getTotalMoney(),Const.UseraccountdealdetailFlag.USER_CANCEL);
			//供货商返还额度
			Supply supply = (Supply)SupplyKernel.getInstance().getSupplyMapKeyId().get(mainorder.getSupply().getId());
			//供货商取消订单刷新
			supply.cancelOrder(mainorder);
			SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
			ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice(), supply, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.USER_CANCEL);
			UserChargeEngine.getInstance().addLast(iCharge);
		    UserChargeEngine.getInstance().addLast(iCharge2);
//			SupplyKernel.getInstance().cancelBalance(mainorder,supply, mainorder.getStockPrice()*mainorder.getGoodsNo(), new UserCharge());
			orderService.updateMoneyBack(mainorder, 0);
		}
		
		return ShowMessage(MSG_TYPE_NORMAL, "系统处理中", "点击返回", "list.do", 0);
	}

	public  String batchCancelOrderExe(){
		// 判断权限
		if (getSessionUser().getUsergroup().getGroupType() != Const.GroupType.ADMIN) {
			return ShowMessage(MSG_TYPE_STOP, "你不是管理员,不能取消订单", "点击返回",
					"list.do", 0);
		}if(orderIds==null){
			return ShowMessage(MSG_TYPE_STOP, "没有选择任何订单", "点击返回", "list.do", 0);
		}
		String orderIdArr[] = orderIds.split(",");
		List<String> lostOrderIds = new ArrayList<String>();
		for(int i=0;i<orderIdArr.length;i++){
			mainorder = orderService.findOrderById(orderIdArr[i].trim());
			if (!mainorder.getState().equals(Const.MainOrderState.PROCESSING)
					&&!mainorder.getState().equals(Const.MainOrderState.APPLY_PROCESS)
					&&!mainorder.getState().equals(Const.MainOrderState.THREAD_EXECUTING)) {
//				return ShowMessage(MSG_TYPE_STOP, "当前状态不允许取消订单", "点击返回", "list.do",
//						0);
				lostOrderIds.add(orderIdArr[i]);
			}
			
			Users users = userService.findUserById(mainorder.getUsers().getUserId());
			//设置状态为申请取消
			orderService.cancelOrderApply(mainorder);
			if (mainorder.getMoneyBack().equals(1)) {
				operationLogService.addOperationLog(users, mainorder, mainorder.getMobile(), Const.OperationLogType.USER_CANCEL, Const.OperationLogResult.SUCCESS,null);
				//商户返还额度
				ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), users, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.USER_CANCEL);
//				useraccountService.refreshAccountes(mainorder,users,null,getSessionUser().getUserName(),new UserCharge(), mainorder.getTotalMoney(),Const.UseraccountdealdetailFlag.USER_CANCEL);
				//供货商返还额度
				Supply supply = (Supply)SupplyKernel.getInstance().getSupplyMapKeyId().get(mainorder.getSupply().getId());
				//供货商取消订单刷新
				supply.cancelOrder(mainorder);
				SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
				ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice(), supply, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.USER_CANCEL);
				UserChargeEngine.getInstance().addLast(iCharge);
			    UserChargeEngine.getInstance().addLast(iCharge2);
//				SupplyKernel.getInstance().cancelBalance(mainorder,supply, mainorder.getStockPrice()*mainorder.getGoodsNo(), new UserCharge());
				orderService.updateMoneyBack(mainorder, 0);
			}
		}
		if(lostOrderIds.size()>0){
			StringBuffer bf = new StringBuffer();
			for(int j = 0;j<lostOrderIds.size();j++){
				bf.append(lostOrderIds.get(j)).append(",");
			}
			return ShowMessage(MSG_TYPE_STOP, bf.toString()+"这些订单取消失败", "点击返回", "list.do", 0);
		}else{
			return ShowMessage(MSG_TYPE_NORMAL, "系统处理中", "点击返回", "list.do", 0);
		}
		
	}
	
	
	/**
	 * 取消直冲订单exe同时记录操作员(2012.10.24)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cancelOrderExe() throws Exception {
		mainorder = orderService.findOrderById(orderId);
		// 判断权限
		if (getSessionUser().getUsergroup().getGroupType() != Const.GroupType.ADMIN) {
			return ShowMessage(MSG_TYPE_STOP, "你不是管理员,不能取消订单", "点击返回",
					"list.do", 0);
		}
		if (!mainorder.getState().equals(Const.MainOrderState.PROCESSING)
				&&!mainorder.getState().equals(Const.MainOrderState.APPLY_PROCESS)
				&&!mainorder.getState().equals(Const.MainOrderState.THREAD_EXECUTING)) {
			return ShowMessage(MSG_TYPE_STOP, "当前状态不允许取消订单", "点击返回", "list.do",
					0);
		}
		
		
		Users users = userService.findUserById(mainorder.getUsers().getUserId());
		//设置状态为申请取消
		orderService.cancelOrderApply(mainorder);
		if (mainorder.getMoneyBack().equals(1)) {
			operationLogService.addOperationLog(users, mainorder, mainorder.getMobile(), Const.OperationLogType.USER_CANCEL, Const.OperationLogResult.SUCCESS,null);
			//商户返还额度
//			useraccountService.refreshAccount(mainorder,users,new Useraccountdealdetail(), mainorder.getTotalMoney(),Const.UseraccountdealdetailFlag.USER_CANCEL);
			//2012.10.24(新添加)
			ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), users, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.USER_CANCEL);
//			useraccountService.refreshAccountes(mainorder,users,null,getSessionUser().getUserName(),new UserCharge(), mainorder.getTotalMoney(),Const.UseraccountdealdetailFlag.USER_CANCEL);
			
			//供货商返还额度
			Supply supply = (Supply)SupplyKernel.getInstance().getSupplyMapKeyId().get(mainorder.getSupply().getId());
			//供货商取消订单刷新
			supply.cancelOrder(mainorder);
			SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
			ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice(), supply, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.USER_CANCEL);
			UserChargeEngine.getInstance().addLast(iCharge);
		    UserChargeEngine.getInstance().addLast(iCharge2);
//			SupplyKernel.getInstance().cancelBalance(mainorder,supply, mainorder.getStockPrice()*mainorder.getGoodsNo(), new UserCharge());
			//订单回水标记0
			orderService.updateMoneyBack(mainorder, 0);
		}
		
		
		return ShowMessage(MSG_TYPE_NORMAL, "系统处理中", "点击返回", "list.do", 0);
	}
	
	/**
	 * 取消直冲订单ex
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cancelOrderNoReturnEx() throws Exception {
		mainorder = orderService.findOrderById(orderId);
		// 判断权限
		if (getSessionUser().getUsergroup().getGroupType() != Const.GroupType.ADMIN) {
			return ShowMessage(MSG_TYPE_STOP, "你不是管理员,不能取消订单", "点击返回",
					"list.do", 0);
		}
		if (!mainorder.getState().equals(Const.MainOrderState.PROCESSING)&&!mainorder.getState().equals(Const.MainOrderState.APPLY_PROCESS)) {
			return ShowMessage(MSG_TYPE_STOP, "当前状态不允许取消订单", "点击返回", "list.do",
					0);
		}
		Users users = userService.findUserById(mainorder.getUsers().getUserId());
		//设置状态为申请取消
		orderService.cancelOrderApply(mainorder);
		
		operationLogService.addOperationLog(users, mainorder, mainorder.getMobile(), Const.OperationLogType.USER_CANCEL, Const.OperationLogResult.SUCCESS,null);
		Supply supply = (Supply)SupplyKernel.getInstance().getSupplyMapKeyId().get(mainorder.getSupply().getId());
		//供货商取消订单刷新
		supply.cancelOrder(mainorder);
		return ShowMessage(MSG_TYPE_NORMAL, "系统处理中", "点击返回", "list.do", 0);
	}
	/**
	 * 取消直冲订单无返回余额ex(记录操作员)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cancelOrderNoReturnExe() throws Exception {
		mainorder = orderService.findOrderById(orderId);
		
		//新添加(2012.10.24)记录操作员
		Useraccountdealdetail useraccountdealdetail = useraccountDealDetailService.findUseraccountDealDetailByOrderId(orderId);
		useraccountdealdetail.setRecodeOperator(getSessionUser().getLoginName());
		useraccountDealDetailService.update(useraccountdealdetail);
		
		// 判断权限
		if (getSessionUser().getUsergroup().getGroupType() != Const.GroupType.ADMIN) {
			return ShowMessage(MSG_TYPE_STOP, "你不是管理员,不能取消订单", "点击返回",
					"list.do", 0);
		}
		if (!mainorder.getState().equals(Const.MainOrderState.PROCESSING)
				&&!mainorder.getState().equals(Const.MainOrderState.APPLY_PROCESS)
				&&!mainorder.getState().equals(Const.MainOrderState.THREAD_EXECUTING)) {
			return ShowMessage(MSG_TYPE_STOP, "当前状态不允许取消订单", "点击返回", "list.do",
					0);
		}
		Users users = userService.findUserById(mainorder.getUsers().getUserId());
		//设置状态为申请取消
		orderService.cancelOrderApply(mainorder);
		
		operationLogService.addOperationLog(users, mainorder, mainorder.getMobile(), Const.OperationLogType.USER_CANCEL, Const.OperationLogResult.SUCCESS,null);
		Supply supply = (Supply)SupplyKernel.getInstance().getSupplyMapKeyId().get(mainorder.getSupply().getId());
		//供货商取消订单刷新
		supply.cancelOrder(mainorder);
		return ShowMessage(MSG_TYPE_NORMAL, "系统处理中", "点击返回", "list.do", 0);
	}

	/**
	 * 直冲冲正(记录操作员:2012.10.24)
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "orderReversal", privilegeAccess = Const.PrivilegeAccessConst.NORMAL)
	public String reversalOrders() throws Exception {
		mainorder = orderService.findOrderById(orderId);
		//订单状态充值成功且未冲正
		if (!(mainorder.getState().equals(1)&&mainorder.getReversalState().equals(-1))) {
			return ShowMessage(MSG_TYPE_STOP, "当前状态不允许冲正,请联系管理员", "", "",0);
		}
		Users user = userService.findUserById(mainorder.getUsers().getUserId());
		if (user.getUsergroup().getGroupType()>Const.GroupType.STAFF&&user.getReversalCount() <= 0) {
			return ShowMessage(MSG_TYPE_STOP, "冲正次数已用完,请联系管理员", "", "", 0);
		}
		SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
		//查看supplyinterface是否禁用
		if (supplyInterface.getCanReverse().equals(0)) {
			return ShowMessage(MSG_TYPE_STOP, "该商品不能冲正,请联系管理员", "", "",0);
		}
		//可冲正日期
		if (mainorder.getCreateTime().getTime()+3600*30*1000<System.currentTimeMillis()) {
			return ShowMessage(MSG_TYPE_STOP, "已过冲正日期(自动冲正仅限于当天后24小时)", "", "",0);
		}
		
		orderService.updateReversalState(mainorder, Const.OrderReversalState.APPLY_REVERSAL);
		//15秒后刷新
		orderService.updateOrderRefreshTime(mainorder, 15000);
		
		return ShowMessage(MSG_TYPE_STOP, "请稍等一下,系统正在处理", "点击返回", "list.do", 0);
	}
	
	/**
	 * 重新冲正订单(页面)
	 * @return
	 * @throws Exception
	 */
	public String reverseOrder() throws Exception {
		return "reverseOrder";
	}
	
	/**
	 * 直冲冲正(记录操作员:2012.10.24)
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "orderReversal", privilegeAccess = Const.PrivilegeAccessConst.NORMAL)
	public String reversalOrdersEx() throws Exception {
		mainorder = orderService.findOrderById(orderId);
		SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
		//查看supplyinterface是否禁用
		if (supplyInterface.getCanReverse().equals(0)) {
			return ShowMessage(MSG_TYPE_STOP, "该商品不能冲正,请联系管理员", "", "",0);
		}
		//可冲正日期
		if (mainorder.getCreateTime().getTime()+3600*30*1000<System.currentTimeMillis()) {
			return ShowMessage(MSG_TYPE_STOP, "已过冲正日期(自动冲正仅限于当天后24小时)", "", "",0);
		}
		
		orderService.updateReversalState(mainorder, Const.OrderReversalState.APPLY_REVERSAL);
		//15秒后刷新
		orderService.updateOrderRefreshTime(mainorder, 15000);
		
		return ShowMessage(MSG_TYPE_STOP, "请稍等一下,系统正在处理", "点击返回", "list.do", 0);
	}
	
	/**
	 * 强制成功(记录操作员:2012.10.24)
	 * @return
	 * @throws Exception
	 */
	public String dealOrders() throws Exception {
		if (getSessionUser().getUsergroup().getGroupType()>Const.GroupType.STAFF) {
			return ShowMessage(MSG_TYPE_STOP, "没有权限", "点击返回", "list.do", 0);
		}
		mainorder = orderService.findOrderById(orderId);
		Users user = userService.findUserById(mainorder.getUsers().getUserId());
		switch (mainorder.getState()) {
		case Const.MainOrderState.PROCESSING:
		case Const.MainOrderState.PROCESS_FAILED:
		case Const.MainOrderState.USER_CANCEL:
		case Const.MainOrderState.THREAD_EXECUTING:
			//订单状态为成功
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESS_SUCCESS);
			//先扣费
//			SupplyKernel.getInstance().purchaseBalance(mainorder);
//			useraccountService.refreshAccount(mainorder,mainorder.getUsers(),new Useraccountdealdetail(),
//					mainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
			//2012.10.24(新添加)
			ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), user, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
//			useraccountService.refreshAccountes(mainorder,mainorder.getUsers(),null,getSessionUser().getUserName(),new UserCharge(),
//					mainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.PURCHASE);
			Supply supply = (Supply)SupplyKernel.getInstance().getSupplyMapKeyId().get(mainorder.getSupply().getId());
			SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
			ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice(), supply, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.PURCHASE);
			UserChargeEngine.getInstance().addLast(iCharge);
		    UserChargeEngine.getInstance().addLast(iCharge2);
			orderService.updateMoneyBack(mainorder, 1);
			operationLogService.addOperationLog(getSessionUser(),mainorder, mainorder.getMobile(),Const.OperationLogType.DEAL_SUCCESS,Const.OperationLogResult.SUCCESS,null);
			if (mainorder.getCardLibIds()!=null) {
				cardLibService.updateCardLibState(mainorder.getCardLibIds(),2); 
			}
			return ShowMessage(MSG_TYPE_NORMAL, "转换成功", "点击返回", "list.do", 0);
		default:
			return ShowMessage(MSG_TYPE_STOP, "订单当前状态不允许转换", "点击返回", "list.do", 0);
		}
	}
	
	
	/**
	 * 强制成功无返回余额(记录操作员:2012.10.24)
	 * @return
	 * @throws Exception
	 */
	public String dealOrdersNoReturn() throws Exception {
		if (getSessionUser().getUsergroup().getGroupType()>Const.GroupType.STAFF) {
			return ShowMessage(MSG_TYPE_STOP, "没有权限", "点击返回", "list.do", 0);
		}
		mainorder = orderService.findOrderById(orderId);
		switch (mainorder.getState()) {
		case Const.MainOrderState.PROCESSING:
		case Const.MainOrderState.PROCESS_FAILED:
		case Const.MainOrderState.USER_CANCEL:
		case Const.MainOrderState.THREAD_EXECUTING:
			//订单状态为成功
			orderService.updateOrderState(mainorder, Const.MainOrderState.PROCESS_SUCCESS);
			orderService.updateMoneyBack(mainorder, 1);
			operationLogService.addOperationLog(getSessionUser(),mainorder, mainorder.getMobile(),Const.OperationLogType.DEAL_SUCCESS,Const.OperationLogResult.SUCCESS,null);
			if (mainorder.getCardLibIds()!=null) {
				cardLibService.updateCardLibState(mainorder.getCardLibIds(),2); 
			}
			return ShowMessage(MSG_TYPE_NORMAL, "转换成功", "点击返回", "list.do", 0);
		default:
			return ShowMessage(MSG_TYPE_STOP, "订单当前状态不允许转换", "点击返回", "list.do", 0);
		}
	}
	
	/**
	 * 强制订单冲正成功
	 * @return
	 * @throws Exception
	 */
	public String reverseOrderSuccess() throws Exception {
		if (getSessionUser().getUsergroup().getGroupType()>Const.GroupType.STAFF) {
			return ShowMessage(MSG_TYPE_STOP, "没有权限", "点击返回", "list.do", 0);
		}
		mainorder = orderService.findOrderById(orderId);
		Users user = userService.findUserById(mainorder.getUsers().getUserId());
		if (mainorder.getReversalState() == Const.OrderReversalState.APPLY_REVERSAL 
				|| mainorder.getReversalState() == Const.OrderReversalState.PROCESSING 
				|| mainorder.getReversalState() == Const.OrderReversalState.THREAD_EXECUTING) {
			orderService.updateReversalState(mainorder, Const.OrderReversalState.PROCESS_SUCCESS);
			//冲正次数-1
			user.setReversalCount(user.getReversalCount()-1);
			userService.update(user);
			if (mainorder.getMoneyBack().equals(1)) {
				//转换冲正的卡密状态
				if (mainorder.getCardLibIds()!=null) {
					cardLibService.updateCardLibState(mainorder.getCardLibIds(),1);
				}
				operationLogService.addOperationLog(user, mainorder, mainorder.getMobile(),Const.OperationLogType.REVERSAL,Const.OperationLogResult.SUCCESS,null);
				//商户自动返还额度
//				useraccountService.refreshAccountes(mainorder,user,null,null,new UserCharge(), mainorder.getTotalMoney(),Const.UseraccountdealdetailFlag.REVERSAL);
				//供货商自动返还额度
				Supply supply = supplyService.findSupplyById(mainorder.getSupply().getId());
//				SupplyKernel.getInstance().cancelBalance(mainorder,supply, mainorder.getStockPrice()*mainorder.getGoodsNo(), new UserCharge());
				ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), user, null, null, Const.UseraccountdealdetailFlag.REVERSAL);
				SupplyInterface supplyInterface = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(mainorder.getSupplyInterface().getId());
				ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice(), (Supply)supply, null, null, Const.UseraccountdealdetailFlag.REVERSAL);
				UserChargeEngine.getInstance().addLast(iCharge);
			    UserChargeEngine.getInstance().addLast(iCharge2);
				//订单回水标记0
				orderService.updateMoneyBack(mainorder, 0);
			}
			return ShowMessage(MSG_TYPE_NORMAL, "转换成功", "点击返回", "list.do", 0);
		}
		return ShowMessage(MSG_TYPE_STOP, "订单当前状态不允许转换", "点击返回", "list.do", 0);
		
	}
	
	/**
	 * 强制订单冲正失败
	 * @return
	 * @throws Exception
	 */
	public String reverseOrderFailed() throws Exception {
		if (getSessionUser().getUsergroup().getGroupType()>Const.GroupType.STAFF) {
			return ShowMessage(MSG_TYPE_STOP, "没有权限", "点击返回", "list.do", 0);
		}
		mainorder = orderService.findOrderById(orderId);
		Users user = userService.findUserById(mainorder.getUsers().getUserId());
		if (mainorder.getReversalState() == Const.OrderReversalState.APPLY_REVERSAL 
				|| mainorder.getReversalState() == Const.OrderReversalState.PROCESSING 
				|| mainorder.getReversalState() == Const.OrderReversalState.THREAD_EXECUTING) {
			orderService.updateReversalState(mainorder, Const.OrderReversalState.PROCESS_FAILED);
			operationLogService.addOperationLog(user, mainorder, mainorder.getMobile(),Const.OperationLogType.REVERSAL,Const.OperationLogResult.FAILED,null);
			return ShowMessage(MSG_TYPE_NORMAL, "转换成功", "点击返回", "list.do", 0);
		}
		return ShowMessage(MSG_TYPE_STOP, "订单当前状态不允许转换", "点击返回", "list.do", 0);
		
	}
	
	
	/**
	 * 人工补充页面
	 * @return
	 * @throws Exception
	 */
	public String manualOrder() throws Exception{
		mainorder = orderService.findOrderById(orderId);
		modifyOrderManual = new ModifyOrderManual();
		copyProperties(modifyOrderManual, mainorder);
		return "manualOrder";
	}
	
	/**
	 * 人工补充提交
	 * @return
	 * @throws Exception
	 */
	public String doManualOrder() throws Exception{
		mainorder = orderService.findOrderById(orderId);
		copyProperties(mainorder, modifyOrderManual);
		mainorder.setManualUsers(getSessionUser());
		mainorder.setManualUserName(getSessionUser().getUserName());
		mainorder.setIsNeedManual(255);
		orderService.update(mainorder);
		//终端提款
		Users user = userService.findUserById(mainorder.getUsers().getUserId());
		ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), user, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.DRAWN);
		UserChargeEngine.getInstance().addLast(iCharge);
//		useraccountService.refreshAccountes(mainorder,mainorder.getUsers(),null,getSessionUser().getUserName(),new UserCharge(),
//				mainorder.getTotalMoney(), Const.UseraccountdealdetailFlag.DRAWN);
		return ShowMessage(MSG_TYPE_NORMAL, "提交成功", "点击返回", "list.do", 0);
	}
	
	
	/**
	 * 查询电信余额页面
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "telecomOrder", privilegeAccess = Const.PrivilegeAccessConst.ADD)
	public String queryBalance() throws Exception {
		Users user = this.getSessionUser();
		List<OrderVO> listLimitFive = orderService.findLimitFive(user.getUserId());
		this.getSession().put("listLimitFive", listLimitFive);
		return "queryBalance";
	}
	
	/**
	 * 电信刷新最近五笔交易
	 */
	public String refreshQueryBalance(){
		Users user = this.getSessionUser();
		listLimitFiveRefresh = orderService.findLimitFive(user.getUserId());
		String json = JSONArray.fromObject(listLimitFiveRefresh).toString();
		ResponseUtils.renderJson(getHttpServletResponse(), json);
		return null;
	}
	/**
	 * 全国刷新最近五笔交易
	 */
	public String refreshMobileOrder(){
		Users user = this.getSessionUser();
		listLimitFiveRefresh = orderService.findLimitFive(user.getUserId());
		String json = JSONArray.fromObject(listLimitFiveRefresh).toString();
		ResponseUtils.renderJson(getHttpServletResponse(), json);
		return null;
	}
	
	/**
	 * 查询电信余额提交
	 * @return
	 * @throws Exception
	 */
	public String doQueryBalance() throws Exception {
		if (mobile.equals("")||mobile == null) {
			return ShowMessage(MSG_TYPE_STOP, "请填写手机号码", "点击返回", "page!queryBalance.do", 0);
		}
		if (mobile.length()==8) {
			mobile = "020"+mobile;
		}
		if (mobile.length()>13) {
			return ShowMessage(MSG_TYPE_STOP, "号码长度不合法!", "点击返回", "page!queryBalance.do", 0);
		}
		
		switch (tradeType) {
		case 30:
			tradeType = 1;
			break;
		case 31:
			tradeType = 3;
			break;
		case 32:
			tradeType = 4;
			break;
		default:
			break;
		}
		XunyuanQueryBalanceReq req = xunYuanService.initQueryBalanceReq(mobile, tradeType);
		XunyuanKernel.sendQueryBalancePo(req);
//		do {
//			resp = xunYuanService.findQueryBalanceRespByStoreSeq(req.getStoreSeq());
//		} while (resp == null);
		storeSeq = req.getStoreSeq();
		return "doQueryBalance";
	}
	
	/**
	 * json刷查询记录
	 * @throws Exception
	 */
	public void query() throws Exception {
		//storeSeq = String.format("%020d", storeSeq);
		resp = xunYuanService.findQueryBalanceRespByStoreSeq(storeSeq);
		writeAjaxStr(JSONObject.fromObject(resp).toString());
	}
	
	
	public String chargeTelecom() throws Exception {
		newOrder();
		resp = xunYuanService.findQueryBalanceRespByStoreSeq(storeSeq);
		if (!resp.getRespCode().equals("0000")) {
			return ShowMessage(MSG_TYPE_STOP, resp.getMobile()+"号码有误,请查证", "点击返回", "page!queryBalance.do", 0);
		}
		mobile = resp.getMobile();
		return "chargeTelecom";
	}
	
	
//	/**
//	 * 是否正确号码(判断移动,联通,电信用)
//	 * @param goods
//	 * @param mobileNum
//	 * @return
//	 */
//	private boolean isCorrectMobile(Goods goods,String mobileNum){
//		String checkNum = mobileNum.substring(0, 3);
//		switch (goods.getGoodsFlag()) {
//		//TODO: 这里要动态获取
//		case 10:
//			return isContainsNum(mobileList, checkNum);
//		case 12:
//			return isContainsNum(unicomList, checkNum);
//		case 11:
//			return isContainsNum(telecomList, checkNum);
//		default:
//			return false;
//		}
//	}
//	
//	private boolean isContainsNum(String[] list,String mobileNum){
//		for (String string : list) {
//			if (string.equals(mobileNum)) {
//				return true;
//			}
//		}
//		return false;
//	}
	
	
	public Mainorder getMainorder() {
		return mainorder;
	}

	public void setMainorder(Mainorder mainorder) {
		this.mainorder = mainorder;
	}

	public Orderdetail getOrderdetail() {
		return orderdetail;
	}

	public void setOrderdetail(Orderdetail orderdetail) {
		this.orderdetail = orderdetail;
	}

	public Integer getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(Integer supplyId) {
		this.supplyId = supplyId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Double getUseableAccount() {
		return useableAccount;
	}

	public void setUseableAccount(Double useableAccount) {
		this.useableAccount = useableAccount;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}

	public String getCreditPassword() {
		return creditPassword;
	}

	public void setCreditPassword(String creditPassword) {
		this.creditPassword = creditPassword;
	}

	public String getConfirmMobile() {
		return confirmMobile;
	}

	public void setConfirmMobile(String confirmMobile) {
		this.confirmMobile = confirmMobile;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public Integer getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(Integer goodsNo) {
		this.goodsNo = goodsNo;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(Integer goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public List<GoodsType> getGoodsTypeList() {
		return goodsTypeList;
	}

	public void setGoodsTypeList(List<GoodsType> goodsTypeList) {
		this.goodsTypeList = goodsTypeList;
	}

	public Integer getGoodsFlag() {
		return goodsFlag;
	}

	public void setGoodsFlag(Integer goodsFlag) {
		this.goodsFlag = goodsFlag;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Double getPriceShow() {
		return priceShow;
	}

	public void setPriceShow(Double priceShow) {
		this.priceShow = priceShow;
	}
	
	public ModifyOrderManual getModifyOrderManual() {
		return modifyOrderManual;
	}

	public void setModifyOrderManual(ModifyOrderManual modifyOrderManual) {
		this.modifyOrderManual = modifyOrderManual;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public XunyuanQueryBalanceResp getResp() {
		return resp;
	}

	public void setResp(XunyuanQueryBalanceResp resp) {
		this.resp = resp;
	}

	public String getStoreSeq() {
		return storeSeq;
	}

	public void setStoreSeq(String storeSeq) {
		this.storeSeq = storeSeq;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getGoodsIdStr() {
		return goodsIdStr;
	}

	public void setGoodsIdStr(String goodsIdStr) {
		this.goodsIdStr = goodsIdStr;
	}

	public Integer getSupplyInterfaceId() {
		return supplyInterfaceId;
	}

	public void setSupplyInterfaceId(Integer supplyInterfaceId) {
		this.supplyInterfaceId = supplyInterfaceId;
	}

	public List<OrderVO> getListLimitFiveRefresh() {
		return listLimitFiveRefresh;
	}

	public void setListLimitFiveRefresh(List<OrderVO> listLimitFiveRefresh) {
		this.listLimitFiveRefresh = listLimitFiveRefresh;
	}

	public String getBatchOrderId() {
		return batchOrderId;
	}

	public void setBatchOrderId(String batchOrderId) {
		this.batchOrderId = batchOrderId;
	}

	public String getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}

	public String getCommentsOn() {
		return commentsOn;
	}

	public void setCommentsOn(String commentsOn) {
		this.commentsOn = commentsOn;
	}
	
	public String getFormUrl() {
		return formUrl;
	}

	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}

	public String getJsUrl() {
		return JsUrl;
	}

	public void setJsUrl(String jsUrl) {
		JsUrl = jsUrl;
	}

	public Query91KAProduct getQueryProduct() {
		return queryProduct;
	}

	public void setQueryProduct(Query91KAProduct queryProduct) {
		this.queryProduct = queryProduct;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getAutoGameId() {
		return autoGameId;
	}

	public void setAutoGameId(String autoGameId) {
		this.autoGameId = autoGameId;
	}

	public Integer getKaBaseNum() {
		return kaBaseNum;
	}

	public void setKaBaseNum(Integer kaBaseNum) {
		this.kaBaseNum = kaBaseNum;
	}

	public Integer getKaCardId() {
		return kaCardId;
	}

	public void setKaCardId(Integer kaCardId) {
		this.kaCardId = kaCardId;
	}

}
