package edu.kirkwood.controller;


import edu.kirkwood.model.PythagoreanTheorem;

import static edu.kirkwood.view.Messages.emiliaGoodbye;
import static edu.kirkwood.view.Messages.emiliaGreet;
import static edu.kirkwood.view.UIUtility.displayError;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getString;

public class EmiliasPythagoreanTheorem {

    /** This is the starting point of all Pythagorean theorem calculation,
     *
     */
    public static void start() {
        emiliaGreet();
        while(true) {
            String input = getString("Enter your two sides and optional decimal place (or 'q' to quit): ");
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

            String side1 = parts[0];
            String side2 = parts[1];
            String places = parts[2];

            PythagoreanTheorem pt = null;
            try {
                pt = parseArr(side1, side2, places);
            } catch (IllegalArgumentException e) {
                displayError(e.getMessage());
                continue; // Restart the loop
            }


            System.out.printf(pt.toString()+"\n\n");

        }
        emiliaGoodbye();
        pressEnterToContinue();
    }

    /** Splits input in two, characters before a space, and characters after a space.
     *
     * @param input the initial input from the user
     * @return String array with 2 strings
     */
    public static String[] splitInput(String input) {
        String[] result = {"","",""};
        int spaceIndex = -1;
        int secondSpaceIndex = -2;
        boolean spaceFound = false;
        boolean secondSpaceFound = false;
        for (int i = 0; i<input.length(); i++) {
            if (!spaceFound && input.charAt(i) == ' ') {
                spaceIndex = i;
                spaceFound = true;
            } else if (spaceFound && input.charAt(i) == ' ') {
                secondSpaceIndex = i;
                secondSpaceFound = true;
            }

        }


        if(input.isEmpty()) {
            throw new IllegalArgumentException("Input must not be empty");
        }

        if (spaceIndex == -1) {
            throw new IllegalArgumentException("A space between two separate numbers is required.");
        }

        result[0] = input.substring(0, spaceIndex).trim();

        if (!secondSpaceFound) {
            result[1] = input.substring(spaceIndex + 1).trim();
            result[2] = "2";
        } else {
            result[1] = input.substring(spaceIndex + 1, secondSpaceIndex).trim();
            result[2] = input.substring(secondSpaceIndex + 1).trim();
        }

        if (result[0].isEmpty() || Double.isNaN(Double.parseDouble(result[0]))) {
            throw new IllegalArgumentException("A valid 1st side is required.");
        }
        if (result[1].isEmpty() || Double.isNaN(Double.parseDouble(result[1]))) {
            throw new IllegalArgumentException("A valid second side is required.");
        }
        if (secondSpaceFound && result[2].isEmpty() || Double.isNaN(Double.parseDouble(result[2]))) {
            throw new IllegalArgumentException("Decimal places must be in valid format");
        }


        return new String[]{result[0], result[1], result[2]};
    }

    /** Takes the 3 strings from the splitInput string[] to convert to 2 doubles and an integer.
     * @param str1 the string representing side 1
     * @param str2 the string representing side 2
     * @param str3 string repesenting decimal places
     * @return Returns the PythagoreanTheorem object consisting of 2 doubles from both inputed strings.
     */
    public static PythagoreanTheorem parseArr(String str1, String str2, String str3) {
        PythagoreanTheorem result = null;
        if (Double.isNaN(Double.parseDouble(str1)) || Double.isNaN(Double.parseDouble(str2)) ) {
            throw new IllegalArgumentException("Input must not contain junk characters");
        }
        if (Double.parseDouble(str1) == 0 || Double.parseDouble(str2) == 0) {
            throw new IllegalArgumentException("Input must not contain zeros.");
        }
        if (Double.parseDouble(str1) < 0 || Double.parseDouble(str2) < 0) {
            throw new IllegalArgumentException("Input must only have positive values.");
        }

        if (str3 != null && Integer.parseInt(str3) < 0) {
            throw new IllegalArgumentException("You cannot have negative decimal places.");
        }
        if (Double.isNaN(Double.parseDouble(str3))) {
            throw new IllegalArgumentException("You must have decimal places in proper format.");
        }

        double side1 = Double.parseDouble(str1);
        double side2 = Double.parseDouble(str2);
        int places;
        if (str3 != null && ( str3.isEmpty() || Double.isNaN(Double.parseDouble(str3)))) {
            places = 2;
        } else {
            places = Integer.parseInt(str3);
        }
        result = new PythagoreanTheorem(side1, side2, places);
//        result = new PythagoreanTheorem(side1, side2);
        return result;
    }


}
