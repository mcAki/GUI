package com.sys.volunteer.supply;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.balance.BalanceService;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.DateUtil;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Useraccountdealdetail;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.supply.usage.ISupply;
import com.sys.volunteer.useraccountdealdetail.UseraccountDealDetailService;
import com.sys.volunteer.usercharge.UserChargeService;

@Service
@Transactional
public class SupplyServiceBean extends CommonDao implements SupplyService {


	@Resource
	UserChargeService userChargeService;
	
	@Override
	public Supply findSupplyById(int supplyId) {
		Supply supply = (Supply) this.findById(Supply.class, supplyId);
		return supply;
	}

	@Override
	public Supply addSupply(Supply supply) {
		supply.setBalance(0d);
		supply.setCreateTime(new Date());
		supply.setAvgScore(new Long(10));
		supply.setIsUsed(1);
		if (supply.getSellType()==2&&supply.getSupplyType()==1) {
			supply.setClazzName("MPRSSupply");
		}else {
			supply.setClazzName("TestSupply");
		}
		this.save(supply);
		return supply;
	}

	@Override
	public void updateAvgScore(ISupply supply, Integer score) {
		supply.setAvgScore((supply.getAvgScore() + score) / 2);
		updateSupply(supply);
	}

	@Override
	public void purchaseBalance(Mainorder mainorder) {
		ISupply supply = SupplyKernel.getInstance().getSupplyMapKeyId().get(mainorder.getSupply().getId());
		Double money=mainorder.getGoodsNo()*mainorder.getStockPrice();
		if (supply.getBalance() >= money) {
			userChargeService.addUserChargeForSupply(
					new UserCharge(), mainorder, money, (Supply)supply,
					Const.UseraccountdealdetailFlag.PURCHASE);
			//结算balance
			Date startDate = DateUtil.getMinDate(new Date());
			Date endDate = DateUtil.getMaxDate(new Date());
			double balance = userChargeService.sumBalance(supply.getId());
			supply.setBalance(balance);
			updateSupply(supply);
		}
	}

	@Override
	public void reverseBalance(Mainorder mainorder) {
		ISupply supply = SupplyKernel.getInstance().getSupplyMapKeyId().get(mainorder.getSupply().getId());
		Double money=mainorder.getGoodsNo()*mainorder.getStockPrice();
		userChargeService.addUserChargeForSupply(
				new UserCharge(), mainorder, money, (Supply)supply,
				Const.UseraccountdealdetailFlag.REVERSAL);
		//结算balance
		Date startDate = DateUtil.getMinDate(new Date());
		Date endDate = DateUtil.getMaxDate(new Date());
		double balance = userChargeService.sumBalance(supply.getId());
		supply.setBalance(balance);
		updateSupply(supply);
	}

	@Override
	public List<Supply> getSupplyList() {
		DetachedCriteria dec = DetachedCriteria.forClass(Supply.class);
		// dec.add(Restrictions.eq("isUsed", 1));
		dec.addOrder(Order.desc("avgScore"));
		List<Supply> list = this.findByDetachedCriteria(dec);
		return list;
	}

	@Override
	public void depositBalance(Supply supply, Double money,
			UserCharge userCharge) {
		userChargeService.addUserChargeForSupply(
				userCharge, null, money, supply,
				Const.UseraccountdealdetailFlag.SUPPLY_DEPOSIT);
		//结算balance
		Date startDate = DateUtil.getMinDate(new Date());
		Date endDate = DateUtil.getMaxDate(new Date());
		double balance = userChargeService.sumBalance(supply.getId());
		supply.setBalance(balance);
		updateSupply(supply);
	}
	
	@Override
	public void drawBalance(Supply supply, Double money,
			UserCharge userCharge) {
		userChargeService.addUserChargeForSupply(
				userCharge, null, money, supply,
				Const.UseraccountdealdetailFlag.DRAWN);
		//结算balance
		Date startDate = DateUtil.getMinDate(new Date());
		Date endDate = DateUtil.getMaxDate(new Date());
		double balance = userChargeService.sumBalance(supply.getId());
		supply.setBalance(balance);
		updateSupply(supply);
	}
	
	@Override
	public void cancelBalance(Mainorder mainorder,Supply supply, Double money,
			UserCharge userCharge){
		userChargeService.addUserChargeForSupply(
				userCharge, mainorder, money, supply,
				Const.UseraccountdealdetailFlag.USER_CANCEL);
		//结算balance
		Date startDate = DateUtil.getMinDate(new Date());
		Date endDate = DateUtil.getMaxDate(new Date());
		double balance = userChargeService.sumBalance(supply.getId());
		supply.setBalance(balance);
		updateSupply(supply);
	}
	
	@Override
	public void refreshSupplyAccounts(Mainorder mainorder, Supply supply, Users recodeUsers,
			String recodeOperator,UserCharge userCharge,
			Double deposit, int flag) {
		userCharge = userChargeService.addUserChargeForSupply(
				userCharge, mainorder, deposit, supply,
				flag);
		supply.setBalance(userCharge.getCurrentBalance());
		updateSupply(supply);
	}

	@Override
	public void updateSupply(ISupply supply) {
		String sql = "UPDATE supply SET supplyName=?,createTime=?,reserve1=?,reserve2=?,"
				+ "avgScore=?,balance=?,`desc`=?,supply_type=?,contact_people=?,"
				+ "address=?,is_used=?,mobile=?,clazz_name=?,sell_type=? "
				+ "WHERE id=?";
		Object[] obj = new Object[] { supply.getSupplyName(),
				supply.getCreateTime(), supply.getReserve1(),
				supply.getReserve2(), supply.getAvgScore(),
				supply.getBalance(), supply.getDesc(), supply.getSupplyType(),
				supply.getContactPeople(), supply.getAddress(),
				supply.getIsUsed(), supply.getMobile(), supply.getClazzName(),
				supply.getSellType(), supply.getId() };
		this.executSql(sql, obj);
	}

	@Override
	public void saveSupply(ISupply supply) {
		String sql = "INSERT INTO supply (supplyName,createTime,reserve1,reserve2,"
				+ "avgScore,balance,`desc`,supply_type,contact_people,"
				+ "address,is_used,mobile,clazz_name,sell_type) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) " + "WHERE id=?";
		Object[] obj = new Object[] { supply.getSupplyName(),
				supply.getCreateTime(), supply.getReserve1(),
				supply.getReserve2(), supply.getAvgScore(),
				supply.getBalance(), supply.getDesc(), supply.getSupplyType(),
				supply.getContactPeople(), supply.getAddress(),
				supply.getIsUsed(), supply.getMobile(), supply.getClazzName(),
				supply.getSellType(), supply.getId() };
		this.executSql(sql, obj);
	}

}
