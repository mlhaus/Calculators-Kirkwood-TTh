package edu.kirkwood.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static edu.kirkwood.view.Helpers.formatDateLong;
import static edu.kirkwood.view.Helpers.isValidString;
import static edu.kirkwood.view.UIUtility.displayError;


public class UserInput {

    private static Scanner scanner = new Scanner(System.in);

    public static Integer getInt(String prompt) {
        return getInt(prompt, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static Integer getInt(String prompt, boolean required) {
        return getInt(prompt, required, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static Integer getInt(String prompt, boolean required, int min) {
        return getInt(prompt, required, min, Integer.MAX_VALUE);
    }

    public static int getInt(String prompt, boolean required, int min, int max) {
        int value = 0;

        String minMax = "";
        // if min is set and max is not set
        if(min != Integer.MIN_VALUE && max == Integer.MAX_VALUE) {
            minMax = String.format(" [minimum %d]", min);
        }
        // if min and max are both set
        if(min != Integer.MIN_VALUE && max != Integer.MAX_VALUE) {
            minMax = String.format(" [between %d and %d]", min, max);
        }

        while(true) {
            System.out.print(prompt + minMax + (required ? " (*)" : "") + ": ");
            String valueStr = scanner.nextLine();
            try {
                value = Integer.parseInt(valueStr);
            } catch (NumberFormatException e) {
                if(!required) {
                    return Integer.MIN_VALUE;
                } else {
                    displayError("Invalid integer");
                    continue;
                }
            }

            if(value < min) {
                displayError("Value too low");
            } else if(value > max) {
                displayError("Value too high");
            } else {
                break;
            }
        }
        return value;
    }

    public static String getString(String prompt) {
        return getString(prompt, true);
    }

    public static String getString(String prompt, boolean required) {
        String value = "";
        while(true) {
            System.out.print(prompt + (required ? " (*)" : "") + ": ");
            value = scanner.nextLine().trim();
            if(required && !isValidString(value)) {
                displayError("Input required");
            } else {
                break;
            }
        }
        return value;
    }

    public static boolean getBoolean(String prompt) {
        return getBoolean(prompt, true);
    }

    public static boolean getBoolean(String prompt, boolean required) {
        boolean value = true;
        while(true) {
            String valueStr = getString(prompt + " [y/n]", required);
            if(required && !(valueStr.equalsIgnoreCase("y") ||
                    valueStr.equalsIgnoreCase("n") ||
                    valueStr.equalsIgnoreCase("yes") ||
                    valueStr.equalsIgnoreCase("no"))
            ) {
                displayError("Invalid input");
            } else {
                value = valueStr.equalsIgnoreCase("y") || valueStr.equalsIgnoreCase("yes");
                break;
            }
        }
        return value;
    }

    public static double getDouble(String prompt) {
        return getDouble(prompt, true, -Double.MAX_VALUE, Double.MAX_VALUE);
    }

    public static double getDouble(String prompt, boolean required) {
        return getDouble(prompt, required, -Double.MAX_VALUE, Double.MAX_VALUE);
    }

    public static double getDouble(String prompt, boolean required, int min) {
        return getDouble(prompt, required, min, Double.MAX_VALUE);
    }

    public static double getDouble(String prompt, boolean required, double min, double max) {
        double value = 0;

        String minMax = "";
        // if min is set and max is not set
        if(min != -Double.MAX_VALUE && max == Double.MAX_VALUE) {
            minMax = String.format(" [minimum %.1f]", min);
        }
        // if min and max are both set
        if(min != -Double.MAX_VALUE && max != Double.MAX_VALUE) {
            minMax = String.format(" [between %.1f and %.1f]", min, max);
        }

        while(true) {
            System.out.print(prompt + minMax + (required ? " (*)" : "") + ": ");
            String valueStr = scanner.nextLine();
            try {
                value = Double.parseDouble(valueStr);
            } catch (NumberFormatException e) {
                if(!required) {
                    return -Double.MAX_VALUE;
                } else {
                    displayError("Invalid number");
                    continue;
                }
            }

            if(value < min) {
                displayError("Value too low");
            } else if(value > max) {
                displayError("Value too high");
            } else {
                break;
            }
        }
        return value;
    }

    public static LocalDate getDate(String prompt) {
        return getDate(prompt, true);
    }

    public static LocalDate getDate(String prompt, boolean required) {
        LocalDate date = null;
        while(true) {
            String dateStr = getString(prompt + " [MM/DD/YYYY]", required);
            try {
                DateTimeFormatter dateFormatInput = DateTimeFormatter.ofPattern("M/d/yyyy");
                date = LocalDate.parse(dateStr, dateFormatInput);
                break;
            } catch(DateTimeParseException e) {
                if(!required) {
                    return LocalDate.MIN;
                } else {
                    displayError("Invalid date");
                }
            }
        }
        return date;
    }
}