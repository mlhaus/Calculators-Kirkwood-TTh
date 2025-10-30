package edu.kirkwood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CelesteMetricMeasurementTest {

    CelesteMetricMeasurement test1;
    CelesteMetricMeasurement test2;

    @BeforeEach
    void setUp() {
        test1 = new CelesteMetricMeasurement();
        test2 = new CelesteMetricMeasurement();
    }

    @Test
    void setValuePositive() {
        double expected = 1;
        CelesteMetricMeasurement actual = new CelesteMetricMeasurement();

        actual.setValue(1);

        assertEquals(expected, actual.getValue());
    }

    @Test
    void setValueNegative() { //Should fail with a negative, measurements can't be negative
        CelesteMetricMeasurement actual = new CelesteMetricMeasurement();
        assertThrows(IllegalArgumentException.class, () -> actual.setValue(-1));
    }

    @Test
    void setValidUnitAbbreviation() {
        String expected = "mm";
        CelesteMetricMeasurement actual = new CelesteMetricMeasurement();

        actual.setUnit("mm");

        assertEquals(expected, actual.getUnit());
    }

    @Test
    void setInvalidUnit() {
        CelesteMetricMeasurement actual = new CelesteMetricMeasurement();
        assertThrows(IllegalArgumentException.class, () -> actual.setUnit("Joe Accounting"));
    }

    @Test
    void getValue() {
        double expected = 1;
        CelesteMetricMeasurement actual = new CelesteMetricMeasurement(1, "mm");

        assertEquals(expected, actual.getValue());
    }

    @Test
    void getUnit() {
        String expected = "mm";
        CelesteMetricMeasurement actual = new CelesteMetricMeasurement(1, "mm");

        assertEquals(expected, actual.getUnit());
    }

    @Test
    void toMMFromCM() {
        CelesteMetricMeasurement meas = new CelesteMetricMeasurement(10, "cm");
        double expected = 100;
        String expectedUnit = "mm";

        CelesteMetricMeasurement actual = meas.toMM();

        assertEquals(expected, actual.getValue());
        assertEquals(expectedUnit, actual.getUnit());

    }

    @Test
    void toMMFromM() {
        CelesteMetricMeasurement meas = new CelesteMetricMeasurement(10, "m");
        double expected = 10000;
        String expectedUnit = "mm";

        CelesteMetricMeasurement actual = meas.toMM();

        assertEquals(expected, actual.getValue());
        assertEquals(expectedUnit, actual.getUnit());

    }

    @Test
    void toMMFromKM() {
        CelesteMetricMeasurement meas = new CelesteMetricMeasurement(10, "km");
        double expected = 10000000;
        String expectedUnit = "mm";

        CelesteMetricMeasurement actual = meas.toMM();

        assertEquals(expected, actual.getValue());
        assertEquals(expectedUnit, actual.getUnit());

    }

    @Test
    void toMMFromMM() {
        CelesteMetricMeasurement meas = new CelesteMetricMeasurement(10, "mm");
        double expected = 10;
        String expectedUnit = "mm";

        CelesteMetricMeasurement actual = meas.toMM();

        assertEquals(expected, actual.getValue());
        assertEquals(expectedUnit, actual.getUnit());

    }

    @Test
    void toCMFromMM() {
        CelesteMetricMeasurement meas = new CelesteMetricMeasurement(10, "cm");
        double expected = 100;
        String expectedUnit = "mm";

        CelesteMetricMeasurement actual = meas.toMM();

        assertEquals(expected, actual.getValue());
        assertEquals(expectedUnit, actual.getUnit());

    }

    @Test
    void toCMFromM() {
        CelesteMetricMeasurement meas = new CelesteMetricMeasurement(10, "m");
        double expected = 1000;
        String expectedUnit = "cm";

        CelesteMetricMeasurement actual = meas.toCM();

        assertEquals(expected, actual.getValue());
        assertEquals(expectedUnit, actual.getUnit());

    }

    @Test
    void toCMFromKM() {
        CelesteMetricMeasurement meas = new CelesteMetricMeasurement(10, "km");
        double expected = 1000000;
        String expectedUnit = "cm";

        CelesteMetricMeasurement actual = meas.toCM();

        assertEquals(expected, actual.getValue());
        assertEquals(expectedUnit, actual.getUnit());

    }

    @Test
    void toCMFromCM() {
        CelesteMetricMeasurement meas = new CelesteMetricMeasurement(10, "cm");
        double expected = 10;
        String expectedUnit = "cm";

        CelesteMetricMeasurement actual = meas.toCM();

        assertEquals(expected, actual.getValue());
        assertEquals(expectedUnit, actual.getUnit());

    }

    @Test
    void toMFromCM() {
        CelesteMetricMeasurement meas = new CelesteMetricMeasurement(10, "cm");
        double expected = .1;
        String expectedUnit = "m";

        CelesteMetricMeasurement actual = meas.toM();

        assertEquals(expected, actual.getValue());
        assertEquals(expectedUnit, actual.getUnit());

    }

    @Test
    void toMFromM() {
        CelesteMetricMeasurement meas = new CelesteMetricMeasurement(10, "m");
        double expected = 10000;
        String expectedUnit = "mm";

        CelesteMetricMeasurement actual = meas.toMM();

        assertEquals(expected, actual.getValue());
        assertEquals(expectedUnit, actual.getUnit());

    }

    @Test
    void toMFromKM() {
        CelesteMetricMeasurement meas = new CelesteMetricMeasurement(10, "km");
        double expected = 10000;
        String expectedUnit = "m";

        CelesteMetricMeasurement actual = meas.toM();

        assertEquals(expected, actual.getValue());
        assertEquals(expectedUnit, actual.getUnit());

    }

    @Test
    void toMFromMM() {
        CelesteMetricMeasurement meas = new CelesteMetricMeasurement(10, "mm");
        double expected = .01;
        String expectedUnit = "m";

        CelesteMetricMeasurement actual = meas.toM();

        assertEquals(expected, actual.getValue());
        assertEquals(expectedUnit, actual.getUnit());

    }

    @Test
    void toKMFromCM() {
        CelesteMetricMeasurement meas = new CelesteMetricMeasurement(10, "cm");
        double expected = .0001;
        String expectedUnit = "km";

        CelesteMetricMeasurement actual = meas.toKM();

        assertEquals(expected, actual.getValue());
        assertEquals(expectedUnit, actual.getUnit());

    }

    @Test
    void toKMFromM() {
        CelesteMetricMeasurement meas = new CelesteMetricMeasurement(10, "m");
        double expected = .01;
        String expectedUnit = "km";

        CelesteMetricMeasurement actual = meas.toKM();

        assertEquals(expected, actual.getValue());
        assertEquals(expectedUnit, actual.getUnit());

    }

    @Test
    void toKMFromKM() {
        CelesteMetricMeasurement meas = new CelesteMetricMeasurement(10, "km");
        double expected = 10;
        String expectedUnit = "km";

        CelesteMetricMeasurement actual = meas.toKM();

        assertEquals(expected, actual.getValue());
        assertEquals(expectedUnit, actual.getUnit());

    }

    @Test
    void toKMFromMM() {
        CelesteMetricMeasurement meas = new CelesteMetricMeasurement(10, "mm");
        double expected = .00001;
        String expectedUnit = "km";

        CelesteMetricMeasurement actual = meas.toKM();

        assertEquals(expected, actual.getValue());
        assertEquals(expectedUnit, actual.getUnit());

    }

    @Test
    void testToString() {
        CelesteMetricMeasurement actual = new CelesteMetricMeasurement(10, "mm");
        String expected = "10mm";
        assertEquals(expected, actual.toString());
    }

    @Test
    void testEqual() {
        test1.setUnit("cm");
        test1.setValue(10);
        test2.setUnit("cm");
        test2.setValue(10);
        boolean expected = true;
        boolean actual = test1.equals(test2);

        assertEquals(expected, actual);
    }

    @Test
    void testUnequalNumber() {
        test1.setUnit("cm");
        test1.setValue(100);
        test2.setUnit("cm");
        test2.setValue(10);
        boolean expected = false;
        boolean actual = test1.equals(test2);

        assertEquals(expected, actual);
    }

    @Test
    void testUnequalUnit() {
        test1.setUnit("m");
        test1.setValue(10);
        test2.setUnit("cm");
        test2.setValue(10);
        boolean expected = false;
        boolean actual = test1.equals(test2);

        assertEquals(expected, actual);
    }
}