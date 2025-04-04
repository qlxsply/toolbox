package com.avalon.tools.util.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HighDateUtil {

    public static LocalDateTime parse(String str) {
        return parse(str, DatePattern.NORM_DATETIME_FORMATTER);
    }

    public static LocalDateTime parse(String str, String format) {
        return parse(str, DatePattern.createFormatter(format));
    }

    public static LocalDateTime parse(String str, DateTimeFormatter format) {
        return LocalDateTime.parse(str, format);
    }

    public static String format(LocalDateTime ldt) {
        return format(ldt, DatePattern.NORM_DATETIME_FORMATTER);
    }

    public static String formatMs(LocalDateTime ldt) {
        return format(ldt, DatePattern.NORM_DATETIME_MS_FORMATTER);
    }

    public static String format(LocalDateTime ldt, String format) {
        return format(ldt, DateTimeFormatter.ofPattern(format));
    }

    public static String format(LocalDateTime ldt, DateTimeFormatter format) {
        return ldt.format(format);
    }

}
