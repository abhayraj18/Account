package com.example.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micrometer.core.instrument.util.StringUtils;

public class DateUtil {

	private final static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	public static final String DD_MM_YYYY_FORMAT = "dd-MM-yyyy";

	public static LocalDate getCurrentDate() {
		return LocalDate.now();
	}

	public static String formatLocalDateToString(LocalDate localDate, String pattern) {
		if (Objects.isNull(localDate)) {
			return "";
		}
		try {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
			return localDate.format(dateTimeFormatter);
		} catch (Exception e) {
			logger.error("Error while formating date: " + localDate);
			e.printStackTrace();
		}
		return "";
	}

	public static LocalDate parseStringToLocalDate(String dateString, String pattern) {
		if (StringUtils.isBlank(dateString)) {
			return null;
		}
		try {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
			return LocalDate.parse(dateString, dateTimeFormatter);
		} catch (Exception e) {
			logger.error("Error while parsing date string: " + dateString + ", pattern: " + pattern);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param dateOfBirth
	 * @return
	 */
	public static int calculateAge(LocalDate dateOfBirth) {
		LocalDate now = getCurrentDate();
		int age = now.getYear() - dateOfBirth.getYear();
		if (now.getMonthValue() < dateOfBirth.getMonthValue()) {
			age--;
		}
		return age;
	}

}
