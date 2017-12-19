package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.NameValuePair;

import test.httpClient.HttpProtocolHandler;
import test.httpClient.HttpRequest;
import test.httpClient.HttpResponse;
import test.httpClient.HttpResultType;

import com.mprs.util.MD5;
import com.sys.volunteer.common.UtilDate;

/**
 * 支付宝充值测试
 * @author hanwen
 *
 * @version 创建时间：2012-12-22  下午03:04:08
 */
public class AlipayChargeText {

	//-------------------基础参数开始配置------------------
	
	/**
	 *  合作身份者ID，以2088开头由16位纯数字组成的字符串
	 */
	public static String partner = "2088102568228615"; //不能为空
	
	/**
	 *  商户的私钥
	 */
	public static String key = "3314vilxvf2d6786p7wmodg0sn0yass9";

	/**
	 *  调试用，创建TXT日志文件夹路径
	 */
	public static String log_path = "D:\\";

	public static String log_name = "AlipayChargeLog.txt";
	/**
	 *  字符编码格式 目前支持 gbk 或 utf-8
	 */
	public static String _input_charset = "utf-8";//不能为空
	
	/**
	 *  签名方式 不需修改
	 */
	public static String sign_type = "MD5"; //不能为空
	
	 /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";
    
    /**
     * 支付宝消息验证地址
     */
    private static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";
    
    /**
     * 接口名称；
     */
    public static final String service = "create_direct_pay_by_user"; //不能为空
    
    /**
     * 请求出错是的通知页面路径
     */
    public String error_notify_url = "";
    /**
     * 服务器异步通知页面路径
     */
    public String notify_url = "";
    
    /**
     *  页面跳转同步通知页面路径
     */
    public String return_url = "";
    
    //-----------------------开始配置业务参数---------------------
    /**
	 * 商品名称
	 */
	public String subjectTemp = ""; //不能为空
	
	/**
	 * 商户与网站唯一订单号
	 */
	public String out_trade_no = UtilDate.getOrderNum(); //不能为空
	/**
	 * 支付类型(默认为1-商品购买)
	 */
	public String payment_type = "1"; //不能为空
	/**
	 * 默认网银
	 */
	public String defaultbank = "CMB";  //不能为空
	
	/**
	 * seller_email 卖家支付帐号
	 */
	public String seller_email = "13503008690@139.com";
	
	/**
	 * seller_id 卖家支付宝帐户号
	 */
	public String seller_id = "";
	
	/**
	 * 卖家别名支付宝帐号
	 */
	public String seller_account_name = "";
	
    /**
     * price 商品单价
     */
	public String price = "";
	
	/**
	 * 交易金额
	 */
	public String total_fee = "100";
	
	/**
	 * 购买数量
	 */
	public String quantity = "";
	
	/**
	 * body 商品描述
	 * 
	 */
	public  String body = "";
	
	
	/**
	 *  show_url 商品展示网址
	 */
	public String show_url = "";
	
	/**
	 * paymethod 默认支付方式
	 */
	public String paymethod = "";
	 
	/**
	 * need_ctucheck 网银支付是否做CTU校验
	 */
	public String need_ctucheck="";
	
	
	public String alipayCharge(String productName){
		String subject ="";
		
		subject = java.net.URLEncoder.encode(productName);
		
		Map<String,String> sParaTemp = new HashMap<String,String>();
		sParaTemp.put("service", service);
        sParaTemp.put("partner", partner);
        sParaTemp.put("_input_charset", _input_charset);
        sParaTemp.put("key", key);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("seller_email", seller_email);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", productName);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("paymethod", paymethod);
		sParaTemp.put("defaultbank", defaultbank);
		sParaTemp.put("show_url", show_url);
		Map<String ,String> sPara = buildRequestPara(sParaTemp);
		sPara.put("subject", subject);
		String sign_result = createLinkString(sPara);
		return sign_result;
	}
	
	 /**
     * 建立请求，以表单HTML形式构造（默认）
     * @param sParaTemp 请求参数数组
     * @param strMethod 提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @return 提交表单HTML文本
     */
    public static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName) {
        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp);
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + ALIPAY_GATEWAY_NEW
                      + "_input_charset=" + _input_charset + "\" method=\"" + strMethod
                      + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);

            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        //submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");

        return sbHtml.toString();
    }
	  /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }
	
    /**
     * 建立请求，以模拟远程HTTP的POST请求方式构造并获取支付宝的处理结果
     * 如果接口中没有上传文件参数，那么strParaFileName与strFilePath设置为空值
     * 如：buildRequest("", "",sParaTemp)
     * @param strParaFileName 文件类型的参数名
     * @param strFilePath 文件路径
     * @param sParaTemp 请求参数数组
     * @return 支付宝处理结果
     * @throws Exception
     */
    public static String buildRequest(String strParaFileName, String strFilePath,Map<String, String> sParaTemp) throws Exception {
        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp);

        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();

        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        //设置编码集
        request.setCharset("utf-8");

        request.setParameters(generatNameValuePair(sPara));
        request.setUrl(ALIPAY_GATEWAY_NEW+"_input_charset="+"utf-8");

        HttpResponse response = httpProtocolHandler.execute(request,strParaFileName,strFilePath);
        if (response == null) {
            return null;
        }
        
        String strResult = response.getStringResult();

        return strResult;
    }
    /**
     * 生成签名结果
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
	public static String buildRequestMysign(Map<String, String> sPara) {
    	String prestr = createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        if(sign_type.equals("MD5") ) {
        	mysign = sign(prestr, key, _input_charset);
        }
        return mysign;
    }
	
    /**
     * 生成要请求给支付宝的参数数组
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
    private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
        //除去数组中的空值和签名参数
        Map<String, String> sPara = paraFilter(sParaTemp);
        //生成签名结果
        String mysign = buildRequestMysign(sPara);

        //签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        sPara.put("sign_type", sign_type);

        return sPara;
    }
    
    /** 
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }
    
    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String input_charset) {
    	text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }
    
    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param sign 签名结果
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key, String input_charset) {
    	text = text + key;
    	String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
    	if(mysign.equals(sign)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException 
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }
    
    /**
     * MAP类型数组转换成NameValuePair类型
     * @param properties  MAP类型数组
     * @return NameValuePair类型数组
     */
    private static NameValuePair[] generatNameValuePair(Map<String, String> properties) {
        NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            nameValuePair[i++] = new NameValuePair(entry.getKey(), entry.getValue());
        }

        return nameValuePair;
    }
    
    
    /***************************以下为支付宝返回处理***************************************/
    
    public String AlipayChargeBack(HttpServletRequest request) throws Exception{
    	//获取支付宝GET过来反馈信息
    	Map<String,String> params = new HashMap<String,String>();
    	Map requestParams = request.getParameterMap();
    	for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
    		String name = (String) iter.next();
    		String[] values = (String[]) requestParams.get(name);
    		String valueStr = "";
    		for (int i = 0; i < values.length; i++) {
    			valueStr = (i == values.length - 1) ? valueStr + values[i]
    					: valueStr + values[i] + ",";
    		}
    		//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
    		valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
    		params.put(name, valueStr);
    	}
    	
    	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
    	
    	//商户订单号
    	String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

    	//支付宝交易号
    	String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

    	//交易状态
    	String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

    	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
    	
    	//计算得出通知验证结果
    	boolean verify_result = verify(params);
    	
    	if(verify_result){//验证成功
    		
    		if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
    			//判断该笔订单是否在商户网站中已经做过处理
    				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
    				//如果有做过处理，不执行商户的业务程序
    		}
    		System.out.println("验证成功");
    		return "success";
    	}else{
    		System.out.println("验证失败");
    		return "error";
    	}
    	 
    }
    /**
     * 验证消息是否是支付宝发出的合法消息
     * @param params 通知返回来的参数数组
     * @return 验证结果
     */
    public static boolean verify(Map<String, String> params) {

        //判断responsetTxt是否为true，isSign是否为true
        //responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
        //isSign不是true，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
    	String responseTxt = "true";
		if(params.get("notify_id") != null) {
			String notify_id = params.get("notify_id");
			responseTxt = verifyResponse(notify_id);
		}
	    String sign = "";
	    if(params.get("sign") != null) {sign = params.get("sign");}
	    boolean isSign = getSignVeryfy(params, sign);

        //写日志记录（若要调试，请取消下面两行注释）
        //String sWord = "responseTxt=" + responseTxt + "\n isSign=" + isSign + "\n 返回回来的参数：" + AlipayCore.createLinkString(params);
	    //AlipayCore.logResult(sWord);

        if (isSign && responseTxt.equals("true")) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 获取远程服务器ATN结果,验证返回URL
     * @param notify_id 通知校验ID
     * @return 服务器ATN结果
     * 验证结果集：
     * invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 
     * true 返回正确信息
     * false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
     */
     private static String verifyResponse(String notify_id) {
         //获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求

         String veryfy_url = HTTPS_VERIFY_URL + "partner=" + partner + "&notify_id=" + notify_id;

         return checkUrl(veryfy_url);
     }
     
     /**
      * 根据反馈回来的信息，生成签名结果
      * @param Params 通知返回来的参数数组
      * @param sign 比对的签名结果
      * @return 生成的签名结果
      */
 	private static boolean getSignVeryfy(Map<String, String> Params, String sign) {
     	//过滤空值、sign与sign_type参数
     	Map<String, String> sParaNew = paraFilter(Params);
         //获取待签名字符串
         String preSignStr = createLinkString(sParaNew);
         //获得签名验证结果
         boolean isSign = false;
         if(sign_type.equals("MD5") ) {
         	isSign = verify(preSignStr, sign, key, _input_charset);
         }
         return isSign;
     }
 	 /**
     * 获取远程服务器ATN结果
     * @param urlvalue 指定URL路径地址
     * @return 服务器ATN结果
     * 验证结果集：
     * invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 
     * true 返回正确信息
     * false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
     */
     private static String checkUrl(String urlvalue) {
         String inputLine = "";

         try {
             URL url = new URL(urlvalue);
             HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
             BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection
                 .getInputStream()));
             inputLine = in.readLine().toString();
         } catch (Exception e) {
             e.printStackTrace();
             inputLine = "";
         }

         return inputLine;
     }
  public static void main(String[] args) {
	  String maptext = new AlipayChargeText().alipayCharge("1金币");
	System.out.println(ALIPAY_GATEWAY_NEW+maptext);
	 String a  = "%B1%B4%B6%FB%BD%F0%BB%A4%CD%F3%CA%BD";
}
	
	
	
	
	
	
	
	
	
	
	
}
