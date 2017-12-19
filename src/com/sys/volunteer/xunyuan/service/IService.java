package com.sys.volunteer.xunyuan.service;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

public interface IService  {

	
	/**
	 * 启用模板模式
	 * @param ctx
	 * @param e
	 * @param vo
	 */
	public  void deal(ChannelHandlerContext ctx, MessageEvent e,
			Object vo);
}
