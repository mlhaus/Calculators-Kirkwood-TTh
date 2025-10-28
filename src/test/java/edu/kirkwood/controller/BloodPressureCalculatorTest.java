package edu.kirkwood.controller;

import edu.kirkwood.model.BloodPressure;
import edu.kirkwood.view.UIUtility;
import edu.kirkwood.view.UserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

class BloodPressureCalculatorTest {

    @Test
    @DisplayName("Test start with valid input then quit")
    void startWithValidInputThenQuit() {
        try (
            MockedStatic<UserInput> staticUserInput = Mockito.mockStatic(UserInput.class);
            MockedStatic<UIUtility> staticUIUtility = Mockito.mockStatic(UIUtility.class)) {
            // Arrange
            staticUserInput.when(() -> UserInput.getString(anyString()))
                    .thenReturn("120/80", "quit");
            // Act
            BloodPressureCalculator.start();
            // Assert
            staticUIUtility.verify(() -> UIUtility.displayError(anyString()), never());
        }
    }

    @Test
    @DisplayName("Test start with invalid input throws error")
    void startWithInvalidInput() {
        try (
            MockedStatic<UserInput> staticUserInput = Mockito.mockStatic(UserInput.class);
            MockedStatic<UIUtility> staticUIUtility = Mockito.mockStatic(UIUtility.class)) {
            // Arrange
            staticUserInput.when(() -> UserInput.getString(anyString()))
                    .thenReturn("120//80","quit");
            // Act
            BloodPressureCalculator.start();
            // Assert
            staticUIUtility.verify(() -> UIUtility.displayError(anyString()), times(1));
        }
    }


    private int expectedSystolic;
    private int expectedDiastolic;
    private String input;
    private BloodPressure actual;
    @BeforeEach
    void setUp() {
        expectedSystolic = 136;
        expectedDiastolic = 84;
    }

    @Test
    @DisplayName("Test split pressure with valid blood pressure")
    void splitPressureValidInput() {
        // Arrange
        input = "136/84";
        // Act
        actual = BloodPressureCalculator.splitPressure(input);
        // Assert
        assertEquals(expectedSystolic, actual.getSystolic());
        assertEquals(expectedDiastolic, actual.getDiastolic());
    }


    @Test
    @DisplayName("Test split pressure throws error for invalid input")
    void splitPressureWithInvalidInput() {
        // Arrange
        input = "eighty/sixty";
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> BloodPressureCalculator.splitPressure(input));

    }

    @Test
    @DisplayName("Test split pressure handles spaces in the around the division bar")
    void splitPressureWithSpaces() {
        // Spaces around the division bar
        // Arrange
        input = "136 / 84";
        // Act
        actual = BloodPressureCalculator.splitPressure(input);
        // Assert
        assertEquals(expectedSystolic, actual.getSystolic());
        assertEquals(expectedDiastolic, actual.getDiastolic());
    }

    @Test
    @DisplayName("Test split pressure handles erroneous spaces throughout")
    void splitPressureWithErroneousSpaces() {
        // Many spaces in input
        // Arrange
        input = "13 6 / 8 4";
        // Act
        actual = BloodPressureCalculator.splitPressure(input);
        // Assert
        assertEquals(expectedSystolic, actual.getSystolic());
        assertEquals(expectedDiastolic, actual.getDiastolic());
    }

    @Test
    @DisplayName("Test split pressure handles erroneous invalid characters")
    void splitPressureWithInvalidCharacters() {
        // Arrange
        input = "136^/8$4";
        // Act
        actual =  BloodPressureCalculator.splitPressure(input);
        // Assert
        assertEquals(expectedSystolic, actual.getSystolic());
        assertEquals(expectedDiastolic, actual.getDiastolic());
    }

    @Test
    @DisplayName("Test split pressure throws error for missing systolic")
    void splitPressureWithMissingSystolic() {
        // Arrange
        input = "/105";
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> BloodPressureCalculator.splitPressure(input));
    }

    @Test
    @DisplayName("Test split pressure throws error for missing diastolic")
    void splitPressureWithMissingDiastolic() {
        // Arrange
        input = "120/";
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> BloodPressureCalculator.splitPressure(input));
    }
}
