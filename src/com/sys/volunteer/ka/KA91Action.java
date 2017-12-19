package com.sys.volunteer.ka;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.common.KAUtil;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.SupplyInterface;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class KA91Action extends BaseAction {

	protected static final Log logger = LogFactory.getLog(KA91Action.class);
	
	/**
	 * 返回地址
	 */
	private String returnUrl;
	
	private Integer ret_code;
	private String err_msg;
	private String cpid;
	private String cp_card_id;
	private Integer price;
	private Integer left_card_num;
	private String ret_para;
	private Long time;
	private String sign;
	
	private String formUrl;
	private String JsUrl;
	
	private Query91KAProduct queryProduct;
	
	private String gameId;
	private String autoGameId;
	private Integer kaBaseNum;
	
	/**
	 * 测试查询接口
	 * @return
	 */
	public String testQueryInventory() throws Exception{
//		returnUrl=KAUtil.testQueryInventory();
//		System.out.println(returnUrl);
		queryProduct = new Query91KAProduct();
		queryProduct.init(KAUtil.queryProduct("2030"));
		System.out.println(queryProduct.getAutogameId());
		autoGameId = queryProduct.getAutogameId();
		gameId = queryProduct.getGameId();
		formUrl = KAUtil.askForm(autoGameId);
		JsUrl = KAUtil.askJS(autoGameId);
		kaBaseNum = 1;
		return "test";
//		return SUCCESS;
	}
	
	public String testRecharge() throws Exception {
		Map<String, String[]> paraMap = getHttpServletRequest().getParameterMap();
		Iterator iterator = paraMap.entrySet().iterator();
		int goodsNo = 0;
		String mobile = "";
		String at = "";
		while (iterator.hasNext()) {
			Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) iterator.next();
			String key = entry.getKey();
			String keySub = key.substring(0,3);
			if (keySub.equals("at_")) {
				at += key+"="+entry.getValue()[0]+"&";
			}
			if (key.equals("at_num")) {
				goodsNo = Integer.valueOf(entry.getValue()[0]);
			}
			if (key.equals("at_ele1")) {
				mobile = entry.getValue()[0];
			}
		}
		System.out.println("at:-----------"+at);
		System.out.println("goodsNo:--------------"+goodsNo);
		System.out.println("qq:-----------"+mobile);
		//Query91KAProduct queryProduct = new Query91KAProduct();
		//queryProduct.init(KAUtil.queryProduct("2888"));
		//logger.info(queryProduct.getAutogameId());
		//Mainorder mainorder = new Mainorder();
		//mainorder.setMainOrderId("32165464949494");
		//SupplyInterface supplyInterface = new SupplyInterface();
		//supplyInterface.setStockPrice(1.0d);
		//Recharge91KAGame recharge91kaGame = new Recharge91KAGame();
		//recharge91kaGame.init(KAUtil.gameRecharge(mainorder, supplyInterface, gameId, autoGameId, at));
		return ShowMessage(MSG_TYPE_NORMAL, "", "", "", 0);
	}
	
	
	/**
	 * 本地调用响应
	 * @return
	 */
	public String queryInventory(){
		System.out.println(cpid);
		return SUCCESS;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public Integer getRet_code() {
		return ret_code;
	}

	public void setRet_code(Integer retCode) {
		ret_code = retCode;
	}

	public String getErr_msg() {
		return err_msg;
	}

	public void setErr_msg(String errMsg) {
		err_msg = errMsg;
	}

	public String getCpid() {
		return cpid;
	}

	public void setCpid(String cpid) {
		this.cpid = cpid;
	}

	public String getCp_card_id() {
		return cp_card_id;
	}

	public void setCp_card_id(String cpCardId) {
		cp_card_id = cpCardId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getLeft_card_num() {
		return left_card_num;
	}

	public void setLeft_card_num(Integer leftCardNum) {
		left_card_num = leftCardNum;
	}

	public String getRet_para() {
		return ret_para;
	}

	public void setRet_para(String retPara) {
		ret_para = retPara;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getFormUrl() {
		return formUrl;
	}

	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}

	public String getJsUrl() {
		return JsUrl;
	}

	public void setJsUrl(String jsUrl) {
		JsUrl = jsUrl;
	}

	public Query91KAProduct getQueryProduct() {
		return queryProduct;
	}

	public void setQueryProduct(Query91KAProduct queryProduct) {
		this.queryProduct = queryProduct;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getAutoGameId() {
		return autoGameId;
	}

	public void setAutoGameId(String autoGameId) {
		this.autoGameId = autoGameId;
	}

	public Integer getKaBaseNum() {
		return kaBaseNum;
	}

	public void setKaBaseNum(Integer kaBaseNum) {
		this.kaBaseNum = kaBaseNum;
	}
}
