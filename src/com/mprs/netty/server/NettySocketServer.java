package com.mprs.netty.server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.mprs.netty.handler.MessageServerHandler;
import com.mprs.netty.handler.ServerDecoder;

public class NettySocketServer {



	public static void InitPlayer() {
		//Context context = Context.getInstance();
	}

	/**
	 * netty服务器启动
	 */
	public static void start(int port) {
		ChannelFactory factory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());

		ServerBootstrap bootstrap = new ServerBootstrap(factory);
		
		MessageServerHandler messageServerHandler = new MessageServerHandler();
		ServerDecoder serverDecoder = new ServerDecoder();
//		ServerEncoder serverEncoder = new ServerEncoder();

		ChannelPipeline pipeline = bootstrap.getPipeline();
		pipeline.addLast("serverDecoder", serverDecoder);
		pipeline.addLast("messageServerHandler", messageServerHandler);
//		pipeline.addLast("serverEncoder", serverEncoder);

//		bootstrap.setOption("child.tcpNoDelay", true);
//		bootstrap.setOption("child.keepAlive", true);

		bootstrap.bind(new InetSocketAddress(port));

		System.out.println("[Socket Server Start On: " + port + " ]");
		
	}

}
