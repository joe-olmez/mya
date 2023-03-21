package com.olmez.mya.utility;

public class MathHelper {
	double total = 0;
	Double min = null;
	Double max = null;
	int cnt = 0;

	public enum HelperValue {
		MIN, MAX, AVERAGE, TOTAL
	}

	public void addValue(Float value) {
		if (value != null) {
			addValue((double) value);
		}
	}

	public void addValue(Double value) {
		// Comment to force a build
		if (value != null) {
			if ((max == null) || (value > max)) {
				max = value;
			}
			if ((min == null) || (value < min)) {
				min = value;
			}
			total += value;
			cnt++;
		}
	}

	public Double getValue(HelperValue valueType) {
		switch (valueType) {
			case AVERAGE:
				return getAverageDouble();
			case MAX:
				return getMax();
			case MIN:
				return getMin();
			case TOTAL:
				return getTotal();
		}
		return null;
	}

	public double getTotal() {
		return total;
	}

	public Double getMin() {
		return min;
	}

	public Double getMax() {
		return max;
	}

	public int getCnt() {
		return cnt;
	}

	public Double getAverage() {
		return isValid() ? total / cnt : null;
	}

	public double getAverageDouble() {
		return isValid() ? total / cnt : 0.;
	}

	public Float getAverageFloat() {
		return isValid() ? (float) (total / cnt) : null;
	}

	public boolean isValid() {
		return cnt > 0;
	}

	/*
	 * Initialize this math helper to have the same stats as an other
	 */
	public void clone(MathHelper other) {
		total = other.getTotal();
		min = other.getMin();
		max = other.getMax();
		cnt = other.getCnt();
	}

}
