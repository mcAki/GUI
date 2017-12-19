package com.sys.volunteer.epaybank;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.epaylinks.dsf.ws.client.EpayDsfAPI;
import cn.epaylinks.dsf.ws.client.EpayDsfAPIConfig;

import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.DateUtil;
import com.sys.volunteer.common.EpayBankUtil;
import com.sys.volunteer.common.RandomUtils;
import com.sys.volunteer.common.StringUtil;
import com.sys.volunteer.common.SysUtil;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.EpayBankpayRequest;
import com.sys.volunteer.pojo.EpayBankpayResponse;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.usercharge.UserChargeEngine;
import com.sys.volunteer.usercharge.engine.ICharge;
import com.sys.volunteer.usercharge.engine.charge.Charge4User;
import com.sys.volunteer.users.UserService;

@Service
@Transactional
public class EpayBankServiceBean extends CommonDao implements EpayBankService {
	
	protected static final Log logger = LogFactory.getLog(EpayBankServiceBean.class);

	@Resource
	UserService userService;
	
	
	@Override
	public EpayBankpayRequest initEpayBankpayRequest(String payCardNo,double money,Users user,String summary) throws Exception {
		EpayBankpayRequest request = new EpayBankpayRequest();
		request.setTermNo(EpayBankUtil.AUTHCHARGE_TERMNO);
		request.setMerNo(EpayDsfAPIConfig.merchantNo);
		request.setTrackNo(EpayDsfAPI.getSeqNo());
		request.setOrderNo(request.getTrackNo());
		request.setTranCode(EpayBankUtil.AUTHCHARGE_TRANCODE);
		request.setPayCardNo(payCardNo);
		request.setBankAccNo("");
		request.setPayType("1");
		request.setPayWay("1");
		request.setRecCardNo(EpayBankUtil.AUTHCHARGE_RECARDNO);
		String amount = StringUtil.subZeroAndDot(money*100+"");
		request.setAmount(amount);
		request.setTradeDate(DateUtil.DateToString(new Date(), "yyyyMMdd"));
		request.setReqTime(DateUtil.DateToString(new Date(), "yyyyMMddHHmmss"));
		request.setSummary(summary);
		request.setUserId(user.getUserId());
		request.setUserName(user.getUserName());
		String xml = EpayBankUtil.initXml(request);
		String sign = EpayBankUtil.initSign(xml);
		request.setSign(sign);
		xml = xml.replaceAll("<sign>[0-9a-zA-Z]*</sign>|<sign\\s*/>", 
				"<sign>" + sign + "</sign>");
		request.setXmlText(xml);
		logger.info("EpayBankServiceBean生成xml:"+xml);
		this.save(request);
		return request;
	}
	
	@Override
	public EpayBankpayResponse initEpayBankpayResponse(EpayBankpayRequest request) {
		EpayBankpayResponse response = new EpayBankpayResponse();
		response.setUserId(request.getUserId());
		response.setUserName(request.getUserName());
		return response;
	}
	
	@Override
	public int refreshEpayBank(EpayBankpayResponse response) {
		int result = 0;
		save(response);
		Users users = userService.getAdminUser();
		Users currentUser = userService.findUserById(response.getUserId());
		double recharge = 0;
		if (response.getAmount() != null) {
			recharge = Double.valueOf(response.getAmount())/100;
		}
		 
		if (response.getRespCode()!=null && response.getRespCode().equals("0000")) {
			//成功
			result = 1;
			
			UserCharge ud = new UserCharge();
			ud.setVoucherType(8);
			ud.setVoucherCode(response.getOrderNo());
			UserCharge ud2 = new UserCharge();
			ud2.setVoucherType(8);
			ud2.setVoucherCode(response.getOrderNo());
			UserCharge ud3 = new UserCharge();
			ud3.setVoucherType(8);
			ud3.setVoucherCode(response.getOrderNo());
			UserCharge ud4 = new UserCharge();
			ud4.setVoucherType(8);
			ud4.setVoucherCode(response.getOrderNo());

			UserCharge ud5 = new UserCharge();
			ud5.setVoucherType(8);
			ud5.setVoucherCode(response.getOrderNo());
			UserCharge ud6 = new UserCharge();
			ud6.setVoucherType(8);
			ud6.setVoucherCode(response.getOrderNo());
			
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
				//扣手续费
//				if (recharge < 2000) {
//					ICharge iCharge5 = new Charge4User(ud5, null, 1.0,
//							currentUser, users, currentUser
//									.getUserName(),
//							Const.UseraccountdealdetailFlag.CROSS_CLAIM);
//					// useraccountService.refreshAccountes(null,currentUser,users,currentUser.getUserName(),ud5,1.0,
//					// Const.UseraccountdealdetailFlag.CROSS_CLAIM);
//					ICharge iCharge6 = new Charge4User(ud6, null, 1.0,
//							users, currentUser, currentUser
//									.getUserName(),
//							Const.UseraccountdealdetailFlag.PLUS_CLAIM);
//					// useraccountService.refreshAccountes(null,users,currentUser,currentUser.getUserName(),ud6,1.0,
//					// Const.UseraccountdealdetailFlag.PLUS_CLAIM);
//					UserChargeEngine.getInstance().addLast(iCharge5);
//					UserChargeEngine.getInstance().addLast(iCharge6);
//
//				}
			} else {
				ICharge iCharge = new Charge4User(ud, null, recharge,
						users, currentUser, currentUser.getUserName(),
						Const.UseraccountdealdetailFlag.REMIT);
				// useraccountService.refreshAccountes(null,currentUser,users,currentUser.getUserName(),ud,
				// recharge,
				// Const.UseraccountdealdetailFlag.REMIT);
				//扣手续
//				if (recharge < 2000) {
//					ICharge iCharge5 = new Charge4User(ud5, null, 1.0,
//							currentUser, users, currentUser
//									.getUserName(),
//							Const.UseraccountdealdetailFlag.CROSS_CLAIM);
//					// useraccountService.refreshAccountes(null,currentUser,users,currentUser.getUserName(),ud5,
//					// 1.0,
//					// Const.UseraccountdealdetailFlag.CROSS_CLAIM);
//					ICharge iCharge6 = new Charge4User(ud6, null, 1.0,
//							users, currentUser, currentUser
//									.getUserName(),
//							Const.UseraccountdealdetailFlag.PLUS_CLAIM);
//					// useraccountService.refreshAccountes(null,users,currentUser,currentUser.getUserName(),ud6,1.0,
//					// Const.UseraccountdealdetailFlag.PLUS_CLAIM);
//					UserChargeEngine.getInstance().addLast(iCharge5);
//					UserChargeEngine.getInstance().addLast(iCharge6);
//				}
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
		}
		return result;
	}
}
