package edu.kirkwood.controller;

import edu.kirkwood.model.Loan;
import edu.kirkwood.view.Helpers;

import static edu.kirkwood.view.Messages.*;
import static edu.kirkwood.view.UiUtility.*;
import static edu.kirkwood.view.UserInput.getString;

/**
 * Controller for a simple console-based Loan calculator.
 * <p>
 * Flow matches the in-class controller:
 * <ol>
 *   <li>Greet</li>
 *   <li>Loop prompts with {@code getString}; user can type {@code q} or {@code quit} to exit</li>
 *   <li>Parse/validate input (throws {@link IllegalArgumentException} on invalid values)</li>
 *   <li>Create a {@link Loan} and display results</li>
 *   <li>{@code pressEnterToContinue()} between runs</li>
 *   <li>Goodbye message on exit</li>
 * </ol>
 * <p>
 * Notes:
 * - “Term” is unit-agnostic (years, months, or weeks).
 * - “Payments per term” must match whatever unit you choose for term.
 */
public class LoanCalculator {

    /**
     * Starts interactive loan calculation.
     * Prompts for principal, APR (whole-number percent), generic term (years/months/weeks),
     * and payments per term.
     */
    public static void start() {
        Hello();
        while (true) {
            try {
                // --- Principal ---
                String principalInput = getString("Enter your principal amount or enter 'q' to quit: ");
                if (principalInput.equalsIgnoreCase("q") || principalInput.equalsIgnoreCase("quit")) {
                    break;
                }
                double principal = parsePrincipal(principalInput);

                // --- APR (whole-number percent, e.g., 6 or 6.5) ---
                String aprInput = getString("Enter APR percent (e.g., 6 or 6.5), or 'q' to quit: ");
                if (aprInput.equalsIgnoreCase("q") || aprInput.equalsIgnoreCase("quit")) {
                    break;
                }
                double aprPercent = parseAprPercent(aprInput);

                // --- Term (generic) ---
                String termInput = getString("Enter term amount (how many years, months, or weeks), or 'q' to quit: ");
                if (termInput.equalsIgnoreCase("q") || termInput.equalsIgnoreCase("quit")) {
                    break;
                }
                int term = parsePositiveInt(termInput, "term");

                // --- Payments per term (must match chosen term unit) ---
                String pptInput = getString("Enter payments per term (e.g., 12), or 'q' to quit: ");
                if (pptInput.equalsIgnoreCase("q") || pptInput.equalsIgnoreCase("quit")) {
                    break;
                }
                int paymentsPerTerm = parsePositiveInt(pptInput, "payments per term");

                // Build model (Loan converts whole-number APR percent to decimal internally)
                Loan loan = new Loan(principal, aprPercent, term, paymentsPerTerm);

                // Display results
                displayMessage("Periodic payment: $" + Helpers.round(loan.payment(), 2));
                displayMessage("Total payments: " + loan.totalPayments());
                displayMessage("Total paid: $" + Helpers.round(loan.totalPaid(), 2));
                displayMessage("Total interest: $" + Helpers.round(loan.totalInterest(), 2));

            } catch (IllegalArgumentException ex) {
                displayError(ex.getMessage());
            }

            pressEnterToContinue();
        }
        Goodbye();
    }

    //used GPT 5.0 to polish my parse methods.
    /**
     * Parses a principal value from user text.
     * Accepts optional '$' and commas (e.g., "$200,000.00").
     *
     * @param i raw input
     * @return principal as a double &gt; 0
     * @throws IllegalArgumentException if null/empty/not numeric or ≤ 0
     */
    public static double parsePrincipal(String i) {
        if (i == null) {
            throw new IllegalArgumentException("Principal cannot be null.");
        }
        String s = i.trim().replace("$", "").replace(",", "");
        if (s.isEmpty()) {
            throw new IllegalArgumentException("Principal can't be empty.");
        }
        try {
            double parsedDouble = Double.parseDouble(s);
            if (parsedDouble <= 0) {
                throw new IllegalArgumentException("Principal must be greater than zero.");
            }
            return parsedDouble;
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Invalid principal: " + i);
        }
    }

    /**
     * Parses an APR whole-number percent (e.g., "6", "6.5", or "6%").
     * Returns the whole-number percent; {@link Loan} converts it to a decimal internally.
     *
     * @param input raw APR text
     * @return APR as whole-number percent (≥ 0)
     * @throws IllegalArgumentException if null/empty/not numeric or negative
     */
    public static double parseAprPercent(String input) {
        if (input == null) {
            throw new IllegalArgumentException("APR cannot be null.");
        }
        String s = input.trim();
        if (s.endsWith("%")) {
            s = s.substring(0, s.length() - 1).trim();
        }
        if (s.isEmpty()) {
            throw new IllegalArgumentException("APR cannot be empty.");
        }
        try {
            double v = Double.parseDouble(s);
            if (v < 0) {
                throw new IllegalArgumentException("APR cannot be negative.");
            }
            return v;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid APR: " + input);
        }
    }

    /**
     * Parses a positive integer (e.g., term or payments per term).
     *
     * @param input     raw text
     * @param fieldName used in error messages
     * @return integer &gt; 0
     * @throws IllegalArgumentException if null/empty/not numeric or ≤ 0
     */
    public static int parsePositiveInt(String input, String fieldName) {
        if (input == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null.");
        }
        String s = input.trim();
        if (s.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        try {
            int v = Integer.parseInt(s);
            if (v <= 0) {
                throw new IllegalArgumentException(fieldName + " must be greater than 0.");
            }
            return v;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid " + fieldName + ": " + input);
        }
    }
}