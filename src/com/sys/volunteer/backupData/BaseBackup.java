package com.sys.volunteer.backupData;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.sys.volunteer.common.StringUtil;

public class BaseBackup {

	
	public static final int DEFINE_RESULT_SUCCESS_ = 1;
	/**
	 * SQL:表定义已经存在
	 */
	public static final int RESULT_TABLE_EXIST = -1050;
	/**
	 * SQL:表名字不正确
	 */
	public static final int RESULT_INCORRECT_TABLE_NAME = -1103;
	/**
	 * SQL:列不存在
	 */
	public static final int RESULT_KEY_COLUMN_NOT_EXIST = -1072;
	/**
	 * SQL:语法标点符号错误
	 */
	public static final int RESULT_ERROR_SQL_SYNTAX = -1064;
	
	/**
	 * SQL:错误字符串数据
	 */
	public static final int INCORRECT_STRING_VALUE = -1366;
	
	/**
	 * 表不存在
	 */
	public static final int TABLE_NOT_EXIST = -1146;

	public static final String LOGIN_URL = "http://127.0.0.1:3366/ajax/register.php?user=u1&password=12345";

	public static final String DEFINE_URL = "http://127.0.0.1:3366/ajax/defineDb.php";
	public static final String BACKUP_URL = "http://127.0.0.1:3366/ajax/backup.php";

	public static final String CHARSET = "UTF-8";
	
	static final DefaultHttpClient httpclient = new DefaultHttpClient();
	
	static Integer uid;
	
	public static int login() {
		HttpGet httpGet = new HttpGet(LOGIN_URL);
		try {
			HttpResponse response = httpclient.execute(httpGet);
			System.out.println("[指令]登陆: " + response.getStatusLine());
			HttpEntity entity1 = response.getEntity();
			String result = EntityUtils.toString(entity1);
			uid = StringUtil.parseInt(result, -1);
			System.out.println("uid:" + uid);
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		} finally {
			httpGet.releaseConnection();
			// httpPost.releaseConnection();
		}
		return uid;
	}
	
	public static int registerTable(
			String tableName, String sqlContent) {
		HttpPost httpPost = new HttpPost(DEFINE_URL);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		int returnValue;
		try {
			nvps.add(new BasicNameValuePair("id", uid.toString()));
			nvps.add(new BasicNameValuePair("tname", tableName));
			nvps.add(new BasicNameValuePair("struct", sqlContent));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, CHARSET));
			HttpResponse response = httpclient.execute(httpPost);
			System.out.println("[指令]定义数据库: " + response.getStatusLine());
			HttpEntity entity1 = response.getEntity();
			String result = EntityUtils.toString(entity1);
			returnValue = StringUtil.parseInt(result, -1);
			// System.out.println("定义数据库结果:" + returnValue);

		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		} finally {
			httpPost.releaseConnection();
		}
		return returnValue;
	}

	public static int backupDb(
			String tableName, String sqlContent) {
		HttpPost httpPost = new HttpPost(BACKUP_URL);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		int returnValue;
		try {
			nvps.add(new BasicNameValuePair("id", uid.toString()));
			nvps.add(new BasicNameValuePair("tname", tableName));
			nvps.add(new BasicNameValuePair("struct", sqlContent));

			httpPost.setEntity(new UrlEncodedFormEntity(nvps, CHARSET));
			HttpResponse response = httpclient.execute(httpPost);
			System.out.println("[指令]备份数据: " + response.getStatusLine());
			HttpEntity entity1 = response.getEntity();
			String result = EntityUtils.toString(entity1);
			returnValue = StringUtil.parseInt(result, -1);
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		} finally {
			httpPost.releaseConnection();
		}
		return returnValue;
	}
}
