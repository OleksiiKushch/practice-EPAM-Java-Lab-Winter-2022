package com.epam.task4.util;

import com.epam.task4.MainApp;
import com.epam.task4.controller.command.ViewOrderByNearestDateCmd;
import com.epam.task4.controller.command.ViewOrderCatalogFromToCmd;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Class for reading data about dates from the console with built-in validation.
 * <p>
 * Are directly called in the {@code execute()} method in the following classes:
 * @see ViewOrderByNearestDateCmd
 * @see ViewOrderCatalogFromToCmd
 * @author Oleksii Kushch
 */
public class DateScanner {
    private static final String MSG_INVALID_FORMAT_YEAR = ConsoleColor.RED +
            "Invalid format year '%s', (valid format, number of year, example: 2022), try again:%n" + ConsoleColor.RESET;

    private static final String MSG_INVALID_FORMAT_MONTH = ConsoleColor.RED +
            "Invalid format month '%s', try again:&n" + ConsoleColor.RESET;
    private static final String MSG_INVALID_NUMERIC_FORMAT_MONTH = ConsoleColor.RED +
            "Invalid value for month of year (valid values 1 - 12): %d, try again:%n" + ConsoleColor.RESET;

    private static final String MSG_INVALID_FORMAT_DAY = ConsoleColor.RED +
            "Invalid format day '%s', try again:%n" + ConsoleColor.RESET;
    private static final String MSG_INVALID_NUMERIC_FORMAT_DAY = ConsoleColor.RED +
            "Invalid value for DayOfMonth (valid values 1 - 28/31): %d, try again:%n" + ConsoleColor.RESET;

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
            if (stringYear.equals(AppLiteral.BACK_CMD_FULL_CAST) ||
                    stringYear.equals(AppLiteral.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            }
            try {
                year = Integer.parseInt(stringYear);
                isYearSet = true;
            } catch(NumberFormatException exception) {
                System.out.printf(MSG_INVALID_FORMAT_YEAR, stringYear);
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
    public static Integer inputMonth() {
        int month = 1;
        boolean isMonthSet = false;
        while (!isMonthSet) {
            String stringMonth = MainApp.getScanner().nextLine();
            if (stringMonth.equals(AppLiteral.BACK_CMD_FULL_CAST) ||
                    stringMonth.equals(AppLiteral.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            }
            try {
                month = Integer.parseInt(stringMonth);
                if (isValidMonth(month)) {
                    isMonthSet = true;
                }
            } catch(NumberFormatException exception) {
                System.out.printf(MSG_INVALID_FORMAT_MONTH, stringMonth);
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
            if (stringDay.equals(AppLiteral.BACK_CMD_FULL_CAST) ||
                    stringDay.equals(AppLiteral.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            }
            try {
                day = Integer.parseInt(stringDay);
                if (isValidDay(year, month, day)) {
                    isDaySet = true;
                }
            } catch(NumberFormatException exception) {
                System.out.printf(MSG_INVALID_FORMAT_DAY, stringDay);
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
        boolean result = false;
        try {
            LocalDate.of(1970, month, 1);
            result = true;
        } catch (DateTimeException exception) {
            System.out.printf(MSG_INVALID_NUMERIC_FORMAT_MONTH, month);
        }
        return result;
    }

    /**
     * Based on the entered year and month, validates the entered day accordingly.
     * @param year year in integer format
     * @param month month (month number) in integer format
     * @param day day in integer format
     * @return true if day is valid, false if day is invalid
     */
    private static boolean isValidDay(int year, int month, int day) {
        boolean result = false;
        try {
            LocalDate.of(year, month, day);
            result = true;
        } catch (DateTimeException exception) {
            System.out.printf(MSG_INVALID_NUMERIC_FORMAT_DAY, day);
        }
        return result;
    }
}
