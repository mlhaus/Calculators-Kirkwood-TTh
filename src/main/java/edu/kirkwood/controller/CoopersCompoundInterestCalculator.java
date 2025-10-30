package edu.kirkwood.controller;

import edu.kirkwood.model.CompoundInterest;

import static edu.kirkwood.view.Messages.cooperGoodbye;
import static edu.kirkwood.view.Messages.cooperGreet;
import static edu.kirkwood.view.UIUtility.displayError;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getString;

public class CoopersCompoundInterestCalculator {
    public static void start() {
        cooperGreet();
        boolean done = false;
        while(!done) {
            CompoundInterest compoundInterest = new CompoundInterest();
            String input = getString("Enter your equation (or 'q' to quit): ");
            if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
                cooperGoodbye();
                break;
            }
            String[] parts = null;
            // Splitting input
            try {
                parts = splitInput(input);
            } catch (Exception e) {
                displayError(e.getMessage());
                done = true;
            }
            // Parsing the split input into a CompoundInterest object
            try {
                compoundInterest = parseInput(parts);
            } catch (Exception e) {
                displayError(e.getMessage());
                done = true;
            }
            // Calculating the future value of the compound interest object
            double result = compoundInterest.calculateFutureValue();
            System.out.printf(" - Principal: %s\n - Interest Rate: %s\n - Periods per Year: %s\n - Time (in years): %s\n - Future Value: %s\n"
                , compoundInterest.getPrincipal(), compoundInterest.getInterestRate(), compoundInterest.getPeriodsPerYear(), compoundInterest.getTime(), result);
            pressEnterToContinue();
        }
    }

    /**
     * Takes compound interest formula and splits it into it's respective pieces.
     * @param input Compound Interest formula as a string. Must be in one of two forms: P(1 + (r / n))^(n * t) OR P(e)^(r * t)
     * @return An array of parts in the following order: {principal, interestRate, basePeriodsPerYear, exponentPeriodsPerYear, time}
     * @throws IllegalArgumentException if the input is not in a valid form
     */
    public static String[] splitInput(String input){
        String[] parts;
        String principal = "";
        String interestRate = "";
        String basePeriodsPerYear = "";
        String exponentPeriodsPerYear = "";
        boolean continuousCompounding = false;
        String time = "";
        // Splitting the input into the base and the exponent of the formula
        String carat = "^";
        int caratIndex;
        if(input.contains(carat)){
            caratIndex = input.indexOf("^");
        } else {
            throw new IllegalArgumentException("No exponent was found, please add a carat(^) symbol to indicate where the exponent is.");
        }
        String base = input.substring(0, caratIndex).trim();
        String exponent = input.substring(caratIndex).trim();

        // Getting the principal
        int firstBaseParaIndex;
        if(base.contains("(") && base.contains(")")){
            firstBaseParaIndex = base.indexOf("(");
            principal = base.substring(0, firstBaseParaIndex).trim();
            if(principal.equalsIgnoreCase("")){
                throw new IllegalArgumentException("Invalid format, principal missing or in wrong location.");
            }
        }
        else {
            throw new IllegalArgumentException("Invalid format, must have principal outside of parentheses");
        }
        // Checking to see if this is the base formula or continuously compounding interest
        if(base.substring(firstBaseParaIndex + 1, base.indexOf(")")).trim().equalsIgnoreCase("e")){
            continuousCompounding = true;
            basePeriodsPerYear = "e";
        }

        // Getting the interestRate and periodsPerYear if not continuouslyCompounding
        if(!continuousCompounding){
            int divisionIndex;
            int lastBaseParaIndex =  base.lastIndexOf("(");
            if(firstBaseParaIndex == lastBaseParaIndex){
                throw new IllegalArgumentException("Invalid format, must have two sets of parentheses in the base of the formula.");
            }
            int lastBaseClosingParaIndex = base.lastIndexOf(")");
            if(lastBaseClosingParaIndex == base.indexOf(")")){
                throw new IllegalArgumentException("Invalid format, missing second closing parenthesis.");
            }
            if(base.contains("/")){
                divisionIndex = base.indexOf("/");
                interestRate = base.substring(lastBaseParaIndex + 1, divisionIndex).trim();
                // Seeing if interest rate is missing
                if(interestRate.equalsIgnoreCase("")){
                    throw new IllegalArgumentException("Invalid format, missing interest rate.");
                }
                basePeriodsPerYear = base.substring(divisionIndex + 1, base.indexOf(")")).trim();
                // Seeing if base periods per year is missing
                if(basePeriodsPerYear.equalsIgnoreCase("")){
                    throw new IllegalArgumentException("Invalid format, missing periods per year.");
                }
            } else {
                throw new IllegalArgumentException("Invalid format, must divide: (interest rate / periods per year) or enter (e).");
            }

            // Checking to see if they included "1 + " before the interestRate and periodsPerYear if not continuouslyCompounding
            int additionIndex;
            if(base.substring(firstBaseParaIndex, lastBaseParaIndex).contains("+")){
                additionIndex = base.indexOf("+");
            } else {
                throw new IllegalArgumentException("Invalid format, must include '1 +' before (interest rate / periods per year) if you are not continuously compounding.");
            }
            // Throws exception if they are adding anything other than 1 to (interestRate / periodsPerYear)
            if(!base.substring(firstBaseParaIndex + 1, additionIndex).trim().equalsIgnoreCase("1")){
                throw new IllegalArgumentException("Invalid format, must only add 1 to (interest rate / periods per year).");
            }
        }

        // Checking to see if the exponent has parentheses around it
        int firstExpParaIndex;
        if(exponent.contains("(") && exponent.contains(")")) {
            firstExpParaIndex = exponent.indexOf("(");
        } else {
            throw new IllegalArgumentException("Invalid format, must be parentheses around the exponent of the formula.");
        }

        // Splitting the exponent if not continuouslyCompounding
        if(!continuousCompounding){
            // Checking to see if there is a multiplication operator in the exponent
            int multiplicationIndex;
            if(exponent.contains("*")){
                multiplicationIndex = exponent.indexOf("*");
            } else {
                throw new IllegalArgumentException("Invalid format, must multiply periods per year and time.");
            }
            //Split periodsPerYear and Time
            exponentPeriodsPerYear = exponent.substring(firstExpParaIndex + 1, multiplicationIndex).trim();
            // Seeing if the exponent periods per year is missing
            if(exponentPeriodsPerYear.equalsIgnoreCase("")){
                throw new IllegalArgumentException("Invalid format, missing periods per year.");
            }
            time = exponent.substring(multiplicationIndex + 1, exponent.length() - 1).trim();
            // Seeing if time is missing
            if(time.equalsIgnoreCase("")){
                throw new IllegalArgumentException("Invalid format, missing time.");
            }
            //Verify basePeriodsPerYear and exponentPeriodsPerYear are equal
            if(!exponentPeriodsPerYear.equalsIgnoreCase(basePeriodsPerYear)){
                throw new IllegalArgumentException("Invalid format, periods per year must be the same in both spots.");
            }
        }

        // Splitting the exponent if they are continuouslyCompounding
        if(continuousCompounding) {
            // Checking to see if there is a multiplication operator in the exponent
            int multiplicationIndex;
            if (exponent.contains("*")) {
                multiplicationIndex = exponent.indexOf("*");
            } else {
                throw new IllegalArgumentException("Invalid format, must multiply interest rate and time.");
            }
            //Split interestRate and Time
            interestRate = exponent.substring(firstExpParaIndex + 1, multiplicationIndex).trim();
            // Seeing if the exponent periods per year is missing
            if(interestRate.equalsIgnoreCase("")){
                throw new IllegalArgumentException("Invalid format, missing interest rate.");
            }
            time =  exponent.substring(multiplicationIndex + 1, exponent.length() - 1).trim();
            // Seeing if time is missing
            if(time.equalsIgnoreCase("")){
                throw new IllegalArgumentException("Invalid format, missing time.");
            }
        }

        // Set parts of compound interest formula
        parts =  new String[]{principal, interestRate, basePeriodsPerYear, time};
        return parts;
    }


    /**
     * Takes an array of parts and parses each part into their respective Compound Interest properties.
     * @param parts A String[] of required parts needed to calculate compound interest. Required to be in the order: principal, interest rate, periods per year, and time.
     * @return A CompoundInterest object with the parts data validated and set.
     */
    public static CompoundInterest parseInput(String[] parts){
        CompoundInterest result = new CompoundInterest();

        // Setting parts that are in both formulas
            // Trying to set the principal
        try {
            double principal = Double.parseDouble(parts[0]);
            result.setPrincipal(principal);
        } catch (Exception e) {
            throw new IllegalArgumentException("Principal: " + parts[0] + " could not be set");
        }
            // Trying to set the interest rate
        try{
            double interestRate = Double.parseDouble(parts[1]);
            result.setInterestRate(interestRate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Interest Rate: " + parts[1] + " could not be set");
        }
            // Trying to set the time
        try {
            int time =  Integer.parseInt(parts[3]);
            result.setTime(time);
        } catch (Exception e){
            throw new IllegalArgumentException("Time: " + parts[3] + " could not be set");
        }

        // Setting periods per year if the formula is continuously compounding
        if(parts[2].equalsIgnoreCase("e")){
            // Trying to set periods per year to Math.E
            try {
                result.setPeriodsPerYear(parts[2]);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(parts[2] + " could not be set to Math.E");
            }
            // Setting periods per year if the formula is not continuously compounding
        } else {
            try {
                result.setPeriodsPerYear(parts[2]);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(parts[2] + " could not be set");
            }
        }
        return result;
    }
}
