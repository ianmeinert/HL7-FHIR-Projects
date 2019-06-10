package com.devdays.fhir.utilities;

/**
 * A utility class for String objects.
 * 
 * @author Ian Meinert
 *
 */
public class StringUtility {
	/**
	 * Checks if a String is null and returns a non-null String.
	 * 
	 * @param s the String to evaluate.
	 * @return String
	 */
	public static String clearNull(String s) {
		return s == null ? new String() : s;
	}
}
