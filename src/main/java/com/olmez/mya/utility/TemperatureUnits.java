package com.olmez.mya.utility;

public enum TemperatureUnits {

	CELSIUS {
		@Override
		public double toCelsius(double temperature) {
			return temperature;
		}

		@Override
		public double toFahrenheit(double temperature) {
			return ((temperature * 9) / 5) + 32;
		}

		@Override
		public double convert(double temperature, TemperatureUnits unit) {
			return unit.toCelsius(temperature);
		}
	},
	FAHRENHEIT {
		@Override
		public double toCelsius(double temperature) {
			return ((temperature - 32) / 9) * 5;
		}

		@Override
		public double toFahrenheit(double temperature) {
			return temperature;
		}

		@Override
		public double convert(double temperature, TemperatureUnits unit) {
			return unit.toFahrenheit(temperature);
		}
	};

	public abstract double toCelsius(double temperature);

	public abstract double toFahrenheit(double temperature);

	public abstract double convert(double temperature, TemperatureUnits unit);
}
