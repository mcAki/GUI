package com.sys.volunteer.backupData.backup.vo.useraccountdealdetail;

import com.sys.volunteer.backupData.backup.vo.BaseBackupVO;
import com.sys.volunteer.pojo.Useraccountdealdetail;

public class UADBackupVO extends BaseBackupVO {

	private final static String tblName = "useraccountdealdetail";

	private Useraccountdealdetail useraccountdealdetail;

	public UADBackupVO(Useraccountdealdetail useraccountdealdetail) {
		this.useraccountdealdetail = useraccountdealdetail;
	}

	@Override
	public String getInsertSQL() {
		String sqlCreate = "(";
		sqlCreate += "`seqno`, `mainOrderId`, `user_id`, `user_name`, " +
				"`terminal_no`, `supply_id`, `supply_name`, `voucher_type`, `voucher_code`, " +
				"`balance`, `dealMoney`, `current_balance`, `dealCommission`, `commission`, " +
				"`current_commission`, `createTime`, `remark`, `log_for`, `flag`, `mobile`, " +
				"`recode_username`, `recode_user_id`, `recode_Operator`, `item_price`";
		sqlCreate += ") VALUES( ";
		sqlCreate += (useraccountdealdetail.getSeqno() == null) ? "NULL" : "'" + useraccountdealdetail.getSeqno() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getMainorder() == null) ? "NULL" : "'" + useraccountdealdetail.getMainorder().getMainOrderId() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getUsers() == null) ? "NULL" : "'" + useraccountdealdetail.getUsers().getUserId() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getUserName() == null) ? "NULL" : "'" + useraccountdealdetail.getUserName() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getTerminalNo() == null) ? "NULL" : "'" + useraccountdealdetail.getTerminalNo() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getSupply() == null) ? "NULL" : "'" + useraccountdealdetail.getSupply().getId() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getSupplyName() == null) ? "NULL" : "'" + useraccountdealdetail.getSupplyName() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getVoucherType() == null) ? "NULL" : "'" + useraccountdealdetail.getVoucherType() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getVoucherCode() == null) ? "NULL" : "'" + useraccountdealdetail.getVoucherCode() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getBalance() == null) ? "NULL" : "'" + useraccountdealdetail.getBalance() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getDealMoney() == null) ? "NULL" : "'" + useraccountdealdetail.getDealMoney() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getCurrentBalance() == null) ? "NULL" : "'" + useraccountdealdetail.getCurrentBalance() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getDealCommission() == null) ? "NULL" : "'" + useraccountdealdetail.getDealCommission() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getCommission() == null) ? "NULL" : "'" + useraccountdealdetail.getCommission() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getCurrentCommission() == null) ? "NULL" : "'" + useraccountdealdetail.getCurrentCommission() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getCreateTime() == null) ? "NULL" : "'" + useraccountdealdetail.getCreateTime() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getRemark() == null) ? "NULL" : "'" + useraccountdealdetail.getRemark() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getLogFor() == null) ? "NULL" : "'" + useraccountdealdetail.getLogFor() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getFlag() == null) ? "NULL" : "'" + useraccountdealdetail.getFlag() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getMobile() == null) ? "NULL" : "'" + useraccountdealdetail.getMobile() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getRecodeUserName() == null) ? "NULL" : "'" + useraccountdealdetail.getRecodeUserName() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getRecodeUsers() == null) ? "NULL" : "'" + useraccountdealdetail.getRecodeUsers().getUserId() + "'";
		sqlCreate += ", ";
		sqlCreate += (useraccountdealdetail.getRecodeOperator() == null) ? "NULL" : "'" + useraccountdealdetail.getRecodeOperator() + "'";
		sqlCreate += ", ";
		sqlCreate += "'" + useraccountdealdetail.getItemPrice() + "'";
		sqlCreate += ");";
		return sqlCreate;
	}

	@Override
	public String getRegSQL() {
		String sqlString = "(`seqno` int(11) NOT NULL ,  `mainOrderId` varchar(32) DEFAULT NULL ," +
				"  `user_id` varchar(32) DEFAULT NULL ,  `user_name` varchar(32) DEFAULT NULL ," +
				"  `terminal_no` varchar(32) DEFAULT NULL ,  `supply_id` int(11) DEFAULT NULL ," +
				"  `supply_name` varchar(32) DEFAULT NULL,  `voucher_type` int(11) DEFAULT '0' ," +
				"  `voucher_code` varchar(100) DEFAULT NULL ,  `balance` double(20,2) NOT NULL DEFAULT '0.00' ," +
				"  `dealMoney` double(20,2) NOT NULL DEFAULT '0.00' ,  `current_balance` double(20,2) NOT NULL DEFAULT '0.00' ," +
				"  `dealCommission` double(20,2) DEFAULT '0.00' ,  `commission` double(10,2) DEFAULT '0.00' ," +
				"  `current_commission` double(20,2) DEFAULT '0.00' ,  `createTime` datetime DEFAULT NULL ," +
				"  `remark` varchar(100) DEFAULT '' ,  `log_for` int(11) DEFAULT '0' ," +
				"  `flag` int(11) NOT NULL ,  `mobile` varchar(32) DEFAULT NULL ," +
				"  `recode_username` varchar(255) DEFAULT NULL,  `recode_user_id` varchar(32) DEFAULT NULL," +
				"  `recode_Operator` varchar(255) DEFAULT NULL,  `item_price` double DEFAULT NULL,  PRIMARY KEY (`seqno`))";
		return sqlString;
	}

	@Override
	public String getTblName() {
		return tblName;
	}

}
