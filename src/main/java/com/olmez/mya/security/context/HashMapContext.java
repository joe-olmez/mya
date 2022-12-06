package com.olmez.mya.security.context;

import java.util.HashMap;
import java.util.Map;

public class HashMapContext implements Context {
	private final Map<String, Object> byString = new HashMap<>();
	private final Map<Class<?>, Object> byClass = new HashMap<>();

	@Override
	public Object getAttribute(String key) {
		return byString.get(key);
	}

	@Override
	public <T> T getAttribute(Class<T> key) {
		Object value = byClass.get(key);
		return (value != null) ? key.cast(value) : null;
	}

	@Override
	public <T> T setAttribute(Class<T> key, T value) {
		byClass.put(key, value);
		return value;
	}

	@Override
	public Object setAttribute(String key, Object value) {
		byString.put(key, value);
		return value;
	}

}
