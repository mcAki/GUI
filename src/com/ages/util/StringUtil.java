package com.ages.util;

public class StringUtil {

	public static boolean isEmpty(String str) {
		boolean result = false;
		if (str == null || str.equals(""))
			result = true;
		return result;
	}
	
	public static int parseInt(String strInt, int defaultValue){
		int res = 0;
		try {
			res = Integer.parseInt(strInt);
		} catch (Exception e) {
			res = defaultValue;
		}
		return res;
	}
}
