package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "91ka_order_query", catalog = "mprs")
public class Query91KAOrder {

	private String queryId;
	private String cpid;
	private String cpOrderNo;
	private String KAorderNo;
	private String retResult;
	private String sign;
	private String orderId;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "query_id", unique = true, nullable = false)
	public String getQueryId() {
		return queryId;
	}
	
	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}
	
	@Column(name="cpid")
	public String getCpid() {
		return cpid;
	}
	
	public void setCpid(String cpid) {
		this.cpid = cpid;
	}
	
	@Column(name="cp_order_no")
	public String getCpOrderNo() {
		return cpOrderNo;
	}
	
	public void setCpOrderNo(String cpOrderNo) {
		this.cpOrderNo = cpOrderNo;
	}
	
	@Column(name="91ka_order_no")
	public String getKAorderNo() {
		return KAorderNo;
	}
	
	public void setKAorderNo(String kAorderNo) {
		KAorderNo = kAorderNo;
	}
	
	@Column(name="ret_result")
	public String getRetResult() {
		return retResult;
	}
	
	public void setRetResult(String retResult) {
		this.retResult = retResult;
	}
	
	@Column(name="sign")
	public String getSign() {
		return sign;
	}
	
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	@Column(name="order_id")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
	/**
	 * 初始化
	 * @param arg
	 * @return
	 */
	public Query91KAOrder init(String arg){
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
	public Query91KAOrder parseType(Query91KAOrder queryOrder,String key,String value){
		if (key.equals("cpid")) {
			queryOrder.setCpid(value);
		}
		if (key.equals("cp_order_no")) {
			queryOrder.setCpOrderNo(value);
		}
		if (key.equals("ret_result")) {
			queryOrder.setRetResult(value);
		}
		if (key.equals("91ka_order_no")) {
			queryOrder.setKAorderNo(value);
		}
		if (key.equals("sign")) {
			queryOrder.setSign(value);
		}
		return queryOrder;
	}
	
}
