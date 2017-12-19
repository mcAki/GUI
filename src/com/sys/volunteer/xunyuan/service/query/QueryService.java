package com.sys.volunteer.xunyuan.service.query;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

import com.sys.volunteer.xunyuan.netty.core.XunyuanKernel;
import com.sys.volunteer.xunyuan.protocol.query.QueryBalancePo_Rp;
import com.sys.volunteer.xunyuan.protocol.query.QueryPo_Rp;
import com.sys.volunteer.xunyuan.service.BaseService;

public class QueryService extends BaseService {

	
	public Object queryReturnInvoke(ChannelHandlerContext ctx, MessageEvent e, Object vo) throws Exception{
		QueryPo_Rp rp = (QueryPo_Rp) vo;
		XunyuanKernel.getInstance().updateQueryResp(rp);
		return null;
	}
	
	public Object queryBalanceReturnInvoke(ChannelHandlerContext ctx, MessageEvent e, Object vo) throws Exception{
		QueryBalancePo_Rp rp = (QueryBalancePo_Rp) vo;
		XunyuanKernel.getInstance().initQueryBalanceResp(rp);
		return null;
	}
}
