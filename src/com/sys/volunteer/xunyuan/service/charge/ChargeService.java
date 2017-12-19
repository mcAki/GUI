package com.sys.volunteer.xunyuan.service.charge;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

import com.sys.volunteer.xunyuan.netty.core.XunyuanKernel;
import com.sys.volunteer.xunyuan.protocol.charge.ChargePo_Rp;
import com.sys.volunteer.xunyuan.protocol.charge.ChargeResultNotifyPo_Rp;
import com.sys.volunteer.xunyuan.service.BaseService;

public class ChargeService extends BaseService {

	
	public Object chargeReturnInvoke(ChannelHandlerContext ctx, MessageEvent e, Object vo) throws Exception{
		ChargePo_Rp rp = (ChargePo_Rp) vo;
		XunyuanKernel xunyuanKernel = XunyuanKernel.getInstance();
		xunyuanKernel.updateChargeResp(rp);
		return null;
	}
	
	public Object chargeResultNotifyInvoke(ChannelHandlerContext ctx, MessageEvent e, Object vo) throws Exception {
		ChargeResultNotifyPo_Rp rp = (ChargeResultNotifyPo_Rp) vo;
		XunyuanKernel xunyuanKernel = XunyuanKernel.getInstance();
		xunyuanKernel.updateChargeResultNotifyResp(rp);
		return null;
	}
}
