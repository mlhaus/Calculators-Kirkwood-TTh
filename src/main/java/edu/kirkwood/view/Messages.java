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

    public static void calculatorEnd() {
        System.out.println("Quitting calculator...");
    }

    public static void celesteGreeting() {
        displayMessage("Welcome to Cel's Metric Conversion Calculator!");
        System.out.println("Input should be in the format [number] [unit]");
        System.out.println("Examples: 10cm, 100 m");
        System.out.println("Note: The only valid units are 'mm', 'cm', 'm', 'km'");
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

     public static void CamrenHello(){
        displayMessage("Welcome to Munn's Loan Calculator!");
    }

    public static void CamrenGoodbye(){
        displayMessage("Thank you for using my application, GoodBye.");
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

    public static void cooperGreet(){
        displayMessage("Welcome to Cooper's Compounding Interest Calculator");
        System.out.println("Formula parts:\n - Principal: P\n - Interest Rate: r\n - Periods per Year: n\n - Time (in years): t");
        System.out.println("Enter formula in one of two forms:\n - Normal: P(1 + (r / n))^(n * t)\n - Continuous Compounding: P(e)^(r * t)");
    }

    public static void cooperGoodbye(){
        displayMessage("Thank you for using Cooper's Compounding Interest Calculator!");
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
