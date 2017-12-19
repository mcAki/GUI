package com.sys.volunteer.cardLib;

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
import com.sys.volunteer.pojo.CardLib;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.Useraccountdealdetail;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.supply.usage.ISupply;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class ListCardLibAction extends BaseListAction implements Preparable{

	@Resource
	private CardLibService cardLibService;
	@Resource
	private GoodsService goodsService;
	@Resource
	private SupplyService supplyService;
	
	private String supplyName;
	
	private Integer state;
	
	private Date startDate;
	private Date endDate;
	
	private Date startIndate;
	
	private Date endIndate;
	
	private String goodsName;
	
	private List<Goods> goodsList;
	
	private List<ISupply> supplyList;
	
	private Integer supplyId;
	
	private Integer goodsId;	
	
	private String username;
	
	private Date startExpireTime;
	
	private Date endExpireTime;
	
	private Integer sellType;
	
	private Integer supplyType;
	
	
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
	 * 用户用卡密列表
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "cardLib", privilegeAccess = Const.PrivilegeAccessConst.QUERY)
	public String listCardLib() throws Exception{
		cardLibService.updateExpireCardLib();
		DetachedCriteria dec=DetachedCriteria.forClass(CardLib.class);
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
		if (!SysUtil.isNull(goodsId)&&goodsId>0) {
			dec.add(Restrictions.eq("goods.id", goodsId));
		}
		if (!SysUtil.isNull(sellType)&&sellType!=0) {
			dec.add(Restrictions.eq("sellType", sellType));
		}
		if (!(SysUtil.isNull(startDate)||SysUtil.isNull(endDate))) {
			Date minDate=DateUtil.getMinDate(startDate);
			Date maxDate=DateUtil.getMaxDate(endDate);
			dec.add(Restrictions.between("buyTime", minDate, maxDate));
		}
		dec.addOrder(Order.desc("buyTime"));
		pageView=new PageView(getPageSize(), getPageIndex());
		QueryResult queryResult=cardLibService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), dec);
		//这里处理多表查询返回的对象数组
		List list = new ArrayList();
		if (queryResult.getResultlist().size()>0&&queryResult.getResultlist().get(0) instanceof Object[]) {
			for (int i = 0; i < queryResult.getResultlist().size(); i++) {
				Object[] objects = (Object[])queryResult.getResultlist().get(i);
				CardLib cardLib = (CardLib)objects[1];
				list.add(cardLib);
			}
			queryResult.getResultlist().clear();
			queryResult.getResultlist().addAll(list);
		}
		pageView.setQueryResult(queryResult);
		return "listCardLib";
	}
	
	
	/**
	 * 管理员用卡密列表
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "cardLib", privilegeAccess = Const.PrivilegeAccessConst.QUERY)
	public String listCardLibForAdmin() throws Exception{
		cardLibService.updateExpireCardLib();
		DetachedCriteria dec=DetachedCriteria.forClass(CardLib.class);
		if (!SysUtil.isNull(supplyId)&&supplyId!=0) {
			dec.add(Restrictions.eq("supply.id", supplyId));
		}
		if (!(SysUtil.isNull(startDate)||SysUtil.isNull(endDate))) {
			Date minDate=DateUtil.getMinDate(startDate);
			Date maxDate=DateUtil.getMaxDate(endDate);
			dec.add(Restrictions.between("buyTime", minDate, maxDate));
		}
		if (!(SysUtil.isNull(startIndate)||SysUtil.isNull(endIndate))) {
			Date minDate=DateUtil.getMinDate(startIndate);
			Date maxDate=DateUtil.getMaxDate(endIndate);
			dec.add(Restrictions.between("indate", minDate, maxDate));
		}
		if (!(SysUtil.isNull(startExpireTime)||SysUtil.isNull(endExpireTime))) {
			Date minDate=DateUtil.getMinDate(startExpireTime);
			Date maxDate=DateUtil.getMaxDate(endExpireTime);
			dec.add(Restrictions.between("expireTime", minDate, maxDate));
		}
		if (!SysUtil.isNull(state)&&state!=0) {
			dec.add(Restrictions.eq("state", state));
		}
		if (!SysUtil.isNull(goodsId)&&goodsId>0) {
			dec.add(Restrictions.eq("goods.id", goodsId));
		}
		if (!SysUtil.isNull(username)) {
			dec.add(Restrictions.like("userName", username, MatchMode.ANYWHERE));
		}
		if (!SysUtil.isNull(sellType)&&sellType!=0) {
			dec.add(Restrictions.eq("sellType", sellType));
		}
		if (!SysUtil.isNull(supplyType)&&supplyType!=0) {
			dec.add(Restrictions.eq("supplyType", supplyType));
		}
		dec.addOrder(Order.desc("buyTime"));
		pageView=new PageView(getPageSize(), getPageIndex());
		QueryResult queryResult=cardLibService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), dec);
		pageView.setQueryResult(queryResult);
		return "listCardLibForAdmin";
	}


	public String getSupplyName() {
		return supplyName;
	}


	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
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


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	public Date getStartIndate() {
		return startIndate;
	}


	public void setStartIndate(Date startIndate) {
		this.startIndate = startIndate;
	}


	public Date getEndIndate() {
		return endIndate;
	}


	public void setEndIndate(Date endIndate) {
		this.endIndate = endIndate;
	}


	public String getGoodsName() {
		return goodsName;
	}


	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public List<ISupply> getSupplyList() {
		return supplyList;
	}

	public void setSupplyList(List<ISupply> supplyList) {
		this.supplyList = supplyList;
	}

	public Integer getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(Integer supplyId) {
		this.supplyId = supplyId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getSellType() {
		return sellType;
	}

	public void setSellType(Integer sellType) {
		this.sellType = sellType;
	}

	public Integer getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(Integer supplyType) {
		this.supplyType = supplyType;
	}

	public Date getStartExpireTime() {
		return startExpireTime;
	}

	public void setStartExpireTime(Date startExpireTime) {
		this.startExpireTime = startExpireTime;
	}

	public Date getEndExpireTime() {
		return endExpireTime;
	}

	public void setEndExpireTime(Date endExpireTime) {
		this.endExpireTime = endExpireTime;
	}


}
