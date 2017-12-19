package com.sys.volunteer.authority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.common.MenuComparator;
import com.sys.volunteer.dao.CommonDao;
import com.sys.volunteer.pojo.Menutree;
import com.sys.volunteer.pojo.Usergroup;
@Service
@Transactional
public class AuthorityServiceBean extends CommonDao implements AuthorityService{

	/**
	 * 根据用户的用户组获得他的菜单列表
	 * @param group
	 * @return
	 */
	public List<Menutree> getMenuTreeBy(Usergroup group) {
		
		List<Menutree> menuTree=new ArrayList<Menutree>();
		Set<Menutree> menuSet= group.getMenutrees();

//		for(int i=0;i<menuSet.size();i++){
//			Menutree menu = (Menutree)menuSet.;
//			
//		}
		
		for(Menutree menu:menuSet){
			if(menu.getParentMenu()==null){		//首先放入顶级菜单
				menuTree.add(menu);
			}
			else{//二级菜单放进一级菜单的subtrees属性
				menu.getParentMenu().getSubtrees().add(menu);
			}
		}
		Collections.sort(menuTree, new MenuComparator());//一级菜单排序
		for(Menutree menu:menuTree){
			Collections.sort(menu.getSubtrees(), new MenuComparator());//二级菜单排序
		}
		return menuTree;
	}

}
