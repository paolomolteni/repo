package it.paolomolteni.food.utility;

import org.apache.commons.lang3.StringUtils;

public class Utils {

	/**
	 * @param value
	 * @return
	 */
	public static String getLikeValue(String value) {
		String newValue = "%"+StringUtils.defaultString(value)+"%";
		return newValue.toLowerCase();
	}
	
}
