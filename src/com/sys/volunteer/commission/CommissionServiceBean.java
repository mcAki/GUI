package com.sys.volunteer.commission;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.Balance;
import com.sys.volunteer.pojo.Commission;

@Service
@Transactional
public class CommissionServiceBean extends CommonDao implements
		CommissionService {

	@Override
	public double addCommission(String userId, Double dealMoney,int flag) {
		Commission commission = new Commission();
		commission.setUserId(userId);
		commission.setDealMoney(dealMoney);
		commission.setIsDeal(flag);
		commission.setCreateTime(new Date());
		commission.setCommission(0d);
		//标记为255,结算
		if (flag == 255) {
			commission.setCommission(calcCommission(userId,0)+getUserCommission(userId));
			//标记之前未结算的为1
			updateIsDeal(userId, commission.getCreateTime());
		}
		this.save(commission);
		return commission.getCommission();
	}
	
	
	private double calcCommission(String userId,int isDeal) {
		String sql = "SELECT SUM(deal_money) FROM commission WHERE user_id = ? AND is_deal = ?";
		Object[] obj = new Object[]{userId,isDeal};
		List<Double> list = this.listBySql(sql, obj);
		if (list.get(0)!=null) {
			return list.get(0);
		}
		return 0;
	}
	
	private double getUserCommission(String userId){
		DetachedCriteria dec = DetachedCriteria.forClass(Commission.class);
		dec.add(Restrictions.eq("userId", userId));
		dec.add(Restrictions.eq("isDeal", 255));
		dec.addOrder(Order.desc("createTime"));
		List<Commission> list = this.findByDetachedCriteria(dec);
		if (list.get(0)!=null) {
			return list.get(0).getCommission();
		}else {
			return calcCommission(userId, 1);
		}
	}
	
	private void updateIsDeal(String userId,Date createTime){
		String sql = "UPDATE commission SET is_deal = 1 WHERE user_id= ? AND is_deal = 0 AND create_time <= ?";
		Object[] obj = new Object[]{userId,createTime};
		this.executSql(sql, obj);
	}
}
