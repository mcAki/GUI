package com.sys.volunteer.goods;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.GoodsType;

@Service
@Transactional
public class GoodsTypeServiceBean extends CommonDao implements GoodsTypeService {

	@Override
	public void addGoodsType(GoodsType goodsType) {
		this.save(goodsType);
	}

	@Override
	public List<GoodsType> listGoodsTypeByGoodsFlag(int goodsFlag) {
		DetachedCriteria dec=DetachedCriteria.forClass(GoodsType.class);
		dec.add(Restrictions.eq("goodsFlag", goodsFlag));
		List<GoodsType> list=this.findByDetachedCriteria(dec);
		return list;
	}

}
