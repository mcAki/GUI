package com.sys.volunteer.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 批量考勤用验证提交数据处理类
 * 
 * @author Administrator
 * 
 */
public class BatchAttendanceVerifyUtil {

	private final static String C_VERIFY_NOSAFE_MOBILE = "\\b\\d{8,16}\\b[,]";

	private final static String C_VERIFY_NOSAFE_USERNAME = "#| |<|>|@|/|'|\"|%|update|insert|and|not|or";

	/**
	 * 获取安全化后的电话数组
	 * 
	 * @param mobiles
	 * @return
	 */
	public static String[] getSafeMobiles(String mobiles) {

		if (mobiles.equals(null) || mobiles.equals("")) {
			return null;
		}
		if (!mobiles.endsWith(",")) {
			mobiles += ",";
		}	

		mobiles = mobiles.replaceAll("，", ",");
		mobiles = mobiles.replaceAll("\r\n", ",");
		mobiles = mobiles.replaceAll("\n", ",");

		Pattern pattern = Pattern.compile(C_VERIFY_NOSAFE_MOBILE);
		Matcher matcher = pattern.matcher(mobiles);
		String arrStr="";
		while(matcher.find()) {
			arrStr += matcher.group().trim();
		}

		if (arrStr.endsWith(",")) {
			arrStr = arrStr.substring(0, arrStr.length() - 1);
		}

		return arrStr.split(",");
		
		
		
	}

	/**
	 * 获取安全化后的人物
	 * 
	 * @param users
	 * @return
	 */
	public static String[] getSafeUserName(String users) {
		String resultString = "";

		if (users.equals(null) || users.equals("")) {
			return null;
		}
	
		users = users.replaceAll("，", ",");
		users = users.replaceAll("\r\n", ",");
		users = users.replaceAll("\n", ",");

		Pattern pattern = Pattern.compile(C_VERIFY_NOSAFE_USERNAME);
		Matcher matcher = pattern.matcher(users);

		users = matcher.replaceAll("");

		String rpUser;
		do {
			rpUser=users;
			users = users.replace(",,", ",");
		} while(!users.equals(rpUser));
				
		
		String[] arrStr = users.split(",");
		
		for(int i=0; i<arrStr.length; i++){
			arrStr[i]=arrStr[i].trim(); 
		}

//		for (int i = 0; i < arrStr.length; i++) {
//			resultString += arrStr[i] + ",";
//		}
//		if (resultString.endsWith(",")) {
//			resultString = resultString.substring(0, resultString.length() - 1);
//		}

		return arrStr;
	}
	
	/**
	 * 获取安全化后的身份证
	 * 
	 * @param users
	 * @return
	 */
	public static String[] getSafeIdCard(String IdCard){
		return getSafeUserName(IdCard);
	
	}
	
	/**
	 * 获取安全化后的人物
	 * 
	 * @param usersId
	 * @return
	 */
	public static String[] getSafeUserId(String usersId) {
		String resultString = "";

		if (usersId.equals(null) || usersId.equals("")) {
			return null;
		}
		usersId = usersId.replaceAll("，", ",");
		usersId = usersId.replaceAll("\r\n", ",");
		usersId = usersId.replaceAll("\n", ",");

		Pattern pattern = Pattern.compile(C_VERIFY_NOSAFE_USERNAME);
		Matcher matcher = pattern.matcher(usersId);

		usersId = matcher.replaceAll("");

		String[] arrStr = usersId.split(",");

//		for (int i = 0; i < arrStr.length; i++) {
//			resultString += arrStr[i] + ",";
//		}
//		if (resultString.endsWith(",")) {
//			resultString = resultString.substring(0, resultString.length() - 1);
//		}

		return arrStr;
	}
}
