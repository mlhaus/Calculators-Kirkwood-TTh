package edu.kirkwood.view;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Helpers {
    // Emilia
    /** This method returns a bool for if a string isn't null or empty.
     * @param str The string needing validation.
     * @return Returns true if string is not null or empty, false if otherwise.
     */
    public static boolean isValidString(String str) {
        return str != null && !str.equals("");
    }

    public static String round(double number, int numDecPlaces) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(number));
        bigDecimal = bigDecimal.setScale(numDecPlaces, RoundingMode.HALF_UP).stripTrailingZeros();
        return bigDecimal.toString();
    }

    // Celeste
    /**
     * Formats a given double value into USD
     * @param amt The double value to be formatted
     * @return A String formatted into USD
     */
    public static String toCurrency(double amt) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(amt);
    }

    public static String formatDateLong(LocalDate date) {
        DateTimeFormatter dateFormatOutput = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        return dateFormatOutput.format(date);
    }

    public static String formatDateShort(LocalDate date) {
        DateTimeFormatter dateFormatOutput = DateTimeFormatter.ofPattern("M/d/yyyy");
        return dateFormatOutput.format(date);
    }

    public static boolean isDateInThePast(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        return date.isBefore(LocalDate.now()); // Check if the date is before today
    }

    public static boolean isDateInRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        if (date == null || startDate == null || endDate == null) {
            throw new IllegalArgumentException("None of the dates can be null");
        }

        return (date.isEqual(startDate) || date.isAfter(startDate)) &&
                (date.isEqual(endDate) || date.isBefore(endDate));
    }
}