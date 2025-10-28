package edu.kirkwood.controller;

import edu.kirkwood.model.Temperature;

import static edu.kirkwood.view.Messages.mouftaouGoodbye;
import static edu.kirkwood.view.Messages.mouftaouGreet;
import static edu.kirkwood.view.UIUtility.*;
import static edu.kirkwood.view.UserInput.getString;

/**
 * TemperatureCalculator is a class that provides methods to parse
 * and validate user input, and perform temperature calculations such as conversion, addition,
 * and substraction based on Temperature model.
 */

public class TemperatureCalculator {

    public static void start() {
        mouftaouGreet();
        TemperatureCalculator calculator = new TemperatureCalculator();
        while(true) {
            String input = getString("Enter your equation (or 'q' to quit): ");
            if(input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
                break;
            }
            try {
                if(input.trim().toLowerCase().startsWith("convert ")) {
                    handleConversion(calculator, input);
                }else {
                    // Handle addition or substraction
                    handleArithmetic(calculator, input);
                }
            }catch(IllegalArgumentException e) {
                displayError(e.getMessage());
            }

        }
        mouftaouGoodbye();
        pressEnterToContinue();
    }

    /**
     * Handles a user command to convert a temperature to a different scale.
     * @param calculator The TemperatureCalculator instance to use for parsing and conversion
     * @param input the raw input string from the user.
     * @throws IllegalArgumentException if input is an invalid format.
     */

    private static void handleConversion(TemperatureCalculator calculator, String input) {
        try {
            String[] parts = input.trim().split("\\s+");
            if(parts.length != 5 || !parts[3].equalsIgnoreCase("to")) {
                throw new IllegalArgumentException("");
            }
            String tempStr = parts[1] + " " + parts[2];
            String targetScale = parts[4];

            Temperature temp = calculator.parseTemperatureInput(tempStr);
            String convertedDegree = calculator.convertTemperature(temp, targetScale);

            System.out.printf("%s = %s%n", temp.toString(), convertedDegree);
        }catch (IllegalArgumentException e) {
            displayMessage(e.getMessage());
        }
    }

    /**
     * Handles user commands for arithmetic operations (addition, substraction) between two temperatures.
     * @param calculator The TemperatureCalculator instance for parsing and computation
     * @param input the raw input string from the user.
     * @throws IllegalArgumentException if format is invalid or operands are incorrect.
     */

    private static void handleArithmetic(TemperatureCalculator calculator, String input) {
        String operator = "";
        int operatorIndex = -1;
        if (input.contains(" + ")) {
            operator = "+";
            operatorIndex = input.indexOf(" + ");
        }else if(input.contains(" - ")) {
            operator = "-";
            operatorIndex = input.indexOf(" - ");
        }
        if(operatorIndex == -1) {
            throw new IllegalArgumentException("Invalid format. Ensure operator (+ or -) has a single space both side.");
        }

        String before = input.substring(0, operatorIndex);
        String after = input.substring(operatorIndex + 3);

        if(before.endsWith(" ") || after.startsWith(" ")) {
            throw new IllegalArgumentException("Invalid format. Too many spaces around operator.");
        }
        String temperature1Str = before.trim();
        String operatorSymbol = operator;
        String temperature2Str = after.trim();
        if(temperature1Str.isEmpty() || temperature2Str.isEmpty()) {
            throw new IllegalArgumentException("Both temperature values are required.");
        }

        // Strict validation of degree + scale format
        validateTemperatureFormat(temperature1Str);
        validateTemperatureFormat(temperature2Str);

        Temperature t1;
        Temperature t2;

        try {
            t1 = calculator.parseTemperatureInput(temperature1Str);
            t2 = calculator.parseTemperatureInput(temperature2Str);
        } catch (IllegalArgumentException e) {
            displayError(e.getMessage());
            return;
        }

        // Perform mathematical operation
        Temperature result = null;
        switch (operator) {
            case "+":
                result = t1.add(t2);
                break;
            case "-":
                result = t1.subtract(t2);
                break;
        }

        // Display output
        System.out.printf("%s %s %s = %s%n%n", t1.toString(), operator, t2.toString(), result.toString());

    }

    /**
     * Validates a temperature value string
     * Ensures only one space between value and scale, value is a valid number, and scale exits.
     * @param operand The operand string to validate.
     * @throws IllegalArgumentException if operand is incorrectly formatted.
     */

    private static void validateTemperatureFormat(String operand) {
        // Check for multiple spaces
        if(operand.contains("  ")){
            throw new IllegalArgumentException("Each operand must have exactly one space between the value and the scale.");
        }
        String[] parts = operand.split(" ");
        if(parts.length != 2){
            throw new IllegalArgumentException("Invalid format. Temperature must be 'value scale' with a single space.");
        }

        // Validate that the degree part is a valid number
        try{
            Double.parseDouble(parts[0]);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("Invalid number: " + parts[0]);
        }

        // Validate that the scale is not empty
        if(parts[1].trim().isEmpty()){
            throw new IllegalArgumentException("Missing temperature scale.");
        }
    }

    /**
     * Parses a user input string and creates a Temperature object.
     * The expected input format is: "value scale" (20 F).
     *
     * @param input the user input string with number and scale
     * @return a Temperature object
     * @throws IllegalArgumentException if the input is invalid or improperly formatted.
     */

    public Temperature parseTemperatureInput(String input){
        if (input == null || input.trim().isEmpty()){
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        String[] parts = input.trim().split("\\s+");
        if (parts.length != 2){
            throw new IllegalArgumentException("Invalid format. Use 'value scale', e.g. '25 C'.");
        }

        double degree;
        try {
            degree = Double.parseDouble(parts[0]);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Invalid degree value: " + parts[0]);
        }

        String scale = parts[1];
        return new Temperature(degree, scale);
    }

    /**
     * Converts a given Temperature to the specified target scale.
     * @param temp the Temperature object to convert
     * @param targetScale The Target scale ("C", "F", or "K")
     * @return the converted temperature as a formatted string
     * @throws IllegalArgumentException if the scale is invalid
     */

    public String convertTemperature(Temperature temp, String targetScale){
        if (temp == null){
            throw new IllegalArgumentException("Temperature cannot be null");
        }
        return temp.convertTo(targetScale);
    }

    /**
     * Adds two temperatures objects together
     * The result is returned in the scale of the first temperature
     * @param t1 the first temperature
     * @param t2 the second temperature
     * @return the resulting temperature after addition
     * @throws IllegalArgumentException if any parameter is null.
     */

    public Temperature add(Temperature t1, Temperature t2){
        if (t1 == null || t2 == null){
            throw new IllegalArgumentException("T1 and T2 cannot be null");
        }
        return t1.add(t2);
    }

    /**
     * Substracts the second temperature from the first
     * @param t1 the first temperature
     * @param t2 the second temperature
     * @return the resulting temperature after substraction
     * @throws IllegalArgumentException if any parameter is null.
     */

    public Temperature subtract(Temperature t1, Temperature t2){
        if (t1 == null || t2 == null){
            throw new IllegalArgumentException("T1 and T2 cannot be null");
        }
        return t1.subtract(t2);
    }

}
