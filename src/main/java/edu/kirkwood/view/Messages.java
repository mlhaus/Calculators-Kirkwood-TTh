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

    public static void mouftaouGreet() {
        displayMessage("Welcome to Mouftaou's Temperature Calculator");
        System.out.println("Enter calculations in one of the following formats:");
        System.out.println(" - [temperature] + [temperature]  (e.g. 25 K + 87 F)");
        System.out.println(" - [temperature] - [temperature]  (e.g. 58 C - 15 F)");
        System.out.println(" - convert [temperature] to [scale]  (e.g. convert 35 F to K)");
    }

    public static void mouftaouGoodbye() {
        displayMessage("Thank you for using Mouftaou's Temperature Calculator");
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

    public static void caelGreet() {
        displayMessage("Welcome to Cael's Temperature Calculator");
        System.out.println("Enter calculations in the format: [number], [scale], [number], [scale]");
        System.out.println("Example: 1 f + 2 f");
    }

    public static void caelGoodbye() {
        displayMessage("Thank you for using Cael's Temperature Calculator");
    }
}
