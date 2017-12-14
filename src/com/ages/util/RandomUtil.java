package com.ages.util;

import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;

public class RandomUtil extends RandomUtils {
	// private static long randomSeed = System.currentTimeMillis();
	private static Random random = new Random(System.currentTimeMillis());
	//private static Random randomEx = new Random();

	/**
	 * 获取一个指定范围内的随机数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int nextRandomInt(int min, int max) {
		int randomMax = (max + 1) - min;
		if (randomMax < 1) {
			// 没有得选，直接返回最大值
			return min;
		}
		return random.nextInt(randomMax) + min;
		// return (int) Math.round(Math.random()*(randomMax-min)+min);
	}

	/**
	 * 获取一个百分比随机真假
	 * 
	 * @param oddsPercent
	 *            真值随机百分比：0-100
	 * @return
	 */
	public static boolean nextRandomBoolean(int trueOddsPercent) {
		if (trueOddsPercent < 1) {
			return false;
		} else if (trueOddsPercent > 100) {
			return true;
		}
		return random.nextInt(100) >= (100 - trueOddsPercent);
	}

}
