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

	
	public static double distance(double lat1, double lon1, double lat2, double lon2, DistanceUnits unit) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		double theta = lon1 - lon2;
		double dist = (Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)))
				+ (Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta)));
		dist = Math.acos(dist);
		dist = Math.toDegrees(dist);

		dist = dist * 60 * 1.1515;
		return unit.convert(dist, DistanceUnits.MILES);
	}

	public static Double roundTwoDigit(double number) {
		return Math.round(number * 100.0) / 100.0;
	}

	public static Double roundTwoDigit(Float number) {
		return number != null ? Math.round(number * 100.0) / 100.0 : null;
	}

}
