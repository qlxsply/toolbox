package com.avalon.tools.util.date;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * author: avalon
 * date: 2025-03-16
 */
public interface DatePattern {

    String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    String NORM_DATETIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    String NORM_DATE_PATTERN = "yyyy-MM-dd";
    String NORM_TIME_PATTERN = "HH:mm:ss";
    String ISO8601_PATTERN = "yyyy-MM-dd HH:mm:ss,SSS";
    String CHINESE_DATE_PATTERN = "yyyy年MM月dd日";
    String CHINESE_DATE_TIME_PATTERN = "yyyy年MM月dd日HH时mm分ss秒";
    String PURE_DATE_PATTERN = "yyyyMMdd";
    String PURE_TIME_PATTERN = "HHmmss";
    String PURE_DATETIME_PATTERN = "yyyyMMddHHmmss";
    String PURE_DATETIME_MS_PATTERN = "yyyyMMddHHmmssSSS";
    String UTC_SIMPLE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    String UTC_SIMPLE_MS_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    String UTC_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    String UTC_WITH_ZONE_OFFSET_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";
    String UTC_MS_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    String UTC_MS_WITH_ZONE_OFFSET_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    String UTC_MS_WITH_XXX_OFFSET_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    DateTimeFormatter NORM_DATETIME_FORMATTER = createFormatter(NORM_DATETIME_PATTERN);
    DateTimeFormatter NORM_DATETIME_MS_FORMATTER = createFormatter(NORM_DATETIME_MS_PATTERN);
    DateTimeFormatter NORM_DATE_FORMATTER = createFormatter(NORM_DATE_PATTERN);
    DateTimeFormatter NORM_TIME_FORMATTER = createFormatter(NORM_TIME_PATTERN);
    DateTimeFormatter ISO8601_FORMATTER = createFormatter(ISO8601_PATTERN);
    DateTimeFormatter CHINESE_DATE_FORMATTER = createFormatter(CHINESE_DATE_PATTERN);
    DateTimeFormatter CHINESE_DATE_TIME_FORMATTER = createFormatter(CHINESE_DATE_TIME_PATTERN);
    DateTimeFormatter PURE_DATE_FORMATTER = createFormatter(PURE_DATE_PATTERN);
    DateTimeFormatter PURE_TIME_FORMATTER = createFormatter(PURE_TIME_PATTERN);
    DateTimeFormatter PURE_DATETIME_FORMATTER = createFormatter(PURE_DATETIME_PATTERN);
    DateTimeFormatter PURE_DATETIME_MS_FORMATTER = createFormatter(PURE_DATETIME_MS_PATTERN);
    DateTimeFormatter UTC_SIMPLE_FORMATTER = createFormatter(UTC_SIMPLE_PATTERN);
    DateTimeFormatter UTC_SIMPLE_MS_FORMATTER = createFormatter(UTC_SIMPLE_MS_PATTERN);
    DateTimeFormatter UTC_FORMATTER = createFormatter(UTC_PATTERN);
    DateTimeFormatter UTC_WITH_ZONE_OFFSET_FORMATTER = createFormatter(UTC_WITH_ZONE_OFFSET_PATTERN);
    DateTimeFormatter UTC_MS_FORMATTER = createFormatter(UTC_MS_PATTERN);
    DateTimeFormatter UTC_MS_WITH_ZONE_OFFSET_FORMATTER = createFormatter(UTC_MS_WITH_ZONE_OFFSET_PATTERN);
    DateTimeFormatter UTC_MS_WITH_XXX_OFFSET_FORMATTER = createFormatter(UTC_MS_WITH_XXX_OFFSET_PATTERN);

    static DateTimeFormatter createFormatter(String pattern) {
        return DateTimeFormatter.ofPattern(pattern, Locale.getDefault()).withZone(ZoneId.systemDefault());
    }

}
