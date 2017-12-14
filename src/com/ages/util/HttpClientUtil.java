package com.ages.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.SystemException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	public static final String CHARSET = "UTF-8";
	// http://gmpost:8081/ajax/voName.php?intValue=123&strValue=testStr
	@SuppressWarnings("unchecked")
	public static String sendPostRequest(String url,Map<String, String> params) throws SystemException{
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		try {
            Iterator iter = params.entrySet().iterator(); 
            while (iter.hasNext()) 
            { 
                Map.Entry entry = (Map.Entry) iter.next(); 
                BasicNameValuePair pair= new BasicNameValuePair((String)entry.getKey(), (String)entry.getValue());
                nvps.add(pair);

            } 
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, CHARSET));
			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity1 = response.getEntity();
			String result = EntityUtils.toString(entity1);
			// System.out.println("定义数据库结果:" + returnValue);
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new SystemException("连接HTTP服务器失败!");
		} finally {
			httpPost.releaseConnection();
		}
	}
	
	public static void main(String args[]) throws Exception{
		Map<String, String> params=new  HashMap<String, String>();
		params.put("intValue", "2");
		params.put("strValue", "ppppdddd");
//		DbManager.getInstance().getRequestUrl(1)
		String str=sendPostRequest("http://192.168.1.106:8081/ajax/echo.php",params);
		System.out.println("========");
	}
}
