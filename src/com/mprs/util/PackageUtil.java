package com.mprs.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.jboss.netty.buffer.ChannelBuffer;

import com.mprs.core.Context;
import com.mprs.protocol.BasePo;
import com.mprs.protocol.FieldType;
import com.mprs.protocol.ITraceGenerator;
import com.mprs.protocol.IVerification;
import com.mprs.protocol.ProtocolContainer;
import com.mprs.protocol.GenerateProtocolPackage.ProtocolField;

/**
 * 自定义socket包
 * @author Seraph.Yang
 *
 */
public class PackageUtil {
	/**
	 * 公共执行结果：未知
	 */
	public static final int RES_GENERAL_UNKNOW = 0x0;

	/**
	 * 公共执行结果：成功
	 */
	public static final int RES_GENERAL_SUCCESSED = 0x1;

	/**
	 * 公共执行结果：失败
	 */
	public static final int RES_GENERAL_FAILED = 0x2;

	/**
	 * 公共执行结果：没权限
	 */
	public static final int RES_GENERAL_UNKNOWERROR = 0x10000000;

	/**
	 * 公共执行结果：没权限
	 */
	public static final int RES_GENERAL_IMPERMISSIBLITY = 0x70000000;

	/**
	 * 公共执行结果：停止错误
	 */
	public static final int RES_GENERAL_STOP = 0x80000000;

	public static final int msgType_AMF = 1;// 消息类型属AMF格式

	public static final int msgType_Ages_Protocol = 2;// 消息类型是我们自定义的协议格式
	/**
	 * 包头标识
	 */
	private static final byte[] headPfx = new byte[] { (byte) 0xFF,
			(byte) 0xEE, (byte) 'p', (byte) 'v', (byte) 'o', (byte) '1',
			(byte) '0', (byte) '0' };

	/**
	 * 包结束标记
	 */
	private static final byte[] etx = new byte[] { (byte) 0xFF, (byte) 0xEE };

	/**
	 * 包头大小:注意改变包头时候必须同步修改
	 */
	@SuppressWarnings("unused")
	private static final int headPfxSize = 8;

	/**
	 * 包结束标记大小:注意改变包头时候必须同步修改
	 */
	@SuppressWarnings("unused")
	private static final int headEtxSize = 2;

	@SuppressWarnings("unused")
	private static final int verifyCodeLength = 8;

	/**
	 * 跟踪代码
	 */
	@SuppressWarnings("unused")
	private int traceNumber;

	/**
	 * 头部
	 */
	protected byte[] headerBuf;

	/**
	 * 头部大小
	 */
	@SuppressWarnings("unused")
	private static final int headerSize = 16;

	/**
	 * 长整型最长size
	 */
	@SuppressWarnings("unused")
	private static final int TYPE_LONG_MAX_SIZE = 20;

	/**
	 * 获取协议子代码
	 * 
	 * @param protocol
	 * @return
	 */
	public static int getProtocolSubCode(int protocol) {
		return Math.abs(protocol) & 0x0000FFFF;
	}

	/**
	 * 获取协议类代码
	 * 
	 * @param protocol
	 * @return
	 */
	public static int getProtocolPfxCode(int protocol) {
		// int x = ( Math.abs(protocol) & 0x0FFF0000);
		// x = x >> 16;
		// return x;
		return (Math.abs(protocol) & 0x0FFF0000) >> 16;
	}

	/**
	 * 打包
	 * 
	 * @param vo
	 * @param traceGenerator
	 * @param verification
	 * @return
	 */
	public static byte[] write(BasePo vo, ITraceGenerator traceGenerator,
			IVerification verification) {
		ByteArrayOutputStream retStream = new ByteArrayOutputStream();// 最终输出流

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		byte[] data = null;
		try {
			data = encodeVo(vo);
			// 包头标识
			stream.write(headPfx);
			// 包类型
			EncodeInteger(stream, vo.getCommandId());
			// 包序号
			EncodeInteger(stream, traceGenerator.nextTrace());

			// 获取验证码
			ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
			dataStream.write(data);
			byte[] GenerateVerifyCode = verification.verifyCodeByByte(
					dataStream, verification);
			byte[] writeVerifyCode = new byte[8];
			for (int i = 0; i < 8; i++) {
				if (i < GenerateVerifyCode.length) {
					writeVerifyCode[i] = GenerateVerifyCode[i];
				}
			}
			// 包验证
			stream.write(writeVerifyCode);
			// 写内容数据
			stream.write(data);
			// 写结束符
			stream.write(etx);

			int dataLength = stream.toByteArray().length;
			EncodeInteger(retStream, dataLength);
			retStream.write(stream.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return retStream.toByteArray();
	}

	/**
	 * 供给netty调用，把数据晒到ChannelBuffer里
	 * 
	 * @param buffer
	 * @param vo
	 * @param traceGenerator
	 * @param verification
	 */
	public static void write(ChannelBuffer buffer, BasePo vo,
			ITraceGenerator traceGenerator, IVerification verification) {
		byte[] data = write(vo, traceGenerator, verification);
		buffer.writeBytes(data);
	}

	/**
	 * 把VO中要映射的属性根据配置文件写到输出流
	 * 
	 * @param vo
	 * @return
	 * @throws IOException
	 */
	public static byte[] encodeVo(BasePo vo) throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		EncodeInteger(stream, vo.getCommandId());
		Map<Integer, ProtocolContainer> parseMap = Context.getInstance()
				.getProtocolMap();
		ProtocolContainer parseContainer = parseMap.get(vo.getCommandId());
		// 解析配置文件的每个字段
		List<ProtocolField> parseList = parseContainer.getParsetList();
		for (ProtocolField parseField : parseList) {
			if (parseField.getFieldType() == FieldType.eStr) {
				String value = (String) getValueFromObj(vo, parseField);
				EncodeString(stream, value);
			} else if (parseField.getFieldType() == FieldType.eFixedStr) {
				String value = (String) getValueFromObj(vo, parseField);
				EncodeString(stream, value, parseField.getDataLength());
			} else if (parseField.getFieldType() == FieldType.eInt) {
				Integer value = (Integer) getValueFromObj(vo, parseField);
				EncodeInteger(stream, value);
			} else if (parseField.getFieldType() == FieldType.eFloat) {
				Float value = (Float) getValueFromObj(vo, parseField);
				EncodeFloat(stream, value);
			} else if (parseField.getFieldType() == FieldType.eDouble) {
				Double value = (Double) getValueFromObj(vo, parseField);
				EncodeDouble(stream, value);
			} else if (parseField.getFieldType() == FieldType.eBoolean) {
				Boolean value = (Boolean) getValueFromObj(vo, parseField);
				EncodeBoolean(stream, value);
			} else if (parseField.getFieldType() == FieldType.eByte) {
				Byte value = (Byte) getValueFromObj(vo, parseField);
				EncodeByte(stream, value);
			}
		}
		return stream.toByteArray();
	}

	/**
	 * 解码,把数据包转换为VO
	 * 
	 * @param stream
	 *            @
	 */
	public static BasePo read(byte[] bytes) {
		Map<Integer, ProtocolContainer> parseMap = Context.getInstance()
				.getProtocolMap();
		int pos = 0;

		// 包类型
		Integer commandId = DecodeInteger(bytes, pos);
		pos += 4;

		ProtocolContainer parseContainer = parseMap.get(commandId);
		String requestClass = parseContainer.getNameSpaceClass();
		BasePo obj = null;
		try {
			obj = (BasePo) SysUtil.getInstance(requestClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		// 解析配置文件的每个字段
		List<ProtocolField> parseList = parseContainer.getParsetList();
		for (ProtocolField parseField : parseList) {
			if (parseField.getFieldType() == FieldType.eStr) {
				String value = DecodeString(bytes, pos);
				pos += 4;
				pos += value.length();
				setValueToObj(obj, parseField, value);
			} else if (parseField.getFieldType() == FieldType.eFixedStr) {
				int length = parseField.getDataLength();
				String value = DecodeString(bytes, pos, length);
				pos += 4;
				pos += length;
				setValueToObj(obj, parseField, value);
			} else if (parseField.getFieldType() == FieldType.eInt) {
				Integer value = DecodeInteger(bytes, pos);
				pos += 4;
				setValueToObj(obj, parseField, value);
			} else if (parseField.getFieldType() == FieldType.eFloat) {
				Float value = DecodeFloat(bytes, pos);
				pos += 4;
				setValueToObj(obj, parseField, value);
			}else if (parseField.getFieldType() == FieldType.eLong) {
				Long value = DecodeLong(bytes, pos);
				pos += 8;
				setValueToObj(obj, parseField, value);
			}else if (parseField.getFieldType() == FieldType.eDouble) {
				Double value = DecodeDouble(bytes, pos);
				pos += 8;
				setValueToObj(obj, parseField, value);
			}
		}
		return obj;
	}

	/**
	 * 检查包头
	 * 
	 * @param bytes
	 * @return
	 */
	public static boolean checkPackageHeader(byte[] bytes) {
		boolean flag = true;
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] != headPfx[i]) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**
	 * 利用暴力反射把值晒到对象之中
	 * 
	 * @param obj
	 * @param parseField
	 * @param value
	 */
	public static void setValueToObj(Object obj, ProtocolField parseField,
			Object value) {
		String targetFieldName = parseField.getFieldName();
		if (targetFieldName != null && targetFieldName.trim().length() > 0) {
			try {
				Field field = obj.getClass().getDeclaredField(targetFieldName);
				field.setAccessible(true);
				field.set(obj, value);
				field.setAccessible(false);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * 利用暴力反射获得属性值
	 * 
	 * @param obj
	 * @param parseField
	 * @return
	 */
	public static Object getValueFromObj(Object obj, ProtocolField parseField) {
		String targetFieldName = parseField.getFieldName();
		if (targetFieldName != null && targetFieldName.trim().length() > 0) {
			try {
				Field field = obj.getClass().getDeclaredField(targetFieldName);
				field.setAccessible(true);
				Object value = field.get(obj);
				field.setAccessible(false);
				return value;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 转流:Short类型
	 * 
	 * @param stream
	 * @param value
	 *            @
	 */
	public static void EncodeShort(OutputStream stream, short value)
			throws IOException {
		EncodeInteger(stream, value);
	}

	/**
	 * 解流:Short类型
	 * 
	 * @param stream
	 * @return @
	 */
	public static Short DecodeShort(byte[] buf, int pos) {
		return Short.parseShort(DecodeInteger(buf, pos).toString());
	}

	/**
	 * 布尔类型
	 * 
	 * @param stream
	 * @param value
	 *            @
	 */
	public static void EncodeBoolean(OutputStream stream, boolean value)
			throws IOException {
		if (value) {
			EncodeByte(stream, (byte) 1);
		} else {
			EncodeByte(stream, (byte) 0);
		}
	}

	/**
	 * 布尔类型
	 * 
	 * @param stream
	 * @return @
	 */
	public static Boolean DecodeBoolean(byte[] buf, int pos) {
		Byte b = DecodeByte(buf, pos);
		return b.equals((byte) 0);
	}

	/**
	 * 一个字节
	 * 
	 * @param stream
	 * @param value
	 *            @
	 */
	public static void EncodeByte(OutputStream stream, byte value)
			throws IOException {
		stream.write(value);
	}

	/**
	 * 一个字节
	 * 
	 * @param stream
	 * @return @
	 */
	public static Byte DecodeByte(byte[] buf, int pos) {
		return buf[pos];
	}

	/**
	 * 多个字节
	 * 
	 * @param buf
	 * @param pos
	 * @return
	 */
	public static byte[] DecodeBytes(byte[] buf, int pos, int len) {
		byte[] content = new byte[len];
		System.arraycopy(buf, pos, content, 0, len);
		return content;
	}

	/**
	 * 单精度浮点
	 * 
	 * @param stream
	 * @param value
	 *            @
	 */
	public static void EncodeFloat(OutputStream stream, float value)
			throws IOException {
		int x = Float.floatToRawIntBits(value);
		EncodeInteger(stream, x);
	}

	/**
	 * 单精度浮点
	 * 
	 * @param stream
	 * @return @
	 */
	public static Float DecodeFloat(byte[] buf, int pos) {
		int x = DecodeInteger(buf, pos);
		return Float.intBitsToFloat(x);
	}

	/**
	 * 双精度浮点
	 * 
	 * @param stream
	 * @param value
	 *            @
	 */
	public static void EncodeDouble(OutputStream stream, double value)
			throws IOException {
		long x = Double.doubleToRawLongBits(value);
		EncodeLong(stream, x);
	}

	/**
	 * 双精度浮点
	 * 
	 * @param stream
	 * @return @
	 */
	public static Double DecodeDouble(byte[] buf, int pos) {
		long x = DecodeLong(buf, pos);
		return Double.longBitsToDouble(x);
	}

	/**
	 * 整形
	 * 
	 * @param stream
	 * @param value
	 *            @
	 */
	public static void EncodeInteger(OutputStream stream, int value)
			throws IOException {
		byte[] buf = new byte[4];
		buf[0] = (byte) ((value & 0xff000000) >> 24);
		buf[1] = (byte) ((value & 0xff0000) >> 16);
		buf[2] = (byte) ((value & 0xff00) >> 8);
		buf[3] = (byte) (value & 0xff);
		stream.write(buf);
	}

	/**
	 * 整形
	 * 
	 * @param stream
	 * @param value
	 *            @
	 */
	public static byte[] EncodeInteger(int value) throws IOException {
		byte[] stream = new byte[4];
		stream[0] = (byte) ((value & 0xff000000) >> 24);
		stream[1] = (byte) ((value & 0xff0000) >> 16);
		stream[2] = (byte) ((value & 0xff00) >> 8);
		stream[3] = (byte) (value & 0xff);
		return stream;
	}

	/**
	 * 整形
	 * 
	 * @param stream
	 * @return @
	 */
	public static Integer DecodeInteger(byte[] buf, int pos) {
		int value = 0;

		value = ((buf[pos++] & 0xff) << 24) | ((buf[pos++] & 0xff) << 16)
				| ((buf[pos++] & 0xff) << 8) | (buf[pos++] & 0xff);

		return value;
	}

	public static void EncodeLong(OutputStream stream, long value)
			throws IOException {
		byte[] buf = new byte[8];
		buf[0] = (byte) ((value & 0xff00000000000000L) >> 56);
		buf[1] = (byte) ((value & 0xff000000000000L) >> 48);
		buf[2] = (byte) ((value & 0xff0000000000L) >> 40);
		buf[3] = (byte) ((value & 0xff00000000L) >> 32);
		buf[4] = (byte) ((value & 0xff000000L) >> 24);
		buf[5] = (byte) ((value & 0xff0000L) >> 16);
		buf[6] = (byte) ((value & 0xff00L) >> 8);
		buf[7] = (byte) (value & 0xffL);
		stream.write(buf);
		// String s = Long.toString(value);
		// EncodeString(stream, s, TYPE_LONG_MAX_SIZE);
	}

	public static Long DecodeLong(byte[] buf, int pos) {
		long value = 0;
		value |= (long) (buf[pos++] & 0xff) << 56;
		value |= (long) (buf[pos++] & 0xff) << 48;
		value |= (long) (buf[pos++] & 0xff) << 40;
		value |= (long) (buf[pos++] & 0xff) << 32;
		value |= (long) (buf[pos++] & 0xff) << 24;
		value |= (long) (buf[pos++] & 0xff) << 16;
		value |= (long) (buf[pos++] & 0xff) << 8;
		value |= (long) (buf[pos++] & 0xff);
		return value;
		// String s = DecodeString(stream, TYPE_LONG_MAX_SIZE);
		// return Long.parseLong(s.trim());
	}

	/**
	 * 字符串(自适应长度)
	 * 
	 * @param stream
	 * @param value
	 *            @
	 */
	public static void EncodeString(OutputStream stream, String value)
			throws IOException {
		EncodeString(stream, value, -1);
	}

	/**
	 * 字符串
	 * 
	 * @param stream
	 * @param value
	 * @param fixedLength
	 *            定长字符串长度，如果0则不定长度，自动适应 @
	 */
	public static void EncodeString(OutputStream stream, String value,
			int fixedLength) throws IOException {
		if(value!=null && value.length()>0){
			int len = value.length();
			byte[] buf;
			if (fixedLength > 0) {
				if (len > fixedLength) {
					len = fixedLength;
				}
				EncodeInteger(stream, len);
				buf = new byte[len];
				byte[] content = value.getBytes();
				System.arraycopy(content, 0, buf, 0, len);
			} else {
				// 不定长字符串，用4个字节记录字符串长度
				// 写字符串长度
				EncodeInteger(stream, len);
				buf = value.getBytes();
			}
			stream.write(buf);
		}else{
			EncodeInteger(stream, 0);
		}
		
	}

	/**
	 * 字符串(自适应长度)
	 * 
	 * @param stream
	 * @return @
	 */
	public static String DecodeString(byte[] buf, int pos) {
		return DecodeString(buf, pos, -1);
	}

	/**
	 * 字符串
	 * 
	 * @param stream
	 * @param fixedLength
	 *            定长字符串长度，如果0则不定长度，自动适应
	 * @return @
	 */
	public static String DecodeString(byte[] buf, int pos, int fixedLength) {
		int len;
		// 先从流中获取长度
		len = DecodeInteger(buf, pos);
		pos = pos + 4;
		if (fixedLength > 0) {
			// 定长
			if (fixedLength > len) {
				len = fixedLength;
			}

		}
		byte[] content = new byte[len];
		System.arraycopy(buf, pos, content, 0, len);
		return new String(content);
	}

	public static byte[] str2Bcd(String asc) {
		int len = asc.length();
		int mod = len % 2;

		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}

		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}

		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;

		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}
			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}
			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}

		return bbt;
	}

	/**
	 * 解包AMF对象
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object decodeAMFObj(byte[] bytes) {
//		SerializationContext serializationContext = new SerializationContext();
//		Amf3Input amf3Input = new Amf3Input(serializationContext);
//		amf3Input.setInputStream(new ByteArrayInputStream(bytes));
//		Object message;
//		try {
//			message = amf3Input.readObject();
//			return message;
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return null;
	}

	/**
	 * 打包AMF对象
	 * 
	 * @param vo
	 *            要被打包的对象
	 * @return 对象流
	 */
	public static byte[] encodeAMFObject(Object vo) {
//		ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
////		SerializationContext serializationContext = new SerializationContext();
////		// 序列化amf3对象
////		Amf3Output amfout = new Amf3Output(serializationContext);
//		amfout.setOutputStream(dataStream);
//		try {
//			amfout.writeObject(vo);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		// 将ByteArrayOutputStream流中转化成字节数组
//		byte[] messageBytes = dataStream.toByteArray();// amf3数据
//		return messageBytes;
		return null;
	}

	/**
	 * 打包成要发送到客户端的消息
	 * 
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static ByteArrayOutputStream writeMsgObject(BasePo obj)
			throws IOException {
//		ByteArrayOutputStream stream = new ByteArrayOutputStream();
//		byte[] messageBytes = null;
//		Map<Integer, ProtocolContainer> parseMap = Context.getInstance()
//		.getProtocolMap();
//		ProtocolContainer parseContainer = parseMap.get(obj.getCommandId());
//		 
//		if (parseContainer.getProtocolType() == msgType_AMF) {
//			messageBytes = PackageUtil.encodeAMFObject(obj);// 把对象打包AMF格式
//		} else if (parseContainer.getProtocolType() == msgType_Ages_Protocol) {// 根据我们的自定义协议打包
//			messageBytes = encodeVo(obj);
//		}
//		// 打印包内容
//		//SysUtil.debugData(messageBytes);
//		
//		// 长度缓冲
//		try {
//			byte[] lenBuf = PackageUtil.EncodeInteger(messageBytes.length);
//			// 包验证缓冲
//			byte[] verifyBuf = new byte[8];
//
//			// 写包头
//			stream.write(Context.getInstance().getProtocolSetting()
//					.getPackageHeaderBytes());
//			// 写消息类型
//			stream.write(PackageUtil.EncodeInteger(parseContainer.getProtocolType()));
//			// 写长度
//			stream.write(lenBuf);
//			// 写验证
//			stream.write(verifyBuf);
//
//			// 把包写入内存流
//			stream.write(messageBytes);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		return stream;
		return null;
	}

	/**
	 * 字节比对
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean compareByte(byte[] a, byte[] b) {
		if (a.length == b.length) {
			for (int i = 0; i < a.length; i++) {
				if (a[i] != b[i]) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
