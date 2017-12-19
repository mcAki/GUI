package com.sys.volunteer.liandong;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xml.sax.SAXException;

import com.sys.volunteer.authority.AuthorityService;
import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.FileTool;
import com.sys.volunteer.common.LiandongUtil;
import com.sys.volunteer.common.SysUtil;
import com.sys.volunteer.pojo.Liandong;
import com.sys.volunteer.pojo.LiandongPay;
import com.sys.volunteer.pojo.Menutree;
import com.sys.volunteer.pojo.Useraccountdealdetail;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.supply.usage.ISupply;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.users.UserService;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class LiandongAction extends BaseAction {

	@Resource
	LiandongService liandongService;
	@Resource
	UseraccountService useraccountService;
	@Resource
	UserService userService;
	@Resource
	AuthorityService authorityService;

	/**
	 * 返回地址
	 */
	private String returnUrl;

	private String cpid;

	private Double payMoney;

	private String payOrder;

	private int status;

	private String returnResult;

	/**
	 * 测试查询接口
	 * 
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	public String testQueryInventory() {
		Liandong liandong = new Liandong();
		liandong.setOrderID("37bf9fa33754d08501375e67316c01db");
		returnUrl = LiandongUtil.searchOrder(liandong);
		liandong = LiandongUtil.getLiandong(returnUrl,2);
		// String xpath="";
		// String value="";
		return ShowMessage(MSG_TYPE_NORMAL, liandong.getUserName() + "的账户余额为:	"
				+ liandong.getAccount() + "元", "", "", 0);
	}

	/**
	 * 支付接口页面
	 * 
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	public String payRecharge() {
		Users user = getSessionUser();
		LiandongPay liandongPay = new LiandongPay();
		// String payOrder=SysUtil.getUUID();
		// liandongPay.setPayOrder(payOrder);
		// TODO:暂时写死一分钱
		// payMoney = 1;
		// 单位:分
		liandongPay.setPayMoney((int) (payMoney * 100));
		liandongPay.setUser(user);
		liandongPay.setUserName(user.getUserName());
		liandongPay = liandongService.addLiandongPay(liandongPay);
		returnUrl = LiandongUtil.payRecharge(liandongPay);
		return SUCCESS;
	}

	/**
	 * 支付notify接口
	 * 
	 * @return
	 */
	public String doPayRecharge() {
		LiandongPay liandongPay = liandongService.findLiandongPayById(payOrder);
		Users users = userService.findUserById(liandongPay.getUser()
				.getUserId());
		// 需要重新登录
		this.getSession().put(Const.USER_SESSION_KEY, users);
		List<Menutree> menuTree = this.authorityService.getMenuTreeBy(users.getUsergroup());
		this.getSession().put(Const.USER_MENUTREE_SESSION_KEY, menuTree);
		if (liandongPay.getIsDeal() == 0) {
			System.out.println("========================="+status);
			liandongPay = liandongService.updateLiandongPayStatus(liandongPay,
					status);
			System.out.println("------------------------"+(liandongPay.getStatus() == 1));
			if (liandongPay.getStatus() == 1) {
				//已停用
				// 成功则划额
//				System.out.println("充值返回成功,现在向用户账户充值");
//				double recharge = (double) liandongPay.getPayMoney() / 100
//						* LiandongUtil.HANDLING_CHARGE;
//				Useraccountdealdetail ud = new Useraccountdealdetail();
//				ud.setVoucherType(4);
//				ud.setVoucherCode(liandongPay.getPayOrder());
//				
//				useraccountService.refreshAccountes(null, users, ud, recharge,
//						Const.UseraccountdealdetailFlag.USER_DEPOSIT);
//				// 如果一级商户只有一个下属,则这笔划款自动转向二级商户
//				List<Users> children = userService.findChildUsers(users
//						.getUserId());
//				if (children.size() == 1) {
//					Users child = children.get(0);
//					useraccountService.refreshAccountes(null, users, ud,
//							recharge, Const.UseraccountdealdetailFlag.REMIT);
//					// 下级账户充值
//					Useraccountdealdetail ud2 = new Useraccountdealdetail();
//					ud2.setVoucherType(4);
//					ud2.setVoucherCode(liandongPay.getPayOrder());
//					users = userService.findUserById(child.getUserId());
//					useraccountService.refreshAccountes(null, child, ud2,
//							recharge,
//							Const.UseraccountdealdetailFlag.USER_DEPOSIT);
//					System.out.println("充值完成!");
//				}
//				//管理员自动提款
//				Useraccountdealdetail ud3=new Useraccountdealdetail();
//				Users admin = userService.getAdminUser();
//				ud3.setVoucherType(4);
//				ud3.setVoucherCode(liandongPay.getPayOrder());
//				useraccountService.refreshAccountes(null,admin,ud3, recharge,Const.UseraccountdealdetailFlag.DRAWN);
				return ShowMessage(MSG_TYPE_STOP, "成功支付" + 0 + "元,请查收",
						"点击返回系统", getHttpServletRequest().getContextPath()
								+ "/frame!main.html", 0);
			} else {
				return ShowMessage(MSG_TYPE_STOP, "支付失败", "点击返回系统",
						getHttpServletRequest().getContextPath()
								+ "/frame!main.html", 0);
			}
		} else {
			return ShowMessage(MSG_TYPE_STOP, "该支付已处理","点击返回系统", getHttpServletRequest()
					.getContextPath()
					+ "frame!main.html", 0);
		}

	}

	/**
	 * 联动支付notifyUrl
	 * 
	 * @return
	 */
	public String notifyLiandong() {
		if (status == 1) {
			returnResult = "Success";
		} else {
			returnResult = "Fail";
		}
		return "notify";
	}

	/**
	 * 本地调用响应
	 * 
	 * @return
	 */
	public String queryInventory() {
		System.out.println(cpid);
		return SUCCESS;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	public String getCpid() {
		return cpid;
	}

	public void setCpid(String cpid) {
		this.cpid = cpid;
	}

	public String getPayOrder() {
		return payOrder;
	}

	public void setPayOrder(String payOrder) {
		this.payOrder = payOrder;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getReturnResult() {
		return returnResult;
	}

	public void setReturnResult(String returnResult) {
		this.returnResult = returnResult;
	}

}
