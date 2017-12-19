package com.sys.volunteer.games;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.pojo.Games;
import com.sys.volunteer.pojo.Useraccount;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.useraccount.UseraccountService;
import com.sys.volunteer.users.UserService;


@Controller
@Scope("prototype")
public class GamesAction extends BaseAction implements Preparable{
	
//	@Resource
//	GamesService gamesService;
	@Resource
	UserService userService;
	@Resource
	UseraccountService useraccountService;
	
	private String userId;
	
	private String gamesId;
	
	private String gamesName;
	
	private String gamesType;
	
	private String confirmaccount;
	
	private Games games;
	
	private Users users;
	
	private Useraccount useraccount;
	
	@Override
	public void prepare() throws Exception {

	}
	
	public String doList() throws Exception {
		
		return "gamesList";
	}

	public String doAdd() throws Exception {
		
		System.out.println("GamesAction.doAdd()");
		
//		users = userService.findUserById(userId);
//		useraccount = useraccountService.findUseraccountByUseraccountId(users);
		
		games.setGamesId(gamesId);
		System.out.println("-----------------------------------"+gamesId);
		
		
//		String gameName = new String(gamesName.getBytes("ISO8859-1"), "UTF-8");
//		games.setGamesName(gameName);
//		System.out.println("-----------------------------------"+gameName);
    
		games.setGamesName(gamesName);
		System.out.println("-----------------------------------"+gamesName);

		games.setGamesType(games.getGamesType());
		System.out.println("-----------------------------------"+gamesType);
		
		games.setCount(games.getCount());
		System.out.println("-----------------------------------"+games.getCount());
		games.setGamesNum(games.getGamesNum());
		System.out.println("-----------------------------------"+games.getGamesNum());
		games.setPrice(games.getPrice());
		System.out.println("-----------------------------------"+games.getPrice());
		games.setTradeTimeDate(new Date());
		games.setState(1);
//        games.setUseraccount(useraccount);
        
//        gamesService.save(games);
		return "test";
	}
	
	public String show() throws Exception {
		
//		List list = gamesService.find();
		
		return "success";
	}
	
	//==============================================================

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGamesId() {
		return gamesId;
	}

	public void setGamesId(String gamesId) {
		this.gamesId = gamesId;
	}

	public Games getGames() {
		return games;
	}

	public void setGames(Games games) {
		this.games = games;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Useraccount getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(Useraccount useraccount) {
		this.useraccount = useraccount;
	}

	public String getConfirmaccount() {
		return confirmaccount;
	}

	public void setConfirmaccount(String confirmaccount) {
		this.confirmaccount = confirmaccount;
	}

	public String getGamesName() {
		return gamesName;
	}

	public void setGamesName(String gamesName) {
		this.gamesName = gamesName;
	}

	public String getGamesType() {
		return gamesType;
	}

	public void setGamesType(String gamesType) {
		this.gamesType = gamesType;
	}

}
