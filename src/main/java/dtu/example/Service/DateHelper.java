package dtu.example.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateHelper {
    //Adam wrote this. Helps determine whether a date is of the date format
    public static boolean isDateFormat(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        try {
            LocalDate.parse(dateString, formatter);
            return true;
        } catch (DateTimeParseException e) {
            // TODO: handle exception
            return false;
        }
    }

    public static boolean isWeekFormat(String week) {
        // Chek if the week is in week format: "WW"
        if (week == null || week.length() != 2) {
            return false;
        }
        try {
            var num = Integer.parseInt(week);
            // Check if the number is between 1 and 52
            if (num < 1 || num > 52) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }
}
