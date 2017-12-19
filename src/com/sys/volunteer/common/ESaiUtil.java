package com.sys.volunteer.common;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sys.volunteer.pojo.ESaiCharge;
import com.sys.volunteer.pojo.ESaiQuery;

public class ESaiUtil {
	
	protected static final Log logger = LogFactory.getLog(ESaiUtil.class);


	public static final String TEST_RECHARGE_URL = "http://www.esaipai.com/IF/EIFPHONE/IRechargeList_Phone_Test";
	public static final String TEST_QUERY_URL = "http://www.esaipai.com/IF/EIFQUERY/IRechargeResult_Test";
	
	public static final String TEST_USERNUMBER = "1500009";
	public static final String TEST_SIGN = "testkey";
	
	public static final String RECHARGE_URL = "http://www.esaipai.com/IF/EIFPHONE/IRechargeList_Phone";
	public static final String QUERY_URL = "http://www.esaipai.com/IF/EIFQUERY/IRechargeResult";
	
	public static final String USERNUMBER = "8000023";
	public static final String SIGN = "f3cb38955e4efc2ab61b9815381b881a";
	
	
	/**
	 * 易赛充值接口
	 * @return
	 */
	public static String rechargeOrder(ESaiCharge eSaiCharge){
		String param = "UserNumber="+eSaiCharge.getUserNumber()+"&InOrderNumber="+eSaiCharge.getInOrderNumber()
					+"&OutOrderNumber="+eSaiCharge.getOutOrderNumber()
					+"&PhoneNumber="+eSaiCharge.getPhoneNumber()+"&Province="+eSaiCharge.getProvince()
					+"&City="+eSaiCharge.getCity()+"&PhoneClass="+eSaiCharge.getPhoneClass()+"&PhoneMoney="+eSaiCharge.getPhoneMoney()
					+"&SellPrice="+eSaiCharge.getSellPrice()+"&StartTime="+eSaiCharge.getStartTime()+"&TimeOut="+eSaiCharge.getTimeOut()
					+"&RecordKey="+eSaiCharge.getRecordKey()+"&Remark="+eSaiCharge.getRemark();
		String sign = eSaiCharge.getUserNumber() + eSaiCharge.getInOrderNumber() + eSaiCharge.getOutOrderNumber()
					+ eSaiCharge.getPhoneNumber() + eSaiCharge.getProvince() + eSaiCharge.getCity()
					+ eSaiCharge.getPhoneClass() + eSaiCharge.getPhoneMoney() + eSaiCharge.getSellPrice()
					+ eSaiCharge.getStartTime() + eSaiCharge.getTimeOut() + SIGN;
		sign = MD5Ex.getMD5Str(sign, "GB2312").substring(0, 16).toUpperCase();
		if (!sign.equals(eSaiCharge.getRecordKey())) {
			param = "UserNumber="+eSaiCharge.getUserNumber()+"&InOrderNumber="+eSaiCharge.getInOrderNumber()
					+"&OutOrderNumber="+eSaiCharge.getOutOrderNumber()
					+"&PhoneNumber="+eSaiCharge.getPhoneNumber()+"&Province="+eSaiCharge.getProvince()
					+"&City="+eSaiCharge.getCity()+"&PhoneClass="+eSaiCharge.getPhoneClass()+"&PhoneMoney="+eSaiCharge.getPhoneMoney()
					+"&SellPrice="+eSaiCharge.getSellPrice()+"&StartTime="+eSaiCharge.getStartTime()+"&TimeOut="+eSaiCharge.getTimeOut()
					+"&RecordKey="+sign+"&Remark="+eSaiCharge.getRemark();
			logger.error("易赛充值接口2次MD5加密不一致!");
		}
		logger.info("rechargeOrder param:"+param);
		return getRotmePagePost(RECHARGE_URL, "GB2312", param);
	}
	
	/**
	 * 易赛查询接口
	 * @param eSaiQuery
	 * @return
	 */
	public static String queryOrder(ESaiQuery eSaiQuery){
		String param = "UserNumber="+eSaiQuery.getUserNumber()+"&InOrderNumber="+eSaiQuery.getInOrderNumber()
					+"&OutOrderNumber="+eSaiQuery.getOutOrderNumber()+"&QueryType="+eSaiQuery.getQueryType()
					+"&RecordKey="+eSaiQuery.getRecordKey()+"&Remark="+eSaiQuery.getRemark();
		String sign = eSaiQuery.getUserNumber() + eSaiQuery.getInOrderNumber() + eSaiQuery.getOutOrderNumber()
					+ eSaiQuery.getQueryType() + SIGN;
		sign = MD5Ex.getMD5Str(sign, "GB2312").substring(0, 16).toUpperCase();
		if (!sign.equals(eSaiQuery.getRecordKey())) {
			param = "UserNumber="+eSaiQuery.getUserNumber()+"&InOrderNumber="+eSaiQuery.getInOrderNumber()
			+"&OutOrderNumber="+eSaiQuery.getOutOrderNumber()+"&QueryType="+eSaiQuery.getQueryType()
			+"&RecordKey="+sign+"&Remark="+eSaiQuery.getRemark();
			logger.error("易赛查询接口2次MD5加密不一致!");
		}
		logger.info("queryOrder param:"+param);
		return getRotmePagePost(QUERY_URL, "GB2312", param);
	}
	
	 /**
     * 递归遍历方法
     *
     * @param element
     */
    private static Map getElementList(Map<String, String> map,Element element) {
        List<Element> elements = element.elements();
        //Map<String, String> map = new HashMap<String, String>();
        if (elements.size() == 0) {
            //没有子元素
            String xpath = element.getName();
            String value = element.getTextTrim();
            map.put(xpath, value);
        } else {
            //有子元素
            for (Iterator it = elements.iterator(); it.hasNext();) {
                Element elem = (Element) it.next();
                System.out.println(elem.getName()+elem.getData());
                //递归遍历
                getElementList(map,elem);
            }
        }
        return map;
    }
    
    /**
     * HTTP读取XML
     * @return
     */
    public static ESaiCharge getESaiCharge(String xml){
    	SAXReader sr=new SAXReader();
		Document document;
		Element re;
		//Element username = null;
		//Element account = null;
		try {
			//<?xml version="1.0" encoding="GB2312"?>
			//<esaipay>
			//<result>success</result><inOrderNumber>IP1500009201401041131124066</inOrderNumber><outOrderNumber>4028d81a43560b4901435b4dd04e000d</outOrderNumber><remark>--</remark><recordKey>7DC7A43EB8B2B734</recordKey>
			//</esaipay>
			document=sr.read(new ByteArrayInputStream(xml.getBytes("GB2312")));
			logger.info("生成xml字符串:"+document.asXML());
			re=document.getRootElement();
			System.out.println(re.getName());
			Map map = new HashMap<String, String>();
			map = getElementList(map,re);
			return ESaiCharge.init(map,document.asXML());
			//username=re.element("UserName");
			//logger.info(username.getStringValue());
			//account=re.element("Account");
			//logger.info(account.getStringValue());
			//logger.info(element.attributeValue("UserName"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    /**
     * HTTP读取XML
     * @return
     */
    public static ESaiQuery getESaiQuery(String xml){
    	SAXReader sr=new SAXReader();
		Document document;
		Element re;
		//Element username = null;
		//Element account = null;
		try {
			document=sr.read(new ByteArrayInputStream(xml.getBytes("GB2312")));
			logger.info("生成xml字符串:"+document.asXML());
			re=document.getRootElement();
			Map map = new HashMap<String, String>();
			map = getElementList(map,re);
			return ESaiQuery.init(map,document.asXML());
			//username=re.element("UserName");
			//logger.info(username.getStringValue());
			//account=re.element("Account");
			//logger.info(account.getStringValue());
			//logger.info(element.attributeValue("UserName"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }


	
	public static void main(String[] args) { 
        //请填写网站相应的编码格式 utf-8 ,gb2312这样就不会乱码了 
        //logger.info(LiandongUtil.getRotmePage(testQueryOrder(),"gbk")); 
		//testQueryOrder();
    }

	/**
	 * 输出远程页面的字符串
	 * @param url
	 * @param charset
	 * @return
	 */
	public static String getRotmePage(String url,String charset) { 
	        String sText = ""; 
	        URLConnection urlcon = null; 
	        try { 
	            URL XmlUrl = new URL(url); 
	            urlcon = XmlUrl.openConnection(); 
	            InputStream in = urlcon.getInputStream(); 
	            String line = ""; 
	            InputStreamReader fileIn = new InputStreamReader(in,charset); 
	            BufferedReader reader = new BufferedReader(fileIn); 
	            while ((line = reader.readLine()) != null) { 
	                sText += line; 
	            } 
	        } catch (MalformedURLException e) { 
	            e.printStackTrace(); 
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	        } 
	        return sText; 
	}

	/**
	 * 输出远程页面的字符串(post)
	 * @param addr 地址
	 * @param charset 字符编码
	 * @param param 参数
	 * @return
	 * @throws IOException
	 */
	public static String getRotmePagePost(String addr,String charset,String param){     
	        /**   
	         * 首先要和URL下的URLConnection对话。 URLConnection可以很容易的从URL得到。比如： // Using   
	         *  java.net.URL and //java.net.URLConnection   
	         *   
	         *  使用页面发送请求的正常流程：在页面http://www.faircanton.com/message/loginlytebox.asp中输入用户名和密码，然后按登录， 
	         *  跳转到页面http://www.faircanton.com/message/check.asp进行验证 
	         *  验证的的结果返回到另一个页面 
	         *   
	         *  使用java程序发送请求的流程：使用URLConnection向http://www.faircanton.com/message/check.asp发送请求 
	         *  并传递两个参数：用户名和密码 
	         *  然后用程序获取验证结果 
	         */    
	        URL url;
	        String sCurrentLine;     
	        sCurrentLine = "";     
			try {
				url = new URL(addr);
				 URLConnection connection = url.openConnection();     
			        /**   
			         * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。   
			         * 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做：   
			         */    
			        connection.setDoOutput(true);     
			        /**   
			         * 最后，为了得到OutputStream，简单起见，把它约束在Writer并且放入POST信息中，例如： ...   
			         */    
			        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), charset);     
			        out.write(param); //向页面传递数据。post的关键所在！     
			        // remember to clean up     
			        out.flush();     
			        out.close();     
			        /**   
			         * 这样就可以发送一个看起来象这样的POST：    
			         * POST /jobsearch/jobsearch.cgi HTTP 1.0 ACCEPT:   
			         * text/plain Content-type: application/x-www-form-urlencoded   
			         * Content-length: 99 username=bob password=someword   
			         */    
			        // 一旦发送成功，用以下方法就可以得到服务器的回应：     
			        InputStream l_urlStream;     
			        l_urlStream = connection.getInputStream();     
			        // 传说中的三层包装阿！     
			        BufferedReader l_reader = new BufferedReader(new InputStreamReader(     
			                l_urlStream));   
			        sCurrentLine = l_reader.readLine();
			} catch (MalformedURLException e) {
				logger.error("httpPost url出现错误");
				e.printStackTrace();
			} catch (IOException e) {
				logger.error("httpPost io出现错误");
				e.printStackTrace();
			}
			return sCurrentLine; 
	       
	    }     

	
}
