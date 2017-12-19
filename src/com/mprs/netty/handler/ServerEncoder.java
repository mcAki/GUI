package com.mprs.netty.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * 编码，向客户端输出响应
 * @author 郭熹
 *
 */
public class ServerEncoder extends OneToOneEncoder{
	protected Log logger = LogFactory.getLog(this.getClass());
	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		//logger.info("进入ServerEncoder");
		if (!(msg instanceof byte[])) {
            return msg;
        }
 
		byte[] messageBytes = (byte[])msg;     
//		//跟踪器
//		ITraceGenerator traceGenerator = new SimpleTraceGenerator((int)(System.currentTimeMillis() % 100000));
//		//校验器
//		IVerification verification = new Crc32Verification();
//		ChannelBuffer buf = ChannelBuffers.dynamicBuffer();
//        PackageUtil.write(buf, vo, traceGenerator, verification);
//        channel.write(buf);

		//获取频道
		ChannelBuffer buf = ChannelBuffers.dynamicBuffer();
		buf.writeBytes(messageBytes);
        //logger.info("离开ServerEncoder");
        return buf;

	}

}
