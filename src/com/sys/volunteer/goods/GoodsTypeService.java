package com.sys.volunteer.goods;

import java.util.List;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.GoodsType;

public interface GoodsTypeService extends IDao {

	/**
	 * 增加商品类型
	 * @param goodsType
	 */
	public void addGoodsType(GoodsType goodsType);
	
	public List<GoodsType> listGoodsTypeByGoodsFlag(int goodsFlag);
}
