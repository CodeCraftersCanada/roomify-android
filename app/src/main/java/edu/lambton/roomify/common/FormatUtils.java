package edu.lambton.roomify.common;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatUtils {

    @NonNull
    public static String FormatDate(String inputString) {
        // Parse the input string to LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(inputString, DateTimeFormatter.ISO_DATE_TIME);

        // Print the formatted string
       return "Added " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
