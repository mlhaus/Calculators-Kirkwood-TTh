package edu.kirkwood.view;

public class UIUtility {

    public static void displayMessage(String message) {
        displayMessage(message, "");
    }

    public static void displayMessage(String message, String type) {
        System.out.printf("*** %s%s ***\n", (!type.equals("") ? type.toUpperCase() + " - " : ""), message);
    }

    public static void displayError(String message) {
        displayMessage(message, "error");
    }

    public static void displayWarning(String message) {
        displayMessage(message, "warning");
    }

    public static void displaySuccess(String message) {
        displayMessage(message, "success");
    }

    public static void pressEnterToContinue() {
        UserInput.getString("Press enter to continue", false);
    }

    public static void printLine() {
        printLine(40);
    }

    public static void printLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printMenu(String title, String[] menuItems) {
        System.out.println();
        printLine();
        displayMessage(title);
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println((i + 1) + ") " + menuItems[i]);
        }
        printLine();
    }

    public static String separator(int[] columnWidths) {
        StringBuilder sb = new StringBuilder();
        for (int width : columnWidths) {
            sb.append("+ ");
            for (int i = 0; i < width; i++) {
                sb.append('-');
            }
            sb.append(' ');
        }
        sb.append("+");
        return sb.toString();
    }
}
