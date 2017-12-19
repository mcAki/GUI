package com.sys.volunteer.xunyuan.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 自定义socket包
 * 
 * @author Seraph.Yang
 * 
 */
public class PackageUtil {

	/**
	 * 转流:Short类型
	 * 
	 * @param stream
	 * @param value
	 *            @
	 */
	public static void EncodeShort(OutputStream stream, short value)
			throws IOException {
		byte[] buf = new byte[2];
		buf[0] = (byte) ((value & 0xff00) >> 8);
		buf[1] = (byte) (value & 0xff);
		stream.write(buf);
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
		return b.equals((byte) 1);
	}

	/**
	 * 
	 * @param inputStream
	 * @return
	 */
	public static Boolean DecodeBoolean(InputStream inputStream) {
		Byte b = DecodeByte(inputStream);
		return b.equals((byte) 1);
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
	 * 
	 * @param inputStream
	 * @return
	 */
	public static Byte DecodeByte(InputStream inputStream) {
		byte[] byteArr = new byte[1];
		try {
			inputStream.read(byteArr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return byteArr[0];
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
	 * 
	 * @param buf
	 * @param pos
	 * @return
	 */
	public static Float DecodeFloat(InputStream inputStream) {
		int x = DecodeInteger(inputStream);
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
	 * 
	 * @param buf
	 * @param pos
	 * @return
	 */
	public static Double DecodeDouble(InputStream inputStream) {
		long x = DecodeLong(inputStream);
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
	public static Integer DecodeInteger(InputStream inputStream) {
		int pos = 0;
		byte[] buf = new byte[4];
		try {
			inputStream.read(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int value = 0;
		value = ((buf[pos++] & 0xff) << 24) | ((buf[pos++] & 0xff) << 16)
				| ((buf[pos++] & 0xff) << 8) | (buf[pos++] & 0xff);

		return value;
	}

	
	public static Short DecodeShort(InputStream inputStream) {
		int pos = 0;
		byte[] buf = new byte[2];
		try {
			inputStream.read(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int value = 0;
		value = ((buf[pos++] & 0xff) << 8) | (buf[pos++] & 0xff);
		
		return Short.parseShort(value+"");
	}
	
	/**
	 * 
	 * @param inputStream
	 * @param len
	 */
	public static void skipInputStream(InputStream inputStream,int len) {
		byte[] buf = new byte[len];
		try {
			inputStream.read(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public static Long DecodeLong(InputStream inputStream) {
		int pos = 0;
		byte[] buf = new byte[8];
		try {
			inputStream.read(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		if (value != null && value.length() > 0) {
			int len = value.getBytes("UTF-8").length;
			byte[] buf;
			if (fixedLength > 0) {
				if (len > fixedLength) {
					len = fixedLength;
				}
				EncodeInteger(stream, len);
				buf = new byte[len];
				byte[] content = value.getBytes("UTF-8");
				System.arraycopy(content, 0, buf, 0, len);
			} else {
				// 不定长字符串，用4个字节记录字符串长度
				// 写字符串长度
				EncodeInteger(stream, len);
				buf = value.getBytes("UTF-8");
			}
			stream.write(buf);
		} else {
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

	/**
	 * 
	 * @param inputStream
	 * @return
	 */
	public static String DecodeString(InputStream inputStream) {
		return DecodeString(inputStream, -1);
	}

	/**
	 * 
	 * @param inputStream
	 * @param fixedLength
	 * @return
	 */
	public static String DecodeString(InputStream inputStream, int fixedLength) {
		int len;
		// 先从流中获取长度
		len = DecodeInteger(inputStream);
		if (fixedLength > 0) {
			// 定长
			if (fixedLength > len) {
				len = fixedLength;
			}
		}
		byte[] content = new byte[len];
		try {
			inputStream.read(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(content);
	}
}
