package controller;

import edu.kirkwood.controller.LoanCalculator;
import edu.kirkwood.model.Loan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * INFO
 * Used gpt 5.0 for all javadoc comments
 */


/**
 * Unit tests for {@link LoanCalculator} using the Arrange–Act–Assert pattern.
 * <p>
 * These tests cover parsing helpers and an integration check against {@link Loan}.
 * They meet the rubric requirement of “Write as many test methods as needed that utilize
 * Arrange, Act, and Assert. Minimum 5.”
 * </p>
 */
public class LoanCalculatorTest {

    /**
     * Verifies that {@link LoanCalculator#parsePrincipal(String)} accepts currency symbols and commas.
     * <p><b>Arrange:</b> "$200,000.00"<br>
     * <b>Act:</b> parsePrincipal<br>
     * <b>Assert:</b> equals 200000.00
     * </p>
     */
    @Test
    void parsePrincipalAcceptsCurrencyAndCommas() {
        // Arrange
        String input = "$200,000";

        // Act
        double a = LoanCalculator.parsePrincipal(input);

        // Assert
        assertEquals(200000, a);
    }

    /**
     * Verifies that {@link LoanCalculator#parsePrincipal(String)} throws for 0 or negative values.
     * <p><b>Arrange:</b> "0" and "-5"<br>
     * <b>Act:</b> parsePrincipal<br>
     * <b>Assert:</b> both throw IllegalArgumentException
     * </p>
     */
    @Test
    void parsePrincipalThrowsOnZeroAndNegative() {
        // Arrange
        String zero = "0";
        String negative = "-5";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> LoanCalculator.parsePrincipal(zero));
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> LoanCalculator.parsePrincipal(negative));
    }

    /**
     * Verifies that {@link LoanCalculator#parseAprPercent(String)} accepts a trailing percent sign.
     * <p><b>Arrange:</b> "6%"<br>
     * <b>Act:</b> parseAprPercent<br>
     * <b>Assert:</b> equals 6.0 (whole-number percent)
     * </p>
     */
    @Test
    void parseAprPercentAcceptsPercentSign() {
        // Arrange
        String input = "6%";

        // Act
        double a = LoanCalculator.parseAprPercent(input);

        // Assert
        assertEquals(6.0, a);
    }

    /**
     * Verifies that {@link LoanCalculator#parseAprPercent(String)} throws for negative APR.
     * <p><b>Arrange:</b> "-1"<br>
     * <b>Act:</b> parseAprPercent<br>
     * <b>Assert:</b> throws IllegalArgumentException
     * </p>
     */
    @Test
    void parseAprPercentThrowsOnNegative() {
        // Arrange
        String input = "-1";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> LoanCalculator.parseAprPercent(input));
    }

    /**
     * Verifies that {@link LoanCalculator#parsePositiveInt(String, String)} parses a valid positive integer.
     * <p><b>Arrange:</b> "12"<br>
     * <b>Act:</b> parsePositiveInt("12", "payments per year")<br>
     * <b>Assert:</b> equals 12
     * </p>
     */
    @Test
    void parsePositiveIntParsesValidInteger() {
        // Arrange
        String input = "12";

        // Act
        int a = LoanCalculator.parsePositiveInt(input, "payments per year");

        // Assert
        assertEquals(12, a);
    }

    /**
     * Verifies that {@link LoanCalculator#parsePositiveInt(String, String)} throws on 0 or negative values.
     * <p><b>Arrange:</b> "0" and "-3"<br>
     * <b>Act:</b> parsePositiveInt<br>
     * <b>Assert:</b> both throw IllegalArgumentException
     * </p>
     */
    @Test
    void parsePositiveIntThrowsOnZeroAndNegative() {
        // Arrange
        String zero = "0";
        String negative = "-3";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> LoanCalculator.parsePositiveInt(zero, "years"));
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> LoanCalculator.parsePositiveInt(negative, "years"));
    }


    // used gpt 5.0 for this test idea
    /**
     * Integration smoke test using controller parsers with the {@link Loan} model.
     * <p>
     * <b>Arrange:</b> principal=200000, aprPercent=6, years=30, payments/year=12<br>
     * <b>Act:</b> compute monthly payment via {@link Loan#payment()}<br>
     * <b>Assert:</b> approximately $1,199.10
     * </p>
     */
    @Test
    void integrationKnownMonthlyPayment() {
        // Arrange
        double principal = LoanCalculator.parsePrincipal("200000");
        double aprPercent = LoanCalculator.parseAprPercent("6");
        int years = LoanCalculator.parsePositiveInt("30", "years");
        int ppy = LoanCalculator.parsePositiveInt("12", "payments per year");

        // Act
        Loan loan = new Loan(principal, aprPercent, years, ppy);
        double payment = loan.payment();

        // Assert
        assertEquals(1199.10, payment, 0.2);
    }
}