package com.sys.volunteer.usercharge;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.StringUtil;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.supply.usage.ISupply;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.useraccountdealdetail.UseraccountDealDetailService;
import com.sys.volunteer.users.UserService;

@Service
@Transactional
public class UserChargeServiceBean extends CommonDao implements
		UserChargeService {

	@Resource
	UseraccountService useraccountService;
	@Resource
	UserService userService;
	@Resource
	SupplyService supplyService;
	@Resource
	UseraccountDealDetailService useraccountDealDetailService;
	
	
	@Override
	// 2012.10.23
	public UserCharge addUserCharge(
			UserCharge userCharge, Mainorder mainorder,
			Double dealMoney, Users user, Users recodeUsers, String recodeOperator, int flag) {

		if (mainorder != null) {
			userCharge.setMainorder(mainorder);
			userCharge.setMobile(mainorder.getMobile());
		}

		userCharge.setCreateTime(new Date());
		userCharge.setUsers(user);
		userCharge.setUserName(user.getUserName());
		userCharge.setRecodeOperator(recodeOperator);
		if (recodeUsers!=null) {
			userCharge.setRecodeUsers(recodeUsers);
			userCharge.setRecodeUserName(recodeUsers.getUserName());
		}
		userCharge.setBalance(0d);
		userCharge.setCommission(0d);
		double sumBal = sumBalance(user.getUserId());
		double sumCom = sumCommission(user.getUserId());
		switch (flag) {
		case Const.UseraccountdealdetailFlag.USER_DEPOSIT:
		case Const.UseraccountdealdetailFlag.REVERSAL:
		case Const.UseraccountdealdetailFlag.DRAWING:
		case Const.UseraccountdealdetailFlag.USER_CANCEL:
		case Const.UseraccountdealdetailFlag.PLUS_CLAIM:
			userCharge.setDealMoney(dealMoney);
			// 统计余额
			userCharge.setCurrentBalance(sumBal + dealMoney);
			if (mainorder != null) {
				userCharge.setDealCommission(-mainorder
						.getCommission());
				// 统计佣金
				userCharge.setCurrentCommission(sumCom
						- mainorder.getCommission());
			} else {
				userCharge.setDealCommission(0d);
				// 统计佣金
				userCharge.setCurrentCommission(sumCom);
			}

			break;
		case Const.UseraccountdealdetailFlag.PURCHASE:
		case Const.UseraccountdealdetailFlag.REMIT:
		case Const.UseraccountdealdetailFlag.DRAWN:
		case Const.UseraccountdealdetailFlag.CROSS_CLAIM:
			userCharge.setDealMoney(-dealMoney);
			userCharge.setCurrentBalance(sumBal - dealMoney);
			if (mainorder != null) {
				userCharge.setDealCommission(mainorder
						.getCommission());
				userCharge.setCurrentCommission(sumCom
						+ mainorder.getCommission());
			} else {
				userCharge.setDealCommission(0d);
				// 统计佣金
				userCharge.setCurrentCommission(sumCom);
			}
			break;
		case Const.UseraccountdealdetailFlag.CHECK_OUT:
			userCharge.setDealMoney(sumBal);
			userCharge.setDealCommission(sumCom);
			userCharge.setCurrentBalance(userCharge
					.getDealMoney());
			userCharge.setCurrentCommission(userCharge
					.getDealCommission());
			userCharge.setBalance(userCharge
					.getCurrentBalance());
			userCharge.setCommission(userCharge
					.getCurrentCommission());
			break;
		case Const.UseraccountdealdetailFlag.DRAW_COMMISSION:
			userCharge.setDealMoney(sumCom);
			userCharge.setDealCommission(-userCharge
					.getDealMoney());
			userCharge.setCurrentBalance(sumBal + userCharge.getDealMoney());
			userCharge.setCurrentCommission(0d);
			break;
		default:
			break;
		}
		userCharge.setFlag(flag);
		userCharge
				.setLogFor(Const.UseraccountdealdetailLogFor.FOR_USER);
		save(userCharge);
		useraccountDealDetailService.addUseraccountdealdetail(userCharge);
		// 增量备份
//		BackupEngine backupEngine = BackupEngine.getInstance();
//		IBackupVO vo = new UADBackupVO(userCharge);
//		backupEngine.getTaskList().add(vo);
		return userCharge;
	}

	@Override
	public UserCharge addUserChargeForSupply(
			UserCharge userCharge, Mainorder mainorder,
			Double dealMoney, Supply supply, int flag) {
		if (mainorder != null) {
			userCharge.setMainorder(mainorder);
			userCharge.setMobile(mainorder.getMobile());
			userCharge.setUsers(mainorder.getUsers());
			userCharge.setUserName(mainorder.getUserName());
		}
		userCharge.setCreateTime(new Date());
		userCharge.setSupply(supply);
		userCharge.setSupplyName(supply.getSupplyName());
		userCharge.setBalance(0d);
		userCharge.setCommission(0d);
		userCharge.setDealCommission(0d);
		userCharge.setCurrentCommission(0d);
		double sumBal = sumBalance(supply.getId());
		switch (flag) {
		case Const.UseraccountdealdetailFlag.USER_DEPOSIT:
		case Const.UseraccountdealdetailFlag.REVERSAL:
		case Const.UseraccountdealdetailFlag.DRAWING:
		case Const.UseraccountdealdetailFlag.SUPPLY_DEPOSIT:
		case Const.UseraccountdealdetailFlag.USER_CANCEL:
			userCharge.setDealMoney(dealMoney);
			userCharge.setCurrentBalance(sumBal + dealMoney);
			break;
		case Const.UseraccountdealdetailFlag.PURCHASE:
		case Const.UseraccountdealdetailFlag.REMIT:
		case Const.UseraccountdealdetailFlag.DRAWN:
			userCharge.setDealMoney(-dealMoney);
			userCharge.setCurrentBalance(sumBal - dealMoney);
			break;
		case Const.UseraccountdealdetailFlag.CHECK_OUT:
			userCharge.setDealMoney(sumBal);
			userCharge.setCurrentBalance(userCharge
					.getDealMoney());
			userCharge.setBalance(userCharge
					.getCurrentBalance());
			break;
		default:
			break;
		}
		userCharge.setFlag(flag);
		userCharge
				.setLogFor(Const.UseraccountdealdetailLogFor.FOR_SUPPLY);
		save(userCharge);
		useraccountDealDetailService.addUseraccountdealdetail(userCharge);
		// 增量备份
//		BackupEngine backupEngine = BackupEngine.getInstance();
//		IBackupVO vo = new UADBackupVO(userCharge);
//		backupEngine.getTaskList().add(vo);
		return userCharge;
	}
	
	
	@Override
	public double sumBalance(String userId) {
		// double j = 0;
		// Date date = new Date();
		// String sql2 =
		// "SELECT createTime,dealMoney FROM useraccountdealdetail "
		// + " WHERE user_id = ? AND flag = 100 AND log_for = 1 "
		// + " ORDER BY createTime DESC LIMIT 1";
		// Object[] obj2 = new Object[] { userId };
		// List<Object[]> list2 = this.listBySql(sql2, obj2);
		// if (!list2.isEmpty() && list2.get(0) != null) {
		// j = (Double) list2.get(0)[1];
		// date = (Date) list2.get(0)[0];
		// }
		// Date startDate = DateUtil.getMinDate(date);
		// Date endDate = DateUtil.getMaxDate(new Date());
		// String sql = "SELECT SUM(dealMoney) "
		// + " FROM useraccountdealdetail "
		// +
		// " WHERE user_id=? AND flag != 100 AND createTime BETWEEN ? AND ? AND log_for = 1 AND balance = 0 ";
		// Object[] obj = new Object[] { userId, startDate, endDate };
		// List<Double> list = this.listBySql(sql, obj);
		// double i = 0;
		// if (!list.isEmpty() && list.get(0) != null) {
		// i = list.get(0);
		// }
		// return i + j;
		String sql = "SELECT IF(SUM(u1.dealMoney) IS NULL,0,SUM(u1.dealMoney))"
				+ " FROM user_charge u1"
				+ " WHERE user_id = ? AND log_for = 1";
		Object[] obj = new Object[] { userId };
		List<Double> list = this.listBySql(sql, obj);
		double i = 0;
		if (!list.isEmpty() && list.get(0) != null) {
			i = list.get(0);
		}
		return i;
	}

	@Override
	public double sumCommission(String userId) {
		// double j = 0;
		// Date date = new Date();
		// String sql2 =
		// "SELECT createTime,dealCommission FROM useraccountdealdetail "
		// + " WHERE user_id = ? AND flag = 100 AND log_for = 1 "
		// + " ORDER BY createTime DESC LIMIT 1";
		// Object[] obj2 = new Object[] { userId };
		// List<Object[]> list2 = this.listBySql(sql2, obj2);
		// if (!list2.isEmpty() && list2.get(0) != null) {
		// j = (Double) list2.get(0)[1];
		// date = (Date) list2.get(0)[0];
		// }
		// Date startDate = DateUtil.getMinDate(date);
		// Date endDate = DateUtil.getMaxDate(new Date());
		// String sql = "SELECT SUM(dealCommission) "
		// + " FROM useraccountdealdetail "
		// +
		// " WHERE user_id=? AND flag != 100 AND createTime BETWEEN ? AND ? AND log_for = 1 AND balance = 0 ";
		// Object[] obj = new Object[] { userId, startDate, endDate };
		// List<Double> list = this.listBySql(sql, obj);
		//
		// double i = 0;
		//
		// if (!list.isEmpty() && list.get(0) != null) {
		// i = list.get(0);
		// }
		//
		// return i + j;
		String sql = "SELECT IF(SUM(u1.dealCommission) IS NULL,0,SUM(u1.dealCommission))"
				+ " FROM user_charge u1"
				+ " WHERE user_id = ? AND log_for = 1";
		Object[] obj = new Object[] { userId };
		List<Double> list = this.listBySql(sql, obj);
		double i = 0;
		if (!list.isEmpty() && list.get(0) != null) {
			i = list.get(0);
		}
		return i;
	}

	@Override
	public double sumBalance(int supplyId) {
		// double j = 0;
		// Date date = new Date();
		// String sql2 =
		// "SELECT createTime,dealMoney FROM useraccountdealdetail "
		// + " WHERE supply_id = ? AND flag = 100 AND log_for = 2 "
		// + " ORDER BY createTime DESC LIMIT 1";
		// Object[] obj2 = new Object[] { supplyId };
		// List<Object[]> list2 = this.listBySql(sql2, obj2);
		// if (!list2.isEmpty() && list2.get(0) != null) {
		// j = (Double) list2.get(0)[1];
		// date = (Date) list2.get(0)[0];
		// }
		// Date startDate = DateUtil.getMinDate(date);
		// Date endDate = DateUtil.getMaxDate(new Date());
		// String sql = "SELECT SUM(dealMoney) "
		// + " FROM useraccountdealdetail "
		// +
		// " WHERE supply_id=? AND flag != 100 AND createTime BETWEEN ? AND ? AND log_for = 2 AND balance = 0 ";
		// Object[] obj = new Object[] { supplyId, startDate, endDate };
		// List<Double> list = this.listBySql(sql, obj);
		//
		// double i = 0;
		// if (!list.isEmpty() && list.get(0) != null) {
		// i = list.get(0);
		// }
		//
		// return i + j;
		String sql = "SELECT IF(SUM(u1.dealMoney) IS NULL,0,SUM(u1.dealMoney))"
				+ " FROM user_charge u1"
				+ " WHERE supply_id = ? AND log_for = 2";
		Object[] obj = new Object[] { supplyId };
		List<Double> list = this.listBySql(sql, obj);
		double i = 0;
		if (!list.isEmpty() && list.get(0) != null) {
			i = list.get(0);
		}
		return i;
	}

	@Override
	public void addCheckOut() {
		List<Users> list = (List<Users>) userService.findAll(Users.class);
		List<Integer> ids = new ArrayList<Integer>();
		for (Users users : list) {
			UserCharge userCharge = this.addUserCharge(new UserCharge(), null,
					0d, users, null, null, Const.UseraccountdealdetailFlag.CHECK_OUT);
			ids.add(userCharge.getSeqno());
		}
		Map<Integer, ISupply> supplyMapKeyId = SupplyKernel.getInstance()
				.getSupplyMapKeyId();
		Iterator iterator = supplyMapKeyId.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, ISupply> entry = (Map.Entry<Integer, ISupply>) iterator
					.next();
			Supply supply = (Supply) entry.getValue();
			UserCharge userCharge = this.addUserChargeForSupply(new UserCharge(),
					null, 0d, supply, Const.UseraccountdealdetailFlag.CHECK_OUT);
			ids.add(userCharge.getSeqno());
		}
		String idsStr = StringUtil.ListToStr(ids, ",");
		this.checkOutRemove(idsStr);
	}
	
	private void checkOutRemove(String ids) {
		String sql = "DELETE FROM user_charge WHERE seqno not in ("+ids+")";
		this.executSql(sql);
	}

}
