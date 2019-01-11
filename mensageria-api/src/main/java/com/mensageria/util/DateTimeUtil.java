package com.mensageria.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static String format(final LocalDateTime dateTime) {
        return DATE_TIME_FORMATTER.format(dateTime);
    }
}