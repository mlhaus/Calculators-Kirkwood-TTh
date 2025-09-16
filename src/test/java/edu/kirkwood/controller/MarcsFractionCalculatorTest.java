package edu.kirkwood.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class MarcsFractionCalculatorTest {

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
        assertThrows(IllegalArgumentException.class, actual);
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









}