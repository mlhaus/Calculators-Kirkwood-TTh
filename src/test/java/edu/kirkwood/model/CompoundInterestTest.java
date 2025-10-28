package edu.kirkwood.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CompoundInterestTest {

    private CompoundInterest compoundInterest = new CompoundInterest();

    @Test
    void getPrincipal() {
        // Arrange
        double expected = 1000.00;
        compoundInterest.setPrincipal(1000.00);
        // Act
        double actual = compoundInterest.getPrincipal();
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void setPrincipalDouble() {
        // Arrange
        double input = 1000.00;
        double expected = 1000.00;
        // Act
        compoundInterest.setPrincipal(input);
        // Assert
        assertEquals(expected, compoundInterest.getPrincipal());
    }

    @Test
    void setPrincipalZero() {
        assertThrows(IllegalArgumentException.class, () -> {compoundInterest.setPrincipal(0.00);});
    }

    @Test
    void setPrincipalNegative() {
        assertThrows(IllegalArgumentException.class, () -> {compoundInterest.setPrincipal(-1.23);});
    }

    @Test
    void getInterestRate() {
        // Arrange
        double expected = 0.62;
        compoundInterest.setInterestRate(0.62);
        // Act
        double actual = compoundInterest.getInterestRate();
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void setInterestRate() {
        // Arrange
        double input = 0.62;
        double expected = 0.62;
        // Act
        compoundInterest.setInterestRate(input);
        // Assert
        assertEquals(expected, compoundInterest.getInterestRate());
    }

    @Test
    void setInterestRateOutsideUpperBound() {
        assertThrows(IllegalArgumentException.class, () -> {compoundInterest.setInterestRate(1.01);});
    }

    @Test
    void setInterestRateOutsideLowerBound() {
        assertThrows(IllegalArgumentException.class, () -> {compoundInterest.setInterestRate(-0.01);});
    }

    @Test
    void setInterestRateZero() {
        assertThrows(IllegalArgumentException.class, () -> {compoundInterest.setInterestRate(0.0);});
    }

    @Test
    void getPeriodsPerYearDouble() {
        // Arrange
        double expected = 3.00;
        compoundInterest.setPeriodsPerYear("3.00");
        // Act
        double actual = compoundInterest.getPeriodsPerYear();
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void setPeriodsPerYearNumber() {
        // Arrange
        String input = "3.00";
        double expected = 3.00;
        // Act
        compoundInterest.setPeriodsPerYear(input);
        // Assert
        assertEquals(expected, compoundInterest.getPeriodsPerYear());
    }

    @Test
    void setPeriodsPerYearCompounding() {
        // Arrange
        String input = "E";
        double expected = Math.E;
        // Act
        compoundInterest.setPeriodsPerYear(input);
        // Assert
        assertEquals(expected, compoundInterest.getPeriodsPerYear());
    }

    @Test
    void setPeriodsPerYearNonNumberNonCompounding() {
        assertThrows(IllegalArgumentException.class, () -> {compoundInterest.setPeriodsPerYear("X");});
    }

    @Test
    void setPeriodsPerYearZero() {
        assertThrows(IllegalArgumentException.class, () -> {compoundInterest.setPeriodsPerYear("0");});
    }

    @Test
    void setPeriodsPerYearNegative() {
        assertThrows(IllegalArgumentException.class, () -> {compoundInterest.setPeriodsPerYear("-1.1");});
    }

    @Test
    void getTime() {
        // Arrange
        int expected = 2;
        compoundInterest.setTime(2);
        // Act
        int actual = compoundInterest.getTime();
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void setTime() {
        // Arrange
        int input = 2;
        int expected = 2;
        // Act
        compoundInterest.setTime(input);
        // Assert
        assertEquals(expected, compoundInterest.getTime());
    }

    @Test
    void setTimeZero() {
        assertThrows(IllegalArgumentException.class, () -> {compoundInterest.setTime(0);});
    }

    @Test
    void setTimeNegative() {
        assertThrows(IllegalArgumentException.class, () -> {compoundInterest.setTime(-1);});
    }

    @Test
    void calculateFutureValueNormal() {
        // Arrange
        CompoundInterest compoundInterestTest = new CompoundInterest(10000.00, 0.07, "4", 10);
        double expected = 20015.97;
        // Act
        double actual = compoundInterestTest.calculateFutureValue();
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void calculateFutureValueCompoundingContinuously() {
        // Arrange
        CompoundInterest compoundInterestTest = new CompoundInterest(10000.00, 0.07, "E", 10);
        double expected = 20137.53;
        // Act
        double actual = compoundInterestTest.calculateFutureValue();
        // Assert
        assertEquals(expected, actual);
    }
}