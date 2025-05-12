package dtu.example.Service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Adam
public class DateHelperTest {

    @Test
    void testIsDateFormat() {
        // Valid date formats
        assertTrue(DateHelper.isDateFormat("01012025")); // January 1, 2025
        assertTrue(DateHelper.isDateFormat("31122025")); // December 31, 2025

        // Invalid date formats
        assertFalse(DateHelper.isDateFormat("20250101")); // Wrong format (YYYYMMDD)
        assertFalse(DateHelper.isDateFormat("010125"));   // Incomplete date
        assertFalse(DateHelper.isDateFormat("abcd1234")); // Non-numeric input
        assertFalse(DateHelper.isDateFormat("32012025")); // Invalid day
        assertFalse(DateHelper.isDateFormat("01002025")); // Invalid month
        assertFalse(DateHelper.isDateFormat("null"));       // Null input
    }

    @Test
    void testIsWeekFormat() {
        // Valid week formats
        assertTrue(DateHelper.isWeekFormat("1"));  // First week of the year
        assertTrue(DateHelper.isWeekFormat("52")); // Last week of the year

        // Invalid week formats
        assertFalse(DateHelper.isWeekFormat("0"));  // Week number too low
        assertFalse(DateHelper.isWeekFormat("53")); // Week number too high
        assertFalse(DateHelper.isWeekFormat("abc")); // Non-numeric input
        assertFalse(DateHelper.isWeekFormat(""));    // Empty string
        assertFalse(DateHelper.isWeekFormat(null));  // Null input
    }
}