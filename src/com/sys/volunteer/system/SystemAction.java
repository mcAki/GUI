package com.sys.volunteer.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ages.model.Administrators;
import com.ages.model.ConnectInfo;
import com.ages.model.ServerInfoBean;
import com.ages.util.DbManager;
import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.common.Const;

@Controller
@Scope("prototype")
public class SystemAction extends BaseAction {
	
	private Administrators user;
	private List<ConnectInfo> servers;
	private Integer serverId;
	private String result;
	
	/**
	 * 读取UserBean
	 * @throws Exception 
	 */
	public boolean getUserBean(){
		if(getSession().containsKey(Const.USER_SESSION_KEY)){
			user = (Administrators)getSession().get( Const.USER_SESSION_KEY );
			return true;
		}else {
			return false;
		}
	}
	
	public String top() throws Exception {
		servers = DbManager.getInstance().getConnectInfos();
//		ConnectInfo connectInfo = new ConnectInfo();
//		connectInfo.setId(2);
//		connectInfo.setServername("S2");
//		servers.add(connectInfo);
		return "top";
	}

	public String left() throws Exception {
		//getUserBean();
		return "left";
	}
	
	public String defPage() throws Exception {
		
		return "defPage";
	}
	
	public String main() throws Exception {
		if(getUserBean()){
			return "main";
		}else {
			
			return ShowMessage(MSG_TYPE_WARNING,"超时操作,请重新登录系统", "点击登录", "adminLogin.do",0);
		}
	}
	
	/**
	 * 改session的服务器ID
	 * @throws Exception
	 */
	public String changeServer() throws Exception {
		if (serverId != null) {
			ConnectInfo connectInfo = DbManager.getInstance().getConnectInfos().get(serverId-1);
			getSession().put(Const.SERVERID_SESSION_KEY, connectInfo);
			result = "success";
		}else {
			result = "failed";
		}
		return "json";
	}
	
	/**
	 * 选择服务器提示
	 * @return
	 * @throws Exception
	 */
	public String noserver() throws Exception {
		return ShowMessage(MSG_TYPE_STOP, "请选择服务器!", "", "", 0);
	}

	/**
	 * @return the user
	 */
	public Administrators getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Administrators user) {
		this.user = user;
	}

	public List<ConnectInfo> getServers() {
		return servers;
	}

	public void setServers(List<ConnectInfo> servers) {
		this.servers = servers;
	}

	public Integer getServerId() {
		return serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
