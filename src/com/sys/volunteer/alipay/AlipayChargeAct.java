/**
 * 
 */
package com.sys.volunteer.alipay;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.common.AlipayUtil;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.SysUtil;
import com.sys.volunteer.pojo.Alipay;
import com.sys.volunteer.pojo.AlipayBack;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.usercharge.UserChargeEngine;
import com.sys.volunteer.usercharge.engine.ICharge;
import com.sys.volunteer.usercharge.engine.charge.Charge4User;
import com.sys.volunteer.users.UserService;

/**
 * @author hanwen 支付宝充值
 * 
 * @version 创建时间：2012-12-28 下午04:41:45
 */
@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
public class AlipayChargeAct extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	public AlipayService alipayService;
	@Resource
	UseraccountService useraccountService;
	@Resource
	UserService userService;

	String productName;
	String total_fee;
	String mobile;
	HttpServletRequest request;
	String defaultBank;
	String sign;
	String returnUrl;

	String out_trade_no;
	String trade_status;
	String notify_id;
	
	private Integer alipayId;
	
	private String returnResult;

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getDefaultBank() {
		return defaultBank;
	}

	public void setDefaultBank(String defaultBank) {
		this.defaultBank = defaultBank;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String alipayPage() {
		return "success";
	}

	/**
	 * 
	 * @param productName
	 *            商品名称
	 * @param total_fee
	 *            交易金额
	 * @return
	 */
	public String alipayCharge() {
		
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("alipay.properties");   
		  Properties p = new Properties();
		  try {
			p.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String notify_url = p.getProperty("notify_url");
		String return_url = p.getProperty("return_url");
	
		if (Integer.parseInt(total_fee) < 500) {
			if (Integer.parseInt(total_fee) != 1) {
				return ShowMessage(MSG_TYPE_WARNING, "充值金额不能小于500", "点击返回",
						"alipayPage.do", 3);
			}
		}
//		HttpServletResponse response = this.getHttpServletResponse();
		Alipay alipay = new Alipay();
		Users user = getSessionUser();
		// if(!(total_fee.matches("\\d+"))){
		// ResponseUtils.renderText(response, "error#金额输入有误");
		// return null;
		// }
		String subject2 = user.getUserId()+"chongzhi";
		String out_trade_no2 = SysUtil.getUUID();
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayChargeConfig.service);
		sParaTemp.put("partner", AlipayChargeConfig.partner);
		sParaTemp.put("_input_charset", AlipayChargeConfig._input_charset);
		sParaTemp.put("key", AlipayChargeConfig.key);
		sParaTemp.put("payment_type", AlipayChargeConfig.payment_type);
		sParaTemp.put("notify_url", notify_url);
		log.info("支付宝notify_url:"+notify_url);
		sParaTemp.put("return_url", return_url);
		log.info("支付宝return_url:"+return_url);
		sParaTemp.put("seller_email", AlipayChargeConfig.seller_email);
		sParaTemp.put("out_trade_no", out_trade_no2);
		sParaTemp.put("subject", subject2);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", AlipayChargeConfig.body);
		sParaTemp.put("paymethod", AlipayChargeConfig.paymethod);
		sParaTemp.put("defaultbank", defaultBank);
		sParaTemp.put("show_url", AlipayChargeConfig.show_url);
		Map<String, String> sPara = AlipayUtil.buildRequestPara(sParaTemp);
//		this.getSession().put("mysign", sPara.get("sign"));
		String sign_result = AlipayUtil.createLinkString(sPara);
		log.info(AlipayChargeConfig.ALIPAY_GATEWAY_NEW + sign_result);
		alipay.setTotalFee(total_fee);
		alipay.setTradeNo(out_trade_no2);
		alipay.setUserId(user.getUserId());
		alipay.setUserName(user.getUserName());
		alipay.setIsIntoaccount(0);
		alipayService.addAlipay(alipay);
		returnUrl = AlipayChargeConfig.ALIPAY_GATEWAY_NEW + sign_result;
		// try {
		// response.sendRedirect(AlipayChargeConfig.ALIPAY_GATEWAY_NEW+sign_result);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		return SUCCESS;
	}

	/*************************** 以下为支付宝返回处理 ***************************************/

	public String AlipayChargeBack() throws Exception {
//		log.info("进入支付宝返回处理方法------->AlipayChargeBack");
		// 获取支付宝GET过来反馈信息
		// Map<String,String> params = new HashMap<String,String>();
		// Map requestParams = request.getParameterMap();
		// for (Iterator iter = requestParams.keySet().iterator();
		// iter.hasNext();) {
		// String name = (String) iter.next();
		// String[] values = (String[]) requestParams.get(name);
		// String valueStr = "";
		// for (int i = 0; i < values.length; i++) {
		// valueStr = (i == values.length - 1) ? valueStr + values[i]
		// : valueStr + values[i] + ",";
		// }
		// //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
		// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
		// params.put(name, valueStr);
		// }

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//

		// 商户订单号
		// String out_trade_no = new
		// String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		// 支付宝交易号
		// String trade_no = new
		// String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		// 交易状态
		// String trade_status = new
		// String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

		String mysign = (String) this.getSession().get("mysign");
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表
//        AlipayBack alipayBack1 = new AlipayBack(); 
        double recharge = 0;
		boolean verify_result = false;
		String responseTxt = "true";
		if (notify_id != null) {
			responseTxt = AlipayUtil.verifyResponse(notify_id);
		}
		if (responseTxt.equals("true")) {
			verify_result = true;
		}

		Alipay alipay = alipayService.findByTradeNo(out_trade_no);
//		Users users = userService.getAdminUser();
//		Users currentUser = userService.findUserById(alipay.getUserId());
		if (verify_result) {// 验证成功

			if (trade_status.equals("TRADE_FINISHED")
					|| trade_status.equals("TRADE_SUCCESS")) {

//				
//				System.out.println("充值返回成功,现在向用户账户充值");
//				//int isIntoaccount = alipay.getIsIntoaccount();
				 recharge = Double.parseDouble(alipay.getTotalFee());
//				AlipayBack alipayBack = alipayService.findByTradeNoBack(out_trade_no);
//				if(alipayBack!=null){
//					if(alipayBack.getStatus().equals("1")&&alipayBack.getIsIntoaccount()==1){
//						return ShowMessage(MSG_TYPE_STOP, "成功支付" + recharge + "元,请查收",
//								"点击返回系统", getHttpServletRequest().getContextPath()
//								+ "/frame!main_front.html", 0);
//					}else{
//						return ShowMessage(MSG_TYPE_STOP, "验证失败，请联系管理员；", "点击返回系统",
//								getHttpServletRequest().getContextPath()
//										+ "/frame!main_front.html", 0);
//					}
//				}
//				UserCharge ud = new UserCharge();
//				ud.setVoucherType(6);
//				ud.setVoucherCode(out_trade_no);
//				UserCharge ud2 = new UserCharge();
//				ud2.setVoucherType(6);
//				ud2.setVoucherCode(out_trade_no);
//				UserCharge ud3 = new UserCharge();
//				ud3.setVoucherType(6);
//				ud3.setVoucherCode(out_trade_no);
//				UserCharge ud4 = new UserCharge();
//				ud4.setVoucherType(6);
//				ud4.setVoucherCode(out_trade_no);
//
//				UserCharge ud5 = new UserCharge();
//				ud5.setVoucherType(6);
//				ud5.setVoucherCode(out_trade_no);
//				UserCharge ud6 = new UserCharge();
//				ud6.setVoucherType(6);
//				ud6.setVoucherCode(out_trade_no);
//				//    				
//				// useraccountService.refreshAccountes(null, users, ud,
//				// recharge,
//				// Const.UseraccountdealdetailFlag.USER_DEPOSIT);
//				// // 如果一级商户只有一个下属,则这笔划款自动转向二级商户
//				// List<Users> children = userService.findChildUsers(users
//				// .getUserId());
//				// if (children.size() == 1) {
//				// Users child = children.get(0);
//				// useraccountService.refreshAccountes(null, users, ud,
//				// recharge, Const.UseraccountdealdetailFlag.REMIT);
//				// // 下级账户充值
//				// Useraccountdealdetail ud2 = new Useraccountdealdetail();
//				// ud2.setVoucherType(6);
//				// ud2.setVoucherCode(out_trade_no);
//				// users = userService.findUserById(child.getUserId());
//				// useraccountService.refreshAccountes(null, child, ud2,
//				// recharge,
//				// Const.UseraccountdealdetailFlag.USER_DEPOSIT);
//				// System.out.println("充值完成!");
//				// }
//				// //管理员自动提款
//				// Useraccountdealdetail ud3=new Useraccountdealdetail();
//				// Users admin = userService.getAdminUser();
//				// ud3.setVoucherType(6);
//				// ud3.setVoucherCode(out_trade_no);
//				// useraccountService.refreshAccountes(null,admin,ud3,
//				// recharge,Const.UseraccountdealdetailFlag.DRAWN);
//				// 管理员--一级商户--二级商户
//				if (currentUser.getUsergroup().getId() == Const.Usergroup.GRADE_TWO) {
//					Users parentUser = userService.findUserById(currentUser
//							.getParentUsers().getUserId());
//
//					// 管理员--一级商户
//					ICharge iCharge = new Charge4User(ud, null, recharge,
//							users, parentUser, getSessionUser().getUserName(),
//							Const.UseraccountdealdetailFlag.REMIT);
//					// useraccountService.refreshAccountes(null,users,parentUser,getSessionUser().getUserName(),ud,
//					// recharge,
//					// Const.UseraccountdealdetailFlag.REMIT);
//					ICharge iCharge2 = new Charge4User(ud2, null, recharge,
//							parentUser, users, getSessionUser().getUserName(),
//							Const.UseraccountdealdetailFlag.USER_DEPOSIT);
//					// useraccountService.refreshAccountes(null,parentUser,users,getSessionUser().getUserName(),ud2,recharge,
//					// Const.UseraccountdealdetailFlag.USER_DEPOSIT);
//
//					// 一级商户--二级商户
//					ICharge iCharge3 = new Charge4User(ud3, null, recharge,
//							parentUser, currentUser, getSessionUser()
//									.getUserName(),
//							Const.UseraccountdealdetailFlag.REMIT);
//					// useraccountService.refreshAccountes(null,parentUser,currentUser,getSessionUser().getUserName(),
//					// ud3, recharge,
//					// Const.UseraccountdealdetailFlag.REMIT);
//					ICharge iCharge4 = new Charge4User(ud4, null, recharge,
//							currentUser, parentUser, getSessionUser()
//									.getUserName(),
//							Const.UseraccountdealdetailFlag.USER_DEPOSIT);
//					// useraccountService.refreshAccountes(null,currentUser,parentUser,getSessionUser().getUserName(),ud4,recharge,
//					// Const.UseraccountdealdetailFlag.USER_DEPOSIT);
//					UserChargeEngine.getInstance().addLast(iCharge);
//					UserChargeEngine.getInstance().addLast(iCharge2);
//					UserChargeEngine.getInstance().addLast(iCharge3);
//					UserChargeEngine.getInstance().addLast(iCharge4);
//					if (recharge < 2000) {
//						ICharge iCharge5 = new Charge4User(ud5, null, 1.0,
//								currentUser, users, getSessionUser()
//										.getUserName(),
//								Const.UseraccountdealdetailFlag.CROSS_CLAIM);
//						// useraccountService.refreshAccountes(null,currentUser,users,getSessionUser().getUserName(),ud5,1.0,
//						// Const.UseraccountdealdetailFlag.CROSS_CLAIM);
//						ICharge iCharge6 = new Charge4User(ud6, null, 1.0,
//								users, currentUser, getSessionUser()
//										.getUserName(),
//								Const.UseraccountdealdetailFlag.PLUS_CLAIM);
//						// useraccountService.refreshAccountes(null,users,currentUser,getSessionUser().getUserName(),ud6,1.0,
//						// Const.UseraccountdealdetailFlag.PLUS_CLAIM);
//						UserChargeEngine.getInstance().addLast(iCharge5);
//						UserChargeEngine.getInstance().addLast(iCharge6);
//
//					}
//				} else {
//					ICharge iCharge = new Charge4User(ud, null, recharge,
//							users, currentUser, getSessionUser().getUserName(),
//							Const.UseraccountdealdetailFlag.REMIT);
//					// useraccountService.refreshAccountes(null,currentUser,users,getSessionUser().getUserName(),ud,
//					// recharge,
//					// Const.UseraccountdealdetailFlag.REMIT);
//					if (recharge < 2000) {
//						ICharge iCharge5 = new Charge4User(ud5, null, 1.0,
//								currentUser, users, getSessionUser()
//										.getUserName(),
//								Const.UseraccountdealdetailFlag.CROSS_CLAIM);
//						// useraccountService.refreshAccountes(null,currentUser,users,getSessionUser().getUserName(),ud5,
//						// 1.0,
//						// Const.UseraccountdealdetailFlag.CROSS_CLAIM);
//						ICharge iCharge6 = new Charge4User(ud6, null, 1.0,
//								users, currentUser, getSessionUser()
//										.getUserName(),
//								Const.UseraccountdealdetailFlag.PLUS_CLAIM);
//						// useraccountService.refreshAccountes(null,users,currentUser,getSessionUser().getUserName(),ud6,1.0,
//						// Const.UseraccountdealdetailFlag.PLUS_CLAIM);
//						UserChargeEngine.getInstance().addLast(iCharge5);
//						UserChargeEngine.getInstance().addLast(iCharge6);
//					}
//					// 下级账户充值
//					// users = userService.findUserById(users.getUserId());
//					ICharge iCharge2 = new Charge4User(ud2, null, recharge,
//							currentUser, users, getSessionUser().getUserName(),
//							Const.UseraccountdealdetailFlag.USER_DEPOSIT);
//					// useraccountService.refreshAccountes(null,users,currentUser,getSessionUser().getUserName(),ud2,recharge,Const.UseraccountdealdetailFlag.USER_DEPOSIT);
//					UserChargeEngine.getInstance().addLast(iCharge);
//					UserChargeEngine.getInstance().addLast(iCharge2);
//					// 如果一级商户只有一个下属,则这笔划款自动转向二级商户
//					List<Users> children = userService.findChildUsers(users
//							.getUserId());
//					if (children.size() == 1) {
//						Users child = children.get(0);
//						ICharge iCharge3 = new Charge4User(ud3, null, recharge,
//								users, currentUser, getSessionUser()
//										.getUserName(),
//								Const.UseraccountdealdetailFlag.REMIT);
//						// useraccountService.refreshAccountes(null,users,currentUser,getSessionUser().getUserName(),ud3,
//						// recharge,
//						// Const.UseraccountdealdetailFlag.REMIT);
//						// 下级账户充值
//						ICharge iCharge4 = new Charge4User(ud4, null, recharge,
//								child, users, getSessionUser().getUserName(),
//								Const.UseraccountdealdetailFlag.USER_DEPOSIT);
//						// useraccountService.refreshAccountes(null,child,users,getSessionUser().getUserName(),ud4,recharge,Const.UseraccountdealdetailFlag.USER_DEPOSIT);
//						UserChargeEngine.getInstance().addLast(iCharge3);
//						UserChargeEngine.getInstance().addLast(iCharge4);
//					}
//				}
//				log.info("支付宝返回信息------>支付成功……");
//				alipayBack1.setTotalFee(recharge+"");
//				alipayBack1.setTradeNo(out_trade_no);
//				alipayBack1.setUserId(users.getUserId());
//				alipayBack1.setUserName(users.getUserName());
//				alipayBack1.setIsIntoaccount(1);
//				alipayBack1.setStatus("1");
//				alipayService.addAlipayBack(alipayBack1);
				return ShowMessage(MSG_TYPE_STOP, "成功支付" + recharge + "元,请查收",
						"点击返回系统", getHttpServletRequest().getContextPath()
								+ "/frame!main_front.html", 0);
			}
//			log.info("支付宝返回处理，验证成功（充值失败）");
//			alipayBack1.setTotalFee(recharge+"");
//			alipayBack1.setTradeNo(out_trade_no);
//			alipayBack1.setUserId(users.getUserId());
//			alipayBack1.setUserName(users.getUserName());
//			alipayBack1.setIsIntoaccount(0);
//			alipayBack1.setStatus("0");
//			alipayService.addAlipayBack(alipayBack1);
//			System.out.println("验证成功");
			return ShowMessage(MSG_TYPE_STOP, "充值失败，", "点击返回系统",
					getHttpServletRequest().getContextPath()
							+ "/frame!main_front.html", 0);
		} else {
//			log.info("支付宝返回处理，验证失败（充值失败）");
//			alipayBack1.setTotalFee(recharge+"");
//			alipayBack1.setTradeNo(out_trade_no);
//			alipayBack1.setUserId(users.getUserId());
//			alipayBack1.setUserName(users.getUserName());
//			alipayBack1.setIsIntoaccount(0);
//			alipayBack1.setStatus("0");
//			alipayService.addAlipayBack(alipayBack1);
			return ShowMessage(MSG_TYPE_STOP, "充值失败，请联系管理员；", "点击返回系统",
					getHttpServletRequest().getContextPath()
							+ "/frame!main_front.html", 0);
		}
	}

	/**
	 * 异步处理支付宝返回数据
	 */
	public String alipayNotifyDispose(){
		AlipayBack alipayBack1 = new AlipayBack(); 
        double recharge = 0;
		boolean verify_result = false;
		String responseTxt = "true";
		
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = this.getHttpServletRequest().getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		if (notify_id != null) {
			responseTxt = AlipayUtil.verifyResponse(notify_id);
		}
		//写日志调试
//		String sWord = "responseTxt=" + responseTxt + "\n  返回回来的参数：" + AlipayUtil.createLinkString(params);
//		AlipayUtil.logResult(sWord);
	
		if (responseTxt.equals("true")) {
			verify_result = true;
		}

		log.info("返回支付宝流水号"+out_trade_no);
		Alipay alipay = alipayService.findByTradeNo(out_trade_no);
		if(alipay==null){
			returnResult = "FAILED";
			return "notify";
		}
		Users users = userService.getAdminUser();
		Users currentUser = userService.findUserById(alipay.getUserId());
		
		if (verify_result) {// 验证成功

			if (trade_status.equals("TRADE_SUCCESS") && alipay.getIsIntoaccount()==0) {
				log.info("充值返回成功,现在向用户账户充值");
				 recharge = Double.parseDouble(alipay.getTotalFee());
				AlipayBack alipayBack = alipayService.findByTradeNoBack(out_trade_no);
				if(alipayBack!=null){
					returnResult = "FAILED";
					return "notify";
				}
				UserCharge ud = new UserCharge();
				ud.setVoucherType(6);
				ud.setVoucherCode(out_trade_no);
				UserCharge ud2 = new UserCharge();
				ud2.setVoucherType(6);
				ud2.setVoucherCode(out_trade_no);
				UserCharge ud3 = new UserCharge();
				ud3.setVoucherType(6);
				ud3.setVoucherCode(out_trade_no);
				UserCharge ud4 = new UserCharge();
				ud4.setVoucherType(6);
				ud4.setVoucherCode(out_trade_no);

				UserCharge ud5 = new UserCharge();
				ud5.setVoucherType(6);
				ud5.setVoucherCode(out_trade_no);
				UserCharge ud6 = new UserCharge();
				ud6.setVoucherType(6);
				ud6.setVoucherCode(out_trade_no);
				
				// 管理员--一级商户--二级商户
				if (currentUser.getUsergroup().getGroupType() == Const.GroupType.GRADE_TWO) {
					Users parentUser = userService.findUserById(currentUser
							.getParentUsers().getUserId());

					// 管理员--一级商户
					ICharge iCharge = new Charge4User(ud, null, recharge,
							users, parentUser, currentUser.getUserName(),
							Const.UseraccountdealdetailFlag.REMIT);
					// useraccountService.refreshAccountes(null,users,parentUser,currentUser.getUserName(),ud,
					// recharge,
					// Const.UseraccountdealdetailFlag.REMIT);
					ICharge iCharge2 = new Charge4User(ud2, null, recharge,
							parentUser, users, currentUser.getUserName(),
							Const.UseraccountdealdetailFlag.USER_DEPOSIT);
					// useraccountService.refreshAccountes(null,parentUser,users,currentUser.getUserName(),ud2,recharge,
					// Const.UseraccountdealdetailFlag.USER_DEPOSIT);

					// 一级商户--二级商户
					ICharge iCharge3 = new Charge4User(ud3, null, recharge,
							parentUser, currentUser, currentUser
									.getUserName(),
							Const.UseraccountdealdetailFlag.REMIT);
					// useraccountService.refreshAccountes(null,parentUser,currentUser,currentUser.getUserName(),
					// ud3, recharge,
					// Const.UseraccountdealdetailFlag.REMIT);
					ICharge iCharge4 = new Charge4User(ud4, null, recharge,
							currentUser, parentUser, currentUser
									.getUserName(),
							Const.UseraccountdealdetailFlag.USER_DEPOSIT);
					// useraccountService.refreshAccountes(null,currentUser,parentUser,currentUser.getUserName(),ud4,recharge,
					// Const.UseraccountdealdetailFlag.USER_DEPOSIT);
					UserChargeEngine.getInstance().addLast(iCharge);
					UserChargeEngine.getInstance().addLast(iCharge2);
					UserChargeEngine.getInstance().addLast(iCharge3);
					UserChargeEngine.getInstance().addLast(iCharge4);
					if (recharge < 2000) {
						ICharge iCharge5 = new Charge4User(ud5, null, 1.0,
								currentUser, users, currentUser
										.getUserName(),
								Const.UseraccountdealdetailFlag.CROSS_CLAIM);
						// useraccountService.refreshAccountes(null,currentUser,users,currentUser.getUserName(),ud5,1.0,
						// Const.UseraccountdealdetailFlag.CROSS_CLAIM);
						ICharge iCharge6 = new Charge4User(ud6, null, 1.0,
								users, currentUser, currentUser
										.getUserName(),
								Const.UseraccountdealdetailFlag.PLUS_CLAIM);
						// useraccountService.refreshAccountes(null,users,currentUser,currentUser.getUserName(),ud6,1.0,
						// Const.UseraccountdealdetailFlag.PLUS_CLAIM);
						UserChargeEngine.getInstance().addLast(iCharge5);
						UserChargeEngine.getInstance().addLast(iCharge6);

					}
				} else {
					ICharge iCharge = new Charge4User(ud, null, recharge,
							users, currentUser, currentUser.getUserName(),
							Const.UseraccountdealdetailFlag.REMIT);
					// useraccountService.refreshAccountes(null,currentUser,users,currentUser.getUserName(),ud,
					// recharge,
					// Const.UseraccountdealdetailFlag.REMIT);
					if (recharge < 2000) {
						ICharge iCharge5 = new Charge4User(ud5, null, 1.0,
								currentUser, users, currentUser
										.getUserName(),
								Const.UseraccountdealdetailFlag.CROSS_CLAIM);
						// useraccountService.refreshAccountes(null,currentUser,users,currentUser.getUserName(),ud5,
						// 1.0,
						// Const.UseraccountdealdetailFlag.CROSS_CLAIM);
						ICharge iCharge6 = new Charge4User(ud6, null, 1.0,
								users, currentUser, currentUser
										.getUserName(),
								Const.UseraccountdealdetailFlag.PLUS_CLAIM);
						// useraccountService.refreshAccountes(null,users,currentUser,currentUser.getUserName(),ud6,1.0,
						// Const.UseraccountdealdetailFlag.PLUS_CLAIM);
						UserChargeEngine.getInstance().addLast(iCharge5);
						UserChargeEngine.getInstance().addLast(iCharge6);
					}
					// 下级账户充值
					// users = userService.findUserById(users.getUserId());
					ICharge iCharge2 = new Charge4User(ud2, null, recharge,
							currentUser, users, currentUser.getUserName(),
							Const.UseraccountdealdetailFlag.USER_DEPOSIT);
					// useraccountService.refreshAccountes(null,users,currentUser,currentUser.getUserName(),ud2,recharge,Const.UseraccountdealdetailFlag.USER_DEPOSIT);
					UserChargeEngine.getInstance().addLast(iCharge);
					UserChargeEngine.getInstance().addLast(iCharge2);
					// 如果一级商户只有一个下属,则这笔划款自动转向二级商户
					List<Users> children = userService.findChildUsers(users
							.getUserId());
					if (children.size() == 1) {
						Users child = children.get(0);
						ICharge iCharge3 = new Charge4User(ud3, null, recharge,
								users, currentUser, currentUser
										.getUserName(),
								Const.UseraccountdealdetailFlag.REMIT);
						// useraccountService.refreshAccountes(null,users,currentUser,currentUser.getUserName(),ud3,
						// recharge,
						// Const.UseraccountdealdetailFlag.REMIT);
						// 下级账户充值
						ICharge iCharge4 = new Charge4User(ud4, null, recharge,
								child, users, currentUser.getUserName(),
								Const.UseraccountdealdetailFlag.USER_DEPOSIT);
						// useraccountService.refreshAccountes(null,child,users,currentUser.getUserName(),ud4,recharge,Const.UseraccountdealdetailFlag.USER_DEPOSIT);
						UserChargeEngine.getInstance().addLast(iCharge3);
						UserChargeEngine.getInstance().addLast(iCharge4);
					}
				}
				log.info("支付宝返回信息------>支付成功……");
				alipayBack1.setTotalFee(recharge+"");
				alipayBack1.setTradeNo(out_trade_no);
				alipayBack1.setUserId(currentUser.getUserId());
				alipayBack1.setUserName(currentUser.getUserName());
				alipayBack1.setIsIntoaccount(1);
				alipayBack1.setStatus("1");
				alipayService.addAlipayBack(alipayBack1);
				
				//更新alipay
				alipay.setStatus("1");
				alipay.setIsIntoaccount(1);
				alipayService.update(alipay);
				returnResult = "SUCCESS";
				return "notify";
//				return null;
			} else{
				log.info("支付宝返回处理，验证成功（充值失败）");
				alipayBack1.setTotalFee(recharge+"");
				alipayBack1.setTradeNo(out_trade_no);
				alipayBack1.setUserId(currentUser.getUserId());
				alipayBack1.setUserName(currentUser.getUserName());
				alipayBack1.setIsIntoaccount(0);
				alipayBack1.setStatus("0");
				alipayService.addAlipayBack(alipayBack1);
				System.out.println("验证成功");
				returnResult = "FAILED";
				return "notify";
	//			return null;
				}
		}else {
			log.info("支付宝返回处理，验证失败（充值失败）");
			alipayBack1.setTotalFee(recharge+"");
			alipayBack1.setTradeNo(out_trade_no);
			alipayBack1.setUserId(currentUser.getUserId());
			alipayBack1.setUserName(currentUser.getUserName());
			alipayBack1.setIsIntoaccount(0);
			alipayBack1.setStatus("0");
			alipayService.addAlipayBack(alipayBack1);
			returnResult = "FAILED";
			return "notify";
//			return null;
		}
	}
	
	/**
	 * 支付宝到账划额
	 * @return
	 * @throws Exception
	 */
	public String doRecharge() throws Exception{
		Alipay alipay = (Alipay)alipayService.findById(Alipay.class,alipayId);
		Users users = userService.getAdminUser();
		Users currentUser = userService.findUserById(alipay.getUserId());
		if(alipay==null){
			log.error("没有找到该条记录");
			return ShowMessage(MSG_TYPE_NORMAL, "没有找到该条记录", "点击返回", "list!listAlipay.do", 0);
		}
		if (alipay.getIsIntoaccount()==1) {
			//已到账
			log.error("该记录已到账,禁止重复划额");
			return ShowMessage(MSG_TYPE_NORMAL, "该记录已到账,禁止重复划额", "点击返回", "list!listAlipay.do", 0);
		}
		log.info("充值返回成功,现在向用户账户充值");
		double recharge = Double.parseDouble(alipay.getTotalFee());
		UserCharge ud = new UserCharge();
		ud.setVoucherType(6);
		ud.setVoucherCode(alipay.getTradeNo());
		UserCharge ud2 = new UserCharge();
		ud2.setVoucherType(6);
		ud2.setVoucherCode(alipay.getTradeNo());
		UserCharge ud3 = new UserCharge();
		ud3.setVoucherType(6);
		ud3.setVoucherCode(alipay.getTradeNo());
		UserCharge ud4 = new UserCharge();
		ud4.setVoucherType(6);
		ud4.setVoucherCode(alipay.getTradeNo());

		UserCharge ud5 = new UserCharge();
		ud5.setVoucherType(6);
		ud5.setVoucherCode(alipay.getTradeNo());
		UserCharge ud6 = new UserCharge();
		ud6.setVoucherType(6);
		ud6.setVoucherCode(alipay.getTradeNo());
		
		// 管理员--一级商户--二级商户
		if (currentUser.getUsergroup().getGroupType() == Const.GroupType.GRADE_TWO) {
			Users parentUser = userService.findUserById(currentUser
					.getParentUsers().getUserId());

			// 管理员--一级商户
			ICharge iCharge = new Charge4User(ud, null, recharge,
					users, parentUser, currentUser.getUserName(),
					Const.UseraccountdealdetailFlag.REMIT);
			// useraccountService.refreshAccountes(null,users,parentUser,currentUser.getUserName(),ud,
			// recharge,
			// Const.UseraccountdealdetailFlag.REMIT);
			ICharge iCharge2 = new Charge4User(ud2, null, recharge,
					parentUser, users, currentUser.getUserName(),
					Const.UseraccountdealdetailFlag.USER_DEPOSIT);
			// useraccountService.refreshAccountes(null,parentUser,users,currentUser.getUserName(),ud2,recharge,
			// Const.UseraccountdealdetailFlag.USER_DEPOSIT);

			// 一级商户--二级商户
			ICharge iCharge3 = new Charge4User(ud3, null, recharge,
					parentUser, currentUser, currentUser
							.getUserName(),
					Const.UseraccountdealdetailFlag.REMIT);
			// useraccountService.refreshAccountes(null,parentUser,currentUser,currentUser.getUserName(),
			// ud3, recharge,
			// Const.UseraccountdealdetailFlag.REMIT);
			ICharge iCharge4 = new Charge4User(ud4, null, recharge,
					currentUser, parentUser, currentUser
							.getUserName(),
					Const.UseraccountdealdetailFlag.USER_DEPOSIT);
			// useraccountService.refreshAccountes(null,currentUser,parentUser,currentUser.getUserName(),ud4,recharge,
			// Const.UseraccountdealdetailFlag.USER_DEPOSIT);
			UserChargeEngine.getInstance().addLast(iCharge);
			UserChargeEngine.getInstance().addLast(iCharge2);
			UserChargeEngine.getInstance().addLast(iCharge3);
			UserChargeEngine.getInstance().addLast(iCharge4);
			if (recharge < 2000) {
				ICharge iCharge5 = new Charge4User(ud5, null, 1.0,
						currentUser, users, currentUser
								.getUserName(),
						Const.UseraccountdealdetailFlag.CROSS_CLAIM);
				// useraccountService.refreshAccountes(null,currentUser,users,currentUser.getUserName(),ud5,1.0,
				// Const.UseraccountdealdetailFlag.CROSS_CLAIM);
				ICharge iCharge6 = new Charge4User(ud6, null, 1.0,
						users, currentUser, currentUser
								.getUserName(),
						Const.UseraccountdealdetailFlag.PLUS_CLAIM);
				// useraccountService.refreshAccountes(null,users,currentUser,currentUser.getUserName(),ud6,1.0,
				// Const.UseraccountdealdetailFlag.PLUS_CLAIM);
				UserChargeEngine.getInstance().addLast(iCharge5);
				UserChargeEngine.getInstance().addLast(iCharge6);

			}
		} else {
			ICharge iCharge = new Charge4User(ud, null, recharge,
					users, currentUser, currentUser.getUserName(),
					Const.UseraccountdealdetailFlag.REMIT);
			// useraccountService.refreshAccountes(null,currentUser,users,currentUser.getUserName(),ud,
			// recharge,
			// Const.UseraccountdealdetailFlag.REMIT);
			if (recharge < 2000) {
				ICharge iCharge5 = new Charge4User(ud5, null, 1.0,
						currentUser, users, currentUser
								.getUserName(),
						Const.UseraccountdealdetailFlag.CROSS_CLAIM);
				// useraccountService.refreshAccountes(null,currentUser,users,currentUser.getUserName(),ud5,
				// 1.0,
				// Const.UseraccountdealdetailFlag.CROSS_CLAIM);
				ICharge iCharge6 = new Charge4User(ud6, null, 1.0,
						users, currentUser, currentUser
								.getUserName(),
						Const.UseraccountdealdetailFlag.PLUS_CLAIM);
				// useraccountService.refreshAccountes(null,users,currentUser,currentUser.getUserName(),ud6,1.0,
				// Const.UseraccountdealdetailFlag.PLUS_CLAIM);
				UserChargeEngine.getInstance().addLast(iCharge5);
				UserChargeEngine.getInstance().addLast(iCharge6);
			}
			// 下级账户充值
			// users = userService.findUserById(users.getUserId());
			ICharge iCharge2 = new Charge4User(ud2, null, recharge,
					currentUser, users, currentUser.getUserName(),
					Const.UseraccountdealdetailFlag.USER_DEPOSIT);
			// useraccountService.refreshAccountes(null,users,currentUser,currentUser.getUserName(),ud2,recharge,Const.UseraccountdealdetailFlag.USER_DEPOSIT);
			UserChargeEngine.getInstance().addLast(iCharge);
			UserChargeEngine.getInstance().addLast(iCharge2);
			// 如果一级商户只有一个下属,则这笔划款自动转向二级商户
			List<Users> children = userService.findChildUsers(users
					.getUserId());
			if (children.size() == 1) {
				Users child = children.get(0);
				ICharge iCharge3 = new Charge4User(ud3, null, recharge,
						users, currentUser, currentUser
								.getUserName(),
						Const.UseraccountdealdetailFlag.REMIT);
				// useraccountService.refreshAccountes(null,users,currentUser,currentUser.getUserName(),ud3,
				// recharge,
				// Const.UseraccountdealdetailFlag.REMIT);
				// 下级账户充值
				ICharge iCharge4 = new Charge4User(ud4, null, recharge,
						child, users, currentUser.getUserName(),
						Const.UseraccountdealdetailFlag.USER_DEPOSIT);
				// useraccountService.refreshAccountes(null,child,users,currentUser.getUserName(),ud4,recharge,Const.UseraccountdealdetailFlag.USER_DEPOSIT);
				UserChargeEngine.getInstance().addLast(iCharge3);
				UserChargeEngine.getInstance().addLast(iCharge4);
			}
		}
		log.info("支付宝返回信息------>支付成功……");
		
		//更新alipay
		alipay.setStatus("1");
		alipay.setIsIntoaccount(1);
		alipayService.update(alipay);
		return ShowMessage(MSG_TYPE_NORMAL, "支付成功", "点击返回", "list!listAlipay.do", 0);
	}
	
	
	public static void main(String[] args) {
		new AlipayChargeAct().alipayCharge();
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String totalFee) {
		total_fee = totalFee;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String outTradeNo) {
		out_trade_no = outTradeNo;
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String tradeStatus) {
		trade_status = tradeStatus;
	}

	public String getNotify_id() {
		return notify_id;
	}

	public void setNotify_id(String notifyId) {
		notify_id = notifyId;
	}

	public String getReturnResult() {
		return returnResult;
	}

	public void setReturnResult(String returnResult) {
		this.returnResult = returnResult;
	}

	public Integer getAlipayId() {
		return alipayId;
	}

	public void setAlipayId(Integer alipayId) {
		this.alipayId = alipayId;
	}
}
