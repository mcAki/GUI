package com.sys.volunteer.xunyuan.test;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String headlength = "0027";
		byte[] arr = headlength.getBytes();
		System.out.println(new String(arr));
		System.out.println(Integer.parseInt(headlength));
	}

}
