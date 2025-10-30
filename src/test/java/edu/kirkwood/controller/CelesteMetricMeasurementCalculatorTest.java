package edu.kirkwood.controller;

import edu.kirkwood.model.CelesteMetricMeasurement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static edu.kirkwood.controller.CelesteMetricMeasurementCalculator.*;
import static org.junit.jupiter.api.Assertions.*;

class CelesteMetricMeasurementCalculatorTest {

    @Test
    void splitInputCM() {
        // Arrange
        String input = "5cm";
        String[] expected = {"5", "cm"};
        // Act
        String[] actual = splitInput(input);
        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    void splitInputM() {
        // Arrange
        String input = "5m";
        String[] expected = {"5", "m"};
        // Act
        String[] actual = splitInput(input);
        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    void splitInputLongerNumber() {
        // Arrange
        String input = "1234m";
        String[] expected = {"1234", "m"};
        // Act
        String[] actual = splitInput(input);
        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    void splitInputWithSpaceBetweenNumberAndUnit() {
        // Arrange
        String input = "50 cm";
        String[] expected = {"50", "cm"};
        // Act
        String[] actual = splitInput(input);
        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    void splitInputInvalidUnit() {
        // Arrange
        String input = "5gamer";
        // Act
        Executable actual = () -> splitInput(input);
        // Assert
        Exception e = assertThrows(IllegalArgumentException.class, actual);

        //Arrange
        String expectedErrorMsg = "Invalid measurement. Make sure the unit is one of the following abbreviations: 'mm', 'cm', 'm', 'km'";
        //Act
        String actualErrorMsg = e.getMessage();
        //Assert
        assertEquals(expectedErrorMsg, actualErrorMsg);
    }

    @Test
    void splitInputInvalidNumber() {
        // Arrange
        String input = "gamercm";
        // Act
        Executable actual = () -> splitInput(input);
        // Assert
        Exception e = assertThrows(IllegalArgumentException.class, actual);

        //Arrange
        String expectedErrorMsg = "Invalid measurement. Make sure the input begins with a number.";
        //Act
        String actualErrorMsg = e.getMessage();
        //Assert
        assertEquals(expectedErrorMsg, actualErrorMsg);
    }


    @Test
    void parseValidCM() {
        String[] input = {"5", "cm"};
        CelesteMetricMeasurement expected = new CelesteMetricMeasurement(5, "cm");

        CelesteMetricMeasurement actual = parseInput(input);

        assertEquals(expected, actual);
    }

    @Test
    void parseValidM() {
        String[] input = {"5", "m"};
        CelesteMetricMeasurement expected = new CelesteMetricMeasurement(5, "m");

        CelesteMetricMeasurement actual = parseInput(input);

        assertEquals(expected, actual);
    }

    @Test
    void parseInvalidNumber() {
        String[] input = {"gamer", "cm"};

        // Act
        Executable actual = () -> parseInput(input);
        // Assert
        Exception e = assertThrows(IllegalArgumentException.class, actual);

        //Arrange
        String expectedErrorMsg = "Invalid measurement. Make sure the input begins with a number.";
        //Act
        String actualErrorMsg = e.getMessage();
        //Assert
        assertEquals(expectedErrorMsg, actualErrorMsg);
    }

    @Test
    void parseInvalidUnit() {
        String[] input = {"5", "pm"};

        // Act
        Executable actual = () -> parseInput(input);
        // Assert
        Exception e = assertThrows(IllegalArgumentException.class, actual);

        //Arrange
        String expectedErrorMsg = "Invalid measurement. Make sure the unit is one of the following abbreviations: 'mm', 'cm', 'm', 'km'";
        //Act
        String actualErrorMsg = e.getMessage();
        //Assert
        assertEquals(expectedErrorMsg, actualErrorMsg);
    }

    @Test
    void splitInputNegativeNumber() {
        // Arrange
        String[] input = {"-5", "cm"};
        // Act
        Executable actual = () -> parseInput(input);
        // Assert
        Exception e = assertThrows(IllegalArgumentException.class, actual);

        //Arrange
        String expectedErrorMsg = "Invalid measurement. Number cannot be negative.";
        //Act
        String actualErrorMsg = e.getMessage();
        //Assert
        assertEquals(expectedErrorMsg, actualErrorMsg);
    }

    @Test
    void convertToKM() {
        CelesteMetricMeasurement input = new CelesteMetricMeasurement(5, "cm");
        CelesteMetricMeasurement expected = new CelesteMetricMeasurement(0.00005, "km");

        CelesteMetricMeasurement actual = convert(input, "km");

        assertEquals(expected, actual);
    }

    @Test
    void convertToMM() {
        CelesteMetricMeasurement input = new CelesteMetricMeasurement(5, "cm");
        CelesteMetricMeasurement expected = new CelesteMetricMeasurement(50, "mm");

        CelesteMetricMeasurement actual = convert(input, "mm");

        assertEquals(expected, actual);
    }

    @Test
    void convertToInvalidUnit() {
        CelesteMetricMeasurement input = new CelesteMetricMeasurement(5, "cm");
        // Act
        Executable actual = () -> convert(input, "gamer");
        // Assert
        Exception e = assertThrows(IllegalArgumentException.class, actual);

        //Arrange
        String expectedErrorMsg = "Invalid target unit.";
        //Act
        String actualErrorMsg = e.getMessage();
        //Assert
        assertEquals(expectedErrorMsg, actualErrorMsg);
    }
}