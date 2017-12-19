package com.sys.volunteer.xunyuan.protocol;

import java.io.Serializable;

import org.jboss.netty.buffer.ChannelBuffer;

import com.sys.volunteer.xunyuan.util.GenPackHeader;


/**
 * 基础网络VO对象
 * 
 * @author Seraph
 * 
 */
public abstract class BasePo implements Serializable, IPackageObject {

	private static final long serialVersionUID = -2503482652061267695L;
	
	
	
	/**
	 * 报文长度
	 */
	private String length;
	
	/**
	 * 报文序列号(包头识别)
	 */
	private String serialId;
	
	/**
	 * 业务类型
	 */
	private String businessType;
	
	/**
	 * 消息码
	 */
	private String msgCode;
	
	/**
	 * 响应码
	 */
	private String respCode;
	
	public abstract ChannelBuffer encodeVo(ChannelBuffer channelBuffer) throws Exception;
	
	public abstract boolean validatePoLength() throws Exception;
	
	
	@Override
	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	@Override
	public String getSerialId() {
		serialId = GenPackHeader.genHeader();
		System.out.println(serialId);
		return serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	@Override
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	@Override
	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	@Override
	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	
	

}
