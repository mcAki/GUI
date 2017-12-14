package test;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ages.util.DbManager;
import com.sys.volunteer.common.DateUtil;
import com.sys.volunteer.gmmanage.GMManageService;
import com.sys.volunteer.pagemodel.QueryResult;
import com.sys.volunteer.vo.CalFirstChargeAvgVo;
import com.sys.volunteer.vo.CalPlayerChurnRateVo;
import com.sys.volunteer.vo.ServerInfoUserInfoVo;
import com.sys.volunteer.vo.UserInfo;

public class TestDB {
	
	private static GMManageService gmManageService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext act = new ClassPathXmlApplicationContext(
					new String[] { "applicationSpringContext-main.xml" });
//			goodsService = (GoodsService)act.getBean("goodsServiceBean");
//			goodsTypeService = (GoodsTypeService) act.getBean("goodsTypeServiceBean");
//			orderService = (OrderService) act.getBean("orderServiceBean");
//			supplyService = (SupplyService) act.getBean("supplyServiceBean");
//			usergroupService = (UsergroupService) act.getBean("usergroupServiceBean");
//			authorityService = (AuthorityService) act.getBean("authorityServiceBean");
//			balanceService = (BalanceService) act.getBean("balanceServiceBean");
//			echoDao = (IEchoDao) act.getBean("echoDao");
			gmManageService = (GMManageService)act.getBean("GMManageServiceBean");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	public static void info(Object obj)
	{
		System.out.println(obj);
	}
	
	@Test
	public void testCreateDB(){

	}
	
	@Test
	public void testSupplyKernel() throws Exception{
//		String url = DbManager.getRequestUrl(1)//1--servierId
//			+"ajax/onlineplayer.php?userId=1";
//			String result = RemotePageUtil.getRotmePage(url, "utf-8");
//		String[] playerIds = JsonUtil.getStringArray4Json(result);
//		QueryResult queryResult = gmManageService.findUserInfosByIds(1, playerIds, 
//		0, 20);
//		System.out.println(queryResult.getTotalrecord());
		Date startDate = DateUtil.formatDate("2014-06-19 10:17:28");
		Date endDate = DateUtil.formatDate("2014-06-19 16:39:30");
//		QueryResult queryResult = gmManageService.calLoginByEveryday(1, startDate, endDate, 0, 20);
//		System.out.println(queryResult.getTotalrecord());
//		List<CalLoginEverydayVo> list = gmManageService.calLoginByEveryday(1, startDate, endDate, 0, 20);
//		for (CalLoginEverydayVo calLoginEverydayVo : list) {
//			System.out.println(calLoginEverydayVo.getUserId()+":"+calLoginEverydayVo.getLoginTime());
//		}
//		long s = System.currentTimeMillis();
//		List<LivenessVo> list = gmManageService.findLivenessPlayer(1, startDate, endDate, 0, 2000).getResultlist();
//		System.out.println("execute time:"+(System.currentTimeMillis()-s));
//		List<String> list = gmManageService.findUserInfoByRolenames(1, "\'童菲\',\'包又莲\'");
//		System.out.println(list.size());
//		String sql = "select state from user_info where rolename in (\'童菲\',\'包又莲\')";
//		List<Integer> list = gmManageService.findSingleRowListBySql(DbManager
//				.getBackUpCon(1), int.class, sql);
//		System.out.println(list.size());
//		List<String> list = gmManageService.findUserInfoByLevelAndLastloginTime(1, 0, 10, 3);
//		System.out.println(list.size());
//		String sql = "select continueWin from user_info where id = ?";
//		Object[] params = new Object[] {"d6eb8f83-a361-4f99-a285-3e1e460255ba"};
//		QueryResult queryResult = gmManageService.getScrollData(DbManager
//				.getBackUpCon(1), 0, 20, sql, params,
//				TestVo.class);
//		ServerInfoUserInfoVo vo = gmManageService.findServerInfoUserInfoVo(1);
//		System.out.println(vo.getRegisterCount());
//		CalFirstChargeAvgVo vo = gmManageService.calFirstChargeAvg(1);
//		System.out.println(vo.getAvg());
		QueryResult queryResult = gmManageService.findRegisterEveryday(1, DateUtil.getMinDate(startDate), DateUtil.getMaxDate(endDate), 0, 10);
		System.out.println(queryResult.getTotalrecord());
	}
}
