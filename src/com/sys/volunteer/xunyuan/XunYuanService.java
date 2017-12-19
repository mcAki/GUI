package com.sys.volunteer.xunyuan;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.XunyuanChargeReq;
import com.sys.volunteer.pojo.XunyuanChargeResp;
import com.sys.volunteer.pojo.XunyuanChargeResultNotifyResp;
import com.sys.volunteer.pojo.XunyuanQueryBalanceReq;
import com.sys.volunteer.pojo.XunyuanQueryBalanceResp;
import com.sys.volunteer.pojo.XunyuanQueryReq;
import com.sys.volunteer.pojo.XunyuanQueryResp;
import com.sys.volunteer.pojo.XunyuanReverseReq;
import com.sys.volunteer.pojo.XunyuanReverseResp;
import com.sys.volunteer.xunyuan.protocol.charge.ChargePo_Rp;
import com.sys.volunteer.xunyuan.protocol.charge.ChargeResultNotifyPo_Rp;
import com.sys.volunteer.xunyuan.protocol.query.QueryBalancePo_Rp;
import com.sys.volunteer.xunyuan.protocol.query.QueryPo_Rp;
import com.sys.volunteer.xunyuan.protocol.reverse.ReversePo_Rp;

public interface XunYuanService extends IDao {

	public XunyuanChargeReq initChargeReq(Mainorder mainorder);
	
	public XunyuanReverseReq initReverseReq(Mainorder mainorder);

	public XunyuanChargeResp initChargeResp(XunyuanChargeReq xunyuanChargeReq);
	
	public XunyuanReverseResp initReverseResp(XunyuanReverseReq xunyuanReverseReq);

	public void updateXunYuanChargeResp(XunyuanChargeResp xunyuanChargeResp,
			ChargePo_Rp chargePoRp);
	
	public void updateXunyuanReverseResp(XunyuanReverseResp xunyuanReverseResp,
			ReversePo_Rp reversePoRp);
	
	public XunyuanChargeResp findChargeRespByOrderId(String orderId);

	public XunyuanChargeResp findChargeRespByStoreSeq(String storeSeq);

	public XunyuanQueryResp initXunyuanQueryResp(XunyuanQueryReq xunyuanQueryReq,
			Mainorder mainorder);
	
	public XunyuanQueryResp updateXunyuanQueryResp(XunyuanQueryResp xunyuanQueryResp, QueryPo_Rp queryPoRp);
	
	public XunyuanQueryReq findQueryReqByOrderId(String orderId,int logFor);
	
	public XunyuanQueryResp findQueryRespByStoreSeq(String storeSeq);

	public XunyuanQueryReq initXunyuanQueryReq(Mainorder mainorder,
			XunyuanChargeResp resp);
	
	public int refreshOrderState(Mainorder mainorder,XunyuanQueryResp resp);

//	public XunyuanQueryReq initXunyuanQueryReq(Mainorder mainorder,
//			XunyuanReverseResp resp);

	public XunyuanQueryResp findQueryRespByOrderId(String orderId);

	public XunyuanReverseResp findReverseRespByStoreSeq(String storeSeq);

	public XunyuanReverseResp findReverseRespByOrderId(String orderId);

	public XunyuanQueryBalanceReq initQueryBalanceReq(String mobile, int tradeType);

	public XunyuanQueryBalanceResp initQueryBalanceResp(QueryBalancePo_Rp rp);

	public XunyuanQueryBalanceResp findQueryBalanceRespByStoreSeq(String storeSeq);

	public XunyuanQueryBalanceReq findQueryBalanceReqByStoreSeq(String storeSeq);

	public int refreshOrderState(Mainorder mainorder, XunyuanChargeResp resp);

	public int refreshOrderState(Mainorder mainorder, XunyuanReverseResp resp);

	public XunyuanChargeResultNotifyResp initChargeResultNotifyResp(XunyuanChargeResp chargeResp);

	public XunyuanChargeResultNotifyResp updateChargeResultNotifyResp(
			XunyuanChargeResultNotifyResp resp,
			ChargeResultNotifyPo_Rp chargeResultNotifyPoRp);

	public XunyuanChargeResultNotifyResp findChargeResultNotifyRespByOrderId(
			String orderId);

	public XunyuanChargeResultNotifyResp findChargeResultNotifyRespByStoreSeq(
			String storeSeq);
}
