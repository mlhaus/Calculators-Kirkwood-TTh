package edu.kirkwood.controller;

import edu.kirkwood.model.Measurement;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static edu.kirkwood.view.MainMenu.showUnitsMenu;
import static edu.kirkwood.view.Messages.asleGoodbye;
import static edu.kirkwood.view.Messages.asleGreet;
import static edu.kirkwood.view.UIUtility.*;
import static edu.kirkwood.view.UserInput.getInt;
import static edu.kirkwood.view.UserInput.getString;

public class AslesMeasurementCalculator {
    final static String[] units = {"Inches", "Feet", "Yards", "Miles", "Centimeters", "Meters", "Kilometers"};
    static ArrayList<Measurement> measurements = new ArrayList<>();
    /**
     * Runs Measurement Menu
     */
    public static void start() {
        asleGreet();
        //showAsleMenu();
        String[] menuItems = {
                "Create A Measurement",
                "View Measurement(s)",
                "Add Measurements",
                "Subtract Measurements",
                "Quit"
        };
        String menuTitle = "Asle's Measurement Menu";
        boolean doingCalculations = true;


            while (doingCalculations) {
                boolean creatingMeasurement = true;
                printMenu(menuTitle, menuItems);
                int choice = getInt("Choose an option", false, 1, menuItems.length);
                switch (choice) {
                    case 1:
                        while (creatingMeasurement) {
                            try {
                                // returns true or false whether a measurement is created or not, if not reruns create a measurement to "recreate measurement"
                                creatingMeasurement = AslesMeasurementCalculator.createAMeasurement();
                            } catch (IllegalArgumentException e) {
                                displayError(e.getMessage());
                            }
                        }
                        break;
                    case 2:
                        // shows a list of saved measurements
                        try {
                            AslesMeasurementCalculator.viewMeasurements(false);
                        } catch (NoSuchElementException e) {
                            displayError(e.getMessage());
                            pressEnterToContinue();
                        }
                        break;
                    case 3:
                        try {
                            AslesMeasurementCalculator.addMeasurement();
                        } catch (NoSuchElementException e) {
                            displayError(e.getMessage());
                            pressEnterToContinue();
                        }
                        break;
                    case 4:
                        try {
                            AslesMeasurementCalculator.subtractMeasurements();
                        } catch (NoSuchElementException e) {
                            displayError(e.getMessage());
                            pressEnterToContinue();
                        }
                        break;
                    default:
                        if (choice != 5){
                            displayError("The value you entered is not a valid choice.");
                        } else {
                            doingCalculations = false;
                        }


            }
        }

        asleGoodbye();
    }

    /**
     * takes a number representing length and a number representing a unit of measure from the user and creates a measurement
     * @return boolean returns false if measurement is created, returns true if measurement not created
     * @throws IllegalArgumentException if length input is invalid
     */
    public static boolean createAMeasurement() {
        boolean result = true;
        double length = 0.0;
        Measurement newMeasurement = new Measurement();
        while(result){
            String getLength = getString("Enter your measurement length [ex: 40.26] (or 'q' to quit): ");
            // taken from MarcsFractionCalculator
            if(getLength.equalsIgnoreCase("q") || getLength.equalsIgnoreCase("quit")) {
                result = false;
                return result;
            }
            // Validate input is a valid number and is greater than 0
            try {
                if(Double.parseDouble(getLength) <= 0){
                    throw new IllegalArgumentException("Number must be greater than zero.");
                    //displayError("Number must be greater than zero.");
                }
                length = Double.parseDouble(getLength);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid measurement length.");
                // displayError("Could not format number into a number with a decimal point. Please try again.");
            }
            // Get unit through unitMenu
            Number unitIndex = showUnitsMenu(false);
            // Assign length and unit to newMeasurement
            if (unitIndex.equals(8)) {
                continue;
            } else {
                newMeasurement.setUnit(units[unitIndex.intValue() - 1]);
                newMeasurement.setLength(length);
            }
            // Confirm measurement
            String getConfirmation = "You wish to create a measurement of "+ newMeasurement.getLength() + " " + newMeasurement.getUnit().toLowerCase() + "?(y or yes to confirm or n or no to recreate measurement.)";
            boolean confirming = true;
            while(confirming){
                try {
                    if (confirmMeasurements(getConfirmation, newMeasurement)){
                        confirming = false;
                        result = false;
                        return result;
                    }else{
                        confirming = false;
                        return result;
                    }
                } catch (IllegalArgumentException e) {
                    displayError(e.getMessage());
                }
            }
        }
        return result;
    }

    /**
     * Prints out measurements created for user to view, if none have been created tells the user to create some measurements
     * @param isMathYesNo boolean that if false has user enter to continue back to menu, if true it only displays measurements
     * @throws NoSuchElementException if there are no created measurements
     */
    public static void viewMeasurements(boolean isMathYesNo) {
        if(measurements.isEmpty()) {
            // Google ai helped me figure out what exception to throw
            throw new NoSuchElementException("No measurements found.\nPlease create a measurement before viewing.");
        }else{
            Integer i = 1;
            for(Measurement measurement : measurements){
                System.out.print(i + ": ");
                System.out.println(measurement.toString());
                i++;
            }
            if(!isMathYesNo){
                pressEnterToContinue();
            }

        }
    }

    /**
     * Has user select two measurements, then adds their lengths together and creates a new measurement with the desired unit
     * @throws NoSuchElementException thrown if no measurements created.
     */
    public static void addMeasurement() {
        // setup measurements for math
        Measurement newMeasure;
        Measurement m1;
        Measurement m2;
        Integer userInput = null;
        Number unitIndex;
        String unit;
        String getConfirmAdd;
        // boolean that determines if measurements array has items
        boolean measurementsHasMeasure = !measurements.isEmpty();
        // get the first measurement to add
        if (!measurementsHasMeasure) {
            throw new NoSuchElementException("You have not created any measurements.\nPlease create a measurement before performing operations.");
        }else {
            System.out.println("Please select the first measurement you wish to add: ");
            viewMeasurements(measurementsHasMeasure);
            userInput =  getInt("Choose an option", true, 1, measurements.size());
        }


        // set first measurement to selected measure and get second measure and set it
        if (userInput == null) {
            pressEnterToContinue();
        }else {
            m1 =  measurements.get(userInput - 1);
            System.out.println("Please select the second measurement you wish to add: ");
            viewMeasurements(measurementsHasMeasure);
            userInput =  getInt("Choose an option", true, 1, measurements.size());
            m2 =  measurements.get(userInput - 1);
            // get unit of newMeasure from user
            System.out.println("Please select the unit of measurement for added measurements to be set to.");
            unitIndex = showUnitsMenu(true);
            // set unit selected from showUnitsMenu to a string
            unit = units[unitIndex.intValue() - 1];

            // add the measurements to and set returned combined measurement to newMeasure
            newMeasure = Measurement.addMeasurements(m1, m2, unit);

            // display newMeasure info to user, then ask if they wish to save the measurement or not
            System.out.println("The combined measurement of " + m1.toString() + " and " + m2.toString() + " in " + newMeasure.getUnit() + " is " + newMeasure.toString() + ".");
            // Confirm save measurement or not
            boolean confirming = true;
            while(confirming){
                try {
                    getConfirmAdd = String.format("Do you wish to create a measurement of %.5f %s?(y or yes to confirm or n or no to not create measurement.)", newMeasure.getLength(), newMeasure.getUnit().toLowerCase());
                    confirmMeasurements(getConfirmAdd, newMeasure);
                    confirming = false;
                } catch (IllegalArgumentException e) {
                    displayError(e.getMessage());
                }
            }

        }




    }

    /**
     * Has user select or create two measurements, then subtracts the lengths and creates a new measurement with the desired unit
     * @throws NoSuchElementException thrown if no measurements have been created yet
     */
    public static void subtractMeasurements() {
        // setup measurements for math
        Measurement newMeasure;
        Measurement m1;
        Measurement m2;
        Integer userInput = null;
        Number unitIndex;
        String unit;
        String getConfirmSubtract;
        // boolean that determines if measurements array has items
        boolean measurementsHasMeasure = !measurements.isEmpty();
        // get the first measurement to add
        if (!measurementsHasMeasure) {
            throw new NoSuchElementException("You have not created any measurements.\nPlease create a measurement before performing operations.");
        }else {
            System.out.println("Please select the first measurement you wish to add: ");
            viewMeasurements(measurementsHasMeasure);
            userInput =  getInt("Choose an option", true, 1, measurements.size());
        }


        // set first measurement to selected measure and get second measure and set it
        if (userInput == null) {
            pressEnterToContinue();
        }else {
            m1 =  measurements.get(userInput - 1);
            System.out.println("Please select the second measurement you wish to subtract: ");
            viewMeasurements(measurementsHasMeasure);
            userInput =  getInt("Choose an option", true, 1, measurements.size());
            m2 =  measurements.get(userInput - 1);
            // get unit of newMeasure from user
            System.out.println("Please select the unit of measurement for subtracted measurements to be set to.");
            unitIndex = showUnitsMenu(true);
            // set unit selected from showUnitsMenu to a string
            unit = units[unitIndex.intValue() - 1];

            // subtract the measurements to and set returned combined measurement to newMeasure
            newMeasure = Measurement.subtractMeasurements(m1, m2, unit);
            // check if newMeasure is null, if not quit back to program
            // if it is, go through with confirmation
            if (newMeasure.getUnit().equals("null")) {
                pressEnterToContinue();
            } else {
                // display newMeasure info to user, then ask if they wish to save the measurement or not
                System.out.println("The subtracted measurement of " + m1.toString() + " and " + m2.toString() + "in " + newMeasure.getUnit() + " is " + newMeasure.toString() + ".");
                boolean confirming = true;
                while(confirming){
                    try {
                        getConfirmSubtract = String.format("Do you wish to create a measurement of %.5f %s?(y or yes to confirm or n or no to not create measurement.)", newMeasure.getLength(), newMeasure.getUnit().toLowerCase());
                        confirmMeasurements(getConfirmSubtract, newMeasure);
                        confirming = false;
                    } catch (IllegalArgumentException e) {
                        displayError(e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * Function to confirm to add a measurement
     * @param confirmationString String that displays a message of what the user is confirming
     * @param measureToCreate the measurement to be created
     * @return a boolean, true if measurement created, false if not
     * @throws IllegalArgumentException thrown if user enters something other than yes, y, no, n,
     */
    public static boolean confirmMeasurements(String confirmationString, Measurement measureToCreate) {
        String getConfirmation;
        boolean result = false;
        getConfirmation = getString(confirmationString);
        if(getConfirmation.equalsIgnoreCase("y") || getConfirmation.equalsIgnoreCase("yes")) {
            measurements.add(measureToCreate);
            System.out.println("New measurement created.");
            pressEnterToContinue();
            result = true;
            return result;
        } else if (getConfirmation.equalsIgnoreCase("n") || getConfirmation.equalsIgnoreCase("no")) {
            System.out.println("You chose not to create a measurement.");
            pressEnterToContinue();
            return result;
        } else {
            throw new IllegalArgumentException("Must enter either y or yes or n or no");
        }

    }


}
