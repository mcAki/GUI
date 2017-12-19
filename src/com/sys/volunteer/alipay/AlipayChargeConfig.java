/**
 * 
 */
package com.sys.volunteer.alipay;

import com.sys.volunteer.common.UtilDate;

/**
 * @author hanwen 支付宝参数
 *
 * @version 创建时间：2012-12-28  下午04:46:08
 */
public class AlipayChargeConfig {

//-------------------基础参数开始配置------------------
	
	/**
	 *  合作身份者ID，以2088开头由16位纯数字组成的字符串
	 */
	public static String partner = "2088601342243360"; //不能为空
	
	/**
	 *  商户的私钥
	 */
	public static String key = "uq3qvb6d3nfr6ji8plb8pazbgzehn3me";

	/**
	 *  调试用，创建TXT日志文件夹路径
	 */
	public static String log_path = "D:\\";

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
    public static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";
    
    /**
     * 支付宝消息验证地址
     */
    public static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";
    
    /**
     * 接口名称；
     */
    public static final String service = "create_direct_pay_by_user"; //不能为空
    
    /**
     * 请求出错是的通知页面路径
     */
    public static String error_notify_url = "";
    /**
     * 服务器异步通知页面路径
     */
    public static String notify_url = "";
    
    /**
     *  页面跳转同步通知页面路径
     */
    public static String return_url = "http://www.517di.com:8123/MPRS/alipayCharge/chargeback.do";
    
    //-----------------------开始配置业务参数---------------------
    /**
	 * 商品名称
	 */
	public static String subjectTemp = ""; //不能为空
	
	/**
	 * 商户与网站唯一订单号
	 */
	public static String out_trade_no = UtilDate.getOrderNum(); //不能为空
	/**
	 * 支付类型(默认为1-商品购买)
	 */
	public static String payment_type = "1"; //不能为空
	/**
	 * 默认网银
	 */
	public static String defaultbank = "ICBCB2C";  //不能为空
	
	/**
	 * seller_email 卖家支付帐号
	 */
	public static String seller_email = "miaomingmaoyi@163.com";
	
	/**
	 * seller_id 卖家支付宝帐户号
	 */
	public static String seller_id = "";
	
	/**
	 * 卖家别名支付宝帐号
	 */
	public static String seller_account_name = "";
	
    /**
     * price 商品单价
     */
	public static String price = "";
	
	/**
	 * 交易金额
	 */
	public static String total_fee = "";
	
	/**
	 * 购买数量
	 */
	public static String quantity = "";
	
	/**
	 * body 商品描述
	 * 
	 */
	public static  String body = "";
	
	
	/**
	 *  show_url 商品展示网址
	 */
	public static String show_url = "";
	
	/**
	 * paymethod 默认支付方式
	 */
	public static String paymethod = "bankPay";
	 
	/**
	 * need_ctucheck 网银支付是否做CTU校验
	 */
	public static String need_ctucheck="";
}
