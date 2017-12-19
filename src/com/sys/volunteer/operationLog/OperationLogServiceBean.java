package com.sys.volunteer.operationLog;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.backupData.BackupEngine;
import com.sys.volunteer.backupData.backup.vo.IBackupVO;
import com.sys.volunteer.backupData.backup.vo.operationlog.OperationLogBackupVO;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.order.OrderService;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.OperationLog;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.supply.SupplyService;

@Service
@Transactional
public class OperationLogServiceBean extends CommonDao implements
		OperationLogService {

	@Resource
	OrderService orderService;
	@Resource
	SupplyService supplyService;
	
	@Override
	public void addOperationLog(Users user,Mainorder mainorder,String userMobile,Integer type,Integer result,String loginIp) {
		OperationLog operationLog=new OperationLog();
		if (mainorder!=null) {
			Supply supply=(Supply) SupplyKernel.getInstance().getSupplyMapKeyId().get(mainorder.getSupply().getId());//mainorder.getSupply();
			operationLog.setMainorder(mainorder);
			operationLog.setSupply(supply);
			operationLog.setSupplyName(supply.getSupplyName());
			operationLog.setSubtotal(mainorder.getTotalMoney());
			operationLog.setAmount(mainorder.getGoodsNo());
			operationLog.setMobileNum(userMobile);
			operationLog.setGoods(mainorder.getGoods());
			operationLog.setGoodsName(mainorder.getGoodsName());
		}
		operationLog.setUsers(user);
		operationLog.setUserName(user.getUserName());
		operationLog.setCreateTime(new Date());
		operationLog.setType(type);
		operationLog.setResult(result);
		operationLog.setLoginIp(loginIp);
		this.save(operationLog);
		//增量备份
		BackupEngine backupEngine = BackupEngine.getInstance();
		IBackupVO vo = new OperationLogBackupVO(operationLog);
		backupEngine.getTaskList().add(vo);
	}

	@Override
	public OperationLog findOperationLogById(Integer operationLogId) {
		OperationLog operationLog=(OperationLog)this.findById(OperationLog.class, operationLogId);
		return operationLog;
	}

	@Override
	public List<OperationLog> findOperationLogsBySupplyId(String supplyId) {
		DetachedCriteria dec=DetachedCriteria.forClass(OperationLog.class);
		dec.add(Restrictions.eq("supply.id", supplyId));
		List<OperationLog> list=this.findByDetachedCriteria(dec);
		return list;
	}

	@Override
	public OperationLog findOperationLogsByOrderId(String orderId) {
		DetachedCriteria dec=DetachedCriteria.forClass(OperationLog.class);
		dec.add(Restrictions.eq("mainOrderId", orderId));
		List<OperationLog> list=this.findByDetachedCriteria(dec);
		OperationLog operationLog=null;
		if (!list.isEmpty()) {
			operationLog=list.get(0);
		}
		return operationLog;
	}

	@Override
	public void updateOperationLogResult(OperationLog operationLog, Integer result) {
		operationLog.setResult(result);
		this.update(operationLog);
	}


	
}
