package com.sys.volunteer.order;

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
import com.sys.volunteer.goods.GoodsTypeService;
import com.sys.volunteer.pagemodel.PageView;
import com.sys.volunteer.pagemodel.QueryResult;
import com.sys.volunteer.pojo.AirReversalResponseNew;
import com.sys.volunteer.pojo.BatchOrder;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.GoodsType;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Orderdetail;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.supply.usage.ISupply;
import com.sys.volunteer.users.UserService;
import com.sys.volunteer.vo.MainorderVO;
import com.sys.volunteer.vo.StatisticsOrderVO;

import ft.otp.a.b;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
@SuppressWarnings("unchecked")
public class ListOrderAction extends BaseListAction implements Preparable{
	@Resource
	OrderService orderService;
	@Resource
	SupplyService supplyService;
	@Resource
	GoodsService goodsService;
	@Resource
	GoodsTypeService goodsTypeService;
	@Resource
	UserService userService;
    
	
	private Mainorder mainorder;
	
	private BatchOrder batchOrder;
	
	private String batchOrderId;
	
	private String supplyName;
	
	private String mainOrderId;
	
	private Users users;
	
	private String userId;
	
	private String userIdEx;
	
	private String userIdExEx;
	
	private Integer supplyId;
	
	private String username;
	
	private String mobile;
	
	private List<Orderdetail> orderdetails;
	
	private Date startDate;
	
	private Date endDate;
	
	private Date start2Date;
	
	private Date end2Date;
	
	private String goodsName;
	
	private String terminalNo;
	
	private List<AirReversalResponseNew> airReversalResponseNews;
	
	private List<ISupply> supplyList;
	
	private List<Goods> goodsList;
	
	private List<GoodsType> goodsTypeList;
	
	private Integer goodsId;
	
	private Integer goodsTypeId;
	
	private Integer sellType;
	
	private Integer state;
	
	private Integer reversalState;
	
	private Double stockMoney;
	
	private Double retailMoney;
	
	private List<MainorderVO> mainorderVOs;
	
	private List<Mainorder> list;
	
	private Integer isNeedManual;
	
	private String manualUserName;
	
	private Integer orderType;
	
	private Integer groupIdSelect;
	
	private Integer isTerminal;
	
	private Double amount;
	
	private int isLocal;

	List<StatisticsOrderVO> statisticsOrderVOList;
	
	private List<Users> usersListEx;
	private List<Users> usersListExEx;
	
	List<Mainorder> listMainOrder;
	public List<Mainorder> getListMainOrder() {
		return listMainOrder;
	}

	public void setListMainOrder(List<Mainorder> listMainOrder) {
		this.listMainOrder = listMainOrder;
	}

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
		goodsTypeList = (List<GoodsType>)goodsTypeService.findAll(GoodsType.class);
		goodsTypeList.get(0).setGoodsTypeName("请选择商品类型");
	}
	
	/**
	 * 管理员列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public void list() throws Exception{
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		DetachedCriteria dec = DetachedCriteria
				.forClass(Mainorder.class);
		int groupId=getSessionUser().getUsergroup().getGroupType();
		if (Const.GroupType.SUPPLY<groupId) {
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
		}
		if(!SysUtil.isNull(mainOrderId))
		{
			dec.add(Restrictions.eq("id", mainOrderId));
		}
		
		if(groupId>2){
			
			if (!SysUtil.isNull(startDate)&&!SysUtil.isNull(endDate)) {
				dec.add(Restrictions.between("createTime", DateUtil.getMinDate(startDate), DateUtil.getMaxDate(endDate)));
			}else{
				dec.add(Restrictions.between("createTime", DateUtil.getMinDate(new Date()), DateUtil.getMaxDate(new Date())));
			}
		}else{
			if (!SysUtil.isNull(startDate)&&!SysUtil.isNull(endDate)) {
				dec.add(Restrictions.between("createTime", DateUtil.getMinDate(startDate), DateUtil.getMaxDate(endDate)));
			}
			
		}
		
		
		if (!SysUtil.isNull(start2Date)&&!SysUtil.isNull(end2Date)) {
			dec.add(Restrictions.between("reversalTime", DateUtil.getMinDate(start2Date), DateUtil.getMaxDate(end2Date)));
		}
		if(!SysUtil.isNull(supplyId)&&supplyId!=0){
			dec.add(Restrictions.eq("supply.id",supplyId));
		}
		if (!SysUtil.isNull(goodsTypeId)&&goodsTypeId>0) {
			dec.add(Restrictions.eq("goodsType.id", goodsTypeId));
		}
		if (!SysUtil.isNull(goodsId)&&goodsId>0) {
			dec.add(Restrictions.eq("goods.id", goodsId));
		}
		if (!SysUtil.isNull(username)) {
			dec.add(Restrictions.like("userName", username,MatchMode.ANYWHERE));
		}
		if (!SysUtil.isNull(terminalNo)) {
			dec.add(Restrictions.like("terminalNo", terminalNo,MatchMode.ANYWHERE));
		}
		if(!SysUtil.isNull(mobile)){
			dec.add(Restrictions.eq("mobile", mobile));
		}
		if (!SysUtil.isNull(sellType)&&sellType!=0) {
			dec.add(Restrictions.eq("orderType", sellType));
		}
		if (!SysUtil.isNull(state)&&state!=-2) {
			if (state.equals(3)) {
				dec.add(Restrictions.or(Restrictions.eq("state", state), Restrictions.eq("state", Const.MainOrderState.THREAD_EXECUTING)));
			}else {
				dec.add(Restrictions.eq("state", state));
			}
		}
		if (!SysUtil.isNull(reversalState)&&reversalState!=-3) {
			if (state.equals(0)) {
				dec.add(Restrictions.or(Restrictions.eq("reversalState", state), Restrictions.eq("reversalState", Const.OrderReversalState.THREAD_EXECUTING)));
			}else {
				dec.add(Restrictions.eq("reversalState", reversalState));
			}
		}
		if (!SysUtil.isNull(isNeedManual)) {
			dec.add(Restrictions.eq("isNeedManual", isNeedManual));
		}
		if (!SysUtil.isNull(manualUserName)) {
			dec.add(Restrictions.eq("manualUserName", manualUserName));
		}
		if (!SysUtil.isNull(orderType)) {
			dec.add(Restrictions.eq("orderType", orderType));
		}
		if (!SysUtil.isNull(isTerminal)) {
			dec.add(Restrictions.eq("isTerminal", isTerminal));
		}
		if (!SysUtil.isNull(batchOrderId)) {
			dec.add(Restrictions.eq("batchOrderId", batchOrderId));
		}
		
		if (end2Date==null) {
			dec.addOrder(Order.desc("createTime"));
		} else {
			dec.addOrder(Order.desc("reversalTime"));
		}
		
//		listMainOrder = orderService.findByDetachedCriteria(dec);
//		this.getSession().put("listMainOrder", listMainOrder);
		
		QueryResult queryResult = orderService.getScrollData(pageView
				.getFirstResult(), pageView.getMaxresult(), dec);
		if (Const.Usergroup.GRADE_ONE == groupId) {
			List<Mainorder> list = new ArrayList<Mainorder>();
			for (Object obj : queryResult.getResultlist()) {
				Object[] obj2 = (Object[])obj;
				Mainorder mainorder = (Mainorder) obj2[1];
				list.add(mainorder);
			}
			queryResult.getResultlist().removeAll(queryResult.getResultlist());
			queryResult.getResultlist().addAll(list);
		}
		pageView.setQueryResult(queryResult);
		
	}
	
	/**
	 * 管理员列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listOrder2Customer() throws Exception{
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		DetachedCriteria dec = DetachedCriteria
		.forClass(Mainorder.class);
		int groupId=getSessionUser().getUsergroup().getGroupType();
		if (Const.Usergroup.SUPPLY<groupId) {
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
		}
		if(!SysUtil.isNull(mainOrderId))
		{
			dec.add(Restrictions.eq("id", mainOrderId));
		}
		if (!SysUtil.isNull(startDate)&&!SysUtil.isNull(endDate)) {
			dec.add(Restrictions.between("createTime", DateUtil.getMinDate(startDate), DateUtil.getMaxDate(endDate)));
		}else{
			dec.add(Restrictions.between("createTime", DateUtil.getMinDate(new Date()), DateUtil.getMaxDate(new Date())));
		}
		if (!SysUtil.isNull(start2Date)&&!SysUtil.isNull(end2Date)) {
			dec.add(Restrictions.between("reversalTime", DateUtil.getMinDate(start2Date), DateUtil.getMaxDate(end2Date)));
		}
		if(!SysUtil.isNull(supplyId)&&supplyId!=0){
			dec.add(Restrictions.eq("supply.id",supplyId));
		}
		if (!SysUtil.isNull(goodsTypeId)&&goodsTypeId>0) {
			dec.add(Restrictions.eq("goodsType.id", goodsTypeId));
		}
		if (!SysUtil.isNull(goodsId)&&goodsId>0) {
			dec.add(Restrictions.eq("goods.id", goodsId));
		}
		if (!SysUtil.isNull(username)) {
			dec.add(Restrictions.like("userName", username,MatchMode.ANYWHERE));
		}
		if (!SysUtil.isNull(terminalNo)) {
			dec.add(Restrictions.like("terminalNo", terminalNo,MatchMode.ANYWHERE));
		}
		if(!SysUtil.isNull(mobile)){
			dec.add(Restrictions.eq("mobile", mobile));
		}
		if (!SysUtil.isNull(sellType)&&sellType!=0) {
			dec.add(Restrictions.eq("orderType", sellType));
		}
		if (!SysUtil.isNull(state)&&state!=-2) {
			if (state.equals(3)) {
				dec.add(Restrictions.or(Restrictions.eq("state", state), Restrictions.eq("state", Const.MainOrderState.THREAD_EXECUTING)));
			}else {
				dec.add(Restrictions.eq("state", state));
			}
		}		if (!SysUtil.isNull(reversalState)&&reversalState!=-3) {
			if (!SysUtil.isNull(state)&&state.equals(0)) {
				dec.add(Restrictions.or(Restrictions.eq("reversalState", state), Restrictions.eq("reversalState", Const.OrderReversalState.THREAD_EXECUTING)));
			}else {
				dec.add(Restrictions.eq("reversalState", reversalState));
			}
		}
		if (end2Date==null) {
			dec.addOrder(Order.desc("createTime"));
		} else {
			dec.addOrder(Order.desc("reversalTime"));
		}
		QueryResult queryResult = orderService.getScrollData(pageView
				.getFirstResult(), pageView.getMaxresult(), dec);
		if (Const.Usergroup.GRADE_ONE == groupId) {
			List<Mainorder> list = new ArrayList<Mainorder>();
			for (Object obj : queryResult.getResultlist()) {
				Object[] obj2 = (Object[])obj;
				Mainorder mainorder = (Mainorder) obj2[1];
				list.add(mainorder);
			}
			queryResult.getResultlist().removeAll(queryResult.getResultlist());
			queryResult.getResultlist().addAll(list);
		}
		pageView.setQueryResult(queryResult);
		return "listOrder2Customer";
	}
	
	/**
	 * 批量订单列表
	 * @return
	 * @throws Exception
	 */
	public String listBatchOrder() throws Exception {
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
				DetachedCriteria dec = DetachedCriteria
					.forClass(BatchOrder.class);
		int groupId=getSessionUser().getUsergroup().getGroupType();
		if (Const.GroupType.SUPPLY<groupId) {
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
		}
		if(!SysUtil.isNull(batchOrderId))
		{
			dec.add(Restrictions.eq("batchOrderId", batchOrderId));
		}
		if(!SysUtil.isNull(username))
		{
			dec.add(Restrictions.eq("userName", username));
		}
		if(!SysUtil.isNull(state))
		{
			if (state.equals(3)) {
				dec.add(Restrictions.or(Restrictions.eq("state", state), Restrictions.eq("state", Const.MainOrderState.THREAD_EXECUTING)));
			}else {
				dec.add(Restrictions.eq("state", state));
			}
		}
		if (!SysUtil.isNull(startDate)&&!SysUtil.isNull(endDate)) {
			dec.add(Restrictions.between("createTime", DateUtil.getMinDate(startDate), DateUtil.getMaxDate(endDate)));
		}
		dec.addOrder(Order.desc("createTime"));
		QueryResult queryResult = orderService.getScrollData(pageView
				.getFirstResult(), pageView.getMaxresult(), dec);
		if (Const.Usergroup.GRADE_ONE == groupId) {
			List<BatchOrder> list = new ArrayList<BatchOrder>();
			for (Object obj : queryResult.getResultlist()) {
				Object[] obj2 = (Object[])obj;
				BatchOrder mainorder = (BatchOrder) obj2[1];
				list.add(mainorder);
			}
			queryResult.getResultlist().removeAll(queryResult.getResultlist());
			queryResult.getResultlist().addAll(list);
		}
		pageView.setQueryResult(queryResult);
		return "listBatchOrder";
	}
	
	/**
	 * 查看批量订单
	 * @return
	 * @throws Exception
	 */
	public String viewBatchOrder() throws Exception {
		batchOrder = orderService.findBatchOrderById(batchOrderId);
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
			DetachedCriteria dec = DetachedCriteria
				.forClass(Mainorder.class);
		if(!SysUtil.isNull(mainOrderId))
		{
			dec.add(Restrictions.eq("mainOrderId", mainOrderId));
		}
		if(!SysUtil.isNull(mobile)){
			dec.add(Restrictions.eq("mobile", mobile));
		}
		if(!SysUtil.isNull(state)){
			if (state.equals(3)) {
				dec.add(Restrictions.or(Restrictions.eq("state", state), Restrictions.eq("state", Const.MainOrderState.THREAD_EXECUTING)));
			}else {
				dec.add(Restrictions.eq("state", state));
			}
			
		}
		if(!SysUtil.isNull(reversalState)){
			if (state.equals(0)) {
				dec.add(Restrictions.or(Restrictions.eq("reversalState", state), Restrictions.eq("reversalState", Const.OrderReversalState.THREAD_EXECUTING)));
			}else {
				dec.add(Restrictions.eq("reversalState", reversalState));
			}
			
		}
		dec.add(Restrictions.eq("batchOrderId", batchOrderId));
		QueryResult queryResult = orderService.getScrollData(pageView
				.getFirstResult(), pageView.getMaxresult(), dec);
		pageView.setQueryResult(queryResult);
		return "viewBatchOrder";
	}
	
	public void newFind(){
		
	}

	/**
	 * 执行
	 */
	@Privilege(permissionName = "order", privilegeAccess = Const.PrivilegeAccessConst.QUERY)
	@Override
	public String execute() throws Exception {
		newFind();
		list();
		Users user = (Users)this.getSession().get("user");
		int groupType = user.getUsergroup().getGroupType();
		if(groupType==1){
			return SUCCESS;
		}else{
			return "listForUser";
		}
		
	}
	
	/**
	 * 统计移动、电信……订单page
	 * @return
	 */
	public String statisticsOrderVoPage(){
		users = this.getSessionUser();
		isLocal = 255;
		if(users.getUsergroup().getGroupType()==Const.GroupType.GRADE_ONE){
//			usersListEx.add(users);
			 usersListExEx = userService.findChildUsers(users.getUserId());
		}else if(users.getUsergroup().getGroupType()<Const.GroupType.STAFF){
			usersListEx = userService.findAllUsersByGroup(Const.GroupType.GRADE_ONE);
			usersListExEx = userService.findAllUsersByGroup(Const.GroupType.GRADE_TWO);
		}
		 if(userIdEx!=null&&!"".equals(userIdEx)){
			 statisticsOrderVOList = orderService.findStatisticsOrderVOEx(userIdEx, isLocal,startDate ,endDate );
		 }else if(userIdExEx!=null&&!"".equals(userIdExEx)){
			 statisticsOrderVOList = orderService.findStatisticsOrderVOExEx(userIdExEx, isLocal,startDate ,endDate );
		 }else{
			 statisticsOrderVOList = orderService.findStatisticsOrderVOEx(users.getUserId(), isLocal,startDate ,endDate );
		 }
		 this.getSession().put("isLocal", isLocal);
		return "statisticsOrderVo";
	}
	
	/**
	 * 统计移动、电信……订单
	 * @return
	 */
	public String statisticsOrderVOList(){
		if(startDate!=null&&!"".equals(startDate)&&endDate!=null&&!"".equals(endDate)){
			startDate = DateUtil.getMinDate(startDate);
			endDate = DateUtil.getMaxDate(endDate);
		}
//		Users users_2 = null;
		 users = this.getSessionUser();
		 if(userIdEx!=null&&!"".equals(userIdEx)){
//			  users_2 = userService.findUserById(userIdEx);
			 statisticsOrderVOList = orderService.findStatisticsOrderVOEx(userIdEx, isLocal,startDate ,endDate );
		 }else if(userIdExEx!=null&&!"".equals(userIdExEx)){
//			 users_2 = userService.findUserById(userIdExEx);
			 statisticsOrderVOList = orderService.findStatisticsOrderVOExEx(userIdExEx, isLocal,startDate ,endDate );
		 }else{
			 statisticsOrderVOList = orderService.findStatisticsOrderVOEx(users.getUserId(), isLocal,startDate ,endDate );
		 }
		 
		 this.getHttpServletRequest().getSession().setAttribute("startDate", DateUtil.DateToString(startDate, "yyyy-MM-dd"));
		 this.getHttpServletRequest().getSession().setAttribute("endDate", DateUtil.DateToString(endDate, "yyyy-MM-dd"));
		 this.getHttpServletRequest().getSession().setAttribute("isLocal", isLocal);
//		 usersListEx = userService.findAllUsersByGroup(Const.Usergroup.GRADE_ONE);
//		 usersListExEx = userService.findAllUsersByGroup(Const.Usergroup.GRADE_TWO);
		 if(users.getUsergroup().getGroupType()==Const.GroupType.GRADE_ONE){
//				usersListEx.add(users);
				 usersListExEx = userService.findChildUsers(users.getUserId());
			}else if(users.getUsergroup().getGroupType()<Const.GroupType.STAFF){
				usersListEx = userService.findAllUsersByGroup(Const.Usergroup.GRADE_ONE);
				usersListExEx = userService.findAllUsersByGroup(Const.Usergroup.GRADE_TWO);
			}
		return "statisticsOrderVOList";
	}
	
	/**
	 * 订单详细列表
	 * @return
	 * @throws Exception
	 */
	public String listOrderdetail() throws Exception{
		orderdetails=orderService.findOrderdetailsByOrderId(mainOrderId);
		return "listOrderdetail";
	}
	
	/**
	 * 冲正记录列表
	 * @return
	 * @throws Exception
	 */
	public String listReversalResponse() throws Exception{
		DetachedCriteria dec=DetachedCriteria.forClass(AirReversalResponseNew.class);
		airReversalResponseNews = orderService.findByDetachedCriteria(dec);
		return "listReversalResponse";
	}

	/**
	 * 显示订单信息
	 * @return
	 * @throws Exception
	 */
	public String mainOrderShowModal() throws Exception{
		mainorder = orderService.findOrderById(mainOrderId);
		return "mainOrderShowModal";
	}
	
	/**
	 * 统计订单
	 * @return
	 * @throws Exception
	 */
	public String listMainOrderVO() throws Exception {
		Date sDate = new Date();
		Date eDate = new Date();
		if (!SysUtil.isNull(this.startDate)&&!SysUtil.isNull(this.endDate)) {
			sDate = DateUtil.getMinDate(startDate);
			eDate = DateUtil.getMaxDate(endDate);
		}else {
			sDate = DateUtil.getMinDate(sDate);
			eDate = DateUtil.getMaxDate(eDate);
		}
		// 用户只能查关于自己的订单统计
		if (getSessionUser().getUsergroup().getGroupType()>Const.GroupType.STAFF) {
			mainorderVOs = orderService.listMainorderVOs(sDate, eDate, getSessionUser().getUserId());
		} else {
			mainorderVOs = orderService.listMainorderVOs(sDate, eDate, null);
		}
		return "listMainorderVO";
	}
	
	/**
	 * 列出需要冲正的列表
	 * @return
	 * @throws Exception
	 */
	public String listReversalOrder() throws Exception {
		list = orderService.findReversalOrderByMobileAndAmount(mobile, amount);
		return "listReversalOrder";
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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}


	public String getTerminalNo() {
		return terminalNo;
	}


	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}


	public List<AirReversalResponseNew> getAirReversalResponseNews() {
		return airReversalResponseNews;
	}


	public void setAirReversalResponseNews(
			List<AirReversalResponseNew> airReversalResponseNews) {
		this.airReversalResponseNews = airReversalResponseNews;
	}


	public Mainorder getMainorder() {
		return mainorder;
	}


	public void setMainorder(Mainorder mainorder) {
		this.mainorder = mainorder;
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

	public Integer getSellType() {
		return sellType;
	}

	public void setSellType(Integer sellType) {
		this.sellType = sellType;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Double getStockMoney() {
		return stockMoney;
	}

	public void setStockMoney(Double stockMoney) {
		this.stockMoney = stockMoney;
	}

	public Double getRetailMoney() {
		return retailMoney;
	}

	public void setRetailMoney(Double retailMoney) {
		this.retailMoney = retailMoney;
	}

	public Integer getReversalState() {
		return reversalState;
	}

	public void setReversalState(Integer reversalState) {
		this.reversalState = reversalState;
	}

	public List<MainorderVO> getMainorderVOs() {
		return mainorderVOs;
	}

	public void setMainorderVOs(List<MainorderVO> mainorderVOs) {
		this.mainorderVOs = mainorderVOs;
	}

	public List<GoodsType> getGoodsTypeList() {
		return goodsTypeList;
	}

	public void setGoodsTypeList(List<GoodsType> goodsTypeList) {
		this.goodsTypeList = goodsTypeList;
	}

	public Integer getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(Integer goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public Date getStart2Date() {
		return start2Date;
	}

	public void setStart2Date(Date start2Date) {
		this.start2Date = start2Date;
	}

	public Date getEnd2Date() {
		return end2Date;
	}

	public void setEnd2Date(Date end2Date) {
		this.end2Date = end2Date;
	}

	public Integer getIsNeedManual() {
		return isNeedManual;
	}

	public void setIsNeedManual(Integer isNeedManual) {
		this.isNeedManual = isNeedManual;
	}

	public String getManualUserName() {
		return manualUserName;
	}

	public void setManualUserName(String manualUserName) {
		this.manualUserName = manualUserName;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getGroupIdSelect() {
		return groupIdSelect;
	}

	public void setGroupIdSelect(Integer groupIdSelect) {
		this.groupIdSelect = groupIdSelect;
	}

	public Integer getIsTerminal() {
		return isTerminal;
	}

	public void setIsTerminal(Integer isTerminal) {
		this.isTerminal = isTerminal;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public List<Mainorder> getList() {
		return list;
	}

	public void setList(List<Mainorder> list) {
		this.list = list;
	}

	public int getIsLocal() {
		return isLocal;
	}

	public void setIsLocal(int isLocal) {
		this.isLocal = isLocal;
	}

	public List<StatisticsOrderVO> getStatisticsOrderVOList() {
		return statisticsOrderVOList;
	}

	public void setStatisticsOrderVOList(
			List<StatisticsOrderVO> statisticsOrderVOList) {
		this.statisticsOrderVOList = statisticsOrderVOList;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public List<Users> getUsersListEx() {
		return usersListEx;
	}

	public void setUsersListEx(List<Users> usersListEx) {
		this.usersListEx = usersListEx;
	}

	public List<Users> getUsersListExEx() {
		return usersListExEx;
	}

	public void setUsersListExEx(List<Users> usersListExEx) {
		this.usersListExEx = usersListExEx;
	}

	public String getUserIdEx() {
		return userIdEx;
	}

	public void setUserIdEx(String userIdEx) {
		this.userIdEx = userIdEx;
	}

	public String getUserIdExEx() {
		return userIdExEx;
	}

	public void setUserIdExEx(String userIdExEx) {
		this.userIdExEx = userIdExEx;
	}

	public BatchOrder getBatchOrder() {
		return batchOrder;
	}

	public void setBatchOrder(BatchOrder batchOrder) {
		this.batchOrder = batchOrder;
	}

	public String getBatchOrderId() {
		return batchOrderId;
	}

	public void setBatchOrderId(String batchOrderId) {
		this.batchOrderId = batchOrderId;
	}

	
}
