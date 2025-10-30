package edu.kirkwood.controller;

import edu.kirkwood.model.TemperatureEntry;

import static edu.kirkwood.view.Messages.caelGoodbye;
import static edu.kirkwood.view.Messages.caelGreet;
import static edu.kirkwood.view.UserInput.getFloat;
import static edu.kirkwood.view.UserInput.getString;

/**
 * The {@code CaelsTemperatureCalculator} class handles temperature calculations
 * between two {@link TemperatureEntry} objects. It supports scale conversion,
 * arithmetic operations, and absolute zero validation.
 * <p>
 * Inputs are gathered via static methods from {@code UserInput} and used to
 * instantiate temperature entries and perform calculations.
 */
public class CaelsTemperatureCalculator {


    public static void start(){

        // User input values and scales
        caelGreet();
        float F1 = getFloat("enter the first value");
        String S1 = getString("enter the first scale");
        String sign = getString("enter the operator");
        float F2 = getFloat("enter the second value");
        String S3 = getString("enter the second scale");

        // Temperature entries created from user input
        TemperatureEntry entry1 = new TemperatureEntry(F1, S1);
        TemperatureEntry entry2 = new TemperatureEntry(F2, S3);

        absoluteZero(entry1, entry2);
        setScale(entry1, entry2);
        calculate(entry1, entry2, sign);

        System.out.println();
        caelGoodbye();
    }


    /**
     * Ensures both temperature entries use the same scale (Celsius or Fahrenheit).
     * If they differ, prompts the user to choose a preferred scale and converts
     * one entry accordingly.
     *
     * @param entry1 the first temperature entry
     * @param entry2 the second temperature entry
     * @return
     * @throws IllegalArgumentException if the chosen scale is invalid
     */
    public static <string> TemperatureEntry setScale(TemperatureEntry entry1, TemperatureEntry entry2) {
        if (!entry2.getScale().equals(entry1.getScale())) {
            String S4 = getString("What scale do you want to use?");
            if (entry2.getScale().equals(S4) && !entry1.getScale().equals(S4)) {
                entry1.setScale(entry2.getScale());
                if (entry2.getScale().equals("F")) {
                    entry1.setValue(entry1.getValue() * 9 / 5 + 32);
                } else {
                    entry1.setValue((entry1.getValue() - 32) * 5 / 9);
                }
                return entry1;
            } else if (entry1.getScale().equals(S4) && !entry2.getScale().equals(S4)) {
                entry2.setScale(entry1.getScale());
                if (entry1.getScale().equals("F")) {
                    entry2.setValue(entry2.getValue() * 9 / 5 + 32);
                } else {
                    entry2.setValue((entry2.getValue() - 32) * 5 / 9);
                }
                return entry2;
            } else {
                throw new IllegalArgumentException("Scale doesn't match Celsius or Fahrenheit.");
            }
        }
        return null;
    }

    /**
     * Performs arithmetic calculation between two temperature values based on the
     * operator provided by the user.
     *
     * @param entry1 the first temperature entry
     * @param entry2 the second temperature entry
     * @return the result of the arithmetic operation
     * @throws IllegalArgumentException if the operator is not '+' or '-'
     */
    public static void calculate(TemperatureEntry entry1, TemperatureEntry entry2,String sign) {
        Float F3;
        if (sign.equals("+")) {
            F3 = entry1.getValue() + entry2.getValue();
        } else if (sign.equals("-")) {
            F3 = entry1.getValue() - entry2.getValue();
        } else {
            throw new IllegalArgumentException("operator doesn't match minus or plus signs.");
        }
        Display(F3, entry1);
    }

    private static void Display(Float f3, TemperatureEntry entry1) {
        System.out.printf(f3 + entry1.getScale());
    }

    /**
     * Validates that neither temperature entry is below absolute zero.
     * If either value is below -459.67°F or -273.15°C, an exception is thrown.
     * If the scale is unknown, prompts the user to set a common scale.
     *
     * @param entry1 the first temperature entry
     * @param entry2 the second temperature entry
     * @throws IllegalArgumentException if any temperature is below absolute zero
     */
    public static void absoluteZero(TemperatureEntry entry1, TemperatureEntry entry2) {
        Float zeroF = (float) -459.67;
        Float zeroC = (float) -273.15;

        if (entry1.getScale().equals("F") || entry2.getScale().equals("F")) {
            if (entry1.getValue() < zeroF || entry2.getValue() < zeroF) {
                throw new IllegalArgumentException("You cannot put the temp below absolute zero");
            }
        } else if (entry1.getScale().equals("C") || entry2.getScale().equals("C")) {
            if (entry1.getValue() < zeroC || entry2.getValue() < zeroC) {
                throw new IllegalArgumentException("You cannot put the temp below absolute zero");
            }
        } else {
            setScale(entry1, entry2);
        }
    }
}