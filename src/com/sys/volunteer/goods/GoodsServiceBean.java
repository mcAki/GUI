package com.sys.volunteer.goods;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.AreaCode;
import com.sys.volunteer.pojo.Goods;

@Service
@Transactional
public class GoodsServiceBean extends CommonDao implements GoodsService {

	@Override
	public Goods findById(int id) {
		Goods goods=(Goods)findById(Goods.class, id);
		return goods;
	}
	
	@Override
	public List<Goods> getGoodsListByType(Integer type) {
		DetachedCriteria dec=DetachedCriteria.forClass(Goods.class);
		if(type!=0){
			dec.add(Restrictions.eq("goodsType", type));
		}
		List<Goods> list=this.findByDetachedCriteria(dec);
		return list;
	}

	@Override
	public List<Goods> getGoodsListByFlag(Integer flag) {
		DetachedCriteria dec=DetachedCriteria.forClass(Goods.class);
		dec.add(Restrictions.eq("goodsFlag", flag));
		dec.add(Restrictions.eq("isUsed", 1));
		List<Goods> list=this.findByDetachedCriteria(dec);
		return list;
	}

	@Override
	public Goods findGoodsBySupplyIdAndTypeId(int supplyId, int goodsTypeId) {
		DetachedCriteria dec=DetachedCriteria.forClass(Goods.class);
		dec.add(Restrictions.eq("supply.id", supplyId));
		dec.add(Restrictions.eq("goodsType.id", goodsTypeId));
		Goods goods=null;
		List<Goods> list=this.findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			goods=list.get(0);
		}
		return goods;
	}
	
	@Override
	public void updateGoodsIsUsed(Goods goods,int isUsed){
		goods.setIsUsed(isUsed);
		update(goods);
	}

	@Override
	public Goods findByTypeAndValue(int type,double value){
		DetachedCriteria dec = DetachedCriteria.forClass(Goods.class);
		dec.add(Restrictions.eq("goodsType.id", type));
		dec.add(Restrictions.eq("value", value));
		dec.add(Restrictions.eq("isUsed", 1));
		List<Goods> list = findByDetachedCriteria(dec);
		if (!list.isEmpty()&&list.get(0)!=null) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Goods findByAreaCodeAndValue(AreaCode areaCode, double value) {
		Goods goods = null;
		int flag = 0;
		switch (areaCode.getBusinessType()) {
		case 1:
			//移动
			flag = 10;
			break;
		case 2:
			//电信
			flag = 11;
			break;
		case 3:
			//联通
			flag = 12;
			break;
		default:
			break;
		}
		goods = findByFlagProvinceCodeValue(flag,areaCode.getProvinceCode(),value);
		if (goods == null) {
			if (areaCode.getIsGuangdong()==1) {
				//广东
				goods = findByFlagProvinceCodeValue(flag,1200,value);
			}else {
				//全国
				goods = findByFlagProvinceCodeValue(flag,0,value);
			}
		}
		return goods;
	}
	
	@Override
	public Goods findByFlagProvinceCodeValue(int flag,int provinceCode,double value){
		DetachedCriteria dec = DetachedCriteria.forClass(Goods.class);
		dec.add(Restrictions.eq("goodsFlag", flag));
		dec.add(Restrictions.eq("provinceCode", provinceCode));
		dec.add(Restrictions.eq("value", value));
		dec.add(Restrictions.eq("isUsed", 1));
		List<Goods> list = findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public Goods findByFlagProvinceCode(int flag,int provinceCode){
		DetachedCriteria dec = DetachedCriteria.forClass(Goods.class);
		dec.add(Restrictions.eq("goodsFlag", flag));
		dec.add(Restrictions.eq("provinceCode", provinceCode));
		dec.add(Restrictions.eq("isUsed", 1));
		List<Goods> list = findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public Goods findByKACardId(Integer kaCardId) {
		DetachedCriteria dec = DetachedCriteria.forClass(Goods.class);
		dec.add(Restrictions.eq("kaCardId", kaCardId));
		dec.add(Restrictions.eq("isUsed", 1));
		List<Goods> list = findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}
