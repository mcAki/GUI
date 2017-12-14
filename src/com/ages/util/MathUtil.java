package com.ages.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MathUtil {

	
	public static final String TWO_DECIMAL_FORMAT="#.##";
	
	public static double formatDoubleValue(double value,String formatString){
		DecimalFormat  df =new DecimalFormat(formatString); 
        return Double.parseDouble(df.format(value));
	}

	/**
	 * 
	 * @Title: formatDoubleValue
	 * @Description: 精度截取
	 * @param value
	 *            原数值
	 * @param newScale
	 *            精度位数(保留小数位),四舍五入原则
	 * @return double
	 */
	public static double formatDoubleValue(double value, int newScale) {
		BigDecimal bd = new BigDecimal(value);
		// 四舍五入原则,BigDecimal.ROUND_HALF_UP
		bd = bd.setScale(newScale, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}

	/**
	 * 
	 * @Title: doubleToInt
	 * @Description: double转换int
	 * @param value
	 * @return int
	 */
	public static int doubleToInt(double value) {
		BigDecimal bd = new BigDecimal(value);
		return bd.intValue();
	}
}
