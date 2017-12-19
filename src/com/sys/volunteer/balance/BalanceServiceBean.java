package com.sys.volunteer.balance;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.Balance;

@Service
@Transactional
public class BalanceServiceBean extends CommonDao implements BalanceService {

	@Override
	public double addBalance(String userId, Double dealMoney,int flag) {
		Balance balance = new Balance();
		balance.setUserId(userId);
		balance.setDealMoney(dealMoney);
		balance.setIsDeal(flag);
		balance.setCreateTime(new Date());
		balance.setBalance(0d);
		//标记为255,结算
		if (flag == 255) {
			balance.setBalance(calcBalance(userId,0)+getUserBalance(userId));
			//标记之前未结算的为1
			updateIsDeal(userId, balance.getCreateTime());
		}
		balance.setLogFor(1);
		this.save(balance);
		return balance.getBalance();
	}

	@Override
	public double addBalanceForSupply(int supplyId, Double dealMoney,int flag) {
		Balance balance = new Balance();
		balance.setSupplyId(supplyId);
		balance.setDealMoney(dealMoney);
		balance.setIsDeal(flag);
		balance.setCreateTime(new Date());
		balance.setBalance(0d);
		//标记为255,结算
		if (flag == 255) {
			balance.setBalance(calcBalanceForSupply(supplyId,0)+getSupplyBalance(supplyId));
			//标记之前未结算的为1
			updateIsDealForSupply(supplyId, balance.getCreateTime());
		}
		balance.setLogFor(2);
		this.save(balance);
		return balance.getBalance();
	}

	private double calcBalance(String userId,int isDeal) {
		String sql = "SELECT SUM(deal_money) FROM balance WHERE user_id = ? AND is_deal = ?";
		Object[] obj = new Object[]{userId,isDeal};
		List<Double> list = this.listBySql(sql, obj);
		if (list.get(0)!=null) {
			return list.get(0);
		}
		return 0;
	}
	
	private double getUserBalance(String userId){
		DetachedCriteria dec = DetachedCriteria.forClass(Balance.class);
		dec.add(Restrictions.eq("userId", userId));
		dec.add(Restrictions.eq("isDeal", 255));
		dec.addOrder(Order.desc("createTime"));
		List<Balance> list = this.findByDetachedCriteria(dec);
		if (list.get(0)!=null) {
			return list.get(0).getBalance();
		}else {
			return calcBalance(userId, 1);
		}
	}
	
	private void updateIsDeal(String userId,Date createTime){
		String sql = "UPDATE balance SET is_deal = 1 WHERE user_id= ? AND is_deal = 0 AND create_time <= ?";
		Object[] obj = new Object[]{userId,createTime};
		this.executSql(sql, obj);
	}
	
	
	private double calcBalanceForSupply(int supplyId,int isDeal) {
		String sql = "SELECT SUM(deal_money) FROM balance WHERE supply_id = ? AND is_deal = ?";
		Object[] obj = new Object[]{supplyId,isDeal};
		List<Double> list = this.listBySql(sql, obj);
		if (list.get(0)!=null) {
			return list.get(0);
		}
		return 0;
	}
	
	private double getSupplyBalance(int supplyId){
		DetachedCriteria dec = DetachedCriteria.forClass(Balance.class);
		dec.add(Restrictions.eq("supplyId", supplyId));
		dec.add(Restrictions.eq("isDeal", 255));
		dec.addOrder(Order.desc("id"));
		List<Balance> list = this.findByDetachedCriteria(dec);
		if (list.get(0)!=null) {
			return list.get(0).getBalance();
		}else {
			return calcBalanceForSupply(supplyId, 1);
		}
	}
	
	private void updateIsDealForSupply(int supplyId,Date createTime){
		String sql = "UPDATE balance SET is_deal = 1 WHERE supply_id = ? AND is_deal = 0 and create_time <= ?";
		Object[] obj = new Object[]{supplyId,createTime};
		this.executSql(sql, obj);
	}
	

	
}
