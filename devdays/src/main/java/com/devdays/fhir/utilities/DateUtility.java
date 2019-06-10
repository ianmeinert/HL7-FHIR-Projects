package com.devdays.fhir.utilities;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * A utility class for the different types of date and time classes.
 * 
 * @author Ian Meinert
 *
 */
public class DateUtility {

	/**
	 * This method accepts a {@link LocalDate} for conversion to a {@link Date}.
	 * 
	 * @param localDate {@link LocalDate}
	 * @return {@link Date}
	 */
	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * This method accepts a {@link LocalDateTime} for conversion to a {@link Date}.
	 * 
	 * @param localDateTime {@link LocalDateTime}
	 * @return {@link Date}
	 */
	public static Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * This method accepts a {@link Date} for conversion to a {@link LocalDate}.
	 * 
	 * @param date {@link Date}
	 * @return {@link LocalDate}
	 */
	public static LocalDate asLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * This method accepts a {@link Date} for conversion to a {@link LocalDateTime}.
	 * 
	 * @param date {@link Date}
	 * @return {@link LocalDateTime}
	 */
	public static LocalDateTime asLocalDateTime(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
}
