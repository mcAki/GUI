package com.sys.volunteer.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.otherMes.OtherMesService;
import com.sys.volunteer.pojo.OtherMes;
import com.sys.volunteer.pojo.Useraccount;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.useraccount.UseraccountService;

@Controller
@Scope("prototype")
public class SystemAction extends BaseAction {
	
	@Resource
	UseraccountService useraccountService;
	@Resource
	private OtherMesService otherMesService;
	
	private Users user;
	
	private Useraccount useraccount; 
	/**
	 * 读取UserBean
	 * @throws Exception 
	 */
	public boolean getUserBean(){
		if(getSession().containsKey(Const.USER_SESSION_KEY)){
			user = (Users)getSession().get( Const.USER_SESSION_KEY );
			return true;
		}else {
			return false;
		}
	}
	
	public String top() throws Exception {
		if(getUserBean()){
			String str = user.getUsergroup().getGroupName();
		}
		return "top";
	}

	public String left() throws Exception {
		//getUserBean();
		return "left";
	}
	public String left_front() throws Exception {
		//getUserBean();
		return "left_front";
	}
	public String header() throws Exception {
		//getUserBean();
		return "header";
	}
	public String center() throws Exception {
		//getUserBean();
		return "center";
	}
	public String center_right() throws Exception {
		//getUserBean();
		return "center_right";
	}
	public String footer() throws Exception {
		//getUserBean();
		return "footer";
	}
	
	public String defPage() throws Exception {
		useraccount = useraccountService.findUseraccountByUseraccountId(getSessionUser());
		return "defPage";
	}
	
	public String main() throws Exception {
		if(getUserBean()){
			return "main";
		}else {
			
			return ShowMessage(MSG_TYPE_WARNING,"超时操作,请重新登录系统", "点击登录", "login.do",0);
		}
	}
	public String main_front() throws Exception {
		if(getUserBean()){
			
			List<OtherMes>  otherMesTopNewList = otherMesService.findNewTop();
			List<OtherMes>  otherMesCommonNewList = otherMesService.findCommonTop();
			if(otherMesTopNewList==null||otherMesTopNewList.size()<=0) otherMesTopNewList = otherMesService.findByType(1);
			if(otherMesTopNewList!=null&&otherMesTopNewList.size()>0){
				
				this.getSession().put("otherMesTopNewFirst", otherMesTopNewList.get(0));
				this.getSession().put("otherMesTopNewList", otherMesTopNewList);
			}
			if(otherMesCommonNewList!=null&&otherMesCommonNewList.size()>0){
				this.getSession().put("otherMesCommonNewList", otherMesCommonNewList);
			}
			
			return "main_front";
		}else {
			
			return ShowMessage(MSG_TYPE_WARNING,"超时操作,请重新登录系统", "点击登录", "login.do",0);
		}
	}

	/**
	 * @return the user
	 */
	public Users getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Users user) {
		this.user = user;
	}

	public Useraccount getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(Useraccount useraccount) {
		this.useraccount = useraccount;
	}

}
