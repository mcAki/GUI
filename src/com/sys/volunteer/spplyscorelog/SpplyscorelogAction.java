package com.sys.volunteer.spplyscorelog;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.pojo.Spplyscorelog;
import com.sys.volunteer.pojo.Supply;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.supply.SupplyKernel;
import com.sys.volunteer.supply.SupplyService;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class SpplyscorelogAction extends BaseAction {

	@Resource
	SpplyscorelogService spplyscorelogService;
	@Resource
	SupplyService supplyService;
	
	private Spplyscorelog spplyscorelog;
	
	private Integer supplyId;
	
	private Integer score;
	
	public String addScore() throws Exception{
		Users currentUser=getSessionUser();
		if (currentUser.getUsergroup().getGroupType()>Const.GroupType.STAFF) {
			return ShowMessage(MSG_TYPE_NORMAL, "你不是管理员,没有权限评分", "", "", 0);
		}
		spplyscorelog=new Spplyscorelog();
		spplyscorelog.setUsers(currentUser);
		return "do";
	}
	
	public String doAdd() throws Exception{
		Users currentUser=getSessionUser();
		//Supply supply=supplyService.findSupplyById(supplyId);
		spplyscorelog.setScore(score);
		spplyscorelogService.addLog(spplyscorelog, currentUser);
		//supplyService.updateAvgScore(supply, score);
		SupplyKernel supplyKernel = SupplyKernel.getInstance();
		supplyKernel.refreshAvgScore(supplyId, score);
		return ShowMessage(MSG_TYPE_NORMAL, "评分成功", "", "", 0);
	}

	public Spplyscorelog getSpplyscorelog() {
		return spplyscorelog;
	}

	public void setSpplyscorelog(Spplyscorelog spplyscorelog) {
		this.spplyscorelog = spplyscorelog;
	}

	public Integer getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(Integer supplyId) {
		this.supplyId = supplyId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
