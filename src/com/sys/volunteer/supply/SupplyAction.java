package com.sys.volunteer.supply;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.Privilege;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.SupplyInterface;
import com.sys.volunteer.pojo.UserCharge;
import com.sys.volunteer.pojo.Useraccountdealdetail;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.pojo.SupplyUtils.ModifySupply;
import com.sys.volunteer.supply.usage.ISupply;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.useraccountdealdetail.UseraccountDealDetailService;
import com.sys.volunteer.usercharge.UserChargeEngine;
import com.sys.volunteer.usercharge.UserChargeService;
import com.sys.volunteer.usercharge.engine.ICharge;
import com.sys.volunteer.usercharge.engine.charge.Charge4Supply;
import com.sys.volunteer.usercharge.engine.charge.Charge4User;
import com.sys.volunteer.users.UserService;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class SupplyAction extends BaseAction {

	@Resource
	UserService userService;
	@Resource
	UseraccountService useraccountService;
	@Resource
	UserChargeService userChargeService;
	
	private Supply supply;
	
	private Integer supplyId;
	
	private ModifySupply modifySupply;
	
	private String userId;
	
	private Integer balance;
	
	private Double recharge;
	
	private List<Integer> interfaceList;
	
	private UserCharge useraccountdealdetail;
	
	/**
	 * 增加供货商(页面)
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "supply", privilegeAccess = Const.PrivilegeAccessConst.ADD)
	public String add() throws Exception{
		supply=new Supply();
		return "do";
	}
	
	/**
	 * 增加供货商(提交)
	 * @return
	 * @throws Exception
	 */
	public String doAdd() throws Exception{
		//记录操作人员
//		supply.setBalance(new Long(balance));
		SupplyKernel supplyKernel=SupplyKernel.getInstance();
		supplyKernel.addSupply(supply);
		//supplyInterfaceService.addSupplyInterfaceByList(supply, interfaceList);
		return ShowMessage(MSG_TYPE_NORMAL, "增加成功", "点击返回", "list.do?", 0);
	}
	
	/**
	 * 修改Supply信息[页面]
	 * 
	 */
	@Privilege(permissionName = "supply", privilegeAccess = Const.PrivilegeAccessConst.MODIFY)
	public String viewSupply() {
		//supply = this.supplyService.findSupplyById(supplyId);
		/*supplyInterfaces = supplyInterfaceService.findSupplyInterfacesBySupplyId(supplyId);
		interfaceList=new ArrayList<Integer>();
		switch (supplyInterfaces.size()) {
		case 1:
			if (supplyInterfaces.get(0).getFlag()==1) {
				interfaceList.add(1);
			}else {
				interfaceList.add(2);
			}
			break;
		case 2:
			interfaceList.add(1);
			interfaceList.add(2);
			break;
		}*/
		supply = (Supply) SupplyKernel.getInstance().getSupplyMapKeyId().get(supplyId);
		modifySupply = new ModifySupply();
		copyProperties(modifySupply, supply);
		return "do";
	}

	/**
	 * 修改Supply信息[提交]
	 * 
	 * @return
	 */
	
	public String modifySupply() {
		//supply = this.supplyService.findSupplyById(supplyId);
		supply = (Supply) SupplyKernel.getInstance().getSupplyMapKeyId().get(supplyId);
		copyProperties(supply, modifySupply);
//		supply.setBalance(new Long(balance));
		SupplyKernel.getInstance().updateSupply(supply);
//		supplyInterfaceService.updateSupplyInterfacesBySupply(supply, interfaceList);
		return ShowMessage(MSG_TYPE_NORMAL, "修改成功", "返回列表",
			getHttpServletRequest().getContextPath() + "/supply/list.do", 0);
	}
	
	/**
	 * 划额页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "udForSupply", privilegeAccess = Const.PrivilegeAccessConst.ADD)
	public String recharge() throws Exception {
		//supply = supplyService.findSupplyById(supplyId);
		supply = (Supply) SupplyKernel.getInstance().getSupplyMapKeyId().get(supplyId);
		useraccountdealdetail = new UserCharge();
		return "recharge";
	}

	/**
	 * 划额提交
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doRecharge() throws Exception {
		//supply = supplyService.findSupplyById(supply.getId());
		supply = (Supply) SupplyKernel.getInstance().getSupplyMapKeyId().get(supply.getId());
		ICharge iCharge2 = new Charge4Supply(useraccountdealdetail, null, recharge, supply, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.SUPPLY_DEPOSIT);
//		SupplyKernel.getInstance().depositBalance(supply, recharge ,useraccountdealdetail);
		//自动增加管理员额度
		UserCharge ud=new UserCharge();
		ud.setVoucherType(useraccountdealdetail.getVoucherType());
		ud.setVoucherCode(useraccountdealdetail.getVoucherCode());
		ud.setRemark(useraccountdealdetail.getRemark());
		Users users = userService.getAdminUser();
		ICharge iCharge = new Charge4User(ud, null, recharge, users, null, users.getUserName(), Const.UseraccountdealdetailFlag.USER_DEPOSIT);
		UserChargeEngine.getInstance().addLast(iCharge2);
		UserChargeEngine.getInstance().addLast(iCharge);
//		useraccountService.refreshAccountes(null,users,null,users.getUserName(), ud,recharge,Const.UseraccountdealdetailFlag.USER_DEPOSIT);
		return ShowMessage(MSG_TYPE_NORMAL, "划款成功", "返回列表",
				"list.do", 0);
	}
	
	/**
	 * 提额提交
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doDrawing() throws Exception {
		//supply = supplyService.findSupplyById(supply.getId());
		supply = (Supply) SupplyKernel.getInstance().getSupplyMapKeyId().get(supply.getId());
		if (supply.getBalance()<recharge) {
			return ShowMessage(MSG_TYPE_STOP, "划额大于余额", "", "", 0);
		}
		ICharge iCharge2 = new Charge4Supply(useraccountdealdetail, null, recharge, supply, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.DRAWN);
//		SupplyKernel.getInstance().drawBalance(supply, recharge ,useraccountdealdetail);
		UserCharge ud=new UserCharge();
		//管理员自动提款
		Users users = userService.getAdminUser();
		ud.setVoucherType(this.useraccountdealdetail.getVoucherType());
		ud.setVoucherCode(this.useraccountdealdetail.getVoucherCode());
		ud.setRemark(this.useraccountdealdetail.getRemark());
		ICharge iCharge = new Charge4User(ud, null, recharge, users, null, getSessionUser().getUserName(), Const.UseraccountdealdetailFlag.DRAWN);
		UserChargeEngine.getInstance().addLast(iCharge2);
	    UserChargeEngine.getInstance().addLast(iCharge);
//		useraccountService.refreshAccountes(null,users,null,users.getUserName(),ud, recharge,Const.UseraccountdealdetailFlag.DRAWN);
		return ShowMessage(MSG_TYPE_NORMAL, "提款成功", "返回列表",
				"list.do", 0);
	}
	
	/**
	 * 禁用供货商
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "supply", privilegeAccess = Const.PrivilegeAccessConst.DEL)
	public String doDel() throws Exception{
		//Supply supply=supplyService.findSupplyById(supplyId);
		supply = (Supply) SupplyKernel.getInstance().getSupplyMapKeyId().get(supplyId);
		supply.setIsUsed(0);
		SupplyKernel.getInstance().updateSupply(supply);
		return ShowMessage(MSG_TYPE_NORMAL, "操作成功", "点击返回", "list.do", 0);
	}
	
	/**
	 * 恢复供货商
	 * @return
	 * @throws Exception
	 */
	@Privilege(permissionName = "supply", privilegeAccess = Const.PrivilegeAccessConst.DEL)
	public String doRecover() throws Exception{
		//Supply supply=supplyService.findSupplyById(supplyId);
		supply = (Supply) SupplyKernel.getInstance().getSupplyMapKeyId().get(supplyId);
		supply.setIsUsed(1);
		SupplyKernel.getInstance().updateSupply(supply);
		return ShowMessage(MSG_TYPE_NORMAL, "操作成功", "点击返回", "list.do", 0);
	}
	
	/**
	 * 更新供货商余额
	 * @return
	 */
	public String commitAccount(){
		supply = (Supply) SupplyKernel.getInstance().getSupplyMapKeyId().get(supplyId);
		double sum = userChargeService.sumBalance(supplyId);
		supply.setBalance(sum);
		SupplyKernel.getInstance().updateSupply(supply);
		return ShowMessage(MSG_TYPE_NORMAL, "操作成功", "点击返回", "list.do", 0);
	}
	
	/**
	 * 批量更新供货商余额
	 * @return
	 */
	public String batchCommitAccount(){
		Map<Integer, ISupply> map =  SupplyKernel.getInstance().getSupplyMapKeyId();
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, ISupply> entry = (Map.Entry<Integer, ISupply>) iterator.next();
			ISupply supply = entry.getValue();
			double sum = userChargeService.sumBalance(supply.getId());
			supply.setBalance(sum);
			SupplyKernel.getInstance().updateSupply((Supply)supply);
		}
		return ShowMessage(MSG_TYPE_NORMAL, "操作成功", "点击返回", "list.do", 0);
	}
	
	public ModifySupply getModifySupply() {
		return modifySupply;
	}

	public void setModifySupply(ModifySupply modifySupply) {
		this.modifySupply = modifySupply;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Supply getSupply() {
		return supply;
	}

	public void setSupply(Supply supply) {
		this.supply = supply;
	}

	public Integer getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(Integer supplyId) {
		this.supplyId = supplyId;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public List<Integer> getInterfaceList() {
		return interfaceList;
	}

	public void setInterfaceList(List<Integer> interfaceList) {
		this.interfaceList = interfaceList;
	}

	public UserCharge getUseraccountdealdetail() {
		return useraccountdealdetail;
	}

	public void setUseraccountdealdetail(UserCharge useraccountdealdetail) {
		this.useraccountdealdetail = useraccountdealdetail;
	}

	public Double getRecharge() {
		return recharge;
	}

	public void setRecharge(Double recharge) {
		this.recharge = recharge;
	}




}
