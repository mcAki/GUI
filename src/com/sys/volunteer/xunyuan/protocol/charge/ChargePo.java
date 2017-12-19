package com.sys.volunteer.xunyuan.protocol.charge;

import org.jboss.netty.buffer.ChannelBuffer;

import com.sys.volunteer.common.MD5;
import com.sys.volunteer.common.StringUtil;
import com.sys.volunteer.xunyuan.netty.core.XunyuanKernel;
import com.sys.volunteer.xunyuan.protocol.BasePo;

public class ChargePo extends BasePo {

	public final static String cmd = "000005";

	String length = "0118";
	String businessType = "DCCZ";
	String msgCode = "000005";
	MD5 md5 = MD5.getiInstance();
	String tradePwd = md5.getMD5ofStr(XunyuanKernel.TRADPWD).toLowerCase();
	String tradeType;
	String amount;
	String mobile;
	String storeSeq;
	String corpMobile = XunyuanKernel.CORPMOBILE;

	@Override
	public ChannelBuffer encodeVo(ChannelBuffer channelBuffer) throws Exception {
		channelBuffer.writeBytes(length.getBytes());
		channelBuffer.writeBytes(getSerialId().getBytes());
		channelBuffer.writeBytes(businessType.getBytes());
		channelBuffer.writeBytes(msgCode.getBytes());
		channelBuffer.writeBytes(tradePwd.getBytes());
		channelBuffer.writeBytes(tradeType.getBytes());
		channelBuffer.writeBytes(amount.getBytes());
		if (mobile.length() < 13) {
			mobile = StringUtil.addSpaceForNum(mobile, 13);
		}
		channelBuffer.writeBytes(mobile.getBytes());
		channelBuffer.writeBytes(storeSeq.getBytes());
		channelBuffer.writeBytes(corpMobile.getBytes());
		return channelBuffer;
	}

	public String getAmount() {
		return amount;
	}

	public ChargePo(String tradeType, String amount, String mobile,
			String storeSeq) {
		this.tradeType = tradeType;
		this.amount = amount;
		this.mobile = mobile;
		this.storeSeq = storeSeq;
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

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	@Override
	public String getCommandId() {
		return cmd;
	}

	@Override
	public boolean validatePoLength() throws Exception {
		if (mobile.length() < 13) {
			mobile = StringUtil.addSpaceForNum(mobile, 13);
		}
		int length = this.length.length() + getSerialId().length()
				+ businessType.length() + msgCode.length() + tradePwd.length()
				+ tradeType.length() + amount.length() + mobile.length()
				+ storeSeq.length() + corpMobile.length();
		return Integer.valueOf(this.length).equals(length);
	}

}
