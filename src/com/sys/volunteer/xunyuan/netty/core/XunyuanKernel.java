package com.sys.volunteer.xunyuan.netty.core;

import org.springframework.context.ApplicationContext;

import com.sys.volunteer.goods.GoodsService;
import com.sys.volunteer.order.OrderService;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.XunyuanChargeReq;
import com.sys.volunteer.pojo.XunyuanChargeResp;
import com.sys.volunteer.pojo.XunyuanChargeResultNotifyResp;
import com.sys.volunteer.pojo.XunyuanQueryBalanceReq;
import com.sys.volunteer.pojo.XunyuanQueryReq;
import com.sys.volunteer.pojo.XunyuanQueryResp;
import com.sys.volunteer.pojo.XunyuanReverseReq;
import com.sys.volunteer.pojo.XunyuanReverseResp;
import com.sys.volunteer.springContext.SpringContextUtil;
import com.sys.volunteer.xunyuan.XunYuanService;
import com.sys.volunteer.xunyuan.charge.XunYuanNettyThreadEngine;
import com.sys.volunteer.xunyuan.protocol.charge.ChargePo;
import com.sys.volunteer.xunyuan.protocol.charge.ChargePo_Rp;
import com.sys.volunteer.xunyuan.protocol.charge.ChargeResultNotifyPo_Rp;
import com.sys.volunteer.xunyuan.protocol.login.LogoutPo;
import com.sys.volunteer.xunyuan.protocol.query.QueryBalancePo;
import com.sys.volunteer.xunyuan.protocol.query.QueryBalancePo_Rp;
import com.sys.volunteer.xunyuan.protocol.query.QueryPo;
import com.sys.volunteer.xunyuan.protocol.query.QueryPo_Rp;
import com.sys.volunteer.xunyuan.protocol.reverse.ReversePo;
import com.sys.volunteer.xunyuan.protocol.reverse.ReversePo_Rp;

public class XunyuanKernel {

	/**
	 * 测试ip
	 */
	public final static String TESTIP = "219.135.147.234";
	/**
	 * 测试端口
	 */
	public final static int TESTPORT = 9110;
	/**
	 * 测试用企业id
	 */
	public final static String TESTUSERID = "02000600";
	/**
	 * 测试用登陆密码(需MD5加密)
	 */
	public final static String TESTLOGINPWD = "123";
	/**
	 * 测试用交易密码(需MD5加密)
	 */
	public final static String TESTTRADPWD = "123";
	/**
	 * 测试用代理电话
	 */
	public final static String TESTCORPMOBILE = "  15344444444";
	
	/**
	 * ip
	 */
	public final static String XUNYUANIP = "14.18.206.244";//"58.63.225.52";
	/**
	 * 端口
	 */
	public final static int XUNYUANPORT = 8300;
	
	/**
	 * 端口
	 */
	public final static int XUNYUANPORT2 = 8100;
	/**
	 * 用企业id
	 */
	public final static String XUNYUANUSERID = "02003300";
	/**
	 * 登陆密码(需MD5加密)
	 */
	public final static String LOGINPWD = "RYD_123";
	/**
	 * 交易密码(需MD5加密)
	 */
	public final static String TRADPWD = "162534";
	/**
	 * 用代理电话
	 */
	public final static String CORPMOBILE = "  13632290688";
	
	/**
	 * 用企业id2
	 */
	public final static String XUNYUANUSERID2 = "02004400";
	/**
	 * 用登陆密码2(需MD5加密)
	 */
	public final static String LOGINPWD2 = "RYD_321";
	/**
	 * 用交易密码2(需MD5加密)
	 */
	public final static String TRADPWD2 = "162534";
	/**
	 * 用代理电话2
	 */
	public final static String CORPMOBILE2 = "  13660212175";
	
	private static XunyuanKernel hinstance;
	
	private static XunYuanService xunYuanService;
	
	private static OrderService orderService;
	
	private static GoodsService goodsService;
	
	private XunyuanKernel() {
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		xunYuanService = (XunYuanService) act.getBean("xunYuanServiceBean");
		orderService = (OrderService) act.getBean("orderServiceBean");
		goodsService = (GoodsService) act.getBean("goodsServiceBean");
	}
	
	/**
	 * 获取单例
	 * 
	 * @return
	 * @throws Throwable
	 */
	public static XunyuanKernel getInstance() {
		if (hinstance == null) init();
		return hinstance;
	}

	/**
	 * 手动初始化
	 */
	public static void init(){
		hinstance = new XunyuanKernel();
	}
	
	/**
	 * 发充值包
	 * @param req
	 */
	public static void sendChargePo(XunyuanChargeReq req) {
		ChargePo chargePo = new ChargePo(req.getTradeType(), req.getAmount(), req.getMobile(), req.getStoreSeq());
		XunYuanNettyThreadEngine.getInstance().addSendList(chargePo);
		
	}
	
	/**
	 * 发冲正包
	 * @param req
	 */
	public static void sendReversePo(XunyuanReverseReq req) {
		ReversePo reversePo = new ReversePo(req.getAmount(), req.getMobile(), req.getReverseSeq(), req.getStoreSeq());
		XunYuanNettyThreadEngine.getInstance().addSendList(reversePo);
	}
	
	/**
	 * 发查询包
	 * @param req
	 */
	public static void sendQueryOrderPo(XunyuanQueryReq req) {
		QueryPo queryPo = new QueryPo(req.getTradeType(), req.getAmount(), req.getMobile(), req.getQueryStoreSeq(), req.getStoreSeq());
		XunYuanNettyThreadEngine.getInstance().addSendList(queryPo);
	}
	
	/**
	 * 发查询余额包
	 * @param req
	 */
	public static void sendQueryBalancePo(XunyuanQueryBalanceReq req) {
		System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\讯源增加余额查询\\\\\\\\\\\\\\\\\\\\\\\\");
		QueryBalancePo queryBalancePo = new QueryBalancePo(req.getTradeType(), req.getMobile(), req.getStoreSeq());
		XunYuanNettyThreadEngine.getInstance().addSendList(queryBalancePo);
		System.out.println("========================"+queryBalancePo+"=================");
	}
	
	/**
	 * 发登出包
	 */
	public static void sendLogoutPo() {
		LogoutPo logoutPo = new LogoutPo();
		XunYuanNettyThreadEngine.getInstance().addSendList(logoutPo);
	}
	

	/**
	 * 保存充值返回包数据
	 * @param chargePoRp
	 */
	public void updateChargeResp(ChargePo_Rp chargePoRp) {
		//找到resp
		XunyuanChargeResp resp = xunYuanService.findChargeRespByStoreSeq(chargePoRp.getStoreSeq());
		xunYuanService.updateXunYuanChargeResp(resp, chargePoRp);
		//生成交易结果数据
		initChargeResultNotifyResp(resp);
		//生成查询
		Mainorder mainorder = orderService.findOrderById(resp.getOrderId());
		Goods goods = goodsService.findById(mainorder.getGoods().getGoodsId());
		//移动,联通不刷
		if (!goods.getGoodsFlag().equals(12)&&!goods.getGoodsFlag().equals(10)) {
			XunyuanQueryReq req = xunYuanService.initXunyuanQueryReq(mainorder, resp);
			xunYuanService.initXunyuanQueryResp(req, mainorder);
		}
	}
	
	/**
	 * 保存返回冲正数据
	 * @param reversePoRp
	 */
	public void updateReverseResp(ReversePo_Rp reversePoRp) {
		XunyuanReverseResp resp = xunYuanService.findReverseRespByStoreSeq(reversePoRp.getStoreSeq());
		xunYuanService.updateXunyuanReverseResp(resp, reversePoRp);
		//不用生成查询
		//刷新订单状态
		Mainorder mainorder = orderService.findOrderById(resp.getOrderId());
		xunYuanService.refreshOrderState(mainorder, resp);
	}
	
	/**
	 * 保存返回查询数据
	 * @param reversePoRp
	 */
	public void updateQueryResp(QueryPo_Rp queryPoRp) {
		XunyuanQueryResp resp = xunYuanService.findQueryRespByStoreSeq(queryPoRp.getStoreSeq());
		Mainorder mainorder = orderService.findOrderById(resp.getOrderId());
		resp = xunYuanService.updateXunyuanQueryResp(resp, queryPoRp);
		//刷新订单状态
		xunYuanService.refreshOrderState(mainorder, resp);
	}
	
	/**
	 * 保存返回查询余额数据
	 * @param queryBalancePoRp
	 */
	public void initQueryBalanceResp(QueryBalancePo_Rp queryBalancePoRp) {
		xunYuanService.initQueryBalanceResp(queryBalancePoRp);
	}
	
	/**
	 * 生成充值返回0001时交易结果数据
	 * @param mainorder
	 */
	public void initChargeResultNotifyResp(XunyuanChargeResp chargeResp) {
		xunYuanService.initChargeResultNotifyResp(chargeResp);
	}
	
	/**
	 * 更新交易结果数据
	 * @param chargeResultNotifyPoRp
	 */
	public void updateChargeResultNotifyResp(ChargeResultNotifyPo_Rp chargeResultNotifyPoRp) {
		XunyuanChargeResultNotifyResp resp = xunYuanService.findChargeResultNotifyRespByStoreSeq(chargeResultNotifyPoRp.getStoreSeq());
		xunYuanService.updateChargeResultNotifyResp(resp, chargeResultNotifyPoRp);
	}
}
