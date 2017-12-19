package com.sys.volunteer.pojo;

public class GamesType {
    /**
     * 游戏类别	
     */
	public static String getGamesType(String str){
		String[] args = {"HOT","A2D","E2H","I2L","M2P","Q2T","U2X"}; 
		for (String agr : args) {
			str = agr;
		}
		System.out.println(str);
		return str;
	}
	
	public static void main(String[] args) {
		getGamesType("HOT");
	}

}
