package com.olmez.mya.utility;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MathUtils {

	/**
	 * Checks if the given number is between <code>min</code> and <code>max</code>
	 * Minimum and maximum is included.
	 *
	 * @param number Number to check
	 * @param min
	 * @param max
	 * @return true if the number is within the range
	 */
	public static boolean isBetween(int number, int min, int max) {
		return (number >= min) && (number <= max);
	}

	/**
	 * @param x
	 * @return the max value in the array x
	 */
	public static double maxValue(double[] x) {
		double max = x[0];
		for (double element : x) {
			if (element > max) {
				max = element;
			}
		}
		return max;
	}

	/**
	 * @param x
	 * @return the min value in the array x
	 */
	public static double minValue(double[] x) {
		double min = x[0];
		for (double element : x) {
			if (element < min) {
				min = element;
			}
		}
		return min;
	}

	/**
	 * @param x
	 * @return the mean of the array x
	 */
	public static double mean(double[] x) {
		double sum = 0;
		for (double i : x) {
			sum += i;
		}
		return sum / x.length;
	}

	/**
	 * @param x
	 * @return the sum of the array x
	 */
	public static double sum(double[] x) {
		double sum = 0;
		for (double i : x) {
			sum += i;
		}
		return sum;
	}

	public static Double roundTwoDigit(double number) {
		return Math.round(number * 100.0) / 100.0;
	}

	public static Double roundTwoDigit(Float number) {
		return number != null ? Math.round(number * 100.0) / 100.0 : null;
	}

	public static Double toDouble(String value) {
		if (StringUtility.isEmpty(value)) {
			return null;
		}
		return Double.valueOf(value);
	}

}
