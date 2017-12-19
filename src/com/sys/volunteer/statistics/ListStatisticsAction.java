package com.sys.volunteer.statistics;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sys.volunteer.baseaction.BaseListAction;
import com.sys.volunteer.common.Const;
import com.sys.volunteer.common.DateUtil;
import com.sys.volunteer.exception.SystemException;
import com.sys.volunteer.pojo.Users;
import com.sys.volunteer.vo.StatisticsVO;
/**
 * 各类统计
 * @author Administrator
 *
 */
@Controller
/* Controller为了把Action交给Spring管理 */
@Scope("prototype")
/* 每次创建新实例 */
public class ListStatisticsAction extends BaseListAction {
	
	@Resource
	StatisticsService statisticsService;
	
	
	private List<StatisticsVO> statisticsVOs;
	
	private String startDate;
	
	private String endDate;
	
	private String supplyName;
	
	private String goodsName;
	
	private String userName;
	
	public String showStatistics() throws Exception{
		return "showStatistics";
	}
	
	/**
	 * 验证是否有输入日期
	 */
	public void verifySelectedDateToStatistic(){
		
		if (null==startDate||startDate.equals("")) {
			throw new SystemException("请选择开始计算日期");
		}
		if (null==endDate||endDate.equals("")) {
			throw new SystemException("请选择结束计算日期");
		}
		Date sdDate=DateUtil.formatDate(startDate);
		Date edDate=DateUtil.formatDate(endDate);
		if(sdDate.after(edDate)){
			throw new SystemException("开始日期不能后于结束日期");			
		}		
	}
	
	/**
	 * 统计日期内各任务考勤情况
	 * @return
	 * @throws Exception
	 */
	public String listStatistics() throws Exception{
		Users user=getSessionUser();
		if (user.getUsergroup().getGroupType()>Const.GroupType.ADMIN) {
			return ShowMessage(MSG_TYPE_NORMAL, "你不是管理员,只有管理员级别才能查看统计", "", "", 0);
		}
		
		verifySelectedDateToStatistic();
		statisticsVOs=statisticsService.getStatisticsVOs( startDate, endDate,supplyName,goodsName,userName);
		return SUCCESS;
	}
	
	public List<StatisticsVO> getStatisticsVOs() {
		return statisticsVOs;
	}

	public void setStatisticsVOs(List<StatisticsVO> statisticsVOs) {
		this.statisticsVOs = statisticsVOs;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	
}
