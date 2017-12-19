package com.mprs.protocol;

import java.io.ByteArrayOutputStream;

/**
 * 校验֤
 * @author admin
 *
 */
public interface IVerification {

	/**
	 * 校验码
	 * @return 字节
	 */
	public byte[] verifyCodeByByte(ByteArrayOutputStream bodyStream, IVerification verification);
	
	
}
