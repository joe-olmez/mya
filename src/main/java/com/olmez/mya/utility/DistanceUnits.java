package com.olmez.mya.utility;

public enum DistanceUnits {

	KM {
		@Override
		public double toKm(double distance) {
			return distance;
		}

		@Override
		public double toMiles(double distance) {
			return distance / A_MILE_TO_KM;
		}

		@Override
		public double convert(double distance, DistanceUnits unit) {
			return unit.toKm(distance);
		}
	},
	MILES {
		@Override
		public double toKm(double distance) {
			return distance * A_MILE_TO_KM;
		}

		@Override
		public double toMiles(double distance) {
			return distance;
		}

		@Override
		public double convert(double distance, DistanceUnits unit) {
			return unit.toMiles(distance);
		}
	};

	public static final double A_MILE_TO_KM = 1.609344;

	public abstract double toKm(double distance);

	public abstract double toMiles(double distance);

	public abstract double convert(double distance, DistanceUnits unit);
}
