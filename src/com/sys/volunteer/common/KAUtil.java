package com.sys.volunteer.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sys.volunteer.ka.Query91KAProduct;
import com.sys.volunteer.ka.Recharge91KAGame;
import com.sys.volunteer.pojo.GameRechargeRequest;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Query91KAOrder;
import com.sys.volunteer.pojo.SupplyInterface;

public class KAUtil {

	protected static final Log logger = LogFactory.getLog(KAUtil.class);
	
	private static final String QUERY_INVENTORY = "http://gzrydi.if.91ka.com/if/card/query.php";
	private static final String ORDER = "http://gzrydi.if.91ka.com/if/card/order.php";
	private static final String ORDER_STATUS = "http://gzrydi.if.91ka.com/if/card/card_query_order_status.php";
	private static final String QUERY_BALANCE = "http://gzrydi.if.91ka.com/if/card/card_query_user_account.php";
	
	private static final String GAME_RECHARGE = "http://gzrydi.if.91ka.com/if/autogame/auto_order.php";
	private static final String QUERY_PRODUCT = "http://gzrydi.if.91ka.com/if/autogame/auto_query_game_info.php";
	private static final String QUERY_ORDER = "http://gzrydi.if.91ka.com/if/autogame/auto_query_order_status.php";
	
	private static final String ASK_FORM = "http://www.91ka.com/templates/autogame_tpl/";
	private static final String ASK_JS = "http://www.91ka.com/jscript/autogame/";
	
	
	public static final String cpid="289682";
	
	public static final String test_cpid="6827";
	/**
	 * 盛大一卡通10元
	 */
	public static final String test_cp_card_id="2506";
	
	public static final String MD5KEY="L5nl416bYhTtEKSYL2NHcmzUNrNx1XBX";
	public static final String TESTMD5KEY="own1acrpfryddacpax0zpiqi";
	public static final String DESKEY="L5nl416bYhTtEKSYL2NHcmzU";
	
	private static final String dateformat = "yyyyMMddHHmmss";
	
	/**
	 * 测试查询
	 * @param cpid
	 * @param cpCardId
	 * @return
	 */
	public static String testQueryInventory(Goods goods){
		long time=new Date().getTime();
		
		String param="cpid="+cpid+"&cp_card_id="+goods.getKaCardId()+"&time="+time+"&ret_para=";
		logger.info(param+MD5KEY);
		MD5 md5=MD5.getiInstance();
		String sign=md5.getMD5ofStr(param+MD5KEY);
		logger.info(sign);
		param=param+"&sign="+sign;
		logger.info(param);
		return getRotmePage(QUERY_INVENTORY+"?"+param, "gb2312");
	}
	
	/**
	 * 下订单
	 * @return
	 */
	public static String order(Mainorder mainorder,Goods goods){
		long time = System.currentTimeMillis();
		double fee = goods.getValue()*10000;
		StringBuffer sb = new StringBuffer();
		sb.append("cpid="+cpid+"&cp_card_id="+goods.getKaCardId()+"&cp_order_no="+mainorder.getMainOrderId()+
					"&card_num="+mainorder.getGoodsNo()+"&fee="+fee+"&time="+time+"&ret_para=");
		if (mainorder.getTerminalNo()!=null) {
			sb.append(mainorder.getTerminalNo());
		}
		//测试
		sb.append("n49ds3hb");
		String param = sb.toString();
		logger.info(param+MD5KEY);
		MD5 md5=MD5.getiInstance();
		String sign=md5.getMD5ofStr(param+MD5KEY);
		logger.info(sign);
		param=param+"&sign="+sign;
		logger.info(param);
		return getRotmePage(ORDER+"?"+param,"gb2312");
	}
	
	/**
	 * 获取游戏直充表单
	 * @param autoGameId
	 * @return
	 */
	public static String askForm(String autoGameId){
		String time = DateUtil.DateToString(new Date(), dateformat);
		String url = ASK_FORM + autoGameId + ".htm?time=" + time;
		return url;
	}
	
	/**
	 * 获取游戏直充JS
	 * @param autoGameId
	 * @return
	 */
	public static String askJS(String autoGameId){
		String time = DateUtil.DateToString(new Date(), dateformat);
		String url = ASK_JS + autoGameId + ".js?time=" + time;
		return url;
	}
	
	/**
	 * 游戏直充
	 * @param mainorder
	 * @param gameId
	 * @param autoGameId
	 * @param at
	 * @return
	 */
	public static String gameRecharge(GameRechargeRequest request) {
		StringBuffer sb = new StringBuffer();
		sb.append("cpid="+request.getCpid()+"&game_id="+request.getGameId()+"&autogame_id="+request.getAutogameId()+"&create_time="+request.getCreateTime()+
				"&version="+request.getVersion()+"&cp_order_no="+request.getCpOrderNo()+"&amount="+request.getAmount()+"&"+request.getAt()+
				"ret_para="+request.getRetPara()+"&client_ip="+request.getClientIp());//+"&sign="+request.getSign());
		String param = sb.toString();
		//MD5 md5 = MD5.getiInstance();
		String parakey;
		parakey = param + KAUtil.MD5KEY;
		logger.info("91ka充值参数+key"+parakey);
		String sign = MD5Ex.getMD5Str(parakey,"gb2312").toLowerCase();
		param = param+"&sign="+sign;
		logger.info("91ka发送充值参数"+param);
		return getRotmePagePost(GAME_RECHARGE,"gb2312",param);
	}
	
	/**
	 * 游戏直充产品查询
	 * @param game_id
	 * @return
	 */
	public static String queryProduct(String game_id) {
		StringBuffer sb = new StringBuffer();
		sb.append("cpid="+cpid+"&rtn_type=2&game_id="+game_id);
		String param = sb.toString();
		logger.info(param+MD5KEY);
		MD5 md5=MD5.getiInstance();
		String sign=md5.getMD5ofStr(param+MD5KEY).toLowerCase();
		logger.info(sign);
		param=param+"&sign="+sign;
		logger.info(param);
		return getRotmePagePost(QUERY_PRODUCT, "gb2312",param);
	}
	
	/**
	 * 查询直充结果
	 * @param mainorder
	 * @return
	 */
	public static String queryOrder(Query91KAOrder query) {
		StringBuffer sb = new StringBuffer();
		sb.append("cpid="+cpid+"&cp_order_no="+query.getCpOrderNo());
		String param = sb.toString();
		logger.info(param+MD5KEY);
		MD5 md5=MD5.getiInstance();
		String sign=md5.getMD5ofStr(param+MD5KEY).toLowerCase();
		logger.info(sign);
		param=param+"&sign="+sign;
		logger.info(param);
		return getRotmePage(QUERY_ORDER+"?"+param,"gb2312");
	}
	
	
	public static void main(String[] args) { 
        //请填写网站相应的编码格式 utf-8 ,gb2312这样就不会乱码了 
       // logger.info(testQueryInventory()); 
//		logger.info(queryProduct("2888"));
		Query91KAProduct queryProduct = new Query91KAProduct();
		queryProduct.init(queryProduct("2888"));
		logger.info(queryProduct.getAutogameId());
		Mainorder mainorder = new Mainorder();
		mainorder.setMainOrderId("32165464949494");
		SupplyInterface supplyInterface = new SupplyInterface();
		supplyInterface.setStockPrice(1.0d);
		Recharge91KAGame recharge91kaGame = new Recharge91KAGame();
		//recharge91kaGame.init(gameRecharge(mainorder, supplyInterface, queryProduct.getGameId(), queryProduct.getAutogameId(), at));
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
