package edu.kirkwood.controller;

import edu.kirkwood.model.BloodPressure;

import static edu.kirkwood.view.Messages.loganGoodbye;
import static edu.kirkwood.view.Messages.loganGreet;
import static edu.kirkwood.view.UIUtility.displayError;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getString;

public class BloodPressureCalculator {
    public static void start() {
        loganGreet();

        while (true) {
            String input = getString("Enter blood pressure (or 'q' to return to menu): ");
            if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
                break;
            }
            // Validate user input
            BloodPressure bloodPressure = null;
            try {
                bloodPressure = splitPressure(input);
            } catch (Exception e) {
                displayError(e.getMessage() );
                continue;
            }
            // Perform the calculation
            int meanArterialPressure = bloodPressure.calculateMAP();
            String mapAlarm = "";
            if (meanArterialPressure < 65) {
                mapAlarm = "ALERT: MAP low";
            } else if (meanArterialPressure > 100) {
                mapAlarm = "ALERT: MAP high";
            }
            // Output
            System.out.println(bloodPressure + " ("+ meanArterialPressure +") " + mapAlarm);
        }
        loganGoodbye();
        pressEnterToContinue();
    }

    /**
     * Converts a string blood pressure to a BloodPressure object
     * @param input The string representation of a blood pressure
     * @return The input as a BloodPressure object
     */
    public static BloodPressure splitPressure(String input) {
        // Remove the non-numeric characters. I left the - sign to allow the errors to bubble up from
        // the BloodPressure class
        // source for regex: https://stackoverflow.com/a/10372914
        input = input.replaceAll("[^0-9/.-]", "");

        // Make sure I didn't break anything cleaning the input with the regex
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Invalid input. Please enter blood pressure in the format '120/80'.");
        }

        // Split the input
        String[] pressures = input.split("/");
        // There should always be a systolic and diastolic pressure, so the length of the pressures array
        // should never not be two.
        if (pressures.length != 2) {
            throw new IllegalArgumentException("Invalid format. Please check your input and try again." +
                    " See example above.");
        }
        if (pressures[0].equals("")) {
            throw new IllegalArgumentException("Format issue, please check your input and try again." +
                    " See example above.");
        }

        String systolicStr = pressures[0].trim();
        String diastolicStr = pressures[1].trim();

        int systolic;
        int diastolic;
        // Parse systolic and diastolic from pressures
        try {
            if (systolicStr.contains(".") || diastolicStr.contains(".")) {
                throw new IllegalArgumentException("Systolic and diastolic must be whole numbers.");
            }
            systolic = Integer.parseInt(systolicStr);
            diastolic = Integer.parseInt(diastolicStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Both systolic and diastolic must be valid integers");
        }

        return new BloodPressure(systolic, diastolic);
    }


}
