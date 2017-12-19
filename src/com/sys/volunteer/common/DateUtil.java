package com.sys.volunteer.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 时间格式转换
 * @author Administrator
 *
 */
public class DateUtil {
	private static final Log log = LogFactory.getLog(DateUtil.class);
	public static final String CM_LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String CM_LONG_DATE_FORMAT_NO_HIPHEN = "yyyyMMddHHmmss";
	public static final String CM_SHORT_DATE_FORMAT = "yyyy-MM-dd";
	public static final String CM_SHORT_MONTH_FORMAT = "yyyy-MM";
	public static final String CM_SHORT_YEAR_FORMAT = "yyyy";
	public static final String YEAR_MONTH = "yyyyMM";
	public static final String SHORT_MONTH_FORMAT = "MM";

	public static Date formatDate(String dateStr) {
		Date dt = null;
		if (dateStr == null || dateStr.trim().equalsIgnoreCase("")) return null;;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dt = sdf.parse(dateStr);
		} catch (ParseException e) {
			log.error(e);
			e.printStackTrace();
		}

		return dt;
	}

	public static Date formateDate(String date, String hour) {
		String target = date + " " + hour + ":00:00.0";
		return formatDate(target, CM_LONG_DATE_FORMAT);
	}

	public static Date formatDate(String dateStr, String formate) {
		Date dt = null;
		if (dateStr == null || dateStr.trim().equalsIgnoreCase("")) return null;;
		SimpleDateFormat sdf = new SimpleDateFormat(formate);
		try {
			dt = sdf.parse(dateStr);
		} catch (ParseException e) {
			log.error(e);
			e.printStackTrace();
		}

		return dt;
	}

	/**
	 * 取得当前年份
	 * 
	 * @return
	 */
	public static String getNowYear() {
		Date myDate = new Date();
		String nowYear = DateUtil.DateToString(myDate, CM_SHORT_YEAR_FORMAT);
		return nowYear;
	}

	/**
	 * 取得当前月份
	 * 
	 * @return
	 */
	public static String getMonth() {
		Date myDate = new Date();
		String month = DateUtil.DateToString(myDate, SHORT_MONTH_FORMAT);
		return month;
	}

	/**
	 * date装string
	 * 
	 * @param date
	 * @param iso
	 * @return
	 */
	public static String DateToString(Date date, String iso) {
		if (date == null) {
			return "";
		}
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(iso);
		return format.format(date);
	}

	public static String getUpYearByParam(int yearOffest) {
		Date myDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.YEAR, yearOffest);

		String nextyear = DateUtil.DateToString(cal.getTime(), CM_SHORT_YEAR_FORMAT);
		return nextyear;
	}

	public static int getLastDayByYearMonth(String year, String month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, new Integer(year).intValue());
		c.set(Calendar.MONTH, new Integer(month).intValue() - 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println(c.getActualMaximum(Calendar.DAY_OF_MONTH));
		// System.out.println(DateToString(c.getTime(),CM_SHORT_DATE_FORMAT));
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static Date getMinDayByYearMonth(String year, String month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, new Integer(year).intValue());
		c.set(Calendar.MONTH, new Integer(month).intValue() - 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return DateUtil.getMinDate(c.getTime());
	}

	public static Date getMaxDayByYearMonth(String year, String month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, new Integer(year).intValue());
		c.set(Calendar.MONTH, new Integer(month).intValue() - 1);
		int day = DateUtil.getLastDayByYearMonth(year, month);
		c.set(Calendar.DAY_OF_MONTH, day);
		return DateUtil.getMaxDate(c.getTime());
	}

	public static List get5yearList() {
		List list = new ArrayList();
		list.add(getUpYearByParam(-2));
		list.add(getUpYearByParam(-1));
		list.add(getNowYear());
		list.add(getUpYearByParam(1));
		list.add(getUpYearByParam(2));
		return list;
	}

	/**
	 * 检测一个日期是否在指定的两个日期段之内
	 * 
	 * @param checkDate
	 *            被检测日期
	 * @param alphaDate
	 *            第一个日期
	 * @param betaDate
	 *            第二个日期
	 * @return
	 */
	public static boolean isTheDateBetween2Date(Date checkDate, Date alphaDate, Date betaDate) {
		int d1 = checkDate.compareTo(alphaDate);// 如果小于0就是chekdate<这个date
		int d2 = checkDate.compareTo(betaDate);// 如果大于0就是checkdate>这个date

		return ((d1 <= 0) && (d2 >= 0)) ^ ((d2 <= 0) && (d1 >= 0));
	}

	public static List<String> get12MonthList() {
		List<String> list = new ArrayList<String>();
		list.add("01");
		list.add("02");
		list.add("03");
		list.add("04");
		list.add("05");
		list.add("06");
		list.add("07");
		list.add("08");
		list.add("09");
		list.add("10");
		list.add("11");
		list.add("12");
		return list;
	}

	public static List getHours() {
		List list = new ArrayList();
		for (int i = 0; i < 24; i++) {
			String s = new String();
			if (i < 10) {
				s = "0" + i;
			} else {
				s = i + "";
			}
			list.add(s);
		}
		return list;
	}

	/**
	 * 获取当日0时0分0秒
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMinDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date minDate = cal.getTime();
		return minDate;
	}

	public static Date getMinDate(String dateStr) {
		return DateUtil.getMinDate(DateUtil.formatDate(dateStr));
	}

	/**
	 * 获取当日最后23点59分59秒
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMaxDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		Date maxDate = cal.getTime();
		return maxDate;
	}

	public static Date getMaxDate(String dateStr) {
		return DateUtil.getMaxDate(DateUtil.formatDate(dateStr));
	}

	public static Date makeDateByDateAndHour(String dateStr, Integer hour) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtil.formatDate(dateStr));
		cal.set(Calendar.HOUR_OF_DAY, hour);
		Date minDate = cal.getTime();
		return minDate;
	}

	public static int currentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public static int currentMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public static Timestamp dateToTimestamp(Date date, String formateStr) {
		String dateStr = DateUtil.DateToString(date, formateStr);
		Timestamp ts = Timestamp.valueOf(dateStr);
		return ts;
	}

	public static Timestamp stringToTimestamp(String dateStr, String formate) {
		Timestamp ts = null;
		if (dateStr == null || dateStr.trim().equalsIgnoreCase("")) return null;;
		SimpleDateFormat sdf = new SimpleDateFormat(formate);
		try {
			Date date = sdf.parse(dateStr);
			ts = new Timestamp(date.getTime());
		} catch (ParseException e) {
			log.error(e);
			e.printStackTrace();
		}

		return ts;
	}

	/**
	 * 判断俩个时间差，根据flag转化结果为时，分，秒
	 * @param endTimestamp
	 * @param startTimestamp
	 * @param flag 0：秒 1：分 2：时
	 * @return
	 */
	public static long subtractTime(Timestamp endTimestamp,Timestamp startTimestamp,int flag){
		int temp=1000;
		if(flag==0){
			temp=1000;
		}else if(flag==1){
			temp=60000;
		}else if(flag==2){
			temp=3600000;
		}
		return (endTimestamp.getTime()-startTimestamp.getTime())/temp;
	}
	
	public static void main(String args[]) {
		// Calendar c = Calendar.getInstance();
		// c.set(Calendar.YEAR, 2007);
		// c.set(Calendar.MONTH, 10);
		// c.set(Calendar.DAY_OF_MONTH, 1);
		// System.out.println(DateToString(c.getTime(), CM_SHORT_DATE_FORMAT));
		// System.out.println(c.getActualMaximum(Calendar.DAY_OF_MONTH));
		// System.out.println(DateUtil.getMaxDayByYearMonth("2009", "4"));
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 2009);
		c.set(Calendar.MONTH, 10);
		System.out.println((new Date()).getMonth());
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.CM_LONG_DATE_FORMAT);
		try {
			Date date = sdf.parse("1111-11-11 11:11:11");
			Timestamp ts = new Timestamp(date.getTime());
			System.out.println(ts);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
