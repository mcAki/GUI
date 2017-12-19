package com.sys.volunteer.ka;

import java.util.ArrayList;
import java.util.List;

import com.sys.volunteer.common.CryptUtil3DES;
import com.sys.volunteer.pojo.CardInfo;

/**
 * 91KA接收返回订单信息
 * @author admin
 *
 */
public class Order91KA {

	/**
	 * 返回码 0.成功;其他失败
	 */
	private int retCode;
	/**
	 * 错误描述 retCode为0,显示ok
	 */
	private String errMsg;
	/**
	 * 商户id
	 */
	private String cpid;
	/**
	 * 商户商品id
	 */
	private String cpCardId;
	/**
	 * 商户订单号
	 */
	private String cpOrderNo;
	/**
	 * 91ka订单号
	 */
	private String orderNo;
	/**
	 * 数量
	 */
	private int cardNum;
	/**
	 * 卡密信息(卡号,密码,有效期|卡号,密码,有效期|...)
	 */
	private String cardInfo;
	/**
	 * 回调参数
	 */
	private String retPara;
	/**
	 * 时间戳
	 */
	private long time;
	/**
	 * 验证码
	 */
	private String sign;
	
	/**
	 * 对应card_info解码列表
	 */
	private List<CardInfo> infos;
	
	public int getRetCode() {
		return retCode;
	}
	
	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}
	
	public String getErrMsg() {
		return errMsg;
	}
	
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	public String getCpid() {
		return cpid;
	}
	
	public void setCpid(String cpid) {
		this.cpid = cpid;
	}
	
	public String getCpCardId() {
		return cpCardId;
	}
	
	public void setCpCardId(String cpCardId) {
		this.cpCardId = cpCardId;
	}
	
	public String getCpOrderNo() {
		return cpOrderNo;
	}
	
	public void setCpOrderNo(String cpOrderNo) {
		this.cpOrderNo = cpOrderNo;
	}
	
	public String getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public int getCardNum() {
		return cardNum;
	}
	
	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}
	
	public String getCardInfo() {
		return cardInfo;
	}
	
	public void setCardInfo(String cardInfo) {
		this.cardInfo = cardInfo;
	}
	
	public String getRetPara() {
		return retPara;
	}
	
	public void setRetPara(String retPara) {
		this.retPara = retPara;
	}
	
	public long getTime() {
		return time;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
	public String getSign() {
		return sign;
	}
	
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public List<CardInfo> getInfos() {
		return infos;
	}

	public void setInfos(List<CardInfo> infos) {
		this.infos = infos;
	}
	
	/**
	 * 根据响应参数初始化order91ka
	 * @param arg
	 * @return
	 */
	public Order91KA init(String arg){
		if (arg==null||arg.equals("")) {
			return null;
		}
		infos = new ArrayList<CardInfo>();
		String[] paras = arg.split("&");
		for (int i = 0; i < paras.length; i++) {
			String[] childParas = paras[i].split("=");
			String key = childParas[0];
			String value = "";
			if (childParas.length==2) {
				value = childParas[1];
			}
			parseType(this, key, value);
		}
		//把card_info解码,放到对应的List
		String desedCardInfo = this.getCardInfo();
		CardInfo cardInfo = null;
		if (desedCardInfo!=null&&!desedCardInfo.equals("")) {
			String allInfo = CryptUtil3DES.decrypt(desedCardInfo);
			if (allInfo.contains("|")) {
				String[] cardInfos = allInfo.split("|");
				if (cardInfos.length>0) {
					for (int i = 0; i < cardInfos.length; i++) {
						String[] cards = cardInfos[i].split(",");
						String cardCode = cards[0];
						String password = cards[1];
						long time = Long.valueOf(cards[2])*1000;
						cardInfo = new CardInfo(cardCode, password, time);
					}
				}
			}else {
				String[] cardInfos = allInfo.split(",");
				for (int i = 0; i < cardInfos.length; i++) {
					String cardCode = cardInfos[0];
					String password = cardInfos[1];
					long time = Long.valueOf(cardInfos[2])*1000;
					cardInfo = new CardInfo(cardCode, password, time);
				}
			}
			infos.add(cardInfo);
		}
		return this;
	}
	
	/**
	 * 转化对应类型
	 * @param order91ka
	 * @param key
	 * @param value
	 * @return
	 */
	public Order91KA parseType(Order91KA order91ka,String key,String value){
		if (key.equals("ret_code")) {
			order91ka.setRetCode(Integer.valueOf(value));
		}
		if (key.equals("err_msg")) {
			order91ka.setErrMsg(value);
		}
		if (key.equals("cpid")) {
			order91ka.setCpid(value);
		}
		if (key.equals("cp_card_id")) {
			order91ka.setCpCardId(value);
		}
		if (key.equals("cp_order_no")) {
			order91ka.setCpOrderNo(value);
		}
		if (key.equals("order_no")) {
			order91ka.setOrderNo(value);
		}
		if (key.equals("card_num")&&!value.equals("")) {
			order91ka.setCardNum(Integer.valueOf(value));
		}
		if (key.equals("card_info")) {
			order91ka.setCardInfo(value);
		}
		if (key.equals("ret_para")) {
			order91ka.setRetPara(value);
		}
		if (key.equals("time")&&!value.equals("")) {
			order91ka.setTime(Long.valueOf(value));
		}
		if (key.equals("sign")) {
			order91ka.setSign(value);
		}
		return order91ka;
	}

	
}
