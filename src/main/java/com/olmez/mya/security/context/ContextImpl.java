package com.olmez.mya.security.context;

import java.util.HashMap;
import java.util.Map;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ContextImpl implements Context {

	private final Map<String, Object> map = new HashMap<>();
	private final Map<String, Object> classMap = new HashMap<>();

	@Override
	public Object getAttribute(String key) {
		return map.get(key);
	}

	@Override
	public <T> T getAttribute(Class<T> key) {
		return key.cast(classMap.get(key.getName()));
	}

	@Override
	public <T> T setAttribute(Class<T> key, T value) {
		classMap.put(key.getName(), value);
		return value;
	}

	@Override
	public Object setAttribute(String key, Object value) {
		map.put(key, value);
		return value;
	}

}
