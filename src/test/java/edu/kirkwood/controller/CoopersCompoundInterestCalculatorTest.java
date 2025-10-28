package edu.kirkwood.controller;

import edu.kirkwood.model.CompoundInterest;
import org.junit.jupiter.api.Test;

import static edu.kirkwood.controller.CoopersCompoundInterestCalculator.parseInput;
import static edu.kirkwood.controller.CoopersCompoundInterestCalculator.splitInput;
import static org.junit.jupiter.api.Assertions.*;

class CoopersCompoundInterestCalculatorTest {

    // Begin parseInput Tests
    @Test
    void parseInputWithValidContinuouslyCompoundingInterestParts(){
        // Arrange
        String[] input = {"10000", "0.07", "E", "10"};
        CompoundInterest expectedResult = new CompoundInterest(10000, .07, "e", 10);
        double expectedPrincipal = expectedResult.getPrincipal();
        double expectedInterestRate = expectedResult.getInterestRate();
        double expectedPeriodsPerYear = Math.E;
        int expectedTime = expectedResult.getTime();
        // Act
        CompoundInterest actualResult = parseInput(input);
        double actualPrincipal = actualResult.getPrincipal();
        double actualInterestRate = actualResult.getInterestRate();
        double actualPeriodsPerYear = actualResult.getPeriodsPerYear();
        int actualTime = actualResult.getTime();
        // Assert
        assertEquals(expectedPrincipal, actualPrincipal);
        assertEquals(expectedInterestRate, actualInterestRate);
        assertEquals(expectedPeriodsPerYear, actualPeriodsPerYear);
        assertEquals(expectedTime, actualTime);
    }

    @Test
    void parseInputWithValidCompoundingInterestParts(){
        // Arrange
        String[] input = {"10000", "0.07", "4", "10"};
        CompoundInterest expectedResult = new CompoundInterest(10000, .07, "4", 10);
        double expectedPrincipal = expectedResult.getPrincipal();
        double expectedInterestRate = expectedResult.getInterestRate();
        double expectedPeriodsPerYear = expectedResult.getPeriodsPerYear();
        int expectedTime = expectedResult.getTime();
        // Act
        CompoundInterest actualResult = parseInput(input);
        double actualPrincipal = actualResult.getPrincipal();
        double actualInterestRate = actualResult.getInterestRate();
        double actualPeriodsPerYear = actualResult.getPeriodsPerYear();
        int actualTime = actualResult.getTime();
        // Assert
        assertEquals(expectedPrincipal, actualPrincipal);
        assertEquals(expectedInterestRate, actualInterestRate);
        assertEquals(expectedPeriodsPerYear, actualPeriodsPerYear);
        assertEquals(expectedTime, actualTime);
    }


    @Test
    void parseInputWithInvalidPrincipal(){
        assertThrows(IllegalArgumentException.class, () -> CoopersCompoundInterestCalculator.parseInput(new String[]{"-50", "0.07", "e", "10"}));
    }

    @Test
    void parseInputWithInvalidLetterPeriodsPerYear(){
        assertThrows(IllegalArgumentException.class, () -> CoopersCompoundInterestCalculator.parseInput(new String[]{"10000", "0.07", "l", "10"}));
    }

    @Test
    void parseInputWithInvalidLowerBoundNumberForPeriodsPerYear(){
        assertThrows(IllegalArgumentException.class, () -> CoopersCompoundInterestCalculator.parseInput(new String[]{"10000", "0.07", "-1", "10"}));
    }

    @Test
    void parseInputWithInvalidUpperBoundForInterestRate(){
        assertThrows(IllegalArgumentException.class, () -> CoopersCompoundInterestCalculator.parseInput(new String[]{"10000", "2", "e", "10"}));
    }

    @Test
    void parseInputWithInvalidLowerBoundForInterestRate(){
        assertThrows(IllegalArgumentException.class, () -> CoopersCompoundInterestCalculator.parseInput(new String[]{"10000", "-.01", "e", "10"}));
    }
    // End parseInput Tests

    // Begin splitInput Tests
    @Test
    void splitInputWithValidCompoundingInterestParts(){
        // Arrange
        String input = "10000(1 + (.07 / 4))^(4 * 10)";
        String[] expectedResult = {"10000", ".07", "4", "10"};
        String[] actualResult;
        // Act
        actualResult = splitInput(input);
        // Assert
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void splitInputWithValidContinuouslyCompoundingInterestParts(){
        // Arrange
        String input = "10000(e)^(.07 * 10)";
        String[] expectedResult = {"10000", ".07", "e", "10"};
        String[] actualResult;
        // Act
        actualResult = splitInput(input);
        // Assert
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void splitInputThrowsMissingPrincipalException(){
        // Arrange
        Exception exception = assertThrows(IllegalArgumentException.class, () -> splitInput("(1 + (.07 / 4))^(4 * 10)"));
        String expectedMessage = "Invalid format, principal missing or in wrong location.";
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void splitInputThrowsPrincipalInWrongPlaceException(){
        // Arrange
        Exception exception = assertThrows(IllegalArgumentException.class, () -> splitInput("(10000(1 + (.07 / 4)))^(4 * 10)"));
        String expectedMessage = "Invalid format, principal missing or in wrong location.";
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void splitInputThrowsMissingFirstParenthesisException(){
        // Arrange
        Exception exception = assertThrows(IllegalArgumentException.class, () -> splitInput("10000 1 + (.07 / 4))^(4 * 10)"));
        String expectedMessage = "Invalid format, must have two sets of parentheses in the base of the formula.";
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void splitInputThrowsMissingClosingParenthesisException(){
        // Arrange
        Exception exception = assertThrows(IllegalArgumentException.class, () -> splitInput("10000(1 + (.07 / 4)^(4 * 10)"));
        String expectedMessage = "Invalid format, missing second closing parenthesis.";
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void splitInputThrowsMissingSecondSetOfParenthesesException(){
        // Arrange
        Exception exception = assertThrows(IllegalArgumentException.class, () -> splitInput("10000(1 + .07 / 4)^(4 * 10)"));
        String expectedMessage = "Invalid format, must have two sets of parentheses in the base of the formula.";
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void splitInputThrowsMissingOnePlusException(){
        // Arrange
        Exception exception = assertThrows(IllegalArgumentException.class, () -> splitInput("10000((.07 / 4))^(4 * 10)"));
        String expectedMessage = "Invalid format, must include '1 +' before (interest rate / periods per year) if you are not continuously compounding.";
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void splitInputThrowsInvalidOnePlusException(){
        // Arrange
        Exception exception = assertThrows(IllegalArgumentException.class, () -> splitInput("10000(3 + (.07 / 4))^(4 * 10)"));
        String expectedMessage = "Invalid format, must only add 1 to (interest rate / periods per year).";
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void splitInputThrowsMissingBaseInterestRateException(){
        // Arrange
        Exception exception = assertThrows(IllegalArgumentException.class, () -> splitInput("10000(1 + ( / 4))^(4 * 10)"));
        String expectedMessage = "Invalid format, missing interest rate.";
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void splitInputThrowsMissingBasePeriodsPerYearException(){
        // Arrange
        Exception exception = assertThrows(IllegalArgumentException.class, () -> splitInput("10000(1 + (.07 /))^(4 * 10)"));
        String expectedMessage = "Invalid format, missing periods per year.";
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void splitInputThrowsMissingExponentOperatorException(){
        // Arrange
        Exception exception = assertThrows(IllegalArgumentException.class, () -> splitInput("10000(1 + (.07 / 4))(4 * 10)"));
        String expectedMessage = "No exponent was found, please add a carat(^) symbol to indicate where the exponent is.";
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void splitInputThrowsMissingExponentOpeningParenthesisException(){
        // Arrange
        Exception exception = assertThrows(IllegalArgumentException.class, () -> splitInput("10000(1 + (.07 / 4))^4 * 10)"));
        String expectedMessage = "Invalid format, must be parentheses around the exponent of the formula.";
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void splitInputThrowsMissingExponentClosingParenthesisException(){
        // Arrange
        Exception exception = assertThrows(IllegalArgumentException.class, () -> splitInput("10000(1 + (.07 / 4))^(4 * 10"));
        String expectedMessage = "Invalid format, must be parentheses around the exponent of the formula.";
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void splitInputThrowsMissingExponentPeriodsPerYearException(){
        // Arrange
        Exception exception = assertThrows(IllegalArgumentException.class, () -> splitInput("10000(1 + (.07 / 4))^(* 10)"));
        String expectedMessage = "Invalid format, missing periods per year.";
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void splitInputThrowsMissingTimeException(){
        // Arrange
        Exception exception = assertThrows(IllegalArgumentException.class, () -> splitInput("10000(1 + (.07 / 4))^(4 *)"));
        String expectedMessage = "Invalid format, missing time.";
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void splitInputThrowsMissingInterestRateException(){
        // Arrange
        Exception exception = assertThrows(IllegalArgumentException.class, () -> splitInput("10000(e)^( * 10)"));
        String expectedMessage = "Invalid format, missing interest rate.";
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void splitInputThrowsMissingContinuouslyCompoundingTimeException(){
        // Arrange
        Exception exception = assertThrows(IllegalArgumentException.class, () -> splitInput("10000(1 + (.07 / 4))^(4 *)"));
        String expectedMessage = "Invalid format, missing time.";
        // Act
        String actualMessage = exception.getMessage();
        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }



}