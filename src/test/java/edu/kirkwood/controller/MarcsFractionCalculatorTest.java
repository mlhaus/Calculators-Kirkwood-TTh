package edu.kirkwood.controller;

import edu.kirkwood.model.Fraction;
import edu.kirkwood.view.UIUtility;
import edu.kirkwood.view.UserInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class MarcsFractionCalculatorTest {

    @Test
    void startWithValidInput() {
        // We are mocking static methods, so we need to use mockStatic in a try-with-resources block.
        try (
             MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
             MockedStatic<UIUtility> staticUIUtility = mockStatic(UIUtility.class)) {

            // 1. ARRANGE (Stubbing the methods)
            // Tell our mock UserInput what to do when getString is called.
            // It will return "1/2 + 1/2" the first time, and "quit" the second time.
            staticUserInput.when(() -> UserInput.getString(anyString())).thenReturn("1/2 + 1/2", "quit");

            // 2. ACT
            // Run the method we are testing.
            MarcsFractionCalculator.start();

            // 3. ASSERT (Verifying interactions)
            // We can verify that our mock methods were called as expected.
            // Let's verify that displayError was NEVER called, because the input was valid.
            staticUIUtility.verify(() -> UIUtility.displayError(anyString()), never());
        }
    }

    @Test
    void startWithInValidInput() {
        // We are mocking static methods, so we need to use mockStatic in a try-with-resources block.
        try (
                MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
                MockedStatic<UIUtility> staticUIUtility = mockStatic(UIUtility.class)) {

            // 1. ARRANGE
            // The ^ operator is not valid
            staticUserInput.when(() -> UserInput.getString(anyString())).thenReturn("1/2 ^ 1/2", "quit");

            // 2. ACT
            MarcsFractionCalculator.start();

            // 3. ASSERT 
            // I assert that the displayError method was called once
            staticUIUtility.verify(() -> UIUtility.displayError(anyString()), times(1));
        }
    }

    @Test
    void splitInputWithAddition() {
        // Arrange
        String input = "1/2 + 3/4";
        String[] expected = {"1/2", "+", "3/4"};
        // Act
        String[] actual = MarcsFractionCalculator.splitInput(input);
        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Test splitCalculation with subtraction operator and mixed numbers")
    void splitCalculationWithSubtractionAndMixedNumbers() {
        // Arrange
        String input = "3 1/4 - 1/2";
        String[] expected = {"3 1/4", "-", "1/2"};
        // Act
        String[] actual = MarcsFractionCalculator.splitInput(input);
        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Test splitCalculation with multiplication and negative numbers")
    void splitCalculationWithMultiplicationAndNegatives() {
        // Arrange
        String input = "-5 * -2 1/3";
        String[] expected = {"-5", "*", "-2 1/3"};
        // Act
        String[] actual = MarcsFractionCalculator.splitInput(input);
        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Test splitCalculation with division operator")
    void splitCalculationWithDivision() {
        // Arrange
        String input = "10/3 / 5";
        String[] expected = {"10/3", "/", "5"};
        // Act
        String[] actual = MarcsFractionCalculator.splitInput(input);
        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Test splitCalculation with extra spaces in input")
    void splitCalculationWithExtraSpaces() {
        // Arrange
        String input = "  1/2   +   3/4  ";
        String[] expected = {"1/2", "+", "3/4"};

        // Act
        String[] actual = MarcsFractionCalculator.splitInput(input);

        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    void splitInputWithNoSpacesAroundOperator() {
        //Arrange
        String input = "1/2+3/4";
        // Act and Assert
        Executable actual = () -> MarcsFractionCalculator.splitInput(input);
        // Assert
        Exception e = assertThrows(IllegalArgumentException.class, actual);

        // Arrange
        String expectedErrorMsg = "Invalid format";
        //Act
        String actualErrorMsg = e.getMessage();
        assertTrue(actualErrorMsg.contains(expectedErrorMsg));
    }

    @Test
    void splitInputWithInvalidOperator() {
        //Arrange
        String input = "1/2 ! 3/4";
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> MarcsFractionCalculator.splitInput(input));
    }

    @Test
    void splitInputWithInvalidFraction1() {
        //Arrange
        String input = " + 3/4";
        // Act and Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> MarcsFractionCalculator.splitInput(input));

        //Arrage
        String expectedErrorMsg = "First fraction is required";
        //Act
        String actualErrorMsg = e.getMessage();
        //Assert
        assertEquals(expectedErrorMsg, actualErrorMsg);
    }

    @Test
    void splitInputWithInvalidFraction2() {
        //Arrange
        String input = "1/2 + ";
        // Act and Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> MarcsFractionCalculator.splitInput(input));

        //Arrage
        String expectedErrorMsg = "Second fraction is required";
        //Act
        String actualErrorMsg = e.getMessage();
        //Assert
        assertEquals(expectedErrorMsg, actualErrorMsg);
    }

    @Test
    void parseFractionWithPositiveWholeNumber() {
        // Arrange
        String input = "5";
        Fraction expected = new Fraction(5,1);
        // Act
        Fraction actual = MarcsFractionCalculator.parseFraction(input);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void parseFractionWithNegativeWholeNumber() {
        Fraction f = MarcsFractionCalculator.parseFraction("-1");
        assertEquals(-1, f.getNumerator());
        assertEquals(1, f.getDenominator());
    }

    @Test
    void parseFractionWithProperPositive() {
        // Arrange
        String input = "3/4";
        Fraction expected = new Fraction(3,4);
        // Act
        Fraction actual = MarcsFractionCalculator.parseFraction(input);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void parseFractionWithProperNegative() {
        // Arrange
        String input = "-3/4";
        Fraction expected = new Fraction(-3,4);
        // Act
        Fraction actual = MarcsFractionCalculator.parseFraction(input);
        // Assert
        assertEquals(expected, actual);

        // Arrange
        input = "3/-4";
        expected = new Fraction(-3,4);
        // Act
        actual = MarcsFractionCalculator.parseFraction(input);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void parseFractionWithPositiveMixedNumber() {
        Fraction f = MarcsFractionCalculator.parseFraction("2 1/3");
        assertEquals(7, f.getNumerator());
        assertEquals(3, f.getDenominator());
    }

    @Test
    void parseFractionWithNegativeMixedNumber() {
        Fraction f = MarcsFractionCalculator.parseFraction("-2 1/3");
        assertEquals(-7, f.getNumerator());
        assertEquals(3, f.getDenominator());
    }

    @Test
    void parseFractionWithNegativeMixedNumber2() {
        assertThrows(IllegalArgumentException.class, () -> MarcsFractionCalculator.parseFraction("2 -1/3"));
    }

    @Test
    void parseFractionWithNegativeMixedNumber3() {
        assertThrows(IllegalArgumentException.class, () -> MarcsFractionCalculator.parseFraction("2 1/-3"));
    }

    @Test
    void parseFractionInvalidNumber() {
        assertThrows(IllegalArgumentException.class, () -> MarcsFractionCalculator.parseFraction("X"), "X is not a valid fraction");
    }

    @Test
    void parseFractionInvalidNumerator() {
        assertThrows(IllegalArgumentException.class, () -> MarcsFractionCalculator.parseFraction("X/1"), "X is not a valid fraction");
    }

    @Test
    void parseFractionInvalidDenominator() {
        assertThrows(IllegalArgumentException.class, () -> MarcsFractionCalculator.parseFraction("1/X"), "X is not a valid fraction");
    }

    @Test
    void parseFractionInvalidSlash() {
        // Arrange
        String input = "1 3!4";
        Class expected = IllegalArgumentException.class;
        // Act
        Executable actual = () -> MarcsFractionCalculator.parseFraction(input);
        // Assert
        assertThrows(expected, actual);
    }

    @Test
    void parseFractionZeroDenominator() {
        assertThrows(IllegalArgumentException.class, () -> MarcsFractionCalculator.parseFraction("1/0"));
    }

    @Test
    void parseFractionNonFractionCharacters() {
        assertThrows(IllegalArgumentException.class, () -> MarcsFractionCalculator.parseFraction("=1/2"));
        assertThrows(IllegalArgumentException.class, () -> MarcsFractionCalculator.parseFraction("1/=2"));
    }







}