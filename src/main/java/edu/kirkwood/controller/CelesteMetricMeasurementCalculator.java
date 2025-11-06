package edu.kirkwood.controller;

import edu.kirkwood.model.CelesteMetricMeasurement;

import static edu.kirkwood.view.Messages.calculatorEnd;
import static edu.kirkwood.view.Messages.celesteGreeting;
import static edu.kirkwood.view.UIUtility.*;
import static edu.kirkwood.view.UserInput.getInt;
import static edu.kirkwood.view.UserInput.getString;

public class CelesteMetricMeasurementCalculator {
    public static void start() {
        celesteGreeting();
        String[] conversionChoices = {
                "Millimeter",
                "Centimeter",
                "Meter",
                "Kilometer"
        };
        String title = "Conversion Targets";
        boolean loop = true;
        CelesteMetricMeasurement userMeasurement;
        String input;
        while (loop) {
            input = getString("Enter a metric measurement or enter \"Quit\" to return to the main menu");
            if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("q")) {
                loop = false;
                continue;
            }
            // validate measurement
            try {
                userMeasurement = parseInput(splitInput(input));
            } catch (IllegalArgumentException e) {
                displayError(e.getMessage());
                continue;
            }
            // get conversion
            String conversionChoice = "";
            printMenu(title, conversionChoices);
            int selection = getInt("Enter your choice: ", true, 1, conversionChoices.length);
            switch (selection) {
                case 1:
                    conversionChoice = "mm";
                    break;
                case 2:
                    conversionChoice = "cm";
                    break;
                case 3:
                    conversionChoice = "m";
                    break;
                case 4:
                    conversionChoice = "km";
                    break;
                default:
                    displayWarning("Invalid selection, enter a number between 1 and " + (conversionChoices.length));
            }
            // convert
            CelesteMetricMeasurement conversion = convert(userMeasurement, conversionChoice);

            // display
            displayConversion(userMeasurement, conversion);

        }
        calculatorEnd();
        pressEnterToContinue();
    }

    /**
     * Splits the input into the format: [number, unit]
     * @param input String The entered string
     * @return String[] An array with length of 2 containing the number and unit
     * @throws IllegalArgumentException If either the number or unit is invalid
     */
    public static String[] splitInput(String input) {
        String[] result = null;

        // Regex help from https://www.baeldung.com/java-split-string-keep-delimiters
        result = input.split("((?=[a-z]))", 2);
        if (result.length != 2) {
            throw new IllegalArgumentException("Invalid measurement. Make sure the input is in the format [number] [unit]");
        }
        result[0] = result[0].trim();
        result[1] = result[1].trim();
        try {
            Double.parseDouble(result[0]);
        } catch (NumberFormatException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid measurement. Make sure the input begins with a number.");
        }
        if (!(result[1].equals("mm") || result[1].equals("cm") || result[1].equals("m") || result[1].equals("km"))) {
            throw new IllegalArgumentException("Invalid measurement. Make sure the unit is one of the following abbreviations: 'mm', 'cm', 'm', 'km'");
        }

        return result;
    }

    /**
     * Parses a two-length String array into a CelesteMetricMeasurement object
     * @param input String[] The split array that constructs the object
     * @return CelesteMetricMeasurement The parsed measurement
     */
    public static CelesteMetricMeasurement parseInput(String[] input) {
        CelesteMetricMeasurement result = new CelesteMetricMeasurement();

        if (input.length != 2) {
            throw new IllegalArgumentException("Invalid measurement. Make sure the input is in the format [number] [unit]");
        }

        try {
            result.setValue(Double.parseDouble(input[0]));
        } catch (NumberFormatException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid measurement. Make sure the input begins with a number.");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid measurement. Number cannot be negative.");
        }
        if (!(input[1].equals("mm") || input[1].equals("cm") || input[1].equals("m") || input[1].equals("km"))) {
            throw new IllegalArgumentException("Invalid measurement. Make sure the unit is one of the following abbreviations: 'mm', 'cm', 'm', 'km'");
        }
        result.setUnit(input[1]);

        return result;
    }

    /**
     * Converts the parsed CelesteMetricMeasurement to the chosen unit
     * @param toBeConverted CelesteMetricMeasurement the parsed input
     * @param targetUnit String the chosen unit
     * @return CelesteMetricMeasurement the converted result
     * @throws IllegalArgumentException if the targetUnit is invalid
     */
    public static CelesteMetricMeasurement convert(CelesteMetricMeasurement toBeConverted, String targetUnit) {
        CelesteMetricMeasurement result = new CelesteMetricMeasurement();

        switch (targetUnit) {
            case "mm":
                result = toBeConverted.toMM();
                break;
            case "cm":
                result = toBeConverted.toCM();
                break;
            case "m":
                result = toBeConverted.toM();
                break;
            case "km":
                result = toBeConverted.toKM();
                break;
            default:
                throw new IllegalArgumentException("Invalid target unit.");
        }

        return result;
    }

    public static void displayConversion(CelesteMetricMeasurement original, CelesteMetricMeasurement converted) {
        System.out.println(original.toString() + " is "  + converted.toString());
    }
}
