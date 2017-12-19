package com.sys.volunteer.goods;

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
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.GoodsType;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class ListGoodsAction extends BaseListAction implements Preparable{

	@Resource
	private GoodsService goodsService;
	@Resource
	private GoodsTypeService goodsTypeService;
	
	private Integer type;
	
	private Integer isUsed;
	
	private List<GoodsType> goodsTypes;
	
	private List<Goods> goodsList;
	
	@Override
	public void prepare() throws Exception {
		goodsTypes = (List<GoodsType>)goodsTypeService.findAll(GoodsType.class);
		goodsTypes.get(0).setGoodsTypeName("请选择商品类型");
	}
	
	public String listGoodsByType(){
		goodsList=goodsService.getGoodsListByType(type);
		return SUCCESS;
	}
	
	@Privilege(permissionName = "goods", privilegeAccess = Const.PrivilegeAccessConst.QUERY)
	public String listGoods() throws Exception{
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		DetachedCriteria dec=DetachedCriteria.forClass(Goods.class);
		dec.add(Restrictions.ne("goodsFlag", Const.GoodsFlag.NONE));
		if (type!=null&&type!=0) {
			dec.add(Restrictions.eq("goodsType.id", type));
		}
		if (!SysUtil.isNull(isUsed)&&isUsed!=-1) {
			dec.add(Restrictions.eq("isUsed", isUsed));
		}
		QueryResult queryResult = goodsService.getScrollData(pageView
				.getFirstResult(), pageView.getMaxresult(), dec);
		pageView.setQueryResult(queryResult);
		return "goodsList";
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public List<GoodsType> getGoodsTypes() {
		return goodsTypes;
	}

	public void setGoodsTypes(List<GoodsType> goodsTypes) {
		this.goodsTypes = goodsTypes;
	}

	public Integer getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}
}
