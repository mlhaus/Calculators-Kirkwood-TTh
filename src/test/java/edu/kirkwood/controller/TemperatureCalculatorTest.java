package edu.kirkwood.controller;

import edu.kirkwood.model.Temperature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TemperatureCalculatorTest {
    private TemperatureCalculator calculator;
    @BeforeEach
    void setUp()
    {
        calculator = new TemperatureCalculator();
    }

    @Test
    @DisplayName("Parse valid input string into Temperature object")
    void startWithValidInput() {
        // Arrange
        String input = "87 F";

        // Act
        Temperature temp = calculator.parseTemperatureInput(input);

        // Assert
        assertEquals(87.0, temp.getDegree());
        assertEquals("F",temp.getScale());
    }

    @Test
    void parseInvalidInput() {
        // Arrange
        String input = "100C";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> calculator.parseTemperatureInput(input));
    }

    @Test
    @DisplayName("Parsing Non-numeric value into Temperature object")
    void parseNonNumericValue(){
        // Arrange
        String input = "xyz K";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> calculator.parseTemperatureInput(input));
    }

    @Test
    @DisplayName("Convert temperature correctly from Celsius to Fahrenheit")
    void convertTemperatureCtoF(){
        // Arrange
        Temperature t = new Temperature(32.00,"C");

        // Act
        String result = calculator.convertTemperature(t,"F");

        // Assert
        assertEquals("89.60°F",result);
    }

    @Test
    @DisplayName("Convert temperature correctly from Celsius to Kelvin")
    void convertTemperatureCtoK(){
        // Arrange
        Temperature t = new Temperature(32.00,"C");

        // Act
        String result = calculator.convertTemperature(t,"K");

        // Assert
        assertEquals("305.15°K",result);
    }

    @Test
    @DisplayName("Add two temperatures of different scales")
    void addTemperatureDifferentScales(){
        // Arrange
        Temperature t1 = new Temperature(37.00,"C");
        Temperature t2 = new Temperature(19.40,"F");

        // Act
        Temperature result = calculator.add(t1,t2);

        // Assert
        assertEquals(30,result.getDegree());
        assertEquals("C",result.getScale());
    }

    @Test
    @DisplayName("Add two temperatures of the same scales")
    void addTemperatureSameScales(){
        // Arrange
        Temperature t1 = new Temperature(-25.00,"C");
        Temperature t2 = new Temperature(30.00,"C");

        // Act
        Temperature result = calculator.add(t1,t2);

        // Assert
        assertEquals(5,result.getDegree());
        assertEquals("C",result.getScale());
    }

    @Test
    @DisplayName("Substract two temperatures of different scales")
    void substractTemperatureDifferentScales(){
        // Arrange
        Temperature t1 = new Temperature(30.00,"C");
        Temperature t2 = new Temperature(19.40,"F");

        // Act
        Temperature result = calculator.subtract(t1,t2);

        // Assert
        assertEquals(37,result.getDegree());
        assertEquals("C",result.getScale());
    }

    @Test
    @DisplayName("Substract two temperatures of the same scales")
    void substractTemperatureSameScales(){
        // Arrange
        Temperature t1 = new Temperature(87.00,"F");
        Temperature t2 = new Temperature(50.00,"F");

        // Act
        Temperature result = calculator.subtract(t1,t2);

        // Assert
        assertEquals(37,result.getDegree());
        assertEquals("F",result.getScale());
    }



}