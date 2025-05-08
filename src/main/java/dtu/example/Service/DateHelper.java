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
}
