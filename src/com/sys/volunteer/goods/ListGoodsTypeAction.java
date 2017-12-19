package com.sys.volunteer.goods;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sys.volunteer.baseaction.BaseListAction;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.Privilege;
import com.sys.volunteer.pagemodel.PageView;
import com.sys.volunteer.pagemodel.QueryResult;
import com.sys.volunteer.pojo.GoodsType;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class ListGoodsTypeAction extends BaseListAction {

	@Resource
	private GoodsTypeService goodsTypeService;
	
	/**
	 * 商品类型列表
	 * @throws Exception
	 */
	@Privilege(permissionName = "goodsType", privilegeAccess = Const.PrivilegeAccessConst.QUERY)
	public void list() throws Exception{
		pageView = new PageView(this.getPageSize(), this.getPageIndex());
		DetachedCriteria dec=DetachedCriteria.forClass(GoodsType.class);
		dec.add(Restrictions.ne("id", Const.GoodsFlag.NONE));
		QueryResult queryResult = goodsTypeService.getScrollData(pageView
				.getFirstResult(), pageView.getMaxresult(), dec);
		pageView.setQueryResult(queryResult);
	}
	
	/**
	 * 执行
	 */
	@Override
	public String execute() throws Exception {
		list();
		return SUCCESS;
	}
}
