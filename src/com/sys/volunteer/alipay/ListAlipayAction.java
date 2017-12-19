package com.sys.volunteer.alipay;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sys.volunteer.baseaction.BaseListAction;
import com.sys.volunteer.common.DateUtil;
import com.sys.volunteer.common.SysUtil;
import com.sys.volunteer.pagemodel.PageView;
import com.sys.volunteer.pagemodel.QueryResult;
import com.sys.volunteer.pojo.Alipay;

public class ListAlipayAction extends BaseListAction {

	@Resource
	AlipayService alipayService;
	
	private String username;
	private Date startDate;
	private Date endDate;
	
	
	/**
	 * 支付宝列表
	 * @return
	 * @throws Exception
	 */
	public String listAlipay() throws Exception {
		DetachedCriteria dec = DetachedCriteria.forClass(Alipay.class);
		if (!SysUtil.isNull(username)) {
			dec.add(Restrictions.like("userName", username,MatchMode.ANYWHERE));
		}
		if (!SysUtil.isNull(startDate)&&!SysUtil.isNull(endDate)) {
			dec.add(Restrictions.between("tradeTime", DateUtil.getMinDate(startDate), DateUtil.getMaxDate(endDate)));
		}
		dec.addOrder(Order.desc("tradeTime"));
		pageView=new PageView(getPageSize(), getPageIndex());
		QueryResult queryResult=alipayService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), dec);
		pageView.setQueryResult(queryResult);
		return "listAlipay";
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
}
