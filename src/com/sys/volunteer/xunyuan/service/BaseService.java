package com.sys.volunteer.xunyuan.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.Log4JLoggerFactory;

import com.sys.volunteer.xunyuan.netty.core.Context;
import com.sys.volunteer.xunyuan.protocol.BasePo;
import com.sys.volunteer.xunyuan.protocol.ResponeClass;

public abstract class BaseService implements IService{

	InternalLogger log = Log4JLoggerFactory.getInstance(BaseService.class);
	
	/**
	 * 启用模板模式
	 * @param ctx
	 * @param e
	 * @param vo
	 */
	@Override
	public void deal(ChannelHandlerContext ctx, MessageEvent e,
			Object vo){
		BasePo po=(BasePo)vo;
		ResponeClass responeClass = Context.parseMap.get(po.getCommandId());
		String methodName = responeClass.getMethodName();
		Method method;
		try {
			method = this.getClass().getMethod(methodName, ChannelHandlerContext.class,MessageEvent.class,Object.class);
			log.info("进入" + method.getName() + "方法！！！！");
			Object object = method.invoke(this, ctx,e,vo);
			if(object != null){
				e.getChannel().write(object);
			}
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
	}
	
}
