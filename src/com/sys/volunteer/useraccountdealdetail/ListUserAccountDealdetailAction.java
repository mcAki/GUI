package com.sys.volunteer.useraccountdealdetail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.sys.volunteer.baseaction.BaseListAction;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.DateUtil;
import com.sys.volunteer.common.Privilege;
import com.sys.volunteer.common.SysUtil;
import com.sys.volunteer.pagemodel.PageView;
import com.sys.volunteer.pagemodel.QueryResult;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.Useraccountdealdetail;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.supply.usage.ISupply;
import com.sys.volunteer.usercharge.UserChargeService;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class ListUserAccountDealdetailAction extends BaseListAction implements
		Preparable {

	@Resource
	UseraccountDealDetailService useraccountDealDetailService;
	@Resource
	SupplyService supplyService;
	@Resource
	UserChargeService userChargeService;

	private Integer userId;

	private Integer supplyId;

	private String username;

	private Date startDate;

	private Date endDate;

	private Date startDate_wen;

	private Date endDate_wen;

	private List<ISupply> supplyList;

	private Integer flag;

	private String mobile;

	private Integer voucherType;
	
	private String orderId;
	
	private String recodeOperator;

	@Override
	public void prepare() throws Exception {
		// goodsList = (List<Goods>)goodsService.findAll(Goods.class);
		supplyList = (List<ISupply>) supplyService.findAll(Supply.class);
		Supply supply = new Supply();
		supply.setId(0);
		supply.setSupplyName("请选择供货商");
		supplyList.add(0, supply);
	}

	/**
	 * 商户充值/划拨记录
	 * 
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "udForUsers", privilegeAccess = Const.PrivilegeAccessConst.QUERY)
	public String listUdForUser() throws Exception {

		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		DetachedCriteria dec = DetachedCriteria
				.forClass(Useraccountdealdetail.class);
		dec.add(Restrictions.eq("logFor",
				Const.UseraccountdealdetailLogFor.FOR_USER));
		int groupId = this.getSessionUser().getUsergroup().getGroupType();
		if (groupId > Const.GroupType.STAFF) {

			if (!SysUtil.isNull(startDate) && !SysUtil.isNull(endDate)) {
				dec.add(Restrictions.between("createTime", DateUtil
						.getMinDate(startDate), DateUtil.getMaxDate(endDate)));
			} else {
				dec.add(Restrictions.between("createTime", DateUtil
						.getMinDate(new Date()), DateUtil
						.getMaxDate(new Date())));
			}
		} else {
			if (!SysUtil.isNull(startDate) && !SysUtil.isNull(endDate)) {
				dec.add(Restrictions.between("createTime", DateUtil
						.getMinDate(startDate), DateUtil.getMaxDate(endDate)));
			}

		}
		if (!SysUtil.isNull(username)) {
			dec.add(Restrictions.like("userName", username,
							MatchMode.ANYWHERE));
		}
		if (!SysUtil.isNull(mobile)) {
			dec.add(Restrictions.eq("mobile", mobile));
		}
		if (!SysUtil.isNull(recodeOperator)) {
			dec.add(Restrictions.eq("recodeOperator", recodeOperator));
		}
		switch (getSessionUser().getUsergroup().getGroupType()) {
		case Const.GroupType.GRADE_ONE:
			dec.createAlias("users", "user");
			dec.add(Restrictions.or(Restrictions.eq("users.id",
					getSessionUser().getUserId()), Restrictions.eq(
					"user.parentUsers.id", getSessionUser().getUserId())));
			break;
		case Const.GroupType.GRADE_TWO:
			dec.add(Restrictions.eq("users.id", getSessionUser().getUserId()));
			break;
		default:
			break;
		}
		if (!SysUtil.isNull(flag) && flag != 0) {
			dec.add(Restrictions.eq("flag", flag));
		}
		if (!SysUtil.isNull(voucherType) && voucherType != 0) {
			dec.add(Restrictions.eq("voucherType", voucherType));
		}
		dec.addOrder(Order.desc("seqno"));
		QueryResult queryResult = useraccountDealDetailService.getScrollData(
				pageView.getFirstResult(), pageView.getMaxresult(), dec);
		// 这里处理多表查询返回的对象数组
		List list = new ArrayList();
		if (queryResult.getResultlist().size() > 0
				&& queryResult.getResultlist().get(0) instanceof Object[]) {
			for (int i = 0; i < queryResult.getResultlist().size(); i++) {
				Object[] objects = (Object[]) queryResult.getResultlist()
						.get(i);
				Useraccountdealdetail useraccountdealdetail2 = (Useraccountdealdetail) objects[1];
				list.add(useraccountdealdetail2);
			}
			queryResult.getResultlist().clear();
			queryResult.getResultlist().addAll(list);
		}
		pageView.setQueryResult(queryResult);

		return "listUdForUser";
	}

	/**
	 * 经销商结算
	 * 
	 * @return
	 */
	public String adviceOfSettlementForUser() {

		if (startDate_wen != null && endDate_wen != null) {
			startDate_wen = DateUtil.getMinDate(startDate_wen);
			endDate_wen = DateUtil.getMaxDate(endDate_wen);
		}

		double sum = useraccountDealDetailService.sumAllExDateCount(
				Const.UseraccountdealdetailLogFor.FOR_USER, startDate_wen,
				endDate_wen);

		List<Useraccountdealdetail> selAllExDateCountList = useraccountDealDetailService
				.selUserAllExDateCountList(
						Const.UseraccountdealdetailLogFor.FOR_USER,
						startDate_wen, endDate_wen);
		// Map<String,Double> selAllExDateCountList =
		// useraccountDealDetailService.selUserAllExDateCountList(Const.UseraccountdealdetailLogFor.FOR_USER,startDate_wen,endDate_wen);
		this.getHttpServletRequest().getSession().setAttribute("ExDateCount",
				sum);
		this.getHttpServletRequest().getSession().setAttribute("startDate_wen",
				DateUtil.DateToString(startDate_wen, "yyyy-MM-dd HH:mm:ss"));
		this.getHttpServletRequest().getSession().setAttribute("endDate_wen",
				DateUtil.DateToString(endDate_wen, "yyyy-MM-dd HH:mm:ss"));
		this.getHttpServletRequest().getSession().setAttribute(
				"selAllExDateCountList", selAllExDateCountList);
		return "success";
	}

	/**
	 * 供货商结算
	 * 
	 * @return
	 */
	public String adviceOfSettlementForSupply() {

		if (startDate_wen != null && endDate_wen != null) {
			startDate_wen = DateUtil.getMinDate(startDate_wen);
			endDate_wen = DateUtil.getMaxDate(endDate_wen);
		}

		double sum = useraccountDealDetailService.sumAllExDateCount(
				Const.UseraccountdealdetailLogFor.FOR_SUPPLY, startDate_wen,
				endDate_wen);

		List<Useraccountdealdetail> selAllExDateCountList = useraccountDealDetailService
				.selSupplyAllExDateCountList(
						Const.UseraccountdealdetailLogFor.FOR_SUPPLY,
						startDate_wen, endDate_wen);
		// Map<String,Double> selAllExDateCountList =
		// useraccountDealDetailService.selSupplyAllExDateCountMap(Const.UseraccountdealdetailLogFor.FOR_SUPPLY,startDate_wen,endDate_wen);
		this.getHttpServletRequest().getSession().setAttribute("ExDateCount",
				sum);
		this.getHttpServletRequest().getSession().setAttribute("startDate_wen",
				DateUtil.DateToString(startDate_wen, "yyyy-MM-dd HH:mm:ss"));
		this.getHttpServletRequest().getSession().setAttribute("endDate_wen",
				DateUtil.DateToString(endDate_wen, "yyyy-MM-dd HH:mm:ss"));
		this.getHttpServletRequest().getSession().setAttribute(
				"selAllExDateCountList", selAllExDateCountList);
		return "success";
	}

	/**
	 * 供货商充值记录
	 * 
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "udForSupply", privilegeAccess = Const.PrivilegeAccessConst.QUERY)
	public String listUdForSupply() throws Exception {
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		DetachedCriteria dec = DetachedCriteria
				.forClass(Useraccountdealdetail.class);
		dec.add(Restrictions.eq("logFor",
				Const.UseraccountdealdetailLogFor.FOR_SUPPLY));
		if (!SysUtil.isNull(startDate) && !SysUtil.isNull(endDate)) {
			dec.add(Restrictions.between("createTime", DateUtil
					.getMinDate(startDate), DateUtil.getMaxDate(endDate)));
		} else {
			dec.add(Restrictions.between("createTime", DateUtil
					.getMinDate(new Date()), DateUtil.getMaxDate(new Date())));
		}
		if (!SysUtil.isNull(orderId)) {
			dec.add(Restrictions.eq("mainorder.mainOrderId", orderId));
		}
		if (!SysUtil.isNull(supplyId) && supplyId != 0) {
			dec.add(Restrictions.eq("supply.id", supplyId));
		}
		if (!SysUtil.isNull(flag) && flag != 0) {
			dec.add(Restrictions.eq("flag", flag));
		}
		if (!SysUtil.isNull(voucherType) && voucherType != 0) {
			dec.add(Restrictions.eq("voucherType", voucherType));
		}
		if (!SysUtil.isNull(mobile)) {
			dec.add(Restrictions.eq("mobile", mobile));
		}
		if (!SysUtil.isNull(recodeOperator)) {
			dec.add(Restrictions.eq("recodeOperator", recodeOperator));
		}
		dec.addOrder(Order.desc("seqno"));
		QueryResult queryResult = useraccountDealDetailService.getScrollData(
				pageView.getFirstResult(), pageView.getMaxresult(), dec);
		pageView.setQueryResult(queryResult);

		return "listUdForSupply";
	}

	/**
	 * 批量结算记录
	 * 
	 * @return
	 */
	public String batchAddCheckOut() {
		userChargeService.addCheckOut();
		return ShowMessage(MSG_TYPE_NORMAL, "结算成功", "", "", 0);
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(Integer supplyId) {
		this.supplyId = supplyId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<ISupply> getSupplyList() {
		return supplyList;
	}

	public void setSupplyList(List<ISupply> supplyList) {
		this.supplyList = supplyList;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getVoucherType() {
		return voucherType;
	}

	public void setVoucherType(Integer voucherType) {
		this.voucherType = voucherType;
	}

	public Date getStartDate_wen() {
		return startDate_wen;
	}

	public void setStartDate_wen(Date startDateWen) {
		startDate_wen = startDateWen;
	}

	public Date getEndDate_wen() {
		return endDate_wen;
	}

	public void setEndDate_wen(Date endDateWen) {
		endDate_wen = endDateWen;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getRecodeOperator() {
		return recodeOperator;
	}

	public void setRecodeOperator(String recodeOperator) {
		this.recodeOperator = recodeOperator;
	}

}
