package com.sys.volunteer.authority;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ages.model.Administratorsgroup;
import com.ages.model.Menutree;
import com.ages.util.DbManager;
import com.sys.volunteer.common.MenuComparator;
import com.sys.volunteer.dao.CommonDao;
@Service
public class AuthorityServiceBean extends CommonDao implements AuthorityService{

	/**
	 * 根据用户的用户组获得他的菜单列表
	 * @param group
	 * @return
	 */
	public List<Menutree> getMenuTreeBy(Administratorsgroup group) {
		
		List<Menutree> menuTree=new ArrayList<Menutree>();

		List<Menutree> menuSet;
		try {
			menuSet = this.findListBySql(DbManager.getGmRunner(), Menutree.class, "select * from menutree where id " +
						" in (select menutreeid from menu_group where groupid ="+group.getId() + ")");
			for(Menutree menu:menuSet){
				if(menu.getParentId()==null){		//首先放入顶级菜单
					menuTree.add(menu);
				}
				else{
				}
			}
			
			for(Menutree menu:menuSet){
				//二级菜单放进一级菜单的subtrees属性
				for(Menutree tmpMenu:menuTree){
					if(menu.getParentId()!=null && tmpMenu.getId().intValue()==menu.getParentId().intValue()){
						tmpMenu.getSubtrees().add(menu);
					}
				}
			}

			
			Collections.sort(menuTree, new MenuComparator());//一级菜单排序
			for(Menutree menu:menuTree){
				if(menu.getSubtrees()!=null){
					Collections.sort(menu.getSubtrees(), new MenuComparator());//二级菜单排序
				}
				
			}
			return menuTree;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}


		

	}

}
