package com.sys.volunteer.gmmanage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ages.model.ConnectInfo;
import com.ages.util.DbManager;
import com.ages.util.HttpClientUtil;
import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.common.CryptUtil3DES;
import com.sys.volunteer.common.RemotePageUtil;
import com.sys.volunteer.vo.AddItemsVo;
import com.sys.volunteer.vo.BroadcastVo;
import com.sys.volunteer.vo.GMMail;
import com.sys.volunteer.vo.UserInfo;

@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class GMManageAction extends BaseAction {

	@Resource
	GMManageService gmManageService;

	private List<ConnectInfo> servers;
	private Integer state;
	private String userId;
	private Integer serverId;
	private UserInfo userInfo;
	private BroadcastVo broadcastVo;
	private String uuid;
	private AddItemsVo addItemsVo;
	private Integer startLevel;
	private Integer endLevel;
	private Integer lastDays;
	private Integer typeid;
	private String selectedCompare;
	private Integer number;
	private String content;
	private String returnUrl;
	private String serverid;
	private String openid;
	private String sig;
	private GMMail gmMail;
	private String mailId;

	/**
	 * GM功能封号页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateUserState() throws Exception {
		Integer serverId = getSessionServer().getId();
		userInfo = gmManageService.findUserInfoById(serverId, userId);
		return "updateUserState";
	}

	/**
	 * GM功能封号提交
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doUpdateUserState() throws Exception {
		Integer serverId = getSessionServer().getId();
		String url = DbManager.getRequestUrl(serverId)
				+ "ajax/playerstate.php?state=" + userInfo.getState()
				+ "&userId=" + userId;
		String result = RemotePageUtil.getRotmePage(url, "utf-8");
		if (result.equals("success")) {
			return ShowMessage(MSG_TYPE_NORMAL, "修改状态成功", "", "", 0);
		} else {
			return ShowMessage(MSG_TYPE_NORMAL, "修改状态失败", "", "", 0);
		}
	}

	/**
	 * GM广播功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String broadcast() throws Exception {
		broadcastVo = new BroadcastVo();
		return "broadcast";
	}

	/**
	 * GM广播功能提交
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doBroadcast() throws Exception {
		Map<String, BroadcastVo> map = GMKernel.getInstance()
				.getServerBroadcast().get(1);
		String uuid = UUID.randomUUID().toString();
		broadcastVo.setUuid(uuid);
		GMKernel.getInstance().getServerBroadcast().put(1, map);
		Integer serverId = getSessionServer().getId();
		String url = DbManager.getRequestUrl(serverId)// 1--servierId
				+ "ajax/broadcast.php";
		Map<String, String> param = new HashMap<String, String>();
		param.put("uuid", broadcastVo.getUuid());
		param.put("times", broadcastVo.getTimes().toString());
		param.put("interval", broadcastVo.getInterval().toString());
		param.put("content", broadcastVo.getContent());
		param.put("type", broadcastVo.getType().toString());

		// post
		String result = HttpClientUtil.sendPostRequest(url, param);// RemotePageUtil.getRotmePage(url,
																	// "utf-8");
		broadcastVo.setResult(Integer.parseInt(result));
		map.put(uuid, broadcastVo);
		if (result.equals("1")) {
			return ShowMessage(MSG_TYPE_NORMAL, "发送成功", "", "", 0);
		}
		return ShowMessage(MSG_TYPE_NORMAL, "发送失败", "", "", 0);
	}

	/**
	 * GM广播功能移除消息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String removeBroadcast() throws Exception {
		Map<String, BroadcastVo> map = GMKernel.getInstance()
				.getServerBroadcast().get(1);
		BroadcastVo broadcastVo = map.get(uuid);
		if (broadcastVo != null) {
			GMKernel.getInstance().removeBroadcastMap(serverId, uuid);
			return ShowMessage(MSG_TYPE_NORMAL, "移除成功", "点击返回",
					"list!listBroadcastVo.do", 0);
		}
		return ShowMessage(MSG_TYPE_NORMAL, "找不到这条记录", "点击返回",
				"list!listBroadcastVo.do", 0);
	}

	/**
	 * GM增加玩家道具功能页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addItems() throws Exception {
		addItemsVo = new AddItemsVo();
		return "addItems";
	}

	/**
	 * GM增加玩家道具功能提交
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doAddItems() throws Exception {
		Integer serverId = getSessionServer().getId();
		Map<String, String[]> paraMap = getHttpServletRequest()
				.getParameterMap();
		Iterator iterator = paraMap.entrySet().iterator();
		Integer number = 0;
		String typeid = "";
		String itemIds = "";
		while (iterator.hasNext()) {
			Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) iterator
					.next();
			String key = entry.getKey();
			String keySub = key.substring(0, 6);
			String keyLast = key.substring(6);
			if (keySub.equals("typeid")) {
				typeid = entry.getValue()[0];
				if (typeid == null || typeid.equals("")) {
					return ShowMessage(MSG_TYPE_STOP, "请填写道具id", "", "", 0);
				}
				itemIds += typeid + ":";
				number = Integer.valueOf(paraMap.get("number" + keyLast)[0]);
				if (number == null || number.equals(0)) {
					return ShowMessage(MSG_TYPE_STOP, "请填写道具数量", "", "", 0);
				}
				itemIds += number + ",";
			}
		}
		itemIds = itemIds.substring(0, itemIds.lastIndexOf(","));
		System.out.println("itemIds:" + itemIds);
		String rolenames = "\'"
				+ addItemsVo.getSearchAccount().replaceAll(",", "\',\'") + "\'";
		String ids = "";
		List<String> list = gmManageService.findUserInfoByRolenames(serverId,
				rolenames);
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1) {
				ids += list.get(i);
			} else {
				ids += list.get(i) + ",";
			}
		}
		String url = DbManager.getRequestUrl(serverId)// 1--servierId
				+ "ajax/additems.php";
		Map<String, String> param = new HashMap<String, String>();
		param.put("searchAccount", ids);
		param.put("addItemId", itemIds);
		param.put("content", content);
		String result = HttpClientUtil.sendPostRequest(url, param);
		if (result.equals("noplayer")) {
			// 没有找到玩家
			return ShowMessage(MSG_TYPE_STOP, "没有该玩家", "", "", 0);
		} else if (result.equals("failed")) {
			// 增加失败
			return ShowMessage(MSG_TYPE_STOP, "增加失败", "", "", 0);
		} else if (result.equals("success")) {
			// 增加成功
			return ShowMessage(MSG_TYPE_STOP, "增加成功", "", "", 0);
		} else {
			// 未知返回结果
			return ShowMessage(MSG_TYPE_STOP, "连接服务器失败", "", "", 0);
		}
	}

	/**
	 * GM条件增加玩家道具功能页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addItemsByCondition() throws Exception {
		return "addItemsByCondition";
	}

	/**
	 * GM条件增加玩家道具功能提交
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doAddItemsByCondition() throws Exception {
		String condition = "";
		if (selectedCompare.equals("0")) {
			// 不限等级
		} else if (selectedCompare.equals(">")) {
			// 大于
			condition = " `level` >" + endLevel;
		} else if (selectedCompare.equals(">=")) {
			// 大于等于
			condition = " `level` >=" + endLevel;
		} else if (selectedCompare.equals("=")) {
			// 等于
			condition = " `level` =" + endLevel;
		} else if (selectedCompare.equals("<<")) {
			// 小于
			condition = " `level` <" + endLevel;
		} else if (selectedCompare.equals("<=")) {
			// 小于等于
			condition = " `level` <=" + endLevel;
		} else if (selectedCompare.equals("between")) {
			// 之间
			condition = " `level` BETWEEN " + startLevel + " AND " + endLevel;
		}
		String ids = "";
		Integer serverId = getSessionServer().getId();
		List<String> list = gmManageService
				.findUserInfoByLevelAndLastloginTime(serverId, condition, lastDays);
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1) {
				ids += list.get(i);
			} else {
				ids += list.get(i) + ",";
			}
		}
		String url = DbManager.getRequestUrl(serverId)// 1--servierId
				+ "ajax/additems.php";
		Map<String, String> param = new HashMap<String, String>();
		param.put("searchAccount", ids);
		param.put("addItemId", typeid+":"+number);
		param.put("content", content);
		String result = HttpClientUtil.sendPostRequest(url, param);
		if (result.equals("noplayer")) {
			// 没有找到玩家
			return ShowMessage(MSG_TYPE_STOP, "没有该玩家", "", "", 0);
		} else if (result.equals("failed")) {
			// 增加失败
			return ShowMessage(MSG_TYPE_STOP, "增加失败", "", "", 0);
		} else if (result.equals("success")) {
			// 增加成功
			return ShowMessage(MSG_TYPE_STOP, "增加成功", "", "", 0);
		} else {
			// 未知返回结果
			return ShowMessage(MSG_TYPE_STOP, "未知返回结果", "", "", 0);
		}
	}
	
	/**
	 * GM向玩家充值(页面)
	 * @return
	 * @throws Exception
	 */
	public String recharge() throws Exception {
		return "recharge";
	}
	
	/**
	 * GM向玩家充值(提交)
	 * @return
	 * @throws Exception
	 */
	public String doRecharge() throws Exception {
		String rolename = userInfo.getRoleName();
		Integer serverId = getSessionServer().getId();
		userInfo = gmManageService.findUserInfoByRolename(serverId, rolename);
		String url = DbManager.getRequestUrl(serverId)// 1--servierId
		+ "ajax/recharge.php";
		Map<String, String> param = new HashMap<String, String>();
		param.put("searchAccount", userInfo.getId());
		param.put("amount", number+"");
		String result = HttpClientUtil.sendPostRequest(url, param);
		if (result.equals("noplayer")) {
			// 没有找到玩家
			return ShowMessage(MSG_TYPE_STOP, "没有该玩家", "", "", 0);
		} else if (result.equals("failed")) {
			// 增加失败
			return ShowMessage(MSG_TYPE_STOP, "增加失败", "", "", 0);
		} else if (result.equals("success")) {
			// 增加成功
			return ShowMessage(MSG_TYPE_STOP, "增加成功", "", "", 0);
		} else {
			// 未知返回结果
			return ShowMessage(MSG_TYPE_STOP, "未知返回结果", "", "", 0);
		}
	}

	/**
	 * GM全部增加玩家道具功能页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addItemsByAll() throws Exception {
		return "addItemsByAll";
	}

	/**
	 * GM全部增加玩家道具功能提交
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doAddItemsByAll() throws Exception {
		final String result ="success";
		final Integer serverId = getSessionServer().getId();
		GMKernel.getInstance().getExecutorService().execute(new Runnable() {
			
			@Override
			public void run() {
				List<String> list;
				try {
					list = gmManageService
							.findUserInfoAll(serverId);
					List<String> tempList = new ArrayList<String>();
					for (int i = 0; i < list.size(); i++) {
						if (i%5==0) {
							if (tempList.size()!=0) {
								String ids = "";
								for (int j = 0; j < tempList.size(); j++) {
									if (j == tempList.size() - 1) {
										ids += tempList.get(j);
									} else {
										ids += tempList.get(j) + ",";
									}
								}
								String url = DbManager.getRequestUrl(serverId)// 1--servierId
										+ "ajax/additems.php";
								Map<String, String> param = new HashMap<String, String>();
								param.put("searchAccount", ids);
								param.put("addItemId", typeid+":"+number);
								param.put("content", content);
								HttpClientUtil.sendPostRequest(url, param);
								System.out.println("===发送5组道具成功!===");
							}
							tempList.removeAll(tempList);
							Thread.sleep(100);
						}
						tempList.add(list.get(i));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("===发送所有道具成功!===");
			}
		});
		if (result.equals("noplayer")) {
			// 没有找到玩家
			return ShowMessage(MSG_TYPE_STOP, "没有该玩家", "", "", 0);
		} else if (result.equals("failed")) {
			// 增加失败
			return ShowMessage(MSG_TYPE_STOP, "增加失败", "", "", 0);
		} else if (result.equals("success")) {
			// 增加成功
			return ShowMessage(MSG_TYPE_STOP, "增加成功", "", "", 0);
		} else {
			// 未知返回结果
			return ShowMessage(MSG_TYPE_STOP, "未知返回结果", "", "", 0);
		}
		
		
	}
	
	/**
	 * 关闭服务器(页面)
	 * @return
	 * @throws Exception
	 */
	public String shutdown() throws Exception {
		servers = DbManager.getInstance().getConnectInfos();
		return "shutdown";
	}
	
	/**
	 * 关闭服务器(提交)
	 * @return
	 * @throws Exception
	 */
	public String doShutdown() throws Exception {
		String url = DbManager.getRequestUrl(serverId)// 1--servierId
					+ "ajax/shutdown.php";
		Map<String, String> param = new HashMap<String, String>();
		try {
			HttpClientUtil.sendPostRequest(url, param);
		} catch (Exception e) {
			// 增加成功
			return ShowMessage(MSG_TYPE_STOP, "关闭成功", "", "", 0);
		}
		// 增加成功
		return ShowMessage(MSG_TYPE_STOP, "关闭成功", "", "", 0);
	}
	
	/**
	 * GM登陆玩家账号(页面)
	 * @return
	 * @throws Exception
	 */
	public String loginGame() throws Exception {
		return "loginGame";
	}
	
	/**
	 * GM登陆玩家账号(提交)
	 * @return
	 * @throws Exception
	 */
	public String doLoginGame() throws Exception {
		ConnectInfo info = getSessionServer();
		serverid = info.getTencentserverid()+"";
		sig = CryptUtil3DES.encrypt(openid+info.getTencentserverid());
		returnUrl = info.getWebUrl()+"?openid="+openid+"&serverid="+serverid+"&pw=3guu&openkey=&pf=&pfkey=&sig="+sig;
		//System.out.println(returnUrl+"?openid="+openid+"&serverid="+serverid+"&pw=3guu&sig="+sig);
		gmManageService.insertOpenidLog(openid, getSessionUser().getUserName(),info.getTencentserverid());
		return SUCCESS;
	}
	
	/**
	 * GM回复玩家问题(页面)
	 * @return
	 * @throws Exception
	 */
	public String replyMail() throws Exception {
		Integer serverId = getSessionServer().getId();
		gmMail = gmManageService.findGMMail(serverId, mailId);
		return "replyMail";
	}
	
	/**
	 * GM回复玩家问题(提交)
	 * @return
	 * @throws Exception
	 */
	public String doReplyMail() throws Exception {
		Integer serverId = getSessionServer().getId();
		String url = DbManager.getRequestUrl(serverId)// 1--servierId
				+ "ajax/replyplayer.php";
		Map<String, String> param = new HashMap<String, String>();
		param.put("mailId", mailId);
		param.put("content", content);
		HttpClientUtil.sendPostRequest(url, param);
		return ShowMessage(MSG_TYPE_NORMAL, "发送成功", "", "", 0);
	}
	
	/**
	 * 发送公告(页面)
	 * @return
	 * @throws Exception
	 */
	public String announce() throws Exception {
		return "announce";
	}
	
	/**
	 * 发送公告(提交)
	 * @return
	 * @throws Exception
	 */
	public String doAnnounce() throws Exception {
		Integer serverId = getSessionServer().getId();
		gmManageService.insertAnnouncemnet(serverId, content);
		return ShowMessage(MSG_TYPE_NORMAL, "发送成功", "", "", 0);
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getServerId() {
		return serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public BroadcastVo getBroadcastVo() {
		return broadcastVo;
	}

	public void setBroadcastVo(BroadcastVo broadcastVo) {
		this.broadcastVo = broadcastVo;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public AddItemsVo getAddItemsVo() {
		return addItemsVo;
	}

	public void setAddItemsVo(AddItemsVo addItemsVo) {
		this.addItemsVo = addItemsVo;
	}

	public Integer getStartLevel() {
		return startLevel;
	}

	public void setStartLevel(Integer startLevel) {
		this.startLevel = startLevel;
	}

	public Integer getEndLevel() {
		return endLevel;
	}

	public void setEndLevel(Integer endLevel) {
		this.endLevel = endLevel;
	}

	public Integer getLastDays() {
		return lastDays;
	}

	public void setLastDays(Integer lastDays) {
		this.lastDays = lastDays;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getSelectedCompare() {
		return selectedCompare;
	}

	public void setSelectedCompare(String selectedCompare) {
		this.selectedCompare = selectedCompare;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<ConnectInfo> getServers() {
		return servers;
	}

	public void setServers(List<ConnectInfo> servers) {
		this.servers = servers;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSig() {
		return sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}

	public String getServerid() {
		return serverid;
	}

	public void setServerid(String serverid) {
		this.serverid = serverid;
	}

	public GMMail getGmMail() {
		return gmMail;
	}

	public void setGmMail(GMMail gmMail) {
		this.gmMail = gmMail;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}


}
