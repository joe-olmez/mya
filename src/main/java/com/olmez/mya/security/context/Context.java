package com.olmez.mya.security.context;

import java.util.Optional;

public interface Context {

	Object getAttribute(String key);

	<T> T getAttribute(Class<T> key);

	/**
	 * Sets attribute with given key class and returns new value. If failed to set
	 * returns null.
	 *
	 * @return new value. If failed returns null
	 */
	<T> T setAttribute(Class<T> key, T value);

	/**
	 * Sets attribute with given key string and returns new value. If failed to set
	 * returns null.
	 *
	 * @return new value. If failed returns null
	 */
	Object setAttribute(String key, Object value);

	default Optional<Object> getAttributeOptional(String key) {
		return Optional.ofNullable(getAttribute(key));
	}

	default <T> Optional<T> getAttributeOptional(Class<T> key) {
		return Optional.ofNullable(getAttribute(key));
	}

	default <T> T getAttribute(String key, Class<T> objClass) {
		return objClass.cast(getAttribute(key));
	}

	default <T> Optional<T> getAttributeOptional(String key, Class<T> objClass) {
		return Optional.ofNullable(getAttribute(key, objClass));
	}

	default <T> T getAttribute(Class<T> key, T defaultValue) {
		Optional<T> optVal = getAttributeOptional(key);
		if (optVal.isPresent()) {
			return optVal.get();
		}
		return setAttribute(key, defaultValue);
	}

	default Object getAttribute(String key, Object defaultValue) {
		Optional<Object> optVal = getAttributeOptional(key);
		if (optVal.isPresent()) {
			return optVal.get();
		}
		return setAttribute(key, defaultValue);
	}

	default <T> T getAttribute(String key, Class<T> objClass, T defaultValue) {
		Optional<T> optVal = getAttributeOptional(key, objClass);
		if (optVal.isPresent()) {
			return optVal.get();
		}
		return objClass.cast(setAttribute(key, defaultValue));
	}

	default void clearAttribute(String key) {
		setAttribute(key, null);
	}

	default <T> void clearAttribute(Class<T> key) {
		setAttribute(key, null);
	}

	default boolean hasAttribute(String key) {
		return getAttributeOptional(key).isPresent();
	}

	default <T> boolean hasAttribute(Class<T> key) {
		return getAttributeOptional(key).isPresent();
	}
}
