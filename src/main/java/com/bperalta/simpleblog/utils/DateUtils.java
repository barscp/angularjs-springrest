package com.bperalta.simpleblog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private static final String DATE_FORMAT = "yyyyMMdd";

	private DateUtils() {
	}

	public static String getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		return format.format(new Date());

	}


}