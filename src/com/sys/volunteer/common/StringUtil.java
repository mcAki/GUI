package com.sys.volunteer.common;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class StringUtil {

	public static boolean isEmpty(String str) {
		boolean result = false;
		if (str == null || str.equals(""))
			result = true;
		return result;
	}

	public static int parseInt(String strInt, int defaultValue) {
		int res = 0;
		try {
			res = Integer.parseInt(strInt);
		} catch (Exception e) {
			res = defaultValue;
		}
		return res;
	}

	/**
	 * 数字不足位数左补0
	 * 
	 * @param str
	 * @param strLength
	 */
	public static String addZeroForNum(String str, int strLength) {
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append("0").append(str);// 左补0
				// sb.append(str).append("0");//右补0
				str = sb.toString();
				strLen = str.length();
			}
		}

		return str;
	}

	/**
	 * 数字不足位数左补空格
	 * 
	 * @param str
	 * @param strLength
	 */
	public static String addSpaceForNum(String str, int strLength) {
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append(" ").append(str);// 左补0
				// sb.append(str).append(" ");//右补0
				str = sb.toString();
				strLen = str.length();
			}
		}

		return str;
	}

	/**
	 * 列表转换成字符串
	 * 
	 * @param list
	 * @param symbol
	 * @return
	 */
	public static String ListToStr(List list, String symbol) {
		String resultStr = "";
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (i == 0) {
					resultStr = resultStr + list.get(i);
				} else {
					resultStr = resultStr + symbol + list.get(i);
				}
			}
		}
		return resultStr;
	}

	/**
	 * 字符串转换成列表
	 * 
	 * @param str
	 * @param symbol
	 * @return
	 */
	public static List<String> strToList(String str, String symbol) {
		List<String> list = new ArrayList<String>();
		String[] strs = str.split(symbol);
		if (strs != null && strs.length > 0) {
			for (int i = 0; i < strs.length; i++) {
				list.add(strs[i]);
			}
		}
		return list;
	}

	/**
	 * 字符串编码转换的实现方法
	 * 
	 * @param str
	 *            待转换编码的字符串
	 * @param newCharset
	 *            目标编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String changeCharset(String str, String newCharset)
			throws UnsupportedEncodingException {
		if (str != null) {
			// 用默认字符编码解码字符串。
			byte[] bs = str.getBytes();
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	/**
	 * 字符串编码转换的实现方法
	 * 
	 * @param str
	 *            待转换编码的字符串
	 * @param oldCharset
	 *            原编码
	 * @param newCharset
	 *            目标编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String changeCharset(String str, String oldCharset,
			String newCharset) throws UnsupportedEncodingException {
		if (str != null) {
			// 用旧的字符编码解码字符串。解码可能会出现异常。
			byte[] bs = str.getBytes(oldCharset);
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	/**
	 * 使用java正则表达式去掉多余的.与0
	 * 
	 * @param s
	 * @return
	 */
	public static String subZeroAndDot(String s) {
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");// 去掉多余的0
			s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return s;
	}

}
