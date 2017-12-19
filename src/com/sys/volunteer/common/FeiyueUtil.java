package com.sys.volunteer.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

import com.sys.volunteer.pojo.Feiyue;
import com.sys.volunteer.pojo.Liandong;
import com.sys.volunteer.pojo.LiandongPay;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.SupplyInterface;

public class FeiyueUtil {
	
	protected static final Log logger = LogFactory.getLog(FeiyueUtil.class);

	public static final String CHARGE_URL = "http://www.ek10010.com/api/Recharge.php";
	public static final String REVERSE_URL = "http://www.ek10010.com/api/Reverse.php";
	public static final String QUERY_BALANCE_URL = "http://www.ek10010.com/api/Query_balance.php";
	public static final String STREAMID_QUERY_URL = "http://www.ek10010.com/api/Streamid_query.php";
	public static final String MUT_QUERY_URL = "http://www.ek10010.com/api/Mut_query.php";
	
	/**
	 * 加密key
	 */
	private static final String SIGN = "";
	
	/**
	 * 商户号
	 */
	public static final String agtte1 = "";
	
	/**
	 * 交易密码
	 */
	public static final String pwd = "";
	
	/**
	 * 充值
	 * @param user
	 * @param mobile
	 * @param mainorder
	 * @param goods
	 * @return
	 */
	public static String rechargeOrder(Feiyue feiyue){
		String param="agttel="+feiyue.getAgtte1()+"&phone="+feiyue.getPhone()+"&money="+feiyue.getMoney()
						+"&pwd="+feiyue.getPwd()+"&sn="+feiyue.getSn();
		MD5 md5=MD5.getiInstance();
		String sign=md5.getMD5ofStr(param+SIGN);
		param=param+"&sign="+sign;
		//String url=CHARGE_URL+"?"+param;
		return param;
	}
	
	/**
	 * 冲正
	 * @param user
	 * @param mobile
	 * @param mainorder
	 * @param goods
	 * @return
	 */
	public static String reverseOrder(Feiyue feiyue){
		String param="agttel="+feiyue.getAgtte1()+"&phone="+feiyue.getPhone()+"&money="+feiyue.getMoney()
						+"&pwd="+feiyue.getPwd();
		MD5 md5=MD5.getiInstance();
		String sign=md5.getMD5ofStr(param+SIGN);
		param=param+"&sign="+sign;
		//String url=REVERSE_URL+"?"+param;
		return param;
	}
	
	/**
	 * 查询订单
	 * @param user
	 * @param mobile
	 * @param mainorder
	 * @param goods
	 * @return
	 */
	public static String queryOrder(Feiyue feiyue){
		String param="agttel="+feiyue.getAgtte1()+"&streamid="+feiyue.getStreamid();
		MD5 md5=MD5.getiInstance();
		String sign=md5.getMD5ofStr(param+SIGN);
		param=param+"&sign="+sign;
		//String url=REVERSE_URL+"?"+param;
		return param;
	}
	
	/**
	 * 查询订单
	 * @param user
	 * @param mobile
	 * @param mainorder
	 * @param goods
	 * @return
	 */
	public static String queryBalance(Feiyue feiyue){
		String param="agttel="+feiyue.getAgtte1()+"&pwd="+feiyue.getPwd();
		MD5 md5=MD5.getiInstance();
		String sign=md5.getMD5ofStr(param+SIGN);
		param=param+"&sign="+sign;
		//String url=REVERSE_URL+"?"+param;
		return param;
	}
	
	 /**
     * 递归遍历方法
     *
     * @param element
     */
    private static Map getElementList(Element element) {
        List<Element> elements = element.elements();
        Map<String, String> map = new HashMap<String, String>();
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
                getElementList(elem);
            }
        }
        return map;
    }
    
    /**
     * HTTP读取XML
     * @return
     */
    public static Feiyue getFeiyue(String url, int time){
    	SAXReader sr=new SAXReader();
		Document document;
		Element re;
		//Element username = null;
		//Element account = null;
		try {
			document=sr.read(url);
			logger.info("生成xml字符串:"+document.asXML());
			re=document.getRootElement();
			Map map = getElementList(re);
			return Feiyue.init(map,time,document.asXML());
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
