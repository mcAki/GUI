package com.sys.volunteer.xunyuan.util;

import com.sys.volunteer.common.StringUtil;

public class GenPackHeader {

	public static int num = 0; 
	
	public final static String header = "ry$d"; 
	
	/**
	 * 生成包头序列号
	 * @return
	 */
	public static String genHeader() {
		if (num < 999999) {
			num++;
		}else {
			num = 0;
		}
		String lastHeader = StringUtil.addZeroForNum(num+"", 6);
		String finalHeader = header + lastHeader;
		return finalHeader;
	}
}
