package com.sys.volunteer.xunyuan.protocol.query;

import org.jboss.netty.buffer.ChannelBuffer;

import com.sys.volunteer.xunyuan.protocol.BasePo;

public class QueryBalancePo_Rp extends BasePo{
	
	public final static String cmd = "800008";
	public final static String reflectClassName = "QueryBalancePo_Rp";

	private String balance;
	private String storeSeq;
	private String payType;
	private String cName;

	public QueryBalancePo_Rp decode(ChannelBuffer channelBuffer, String length,
			String serialId,String businessType, String msgCode) throws Exception {
		setLength(length);
		setSerialId(serialId);
		setBusinessType(businessType);
		setMsgCode(msgCode);
		setRespCode(new String(channelBuffer.readBytes(4).array()));
		setBalance(new String(channelBuffer.readBytes(16).array()));
		setStoreSeq(new String(channelBuffer.readBytes(20).array()));
		setPayType(new String(channelBuffer.readBytes(1).array()));
		setcName((new String(channelBuffer.readBytes(100).array())));
		return this;
	}
	
	@Override
	public ChannelBuffer encodeVo(ChannelBuffer channelBuffer) throws Exception {
		return null;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getStoreSeq() {
		return storeSeq;
	}

	public void setStoreSeq(String storeSeq) {
		this.storeSeq = storeSeq;
	}
	
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	@Override
	public String getCommandId() {
		return cmd;
	}

	@Override
	public boolean validatePoLength() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
