package edu.kirkwood.view;

import org.junit.jupiter.api.Test;

import static edu.kirkwood.view.Helpers.isValidString;
import static edu.kirkwood.view.Helpers.toCurrency;
import static org.junit.jupiter.api.Assertions.*;

class HelpersTest {

    // Emilia
    @Test
    void isNullString() {
        // Arrange
        String actual = null;
        Boolean expected = false;
        // Act + Assert
        assertEquals(expected, isValidString(actual));
    }

    // Emilia
    @Test
    void isEmptyString() {
        // Arrange
        String actual = "";
        Boolean expected = false;
        // Act + Assert
        assertEquals(expected, isValidString(actual));
    }

    // Emilia
    @Test
    void stringIsValid() {
        // Arrange
        String actual = "Hooray!";
        Boolean expected = true;
        // Act + Assert
        assertEquals(expected, isValidString(actual));
    }

    // Celeste
    @Test
    void testToCurrencyWithWholeNumberAndDecimal(){
        double input = 1.23;
        String expected = "$1.23";

        String actual = toCurrency(input);

        assertEquals(expected, actual);
    }

    // Celeste
    @Test
    void testToCurrencyWithNegativeNumber(){
        double input = -1.23;
        String expected = "-$1.23";

        String actual = toCurrency(input);

        assertEquals(expected, actual);
    }
}