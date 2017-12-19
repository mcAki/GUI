package com.sys.volunteer.operationLog;

import java.util.List;

import com.sys.volunteer.dao.IDao;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.OperationLog;
import com.sys.volunteer.pojo.Users;

public interface OperationLogService extends IDao {

	public void addOperationLog(Users user,Mainorder mainorder,String userMobile,Integer type,Integer result,String loginIp);

	public OperationLog findOperationLogById(Integer operationLogId);
	
	public List<OperationLog> findOperationLogsBySupplyId(String supplyId);
	
	public OperationLog findOperationLogsByOrderId(String orderId);
	
	public void updateOperationLogResult(OperationLog operationLog,Integer result);
}
