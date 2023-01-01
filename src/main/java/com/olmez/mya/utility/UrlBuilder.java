package com.olmez.mya.utility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class UrlBuilder {
	/**
	 * Successful request status.
	 */
	public static final int HTTP_RESPONSE_STATUS_OK = 200;

	private final HashMap<String, String> parameters = new LinkedHashMap<>();
	private final String baseUrl;
	private String lastParamKey;
	private String path = "";

	public UrlBuilder() {
		this("");
	}

	public UrlBuilder(String baseUrl) {
		this(baseUrl, "");
	}

	public UrlBuilder(String baseUrl, String path) {
		this.baseUrl = baseUrl;
		this.path = path;
	}

	public void addParameter(String key, Integer value) {
		if (value != null) {
			addParameter(key, Integer.toString(value));
		}
	}

	public void addParameter(String key, Long value) {
		if (value != null) {
			addParameter(key, Long.toString(value));
		}
	}

	public void addParameter(String key, Double value) {
		if (value != null) {
			addParameter(key, Double.toString(value));
		}
	}

	public void addParameter(String key, LocalDate value) {
		if (value != null) {
			addParameter(key, value.toString());
		}
	}

	public void addParameter(String key, Boolean value) {
		if (value != null) {
			addParameter(key, String.valueOf(value));
		}
	}

	public void addParameter(String key, String value) {
		if (value != null) {
			parameters.computeIfAbsent(key, k -> lastParamKey = key);
			parameters.put(key, value);
		}
	}

	public String getUrl() {
		return baseUrl + getPath();
	}

	public String getPath() {
		return path + getStringParams();
	}

	public void addParameter(String key, Set<String> value) {
		if (value != null) {
			if (value.isEmpty()) {
				addParameter(key, " ");
			} else {
				List<String> list = new ArrayList<>(value);
				String lastItem = list.get(list.size() - 1);

				StringBuilder sb = new StringBuilder();
				for (String temp : value) {
					sb.append(temp);
					if (!temp.equals(lastItem)) {
						sb.append(",");
					}
				}
				addParameter(key, sb.toString());
			}
		}
	}

	public String getStringParams() {
		if (parameters.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder(parameters.size() * 15);
		sb.append("?");

		for (Entry<String, String> entry : parameters.entrySet()) {
			sb.append(entry.getKey());
			sb.append("=");
			sb.append(entry.getValue());
			if (!entry.getKey().equals(lastParamKey)) {
				sb.append("&");
			}
		}
		return sb.toString();
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return getUrl();
	}
}
