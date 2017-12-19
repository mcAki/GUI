package com.sys.volunteer.goods;

import java.util.List;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.AreaCode;
import com.sys.volunteer.pojo.Goods;

public interface GoodsService extends IDao {

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public Goods findById(int id);
	
	/**
	 * 根据类型查找列表
	 * @param type
	 * @return
	 */
	public java.util.List<Goods> getGoodsListByType(Integer type);
	
	/**
	 * 根据标记查找列表
	 * @param flag
	 * @return
	 */
	public List<Goods> getGoodsListByFlag(Integer flag);
	
	/**
	 * 根据供货商和商品类型查找
	 * @param supplyId
	 * @param goodsTypeId
	 * @return
	 */
	public Goods findGoodsBySupplyIdAndTypeId(int supplyId,int goodsTypeId);

	/**
	 * 更新商品状态
	 * @param goods
	 * @param isUsed
	 */
	public void updateGoodsIsUsed(Goods goods, int isUsed);

	public Goods findByTypeAndValue(int type, double value);
	
	public Goods findByAreaCodeAndValue(AreaCode areaCode,double value);

	public Goods findByFlagProvinceCodeValue(int flag, int provinceCode, double value);

	public Goods findByFlagProvinceCode(int flag, int provinceCode);

	public Goods findByKACardId(Integer KACardId);
	
}
