package com.olmez.mya.utility;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class ElapsedTimer {
	private final Instant start = Instant.now();

	public Double elapsedSeconds() {
		Duration elapsed = Duration.between(start, Instant.now());
		return elapsed.getSeconds() + (TimeUnit.NANOSECONDS.toMillis(elapsed.getNano()) / 1000.);
	}

	@Override
	public String toString() {
		return elapsedSeconds().toString();
	}
}
