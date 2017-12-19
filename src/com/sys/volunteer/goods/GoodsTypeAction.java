package com.sys.volunteer.goods;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.Privilege;
import com.sys.volunteer.pojo.GoodsType;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class GoodsTypeAction extends BaseAction{

	@Resource
	GoodsTypeService goodsTypeService;
	
	private GoodsType goodsType;
	
	private Integer goodsTypeId;
	
	/**
	 * 增加商品类型页面
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "goodsType", privilegeAccess = Const.PrivilegeAccessConst.ADD)
	public String add() throws Exception{
		goodsType = new GoodsType();
		return "do";
	}
	
	/**
	 * 提交商品类型
	 * @return
	 * @throws Exception
	 */
	public String doAdd() throws Exception{
		goodsTypeService.addGoodsType(goodsType);
		return ShowMessage(MSG_TYPE_NORMAL, "增加成功", "点击返回", "list.do", 0);
	}
	
	/**
	 * 修改商品类型页面
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "goodsType", privilegeAccess = Const.PrivilegeAccessConst.MODIFY)
	public String update() throws Exception{
		goodsType=(GoodsType)goodsTypeService.findById(GoodsType.class, goodsTypeId);
		return "update";
	}
	
	/**
	 * 修改商品类型提交
	 * @return
	 * @throws Exception
	 */
	public String doUpdate() throws Exception{
		goodsTypeService.update(goodsType);
		return ShowMessage(MSG_TYPE_NORMAL, "修改成功", "点击返回", "list.do", 0);
	}

	public Integer getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(Integer goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public GoodsType getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(GoodsType goodsType) {
		this.goodsType = goodsType;
	}
}
