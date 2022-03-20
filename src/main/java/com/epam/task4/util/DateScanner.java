package com.epam.task4.util;

import com.epam.task4.MainApp;
import com.epam.task4.controller.command.ViewOrderByNearestDateCMD;
import com.epam.task4.controller.command.ViewOrderCatalogFromToCMD;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Class for reading data about dates from the console with built-in validation.
 * <p>
 * Are directly called in the {@code execute()} method in the following classes:
 * @see ViewOrderByNearestDateCMD
 * @see ViewOrderCatalogFromToCMD
 * @author Oleksii Kushch
 */
public class DateScanner {
    /**
     * Reading data (year) from the console with built-in validation.
     * <p>
     * Noted: return {@code null} if needed abort the entire operation
     * (method execute in the corresponding command classes, see the detailed description of this class)
     * @return year or {@code null} if abort the entire operation
     */
    public static Integer inputYear() {
        int year = 1;
        boolean isYearSet = false;
        while (!isYearSet) {
            String stringYear = MainApp.getScanner().nextLine();
            if (stringYear.equals("--back") || stringYear.equals("-b")) {
                return null;     // abort the entire operation
            }
            try {
                year = Integer.parseInt(stringYear);
                isYearSet = true;
            } catch(NumberFormatException exception) {
                System.out.println("Invalid format year '" + stringYear + "', " +
                        "(valid format, number of year, example: 2022), try again:");
            }
        }
        return year;
    }

    /**
     * Reading data (month (month number)) from the console with built-in validation.
     * <p>
     * Noted: return {@code null} if needed abort the entire operation
     * (method execute in the corresponding command classes, see the detailed description of this class)
     * @return month (month number) or {@code null} if abort the entire operation
     */
    public static Integer inputMonth(int year) {
        int month = 1;
        boolean isMonthSet = false;
        while (!isMonthSet) {
            String stringMonth = MainApp.getScanner().nextLine();
            if (stringMonth.equals("--back") || stringMonth.equals("-b")) {
                return null;     // abort the entire operation
            }
            try {
                month = Integer.parseInt(stringMonth);
                if (isValidMonth(month)) {
                    isMonthSet = true;
                }
            } catch(NumberFormatException exception) {
                System.out.println("Invalid format month '" + stringMonth + "', try again:");
            }
        }
        return month;
    }

    /**
     * Reading data (day) from the console with built-in validation.
     * <p>
     * Noted: return {@code null} if needed abort the entire operation
     * (method execute in the corresponding command classes, see the detailed description of this class)
     * @return day or {@code null} if abort the entire operation
     */
    public static Integer inputDay(int year, int month) {
        int day = 1;
        boolean isDaySet = false;
        while (!isDaySet) {
            String stringDay = MainApp.getScanner().nextLine();
            if (stringDay.equals("--back") || stringDay.equals("-b")) {
                return null;     // abort the entire operation
            }
            try {
                day = Integer.parseInt(stringDay);
                if (isValidDay(year, month, day)) {
                    isDaySet = true;
                }
            } catch(NumberFormatException exception) {
                System.out.println("Invalid format day '" + stringDay + "', try again:");
            }
        }
        return day;
    }

    /**
     * Validates the entered month.
     * @param month month (month number) in integer format
     * @return true if month is valid, false if month is invalid
     */
    private static boolean isValidMonth(int month) {
        try {
            LocalDate.of(1970, month, 1);
        } catch (DateTimeException exception) {
            System.out.println("Invalid value for month of year (valid values 1 - 12): " + month + ", try again:");
            return false;
        }
        return true;
    }

    /**
     * Based on the entered year and month, validates the entered day accordingly.
     * @param year year in integer format
     * @param month month (month number) in integer format
     * @param day day in integer format
     * @return true if day is valid, false if day is invalid
     */
    private static boolean isValidDay(int year, int month, int day) {
        try {
            LocalDate.of(year, month, day);
        } catch (DateTimeException exception) {
            System.out.println("Invalid value for DayOfMonth (valid values 1 - 28/31): " + day + ", try again:");
            return false;
        }
        return true;
    }
}
