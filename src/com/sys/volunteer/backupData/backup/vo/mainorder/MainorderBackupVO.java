package com.sys.volunteer.backupData.backup.vo.mainorder;

import com.sys.volunteer.backupData.backup.vo.BaseBackupVO;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Useraccountdealdetail;

public class MainorderBackupVO extends BaseBackupVO {

	private final static String tblName = "mainorder";

	private Mainorder mainorder;
	
	public MainorderBackupVO(Mainorder mainorder) {
		this.mainorder = mainorder;
	}

	@Override
	public String getInsertSQL() {
		String sqlCreate = "(";
		sqlCreate += "`mainOrderId`, `user_id`, `user_name`, `desc`, "
				+ "`goods_type_id`, `goods_type_name`, `goods_id`, `goods_name`, `goods_no`, "
				+ "`goods_value`, `card_lib_ids`, `totalMoney`, `retail_price`, `stock_price`, "
				+ "`commission`, `createTime`, `state`, `supply_id`, `supply_name`, `supplyInterface_id`, "
				+ "`sign`, `mobile`, `terminal_no`, `is_terminal`, `order_type`, `can_reverse`, `reversal_state`, "
				+ "`query_times`, `last_order_id`, `last_supply_ids`, `is_need_manual`, "
				+ "`card_no`, `manual_user_id`, `manual_user_name`, `refresh_time`, `reversalTime`, "
				+ "`money_back`, `resp_time`, `c_balance`";
		sqlCreate += ") VALUES( ";
		sqlCreate += (mainorder.getMainOrderId() == null) ? "NULL" : "'" + mainorder.getMainOrderId() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getUsers() == null) ? "NULL" : "'" + mainorder.getUsers().getUserId() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getUserName() == null) ? "NULL" : "'" + mainorder.getUserName() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getDesc() == null) ? "NULL" : "'" + mainorder.getDesc() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getGoodsType() == null) ? "NULL" : "'" + mainorder.getGoodsType().getGoodsTypeId() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getGoodsTypeName() == null) ? "NULL" : "'" + mainorder.getGoodsTypeName() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getGoods() == null) ? "NULL" : "'" + mainorder.getGoods().getGoodsId() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getGoodsNo() == null) ? "NULL" : "'" + mainorder.getGoodsNo() + "'";
		sqlCreate += ", ";
		sqlCreate += "'" + mainorder.getGoodsValue() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getCardLibIds() == null) ? "NULL" : "'" + mainorder.getCardLibIds() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getTotalMoney() == null) ? "NULL" : "'" + mainorder.getTotalMoney() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getRetailPrice() == null) ? "NULL" : "'" + mainorder.getRetailPrice() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getStockPrice() == null) ? "NULL" : "'" + mainorder.getStockPrice() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getCommission() == null) ? "NULL" : "'" + mainorder.getCommission() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getCreateTime() == null) ? "NULL" : "'" + mainorder.getCreateTime() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getState() == null) ? "NULL" : "'" + mainorder.getState() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getSupply() == null) ? "NULL" : "'" + mainorder.getSupply().getId() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getSupplyName() == null) ? "NULL" : "'" + mainorder.getSupplyName() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getSupplyInterface() == null) ? "NULL" : "'" + mainorder.getSupplyInterface().getId() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getSign() == null) ? "NULL" : "'" + mainorder.getSign() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getMobile() == null) ? "NULL" : "'" + mainorder.getMobile() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getTerminalNo() == null) ? "NULL" : "'" + mainorder.getTerminalNo() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getIsTerminal() == null) ? "NULL" : "'" + mainorder.getIsTerminal() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getOrderType() == null) ? "NULL" : "'" + mainorder.getOrderType() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getCanReverse() == null) ? "NULL" : "'" + mainorder.getCanReverse() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getReversalState() == null) ? "NULL" : "'" + mainorder.getReversalState() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getLastOrderId() == null) ? "NULL" : "'" + mainorder.getLastOrderId() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getLastSupplyIds() == null) ? "NULL" : "'" + mainorder.getLastSupplyIds() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getIsNeedManual() == null) ? "NULL" : "'" + mainorder.getIsNeedManual() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getCardNo() == null) ? "NULL" : "'" + mainorder.getCardNo() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getManualUsers() == null) ? "NULL" : "'" + mainorder.getManualUsers().getUserId() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getManualUserName() == null) ? "NULL" : "'" + mainorder.getManualUserName() + "'";
		sqlCreate += ", ";
		sqlCreate += "'" + mainorder.getRefreshTime() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getMoneyBack() == null) ? "NULL" : "'" + mainorder.getMoneyBack() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getRespTime() == null) ? "NULL" : "'" + mainorder.getRespTime() + "'";
		sqlCreate += ", ";
		sqlCreate += (mainorder.getcBalance() == null) ? "NULL" : "'" + mainorder.getcBalance() + "'";
		sqlCreate += ");";
		return sqlCreate;
	}

	@Override
	public String getRegSQL() {
		String sqlString = "(`mainOrderId` varchar(32) NOT NULL DEFAULT '' ,  `user_id` varchar(32) NOT NULL ,"
				+ "  `user_name` varchar(32) DEFAULT NULL ,  `desc` varchar(100) DEFAULT '' ,"
				+ "  `goods_type_id` int(11) DEFAULT NULL ,  `goods_type_name` varchar(32) DEFAULT NULL ,"
				+ "  `goods_id` int(11) DEFAULT '0' ,  `goods_name` varchar(32) DEFAULT '' ,"
				+ "  `goods_no` int(11) DEFAULT '0' ,  `goods_value` bigint(11) DEFAULT '0' ,"
				+ "  `card_lib_ids` varchar(1000) DEFAULT NULL ,  `totalMoney` double(10,2) NOT NULL DEFAULT '0.00' ,"
				+ "  `retail_price` double(10,2) DEFAULT NULL ,  `stock_price` double(10,2) DEFAULT '0.00' ,"
				+ "  `commission` double(10,2) DEFAULT '0.00' ,  `createTime` datetime NOT NULL ,"
				+ "  `state` int(11) DEFAULT '0' ,"
				+ "  `supply_id` int(11) DEFAULT NULL ,  `supply_name` varchar(32) DEFAULT NULL ,"
				+ "  `supplyInterface_id` int(11) DEFAULT NULL ,  `sign` varchar(50) DEFAULT NULL ,"
				+ "  `mobile` varchar(255) DEFAULT NULL ,  `terminal_no` varchar(32) DEFAULT NULL ,"
				+ "  `is_terminal` int(11) DEFAULT '0' ,  `order_type` int(11) NOT NULL DEFAULT '0' ,"
				+ "  `can_reverse` int(11) DEFAULT '1' ,  `reversal_state` int(11) DEFAULT '-1' ,"
				+ "  `query_times` int(11) DEFAULT '0' ,  `last_order_id` varchar(32) DEFAULT NULL,"
				+ "  `last_supply_ids` varchar(50) DEFAULT NULL ,  `is_need_manual` int(11) DEFAULT '0' ,"
				+ "  `card_no` varchar(50) DEFAULT NULL ,  `manual_user_id` varchar(32) DEFAULT NULL ,"
				+ "  `manual_user_name` varchar(32) DEFAULT NULL ,  `refresh_time` bigint(50) DEFAULT '0' ,"
				+ "  `reversalTime` datetime DEFAULT NULL,  `money_back` int(11) DEFAULT '1' ,  `resp_time` int(11) DEFAULT NULL,"
				+ "  `c_balance` double DEFAULT NULL,  PRIMARY KEY (`mainOrderId`))";
		return sqlString;
	}

	@Override
	public String getTblName() {
		return tblName;
	}

}
