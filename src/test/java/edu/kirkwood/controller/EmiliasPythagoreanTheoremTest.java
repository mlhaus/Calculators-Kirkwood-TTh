package edu.kirkwood.controller;

import edu.kirkwood.model.PythagoreanTheorem;
import edu.kirkwood.view.UIUtility;
import edu.kirkwood.view.UserInput;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class EmiliasPythagoreanTheoremTest {

    @Test
    void startWithValidInput() {
        try (
                MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
                MockedStatic<UIUtility> staticUIUtility = mockStatic(UIUtility.class)) {


            staticUserInput.when(() -> UserInput.getString(anyString())).thenReturn("5.25 3.23", "quit");
            EmiliasPythagoreanTheorem.start();
            staticUIUtility.verify(() -> UIUtility.displayError(anyString()), never());
        }
    }

    @Test
    void startWithInvalidInput() {
        try (
                MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
                MockedStatic<UIUtility> staticUIUtility = mockStatic(UIUtility.class)) {
            staticUserInput.when(() -> UserInput.getString(anyString())).thenReturn("0 -1", "quit");
            EmiliasPythagoreanTheorem.start();
            staticUIUtility.verify(() -> UIUtility.displayError(anyString()), times(1));
        }
    }

    @Test
    void splitInputValid() {
        // Arrange
        String input = "4 5 2";
        String[] expected = {"4","5","2"};
        // Act
        String[] actual = EmiliasPythagoreanTheorem.splitInput(input);
        // Assert
        assertArrayEquals(expected, actual);
    }


    @Test
    void splitInputEmpty() {
        // Arrange
        String input = "";

        // Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> EmiliasPythagoreanTheorem.splitInput(input));

        //Arrange
        String expectedErrorMsg = "Input must not be empty";
        //Act
        String actualErrorMsg = e.getMessage();
        //Assert
        assertEquals(expectedErrorMsg, actualErrorMsg);

    }


    @Test
    void splitInputSingleDoubleSpace() {
        // Arrange
        String input = "52";

        // Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> EmiliasPythagoreanTheorem.splitInput(input));

        //Arrange
        String expectedErrorMsg = "A space between two separate numbers is required.";
        //Act
        String actualErrorMsg = e.getMessage();
        //Assert
        assertEquals(expectedErrorMsg, actualErrorMsg);

    }




    @Test
    void parseDoublesValid() {
        // Arrange
        String[] input = {"4.0","5.0","2"};
        PythagoreanTheorem expected = new PythagoreanTheorem(4.0, 5.0, 2);
        // Act
        PythagoreanTheorem actual = EmiliasPythagoreanTheorem.parseArr(input[0], input[1], input[2]);
        // Assert
        assertEquals(expected.getA(), actual.getA());
        assertEquals(expected.getB(), actual.getB());

    }

    @Test
    void parseDoublesZero() {
        // Arrange
        String[] input = {"4","0","2"};

        // Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> EmiliasPythagoreanTheorem.parseArr(input[0], input[1], input[2]));

        //Arrange
        String expectedErrorMsg = "Input must not contain zeros.";
        //Act
        String actualErrorMsg = e.getMessage();
        //Assert
        assertEquals(expectedErrorMsg, actualErrorMsg);
    }

    @Test
    void parseDoublesNegative() {
        // Arrange
        String[] input = {"4","-9","3"};

        // Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> EmiliasPythagoreanTheorem.parseArr(input[0], input[1], input[2]));

        //Arrange
        String expectedErrorMsg = "Input must only have positive values.";
        //Act
        String actualErrorMsg = e.getMessage();
        //Assert
        assertEquals(expectedErrorMsg, actualErrorMsg);
    }
}