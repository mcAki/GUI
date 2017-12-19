package com.sys.volunteer.supply;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.sys.volunteer.baseaction.BaseListAction;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.Privilege;
import com.sys.volunteer.common.SysUtil;
import com.sys.volunteer.pagemodel.PageView;
import com.sys.volunteer.pagemodel.QueryResult;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.supply.usage.ISupply;
import com.sys.volunteer.users.UserService;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class ListSupplyAction extends BaseListAction implements Preparable{
	@Resource
	UserService userService;
	@Resource
	SupplyService supplyService;
    
	
	private String supplyName;
	
//	private Users users;
	
	private String userId;
	
	private Integer supplyId;
	
	private Integer isUsed;
	
	private String username;
	
	private String mobile;
	
	private Integer supplyType;
	
	private List<ISupply> supplyList;
	
	@Override
	public void prepare() throws Exception {
		//goodsList = (List<Goods>)goodsService.findAll(Goods.class); 
		supplyList = (List<ISupply>)supplyService.findAll(Supply.class);
		Supply supply=new Supply();
		supply.setId(0);
		supply.setSupplyName("请选择供货商");
		supplyList.add(0, supply);
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
				.forClass(Supply.class);
		if(!SysUtil.isNull(supplyId)&&supplyId!=0){
			dec.add(Restrictions.eq("id",supplyId));
		}
		if(!SysUtil.isNull(isUsed)&&isUsed!=-1){
			dec.add(Restrictions.eq("isUsed",isUsed));
		}
		if (!SysUtil.isNull(supplyType)) {
			switch (supplyType) {
			case 1:
			case 2:
				dec.add(Restrictions.eq("supplyType", supplyType));
				break;
			default:
				break;
			}
		}
		QueryResult queryResult = supplyService.getScrollData(pageView
				.getFirstResult(), pageView.getMaxresult(), dec);
		pageView.setQueryResult(queryResult);
	}
	
	/**
	 * 按名字搜索用户
	 * @return
	 * @throws Exception
	 */
	public String searchSupplyForLog() throws Exception {
		DetachedCriteria dec=DetachedCriteria.forClass(Users.class);
		dec.add(Restrictions.eq("isDelete", 0));
		if(!SysUtil.isNull(username))
		{
			dec.add(Restrictions.eq("userName", username));
		}
		pageView=new PageView(getPageSize(), getPageIndex());
		QueryResult queryResult=userService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), dec);
		pageView.setQueryResult(queryResult);
		return "listSupplyByName";
	}
	
	
	/**
	 * 用户列表
	 */
	public String listSupplyForLog() throws Exception{
		DetachedCriteria dec=DetachedCriteria.forClass(Supply.class);
		pageView=new PageView(this.getPageSize(), this.getPageIndex());
		dec.add(Restrictions.eq("isUsed", 1));
		QueryResult queryResult=userService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), dec);
		pageView.setQueryResult(queryResult);
		return "listSupplyForLog";
	}
	
	/**
	 * 用户列表
	 */
	public String listSupplyForCardLib() throws Exception{
		DetachedCriteria dec=DetachedCriteria.forClass(Supply.class);
		pageView=new PageView(this.getPageSize(), this.getPageIndex());
		dec.add(Restrictions.eq("supplyType", 1));
		dec.add(Restrictions.eq("sellType", 2));
		dec.add(Restrictions.eq("isUsed", 1));
		QueryResult queryResult=userService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), dec);
		pageView.setQueryResult(queryResult);
		return "listSupplyForLog";
	}
		

	/**
	 * 执行
	 */
	@Privilege(permissionName = "supply", privilegeAccess = Const.PrivilegeAccessConst.QUERY)
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

	public Integer getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(Integer supplyType) {
		this.supplyType = supplyType;
	}

	public List<ISupply> getSupplyList() {
		return supplyList;
	}

	public void setSupplyList(List<ISupply> supplyList) {
		this.supplyList = supplyList;
	}

	public Integer getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}
	
}
