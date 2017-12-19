package com.sys.volunteer.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sys.volunteer.vo.UsersBean;

public class ItemSessionUsers
{
	@SuppressWarnings("unchecked")
	public void addUser(Map session, UsersBean users)
	{
		ArrayList list = (ArrayList)session.get("userslist");
		if (SysUtil.isNull(list))
		{
			List userlist = new ArrayList();
			userlist.add(users);
			session.put("userslist",userlist);
		} 
		else
		{
			UsersBean userbean = isExist(list, users.getId());
			if(SysUtil.isNull(userbean))
			{
				list.add(users);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public UsersBean isExist(List list,int id)
	{
		UsersBean cart=null;
		for (int k = 0; k < list.size(); k++)
		{
			UsersBean carts = (UsersBean) list.get(k);
			if (id == carts.getId())
			{
				cart = carts;
				break;
			}
		}
		return cart;
	}
	
	@SuppressWarnings("unchecked")
	public void delUser(Map session, int id)
	{
		ArrayList list = (ArrayList) session.get("userslist");
		for (int i = 0; i < list.size(); i++)
		{
			UsersBean usersbean = (UsersBean) list.get(i);
			if (usersbean.getId() == id)
			{
				list.remove(i);
				break;
			}
		}
	}
}
