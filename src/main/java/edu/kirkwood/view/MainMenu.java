package edu.kirkwood.view;

import edu.kirkwood.controller.*;

import static edu.kirkwood.view.UIUtility.printMenu;
import static edu.kirkwood.view.UserInput.getInt;

public class MainMenu {
    public static void show() {
        String[] menuItems = {
                "Marc's Fraction Calculator",
                "Mouftaou's Temperature Calculator",
                "Camren's Loan Calculator",
                "Logan's Blood Pressure Calculator",
                "Cooper's Compound Interest Calculator",
                "Cael's Temperature Calculator",
                "Emilia's Pythagorean Theorem Calculator",
                "Quit"
        };
        String menuTitle = "Main Menu";
        while(true) {
            printMenu(menuTitle, menuItems);
            int choice = getInt("Choose an option", false, 1, menuItems.length);
            switch (choice) {
                case 1:
                    MarcsFractionCalculator.start();
                    break;
                case 2:
                    TemperatureCalculator.start();
                    break;
                case 3:
                    LoanCalculator.start();
                    break;
                case 4:
                    BloodPressureCalculator.start();
                    break;
                case 5:
                    CoopersCompoundInterestCalculator.start();
                    break;
                case 6:
                    CaelsTemperatureCalculator.start();
                    break;
                case 7:
                    EmiliasPythagoreanTheorem.start();
                    break;
                default:
                    return;
            }
        }
    }
}