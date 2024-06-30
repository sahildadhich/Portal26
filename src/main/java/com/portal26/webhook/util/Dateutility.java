package com.portal26.webhook.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Dateutility {
	
	
	/*
	 * Convert string timestamp to sql timestamp 
	 */
	public static Timestamp convertToTimestamp(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateString, formatter);
        return Timestamp.from(offsetDateTime.toInstant());
    }
	
	/*
	 * Convert toDate in yyyy-MM-dd date format in string to Sql TimeStamp,
	 * If toDate field is null, timestamp will be generated for current datetime
	 */
	public static Timestamp getToDateTime(String fromDate) {
		Timestamp fromTimestamp = null;
		
		if(Objects.isNull(fromDate)) {
			Long millis = System.currentTimeMillis();
			fromTimestamp = new Timestamp(millis);
		}else {
			LocalDate localDate = LocalDate.parse(fromDate);
			LocalDateTime endOfDay = localDate.atTime(LocalTime.MAX);
			fromTimestamp = Timestamp.valueOf(endOfDay);
		}
		
		return fromTimestamp;
	}
	
	
	/*
	 * Convert fromDate in yyyy-MM-dd date format in string to Sql TimeStamp,
	 * If from field is null, timestamp will be generated based on fromDate timestamp and by default we are setting fromDate to 90 days from current datetime,
	 * else given fromDate will be converted to the Sql Timestamp format
	 */
	public static Timestamp getFromTimestamp(String fromDate, Timestamp toTimestamp) {
		if(Objects.isNull(fromDate)) {
			LocalDateTime currentDateTime = toTimestamp.toLocalDateTime();

	        // Subtract 30 days
	        LocalDateTime dateTimeMinus30Days = currentDateTime.minus(180, ChronoUnit.DAYS);

	        // Convert LocalDateTime back to SQL Timestamp
	        return Timestamp.valueOf(dateTimeMinus30Days);
		}else {
			LocalDate localDate = LocalDate.parse(fromDate);
			LocalDateTime localDateTime = localDate.atStartOfDay();
			return Timestamp.valueOf(localDateTime);
		}
	}

}
