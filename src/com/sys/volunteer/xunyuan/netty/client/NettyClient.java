package com.sys.volunteer.xunyuan.netty.client;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sys.volunteer.pojo.XunyuanQueryBalanceReq;
import com.sys.volunteer.xunyuan.XunYuanService;
import com.sys.volunteer.xunyuan.netty.core.Context;
import com.sys.volunteer.xunyuan.netty.core.NettyConnectEngine;
import com.sys.volunteer.xunyuan.netty.core.XunyuanKernel;
import com.sys.volunteer.xunyuan.netty.core.NettyConnectEngine.EnumConnectState;
import com.sys.volunteer.xunyuan.netty.handler.NettyClientEncode;
import com.sys.volunteer.xunyuan.netty.handler.NettyClientHandler;
import com.sys.volunteer.xunyuan.protocol.query.QueryBalancePo;

/**
 * 单例
 * @author Administrator
 *
 */
public class NettyClient {
	
	private static NettyClient hinstance;
	
	private Channel channel;
	
	private ClientBootstrap bootstrap;
	
	private NioClientSocketChannelFactory factory;
	
	private NettyClient() {

//		this.connect(XunyuanKernel.XUNYUANIP, XunyuanKernel.XUNYUANPORT);
		//生成反射数组
		Context.initParseMap();
//		channel.write(po);
	}
	
	/**
	 * 获取单例
	 * 
	 * @return
	 * @throws Throwable
	 */
	public static NettyClient getInstance() {
		if (hinstance == null) init();
		return hinstance;
	}

	/**
	 * 手动初始化
	 */
	public static void init(){
		hinstance = new NettyClient();
	}
	
	public void connect(String ip,int port) {
		factory = new NioClientSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());
		
		bootstrap = new ClientBootstrap(factory);

		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

			@Override
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(
//						new NettyClientDecode(),
						new NettyClientEncode(),
						new NettyClientHandler());
			}
		});

		bootstrap.setOption("tcpNoDelay", true);
		bootstrap.setOption("keepAlive", true);
		
		ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(
				ip, port));
		channel = channelFuture.getChannel();
		channelFuture.addListener(new ChannelFutureListener() {
			
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if (future.isSuccess()) {
					NettyConnectEngine.setConnectState(EnumConnectState.connected);
				}else {
					NettyConnectEngine.setConnectState(EnumConnectState.disconnected);
				}
			}
		});
	}
	
	public void close() {
		channel.unbind();
		System.out.println("channel unbind!");
		channel.close().awaitUninterruptibly();
		System.out.println("channel close!");
		bootstrap.releaseExternalResources();
		System.out.println("release resources");
	}
	
	public static void main(String[] args) throws Exception {
		
//		LoginBattleRoomPo loginBattleRoomPo = new LoginBattleRoomPo();
//		loginBattleRoomPo.setName("player2");
//		loginBattleRoomPo.setPassWord("123456");
//		Context.initProtocolMap();
//		Context.initParseMap();
//		new NettyClient().connect(new LoginPo());
//		ApplicationContext act = new ClassPathXmlApplicationContext(
//				new String[] { "applicationSpringContext-main.xml" });
//		XunYuanService xunYuanService = (XunYuanService) act.getBean("xunYuanServiceBean");
//		NettyClient.getInstance(XunyuanKernel.XUNYUANIP,XunyuanKernel.XUNYUANPORT).getChannel().write(new LoginPo());
//		NettyClient.getInstance(XunyuanKernel.XUNYUANIP,XunyuanKernel.XUNYUANPORT).getChannel().write(new HeartBeatPo());
//		DetachedCriteria dec = DetachedCriteria.forClass(XunyuanChargeReq.class);
//		dec.add(Restrictions.eq("orderId", "402880e53bb2eaf0013bb2eeca170002"));
//		XunyuanChargeReq req = (XunyuanChargeReq) xunYuanService.findByDetachedCriteria(dec).get(0);
//		ChargePo chargePo = new ChargePo(req.getTradeType(),req.getAmount(),req.getMobile(),req.getStoreSeq());
//		NettyClient.getInstance(XunyuanKernel.XUNYUANIP,XunyuanKernel.XUNYUANPORT).getChannel().write(chargePo);
		
//		ReversePo reversePo = new ReversePo();
//		reversePo.setAmount("0000010000");
//		reversePo.setMobile("13802412056");
//		reversePo.setReverseStoreSeq(System.currentTimeMillis()+"");
//		reversePo.setStoreSeq(System.currentTimeMillis()+"");
//		NettyClient.getInstance().getChannel().write(reversePo);
//		NettyClient.getInstance().getChannel().write(new LogoutPo());
		ApplicationContext act = new ClassPathXmlApplicationContext(
				new String[] { "applicationSpringContext-main.xml" });
		XunYuanService xunYuanService = (XunYuanService) act.getBean("xunYuanServiceBean");
//		XunyuanQueryReq req = xunYuanService.findQueryReqByOrderId("402880e53bb731af013bb7382be50004",1);
//		NettyClient.getInstance(XunyuanKernel.XUNYUANIP,XunyuanKernel.XUNYUANPORT).getChannel().write(new LoginPo());
//		NettyClient.getInstance(XunyuanKernel.XUNYUANIP,XunyuanKernel.XUNYUANPORT).getChannel().write(new HeartBeatPo());
//		QueryPo po = new QueryPo(req.getTradeType(), req.getAmount(), req.getMobile(), req.getQueryStoreSeq(), req.getStoreSeq());
//		NettyClient.getInstance(XunyuanKernel.XUNYUANIP,XunyuanKernel.XUNYUANPORT).getChannel().write(po);
		XunyuanQueryBalanceReq req = xunYuanService.findQueryBalanceReqByStoreSeq("00000001356352004388");
		QueryBalancePo po = new QueryBalancePo(req.getTradeType(), req.getMobile(), req.getStoreSeq());
//		NettyClient.getInstance(XunyuanKernel.XUNYUANIP,XunyuanKernel.XUNYUANPORT).getChannel().write(po);
	}

	public Channel getChannel() {
		return channel;
	}

	public ClientBootstrap getBootstrap() {
		return bootstrap;
	}

}
