package com.sys.volunteer.ka;

public class Query91KAProduct {

	private String cpid;
	private String gameId;
	private String retResult;
	private String autogameId;
	private String autogamePar;
	private String sign;
	public String getCpid() {
		return cpid;
	}
	public void setCpid(String cpid) {
		this.cpid = cpid;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public String getRetResult() {
		return retResult;
	}
	public void setRetResult(String retResult) {
		this.retResult = retResult;
	}
	public String getAutogameId() {
		return autogameId;
	}
	public void setAutogameId(String autogameId) {
		this.autogameId = autogameId;
	}
	public String getAutogamePar() {
		return autogamePar;
	}
	public void setAutogamePar(String autogamePar) {
		this.autogamePar = autogamePar;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	/**
	 * 初始化
	 * @param arg
	 * @return
	 */
	public Query91KAProduct init(String arg){
		if (arg==null||arg.equals("")) {
			return null;
		}
		String[] params = arg.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] childParas = params[i].split("=");
			String key = childParas[0];
			String value = "";
			if (childParas.length==2) {
				value = childParas[1];
			}
			this.parseType(this, key, value);
		}
		
		return this;
	}
	
	/**
	 * 转化对应类型
	 * @param order91ka
	 * @param key
	 * @param value
	 * @return
	 */
	public Query91KAProduct parseType(Query91KAProduct queryProduct,String key,String value){
		if (key.equals("cpid")) {
			queryProduct.setCpid(value);
		}
		if (key.equals("game_id")) {
			queryProduct.setGameId(value);
		}
		if (key.equals("ret_result")) {
			queryProduct.setRetResult(value);
		}
		if (key.equals("autogame_id")) {
			queryProduct.setAutogameId(value);
		}
		if (key.equals("autogame_par")) {
			queryProduct.setAutogamePar(value);
		}
		if (key.equals("sign")) {
			queryProduct.setSign(value);
		}
		return queryProduct;
	}
}
