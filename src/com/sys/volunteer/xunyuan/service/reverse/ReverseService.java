package com.sys.volunteer.xunyuan.service.reverse;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

import com.sys.volunteer.xunyuan.netty.core.XunyuanKernel;
import com.sys.volunteer.xunyuan.protocol.reverse.ReversePo_Rp;
import com.sys.volunteer.xunyuan.service.BaseService;

public class ReverseService extends BaseService {

	
	public Object reverseReturnInvoke(ChannelHandlerContext ctx, MessageEvent e, Object vo) throws Exception{
		ReversePo_Rp rp = (ReversePo_Rp) vo;
		XunyuanKernel.getInstance().updateReverseResp(rp);
		return null;
	}
}
