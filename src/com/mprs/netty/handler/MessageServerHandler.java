package com.mprs.netty.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 * 消息处理类
 * 
 * @author 郭熹
 */
public class MessageServerHandler extends SimpleChannelUpstreamHandler {
	protected Log logger = LogFactory.getLog(this.getClass());

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		//TODO:这里需要断开频道逻辑
		super.channelDisconnected(ctx, e);
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		super.channelClosed(ctx, e);
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		super.channelConnected(ctx, e);
	}

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		super.channelOpen(ctx, e);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		e.getCause().printStackTrace();
		Channel ch = e.getChannel();
		ch.close();
	}

	/**
	 * 核心处理逻辑
	 */
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		//logger.info("进入消息处理器");
//		if (!(e.getMessage() instanceof BasePo)) {
//			return;
//		}
//		BasePo baseVo = (BasePo) e.getMessage();
//
//		long runningTimeMillis = System.currentTimeMillis();
//
//		int commandId = baseVo.getCommandId();
//		// String className=SysUtil.loadConfig("/serviceConfig.properties",
//		// String.valueOf(commandId));
//		// 获得处理消息的具体服务类
//		String className = Context.getInstance().getProtocolMap().get(commandId).getSpringBeanName();
//		String invokeName = Context.getInstance().getProtocolMap().get(commandId).getMethodName();
//		logger.info("获得的服务处理类是：" + className);
//		try {
//			// 反射spring服务类
////			IService baseService = (IService) Context.getInstance().getSpringContext().getBean(
////				className);
////			baseService.deal(ctx, e, baseVo);
//			IService baseService = (IService) SpringContextUtil.getBean(className);
//			baseService.deal(ctx, e, baseVo);
//		} catch (Exception e2) {
//			logger.error("[ERR:Refactor] 反射内部出错!");
//			logger.error(e2.getStackTrace());
//		} finally{
//			runningTimeMillis = System.currentTimeMillis() - runningTimeMillis;
//			
//			logger.info("[" + className + "的" + invokeName + "方法] 完毕.  耗时:" + runningTimeMillis + "毫秒");
//			if(runningTimeMillis>500){
//				if(runningTimeMillis>1000){
//					logger.info("[WAR:TimeHigh] 耗时超1秒");
//				}else if(runningTimeMillis>2000){
//					logger.info("[WAR:TimeHigh] 耗时超2秒");
//				}else if(runningTimeMillis>5000){
//					logger.info("[WAR:TimeHigh] 耗时超5秒");
//				}else if(runningTimeMillis>10000){
//					logger.info("[WAR:TimeHigh] 耗时超10秒");
//				}else {
//					logger.info("[WAR:TimeHigh] 耗时超500毫秒");
//				}
//			}
//		}
//		Channel ch=e.getChannel();
//		ch.write(e.getMessage());

		ChannelBuffer buf=(ChannelBuffer)e.getMessage();
		while (buf.readable()) {
			System.out.println((char)buf.readByte());
			
		}


	}

}
