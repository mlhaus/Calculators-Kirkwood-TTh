package edu.kirkwood.controller;

import edu.kirkwood.model.Measurement;
import edu.kirkwood.view.UIUtility;
import edu.kirkwood.view.UserInput;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AslesMeasurementCalculatorTest {
    @Test
    void measurementCalculator_starts_thenQuits()
    { // does not work
        try (

            MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
            MockedStatic<UIUtility> staticUIUtility = mockStatic(UIUtility.class)) {

            // Arrange
            staticUserInput.when(() -> UserInput.getInt(anyString(),anyBoolean(),anyInt(),anyInt())).thenReturn(5);

            // Act
            AslesMeasurementCalculator.start();

            //Assert
            staticUIUtility.verify(() -> UIUtility.displayError(anyString()), never());

        }
    }
    @Test
    void createMeasureQuitsWithQ()
    {
        try (

                MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
                MockedStatic<UIUtility> staticUIUtility = mockStatic(UIUtility.class)) {

            // Arrange
            staticUserInput.when(() -> UserInput.getString(anyString())).thenReturn("q");


            // Act
            AslesMeasurementCalculator.createAMeasurement();

            //Assert
            staticUIUtility.verify(() -> UIUtility.displayError(anyString()), never());

        }
    }

    @Test
    void createMeasureQuitsWithQuit()
    {
        try (

                MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
                MockedStatic<UIUtility> staticUIUtility = mockStatic(UIUtility.class)) {

            // Arrange
            staticUserInput.when(() -> UserInput.getString(anyString())).thenReturn("quit");


            // Act
            AslesMeasurementCalculator.createAMeasurement();

            //Assert
            staticUIUtility.verify(() -> UIUtility.displayError(anyString()), never());

        }
    }

    @Test
    void createMeasureDisplaysError()
    { // used chapt gpt to find out how to make it assert that it throws an execption
        try (

                MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);) {

            // Arrange
            staticUserInput.when(() -> UserInput.getString(anyString())).thenReturn("qsadwd");


            // Act and assert
            assertThrows(IllegalArgumentException.class, () -> {
                AslesMeasurementCalculator.createAMeasurement();
            });

            //Assert


        }
    }

    @Test
    void createMeasureThrowsExceptionWhenLengthLessThanZero()
    {
        try (

                MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
                ) {

            // Arrange
            staticUserInput.when(() -> UserInput.getString(anyString())).thenReturn("-1");


            // Act

            //Assert
            assertThrows(IllegalArgumentException.class, () -> AslesMeasurementCalculator.createAMeasurement());

        }
    }

    @Test
    void confirmMeasurementsThrowsIllegalArgumentExceptionWhenInvalidInput()
    {
        try (
                MockedStatic<UserInput> staticUserInput = mockStatic(UserInput.class);
                ){

                // Arrange
                Measurement mockMeasure = mock(Measurement.class);
                staticUserInput.when(() -> UserInput.getString(anyString())).thenReturn("-dadasd");

                // Act and Assert
                assertThrows(IllegalArgumentException.class, () -> AslesMeasurementCalculator.confirmMeasurements("gaba", mockMeasure ));

        }



    }

}
