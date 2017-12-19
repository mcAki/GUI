package com.sys.volunteer.common;

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

import com.sys.volunteer.pojo.Zhongrong;

public class ZhongRongUtil {
	
	protected static final Log logger = LogFactory.getLog(ZhongRongUtil.class);

	private static final String SEARCHBALANCE = "http://219.153.41.158:8077/soft/searchbalance.do";
	private static final String ORDERCHARGE = "http://219.153.41.158:8077/soft/onlinepay.do";
	private static final String ORDERQUERY = "http://219.153.41.158:8077/soft/searchpay.do";

	private static final String USERID = "10014360";

	private static final String KEY = "2361889DF924F8A9214F0203673A9D2F";

	public static String searchBalance() {
		String param = "userid=" + USERID;
		MD5 md5 = MD5.getiInstance();
		String sign = md5.getMD5ofStr(param + "&key=" + KEY).toLowerCase();
		param = param + "&sign=" + sign;
		String url = SEARCHBALANCE + "?" + param;
		return url;
	}

	/**
	 * 订单充值
	 * 
	 * @param mobile
	 * @param mainorder
	 * @param supplyInterface
	 * @return
	 */
	public static String orderCharge(Zhongrong zhongrong) {
		String time = zhongrong.getSpordertime();
		//sign=MD5(userid=xxxx&productid=xxxxxxx&price=xxxx&num=xxx&mobile=xxxxx&spordertime=xxxxxxx&sporderid=xxxxx&key=xxxxxxx)
		String param = "userid=" + USERID + "&productid="+zhongrong.getProductid()+"&productType="+zhongrong.getProductType()+"&price="
				+ zhongrong.getPrice() + "&num="+zhongrong.getNum()+"&mobile=" + zhongrong.getMobile()
				+ "&spordertime=" + time + "&sporderid="
				+ zhongrong.getSporderid();
		MD5 md5 = MD5.getiInstance();
		String signParam = "userid=" + USERID + "&productid="+zhongrong.getProductid()+"&price="
		+ zhongrong.getPrice() + "&num="+zhongrong.getNum()+"&mobile=" + zhongrong.getMobile()
		+ "&spordertime=" + time + "&sporderid="
		+ zhongrong.getSporderid();
		String sign = md5.getMD5ofStr(signParam + "&key=" + KEY).toLowerCase();
		param = param + "&sign=" + sign + "&backurl=";
		String url = ORDERCHARGE + "?" + param;
		logger.info("中融充值地址参数" + url);
		return url;
	}

	/**
	 * 查询订单
	 * 
	 * @param zhongrong
	 * @return
	 */
	public static String orderQuery(Zhongrong zhongrong) {
		String param = "userid=" + USERID + "&sporderid="
				+ zhongrong.getSporderid();
		String url = ORDERQUERY + "?" + param;
		logger.info("中融查询地址参数"+url);
		return url;
	}

	/**
	 * HTTP读取XML
	 * 
	 * @return
	 */
	public static Zhongrong getZhongrong(String url, int time) {
		SAXReader sr = new SAXReader();
		Document document;
		Element re;
		// Element username = null;
		// Element account = null;
		try {
			document = sr.read(url);
			logger.info("生成xml字符串:" + document.asXML());
			re = document.getRootElement();
			Map map = new HashMap();
			map = getElementList(map,re);
			return Zhongrong.init(map, time, document.asXML());
			// username=re.element("UserName");
			// logger.info(username.getStringValue());
			// account=re.element("Account");
			// logger.info(account.getStringValue());
			// logger.info(element.attributeValue("UserName"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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
}
