package com.sys.volunteer.common;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class DesUtil {

	public static final String keydes = "1234567890123456";

	/**
	 * des加密
	 * 
	 * @param key
	 * @param text
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] endes(byte[] key, byte[] text) {
		try {
			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "DES"));
			return cipher.doFinal(fillDesData(text));
		} catch (Exception e) {
			throw new SecurityException("用Des加密时出错." + e);
		}
	}

	public static byte[] des(byte[] key, byte[] source) throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance("DES");
		kg.init(56);
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		return cipher.doFinal(source);
	}

	/**
	 * des解密码
	 * 
	 * @param key
	 * @param text
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] dedes(byte[] key, byte[] text) {
		try {
			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "DES"));
			return cipher.doFinal(fillDesData(text));
		} catch (Exception e) {
			throw new SecurityException("用Des解密时出错." + e);
		}
	}

	/**
	 * 数据填充为des需要的8的倍数，不足在后面填充0x00
	 * 
	 * @param data
	 * @return byte[]
	 */
	private static byte[] fillDesData(byte[] data) {
		int nn = 8 - data.length % 8;
		if (nn != 8) {
			byte[] tbs = new byte[nn];
			for (int i = 0; i < nn; i++)
				tbs[i] = 0x00;
			data = ByteUtil.union(data, tbs);
		}
		return data;
	}

	/**
	 * 3des加密 <br>
	 * 加密算法： <br>
	 * endes(KEY_left,dedes(KEY_right,endes(KEY_left,DATA))) <br>
	 * 
	 * @param key
	 *            必须16字节
	 * @param data
	 * @return byte[]
	 */
	public static byte[] en3des(byte[] key, byte[] data) {
		// 检查密钥是否为16字节
		if (key.length != 16)
			throw new SecurityException("用3Des加密的密钥匙要求16字节.");
		try {
			data = endes(ByteUtil.sub(key, 0, 8), data);
			data = dedes(ByteUtil.sub(key, 8, 8), data);
			data = endes(ByteUtil.sub(key, 0, 8), data);
		} catch (Exception e) {
			throw new SecurityException("用3Des加密时出错." + e);
		}
		return data;
	}

	/**
	 * 3des解密 <br>
	 * 加密算法： <br>
	 * dedes(KEY_left,endes(KEY_right,dedes(KEY_left,DATA))) <br>
	 * 
	 * @param key
	 *            必须16字节
	 * @param data
	 * @return byte[]
	 */
	public static byte[] de3des(byte[] key, byte[] data) {
		// 检查密钥是否为16字节
		if (key.length != 16)
			throw new SecurityException("用3Des加密的密钥匙要求16字节.");
		// 3des
		try {
			data = dedes(ByteUtil.sub(key, 0, 8), data);
			data = endes(ByteUtil.sub(key, 8, 8), data);
			data = dedes(ByteUtil.sub(key, 0, 8), data);
		} catch (Exception e) {
			throw new SecurityException("用3Des解密时出错." + e);
		}
		return data;
	}

	/**
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @param arrB
	 *            需要转换的byte数组
	 * @return 转换后的字符串
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 */
	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 * 
	 * @param strIn
	 *            需要转换的字符串
	 * @return 转换后的byte数组
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 * @author <a href="mailto:leo841001@163.com">LiGuoQing</a>
	 */
	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	// 转化字符串为十六进制编码
	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;// 0x表示十六进制
	}

	// 转换十六进制编码为字符串
	public static String toStringHex(String s) {
		if ("0x".equals(s.substring(0, 2))) {
			s = s.substring(2);
		}
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
						i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			s = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	/**
	 * des加密(重新封装,可用)
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String desEncode(String value) throws Exception {
		// 将原始字节数组转换为8位
		byte[] arrB = new byte[8];
		for (int i = 0; i < hexStr2ByteArr(keydes).length && i < arrB.length; i++) {
			arrB[i] = hexStr2ByteArr(keydes)[i];
		}
		// value = 123456 encoder = [64][6b][66][86][04][ff][13][e3]
		// String value = "1111111";
		// String value = "12345678";
		// String value = "22222222";
		byte[] encoder = DesUtil.endes(arrB, value.getBytes());
		System.out.println("desEncode:" + byteArr2HexStr(encoder));
		return byteArr2HexStr(encoder);
	}

	/**
	 * des解密(重新封装,可用)
	 * 
	 * @param encoder
	 * @return
	 * @throws Exception
	 */
	public static String desDecode(String encoder) throws Exception {
		byte[] arrB = new byte[8];
		for (int i = 0; i < hexStr2ByteArr(keydes).length && i < arrB.length; i++) {
			arrB[i] = hexStr2ByteArr(keydes)[i];
		}
		byte[] arr = hexStr2ByteArr(encoder);
		byte[] decoderByte = DesUtil.dedes(arrB, arr);
		String decoder = new String(decoderByte);
		System.out.println("desDecode:" + decoder.trim());
		return decoder;
	}

	/**
	 * 3des加密(重新封装,可用)
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String des3Encode(String value) throws Exception {
		// 将原始字节数组转换为8位
		byte[] arrB3Des = new byte[16];
		for (int i = 0; i < hexStr2ByteArr(keydes).length
				&& i < arrB3Des.length; i++) {
			arrB3Des[i] = hexStr2ByteArr(keydes)[i];
		}
		// value = 123456 encoder = [64][6b][66][86][04][ff][13][e3]
		// String value = "1111111";
		// String value = "12345678";
		// String value = "22222222";
		byte[] encoder = DesUtil.en3des(arrB3Des, value.getBytes());
		System.out.println("3desEncode:" + byteArr2HexStr(encoder));
		return byteArr2HexStr(encoder);
	}

	/**
	 * 3des解密(重新封装,可用)
	 * 
	 * @param encoder
	 * @return
	 * @throws Exception
	 */
	public static String des3Decode(String encoder) throws Exception {
		byte[] arrB3Des = new byte[16];
		for (int i = 0; i < hexStr2ByteArr(keydes).length
				&& i < arrB3Des.length; i++) {
			arrB3Des[i] = hexStr2ByteArr(keydes)[i];
		}
		byte[] arr = hexStr2ByteArr(encoder);
		byte[] decoderByte = DesUtil.de3des(arrB3Des, arr);
		String decoder = new String(decoderByte);
		System.out.println("3desDecode:" + decoder.trim());
		return decoder;
	}

	/**
	 * des加密(重新封装,可用)
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String desEncode(String key, String value) throws Exception {
		// 将原始字节数组转换为8位
		byte[] arrB = new byte[8];
		for (int i = 0; i < hexStr2ByteArr(key).length && i < arrB.length; i++) {
			arrB[i] = hexStr2ByteArr(key)[i];
		}
		// value = 123456 encoder = [64][6b][66][86][04][ff][13][e3]
		// String value = "1111111";
		// String value = "12345678";
		// String value = "22222222";
		byte[] encoder = DesUtil.endes(arrB, value.getBytes());
		System.out.println("desEncode:" + byteArr2HexStr(encoder));
		return byteArr2HexStr(encoder);
	}
	
	/**
	 * des加密(重新封装,可用)已补位0xff
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String desEncodeFill(String key, String value) throws Exception {
		// 将原始字节数组转换为8位
		byte[] arrB = new byte[8];
		for (int i = 0; i < hexStr2ByteArr(key).length && i < arrB.length; i++) {
			arrB[i] = hexStr2ByteArr(key)[i];
		}
		// value = 123456 encoder = [64][6b][66][86][04][ff][13][e3]
		// String value = "1111111";
		// String value = "12345678";
		// String value = "22222222";
		int length = value.getBytes().length;
		int x = length % 8;
		int addLen = 0;
		if (x != 0) {
			addLen = 8 - length % 8;
		}
		byte[] data = new byte[length + addLen];
		System.arraycopy(value.getBytes(), 0, data, 0, length);
		for(int i = length;i<data.length;i++){
			data[i] = (byte) 0xff;
		}
		byte[] encoder = DesUtil.endes(arrB, data);
		System.out.println("desEncode:" + byteArr2HexStr(encoder));
		return byteArr2HexStr(encoder);
	}


	/**
	 * des解密(重新封装,可用)
	 * 
	 * @param encoder
	 * @return
	 * @throws Exception
	 */
	public static String desDecode(String key, String encoder) throws Exception {
		byte[] arrB = new byte[8];
		for (int i = 0; i < hexStr2ByteArr(key).length && i < arrB.length; i++) {
			arrB[i] = hexStr2ByteArr(key)[i];
		}
		byte[] arr = hexStr2ByteArr(encoder);
		byte[] decoderByte = DesUtil.dedes(arrB, arr);
		String decoder = new String(decoderByte);
		System.out.println("desDecode:" + decoder.trim());
		return decoder;
	}

	/**
	 * 3des加密(重新封装,可用)
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String des3Encode(String key, String value) throws Exception {
		// 将原始字节数组转换为8位
		byte[] arrB3Des = new byte[16];
		for (int i = 0; i < hexStr2ByteArr(key).length && i < arrB3Des.length; i++) {
			arrB3Des[i] = hexStr2ByteArr(key)[i];
		}
		// value = 123456 encoder = [64][6b][66][86][04][ff][13][e3]
		// String value = "1111111";
		// String value = "12345678";
		// String value = "22222222";
		byte[] encoder = DesUtil.en3des(arrB3Des, value.getBytes());
		System.out.println("3desEncode:" + byteArr2HexStr(encoder));
		return byteArr2HexStr(encoder);
	}

	/**
	 * 3des解密(重新封装,可用)
	 * 
	 * @param encoder
	 * @return
	 * @throws Exception
	 */
	public static String des3Decode(String key, String encoder)
			throws Exception {
		byte[] arrB3Des = new byte[16];
		for (int i = 0; i < hexStr2ByteArr(key).length && i < arrB3Des.length; i++) {
			arrB3Des[i] = hexStr2ByteArr(key)[i];
		}
		byte[] arr = hexStr2ByteArr(encoder);
		byte[] decoderByte = DesUtil.de3des(arrB3Des, arr);
		String decoder = new String(decoderByte);
		System.out.println("3desDecode:" + decoder.trim());
		return decoder;
	}

	public static byte[] IV = new byte[8];

	public static byte byteXOR(byte src, byte src1) {
		return (byte) ((src & 0xFF) ^ (src1 & 0xFF));
	}

	public static byte[] bytesXOR(byte[] src, byte[] src1) {
		int length = src.length;
		if (length != src1.length) {
			return null;
		}
		byte[] result = new byte[length];
		for (int i = 0; i < length; i++) {
			result[i] = byteXOR(src[i], src1[i]);
		}
		return result;
	}

	/**
	 * mac计算,数据不为8的倍数，需要补0，将数据8个字节进行异或，再将异或的结果与下一个8个字节异或，一直到最后，将异或后的数据进行DES计算
	 * 
	 * @param key
	 * @param Input
	 * @return
	 * @throws Exception 
	 */
	public static byte[] clacMac(byte[] key, byte[] Input) throws Exception {
		int length = Input.length;
		int x = length % 8;
		int addLen = 0;
		if (x != 0) {
			addLen = 8 - length % 8;
		}
		int pos = 0;
		byte[] data = new byte[length + addLen];
		System.arraycopy(Input, 0, data, 0, length);
		byte[] oper1 = new byte[8];
		System.arraycopy(data, pos, oper1, 0, 8);
		pos += 8;
		for (int i = 1; i < data.length / 8; i++) {
			byte[] oper2 = new byte[8];
			System.arraycopy(data, pos, oper2, 0, 8);
			byte[] t = bytesXOR(oper1, oper2);
			oper1 = t;
			pos += 8;
		}
		byte[] buff = endes(key, oper1);
		//展成hexstr
		String hexstr = byteArr2HexStr(buff);
		System.out.println(hexstr);
		// 取8个长度字节
		byte[] retBuf = new byte[8];
		System.arraycopy(hexstr.getBytes(), 0, retBuf, 0, 8);
		return retBuf;
	}
	
	public static String calcMac(String key, String value) throws Exception{
		byte[] arrB = new byte[8];
		for (int i = 0; i < hexStr2ByteArr(key).length && i < arrB.length; i++) {
			arrB[i] = hexStr2ByteArr(key)[i];
		}
		byte[] arr = value.getBytes();
		byte[] macByte = clacMac(arrB,arr);
		//System.out.println(new String(macByte).trim());
		return new String(macByte);
	}
}
