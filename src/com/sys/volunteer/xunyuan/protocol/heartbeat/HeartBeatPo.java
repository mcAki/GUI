package com.sys.volunteer.xunyuan.protocol.heartbeat;

import org.jboss.netty.buffer.ChannelBuffer;

import com.sys.volunteer.xunyuan.protocol.BasePo;

public class HeartBeatPo extends BasePo {

	public final static String cmd = "000119";

	String length = "0024";
	String businessType = "DCCZ";
	String msgCode = "000119";

	@Override
	public ChannelBuffer encodeVo(ChannelBuffer channelBuffer) throws Exception {
		channelBuffer.writeBytes(length.getBytes());
		channelBuffer.writeBytes(getSerialId().getBytes());
		channelBuffer.writeBytes(businessType.getBytes());
		channelBuffer.writeBytes(msgCode.getBytes());
		return channelBuffer;
	}

	@Override
	public String getCommandId() {
		return cmd;
	}

	@Override
	public boolean validatePoLength() throws Exception {
		int length = this.length.length() + getSerialId().length()
				+ businessType.length() + msgCode.length();
		return Integer.valueOf(this.length).equals(length);

	}
}
