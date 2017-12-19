package com.sys.volunteer.supplyinterface;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.common.SysUtil;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pagemodel.PageView;
import com.sys.volunteer.pagemodel.QueryResult;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.Permission;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.Usergroup;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.vo.PermissionVO;
import com.sys.volunteer.vo.SppinUsergroupVo;

@Service
@Transactional
public class SupplyInterfaceServiceBean extends CommonDao implements
		SupplyInterfaceService {

	@Override
	public SupplyInterface findById(int id) {
		SupplyInterface supplyInterface = (SupplyInterface)findById(SupplyInterface.class, id);
		return supplyInterface;
	}

	@Override
	public SupplyInterface findBySupplyAndGoods(Integer supplyId,
			Integer goodsId) {
		SupplyInterface supplyInterface=null;
		DetachedCriteria dec=DetachedCriteria.forClass(SupplyInterface.class);
		dec.add(Restrictions.eq("supply.id", supplyId));
		dec.add(Restrictions.eq("goods.id", goodsId));
		dec.add(Restrictions.eq("state", 1));
		List<SupplyInterface> supplyInterfaces=this.findByDetachedCriteria(dec);
		if (supplyInterfaces.size()>0) {
			supplyInterface = supplyInterfaces.get(0);
		}
		return supplyInterface;
	}
	
	@Override
	public List<SupplyInterface> findEnableInterfaces() {
		DetachedCriteria dec = DetachedCriteria.forClass(SupplyInterface.class);
		dec.equals(Restrictions.eq("state", 1));
		List<SupplyInterface> list = findByDetachedCriteria(dec);
		return list;
	}

	@Override
	public void refreshFailedCount(SupplyInterface supplyInterface,int count){
		supplyInterface.setFailedCount(count);
		this.update(supplyInterface);
	}
	
	@Override
	public SupplyInterface findByUsergroupAndSupplyAndGoods(int groupId, int supplyId, int goodsId) throws Exception {
		String sql = "SELECT supplyinterface.id AS id" +
					" FROM supplyinterface,sppin_usergroup,usergroup" + 
					" WHERE supplyinterface.id=sppin_usergroup.spplyin_id AND usergroup.id=sppin_usergroup.usergroup_id" +
					" AND supplyinterface.supply_id = ? AND supplyinterface.goods_id = ? AND usergroup.id=? AND supplyinterface.state = 1";
		Object[] objs = new Object[]{supplyId,goodsId,groupId};
		PageView pageView = new PageView(Integer.MAX_VALUE, 1);
		QueryResult queryResult = this.getScrollDataBySQLQuery(sql, objs, pageView.getFirstResult(), pageView.getMaxresult(), null, null, SupplyInterface.class);
		List<SupplyInterface> list = queryResult.getResultlist();
		if (!list.isEmpty()) {
			return SupplyKernel.getInstance().getSupplyInterfaceMapKeyId().get(list.get(0).getId());
		}
		return null;
	}

	@Override
	public void addSppinUsergroupId(String removeId, String addId,
			int usergroupId) {

		String[] addIds = addId.split(",");
		String[] removeIds = removeId.split(",");
		
		Usergroup usergroup = (Usergroup) this.findById(Usergroup.class, usergroupId);

		Set<SupplyInterface> supplyInterfaces = usergroup.getSupplyInterfaces();

		for (int i = 0; i < removeIds.length; i++) {
			if (SysUtil.isNull(removeIds[i]) || removeIds[i].trim().isEmpty()) {
				break;
			}
			SupplyInterface sf = (SupplyInterface) this.findById(SupplyInterface.class,
				Integer.parseInt(removeIds[i]));
			usergroup.getSupplyInterfaces().remove(sf);
		}

 		for (int i = 0; i < addIds.length; i++) {
			if (SysUtil.isNull(addIds[i]) || addIds[i].trim().isEmpty()) {
				break;
			}
			SupplyInterface sf = (SupplyInterface) this.findById(SupplyInterface.class,
				Integer.parseInt(addIds[i]));
			usergroup.getSupplyInterfaces().add(sf);
		}
		update(usergroup);
		
	}

	
	
	@Override
	public List<SppinUsergroupVo> query(int usergroupId) throws Exception {
		String sql = "SELECT T.usergroup_id AS usergroupId, s.id AS id ,s.supply_id AS supplyId,s.supply_name AS supplyName,s.goods_id AS goodsId,s.goods_name AS goodsName,s.stock_price AS stockPrice, " +
		       " s.retail_price AS retailPrice ,s.value AS value,s.state as state, s.can_reverse AS canReverse "+
				" FROM supplyinterface AS s LEFT JOIN (SELECT * FROM sppin_usergroup WHERE usergroup_id = ?) AS T ON s.id = T.spplyin_id";
		PageView pageView = new PageView(Integer.MAX_VALUE, 1);
		Object[] objects = new Object[] { usergroupId };
		QueryResult  queryResult = this.getScrollDataBySQLQuery(sql, objects, pageView.getFirstResult(), pageView.getMaxresult(), null, null, SppinUsergroupVo.class);
		
		return queryResult.getResultlist();
	}
	
	@Override
	public List<SppinUsergroupVo> queryByCondition(Integer usergroupId,Integer supplyId,Integer state,Integer canReverse,Double value,Integer goodsId,Integer goodsTypeId) throws Exception {
		String sql = "SELECT T.usergroup_id AS usergroupId, s.id AS id ,s.supply_id AS supplyId,s.supply_name AS supplyName,s.goods_id AS goodsId,s.goods_name AS goodsName,s.stock_price AS stockPrice, " +
				" s.retail_price AS retailPrice ,s.value AS value,s.state as state, s.can_reverse AS canReverse "+
				" FROM supplyinterface AS s LEFT JOIN (SELECT * FROM sppin_usergroup WHERE usergroup_id = ?) AS T ON s.id = T.spplyin_id  " ;
//	  Integer val = value.intValue();
//		if(supplyId==0){
//		   supplyId=-1;
//	   }if(val==0){
//		   val = -1 ;
//	   }
//		Object[] obj1 = new Object[]{supplyId,state,canReverse,val};
//		String strArr[] = {"s.supply_id=","s.state=","s.can_reverse=","s.value="};
//		int count = 0;
//		for(int i=0;i<obj1.length;i++){
//			if((Integer)obj1[i]<=-1){
//				continue;
//			}else{
//				if(count<1){
//					sql+=" WHERE "+strArr[i]+obj1[i];
//					count++;
//				}
//				if(count>=1){
//					sql+=" AND "+strArr[i]+obj1[i];
//					count++;
//				}
//			}
//		}
	
		if ((supplyId != null && supplyId>0) || (goodsId != null && goodsId>0) || (goodsTypeId != null && goodsTypeId>0) 
				|| (state != null && state !=-1) || !SysUtil.isNull(value)) {
			sql += "WHERE ";
		}
		if (supplyId != null && supplyId>0) {
			sql += " s.supply_id = " + supplyId;
		}
		if (state != null && state !=-1) {
			if (supplyId != null && supplyId>0) {
				sql += " AND s.state = " + state;
			}else {
				sql += " s.state = " + state;
			}
		}
		if (!SysUtil.isNull(value)) {
			if ((supplyId != null && supplyId>0) || (state != null && state !=-1)) {
				sql += " AND s.value = " + value;
			}else {
				sql += " s.value =" +value;
			}
		}
		if (goodsId != null && goodsId>0) {
			if ((supplyId != null && supplyId>0) || (state != null && state !=-1) || !SysUtil.isNull(value)) {
				sql += " AND s.goods_id = " + goodsId;
			}else {
				sql += " s.goods_id =" +goodsId;
			}
		}
		sql += " ORDER BY s.goods_id ";

		PageView pageView = new PageView(Integer.MAX_VALUE, 1);
		Object[] objects = new Object[] { usergroupId };
		QueryResult  queryResult = this.getScrollDataBySQLQuery(sql, objects, pageView.getFirstResult(), pageView.getMaxresult(), null, null, SppinUsergroupVo.class);
		
		return queryResult.getResultlist();
	}
}
