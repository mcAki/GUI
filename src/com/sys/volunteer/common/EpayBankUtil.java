package com.sys.volunteer.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.epaylinks.dsf.ws.client.EpayDsfAPI;
import cn.epaylinks.dsf.ws.client.EpayDsfAPIConfig;

import com.sys.volunteer.pojo.EpayBankpayRequest;
import com.sys.volunteer.pojo.EpayBankpayResponse;

public class EpayBankUtil {

	protected static final Log logger = LogFactory.getLog(EpayBankUtil.class);

	public static final String AUTHCHARGE_TRANCODE = "8009";
	
	public static final String AUTHCHARGE_TERMNO = EpayDsfAPIConfig.terminalNo;
	
	public static final String TEST_AUTHCHARGE_TERMNO = "43181291";
	
	public static final String TEST_AUTHCHARGE_RECARDNO = "1234567812340015";
	
	public static final String AUTHCHARGE_RECARDNO = "0103000453789683";
	
	public static final String MD5KEY = "yuiwbdueu8394939896481kfweievmjf20509";
	
	public static final String MD5_TERM_PASSWORD = "ed90f84b198862900409d4df8badfda5";//MD5(869590602177)
	
	public static String authCharge(String xml) {
		try{
			
			//String resp = EpayDsfAPI.callWs("payToBank",new Object[]{xml});
			//授权收款
			String resp = EpayDsfAPI.callWs("authCharge",new Object[]{xml});
			//System.out.println(resp);
			//String result = EpayDsfAPI.parseXmlRespCode(resp);
			//System.out.println("result"+result);
			return resp;
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	public static String initXml(EpayBankpayRequest request) throws Exception{
		//加载配置
		if(!EpayDsfAPIConfig.loadConfig()){
			return "";
		}
		
		String merNo = request.getMerNo();//EpayDsfAPIConfig.merchantNo;
		String trackNo = request.getTrackNo();//EpayDsfAPI.getSeqNo();
		String tranCode = request.getTranCode();//"8009";//"8010";
		
		//付款方
		String payCardNo = request.getPayCardNo();//"0103000300621673";//"0103000005016811";
		String payBankAccNo = request.getBankAccNo();//"";	//银行代扣支付时
		String payType = request.getPayType();//"1";		
		String payWay = request.getPayWay();//"1";		//余额支付
		//String payPass = "123321";
		//payPass = SecurityHelper.getMd5Hex(payPass.getBytes());
		//支付密码加密
		//String payPassEncrypt = EpayDsfAPI.desEncrypt(payPass);
		
		//收方
		//String bankCode = "102";
		String bankAccNo = request.getRecCardNo();//"1234567812340015";//"6217002870001407699";	//收款银行账号
		//String bankAccName = "孙四孔";				//收款方开户名
		//String bankAccType = "00";					
		//String bankAccProp = "0";
		//String mobNo = "15890901107";
		
		//String toFlag = "";
		String orderNo = trackNo;
		String amount = request.getAmount();//"1000";	//单位:分
		String tradeDate = request.getTradeDate();//DateUtil.DateToString(new Date(), "yyyyMMddHHmmss");//"20121212";
		String summary = request.getSummary();//"银行授权收款"; //"转账到银行"
	
//		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
//			+ "<payToBank>"
//			+ "<header>"
//			+ "<tranCode>" + tranCode + "</tranCode>"
//			+ "<termNo>" + EpayDsfAPIConfig.terminalNo + "</termNo>"
//			+ "<merNo>" + merNo + "</merNo>"
//			+ "<trackNo>" + trackNo + "</trackNo>"
//			+ "<reqTime>" + EpayDsfAPI.sdf.format(new Date()) + "</reqTime>"
//			+ "<sign></sign>"
//			+ "</header>"
//			+ "<dataBody>"
//			+ "<payCardNo>" + payCardNo + "</payCardNo>"
//			+ "<payBankAccNo>" + payBankAccNo + "</payBankAccNo>"
//			+ "<payPass>" + payPassEncrypt + "</payPass>"
//			+ "<payWay>" + payWay + "</payWay>"
//			+ "<payType>" + payType + "</payType>"
//			+ "<toFlag>" + toFlag + "</toFlag>"
//			+ "<orderNo>" + orderNo + "</orderNo>"
//			+ "<amount>" + amount + "</amount>"
//			+ "<tradeDate>" + tradeDate + "</tradeDate>"
//			+ "<bankCode>" + bankCode + "</bankCode>"
//			+ "<bankAccType>" + bankAccType + "</bankAccType>"
//			+ "<bankAccProp>" + bankAccProp + "</bankAccProp>"
//			+ "<bankAccNo>" + bankAccNo + "</bankAccNo>"
//			+ "<bankAccName>" + bankAccName + "</bankAccName>"
//			+ "<mobNo>" + mobNo + "</mobNo>"
//			+ "<summary>" + summary + "</summary>"
//			+ "</dataBody>"
//			+ "</payToBank>";
//		String sign = EpayDsfAPI.caculateSign(xml);
//		xml = xml.replaceAll("<sign>[0-9a-zA-Z]*</sign>|<sign\\s*/>", 
//				"<sign>" + sign + "</sign>");
		
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
					+ "<authCharge>"
					+ "<header>"
					+ "<tranCode>" +tranCode+ "</tranCode>"
					+ "<termNo>" 
					+ request.getTermNo()//EpayDsfAPIConfig.terminalNo
					+ "</termNo>"
					+ "<merNo>"+merNo+"</merNo>"
					+ "<trackNo>"+trackNo+"</trackNo>"
					+ "<reqTime>"
					+ request.getReqTime()//EpayDsfAPI.sdf.format(new Date())
					+"</reqTime>"
					+ "<sign></sign>"
					+ "</header>"
					+ "<dataBody>"
					+ "<payCardNo>"+ payCardNo +"</payCardNo>"
					+ "<bankAccNo>"+ payBankAccNo +"</bankAccNo>"
					+ "<recCardNo>"+ bankAccNo +"</recCardNo>"
					+ "<orderNo>"+ orderNo +"</orderNo>"
					+ "<amount>"+ amount +"</amount>"
					+ "<tradeDate>" + tradeDate + "</tradeDate>"
					+ "<payWay>" + payWay + "</payWay>"
					+ "<payType>" + payType + "</payType>"
					+ "<summary>" + summary + "</summary>"
					+ "</dataBody>"
					+ "</authCharge>";
		return xml;
	}
	
	public static String initSign(String xml) throws Exception {
		String param = xml.replaceAll("<sign>[0-9a-zA-Z]*</sign>|<sign\\s*/>", 
				"") + MD5KEY + MD5_TERM_PASSWORD;
		logger.info("EpayBankUtil md5参数:"+param);
		//<?xml version="1.0" encoding="UTF-8"?><authCharge><header><tranCode>8009</tranCode><termNo>36207414</termNo><merNo>YQGZYX001</merNo><trackNo>1309261833269940</trackNo><reqTime>20130926183326</reqTime><sign></sign></header><dataBody><payCardNo>0103000453789683</payCardNo><bankAccNo></bankAccNo><recCardNo>0103000453789683</recCardNo><orderNo>1309261833269940</orderNo><amount>100</amount><tradeDate>20130926</tradeDate><payWay>1</payWay><payType>1</payType><summary>fgsdfg</summary></dataBody></authCharge>33kkdkd33333dk869590602177
		//<?xml version="1.0" encoding="UTF-8"?><authCharge><header><tranCode>8009</tranCode><termNo>36207414</termNo><merNo>YQGZYX001</merNo><trackNo>1309261845373430</trackNo><reqTime>20130926184537</reqTime></header><dataBody><payCardNo>0103000453789683</payCardNo><bankAccNo></bankAccNo><recCardNo>0103000453789683</recCardNo><orderNo>1309261845373430</orderNo><amount>100</amount><tradeDate>20130926</tradeDate><payWay>1</payWay><payType>1</payType><summary>sadfasdf</summary></dataBody></authCharge>33kkdkd33333dk869590602177
		String sign = MD5Ex.getMD5Str(param).toUpperCase();//EpayDsfAPI.caculateSign(xml);
		return sign;
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
    public static EpayBankpayResponse getEpayBankpayResponse(EpayBankpayResponse response, String xml){
    	//SAXReader sr=new SAXReader();
		Document document;
		Element re;
		//Element username = null;
		//Element account = null;
		try {
			document=DocumentHelper.parseText(xml);
			logger.info("生成xml字符串:"+document.asXML());
			re=document.getRootElement();
			Map map = new HashMap(); 
			map = getElementList(map,re);
			return EpayBankpayResponse.init(response,map,xml);
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
	
	public static void main(String[] args) throws Exception{
//		String resp = authCharge();
//		EpayBankpayResponse response = getEpayBankpayResponse(resp);
//		System.out.println(response.getTranCode());
//		System.out.println(response.getRespCode());
		
	}
}
