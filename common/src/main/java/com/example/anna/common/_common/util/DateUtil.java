package com.example.anna.common._common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {


    private DateUtil() {
    }


    public static String convertFormat(LocalDate date, String format) {
        return date.format(DateTimeFormatter.ofPattern(format));
    }


    public static String convertFormat(LocalDateTime dateTime, String format) {
        return dateTime.format(DateTimeFormatter.ofPattern(format));
    }


}
