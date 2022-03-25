package com.epam.task4.util;

import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.command.ViewOrderByNearestDateCmd;
import com.epam.task4.controller.command.ViewOrdersFromToByDateCmd;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Class for reading data about dates from the console with built-in validation.
 * <p>
 * Are directly called in the {@code execute()} method in the following classes:
 * @see ViewOrderByNearestDateCmd
 * @see ViewOrdersFromToByDateCmd
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
            String stringYear = MainApp.getContext().getScanner().nextLine();
            if (stringYear.equals(ShopLiterals.BACK_CMD_FULL_CAST) || stringYear.equals(ShopLiterals.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            }
            try {
                year = Integer.parseInt(stringYear);
                isYearSet = true;
            } catch(NumberFormatException exception) {
                System.out.printf(ShopLiterals.MSG_INVALID_FORMAT_YEAR, stringYear);
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
            String stringMonth = MainApp.getContext().getScanner().nextLine();
            if (stringMonth.equals(ShopLiterals.BACK_CMD_FULL_CAST) || stringMonth.equals(ShopLiterals.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            }
            try {
                month = Integer.parseInt(stringMonth);
                if (isValidMonth(month)) {
                    isMonthSet = true;
                }
            } catch(NumberFormatException exception) {
                System.out.printf(ShopLiterals.MSG_INVALID_FORMAT_MONTH, stringMonth);
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
            String stringDay = MainApp.getContext().getScanner().nextLine();
            if (stringDay.equals(ShopLiterals.BACK_CMD_FULL_CAST) || stringDay.equals(ShopLiterals.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            }
            try {
                day = Integer.parseInt(stringDay);
                if (isValidDay(year, month, day)) {
                    isDaySet = true;
                }
            } catch(NumberFormatException exception) {
                System.out.printf(ShopLiterals.MSG_INVALID_FORMAT_DAY, stringDay);
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
            System.out.printf(ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_MONTH, month);
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
            System.out.printf(ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_DAY, day);
        }
        return result;
    }
}
