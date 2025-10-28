package edu.kirkwood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class BloodPressureTest {
    private BloodPressure _bloodPressure = null;

    @BeforeEach
    void setUp() {
        _bloodPressure = new BloodPressure();
    }


    @Test
    void testGetSystolic() {
        // arrange
        int expectedSystolic = 120;
        // act
        int actualSystolic = _bloodPressure.getSystolic();
        // assert
        assertEquals(expectedSystolic, actualSystolic);
    }

    @Test
    void testSetSystolic() {
        // arrange
        int expectedSystolic = 100;
        // act
        _bloodPressure.setSystolic(100);
        int actualSystolic = _bloodPressure.getSystolic();
        // assert
        assertEquals(expectedSystolic, actualSystolic);
    }

    @Test
    void testSetSystolicThrowsExceptionForNegativeIntegers() {
        assertThrows(IllegalArgumentException.class, () -> _bloodPressure.setSystolic(-40));
    }

    @Test
    void testSetSystolicThrowsExceptionForZero() {
        assertThrows(IllegalArgumentException.class, () -> _bloodPressure.setSystolic(0));
    }

    @Test
    void testSetSystolicThrowsExceptionForLessThanDiastolic() {
        assertThrows(IllegalArgumentException.class, () -> _bloodPressure.setSystolic(65));
    }

    @Test
    void testSetSystolicThrowsExceptionForEqualToDiastolic() {
        assertThrows(IllegalArgumentException.class, () -> _bloodPressure.setSystolic(80));
    }

    @Test
    void testSetSystolicThrowsExceptionPastUpperLimit() {
        assertThrows(IllegalArgumentException.class, () -> _bloodPressure.setSystolic(401));
    }

    @Test
    void testGetDiastolic() {
        // arrange
        int expectedDiastolic = 80;
        // act
        int actualDiastolic = _bloodPressure.getDiastolic();
        // assert
        assertEquals(expectedDiastolic, actualDiastolic);
    }

    @Test
    void testSetDiastolic() {
        // arrange
        int expectedDiastolic = 100;
        // act
        _bloodPressure.setDiastolic(100);
        int actualDiastolic = _bloodPressure.getDiastolic();
        // assert
        assertEquals(expectedDiastolic, actualDiastolic);
    }

    @Test
    void testSetDiastolicThrowsExceptionForNegativeIntegers() {
        assertThrows(IllegalArgumentException.class, () -> _bloodPressure.setDiastolic(-40));
    }

    @Test
    void testSetDiastolicThrowsExceptionForZero() {
        assertThrows(IllegalArgumentException.class, () -> _bloodPressure.setDiastolic(0));
    }

    @Test
    void testSetDiastolicThrowsExceptionForMoreThanSystolic() {
        assertThrows(IllegalArgumentException.class, () -> _bloodPressure.setDiastolic(125));
    }

    @Test
    void testSetDiastolicThrowsExceptionForEqualToSystolic() {
        assertThrows(IllegalArgumentException.class, () -> _bloodPressure.setSystolic(80));
    }

    @Test
    void testSetDiastolicThrowsExceptionPastUpperLimit() {
        assertThrows(IllegalArgumentException.class, () -> _bloodPressure.setDiastolic(301));
    }

    @Test
    void testCalculateMAP() {
        // arrange
        int expectedMAP = 93;
        // act
        int actualMAP = _bloodPressure.calculateMAP();
        // assert
        assertEquals(expectedMAP, actualMAP);
    }

    @Test
    void testToString() {
        // arrange
        String expected = "120/80";
        // act
        String actual = _bloodPressure.toString();
        // assert
        assertEquals(expected, actual);
    }
}

