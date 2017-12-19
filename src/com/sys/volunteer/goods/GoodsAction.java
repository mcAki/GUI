package com.sys.volunteer.goods;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.sys.volunteer.area.AreaService;
import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.Privilege;
import com.sys.volunteer.common.SysUtil;
import com.sys.volunteer.pojo.Area;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.GoodsType;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.supply.SupplyService;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class GoodsAction extends BaseAction implements Preparable {

	@Resource
	GoodsTypeService goodsTypeService;
	@Resource
	GoodsService goodsService;
	@Resource
	SupplyService supplyService;
	@Resource
	AreaService areaService;
	
	private List<GoodsType> goodsTypes;
	
	private Goods goods;
	
	private Integer goodsId;
	
	private Integer provinceCode;
	
	@Override
	public void prepare() throws Exception {
		DetachedCriteria dec=DetachedCriteria.forClass(GoodsType.class);
		dec.add(Restrictions.gt("id", 0));
		goodsTypes = (List<GoodsType>)goodsTypeService.findByDetachedCriteria(dec);
	}

	
	/**
	 * 增加商品页面
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "goods", privilegeAccess = Const.PrivilegeAccessConst.ADD)
	public String add() throws Exception{
		goods = new Goods();
		return "do";
	}
	
	/**
	 * 增加商品提交
	 * @return
	 * @throws Exception
	 */
	public String doAdd() throws Exception{
		//Supply supply=supplyService.findSupplyById(goods.getSupply().getId());
		//goods.setSupplyName(supply.getSupplyName());
		goods.setIsUsed(1);
		if (!SysUtil.isNull(provinceCode)) {
			Area area = areaService.findById(provinceCode);
			goods.setProvince(area.getProvince());
			goods.setProvinceCode(area.getProvinceCode());
		}else {
			goods.setProvince(null);
			goods.setProvinceCode(null);
		}
		goodsService.save(goods);
		return ShowMessage(MSG_TYPE_NORMAL, "增加成功", "点击返回", "list!listGoods.do", 0);
	}
	
	/**
	 * 修改商品页面
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "goods", privilegeAccess = Const.PrivilegeAccessConst.MODIFY)
	public String update() throws Exception{
		goods=goodsService.findById(goodsId);
		return "update";
	}
	
	/**
	 * 修改商品提交
	 * @return
	 * @throws Exception
	 */
	public String doUpdate() throws Exception{
		//Supply supply=supplyService.findSupplyById(goods.getSupply().getId());
		//goods.setSupplyName(supply.getSupplyName());
		if (!SysUtil.isNull(provinceCode)) {
			Area area = areaService.findById(provinceCode);
			goods.setProvince(area.getProvince());
			goods.setProvinceCode(area.getProvinceCode());
		}else {
			goods.setProvince(null);
			goods.setProvinceCode(null);
		}
		goodsService.update(goods);
		//所有对应的supplyinterface的商品名称也要修改
		Map<Integer, SupplyInterface> map = SupplyKernel.getInstance().getSupplyInterfaceMapKeyId();
		for (Entry<Integer, SupplyInterface> entry : map.entrySet()) {
			SupplyInterface supplyInterface = entry.getValue();
			if (supplyInterface.getGoods().getGoodsId().equals(goods.getGoodsId())) {
				supplyInterface.setGoodsName(goods.getGoodsName());
				//supplyInterface.setValue(goods.getValue());
				SupplyKernel.getInstance().updateSupplyInterface(entry.getKey(), supplyInterface);
			}
		}
		return ShowMessage(MSG_TYPE_NORMAL, "修改成功", "点击返回", "list!listGoods.do", 0);
	}
	
	

	/**
	 * 禁用商品
	 * @return
	 * @throws Exception
	 */
	public String doDel() throws Exception{
		Goods goods = goodsService.findById(goodsId);
		goodsService.updateGoodsIsUsed(goods, 0);
		return ShowMessage(MSG_TYPE_NORMAL, "禁用成功", "点击返回", "list!listGoods.do", 0);
	}
	
	/**
	 * 恢复商品
	 * @return
	 * @throws Exception
	 */
	public String doRecover() throws Exception{
		Goods goods = goodsService.findById(goodsId);
		goodsService.updateGoodsIsUsed(goods, 1);
		return ShowMessage(MSG_TYPE_NORMAL, "恢复成功", "点击返回", "list!listGoods.do", 0);
	}
	
	public List<GoodsType> getGoodsTypes() {
		return goodsTypes;
	}

	public void setGoodsTypes(List<GoodsType> goodsTypes) {
		this.goodsTypes = goodsTypes;
	}

	
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}


	public Integer getGoodsId() {
		return goodsId;
	}


	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}


	public Integer getProvinceCode() {
		return provinceCode;
	}


	public void setProvinceCode(Integer provinceCode) {
		this.provinceCode = provinceCode;
	}

}
