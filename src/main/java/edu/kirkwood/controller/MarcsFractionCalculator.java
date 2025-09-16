package edu.kirkwood.controller;

import static edu.kirkwood.view.Messages.*;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getString;

public class MarcsFractionCalculator {
    public static void start() {
        marcGreet();
        while(true) {
            String value = getString("Enter your equation (or 'q' to quit): ");
            if(value.equalsIgnoreCase("q") || value.equalsIgnoreCase("quit")) {
                break;
            }
            // Todo: Validate the input
            // Todo: Perform mathematical operation
            // Todo: Display output
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
}










