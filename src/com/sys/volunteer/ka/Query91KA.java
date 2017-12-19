package com.sys.volunteer.ka;


/**
 * 91KA接收查询卡密返回信息
 * @author admin
 *
 */
public class Query91KA {

	/**
	 * 返回码,0为成功
	 */
	private int retCode;
	/**
	 * 错误描述
	 */
	private String errMsg;
	/**
	 * 商户id
	 */
	private String cpid;
	/**
	 * 卡密id(对应商城)
	 */
	private String cpCardId;
	/**
	 * 价格
	 */
	private int price;
	/**
	 * 库存
	 */
	private int leftCardNum;
	/**
	 * 参数
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
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getLeftCardNum() {
		return leftCardNum;
	}
	
	public void setLeftCardNum(int leftCardNum) {
		this.leftCardNum = leftCardNum;
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
	
	/**
	 * 初始化
	 * @param arg
	 * @return
	 */
	public Query91KA init(String arg){
		if (arg==null||arg.equals("")) {
			return null;
		}
		String[] params = arg.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] childParas = params[i].split("=");
			String key = childParas[0];
			String value = "";
			if (childParas.length==2) {
				value = childParas[1];
			}
			this.parseType(this, key, value);
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
	public Query91KA parseType(Query91KA query91ka,String key,String value){
		if (key.equals("ret_code")) {
			query91ka.setRetCode(Integer.valueOf(value));
		}
		if (key.equals("err_msg")) {
			query91ka.setErrMsg(value);
		}
		if (key.equals("cpid")) {
			query91ka.setCpid(value);
		}
		if (key.equals("cp_card_id")) {
			query91ka.setCpCardId(value);
		}
		if (key.equals("price")&&!value.equals("")) {
			query91ka.setPrice(Integer.valueOf(value));
		}
		if (key.equals("left_card_num")&&!value.equals("")) {
			query91ka.setLeftCardNum(Integer.valueOf(value));
		}
		if (key.equals("ret_para")) {
			query91ka.setRetPara(value);
		}
		if (key.equals("time")&&!value.equals("")) {
			query91ka.setTime(Long.valueOf(value));
		}
		if (key.equals("sign")) {
			query91ka.setSign(value);
		}
		return query91ka;
	}
}

