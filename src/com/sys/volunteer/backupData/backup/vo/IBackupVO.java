package com.sys.volunteer.backupData.backup.vo;

public interface IBackupVO {

	/**
	 * 获取表名
	 * @return
	 */
	public String getTblName() ;

	/**
	 * 获取创建表sql
	 * @return
	 */
	public String getRegSQL() ;

	/**
	 * 获取insert sql
	 * @return
	 */
	public String getInsertSQL() ;

}
