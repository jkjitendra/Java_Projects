package org.program;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    public static void main(String[] args) {
        Instant createdDate = Instant.now();
        System.out.println("Original CreatedDate : " + createdDate);
        String formattedDate = formatInstantToIsoString(createdDate);
        System.out.println("Formatted CreatedDate: " + formattedDate);
    }

    public static String formatInstantToIsoString(Instant instant) {
        if (instant == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        return formatter.format(instant);
    }
}
