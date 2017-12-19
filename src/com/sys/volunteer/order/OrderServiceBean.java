package com.sys.volunteer.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.backupData.BackupEngine;
import com.sys.volunteer.backupData.backup.vo.IBackupVO;
import com.sys.volunteer.backupData.backup.vo.mainorder.MainorderBackupVO;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.DateUtil;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.goods.GoodsService;
import com.sys.volunteer.goods.GoodsTypeService;
import com.sys.volunteer.muticharge.engine.OrderVO;
import com.sys.volunteer.operationLog.OperationLogService;
import com.sys.volunteer.orderQuery.OrderQueryResponseNewService;
import com.sys.volunteer.pagemodel.PageView;
import com.sys.volunteer.pagemodel.QueryResult;
import com.sys.volunteer.pojo.BatchOrder;
import com.sys.volunteer.pojo.CardLib;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.GoodsType;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Orderdetail;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.supply.SupplyService;
import com.sys.volunteer.supply.usage.ISupply;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.useraccountdealdetail.UseraccountDealDetailService;
import com.sys.volunteer.usercharge.UserChargeEngine;
import com.sys.volunteer.usercharge.engine.ICharge;
import com.sys.volunteer.usercharge.engine.charge.Charge4Supply;
import com.sys.volunteer.usercharge.engine.charge.Charge4User;
import com.sys.volunteer.users.UserService;
import com.sys.volunteer.vo.MainorderVO;
import com.sys.volunteer.vo.StatisticsOrderVO;

import ft.otp.a.b;

@Service
@Transactional
public class OrderServiceBean extends CommonDao implements OrderService {

	@Resource
	UserService userService;
	@Resource
	OrderQueryResponseNewService orderQueryResponseNewService;
	@Resource
	OperationLogService operationLogService;
	@Resource
	UseraccountService useraccountService;
	@Resource
	UseraccountDealDetailService useraccountDealDetailService;
	@Resource
	SupplyService supplyService;
	@Resource
	GoodsService goodsService;
	@Resource
	GoodsTypeService goodsTypeService;

	@Override
	public Mainorder addMainOrder(Mainorder mainorder, String mobile,
			Users currentUser, SupplyInterface supplyInterface,
			int goodsNo, int isTerminal, String terminalNo, int orderType) {
		mainorder.setMobile(mobile);
		mainorder.setUsers(currentUser);
		mainorder.setUserName(currentUser.getUserName());
		mainorder.setTotalMoney(new Double(supplyInterface.getValue()*goodsNo));
		mainorder.setCreateTime(new Date());
		mainorder.setReversalTime(null);
		mainorder.setSupplyInterface(supplyInterface);
		mainorder.setSupply(supplyInterface.getSupply());
		mainorder.setSupplyName(supplyInterface.getSupplyName());
		mainorder.setCanReverse(supplyInterface.getCanReverse());
		Goods goods = goodsService.findById(supplyInterface.getGoods().getGoodsId());
		GoodsType goodsType = (GoodsType) goodsTypeService.findById(GoodsType.class, goods.getGoodsType().getGoodsTypeId());
		
//		if(goods.getProvinceCode()>=1200&&goods.getProvinceCode()<1300){
//			mainorder.setIsLocal(1);
//		}else{
//			mainorder.setIsLocal(0);
//		}
		int isLocal = 0;
		int isLocalTemp = goods.getGoodsFlag();
		int isLocalTemp2 = goods.getProvinceCode();
		if(isLocalTemp2<1200&&isLocalTemp2>=1300){
			isLocal = 0;
		}else {
			switch(isLocalTemp){
			case 10:
				isLocal = 2;
				break;
			case 11:
				isLocal = 4;
				break;
			case 12:
				isLocal = 3;
				break;
			case 30:
				isLocal = 4;
				break;
			case 31:
				isLocal = 4;
				break;
			case 32:
				isLocal = 4;
				break;
			default:
				isLocal = 5;
				break;
			}
		}
		mainorder.setIsLocal(isLocal);
		mainorder.setGoodsType(goodsType);
		mainorder.setGoodsTypeName(goodsType.getGoodsTypeName());
		mainorder.setGoods(supplyInterface.getGoods());
		mainorder.setGoodsName(supplyInterface.getGoodsName());
		mainorder.setGoodsFlag(goods.getGoodsFlag());
		mainorder.setGoodsNo(goodsNo);
		mainorder.setGoodsValue(new Double(supplyInterface.getValue()));
		mainorder.setStockPrice(supplyInterface.getStockPrice()*goodsNo);
		mainorder.setRetailPrice(supplyInterface.getRetailPrice()*goodsNo);
		//设置佣金
		mainorder.setCommission((supplyInterface.getValue()-supplyInterface.getRetailPrice())*goodsNo);
		if (isTerminal == 1) {
			mainorder.setTerminalNo(terminalNo);
		}
		mainorder.setIsTerminal(isTerminal);
		if (orderType==1) {
			mainorder.setState(Const.MainOrderState.APPLY_PROCESS);
		}else {
			mainorder.setState(Const.MainOrderState.PROCESS_SUCCESS);
		}
		mainorder.setOrderType(orderType);
		mainorder.setReversalState(Const.OrderReversalState.NON_REVERSAL);
		mainorder.setRespTime(0);
		mainorder.setRefreshTime(System.currentTimeMillis()+30*1000);
		mainorder.setMoneyBack(1);
		mainorder.setIsBatch(0);
		mainorder.setBatchOrderState(Const.BatchOrderState.PROCESSING);
		this.save(mainorder);
		// 增量备份
		BackupEngine backupEngine = BackupEngine.getInstance();
		IBackupVO vo = new MainorderBackupVO(mainorder);
		backupEngine.getTaskList().add(vo);
		return mainorder;
	}
	
	@Override
	public Mainorder addMainOrderRecharge(Mainorder lastMainorder, SupplyInterface supplyInterface) {
		Mainorder mainorder = new Mainorder();
		mainorder.setLastOrderId(lastMainorder.getMainOrderId());
		if (lastMainorder.getLastSupplyIds() == null || lastMainorder.getLastSupplyIds().equals("")) {
			mainorder.setLastSupplyIds(lastMainorder.getSupply().getId()+"");
		}else {
			mainorder.setLastSupplyIds(lastMainorder.getLastSupplyIds()+","+lastMainorder.getSupply().getId());
		}
		mainorder.setMobile(lastMainorder.getMobile());
		mainorder.setUsers(lastMainorder.getUsers());
		mainorder.setUserName(lastMainorder.getUserName());
		mainorder.setTotalMoney(lastMainorder.getTotalMoney());
		mainorder.setCreateTime(new Date());
		
		mainorder.setSupplyInterface(supplyInterface);
		mainorder.setSupply(supplyInterface.getSupply());
		mainorder.setSupplyName(supplyInterface.getSupplyName());
		mainorder.setCanReverse(supplyInterface.getCanReverse());
		Goods goods = goodsService.findById(supplyInterface.getGoods().getGoodsId());
		GoodsType goodsType = (GoodsType) goodsTypeService.findById(GoodsType.class, goods.getGoodsType().getGoodsTypeId());
		mainorder.setGoodsType(goodsType);
		mainorder.setGoodsTypeName(goodsType.getGoodsTypeName());
		mainorder.setGoods(supplyInterface.getGoods());
		mainorder.setGoodsName(supplyInterface.getGoodsName());
		mainorder.setGoodsFlag(goods.getGoodsFlag());
		mainorder.setGoodsNo(lastMainorder.getGoodsNo());
		mainorder.setGoodsValue(new Double(supplyInterface.getValue()));
		mainorder.setStockPrice(supplyInterface.getStockPrice()*lastMainorder.getGoodsNo());
		mainorder.setRetailPrice(supplyInterface.getRetailPrice()*lastMainorder.getGoodsNo());
		//设置佣金
		mainorder.setCommission((supplyInterface.getValue()-supplyInterface.getRetailPrice())*mainorder.getGoodsNo());
		mainorder.setIsTerminal(lastMainorder.getIsTerminal());
		mainorder.setTerminalNo(lastMainorder.getTerminalNo());
		if (lastMainorder.getOrderType()==1) {
			mainorder.setState(Const.MainOrderState.APPLY_PROCESS);
		}else {
			mainorder.setState(Const.MainOrderState.PROCESS_SUCCESS);
		}
		mainorder.setIsLocal(lastMainorder.getIsLocal());
		mainorder.setOrderType(lastMainorder.getOrderType());
		mainorder.setReversalState(Const.OrderReversalState.NON_REVERSAL);
		mainorder.setRespTime(0);
		mainorder.setRefreshTime(System.currentTimeMillis()+30*1000);
		mainorder.setMoneyBack(1);
		mainorder.setIsBatch(0);
		mainorder.setBatchOrderState(Const.BatchOrderState.PROCESSING);
		this.save(mainorder);
		// 增量备份
		BackupEngine backupEngine = BackupEngine.getInstance();
		IBackupVO vo = new MainorderBackupVO(mainorder);
		backupEngine.getTaskList().add(vo);
		return mainorder;
	}
	
	@Override
	public Mainorder addMainOrderForTelecom(Mainorder mainorder, String mobile,Double value,
			Users currentUser, SupplyInterface supplyInterface,
			int goodsNo, int isTerminal, String terminalNo, int orderType) {
		mainorder.setMobile(mobile);
		mainorder.setUsers(currentUser);
		mainorder.setUserName(currentUser.getUserName());
		mainorder.setTotalMoney(value*goodsNo);
		mainorder.setCreateTime(new Date());
		mainorder.setReversalTime(null);
		mainorder.setSupplyInterface(supplyInterface);
		mainorder.setSupply(supplyInterface.getSupply());
		mainorder.setSupplyName(supplyInterface.getSupplyName());
		mainorder.setCanReverse(supplyInterface.getCanReverse());
		Goods goods = goodsService.findById(supplyInterface.getGoods().getGoodsId());
		GoodsType goodsType = (GoodsType) goodsTypeService.findById(GoodsType.class, goods.getGoodsType().getGoodsTypeId());
//		if(goods.getProvinceCode()>=1200&&goods.getProvinceCode()<1300){
//			mainorder.setIsLocal(1);
//		}else{
//			mainorder.setIsLocal(0);
//		}
		int isLocal = 0;
		int isLocalTemp = goods.getGoodsFlag();
		int isLocalTemp2 = goods.getProvinceCode();
		if(isLocalTemp2<1200&&isLocalTemp2>=1300){
			isLocal = 0;
		}else {
			switch(isLocalTemp){
			case 10:
				isLocal = 2;
				break;
			case 11:
				isLocal = 4;
				break;
			case 12:
				isLocal = 3;
				break;
			case 30:
				isLocal = 4;
				break;
			case 31:
				isLocal = 4;
				break;
			case 32:
				isLocal = 4;
				break;
			default:
				isLocal = 5;
				break;
			}
		}
		mainorder.setIsLocal(isLocal);
		mainorder.setGoodsType(goodsType);
		mainorder.setGoodsTypeName(goodsType.getGoodsTypeName());
		mainorder.setGoods(supplyInterface.getGoods());
		mainorder.setGoodsName(supplyInterface.getGoodsName());
		mainorder.setGoodsFlag(goods.getGoodsFlag());
		mainorder.setGoodsNo(goodsNo);
		mainorder.setGoodsValue(value);
		mainorder.setStockPrice(value*supplyInterface.getStockPrice()*goodsNo);
		mainorder.setRetailPrice(value*supplyInterface.getRetailPrice()*goodsNo);
		//设置佣金
		mainorder.setCommission((value-mainorder.getRetailPrice())*goodsNo);
		if (isTerminal == 1) {
			mainorder.setTerminalNo(terminalNo);
		}
		mainorder.setIsTerminal(isTerminal);
		if (orderType==1) {
			mainorder.setState(Const.MainOrderState.APPLY_PROCESS);
		}else {
			mainorder.setState(Const.MainOrderState.PROCESS_SUCCESS);
		}
		mainorder.setOrderType(orderType);
		mainorder.setReversalState(Const.OrderReversalState.NON_REVERSAL);
		mainorder.setRespTime(0);
		mainorder.setRefreshTime(System.currentTimeMillis()+30*1000);
		mainorder.setMoneyBack(1);
		mainorder.setIsBatch(0);
		mainorder.setBatchOrderState(Const.BatchOrderState.PROCESSING);
		this.save(mainorder);
		// 增量备份
		BackupEngine backupEngine = BackupEngine.getInstance();
		IBackupVO vo = new MainorderBackupVO(mainorder);
		backupEngine.getTaskList().add(vo);
		return mainorder;
	}
	
	@Override
	public Mainorder addMainOrderForGameRecharge(Mainorder mainorder, String mobile,Double value,
			Users currentUser, SupplyInterface supplyInterface,
			int goodsNo, int isTerminal, String terminalNo, int orderType, String at, String gameId, 
			String autoGameId, String atVerify, String clientIp) {
		mainorder.setMobile(mobile);
		mainorder.setUsers(currentUser);
		mainorder.setUserName(currentUser.getUserName());
		mainorder.setTotalMoney(value*goodsNo);
		mainorder.setCreateTime(new Date());
		mainorder.setReversalTime(null);
		mainorder.setSupplyInterface(supplyInterface);
		mainorder.setSupply(supplyInterface.getSupply());
		mainorder.setSupplyName(supplyInterface.getSupplyName());
		mainorder.setCanReverse(supplyInterface.getCanReverse());
		Goods goods = goodsService.findById(supplyInterface.getGoods().getGoodsId());
		GoodsType goodsType = (GoodsType) goodsTypeService.findById(GoodsType.class, goods.getGoodsType().getGoodsTypeId());
//		if(goods.getProvinceCode()>=1200&&goods.getProvinceCode()<1300){
//			mainorder.setIsLocal(1);
//		}else{
//			mainorder.setIsLocal(0);
//		}
		int isLocal = 0;
		int isLocalTemp = goods.getGoodsFlag();
		int isLocalTemp2 = goods.getProvinceCode();
		if(isLocalTemp2<1200&&isLocalTemp2>=1300){
			isLocal = 0;
		}else {
			switch(isLocalTemp){
			case 10:
				isLocal = 2;
				break;
			case 11:
				isLocal = 4;
				break;
			case 12:
				isLocal = 3;
				break;
			case 30:
				isLocal = 4;
				break;
			case 31:
				isLocal = 4;
				break;
			case 32:
				isLocal = 4;
				break;
			default:
				isLocal = 5;
				break;
			}
		}
		mainorder.setIsLocal(isLocal);
		mainorder.setGoodsType(goodsType);
		mainorder.setGoodsTypeName(goodsType.getGoodsTypeName());
		mainorder.setGoods(supplyInterface.getGoods());
		mainorder.setGoodsName(supplyInterface.getGoodsName());
		mainorder.setGoodsFlag(goods.getGoodsFlag());
		mainorder.setGoodsNo(goodsNo);
		mainorder.setGoodsValue(value);
		mainorder.setStockPrice(value*supplyInterface.getStockPrice()*goodsNo);
		mainorder.setRetailPrice(value*supplyInterface.getRetailPrice()*goodsNo);
		//设置佣金
		mainorder.setCommission((supplyInterface.getValue()-supplyInterface.getRetailPrice())*goodsNo);
		if (isTerminal == 1) {
			mainorder.setTerminalNo(terminalNo);
		}
		mainorder.setIsTerminal(isTerminal);
		if (orderType==1) {
			mainorder.setState(Const.MainOrderState.APPLY_PROCESS);
		}else {
			mainorder.setState(Const.MainOrderState.PROCESS_SUCCESS);
		}
		mainorder.setOrderType(orderType);
		mainorder.setReversalState(Const.OrderReversalState.NON_REVERSAL);
		mainorder.setRespTime(0);
		mainorder.setRefreshTime(System.currentTimeMillis()+30*1000);
		mainorder.setMoneyBack(1);
		mainorder.setIsBatch(0);
		mainorder.setBatchOrderState(Const.BatchOrderState.PROCESSING);
		mainorder.setAt(at);
		mainorder.setAtVerify(atVerify);
		if (autoGameId==null) {
			mainorder.setAutoGameId("");
		}else {
			mainorder.setAutoGameId(autoGameId);
		}
		if (gameId==null) {
			mainorder.setGameId("");
		}else {
			mainorder.setGameId(gameId);
		}
		mainorder.setClientIp(clientIp);
		this.save(mainorder);
		// 增量备份
		BackupEngine backupEngine = BackupEngine.getInstance();
		IBackupVO vo = new MainorderBackupVO(mainorder);
		backupEngine.getTaskList().add(vo);
		return mainorder;
	}
	
	@Override
	public Mainorder addMainOrderForQQ(Mainorder mainorder, String mobile,Double value,
			Users currentUser, SupplyInterface supplyInterface,
			int goodsNo, int isTerminal, String terminalNo, int orderType) {
		mainorder.setMobile(mobile);
		mainorder.setUsers(currentUser);
		mainorder.setUserName(currentUser.getUserName());
		mainorder.setTotalMoney(value*goodsNo);
		mainorder.setCreateTime(new Date());
		mainorder.setReversalTime(null);
		mainorder.setSupplyInterface(supplyInterface);
		mainorder.setSupply(supplyInterface.getSupply());
		mainorder.setSupplyName(supplyInterface.getSupplyName());
		mainorder.setCanReverse(supplyInterface.getCanReverse());
		Goods goods = goodsService.findById(supplyInterface.getGoods().getGoodsId());
		GoodsType goodsType = (GoodsType) goodsTypeService.findById(GoodsType.class, goods.getGoodsType().getGoodsTypeId());
//		if(goods.getProvinceCode()>=1200&&goods.getProvinceCode()<1300){
//			mainorder.setIsLocal(1);
//		}else{
//			mainorder.setIsLocal(0);
//		}
		int isLocal = 0;
		int isLocalTemp = goods.getGoodsFlag();
		int isLocalTemp2 = goods.getProvinceCode();
		if(isLocalTemp2<1200&&isLocalTemp2>=1300){
			isLocal = 0;
		}else {
			switch(isLocalTemp){
			case 10:
				isLocal = 2;
				break;
			case 11:
				isLocal = 4;
				break;
			case 12:
				isLocal = 3;
				break;
			case 30:
				isLocal = 4;
				break;
			case 31:
				isLocal = 4;
				break;
			case 32:
				isLocal = 4;
				break;
			default:
				isLocal = 5;
				break;
			}
		}
		mainorder.setIsLocal(isLocal);
		mainorder.setGoodsType(goodsType);
		mainorder.setGoodsTypeName(goodsType.getGoodsTypeName());
		mainorder.setGoods(supplyInterface.getGoods());
		mainorder.setGoodsName(supplyInterface.getGoodsName());
		mainorder.setGoodsFlag(goods.getGoodsFlag());
		mainorder.setGoodsNo(goodsNo);
		mainorder.setGoodsValue(value);
		mainorder.setStockPrice(value*supplyInterface.getStockPrice()*goodsNo);
		mainorder.setRetailPrice(value*supplyInterface.getRetailPrice()*goodsNo);
		//设置佣金
		mainorder.setCommission((supplyInterface.getValue()-supplyInterface.getRetailPrice())*goodsNo);
		if (isTerminal == 1) {
			mainorder.setTerminalNo(terminalNo);
		}
		mainorder.setIsTerminal(isTerminal);
		if (orderType==1) {
			mainorder.setState(Const.MainOrderState.APPLY_PROCESS);
		}else {
			mainorder.setState(Const.MainOrderState.PROCESS_SUCCESS);
		}
		mainorder.setOrderType(orderType);
		mainorder.setReversalState(Const.OrderReversalState.NON_REVERSAL);
		mainorder.setRespTime(0);
		mainorder.setRefreshTime(System.currentTimeMillis()+30*1000);
		mainorder.setMoneyBack(1);
		mainorder.setIsBatch(0);
		mainorder.setBatchOrderState(Const.BatchOrderState.PROCESSING);
		this.save(mainorder);
		// 增量备份
		BackupEngine backupEngine = BackupEngine.getInstance();
		IBackupVO vo = new MainorderBackupVO(mainorder);
		backupEngine.getTaskList().add(vo);
		return mainorder;
	}
	
	@Override
	public Mainorder addMainOrderForBatch(Mainorder mainorder, String mobile,
			Users currentUser, SupplyInterface supplyInterface,
			int goodsNo, int isTerminal, String terminalNo, int orderType,String batchOrderId,double totalMoney) {
		mainorder.setMobile(mobile);
		mainorder.setUsers(currentUser);
		mainorder.setUserName(currentUser.getUserName());
		mainorder.setTotalMoney(new Double(supplyInterface.getValue()*goodsNo));
		mainorder.setCreateTime(new Date());
		mainorder.setReversalTime(null);
		mainorder.setSupplyInterface(supplyInterface);
		mainorder.setSupply(supplyInterface.getSupply());
		mainorder.setSupplyName(supplyInterface.getSupplyName());
		mainorder.setCanReverse(supplyInterface.getCanReverse());
		Goods goods = goodsService.findById(supplyInterface.getGoods().getGoodsId());
		GoodsType goodsType = (GoodsType) goodsTypeService.findById(GoodsType.class, goods.getGoodsType().getGoodsTypeId());
		
//			if(goods.getProvinceCode()>=1200&&goods.getProvinceCode()<1300){
//				mainorder.setIsLocal(1);
//			}else{
//				mainorder.setIsLocal(0);
//			}
		int isLocal = 0;
		int isLocalTemp = goods.getGoodsFlag();
		int isLocalTemp2 = goods.getProvinceCode();
		if(isLocalTemp2<1200&&isLocalTemp2>=1300){
			isLocal = 0;
		}else {
			switch(isLocalTemp){
			case 10:
				isLocal = 2;
				break;
			case 11:
				isLocal = 4;
				break;
			case 12:
				isLocal = 3;
				break;
			case 30:
				isLocal = 4;
				break;
			case 31:
				isLocal = 4;
				break;
			case 32:
				isLocal = 4;
				break;
			default:
				isLocal = 5;
				break;
			}
		}
		mainorder.setIsLocal(isLocal);
		mainorder.setGoodsType(goodsType);
		mainorder.setGoodsTypeName(goodsType.getGoodsTypeName());
		mainorder.setGoods(supplyInterface.getGoods());
		mainorder.setGoodsName(supplyInterface.getGoodsName());
		mainorder.setGoodsFlag(goods.getGoodsFlag());
		mainorder.setGoodsNo(goodsNo);
		mainorder.setGoodsValue(new Double(supplyInterface.getValue()));
		mainorder.setStockPrice(supplyInterface.getStockPrice()*goodsNo);
		mainorder.setRetailPrice(supplyInterface.getRetailPrice()*goodsNo);
		//设置佣金
		mainorder.setCommission((supplyInterface.getValue()-supplyInterface.getRetailPrice())*goodsNo);
		if (isTerminal == 1) {
			mainorder.setTerminalNo(terminalNo);
		}
		mainorder.setIsTerminal(isTerminal);
		mainorder.setState(Const.MainOrderState.APPLY_PROCESS);
		mainorder.setOrderType(orderType);
		mainorder.setReversalState(Const.OrderReversalState.NON_REVERSAL);
		mainorder.setRespTime(0);
		//mainorder.setRefreshTime(System.currentTimeMillis()+30*1000);
		mainorder.setMoneyBack(1);
		mainorder.setBatchOrderId(batchOrderId);
		mainorder.setIsBatch(1);
		mainorder.setBatchOrderState(Const.BatchOrderState.WAIT_COMMIT);
		this.save(mainorder);
		// 增量备份
		BackupEngine backupEngine = BackupEngine.getInstance();
		IBackupVO vo = new MainorderBackupVO(mainorder);
		backupEngine.getTaskList().add(vo);
		return mainorder;
	}

	@Override
	public void addOrderDetail(Orderdetail orderdetail, Mainorder mainorder,
			List<CardLib> cardLibs) {
		orderdetail.setMobile(mainorder.getMobile());
		orderdetail.setCreateTime(mainorder.getCreateTime());
		orderdetail.setDesc(mainorder.getDesc());
		orderdetail.setMainorder(mainorder);
		orderdetail.setState(mainorder.getState());
		orderdetail.setReversalState(mainorder.getReversalState());
		orderdetail.setUsers(mainorder.getUsers());
		orderdetail.setMoney(mainorder.getTotalMoney());
		Supply supply = mainorder.getSupply();
		orderdetail.setSupply(supply);
		orderdetail.setSupplyName(mainorder.getSupplyName());
		// TODO:暂时先允许一次买一张卡
		if (cardLibs != null) {
			String cardIds = "";
			for (int i = 0; i < cardLibs.size(); i++) {
				if (i == 0) {
					cardIds = cardLibs.get(i).getCardId();
				} else {
					cardIds = cardLibs.get(i).getCardId() + "," + cardIds;
				}
			}
			orderdetail.setCardIds(cardIds);
			orderdetail.setCardNo(cardLibs.size());
		}
		this.save(orderdetail);
	}

	@Override
	public Mainorder findOrderById(String orderId) {
		Mainorder mainorder = (Mainorder) findById(Mainorder.class, orderId);
		return mainorder;
	}
	
	@Override
	public BatchOrder findBatchOrderById(String orderId) {
		BatchOrder batchOrder = (BatchOrder) findById(BatchOrder.class, orderId);
		return batchOrder;
	}
	
	@Override
	public List<Mainorder> findBatchOrderByOrderId(String orderId) {
		DetachedCriteria dec = DetachedCriteria.forClass(Mainorder.class);
		dec.add(Restrictions.eq("batchOrderId", orderId));
		List<Mainorder> list = findByDetachedCriteria(dec);
		return list;
	}
	
	@Override
	public List<Mainorder> findBatchOrderByOrderIdAndState(String orderId,int batchOrderState) {
		DetachedCriteria dec = DetachedCriteria.forClass(Mainorder.class);
		dec.add(Restrictions.eq("batchOrderId", orderId));
		dec.add(Restrictions.eq("batchOrderState", batchOrderState));
		
		dec.add(Restrictions.or(Restrictions.or(Restrictions.eq("state", Const.MainOrderState.APPLY_PROCESS),
													Restrictions.eq("state", Const.MainOrderState.USER_CANCEL_APPLY)), 
				Restrictions.eq("reversalState", Const.OrderReversalState.APPLY_REVERSAL)));
		List<Mainorder> list = findByDetachedCriteria(dec);
		return list;
	}

	/**
	 * 根据订单id查找订单明细
	 */
	@Override
	public List<Orderdetail> findOrderdetailsByOrderId(String orderId) {
		DetachedCriteria dec = DetachedCriteria.forClass(Orderdetail.class);
		dec.add(Restrictions.eq("mainorder.id", orderId));
		List<Orderdetail> list = findByDetachedCriteria(dec);
		return list;
	}

	@Override
	public void cancelOrder(Mainorder mainorder) {
		//设置订单状态为取消
		mainorder.setState(Const.MainOrderState.USER_CANCEL);
		this.update(mainorder);
		List<Orderdetail> list = findOrderdetailsByOrderId(mainorder.getMainOrderId());
		for (Orderdetail orderdetail : list) {
			orderdetail.setState(mainorder.getState());
			this.update(orderdetail);
		}
	}
	
	@Override
	public void cancelOrderApply(Mainorder mainorder) {
		//设置订单状态为取消
		mainorder.setState(Const.MainOrderState.USER_CANCEL_APPLY);
		this.update(mainorder);
		List<Orderdetail> list = findOrderdetailsByOrderId(mainorder.getMainOrderId());
		for (Orderdetail orderdetail : list) {
			orderdetail.setState(mainorder.getState());
			this.update(orderdetail);
		}
	}

	@Override
	public int reversalOrder(Mainorder mainorder) {
		SupplyKernel supplyKernel=SupplyKernel.getInstance();
		ISupply supply=supplyKernel.getSupplyMapKeyId().get(mainorder.getSupply().getId());
		int result=supply.reverse(mainorder);
		if (result==Const.reverseResult.SUCCESS) {
			if (mainorder.getMoneyBack().equals(1)) {
				Users user = userService.findUserById(mainorder.getUsers().getUserId());
//				useraccountService.refreshAccountes(mainorder,user,null,null,new UserCharge(), mainorder.getTotalMoney(),Const.UseraccountdealdetailFlag.REVERSAL);
//				SupplyKernel.getInstance().reverseBalance(mainorder);
				ICharge iCharge = new Charge4User(new UserCharge(), mainorder, mainorder.getTotalMoney(), user, null, null, Const.UseraccountdealdetailFlag.REVERSAL);
				ICharge iCharge2 = new Charge4Supply(new UserCharge(), mainorder, mainorder.getStockPrice()*mainorder.getGoodsNo(), (Supply)supply, null, null, Const.UseraccountdealdetailFlag.REVERSAL);
				UserChargeEngine.getInstance().addLast(iCharge);
			    UserChargeEngine.getInstance().addLast(iCharge2);
				this.updateReversalState(mainorder, Const.OrderReversalState.PROCESS_SUCCESS);
				this.updateMoneyBack(mainorder, 0);
			}
		}
		return result;
	}

	@Override
	public void updateOrderState(Mainorder mainorder, int state) {
		mainorder.setState(state);
		update(mainorder);
		List<Orderdetail> orderdetails = this
				.findOrderdetailsByOrderId(mainorder.getMainOrderId());
		for (Orderdetail orderdetail : orderdetails) {
			orderdetail.setState(state);
			update(orderdetail);
		}
	}
	
	@Override
	public void updateReversalState(Mainorder mainorder, int reversalState) {
		mainorder.setReversalState(reversalState);
		//记录点击冲正的时间
        mainorder.setReversalTime(new Date());	
		update(mainorder);
		List<Orderdetail> orderdetails = this
				.findOrderdetailsByOrderId(mainorder.getMainOrderId());
		for (Orderdetail orderdetail : orderdetails) {
			orderdetail.setReversalState(reversalState);
			//记录点击冲正的时间
	        mainorder.setReversalTime(new Date());	
			update(orderdetail);
		}
	}
	
	@Override
	public void updateOrderCardLibs(Mainorder mainorder,List<CardLib> cardLibs){
		String cardLibIds = "";
		for (int i = 0; i < cardLibs.size(); i++) {
			if (i==cardLibs.size()-1) {
				cardLibIds += cardLibs.get(i).getCardId();
			}else {
				cardLibIds += cardLibs.get(i).getCardId()+",";
			}
		}
		mainorder.setCardLibIds(cardLibIds);
		update(mainorder);
	}
	
	@Override
	public void updateOrderRefreshTime(Mainorder mainorder,long time) {
		mainorder.setRefreshTime(System.currentTimeMillis()+time);
		this.update(mainorder);
	}
	
	@Override
	public void updateMoneyBack(Mainorder mainorder, int moneyBack) {
		mainorder.setMoneyBack(moneyBack);
		this.update(mainorder);
	}
	
	@Override
	public void updateOrderRespTime(Mainorder mainorder) {
		mainorder.setRespTime(mainorder.getRespTime()+1);
		this.update(mainorder);
	}
	
	@Override
	public void updateCBalance(Mainorder mainorder, double cBalance){
		mainorder.setcBalance(cBalance);
		this.update(mainorder);
	}
	
	@Override
	public List<MainorderVO> listMainorderVOs(Date startTime,Date endTime,String userId) throws Exception {
		String sql = "SELECT order_type AS orderType,SUM(goods_value) AS sumValue,SUM(stock_price) AS sumStockPrice,SUM(commission) AS sumComm, " +
					"SUM(retail_price) AS sumRetail FROM mainorder " +
					"WHERE state=1 AND reversal_state IN (-1,2) AND " +
					"createTime BETWEEN ? AND ? ";
		Object[] obj = new Object[]{startTime,endTime};
		if(userId != null){
			sql = sql + " AND user_id = ? ";
			Object[] obj2 = new Object[]{startTime,endTime,userId};
			obj = obj2;
		}
		sql = sql + "GROUP BY order_type";
		PageView pageView = new PageView(Integer.MAX_VALUE, 1);
		QueryResult qr = this.getScrollDataBySQLQuery(sql, obj, pageView.getFirstResult(), pageView.getMaxresult(), null, null, MainorderVO.class);
		return qr.getResultlist();
	}
	
	@Override
	public List<Mainorder> findProcessingOrder(){
		String sql = "SELECT mainOrderId,user_id AS userId,user_name AS userName,goods_type_id AS goodsTypeId,goods_type_name AS goodsTypeName, " +
				" desc,goods_id AS goodsId,goods_name AS goodsName,goods_no AS goodsNo, " +
				" goods_value AS goodsValue,card_lib_ids AS cardLibIds,totalMoney,retail_price AS retailPrice, " +
				" stock_price AS stockPrice,commission,createTime,state,supply_id AS supplyId, " +
				" supply_name AS supplyName,sign,mobile,terminalNo,is_terminal AS isTerminal, " +
				" order_type AS orderType,can_reverse AS canReverse,reversal_state AS reversalState " +
				" FROM mainorder WHERE state = 0 LIMIT 10";
		List<Mainorder> list = this.listBySqlQuery(sql);
		return list;
	}

	@Override
	public List<OrderVO> findLimitFive(String userId) {
		String sql = "SELECT mainOrderId, user_id AS userId, user_name AS userName, goods_type_id AS goodsTypeId,"+
                     "goods_type_name AS goodsTypeName, goods_id AS goodsId, goods_name AS goodsName, goods_no AS goodsNo,"+
                     " card_lib_ids AS cardLibIds, totalMoney, retail_price AS retailPrice,c_balance AS cBalance ,"+
                     "stock_price AS stockPrice, commission, createTime, state, supply_id AS supplyId,  supply_name AS supplyName,"+
                     "mobile, terminal_no as terminalNo, is_terminal AS isTerminal, order_type AS orderType, can_reverse AS canReverse,"+
                     " reversal_state AS reversalState FROM mainorder where user_id = '"+userId+"'  ORDER BY createTime DESC LIMIT 5 ";
		Object[] obj = new Object[]{};
		List<OrderVO> list = (List<OrderVO>)this.listBySqlQuery(sql, obj, null, null, OrderVO.class);
		
		return list;
	}
	
	@Override
	public List<Mainorder> findReversalOrderByMobileAndAmount(String mobile, Double amount) {
		DetachedCriteria dec = DetachedCriteria.forClass(Mainorder.class);
		dec.add(Restrictions.eq("mobile", mobile));
		dec.add(Restrictions.eq("totalMoney", amount));
		List<Mainorder> list = findByDetachedCriteria(dec);
		return list;
		
	}

	@Override
	public List<StatisticsOrderVO> findStatisticsOrderVOEx(String userId,int isLocal,Date beginTime,Date endTime){
		String sql = "";
		List<StatisticsOrderVO> list = new ArrayList<StatisticsOrderVO>();
		if(beginTime!=null&&endTime!=null){
			String startDateTemp = DateUtil.DateToString(beginTime, "yyyy-MM-dd HH:mm:ss");
			String endDateTemp = DateUtil.DateToString(endTime, "yyyy-MM-dd HH:mm:ss");
			
			sql = "SELECT u.user_name AS userName,SUM(m.stock_price) AS stockPriceSum, COUNT(m.mainOrderId) AS sumAll,SUM(m.goods_value) AS amountAll FROM mainorder AS m,users AS u WHERE m.user_id = u.user_id AND u.parent_user_id='"+userId;
			if(isLocal==255){
				sql+="' AND m.is_local != 5" ;
			}else{
				sql+="' AND m.is_local = " + isLocal;
			}
           sql+=" AND m.createTime BETWEEN '"+startDateTemp+"' AND '"+endDateTemp+"' AND m.state = "+1+" AND m.reversal_state IN (-1,2) GROUP BY u.user_id";
			
		}else{
			sql = "SELECT u.user_name AS userName,SUM(m.stock_price) AS stockPriceSum,COUNT(m.mainOrderId) AS sumAll,SUM(m.goods_value) AS amountAll FROM mainorder AS m,users AS u WHERE m.user_id = u.user_id AND u.parent_user_id='"+userId;
			if(isLocal==255){
				sql+="' AND m.is_local != 5" ;
			}else{
				sql+="' AND m.is_local = " + isLocal;
			}
            sql+=" AND DATE(m.createTime) =  CURDATE() AND m.state = "+1+" AND m.reversal_state IN (-1,2) GROUP BY u.user_id";
		}
		Object[] obj = new Object[]{};
		 list = (List<StatisticsOrderVO>)this.listBySqlQuery(sql, obj, null, null, StatisticsOrderVO.class);
		return list;
	}

	@Override
	public List<StatisticsOrderVO> findStatisticsOrderVOExEx(String userId, int isLocal, Date beginTime, Date endTime) {
		String sql = "";
		List<StatisticsOrderVO> list = new ArrayList<StatisticsOrderVO>();
			if(beginTime!=null&&endTime!=null){
				String startDateTemp = DateUtil.DateToString(beginTime, "yyyy-MM-dd HH:mm:ss");
				String endDateTemp = DateUtil.DateToString(endTime, "yyyy-MM-dd HH:mm:ss");
				
				sql = "SELECT user_name AS userName,SUM(m.stock_price) AS stockPriceSum,COUNT(mainOrderId) AS sumAll,SUM(goods_value) AS amountAll FROM mainorder AS m WHERE user_id = '"+ userId;
				if(isLocal==255){
					sql+="' AND is_local != 5" ;
				}else{
					sql+="' AND is_local = " + isLocal;
				}
	           sql+=" AND createTime BETWEEN '"+startDateTemp+"' AND '"+endDateTemp+"' AND m.state = "+1+" AND m.reversal_state IN (-1,2) GROUP BY user_id";
				
			}else{
				sql = "SELECT user_name AS userName,SUM(m.stock_price) AS stockPriceSum,COUNT(mainOrderId) AS sumAll,SUM(goods_value) AS amountAll FROM mainorder AS m WHERE  user_id='"+userId;
				if(isLocal==255){
					sql+="' AND is_local != 5" ;
				}else{
					sql+="' AND is_local = " + isLocal;
				}
	            sql+=" AND DATE(createTime) =  CURDATE() AND m.state = "+1+" AND m.reversal_state IN (-1,2) GROUP BY user_id";
			}
		Object[] obj = new Object[]{};
		 list = (List<StatisticsOrderVO>)this.listBySqlQuery(sql, obj, null, null, StatisticsOrderVO.class);
		return list;
	}
	
	@Override
	public BatchOrder addBatchOrder(BatchOrder batchOrder,String mobile,String mainorderId,double totalMoney,Users users,String userName,int goodsNo) {
		batchOrder.setBatchOrderState(Const.BatchOrderState.WAIT_COMMIT);
		batchOrder.setCreateTime(new Date());
		batchOrder.setCurrentProcessNum(0);
		batchOrder.setGoodsNo(goodsNo);
		batchOrder.setMobile(mobile);
		batchOrder.setRestProcessNum(goodsNo);
		batchOrder.setTotalMoney(totalMoney);
		batchOrder.setUserName(userName);
		batchOrder.setUsers(users);
		batchOrder.setMainorderId(mainorderId);
		this.save(batchOrder);
		return batchOrder;
	}
	
	@Override
	public BatchOrder updateBatchOrderMobileAndMainorderId(BatchOrder batchOrder,String mobile,String mainorderId,double value) {
		batchOrder.setMainorderId(batchOrder.getMainorderId()+","+mainorderId);
		batchOrder.setMobile(batchOrder.getMobile()+","+mobile);
		batchOrder.setGoodsNo(batchOrder.getGoodsNo()+1);
		batchOrder.setRestProcessNum(batchOrder.getRestProcessNum()+1);
		batchOrder.setTotalMoney(batchOrder.getTotalMoney()+value);
		this.update(batchOrder);
		return batchOrder;
	}
	
	@Override
	public BatchOrder updateBatchOrder(BatchOrder batchOrder) {
		batchOrder.setCurrentProcessNum(batchOrder.getCurrentProcessNum()+1);
		batchOrder.setRestProcessNum(batchOrder.getRestProcessNum()-1);
		if (batchOrder.getRestProcessNum() == 0) {
			batchOrder.setBatchOrderState(Const.BatchOrderState.PROCESSED);
		}
		this.update(batchOrder);
		return batchOrder;
	}
	
	@Override
	public void updateBatchOrderState(Mainorder mainorder,int batchOrderState) {
		mainorder.setBatchOrderState(batchOrderState);
		this.update(mainorder);
	}

	@Override
	public void updateBatchOrderState(BatchOrder batchOrder,int batchOrderState) {
		batchOrder.setBatchOrderState(batchOrderState);
		this.update(batchOrder);
	}
	
	@Override
	public void updateBatchOrderState(List<Mainorder> mainorder,int batchOrderState) {
		for (Mainorder mainorder2 : mainorder) {
			mainorder2.setBatchOrderState(batchOrderState);
			this.update(mainorder2);
		}
		
	}

	@Override
	public void updateOrderComments(Mainorder mainorder) {
		this.update(mainorder);
	}
	
}
