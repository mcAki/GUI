package com.sys.volunteer.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import com.sys.volunteer.pojo.Liandong;
import com.sys.volunteer.pojo.LiandongPay;
import com.sys.volunteer.pojo.LiandongQQ;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.SupplyInterface;

public class LiandongUtil {
	
	protected static final Log logger = LogFactory.getLog(LiandongUtil.class);

	public static final String LINK_URL = "http://www.97hui.com/MobileService.shtml";
	private static final String PAY_URL = "http://www.97hui.com/payservice.shtml";
	public static final String QQ_LINK_URL = "http://www.97hui.com/qqService.shtml";
	
	
	private static final String RETURN_URL = "http://www.517di.com:8080/MPRS/liandong/doPayRecharge.do";
	private static final String NOTIFY_URL = "http://www.517di.com:8080/MPRS/liandong/notifyLiandong.do";
	
	public static final String ACTION_CHARGE = "Change";
	public static final String ACTION_QUERY_ORDER = "SearchOrder";
	public static final String ACTION_QUERY_ACCOUNT = "SearchAccount";
	public static final String ACTION_REVERSE = "Reverse";
	
	public static final String ACTION_CHARGE_ = "charge";
	public static final String ACTION_QUERY_ORDER_ = "searchOrder";
	public static final String ACTION_QUERY_QQ_ORDER = "queryOrder";
	
	public static final String userName="ryd";
	private static final String pay_userName="pay_ryd";
	public static final String qq_userName = "rongyidi";
	
	//private static final String order_id="123456";
	
	public static final String sign="8f7a4e4a-1448-4d5a-9427-3c6d1aea83b6";
	//dc359b43-18df-40a9-a7c0-3ee66aac14be
	private static final String pay_sign="dc359b43-18df-40a9-a7c0-3ee66aac14be";
	
	public static final String qq_sign = "2a84102a-191a-463b-a0d8-1a345c189225";
	
	public static final double HANDLING_CHARGE=1;
	
	/**
	 * 测试查询
	 * @param cpid
	 * @param cpCardId
	 * @return
	 */
	public static String testQueryOrder(){
		String param="Action="+ACTION_QUERY_ACCOUNT+"&UserName="+userName;
		//logger.info(param+sign);
		MD5 md5=MD5.getiInstance();
		String Key=md5.getMD5ofStr(param+sign);
		//logger.info(Key);
		param=param+"&Key="+Key.toLowerCase();
		//logger.info(param);
		//组成url
		String url=LINK_URL+"?"+param;
		return url;
	}
	
	/**
	 * 充值
	 * @param user
	 * @param mobile
	 * @param mainorder
	 * @param goods
	 * @return
	 */
	public static String rechargeOrder(String mobile,Mainorder mainorder,SupplyInterface supplyInterface){
		String value = StringUtil.subZeroAndDot(supplyInterface.getValue()+"");
		String param="Action="+ACTION_CHARGE+"&UserName="+userName+
				"&Mobile="+mobile+"&OrderID="+mainorder.getMainOrderId()+
				"&Money="+value+"00";
		logger.info("rechargeOrder param:"+param+sign);
		MD5 md5=MD5.getiInstance();
		String Key=md5.getMD5ofStr(param+sign);
		param=param+"&Key="+Key.toLowerCase();
		String url=LINK_URL+"?"+param;
		logger.info("rechargeOrder URL:"+url);
		return url;
	}
	
	/**
	 * 充值qq
	 * @param user
	 * @param mobile
	 * @param mainorder
	 * @param goods
	 * @return
	 */
	public static String rechargeQQOrder(LiandongQQ liandongQQ){
		String param="Action="+ACTION_CHARGE_+"&UserName="+qq_userName+
				"&QQnum="+liandongQQ.getQQnum()+"&Type="+liandongQQ.getType()+
				"&BuyCount="+liandongQQ.getBuyCount()+"&Comm1="+liandongQQ.getComm1()+"&OrderSN="+liandongQQ.getOrderSN();
		String signParam = ACTION_CHARGE_+qq_userName+liandongQQ.getQQnum()+liandongQQ.getType()+liandongQQ.getBuyCount()
				+liandongQQ.getComm1()+liandongQQ.getOrderSN();
		logger.info("rechargeOrder signParam:"+signParam+qq_sign);
		MD5 md5=MD5.getiInstance();
		String Key=md5.getMD5ofStr(signParam+qq_sign);
		param=param+"&Key="+Key.toLowerCase();
		String url=QQ_LINK_URL+"?"+param;
		logger.info("rechargeQQOrder URL:"+url);
		return url;
	}
	
	/**
	 * 查询订单状态
	 * @param user
	 * @param mobile
	 * @param mainorder
	 * @param goods
	 * @return
	 */
	public static String searchOrder(Liandong liandong){
		String param="Action="+ACTION_QUERY_ORDER+"&UserName="+userName+
				"&OrderID="+liandong.getOrderID();
		logger.info("searchOrder param:"+param+sign);
		MD5 md5=MD5.getiInstance();
		String Key=md5.getMD5ofStr(param+sign);
		param=param+"&Key="+Key.toLowerCase();
		String url=LINK_URL+"?"+param;
		logger.info("searchOrder URL:"+url);
		return url;
	}
	
	/**
	 * 查询QQ订单状态
	 * @param user
	 * @param mobile
	 * @param mainorder
	 * @param goods
	 * @return
	 */
	public static String searchQQOrder(LiandongQQ liandongQQ){
		String param="Action="+ACTION_QUERY_QQ_ORDER+"&UserName="+qq_userName+
				"&OrderSN="+liandongQQ.getOrderSN();
		String signParam = ACTION_QUERY_QQ_ORDER+qq_userName+liandongQQ.getOrderSN();
		logger.info("searchQQOrder signParam:"+signParam+qq_sign);
		MD5 md5=MD5.getiInstance();
		String Key=md5.getMD5ofStr(signParam+qq_sign);
		param=param+"&Key="+Key.toLowerCase();
		String url=QQ_LINK_URL+"?"+param;
		logger.info("searchQQOrder URL:"+url);
		return url;
	}
	
	/**
	 * 冲正状态
	 * @param user
	 * @param mobile
	 * @param mainorder
	 * @param goods
	 * @return
	 */
	public static String reverseOrder(Liandong liandong){
		String param="Action="+ACTION_REVERSE+"&UserName="+userName+
				"&OrderID="+liandong.getOrderID();
		logger.info("reverseOrder param:"+param+sign);
		MD5 md5=MD5.getiInstance();
		String Key=md5.getMD5ofStr(param+sign);
		param=param+"&Key="+Key.toLowerCase();
		String url=LINK_URL+"?"+param;
		logger.info("reverseOrder URL:"+url);
		return url;
	}
	
	/**
	 * 联动财付通支付
	 * @param liandongPay
	 * @return
	 */
	public static String payRecharge(LiandongPay liandongPay){
		String param = "userName="+pay_userName+"&payOrder="+liandongPay.getPayOrder()+
				"&payMoney="+liandongPay.getPayMoney();//+"&key="+sign+
				//"&returnUrl="+RETURN_URL+"&notifyUrl="+NOTIFY_URL;
		MD5 md5=MD5.getiInstance();
		//key=md5(userName+payOrder+payMoney+sign)
		String key=md5.getMD5ofStr(pay_userName+liandongPay.getPayOrder()+liandongPay.getPayMoney()+pay_sign).toLowerCase();
		logger.info(pay_userName+liandongPay.getPayOrder()+liandongPay.getPayMoney()+pay_sign);
		param=param+"&key="+key+"&returnUrl="+RETURN_URL+"&notifyUrl="+NOTIFY_URL;
		String url=PAY_URL+"?"+param;
		return url;
	}
	
	/**
     * 递归遍历方法
     *
     * @param element
     */
    private static Map getElementList(Map map,Element element) {
        List<Element> elements = element.elements();
        if (elements.size() == 0) {
            //没有子元素
            String xpath = element.getName();
            String value = element.getTextTrim();
            map.put(xpath, value);
        } else {
            //有子元素
            for (Iterator it = elements.iterator(); it.hasNext();) {
                Element elem = (Element) it.next();
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
    public static Liandong getLiandong(String url,int time){
    	SAXReader sr=new SAXReader();
		Document document;
		Element re;
		//Element username = null;
		//Element account = null;
		try {
			document=sr.read(url);
			logger.info("生成xml字符串:"+document.asXML());
			re=document.getRootElement();
			Map map = new HashMap();
			map = getElementList(map,re);
			return Liandong.init(map,time,document.asXML());
			//username=re.element("UserName");
			//logger.info(username.getStringValue());
			//account=re.element("Account");
			//logger.info(account.getStringValue());
			//logger.info(element.attributeValue("UserName"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * HTTP读取XML
     * @return
     */
    public static LiandongQQ getLiandongQQ(String url,int time){
    	SAXReader sr=new SAXReader();
		Document document;
		Element re;
		//Element username = null;
		//Element account = null;
		try {
			document=sr.read(url);
			logger.info("生成xml字符串:"+document.asXML());
			re=document.getRootElement();
			Map map = new HashMap();
			map = getElementList(map,re);
			return LiandongQQ.init(map,time,document.asXML());
			//username=re.element("UserName");
			//logger.info(username.getStringValue());
			//account=re.element("Account");
			//logger.info(account.getStringValue());
			//logger.info(element.attributeValue("UserName"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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

	
}
