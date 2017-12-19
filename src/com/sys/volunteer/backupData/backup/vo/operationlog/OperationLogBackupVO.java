package com.sys.volunteer.backupData.backup.vo.operationlog;

import com.sys.volunteer.backupData.backup.vo.BaseBackupVO;
import com.sys.volunteer.common.DateUtil;
import com.sys.volunteer.pojo.OperationLog;

public class OperationLogBackupVO extends BaseBackupVO {
	
	private final static String tblName = "operationlog";
	
	private OperationLog operationLog;
	
	

	public OperationLogBackupVO(OperationLog operationLog) {
		this.operationLog = operationLog;
	}

	@Override
	public String getInsertSQL() {
		String sqlCreate = "(";
		sqlCreate += "`operation_id`, `supply_id`, `supplyName`, `user_id`, `userName`, `terminal_no`, `mainOrderId`, `createTime`, `subtotal`, `amount`, `mobileNum`, `type`, `result`, `goods_id`, `goods_name`, `goods_value`, `is_terminal`";
		sqlCreate += ") VALUES( ";
		sqlCreate += (operationLog.getOperationId() == null) ? "NULL" : "'" + operationLog.getOperationId() + "'";
		sqlCreate += ", ";
		sqlCreate += (operationLog.getSupply() == null) ? "NULL" : "'" + operationLog.getSupply().getId() + "'";
		sqlCreate += ", ";
		sqlCreate += (operationLog.getSupplyName() == null) ? "NULL" : "'" + operationLog.getSupplyName() + "'";
		sqlCreate += ", ";
		sqlCreate += (operationLog.getUsers() == null) ? "NULL" : "'" + operationLog.getUsers().getUserId() + "'";
		sqlCreate += ", ";
		sqlCreate += (operationLog.getUserName() == null) ? "NULL" : "'" + operationLog.getUserName() + "'";
		sqlCreate += ", ";
		sqlCreate += (operationLog.getTerminalNo() == null) ? "NULL" : "'" + operationLog.getTerminalNo() + "'";
		sqlCreate += ", ";
		sqlCreate += (operationLog.getMainorder() == null) ? "NULL" : "'" + operationLog.getMainorder().getMainOrderId() + "'";
		sqlCreate += ", ";
		sqlCreate += (operationLog.getCreateTime() == null) ? "NULL" : "'" + DateUtil.DateToString(operationLog.getCreateTime(), DateUtil.CM_LONG_DATE_FORMAT) + "'";
		sqlCreate += ", ";
		sqlCreate += (operationLog.getSubtotal() == null) ? "NULL" : "'" + operationLog.getSubtotal() + "'";
		sqlCreate += ", ";
		sqlCreate += (operationLog.getAmount() == null) ? "NULL" : "'" + operationLog.getAmount() + "'";
		sqlCreate += ", ";
		sqlCreate += (operationLog.getMobileNum() == null) ? "NULL" : "'" + operationLog.getMobileNum() + "'";
		sqlCreate += ", ";
		sqlCreate += (operationLog.getType() == null) ? "NULL" : "'" + operationLog.getType() + "'";
		sqlCreate += ", ";
		sqlCreate += (operationLog.getResult() == null) ? "NULL" : "'" + operationLog.getResult() + "'";
		sqlCreate += ", ";
		sqlCreate += (operationLog.getGoods() == null) ? "NULL" : "'" + operationLog.getGoods().getGoodsId() + "'";
		sqlCreate += ", ";
		sqlCreate += (operationLog.getGoodsName() == null) ? "NULL" : "'" + operationLog.getGoodsName() + "'";
		sqlCreate += ", ";
		sqlCreate += (operationLog.getGoodsValue() == null) ? "NULL" : "'" + operationLog.getGoodsValue() + "'";
		sqlCreate += ", ";
		sqlCreate += (operationLog.getIsTerminal() == null) ? "NULL" : "'" + operationLog.getIsTerminal() + "'";
		sqlCreate += ");";

		return sqlCreate;
	}

	@Override
	public String getRegSQL() {
		String sqlString = "(`operation_id` varchar(32) NOT NULL, `supply_id` int(11) DEFAULT NULL,  `supplyName` varchar(32) DEFAULT NULL,  `user_id` varchar(32) DEFAULT NULL,  `userName` varchar(32) DEFAULT NULL,  `terminal_no` varchar(32) DEFAULT '',  `mainOrderId` varchar(32) DEFAULT NULL,  `createTime` datetime DEFAULT NULL,  `subtotal` double(10,2) DEFAULT '0.00',  `amount` int(11) DEFAULT '0',  `mobileNum` varchar(32) DEFAULT NULL,  `type` int(11) DEFAULT '0',  `result` int(11) DEFAULT '0',  `goods_id` int(11) DEFAULT '0',  `goods_name` varchar(32) DEFAULT '',  `goods_value` int(11) DEFAULT '0',  `is_terminal` int(11) DEFAULT '0',  PRIMARY KEY (`operation_id`))";
		return sqlString;
	}

	@Override
	public String getTblName() {
		return tblName;
	}

}
