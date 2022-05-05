package com.epam.task4.util;

import com.epam.task4.MainApp;
import com.epam.task4.constants.ShopLiterals;
import com.epam.task4.controller.command.ViewOrderByNearestDateCmd;
import com.epam.task4.controller.command.ViewOrdersFromToByDateCmd;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class for reading data about dates from the console with built-in validation.
 * <p>
 * Are directly called in the {@code execute()} method in the following classes:
 * @see ViewOrderByNearestDateCmd
 * @see ViewOrdersFromToByDateCmd
 * @author Oleksii Kushch
 */
public class DateConsoleScanner {
    private static final int MOCK_VALUE_FOR_YEAR_AND_DAY = 1;
    private static final int MOCK_VALUE_FOR_HOUR_MINUTE_SECOND = 0;

    private final Scanner scanner;

    public DateConsoleScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public LocalDateTime interactiveConsoleInputDate(
            String msgAppealForInputYear, String msgAppealForInputMonth, String msgAppealForInputDay) {
        MainApp.printMessage(msgAppealForInputYear);
        Integer year = inputYear();
        if (Objects.isNull(year)) {
            return null; // abort the entire operation
        }
        MainApp.printMessage(msgAppealForInputMonth);
        Integer month = inputMonth();
        if (Objects.isNull(month)) {
            return null; // abort the entire operation
        }
        MainApp.printMessage(msgAppealForInputDay);
        Integer day = inputDay(year, month);
        if (Objects.isNull(day)) {
            return null; // abort the entire operation
        }
        return LocalDateTime.of(year, month, day, MOCK_VALUE_FOR_HOUR_MINUTE_SECOND, MOCK_VALUE_FOR_HOUR_MINUTE_SECOND, MOCK_VALUE_FOR_HOUR_MINUTE_SECOND);
    }

    /**
     * Reading data (year) from the console with built-in validation.
     * <p>
     * Noted: return {@code null} if needed abort the entire operation
     * (method execute in the corresponding command classes, see the detailed description of this class)
     * @return year or {@code null} if abort the entire operation
     */
    public Integer inputYear() {
        int year = 1;
        boolean isYearSet = false;
        while (!isYearSet) {
            String stringYear = scanner.nextLine();
            if (stringYear.equals(ShopLiterals.BACK_CMD_FULL_CAST) || stringYear.equals(ShopLiterals.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            }
            try {
                year = Integer.parseInt(stringYear);
                isYearSet = true;
            } catch(NumberFormatException exception) {
                MainApp.printWarning(ShopLiterals.MSG_INVALID_FORMAT_YEAR, stringYear);
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
    public Integer inputMonth() {
        int month = 1;
        boolean isMonthSet = false;
        while (!isMonthSet) {
            String stringMonth = scanner.nextLine();
            if (stringMonth.equals(ShopLiterals.BACK_CMD_FULL_CAST) || stringMonth.equals(ShopLiterals.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            }
            try {
                month = Integer.parseInt(stringMonth);
                if (isValidMonth(month)) {
                    isMonthSet = true;
                }
            } catch(NumberFormatException exception) {
                MainApp.printWarning(ShopLiterals.MSG_INVALID_FORMAT_MONTH, stringMonth);
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
    public Integer inputDay(int year, int month) {
        int day = 1;
        boolean isDaySet = false;
        while (!isDaySet) {
            String stringDay = scanner.nextLine();
            if (stringDay.equals(ShopLiterals.BACK_CMD_FULL_CAST) || stringDay.equals(ShopLiterals.BACK_CMD_SHORT_CAST)) {
                return null;     // abort the entire operation
            }
            try {
                day = Integer.parseInt(stringDay);
                if (isValidDay(year, month, day)) {
                    isDaySet = true;
                }
            } catch(NumberFormatException exception) {
                MainApp.printWarning(ShopLiterals.MSG_INVALID_FORMAT_DAY, stringDay);
            }
        }
        return day;
    }

    /**
     * Validates the entered month.
     * @param month month (month number) in integer format
     * @return true if month is valid, false if month is invalid
     */
    private boolean isValidMonth(int month) {
        boolean result = false;
        try {
            LocalDate.of(MOCK_VALUE_FOR_YEAR_AND_DAY, month, MOCK_VALUE_FOR_YEAR_AND_DAY);
            result = true;
        } catch (DateTimeException exception) {
            MainApp.printWarning(ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_MONTH, month);
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
    private boolean isValidDay(int year, int month, int day) {
        boolean result = false;
        try {
            LocalDate.of(year, month, day);
            result = true;
        } catch (DateTimeException exception) {
            MainApp.printWarning(ShopLiterals.MSG_INVALID_NUMERIC_FORMAT_DAY, day);
        }
        return result;
    }
}
