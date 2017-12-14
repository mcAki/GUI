package com.sys.volunteer.vo;

import java.util.Date;

/**
 * 
 * @ClassName: ItemLog
 * @Description: 道具日志
 * @author lyh
 * @date 2012-10-12 下午12:09:56
 */
public class ItemLogVo {
	private int id;
	private String userid;// 玩家id
	private String rolename;// 玩家昵称
	private String itemid;// 操作物品id
	private String itemname;// 物品名称
	private int itemTemplateId;// 模板id
	private long time;// 操作时间
	private String handler;// 操作逻辑
	private String remarks;// 备注
	private Date timeShow;
	private String remarksShow;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getItemTemplateId() {
		return itemTemplateId;
	}

	public void setItemTemplateId(int itemTemplateId) {
		this.itemTemplateId = itemTemplateId;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public Date getTimeShow() {
		return timeShow;
	}

	public void setTimeShow(Date timeShow) {
		this.timeShow = timeShow;
	}

	public String getRemarksShow() {
		return remarksShow;
	}

	public void setRemarksShow(String remarksShow) {
		this.remarksShow = remarksShow;
	}
	
	public void setRemarksShow() {
		String service = remarks.substring(0, remarks.indexOf(":"));
		String count = remarks.substring(remarks.indexOf(":")+1);
		String questId = "";
		if (service.contains("|")) {
			service = service.substring(0, service.indexOf("|"));
			questId = service.substring(service.indexOf("|")+1);
		}
		if (service.equals("vagrantShop")) {
			service = "流浪的商店";
		}
		else if (service.equals("onlineGift")) {
			service = "在线奖励";
		}
		else if (service.equals("signIn")) {
			service = "注册礼物";
		}
		else if (service.equals("smashEgg")) {
			service = "砸蛋";
		}
		else if (service.equals("mailItem")) {
			service = "邮件物品";
		}
		else if (service.equals("synthenicEquip")) {
			service = "合成装备";
		}
		else if (service.equals("reapVegetable")) {
			service = "收菜";
		}
		else if (service.equals("drawCard")) {
			service = "抽卡";
		}
		else if (service.equals("livenessGift")) {
			service = "活跃度礼包";
		}
		else if (service.equals("addAncientSoul")) {
			service = "添加远古之魂";
		}
		else if (service.equals("addWeapon")) {
			service = "添加武器";
		}
		else if (service.equals("buyItemMall")) {
			service = "购买商城道具";
		}
		else if (service.equals("synthenicStone")) {
			service = "合成镶嵌宝石";
		}
		else if (service.equals("syntheticItem")) {
			service = "合成道具";
		}
		else if (service.equals("onlineGift")) {
			service = "vip抽奖";
		}
		else if (service.equals("FuncItemAction")) {
			service = "随机奖励";
		}
		else if (service.equals("PrizePack")) {
			service = "礼包奖励";
		}
		else if (service.equals("questComplete")) {
			service = "任务奖励";
		}
		else if (service.equals("vipGift")) {
			service = "vip每日礼包";
		}
		else if (service.equals("resolution")) {
			service = "拆分道具";
		}
		else if (service.equals("closeNpcPassResult")) {
			service = "副本结算";
		}
		else if (service.equals("discardItem")) {
			service = "丢弃物品";
		}
		else if (service.equals("forgeEquip")) {
			service = "锻造装备";
		}
		else if (service.equals("fuseEquip")) {
			service = "融合装备";
		}
		else if (service.equals("userItem")) {
			service = "使用道具";
		}
		else if (service.equals("sellItem")) {
			service = "卖出道具";
		}
		else if (service.equals("addAttr")) {
			service = "为装备添加额外的属性";
		}
		else if (service.equals("exchangeAttr")) {
			service = "改变装备的附加属性";
		}
		else if (service.equals("upgradeAttr")) {
			service = "升级附加属性的等级";
		}
		else if (service.equals("intensifyEquip")) {
			service = "强化装备";
		}
		else if (service.equals("plantCrop")) {
			service = "种植农作物";
		}
		else if (service.equals("bonusQuest")) {
			service = "回收任务道具";
		}
		else if (service.equals("useSmallHorn")) {
			service = "使用小海螺";
		}
		else if (service.equals("inlayEquip")) {
			service = "镶嵌宝石";
		}
		else if (service.equals("upGemLv")) {
			service = "升级镶嵌宝石";
		}
		else if (service.equals("GMTool")) {
			service = "GM工具";
		}
		this.remarksShow = "用途"+service;
		if (!questId.equals("")) {
			this.remarksShow += ",任务id"+questId;
		}
		this.remarksShow += ",数量"+count;
		
	}
}
