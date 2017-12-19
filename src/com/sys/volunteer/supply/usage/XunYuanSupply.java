package com.sys.volunteer.supply.usage;

import java.util.LinkedList;

import org.springframework.context.ApplicationContext;

import com.sys.volunteer.common.Const;
import com.sys.volunteer.goods.GoodsService;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.XunyuanChargeReq;
import com.sys.volunteer.pojo.XunyuanChargeResp;
import com.sys.volunteer.pojo.XunyuanQueryReq;
import com.sys.volunteer.pojo.XunyuanReverseReq;
import com.sys.volunteer.pojo.XunyuanReverseResp;
import com.sys.volunteer.springContext.SpringContextUtil;
import com.sys.volunteer.xunyuan.XunYuanService;
import com.sys.volunteer.xunyuan.charge.XunYuanNettyThreadEngine;
import com.sys.volunteer.xunyuan.netty.core.XunyuanKernel;

public class XunYuanSupply extends Supply implements ISupply {

	private static XunYuanService xunYuanService;
	private static GoodsService goodsService;
	
	@Override
	public int recharge(Mainorder mainorder, String mobile, SupplyInterface supplyInterface) {
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		xunYuanService = (XunYuanService) act.getBean("xunYuanServiceBean");
		XunYuanNettyThreadEngine engine = XunYuanNettyThreadEngine.getInstance();
		XunyuanChargeReq req = xunYuanService.initChargeReq(mainorder);
		//初始化resp
		xunYuanService.initChargeResp(req);
		//生成po,放在发送列队
		XunyuanKernel.sendChargePo(req);
		//即刻唤醒线程下单
		engine.forceRefreshOrder();
		return Const.OperationLogResult.PROCESSING;
	}
	
	@Override
	public int reverse(Mainorder mainorder) {
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		xunYuanService = (XunYuanService) act.getBean("xunYuanServiceBean");
		XunYuanNettyThreadEngine engine = XunYuanNettyThreadEngine.getInstance();
		//生成冲正req
		XunyuanReverseReq req = xunYuanService.initReverseReq(mainorder);
		//生成冲正resp
		xunYuanService.initReverseResp(req);
		XunyuanKernel.sendReversePo(req);
		engine.forceRefreshOrder();
		return Const.OperationLogResult.PROCESSING;
	}
	
	@Override
	public int refreshState(Mainorder mainorder) throws Exception{
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		xunYuanService = (XunYuanService) act.getBean("xunYuanServiceBean");
		goodsService = (GoodsService) act.getBean("goodsServiceBean");
		XunYuanNettyThreadEngine engine = XunYuanNettyThreadEngine.getInstance();
		int logFor = 0;
		if (mainorder.getState().equals(Const.MainOrderState.THREAD_EXECUTING)) {
			logFor = 1;
		}else if (mainorder.getReversalState().equals(Const.OrderReversalState.THREAD_EXECUTING)) {
			logFor = 2;
		}
		Goods goods = goodsService.findById(mainorder.getGoods().getGoodsId());
		
		if (goods.getGoodsFlag().equals(12)||goods.getGoodsFlag().equals(10)) {
			if (logFor == 1) {
				XunyuanChargeResp chargeResp = xunYuanService.findChargeRespByOrderId(mainorder.getMainOrderId());
				int result = xunYuanService.refreshOrderState(mainorder, chargeResp);
				return result;
			}else {
				XunyuanReverseResp reverseResp = xunYuanService.findReverseRespByOrderId(mainorder.getMainOrderId());
				int result = xunYuanService.refreshOrderState(mainorder, reverseResp);
				return result;
			}
		}else {
			XunyuanQueryReq xunyuanQueryReq = xunYuanService.findQueryReqByOrderId(mainorder.getMainOrderId(),logFor);
			if (xunyuanQueryReq != null) {
				XunyuanKernel.sendQueryOrderPo(xunyuanQueryReq);
				engine.forceRefreshOrder();
			}
		}
		return Const.OperationLogResult.PROCESSING;
	}
}
