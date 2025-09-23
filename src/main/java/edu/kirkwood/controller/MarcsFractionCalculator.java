package edu.kirkwood.controller;

import edu.kirkwood.model.Fraction;

import static edu.kirkwood.view.Messages.*;
import static edu.kirkwood.view.UIUtility.displayError;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getString;

public class MarcsFractionCalculator {
    public static void start() {
        marcGreet();
        while(true) {
            String input = getString("Enter your equation (or 'q' to quit): ");
            if(input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
                break;
            }
            // Validate the input
            String[] parts = null;
            try {
                parts = splitInput(input);
            } catch (IllegalArgumentException e) {
                displayError(e.getMessage());
                continue; // Restart the loop
            }

            String fraction1Str = parts[0];
            String operator = parts[1];
            String fraction2Str = parts[2];

            Fraction f1 = null;
            Fraction f2 = null;
            try {
                f1 = parseFraction(fraction1Str);
                f2 = parseFraction(fraction2Str);
            } catch (IllegalArgumentException e) {
                displayError(e.getMessage());
                continue; // Restart the loop
            }

            // Perform mathematical operation
            Fraction result = null;
            switch (operator) {
                case "+":
                    result = f1.add(f2);
                    break;
                case "-":
                    result = f1.subtract(f2);
                    break;
                case "*":
                    result = f1.multiply(f2);
                    break;
                case "/":
                    result = f1.divide(f2);
                    break;
            }

            // Display output
            System.out.printf("%s %s %s = %s%n%n", f1.toString(), operator, f2.toString(), result.toString());

        }
        marcGoodbye();
        pressEnterToContinue();
    }

    /**
     * Split the user input into three parts: fraction 1, operator, fraction 2
     *
     * @param input the raw input string from the user
     * @return String[] array with three Strings
     * @throws IllegalArgumentException if input or operator are invalid
     */
    public static String[] splitInput(String input) {
        String operator = "";
        int operatorIndex = -1;
        if(input.contains(" + ")) {
            operator = "+";
            operatorIndex = input.indexOf(" + ");
        } else if(input.contains(" - ")) {
            operator = "-";
            operatorIndex = input.indexOf(" - ");
        } else if(input.contains(" * ")) {
            operator = "*";
            operatorIndex = input.indexOf(" * ");
        } else if(input.contains(" / ")) {
            operator = "/";
            operatorIndex = input.indexOf(" / ");
        }

        if(operatorIndex == -1) {
            throw new IllegalArgumentException("Invalid format. Ensure operator (+, -, *, /) has space on both sides.");
        }

        String fraction1 = input.substring(0, operatorIndex).trim();
        String fraction2 = input.substring(operatorIndex + 3).trim();

        if(fraction1.isEmpty()) {
            throw new IllegalArgumentException("First fraction is required");
        }
        if(fraction2.isEmpty()) {
            throw new IllegalArgumentException("Second fraction is required");
        }

        return new String[]{fraction1, operator, fraction2};
    }

    /**
     * Converts a String into a Fraction object. Handles whole numbers, improper and proper fractions, and mixed numbers
     * @param str The String representing a fraction to be parsed
     * @return a Fraction object representing the parsed String
     * @throws IllegalArgumentException if the String's fraction format is not valid.
     */
    public static Fraction parseFraction(String str) {
        Fraction result = null;
        if(str.contains(" ")) { // Handle Mixed numbers (1 2/3 or -2 1/4)
            String[] parts = str.split(" ", 2);
            int whole = 0;
            // Validate the whole number part
            try {
                whole = Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid whole number: '" + str + "' (the correct format is '1 2/3' or '-1 2/3'");
            }
            // Validate if the fraction part is missing the forward slash
            if(!parts[1].contains("/")) {
                throw new IllegalArgumentException("Invalid mixed number format: '" + str + "' (the correct format is '1 2/3' or '-1 2/3'");
            }

            String[] fractionParts = parts[1].split("/");
            int num = 0;
            int den = 0;
            // Validate if the numerator or denominator are not numbers
            try {
                num = Integer.parseInt(fractionParts[0]);
            } catch(NumberFormatException e) {
                throw new IllegalArgumentException("Invalid numerator: '" + str + "'");
            }
            try {
                den = Integer.parseInt(fractionParts[1]);
            } catch(NumberFormatException e) {
                throw new IllegalArgumentException("Invalid denominator: '" + str + "'");
            }

            // Convert the results to an improper fraction
            if(whole >= 0) {
                // Invalid example "2 -1/3 or 2 1/-3)
                if(num < 0 || den < 0) {
                    throw new IllegalArgumentException("Invalid mixed number format: '" + str + "' (the correct format is '1 2/3' or '-1 2/3'");
                }
                num = whole * den + num;
            } else if(whole < 0) {
                num = whole * den - num;
            }

            try {
                result = new Fraction(num, den);
            } catch(ArithmeticException e) {
                // Will have an error if denominator is 0
                throw new IllegalArgumentException(e.getMessage() + ": '" + str + "'");
            }


        } else if(str.contains("/")) { // Handle proper and improper fraction (3/4 or -5/2)
            String[] parts = str.split("/");
            int num = 0;
            int den = 0;
            try {
                num = Integer.parseInt(parts[0]);
            } catch(NumberFormatException e) {
                throw new IllegalArgumentException("Invalid numerator: '" + str + "'");
            }
            try {
                den = Integer.parseInt(parts[1]);
            } catch(NumberFormatException e) {
                throw new IllegalArgumentException("Invalid denominator: '" + str + "'");
            }

            try {
                result = new Fraction(num, den);
            } catch(ArithmeticException e) {
                // Will have an error if denominator is 0
                throw new IllegalArgumentException(e.getMessage() + ": '" + str + "'");
            }

        } else { // Handle whole numbers (5 or -1)
            int whole = 0;
            try {
                whole = Integer.parseInt(str);
            } catch(NumberFormatException e) {
                throw new IllegalArgumentException("Invalid whole number: '" + str + "'");
            }
            result = new Fraction(whole, 1);
        }
        return result;
    }


}










