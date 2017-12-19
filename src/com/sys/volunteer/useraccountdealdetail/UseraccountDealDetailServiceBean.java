package com.sys.volunteer.useraccountdealdetail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.backupData.BackupEngine;
import com.sys.volunteer.backupData.backup.vo.IBackupVO;
import com.sys.volunteer.backupData.backup.vo.useraccountdealdetail.UADBackupVO;
import com.sys.volunteer.common.DateUtil;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Useraccountdealdetail;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.usercharge.UserChargeService;
import com.sys.volunteer.users.UserService;

@Service
@Transactional
public class UseraccountDealDetailServiceBean extends CommonDao implements
		UseraccountDealDetailService {

	@Resource
	UseraccountService useraccountService;
	@Resource
	UserService userService;
	@Resource
	SupplyService supplyService;
	@Resource
	UserChargeService userChargeService;


	@Override
	public Useraccountdealdetail findUseraccountDealDetailByOrderId(
			String orderId) {
		Useraccountdealdetail useraccountdealdetail = null;
		DetachedCriteria dec = DetachedCriteria
				.forClass(Useraccountdealdetail.class);
		dec.add(Restrictions.eq("mainorder.id", orderId));
		List<Useraccountdealdetail> list = this.findByDetachedCriteria(dec);
		if (!list.isEmpty()) {
			useraccountdealdetail = list.get(0);
		}
		return useraccountdealdetail;
	}

	/**
	 * 查询商户当日结算的合计数
	 * 
	 * @return
	 */
	@Override
	public double sumAllExDateCount(int logFor, Date starDate, Date endDate) {
		double sum = 0;
		String sql = "";
		
		if (starDate != null || endDate != null) {
			String startDateTemp = DateUtil.DateToString(starDate, "yyyy-MM-dd HH:mm:ss");
			String endDateTemp = DateUtil.DateToString(endDate, "yyyy-MM-dd HH:mm:ss");
			sql = "SELECT SUM(ud.balance+ud.commission) as sumItemPrice  FROM useraccountdealdetail AS ud  " ;
					if(logFor==1){
						sql+=",users AS u WHERE ud.user_id=u.user_id ";
					}else{
						sql+=",supply as s WHERE ud.supply_id = s.id ";
					}
					sql+=" AND ud.log_for = "+ logFor
					+ " AND ud.createTime BETWEEN '"
					+ startDateTemp
					+ "' AND '" + endDateTemp +"'";
					if(logFor==1){
						sql+= "  AND u.group_id != 1";
					}
		} else {
			sql = "SELECT SUM(ud.balance+ud.commission) as sumItemPrice FROM useraccountdealdetail AS ud " ;
			
					if(logFor==1){
						sql+=" ,users AS u WHERE ud.user_id=u.user_id";
					}else{
						sql+=",supply as s WHERE ud.supply_id = s.id";
					}
					sql+=" AND  ud.log_for = "+ logFor+" AND DATE(ud.createTime) = CURDATE()";
					
					if(logFor==1){
						sql+= "  AND u.group_id != 1";
					}
		}
		List<Double> list = this.listBySql(sql, null);
		System.out.println(sql);
		if (!list.isEmpty() && list.get(0) != null) {
			sum = list.get(0);
		}
		return sum;
	}

	@Override
	public List<Useraccountdealdetail> selUserAllExDateCountList(int logFor,
			Date starDate, Date endDate) {
		List<Useraccountdealdetail> list = new ArrayList<Useraccountdealdetail>();
		String sql = "";
		if (starDate != null || endDate != null) {
			String startDateTemp = DateUtil.DateToString(starDate, "yyyy-MM-dd HH:mm:ss");
			String endDateTemp = DateUtil.DateToString(endDate, "yyyy-MM-dd HH:mm:ss");
			sql = "SELECT user_name as userName, SUM(ud.balance+ud.commission) as sumDealMoney FROM useraccountdealdetail AS ud  WHERE  ud.createTime BETWEEN '"
					+ startDateTemp
					+ "' AND '"
					+ endDateTemp
					+ "' AND ud.log_for = "
					+ logFor + " GROUP BY user_id";
		} else {
			sql = "SELECT user_name as userName, SUM(ud.balance+ud.commission) as sumDealMoney FROM useraccountdealdetail AS ud  WHERE DATE(ud.createTime) = CURDATE() AND ud.log_for = "
					+ logFor + " GROUP BY user_id";
		}
		Object[] obj = new Object[]{};
		list = (List<Useraccountdealdetail>)this.listBySqlQuery(sql, obj, null, null, Useraccountdealdetail.class);
		
		return list;
	}
	@Override
	public List<Useraccountdealdetail> selSupplyAllExDateCountList(int logFor,
			Date starDate, Date endDate) {
		List<Useraccountdealdetail> list = new ArrayList<Useraccountdealdetail>();
		String sql = "";
		if (starDate != null || endDate != null) {
			String startDateTemp = DateUtil.DateToString(starDate, "yyyy-MM-dd HH:mm:ss");
			String endDateTemp = DateUtil.DateToString(endDate, "yyyy-MM-dd HH:mm:ss");
			sql = "SELECT supply_name as supplyName, SUM(ud.balance+ud.commission) as sumDealMoney FROM useraccountdealdetail AS ud  WHERE  ud.createTime BETWEEN '"
				+ startDateTemp
				+ "' AND '"
				+ endDateTemp
				+ "' AND ud.log_for = "
				+ logFor + " GROUP BY supply_name";
		} else {
			sql = "SELECT supply_name as supplyName, SUM(ud.balance+ud.commission) as sumDealMoney FROM useraccountdealdetail AS ud  WHERE DATE(ud.createTime) = CURDATE() AND ud.log_for = "
				+ logFor + " GROUP BY supply_name";
		}
		Object[] obj = new Object[]{};
		list = (List<Useraccountdealdetail>)this.listBySqlQuery(sql, obj, null, null, Useraccountdealdetail.class);
		
		return list;
	}

	
	@Override
	public void addUseraccountdealdetail(UserCharge userCharge){
		Useraccountdealdetail ud = new Useraccountdealdetail();
		ud.setBalance(userCharge.getBalance());
		ud.setCommission(userCharge.getCommission());
		ud.setCreateTime(userCharge.getCreateTime());
		ud.setCurrentBalance(userCharge.getCurrentBalance());
		ud.setCurrentCommission(userCharge.getCurrentCommission());
		ud.setDealCommission(userCharge.getDealCommission());
		ud.setDealMoney(userCharge.getDealMoney());
		ud.setFlag(userCharge.getFlag());
		ud.setItemPrice(userCharge.getItemPrice());
		ud.setLogFor(userCharge.getLogFor());
		ud.setMainorder(userCharge.getMainorder());
		ud.setMobile(userCharge.getMobile());
		ud.setRecodeOperator(userCharge.getRecodeOperator());
		ud.setRecodeUserName(userCharge.getRecodeUserName());
		ud.setRecodeUsers(userCharge.getRecodeUsers());
		ud.setRemark(userCharge.getRemark());
		ud.setSeqno(userCharge.getSeqno());
		ud.setSupply(userCharge.getSupply());
		ud.setSupplyName(userCharge.getSupplyName());
		ud.setTerminalNo(userCharge.getTerminalNo());
		ud.setUserName(userCharge.getUserName());
		ud.setUsers(userCharge.getUsers());
		ud.setVoucherCode(userCharge.getVoucherCode());
		ud.setVoucherType(userCharge.getVoucherType());
		save(ud);
		// 增量备份
		BackupEngine backupEngine = BackupEngine.getInstance();
		IBackupVO vo = new UADBackupVO(ud);
		backupEngine.getTaskList().add(vo);
	}
	

}
