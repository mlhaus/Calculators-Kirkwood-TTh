package edu.kirkwood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TemperatureTest {
    private Temperature t1;
    private Temperature t2;

    @BeforeEach
    void setUp() {
        t1 = new Temperature();
        t2 = new Temperature(87, "F");
    }

    @Test
    @DisplayName("Temperature class throws exception for invalid scale.")
    void InputWithInvalidScale() {
         assertThrows(IllegalArgumentException.class, () ->{new Temperature(-99, "X");});

    }

    @Test
    @DisplayName("Temperature class throws exception for invalid degree.")
    void InputWithInvalidDegree() {
        // Celsius below -273.15°C
        assertThrows(IllegalArgumentException.class, () ->{new Temperature(-500, "C");});

        // Fahrenheit below -459.67°F
        assertThrows(IllegalArgumentException.class, () ->{new Temperature(-500, "F");});

        // Kelvin below 0°K
        assertThrows(IllegalArgumentException.class, () ->{new Temperature(-8, "K");});
    }


    @Test
    void getDegree() {
        assertEquals(0,t1.getDegree());
        assertEquals(87,t2.getDegree());
    }

    @Test
    void setDegree() {
        t1.setDegree(5); // Was originally 0
        assertEquals(5,t1.getDegree());
        t2.setDegree(0); // Was 87
        assertEquals(0,t2.getDegree());
        t1.setDegree(30);
        assertEquals(30,t1.getDegree());
    }

    @Test
    void getScale() {
        assertEquals("C",t1.getScale());
        assertEquals("F",t2.getScale());
    }

    @Test
    void setScale() {
        t1.setScale("F"); // Was originally "C"
        assertEquals("F",t1.getScale());
        t2.setScale("K"); // Was "F"
        assertEquals("K",t2.getScale());
    }

    @Test
    void testToString() {
//        assertEquals("0.00°C",t1.toString());
//        assertEquals("87.00°F",t2.toString());
    }

    @Test
    @DisplayName("Test to convert a Temperature from Celsius to Fahrenheit")
    void testConvertCtoF() {
        Temperature t3 = new Temperature(32.00,"C");
        assertEquals("89.60°F",t3.convertTo("F"));
    }

    @Test
    @DisplayName("Test to convert a Temperature from Celsius to Kelvin")
    void testConvertCtoK() {
        Temperature t3 = new Temperature(32.00,"C");
        assertEquals("305.15°K",t3.convertTo("K"));
    }


    @Test
    @DisplayName("Test to add two Temperatures of the same scale")
    void addSameScale() {
        Temperature t4 = new Temperature(-20.00,"C");
        Temperature t5 = new Temperature(40.00,"C");
        Temperature result = t4.add(t5);
        assertEquals(20,result.getDegree());
        assertEquals("C",result.getScale());
    }

    @Test
    @DisplayName("Test to add two Temperatures with different scales")
    void addDifferentScale() {
        Temperature t1 = new Temperature(37.0, "C");
        Temperature t2 = new Temperature(19.4, "F"); // equals -7°C
        Temperature result = t1.add(t2);
        assertEquals(30.0, result.getDegree());
        assertEquals("C", result.getScale());
    }

    @Test
    @DisplayName("Subtract two temperatures with the same scale")
    void subtractSameScale() {
        Temperature t1 = new Temperature(87, "F");
        Temperature t2 = new Temperature(50, "F");
        Temperature result = t1.subtract(t2);
        assertEquals(37,result.getDegree());
        assertEquals("F",result.getScale());
    }

    @Test
    @DisplayName("Subtract two temperatures with different scales")
    void subtractDifferentScale() {
        Temperature t1 = new Temperature(30.0, "C");
        Temperature t2 = new Temperature(19.4, "F"); // equals -7°C
        Temperature result = t1.subtract(t2);
        assertEquals(37.0, result.getDegree());
        assertEquals("C", result.getScale());
    }
}