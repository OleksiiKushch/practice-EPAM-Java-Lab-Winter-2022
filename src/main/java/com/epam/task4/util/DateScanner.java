package com.epam.task4.util;

import com.epam.task4.MainApp;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Class for reading data about dates from the console with built-in validation
 *
 * @author Oleksii Kushch
 */
public class DateScanner {
    public static Integer inputYear() {
        int year = 1;
        boolean isYearSet = false;
        while (!isYearSet) {
            String stringYear = MainApp.SCANNER.nextLine();
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

    public static Integer inputMonth(int year) {
        int month = 1;
        boolean isMonthSet = false;
        while (!isMonthSet) {
            String stringMonth = MainApp.SCANNER.nextLine();
            if (stringMonth.equals("--back") || stringMonth.equals("-b")) {
                return null;     // abort the entire operation
            }
            try {
                month = Integer.parseInt(stringMonth);
                if (isValidMonth(year, month)) {
                    isMonthSet = true;
                }
            } catch(NumberFormatException exception) {
                System.out.println("Invalid format month '" + stringMonth + "', try again:");
            }
        }
        return month;
    }

    public static Integer inputDay(int year, int month) {
        int day = 1;
        boolean isDaySet = false;
        while (!isDaySet) {
            String stringDay = MainApp.SCANNER.nextLine();
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

    private static boolean isValidMonth(int year, int month) {
        try {
            LocalDate.of(year, month, 1);
        } catch (DateTimeException exception) {
            System.out.println("Invalid value for month of year (valid values 1 - 12): " + month + ", try again:");
            return false;
        }
        return true;
    }

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
