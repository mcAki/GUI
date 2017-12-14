package com.ages.model;

import org.apache.commons.dbutils.QueryRunner;

public class ServerInfoBean {
	private ConnectInfo connectInfo;
	private QueryRunner gameServerConnectRunner;
	private QueryRunner backupConnectRunner;
	public ConnectInfo getConnectInfo() {
		return connectInfo;
	}
	public void setConnectInfo(ConnectInfo connectInfo) {
		this.connectInfo = connectInfo;
	}
	public QueryRunner getGameServerConnectRunner() {
		return gameServerConnectRunner;
	}
	public void setGameServerConnectRunner(QueryRunner gameServerConnectRunner) {
		this.gameServerConnectRunner = gameServerConnectRunner;
	}
	public QueryRunner getBackupConnectRunner() {
		return backupConnectRunner;
	}
	public void setBackupConnectRunner(QueryRunner backupConnectRunner) {
		this.backupConnectRunner = backupConnectRunner;
	}
	
}
