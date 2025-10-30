package edu.kirkwood.view;

import static edu.kirkwood.view.UIUtility.displayMessage;
import static edu.kirkwood.view.UIUtility.printLine;

public class Messages {
    public static void hello() {
        displayMessage("Welcome to the Kirkwood Calculators Application");
    }

    public static void goodbye() {
        displayMessage("Goodbye");
    }

    public static void marcGreet() {
        displayMessage("Welcome to Marc's Fraction Calculator");
        System.out.println("Enter calculations in the format: [fraction] [operator] [fraction]");
        System.out.println("Example: 1 1/2 + 3/4");
    }

    public static void marcGoodbye() {
        displayMessage("Thank you for using Marc's Fraction Calculator");
    }

    public static void emiliaGreet() {
        displayMessage("Welcome to Emilia's Pythagorean Theorem");
        System.out.println("Enter calculations in the format: [side 1] [side 2] [decimal place]");
        System.out.println("Example: 5.24 3.23 2");
        System.out.println("You may leave off the decimal place for a default decimal place of two.");
    }

    public static void emiliaGoodbye() {
        displayMessage("\nThank you for using Emilia's Pythagorean Theorem Calculator!");
    }

    public static void loganGreet() {
        System.out.println();
        displayMessage("Welcome to Logan's Blood Pressure Calculator");
        System.out.println("\nEnter blood pressure in the format: [Systolic] / [Diastolic]" +
                "\nThe mean arterial pressure will be calculated and displayed\nas 'Systolic/Diastolic (MAP)'");
        System.out.println("\tExample: 120/80 -> 120/80 (93)");
        printLine();

    }

    public static void loganGoodbye() {
        displayMessage("\t\t" + "Thank you for using Logan's Blood Pressure Calculator");
    }
}
