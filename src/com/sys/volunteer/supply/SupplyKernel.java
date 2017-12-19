package com.sys.volunteer.supply;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.sys.volunteer.cardLib.CardLibService;
import com.sys.volunteer.goods.GoodsService;
import com.sys.volunteer.pojo.Goods;
import com.sys.volunteer.pojo.Mainorder;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Useraccountdealdetail;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.springContext.SpringContextUtil;
import com.sys.volunteer.supply.usage.EmptySupply;
import com.sys.volunteer.supply.usage.ISupply;
import com.sys.volunteer.supplyinterface.SupplyInterfaceService;


public class SupplyKernel {

	private SupplyService supplyService;
	
	private SupplyInterfaceService supplyInterfaceService;
	
	private GoodsService goodsService;
	
	private CardLibService cardLibService;
	
	private static SupplyKernel hinstance;

	public static final String DEFAULT_SUPPLY_CLASSNAME = "EmptySupply";
	
	/**
	 * 当前类所属的命名空间(动态获取)
	 */
	public static String myPackageName;
	
	private List<Supply> supplyList;
	
	private Map<Integer, ISupply> supplyMapKeyId;
	
	private Map<Integer, SupplyInterface> supplyInterfaceMapKeyId;
	
	private Map<Integer, SupplyInterface> supplyInterfaceMapKeyEnableId;

	private List<ISupply> listBySellType;
	
	/**
	 * 单例,私有构造函数
	 */
	private SupplyKernel() {
		myPackageName = this.getClass().getPackage().getName();
		ApplicationContext act = SpringContextUtil.getApplicationContext();
		supplyService = (SupplyService)act.getBean("supplyServiceBean") ;
		cardLibService = (CardLibService)act.getBean("cardLibServiceBean");
		supplyInterfaceService = (SupplyInterfaceService)act.getBean("supplyInterfaceServiceBean");
		goodsService = (GoodsService)act.getBean("goodsServiceBean");
		//获取供货商列表
		supplyList = supplyService.getSupplyList();
		
		if ((supplyList == null) || (supplyList.size() < 1)) {
			System.out.println("!!加载服务器供货商表出错!!");
		}
		
		supplyMapKeyId = new HashMap<Integer, ISupply>();
		supplyInterfaceMapKeyId = new HashMap<Integer, SupplyInterface>();
		supplyInterfaceMapKeyEnableId = new HashMap<Integer, SupplyInterface>();
		
		List<SupplyInterface> supplyInterfaces = (List<SupplyInterface>)supplyInterfaceService.findAll(SupplyInterface.class);
		
		for (SupplyInterface supplyInterface : supplyInterfaces) {
			supplyInterfaceMapKeyId.put(supplyInterface.getId(), supplyInterface);
		}
		
		//更新可用接口
		refreshSupplyInterfaceMapKey();
		
		for (int i = 0; i < supplyList.size(); i++) {
			Supply supply = supplyList.get(i);
			if (supply == null) continue;
			String className = supply.getClazzName();

			try {
				Class<?> refClass = Thread.currentThread().getContextClassLoader().loadClass(
					myPackageName + ".usage." + className);

				Object supplyObj;
				supplyObj = refClass.newInstance();
				ISupply iSupply = (ISupply) supplyObj;
				iSupply.assign(supply);
				
				if (supplyObj != null) supplyMapKeyId.put(iSupply.getId(), iSupply);
				
				

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取单例
	 * 
	 * @return
	 * @throws Throwable
	 */
	public static SupplyKernel getInstance() {
		if (hinstance == null) init();
		return hinstance;
	}

	/**
	 * 手动初始化
	 */
	public static void init(){
		hinstance = new SupplyKernel();
	}

	public Map<Integer, ISupply> getSupplyMapKeyId() {
		return supplyMapKeyId;
	}
	
	public Map<Integer, SupplyInterface> getSupplyInterfaceMapKeyId() {
		return supplyInterfaceMapKeyId;
	}

	/**
	 * 获取对应类型的供货商列表
	 * @param sellType
	 * @return
	 */
	public List<ISupply> getListBySellType(Goods goods,int clientType) {
		listBySellType=new ArrayList<ISupply>();
		Iterator iterator=supplyInterfaceMapKeyEnableId.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, SupplyInterface> entry = (Map.Entry<Integer, SupplyInterface>) iterator.next();
			SupplyInterface supplyInterface = entry.getValue();
			ISupply supply = supplyMapKeyId.get(supplyInterface.getSupply().getId());
			if (supply==null) {
				System.out.println("Please check,"+supplyInterface.getSupply().getId()+" is null");
			}
			if (supplyInterface.getState()!=0&&supplyInterface.getGoods().getGoodsId().equals(goods.getGoodsId())&&supply.getIsUsed()!=0) {
				System.out.println("Add Supply Success:"+supply.getSupplyName());
				addByClientType(supply,supplyInterface,clientType);
			}
		}
		//这里需要按供货商积分排序
		Collections.sort(listBySellType, new Comparator<ISupply>() {

			@Override
			public int compare(ISupply o1, ISupply o2) {
				//按积分倒序
				return o2.getAvgScore().compareTo(o1.getAvgScore());
			}
		});
		return listBySellType;
	}
	
	/**
	 * 获取对应类型的供货商列表(去除之前不能充值的供货商)
	 * @param sellType
	 * @return
	 */
	public List<ISupply> getListBySellType(Goods goods,String supplyIds,int clientType) {
		String[] ids = null ;
		if (supplyIds != null && !supplyIds.equals("")) {
			ids = supplyIds.split(",");
		}
		
		listBySellType=new ArrayList<ISupply>();
		Iterator iterator=supplyInterfaceMapKeyEnableId.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, SupplyInterface> entry = (Map.Entry<Integer, SupplyInterface>) iterator.next();
			SupplyInterface supplyInterface = entry.getValue();
			ISupply supply = supplyMapKeyId.get(supplyInterface.getSupply().getId());
			if (supply==null) {
				System.out.println("Please check,"+supplyInterface.getSupply().getId()+" is null");
			}
			if (supplyInterface.getState()!=0&&supplyInterface.getGoods().getGoodsId().equals(goods.getGoodsId())&&supply.getIsUsed()!=0) {
				//System.out.println("Add Supply Success:"+supply.getSupplyName());
				if (ids == null) {
					addByClientType(supply,supplyInterface,clientType);
				}else {
					boolean canAdd = true;
					for (int i = 0; i < ids.length; i++) {
						if (supply.getId().equals(Integer.parseInt(ids[i]))) {
							canAdd = false;
							break;
						}
					}
					if (canAdd) {
						addByClientType(supply,supplyInterface,clientType);
					}
				}
			}
		}
		//这里需要按供货商积分排序
		Collections.sort(listBySellType, new Comparator<ISupply>() {

			@Override
			public int compare(ISupply o1, ISupply o2) {
				//按积分倒序
				return o2.getAvgScore().compareTo(o1.getAvgScore());
			}
		});
		return listBySellType;
	}
	
	/**
	 * 从客户来源区分供货商
	 * @param supply
	 * @param supplyInterface
	 * @param clientType
	 */
	private void addByClientType(ISupply supply,SupplyInterface supplyInterface,int clientType){
		switch (supplyInterface.getClientType()) {
		case 1:
			listBySellType.add(supply);
			break;
		case 2:
		case 3:
			if (supplyInterface.getClientType().equals(clientType)) {
				listBySellType.add(supply);
			}
			break;
		default:
			break;
		}
	}
	
	
	/**
	 * 获取对应类型的供货商列表(不科学,停用)
	 * @param sellType
	 * @return
	 */
//	public List<ISupply> getListBySellTypeNew(AreaCode areaCode,Goods goods) {
//		listBySellType=new ArrayList<ISupply>();
//		List<ISupply> list = this.findSuppliesByIds(areaCode.getSupplyIds());
//		for (ISupply iSupply : list) {
//			SupplyInterface supplyInterface = supplyInterfaceService.findBySupplyAndGoods(iSupply.getId(), goods.getGoodsId());
//			if (supplyInterface!=null) {
//				listBySellType.add(iSupply);
//			}
//		}
//		//这里需要按供货商积分排序
//		Collections.sort(listBySellType, new Comparator<ISupply>() {
//
//			@Override
//			public int compare(ISupply o1, ISupply o2) {
//				//按积分倒序
//				return o2.getAvgScore().compareTo(o1.getAvgScore());
//			}
//		});
//		return listBySellType;
//	}
	
	
	
	/**
	 * 获取一个适合的供货商
	 * @return
	 */
	public ISupply getISupply(List<ISupply> supplies,int goodsNo,double value){
		if (supplies.isEmpty()) {
			System.out.println("getSupply方法:supplies列表为空!");
			return null;
		}
		ISupply sp=null;
		for (ISupply supply : supplies) {
			if (supply.getBalance() == null || supply.getBalance() < value*goodsNo) {
				supplies.remove(supply);
				sp=getISupply(supplies,goodsNo,value);
			}else {
				sp=supply;
				break;
			}
			if (supplies.size()==0) {
				break;
			}
			if (sp!=null) {
				break;
			}
		}
		return sp;
	}
	
	/**
	 * 获取一个适合的供货商(卡密用)
	 * @return
	 */
	public ISupply getISupplyForCard(List<ISupply> supplies,Goods goods,int goodsNo){
		if (supplies.isEmpty()) {
			System.out.println("getSupply方法:supplies列表为空!");
			return null;
		}
		ISupply sp=null;
		for (ISupply supply : supplies) {
			if (!supply.isLeftCard(goods, goodsNo)) {
				supplies.remove(supply);
				sp=getISupplyForCard(supplies,goods,goodsNo);
			}else {
				sp=supply;
				break;
			}
			if (supplies.size()==0) {
				break;
			}
			if (sp!=null) {
				break;
			}
		}
		return sp;
	}
	
	/**
	 * 获取一个适合的供货商(卡密用,指定在线还是私有供货商)
	 * @return
	 */
	public ISupply getISupplyForCard(List<ISupply> supplies,Goods goods,int goodsNo,int supplyType){
		if (supplies.isEmpty()) {
			return null;
		}
		ISupply sp=null;
		for (ISupply supply : supplies) {
			if (supply.getSupplyType()!=supplyType||!supply.isLeftCard(goods, goodsNo)) {
				supplies.remove(supply);
				sp=getISupplyForCard(supplies,goods,goodsNo,supplyType);
			}else {
				sp=supply;
				break;
			}
			if (supplies.size()==0) {
				break;
			}
			if (sp!=null) {
				break;
			}
		}
		return sp;
	}
	
	/**
	 * 刷新评分(内存的)
	 * @param supplyId
	 * @param avgScore
	 */
	public void refreshAvgScore(int supplyId,int avgScore){
		ISupply supply = supplyMapKeyId.get(supplyId);
		supply.setAvgScore((long)avgScore);
		supplyService.updateSupply(supply);
	}
	
	/**
	 * 增加一个供货商
	 * @param supply
	 */
	public void addSupply(Supply supply){
		supply=supplyService.addSupply(supply);
		ISupply iSupply = new EmptySupply();
		iSupply.assign(supply);
		supplyMapKeyId.put(iSupply.getId(), iSupply);
	}
	
	/**
	 * 修改一个供货商
	 * @param supply
	 */
	public void updateSupply(Supply supply){
		supplyService.updateSupply(supply);
	}
	
	/**
	 * 划款
	 * @param supply
	 * @param recharge
	 * @param useraccountdealdetail
	 */
	public void depositBalance(Supply supply,double recharge ,UserCharge userCharge){
		supplyService.depositBalance(supply, recharge, userCharge);
	}
	
	/**
	 * 提款
	 * @param supply
	 * @param recharge
	 * @param useraccountdealdetail
	 */
	public void drawBalance(Supply supply,double recharge ,UserCharge userCharge){
		supplyService.drawBalance(supply, recharge, userCharge);
	}
	
	/**
	 * 消费
	 * @param mainorder
	 */
	public void purchaseBalance(Mainorder mainorder){
		supplyService.purchaseBalance(mainorder);
	}
	
	/**
	 * 冲正
	 * @param mainorder
	 */
	public void reverseBalance(Mainorder mainorder){
		supplyService.reverseBalance(mainorder);
	}
	
	/**
	 * 取消
	 * @param supply
	 * @param recharge
	 * @param useraccountdealdetail
	 */
	public void cancelBalance(Mainorder mainorder,Supply supply,double recharge ,UserCharge userCharge){
		supplyService.cancelBalance(mainorder,supply, recharge, userCharge);
	}
	
	/**
	 * 刷新账户余额
	 * @param mainorder
	 * @param supply
	 * @param recodeUsers
	 * @param recodeOperator
	 * @param userCharge
	 * @param deposit
	 * @param flag
	 */
	public void refreshAccounts(Mainorder mainorder, Supply supply, Users recodeUsers,
			String recodeOperator,UserCharge userCharge,
			Double deposit, int flag) {
		supplyService.refreshSupplyAccounts(mainorder, supply, recodeUsers, recodeOperator, userCharge, deposit, flag);
	}
	
	/**
	 * 是否已存在interface
	 * @param supply
	 * @param goods
	 * @return
	 */
	public boolean checkExistInterface(Supply supply,Goods goods){
		Iterator iterator=supplyInterfaceMapKeyId.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, SupplyInterface> entry = (Map.Entry<Integer, SupplyInterface>) iterator.next();
			if (entry.getValue().getGoods().getGoodsId().equals(goods.getGoodsId())&&entry.getValue().getSupply().getId().equals(supply.getId())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 增加interface
	 * @param supplyInterface
	 * @param supply
	 * @param goods
	 */
	public boolean addSupplyInterface(SupplyInterface supplyInterface,Supply supply,Goods goods){
		//if (checkExistInterface(supply, goods)) {
			supplyInterface.setGoodsName(goods.getGoodsName());
			supplyInterface.setValue(goods.getValue());
			supplyInterface.setKaBaseNum(goods.getKaBaseNum());
			supplyInterface.setKaCardId(goods.getKaCardId());
			supplyInterface.setState(1);
			supplyInterface.setFailedCount(0);
			supplyInterface.setKaCardId(goods.getKaCardId());
			supplyInterface.setValue(goods.getValue());
			supplyInterfaceService.save(supplyInterface);
			supplyInterfaceMapKeyId.put(supplyInterface.getId(), supplyInterface);
			//更新可用接口
		    refreshSupplyInterfaceMapKey();
			return true;
		//}
		//return false;
	}
	
	/**
	 * 更新interface
	 * @param id
	 * @param supplyInterface
	 */
	public boolean updateSupplyInterface(int id,SupplyInterface supplyInterface){
		Goods goods=goodsService.findById(supplyInterface.getGoods().getGoodsId());
		supplyInterface.setKaBaseNum(goods.getKaBaseNum());
		supplyInterface.setKaCardId(goods.getKaCardId());
		supplyInterface.setValue(goods.getValue());
		supplyInterfaceService.update(supplyInterface);
		SupplyInterface si = supplyInterfaceMapKeyId.get(id);
		si.setDefaultAlarm(supplyInterface.getDefaultAlarm());
		si.setRetailPrice(supplyInterface.getRetailPrice());
		si.setStockPrice(supplyInterface.getStockPrice());
		si.setValue(goods.getValue());
		si.setCanReverse(supplyInterface.getCanReverse());
		si.setClientType(supplyInterface.getClientType());
		si.setKaBaseNum(supplyInterface.getKaBaseNum());
		si.setKaCardId(supplyInterface.getKaCardId());
		return true;
	}
	
	/**
	 * 更新interface状态
	 * @param supplyInterface
	 * @param state
	 * @return
	 */
	public boolean updateSupplyInterfaceState(int id,int state){
    	SupplyInterface si = supplyInterfaceMapKeyId.get(id);
		si.setState(state);
		supplyInterfaceService.update(si);
		if (state == 1) {
			supplyInterfaceService.refreshFailedCount(si, 0);
		}	
	    //更新可用接口
	    refreshSupplyInterfaceMapKey();
		return true;
	}
	
	/**
	 * 更新interface状态
	 * @param supplyInterface
	 * @param state
	 * @return
	 */
	public boolean updateSupplyInterfaceState(String ids,int state){
	
	    for (String strId : ids.split("\\_")) {
	    	SupplyInterface si = supplyInterfaceMapKeyId.get(Integer.parseInt(strId));
			si.setState(state);
			supplyInterfaceService.update(si);
			if (state == 1) {
				supplyInterfaceService.refreshFailedCount(si, 0);
			}
		}	
	    //更新可用接口
	    refreshSupplyInterfaceMapKey();
		return true;
	}
	
	/**
	 * 更新interface失败次数
	 * @param uid
	 * @param count
	 * @return
	 */
	public boolean updateSupplyInterfaceFailedCount(SupplyInterface si,int count){
		supplyInterfaceService.refreshFailedCount(si, count);
		return true;
	}
	
	private List<ISupply> findSuppliesByIds(String ids){
		List<ISupply> list = new ArrayList<ISupply>();
		String[] id = ids.split(",");
		List<Integer> idInt = new ArrayList<Integer>();
		for (String str : id) {
			idInt.add(Integer.parseInt(str));
		}
		for (Integer integer : idInt) {
			ISupply supply = supplyMapKeyId.get(integer);
			if (supply.getIsUsed()==1) {
				list.add(supply);
			}
		}
		return list;
	}
	
	/**
	 * 更新可用接口
	 */
	private void refreshSupplyInterfaceMapKey() {
		Iterator iterator=supplyInterfaceMapKeyId.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, SupplyInterface> entry = (Map.Entry<Integer, SupplyInterface>) iterator.next();
			SupplyInterface supplyInterface = entry.getValue();
			if (supplyInterface.getState() == 1) {
				supplyInterfaceMapKeyEnableId.put(supplyInterface.getId(), supplyInterface);
			}
		}
	}

	/**
	 * 获取可用接口
	 * @return
	 */
	public Map<Integer, SupplyInterface> getSupplyInterfaceMapKeyEnableId() {
		return supplyInterfaceMapKeyEnableId;
	}
}
