package com.sys.volunteer.xunyuan.protocol.login;

import org.jboss.netty.buffer.ChannelBuffer;

import com.sys.volunteer.common.MD5;
import com.sys.volunteer.xunyuan.netty.core.XunyuanKernel;
import com.sys.volunteer.xunyuan.protocol.BasePo;

public class LoginPo extends BasePo {

	public final static String cmd = "000001";

	String length = "0076";
	String businessType = "DCCZ";
	String msgCode = "000001";
	String spId = "00000000";
	String comId = XunyuanKernel.XUNYUANUSERID;
	MD5 md5 = MD5.getiInstance();
	String pwd = md5.getMD5ofStr(XunyuanKernel.LOGINPWD);
	String protocolVersion = "0010";

	@Override
	public ChannelBuffer encodeVo(ChannelBuffer channelBuffer) throws Exception {
		channelBuffer.writeBytes(length.getBytes());
		channelBuffer.writeBytes(getSerialId().getBytes());
		channelBuffer.writeBytes(businessType.getBytes());
		channelBuffer.writeBytes(msgCode.getBytes());
		channelBuffer.writeBytes(spId.getBytes());
		channelBuffer.writeBytes(comId.getBytes());
		channelBuffer.writeBytes(pwd.getBytes());
		channelBuffer.writeBytes(protocolVersion.getBytes());
		return channelBuffer;
	}

	@Override
	public String getCommandId() {
		return cmd;
	}

	@Override
	public boolean validatePoLength() throws Exception {
		int length = this.length.length() + getSerialId().length()
				+ businessType.length() + msgCode.length() + spId.length()
				+ comId.length() + pwd.length() + protocolVersion.length();
		return Integer.valueOf(this.length).equals(length);

	}
}
