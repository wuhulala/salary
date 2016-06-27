package com.databasecourse.salary.converters;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * date:2016-05-26 00:53
 */
public class DateFormatter implements Formatter<Date> {
    private String datePattern;
    private SimpleDateFormat dateFormat;

    public DateFormatter() {
    }

    public DateFormatter(String datePattern) {
        this.datePattern = datePattern;
        dateFormat = new SimpleDateFormat(datePattern);
        dateFormat.setLenient(false);
    }

    public Date parse(String s, Locale locale) throws ParseException {
        return dateFormat.parse(s);
    }

    public String print(Date date, Locale locale) {
        return dateFormat.format(date);
    }
}
