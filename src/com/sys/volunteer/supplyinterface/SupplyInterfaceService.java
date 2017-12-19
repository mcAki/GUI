package com.sys.volunteer.supplyinterface;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.vo.SppinUsergroupVo;

public interface SupplyInterfaceService extends IDao {

	public SupplyInterface findById(int id);
	
	public SupplyInterface findBySupplyAndGoods(Integer supplyId,Integer goodsId);

	public void refreshFailedCount(SupplyInterface supplyInterface, int count);
	
	public List<SppinUsergroupVo> query(int usergroupId)  throws Exception;
	
	public void addSppinUsergroupId(String removeIds,String addIds,int usergroupId);
	
	public List<SppinUsergroupVo> queryByCondition(Integer usergroupId,Integer supplyId,Integer state,Integer canReverse,Double value
			,Integer goodsId,Integer goodsTypeId) throws Exception ;

	public List<SupplyInterface> findEnableInterfaces();

	public SupplyInterface findByUsergroupAndSupplyAndGoods(int groupId, int supplyId, int goodsId) throws Exception;
}
