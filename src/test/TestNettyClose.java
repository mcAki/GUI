package test;


import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;

import com.sys.volunteer.xunyuan.charge.XunYuanNettyThreadEngine;
import com.sys.volunteer.xunyuan.netty.client.NettyClient;
import com.sys.volunteer.xunyuan.netty.core.XunyuanKernel;

public class TestNettyClose {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XunYuanNettyThreadEngine engine = XunYuanNettyThreadEngine.getInstance();
		
		engine.start();
		
		Channel channel = engine.getChannel();
		channel.close();
		
		Thread thread = new Thread();
		
		try {
			for (int i = 0; i < 10; i++) {
				if (!channel.isOpen()) {
					
					channel.unbind();
					System.out.println("channel unbind!");
					channel.close().awaitUninterruptibly();
					System.out.println("channel close!");
					engine.getBootstrap().releaseExternalResources();
					System.out.println("release resources");
					
					NettyClient.getInstance().connect(XunyuanKernel.XUNYUANIP, XunyuanKernel.XUNYUANPORT);
					System.out.println("netty reconnect");
				}
				thread.sleep(2000);
			}
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
