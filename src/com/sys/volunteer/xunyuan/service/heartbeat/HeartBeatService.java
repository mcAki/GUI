package com.sys.volunteer.xunyuan.service.heartbeat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

import com.sys.volunteer.xunyuan.charge.XunYuanNettyThreadEngine;
import com.sys.volunteer.xunyuan.protocol.heartbeat.HeartBeatPo_Rp;
import com.sys.volunteer.xunyuan.service.BaseService;

public class HeartBeatService extends BaseService {

	protected Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * 心跳
	 * @param ctx
	 * @param e
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Object heartbeatReturnInvoke(ChannelHandlerContext ctx, MessageEvent e, Object vo) throws Exception{
		HeartBeatPo_Rp rp = (HeartBeatPo_Rp) vo;
		if (rp.getMsgCode().equals("800119")) {
			//有心跳包返回
			logger.info("讯源返回心跳");
			//更新心跳时间
			XunYuanNettyThreadEngine.getInstance().setHeartbeatTime(System.currentTimeMillis());
		}else {
			//TODO:心跳包没有返回,连接断开,需要重新链接登陆
//			NettyClient.getInstance().getChannel().write(new LoginPo());
			XunYuanNettyThreadEngine.setLogin(false);
		}
		return null;
	}
}
