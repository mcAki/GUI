package com.sys.volunteer.supplyinterface;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.sys.volunteer.baseaction.BaseListAction;
import com.sys.volunteer.common.SysUtil;
import com.sys.volunteer.goods.GoodsService;
import com.sys.volunteer.goods.GoodsTypeService;
import com.sys.volunteer.pagemodel.PageView;
import com.sys.volunteer.pagemodel.QueryResult;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.GoodsType;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.supply.usage.ISupply;
import com.sys.volunteer.vo.SppinUsergroupVo;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class ListInterfacetypeAction extends BaseListAction implements Preparable{
	@Resource
	SupplyInterfaceService supplyInterfaceService;
	@Resource
	private GoodsService goodsService;
	@Resource
	private SupplyService supplyService;
	@Resource
	private GoodsTypeService goodsTypeService;
    
	private String interfaceName;
	
	private Integer flag;
	
	private List<Goods> goodsList;
	
	private List<ISupply> supplyList;
	
	private List<GoodsType> goodsTypeList;
	
	private Integer supplyId;
	
	private Integer goodsId;
	
	private Integer goodsTypeId;
	
	private Integer state;
	
	private Integer canReverse;
	
	private String goodsName;
	
	private List<SppinUsergroupVo> sppinUsergroupList;
	
	private Integer usergroupId;
	
	private Double value;
	
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
	}
	
	/**
	 * 按名字搜索用户
	 * @return
	 * @throws Exception
	 */
	public String searchInterfaceForSupply() throws Exception {
		DetachedCriteria dec=DetachedCriteria.forClass(SupplyInterface.class);
		if(!SysUtil.isNull(interfaceName))
		{
			dec.add(Restrictions.eq("interfaceName", interfaceName));
		}
		if (!SysUtil.isNull(flag)) {
			dec.add(Restrictions.eq("flag", flag));
		}
		pageView=new PageView(getPageSize(), getPageIndex());
		QueryResult queryResult=supplyInterfaceService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), dec);
		pageView.setQueryResult(queryResult);
		return "listInterfaceByName";
	}
	
	/**
	 * 用户列表
	 */
	public String listInterface() throws Exception{
		DetachedCriteria dec=DetachedCriteria.forClass(SupplyInterface.class);
		if (!SysUtil.isNull(supplyId)&&supplyId!=0) {
			dec.add(Restrictions.eq("supply.id", supplyId));
		}
		if (!SysUtil.isNull(goodsId)&&goodsId>0) {
			dec.add(Restrictions.eq("goods.id", goodsId));
		}
		if (!SysUtil.isNull(goodsTypeId)&&goodsTypeId>0) {
			dec.createAlias("goods", "goods");
			dec.add(Restrictions.eq("goods.goodsType.id", goodsTypeId));
		}
		if (!SysUtil.isNull(goodsName)&&goodsName.length()>0) {
			dec.add(Restrictions.like("goodsName", goodsName, MatchMode.ANYWHERE));
		}
		if (!SysUtil.isNull(state)&&state!=-1) {
			dec.add(Restrictions.eq("state", state));
		}
		if (!SysUtil.isNull(canReverse)&&canReverse!=-1) {
			dec.add(Restrictions.eq("canReverse", canReverse));
		}
		pageView=new PageView(this.getPageSize(), this.getPageIndex());
		QueryResult queryResult=supplyInterfaceService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), dec);
		if (!SysUtil.isNull(goodsTypeId)&&goodsTypeId>0) {
			List<SupplyInterface> list = new ArrayList<SupplyInterface>();
			for (Object obj : queryResult.getResultlist()) {
				Object[] obj2 = (Object[])obj;
				SupplyInterface supplyInterface = (SupplyInterface) obj2[1];
				list.add(supplyInterface);
			}
			queryResult.getResultlist().removeAll(queryResult.getResultlist());
			queryResult.getResultlist().addAll(list);
		}
		pageView.setQueryResult(queryResult);
		return SUCCESS;
	}
	
	/**
	 * spi对应usergroup(页面)
	 * @return
	 */
	public String setSupplyUsergroup(){
		supplyList = (List<ISupply>)supplyService.findAll(Supply.class);
		Supply supply=new Supply();
		supply.setId(0);
		supply.setSupplyName("请选择供货商");
		supplyList.add(0, supply);
		try {
			sppinUsergroupList = supplyInterfaceService.queryByCondition(usergroupId, supplyId, state, canReverse, value,goodsId,goodsTypeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "setSupplyUsergroup";
	}
	

	/**
	 * 查询
	 * @return
	 */
	public String findByCondition(){
		supplyList = (List<ISupply>)supplyService.findAll(Supply.class);
		Supply supply=new Supply();
		supply.setId(0);
		supply.setSupplyName("请选择供货商");
		supplyList.add(0, supply);
		try {
			sppinUsergroupList = supplyInterfaceService.queryByCondition(usergroupId, supplyId, state, canReverse, value,goodsId,goodsTypeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "setSupplyUsergroup";
	}
	
	/**
	 * 验证数据
	 */
	@Override
	public void validate() {
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getCanReverse() {
		return canReverse;
	}

	public void setCanReverse(Integer canReverse) {
		this.canReverse = canReverse;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public List<SppinUsergroupVo> getSppinUsergroupList() {
		return sppinUsergroupList;
	}

	public void setSppinUsergroupList(List<SppinUsergroupVo> sppinUsergroupList) {
		this.sppinUsergroupList = sppinUsergroupList;
	}

	public Integer getUsergroupId() {
		return usergroupId;
	}

	public void setUsergroupId(Integer usergroupId) {
		this.usergroupId = usergroupId;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
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


}
