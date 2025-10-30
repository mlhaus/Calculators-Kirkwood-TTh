package edu.kirkwood.view;

import static edu.kirkwood.view.UIUtility.displayMessage;

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

    public static void cooperGreet(){
        displayMessage("Welcome to Cooper's Compounding Interest Calculator");
        System.out.println("Formula parts:\n - Principal: P\n - Interest Rate: r\n - Periods per Year: n\n - Time (in years): t");
        System.out.println("Enter formula in one of two forms:\n - Normal: P(1 + (r / n))^(n * t)\n - Continuous Compounding: P(e)^(r * t)");
    }

    public static void cooperGoodbye(){
        displayMessage("Thank you for using Cooper's Compounding Interest Calculator!");
    }
}
