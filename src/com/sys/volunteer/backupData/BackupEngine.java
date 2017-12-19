package com.sys.volunteer.backupData;

import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sys.volunteer.backupData.backup.vo.BaseBackupVO;
import com.sys.volunteer.backupData.backup.vo.IBackupVO;

public class BackupEngine extends Thread {

	protected Log logger = LogFactory.getLog(this.getClass());

	/**
	 * 执行任务休眠间隔（毫秒）
	 */
	private int executeTaskSleepInterval = 1000;
	
	private static BackupEngine instance = null;

	/**
	 * 线程必须刷新时间
	 */
	private long refreshTime = 0;

	private LinkedList<IBackupVO> taskList;
	
	static boolean isLogin = false;
	
	/**
	 * 获取实例化管理器
	 * 
	 * @return
	 */
	public static BackupEngine getInstance() {
		if (instance == null) {
			instance = new BackupEngine();
		}
		return instance;
	}

	/**
	 * 构造器
	 * 
	 * @param producerPool
	 */
	private BackupEngine() {
		taskList = new LinkedList<IBackupVO>();
	}

	@Override
	public void destroy() {

	}

	@Override
	public void run() {
		while (true) {

			if (System.currentTimeMillis() > refreshTime) {
				refreshTime = System.currentTimeMillis()
						+ executeTaskSleepInterval;
			} else {
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			}
			
			try {
				//检测登陆
				if (!isLogin) {
					int result = BaseBackup.login();
					if (result>0) {
						//设置状态成已登录
						isLogin = true;
					}
				}
				synchronized (taskList) {
					while (taskList.size()>0) {
						logger.info("进入备份流程");
						
						IBackupVO vo = taskList.getFirst();
						taskList.removeFirst();
//						String sqlContent = BackupClient.getInsertSQL(17, 17, "e票联",
//								"402882e72e6131aa012e6131f81d0001", "s", null,
//								"4028808234a9a73c0134a9ce32290004", "2012-01-05 01:41:02",
//								50.00, 1, "13710568050", 1, 0, 27, "移动充值卡50元", null, null);
//						int reslut = BackupClient.backupDb("testtable2", sqlContent);
//						if (reslut == BackupClient.TABLE_NOT_EXIST) {
//							String sql = "(`operation_id` int(11) NOT NULL, `supply_id` int(11) NOT NULL,  `supplyName` varchar(32) DEFAULT NULL,  `user_id` varchar(32) NOT NULL,  `userName` varchar(32) DEFAULT NULL,  `terminal_no` varchar(32) DEFAULT '',  `mainOrderId` varchar(32) DEFAULT NULL,  `createTime` datetime DEFAULT NULL,  `subtotal` double(10,2) DEFAULT '0.00',  `amount` int(11) DEFAULT '0',  `mobileNum` varchar(32) DEFAULT NULL,  `type` int(11) DEFAULT '0',  `result` int(11) DEFAULT '0',  `goods_id` int(11) DEFAULT '0',  `goods_name` varchar(32) DEFAULT '',  `goods_value` int(11) DEFAULT '0',  `is_terminal` int(11) DEFAULT '0',  PRIMARY KEY (`operation_id`))";
//							BackupClient.registerTable("testtable2", sql);
//						}
						//先创建表
						int result = BaseBackup.registerTable(vo.getTblName(), vo.getRegSQL());
						if (result == BaseBackup.RESULT_TABLE_EXIST || result == BaseBackup.DEFINE_RESULT_SUCCESS_) {
							result = BaseBackup.backupDb(vo.getTblName(), vo.getInsertSQL());
							if (result == 1) {
								logger.info(vo.getTblName()+"备份成功");
							}else {
								logger.error(vo.getTblName()+"备份失败,返回结果为:"+result);
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public synchronized void start() {
		this.setName("BackupEngine");
		super.start();
		logger.info("增量备份接口");
	}
	
	/**
	 * 强制刷新
	 */
	public synchronized void forceRefreshOrder() {
		refreshTime = 0;
	}

	/**
	 * 执行任务休眠间隔（毫秒）
	 * 
	 * @param executeTaskSleepInterval
	 */
	public void setExecuteTaskSleepInterval(int executeTaskSleepInterval) {
		this.executeTaskSleepInterval = executeTaskSleepInterval;
	}

	public LinkedList<IBackupVO> getTaskList() {
		return taskList;
	}

}
