package com.sys.volunteer.operationLog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

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
import com.sys.volunteer.goods.GoodsService;
import com.sys.volunteer.pagemodel.PageView;
import com.sys.volunteer.pagemodel.QueryResult;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.OperationLog;
import com.sys.volunteer.pojo.Orderdetail;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.Useraccountdealdetail;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.supply.usage.ISupply;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class ListOperationLogAction extends BaseListAction implements Preparable{
	@Resource
	OperationLogService operationLogService;
	@Resource
	SupplyService supplyService;
	@Resource
	GoodsService goodsService;
    
	
	private String supplyName;
	
	private String mainOrderId;
	
//	private Users users;
	
	private String userId;
	
	private Integer supplyId;
	
	private String username;
	
	private String mobile;
	
	private List<Orderdetail> orderdetails;
	
	private Date startDate;
	
	private Date endDate;
	
	private Integer opType;
	
	private String terminalNo;
	
	private String goodsName;
	
	private List<ISupply> supplyList;
	
	private List<Goods> goodsList;
	
	private Integer goodsId;
	
	@Override
	public void prepare() throws Exception {
		//goodsList = (List<Goods>)goodsService.findAll(Goods.class); 
		supplyList = (List<ISupply>)supplyService.findAll(Supply.class);
		Supply supply=new Supply();
		supply.setId(0);
		supply.setSupplyName("请选择供货商");
		supplyList.add(0, supply);
		goodsList = (List<Goods>)goodsService.findAll(Goods.class);
		goodsList.get(0).setGoodsName("请选择商品");
	}
	
	/**
	 * 管理员列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public void list()throws Exception{
//		pageView=new PageView(this.getPageSize(), this.getPageIndex());
//		QueryResult queryResult=userService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), DetachedCriteria.forClass(Users.class));
//		pageView.setQueryResult(queryResult);
		//System.out.println("111111111111111111");
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		DetachedCriteria dec = DetachedCriteria
				.forClass(OperationLog.class);
		int groupId=getSessionUser().getUsergroup().getGroupType();
		switch (groupId) {
		case Const.GroupType.GRADE_ONE:
			dec.createAlias("users", "user");
			dec.add(Restrictions.or(Restrictions.eq("users.id", getSessionUser().getUserId()), Restrictions.eq("user.parentUsers.id", getSessionUser().getUserId())));
			break;
		case Const.GroupType.GRADE_TWO:
			dec.add(Restrictions.eq("users.id", getSessionUser().getUserId()));
			break;
		default:
			break;
		}
		//根据供货商查询记录
		if(!SysUtil.isNull(supplyId)&&supplyId!=0)
		{
			dec.add(Restrictions.eq("supply.id",supplyId));
		}
		if(!SysUtil.isNull(username))
		{
			dec.add(Restrictions.like("userName", username, MatchMode.ANYWHERE));
		}
		if(!SysUtil.isNull(terminalNo))
		{
			dec.add(Restrictions.like("terminalNo", terminalNo, MatchMode.ANYWHERE));
		}
		if (!SysUtil.isNull(goodsId)&&goodsId>0) {
			dec.add(Restrictions.eq("goods.id", goodsId));
		}
		if(!SysUtil.isNull(mobile))
		{
			dec.add(Restrictions.eq("mobileNum", mobile));
		}
		//根据订单查询记录
		if(!SysUtil.isNull(mainOrderId))
		{
			dec.add(Restrictions.eq("mainorder.id", mainOrderId));
		}
		if (!SysUtil.isNull(startDate)&&!SysUtil.isNull(endDate)) {
			dec.add(Restrictions.between("createTime", DateUtil.getMinDate(startDate), DateUtil.getMaxDate(endDate)));
		}else{
			dec.add(Restrictions.between("createTime", DateUtil.getMinDate(new Date()), DateUtil.getMaxDate(new Date())));
		}
		if (!SysUtil.isNull(opType)&&opType!=0) {
			dec.add(Restrictions.eq("type", opType));
		}
		dec.addOrder(Order.desc("createTime"));
		QueryResult queryResult = operationLogService.getScrollData(pageView
				.getFirstResult(), pageView.getMaxresult(), dec);
		//这里处理多表查询返回的对象数组
		List list = new ArrayList();
		if (queryResult.getResultlist().size()>0&&queryResult.getResultlist().get(0) instanceof Object[]) {
			for (int i = 0; i < queryResult.getResultlist().size(); i++) {
				Object[] objects = (Object[])queryResult.getResultlist().get(i);
				OperationLog operationLog = (OperationLog)objects[1];
				list.add(operationLog);
			}
			queryResult.getResultlist().clear();
			queryResult.getResultlist().addAll(list);
		}
		pageView.setQueryResult(queryResult);
	}
	

	/**
	 * 执行
	 */
	@Privilege(permissionName = "operationLog", privilegeAccess = Const.PrivilegeAccessConst.QUERY)
	@Override
	public String execute() throws Exception {
		list();
		return SUCCESS;
	}

	/**
	 * 验证数据
	 */
	@Override
	public void validate() {
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSupplyName() {
		return supplyName;
	}


	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
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

	public String getMainOrderId() {
		return mainOrderId;
	}

	public void setMainOrderId(String mainOrderId) {
		this.mainOrderId = mainOrderId;
	}


	public List<Orderdetail> getOrderdetails() {
		return orderdetails;
	}


	public void setOrderdetails(List<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
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


	public Integer getOpType() {
		return opType;
	}


	public void setOpType(Integer opType) {
		this.opType = opType;
	}


	public String getTerminalNo() {
		return terminalNo;
	}


	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}


	public String getGoodsName() {
		return goodsName;
	}


	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}


	public List<ISupply> getSupplyList() {
		return supplyList;
	}


	public void setSupplyList(List<ISupply> supplyList) {
		this.supplyList = supplyList;
	}
	
	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
}
