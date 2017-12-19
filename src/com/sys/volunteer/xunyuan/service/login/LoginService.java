package com.sys.volunteer.xunyuan.service.login;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

import com.sys.volunteer.xunyuan.charge.XunYuanNettyThreadEngine;
import com.sys.volunteer.xunyuan.protocol.login.LoginPo_Rp;
import com.sys.volunteer.xunyuan.protocol.login.LogoutPo_Rp;
import com.sys.volunteer.xunyuan.service.BaseService;

public class LoginService extends BaseService {

	/**
	 * 登陆
	 * @param ctx
	 * @param e
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Object loginReturnInvoke(ChannelHandlerContext ctx, MessageEvent e, Object vo) throws Exception {
		LoginPo_Rp rp = (LoginPo_Rp)vo;
		if (rp.getRespCode().equals("0000")) {
			XunYuanNettyThreadEngine.getInstance();
			//登陆成功
			XunYuanNettyThreadEngine.setLogin(true);
			System.out.println("登陆成功!");
		}else {
			System.out.println("登陆失败,请联系讯源客服!");
		}
		return null;
	}
	
	
	/**
	 * 注销
	 * @param ctx
	 * @param e
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Object logoutReturnInvoke(ChannelHandlerContext ctx, MessageEvent e, Object vo) throws Exception {
		LogoutPo_Rp rp = (LogoutPo_Rp)vo;
		if (rp.getRespCode().equals("0000")) {
			//注销成功
			System.out.println("注销成功!");
		}
		return null;
	}
}
