package com.olmez.mya.utility;

import com.olmez.mya.model.enums.CurrencyCode;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CurrencyUtility {

	public static CurrencyCode getBaseCurrencyCode() {
		return CurrencyCode.USD;
	}

}
