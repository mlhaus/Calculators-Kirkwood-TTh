package edu.kirkwood.view;

import edu.kirkwood.controller.CaelsTemperatureCalculator;
import edu.kirkwood.controller.MarcsFractionCalculator;

import static edu.kirkwood.view.UIUtility.printMenu;
import static edu.kirkwood.view.UserInput.getInt;

public class MainMenu {
    public static void show() {
        String[] menuItems = {
                "Marc's Fraction Calculator",
                "Cael's Temperature Calculator",
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
                    CaelsTemperatureCalculator.start();
                    break;
                default:
                    return;
            }
        }
    }
}