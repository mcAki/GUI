package com.sys.volunteer.xunyuan.protocol.reverse;

import org.jboss.netty.buffer.ChannelBuffer;

import com.sys.volunteer.common.MD5;
import com.sys.volunteer.common.StringUtil;
import com.sys.volunteer.xunyuan.netty.core.XunyuanKernel;
import com.sys.volunteer.xunyuan.protocol.BasePo;

public class ReversePo extends BasePo {

	public final static String cmd = "000004";

	String length = "0132";
	String businessType = "DCCZ";
	String msgCode = "000004";
	MD5 md5 = MD5.getiInstance();
	String tradePwd = md5.getMD5ofStr(XunyuanKernel.TRADPWD);
	String amount;
	String mobile;
	String reverseStoreSeq;
	String storeSeq;
	String corpMobile = XunyuanKernel.CORPMOBILE;

	@Override
	public ChannelBuffer encodeVo(ChannelBuffer channelBuffer) throws Exception {
		channelBuffer.writeBytes(length.getBytes());
		channelBuffer.writeBytes(getSerialId().getBytes());
		channelBuffer.writeBytes(businessType.getBytes());
		channelBuffer.writeBytes(msgCode.getBytes());
		channelBuffer.writeBytes(tradePwd.getBytes());
		channelBuffer.writeBytes(amount.getBytes());
		if (mobile.length() < 13) {
			mobile = StringUtil.addSpaceForNum(mobile, 13);
		}
		channelBuffer.writeBytes(mobile.getBytes());
		channelBuffer.writeBytes(reverseStoreSeq.getBytes());
		channelBuffer.writeBytes(storeSeq.getBytes());
		channelBuffer.writeBytes(corpMobile.getBytes());
		return channelBuffer;
	}

	public ReversePo(String amount, String mobile, String reverseStoreSeq,
			String storeSeq) {
		this.amount = amount;
		this.mobile = mobile;
		this.reverseStoreSeq = reverseStoreSeq;
		this.storeSeq = storeSeq;
	}
	
	@Override
	public boolean validatePoLength() throws Exception {
		if (mobile.length() < 13) {
			mobile = StringUtil.addSpaceForNum(mobile, 13);
		}
		int length = this.length.length() + getSerialId().length()
				+ businessType.length() + msgCode.length() + tradePwd.length()
				+ reverseStoreSeq.length() + amount.length() + mobile.length()
				+ storeSeq.length() + corpMobile.length();
		return Integer.valueOf(this.length).equals(length);
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStoreSeq() {
		return storeSeq;
	}

	public void setStoreSeq(String storeSeq) {
		this.storeSeq = storeSeq;
	}

	public String getReverseStoreSeq() {
		return reverseStoreSeq;
	}

	public void setReverseStoreSeq(String reverseStoreSeq) {
		this.reverseStoreSeq = reverseStoreSeq;
	}

	@Override
	public String getCommandId() {
		return cmd;
	}


}
