package com.sys.volunteer.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sys.volunteer.common.KAUtil;
import com.sys.volunteer.common.MD5;

/**
 * MissionType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "91ka_order_request", catalog = "mprs")
public class GameRechargeRequest {

	protected static final Log logger = LogFactory.getLog(GameRechargeRequest.class);
	
	private Integer orderRequestId;
	private String cpid;
	private String gameId;
	private String autogameId;
	private String createTime;
	private String version;
	private String cpOrderNo;
	private String amount;
	private String at;
	private String atVerify;
	private String retPara;
	private String clientIp;
	private String sign;
	private String orderId;
	
	
	
	public GameRechargeRequest() {
		
	}

	/**
	 * 默认构造器
	 * @param cpid
	 * @param gameId
	 * @param autogameId
	 * @param createTime
	 * @param version
	 * @param cpOrderNo
	 * @param amount
	 * @param at
	 * @param atVerify
	 * @param retPara
	 * @param clientIp
	 */
	public GameRechargeRequest(String cpid, String gameId, String autogameId,
			String createTime, String version, String cpOrderNo, String amount,
			String at, String atVerify, String retPara, String clientIp) {
		this.cpid = cpid;
		this.gameId = gameId;
		this.autogameId = autogameId;
		this.createTime = createTime;
		this.version = version;
		this.cpOrderNo = cpOrderNo;
		this.amount = amount;
		this.at = at;
		this.atVerify = atVerify;
		this.retPara = retPara;
		this.clientIp = clientIp;
		StringBuffer sb = new StringBuffer();
		sb.append("cpid="+cpid+"&game_id="+gameId+"&autogame_id="+autogameId+"&create_time="+createTime+
				"&version="+version+"&cp_order_no="+cpOrderNo+"&amount="+amount+"&"+at+
				"ret_para="+retPara+"&client_ip="+clientIp);
		String param = sb.toString();
		logger.info(param+KAUtil.TESTMD5KEY);
		MD5 md5=MD5.getiInstance();
		String sign=md5.getMD5ofStr(param+KAUtil.TESTMD5KEY);
		logger.info(sign);
		param=param+"&sign="+sign;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "order_request_id", unique = true, nullable = false)
	public Integer getOrderRequestId() {
		return orderRequestId;
	}
	
	public void setOrderRequestId(Integer orderRequestId) {
		this.orderRequestId = orderRequestId;
	}
	
	@Column(name="cpid")
	public String getCpid() {
		return cpid;
	}
	
	public void setCpid(String cpid) {
		this.cpid = cpid;
	}
	
	@Column(name="game_id")
	public String getGameId() {
		return gameId;
	}
	
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	
	@Column(name="create_time")
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="cp_order_no")
	public String getCpOrderNo() {
		return cpOrderNo;
	}
	
	public void setCpOrderNo(String cpOrderNo) {
		this.cpOrderNo = cpOrderNo;
	}
	
	@Column(name="ret_para")
	public String getRetPara() {
		return retPara;
	}
	
	public void setRetPara(String retPara) {
		this.retPara = retPara;
	}
	
	@Column(name="sign")
	public String getSign() {
		return sign;
	}
	
	public void setSign(String sign) {
		this.sign = sign;
	}

	@Column(name="autogame_id")
	public String getAutogameId() {
		return autogameId;
	}

	public void setAutogameId(String autogameId) {
		this.autogameId = autogameId;
	}

	@Column(name="version")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name="amount")
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Column(name="at")
	public String getAt() {
		return at;
	}

	public void setAt(String at) {
		this.at = at;
	}

	@Column(name="at_verify")
	public String getAtVerify() {
		return atVerify;
	}

	public void setAtVerify(String atVerify) {
		this.atVerify = atVerify;
	}

	@Column(name="client_ip")
	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	@Column(name="order_id")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
}
