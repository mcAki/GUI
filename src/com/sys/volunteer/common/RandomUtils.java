package com.sys.volunteer.common;

public class RandomUtils {

	private static String[] randomValues = new String[] { "0", "1", "2", "3",
			"4", "5", "6", "7", "8", "9" };

	public static String getVerificationCode(int lenght) {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < lenght; i++) {
			Double number = Math.random() * (randomValues.length - 1);
			str.append(randomValues[number.intValue()]);
		}

		return str.toString();

	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(RandomUtils.getVerificationCode(6));
		}
		
	}
}
