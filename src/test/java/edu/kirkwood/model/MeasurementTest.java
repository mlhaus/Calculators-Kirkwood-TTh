package edu.kirkwood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MeasurementTest {

    private Measurement m1; 
    private Measurement m2;
    private Measurement m3;
    private Measurement m4;
    private Measurement m5;
    private Measurement m6;
    private Measurement m7;

    @BeforeEach 
    void setUp() {
        
        m1 = new Measurement(4, "Inches");
        m2 = new Measurement();
        m3 = new Measurement(4, "Feet");
        m4 = new Measurement(4, "Yards");
        m5 = new Measurement(4,"Miles");
        m6 = new Measurement(4, "Centimeters");
        m7 = new Measurement(4, "Kilometers");
    }

    @Test
    void addMeasurementsAddsSameMeasurement() {
        m4 = Measurement.addMeasurements(m3,m3,"Feet");
        assertEquals(8.0, m4.getLength());
        assertEquals("Feet", m4.getUnit());
    }

    @Test
    void addMeasurementsAddsDifferentMeasurement() {
        m5 = Measurement.addMeasurements(m3,m4,"Feet");
        assertEquals(16.0, m5.getLength());
        assertEquals("Feet", m5.getUnit());
    }

    @Test
    void subtractMeasurementsSubtractsSameMeasurement() {
        m5.setUnit("Feet");
        m5.setLength(8.0);
        m4 = Measurement.subtractMeasurements(m3,m5,"Feet");
        assertEquals(4.0, m4.getLength());
        assertEquals("Feet", m4.getUnit());
    }

    @Test
    void subtractMeasurementsSubtractsDifferentMeasurement() {
        m5 = Measurement.subtractMeasurements(m3,m4,"Feet");
        assertEquals(8.0, m5.getLength());
        assertEquals("Feet", m5.getUnit());
    }

    @Test
    void getLength() {
        assertEquals(4, m1.getLength()); 
        assertEquals(1, m2.getLength());
    }

    @Test
    void setLength() {
        m1.setLength(2); // Was 4
        assertEquals(2, m1.getLength());
        m2.setLength(2); // Was 2
        assertEquals(2, m2.getLength());
    } 

    @Test 
    void getUnit() {
        assertEquals("Inches", m1.getUnit());
        assertEquals("Meters", m2.getUnit());
    }

    @Test 
    void setUnit() {
        m1.setUnit("Kilometers"); // Was Inches
        assertEquals("Kilometers", m1.getUnit());
        m2.setUnit("Kilometers"); // Was Meters
        assertEquals("Kilometers", m2.getUnit());
    }

    //Start inches test
    @Test 
    void testInchesToFeet() {
        m1 = Measurement.inchToUnit(m1, "Feet");
        assertEquals(4.0 / 12, m1.getLength());
        assertEquals("Feet", m1.getUnit());
    }
    @Test 
    void testInchesToYards() {
        m1 = Measurement.inchToUnit(m1, "Yards");
        assertEquals(4.0 / 36, m1.getLength());
        assertEquals("Yards", m1.getUnit());
    }
    @Test 
    void testInchesToMiles() {
        m1 = Measurement.inchToUnit(m1, "Miles"); 
        assertEquals(4.0 / 63360, m1.getLength());
        assertEquals("Miles", m1.getUnit());
    }
    @Test 
    void testInchesToCentimeters() {
        m1 = Measurement.inchToUnit(m1, "Centimeters"); 
        assertEquals(4.0 * 2.54, m1.getLength());
        assertEquals("Centimeters", m1.getUnit());
    }
    @Test 
    void testInchesToMeters() {
        m1 = Measurement.inchToUnit(m1, "Meters"); 
        assertEquals(4.0 / 39.37, m1.getLength());
        assertEquals("Meters", m1.getUnit());
    }
    @Test 
    void testInchesToKilometers() {
        m1 = Measurement.inchToUnit(m1, "Kilometers"); 
        assertEquals(4.0 / 39370, m1.getLength());
        assertEquals("Kilometers", m1.getUnit());
    }
    @Test
    void testInchesToInches() {
        m1 = Measurement.inchToUnit(m1, "Inches");
        assertEquals(4.0 , m1.getLength());
        assertEquals("Inches", m1.getUnit());
    }
    // End inches test
    // Start meters test
    @Test
    void testMetersToInches() {
        m2 = Measurement.meterToUnit(m2, "Inches"); 
        assertEquals(1.0 * 39.37, m2.getLength());
        assertEquals("Inches", m2.getUnit());
    }
    @Test 
    void testMetersToFeet() {
        m2 = Measurement.meterToUnit(m2, "Feet"); 
        assertEquals(1.0 * 3.281, m2.getLength());
        assertEquals("Feet", m2.getUnit());
    }
    @Test 
    void testMetersToYards() {
        m2 = Measurement.meterToUnit(m2, "Yards"); 
        assertEquals(1.0 * 1.094, m2.getLength());
        assertEquals("Yards", m2.getUnit());
    }
    @Test 
    void testMetersToMiles() {
        m2 = Measurement.meterToUnit(m2, "Miles"); 
        assertEquals(1.0 / 1609, m2.getLength());
        assertEquals("Miles", m2.getUnit());
    }
    @Test 
    void testMetersToCentimeters() {
        m2 = Measurement.meterToUnit(m2, "Centimeters"); 
        assertEquals(1.0 * 100, m2.getLength());
        assertEquals("Centimeters", m2.getUnit());
    }
    @Test 
    void testMetersToKilometers() {
        m2 = Measurement.meterToUnit(m2, "Kilometers"); 
        assertEquals(1.0 / 1000, m2.getLength());
        assertEquals("Kilometers", m2.getUnit());
    }
    @Test
    void testMetersToMeters() {
        m2 = Measurement.meterToUnit(m2, "Meters");
        assertEquals(1.0 , m2.getLength());
        assertEquals("Meters", m2.getUnit());
    }

    // end meters test
    // start feet test
    @Test
    void testFeetToInches() {
        m3 = Measurement.feetToUnit(m3, "Inches");
        assertEquals(4.0 * 12, m3.getLength());
        assertEquals("Inches", m3.getUnit());
    }
    @Test
    void testFeetToYards() {
        m3 = Measurement.feetToUnit(m3, "Yards");
        assertEquals(4.0 / 3, m3.getLength());
        assertEquals("Yards", m3.getUnit());
    }
    @Test
    void testFeetToMiles() {
        m3 = Measurement.feetToUnit(m3, "Miles");
        assertEquals(4.0 / 5280, m3.getLength());
        assertEquals("Miles", m3.getUnit());
    }
    @Test
    void testFeetToCentimeters() {
        m3 = Measurement.feetToUnit(m3, "Centimeters");
        assertEquals(4.0 * 30.48, m3.getLength());
        assertEquals("Centimeters", m3.getUnit());
    }
    @Test
    void testFeetToMeters() {
        m3 = Measurement.feetToUnit(m3, "Meters");
        assertEquals(4.0 / 3.281, m3.getLength());
        assertEquals("Meters", m3.getUnit());
    }
    @Test
    void testFeetToKilometers() {
        m3 = Measurement.feetToUnit(m3, "Kilometers");
        assertEquals(4.0 / 3281, m3.getLength());
        assertEquals("Kilometers", m3.getUnit());
    }
    // end feet test
    // start yards test
    @Test
    void testYardsToFeet() {
        m4 = Measurement.yardToUnit(m4, "Feet");
        assertEquals(4.0 * 3, m4.getLength());
        assertEquals("Feet", m4.getUnit());
    }
    @Test
    void testYardsToYards() {
        m4 = Measurement.yardToUnit(m4, "Yards");
        assertEquals(4.0 , m4.getLength());
        assertEquals("Yards", m4.getUnit());
    }
    @Test
    void testYardsToMiles() {
        m4 = Measurement.yardToUnit(m4, "Miles");
        assertEquals(4.0 / 1760, m4.getLength());
        assertEquals("Miles", m4.getUnit());
    }
    @Test
    void testYardsToCentimeters() {
        m4 = Measurement.yardToUnit(m4, "Centimeters");
        assertEquals(4.0 * 91.44, m4.getLength());
        assertEquals("Centimeters", m4.getUnit());
    }
    @Test
    void testYardsToMeters() {
        m4 = Measurement.yardToUnit(m4, "Meters");
        assertEquals(4.0 / 1.094, m4.getLength());
        assertEquals("Meters", m4.getUnit());
    }
    @Test
    void testYardsToKilometers() {
        m4 = Measurement.yardToUnit(m4, "Kilometers");
        assertEquals(4.0 / 1094, m4.getLength());
        assertEquals("Kilometers", m4.getUnit());
    }
    @Test
    void testYardsToInches() {
        m4 = Measurement.yardToUnit(m4, "Inches");
        assertEquals(4.0 * 36, m4.getLength());
        assertEquals("Inches", m4.getUnit());
    }
    // end yards test
    // start miles test
    @Test
    void testMilesToFeet() {
        m5 = Measurement.mileToUnit(m5, "Feet");
        assertEquals(4.0 * 5280, m5.getLength());
        assertEquals("Feet", m5.getUnit());
    }
    @Test
    void testMilesToYards() {
        m5 = Measurement.mileToUnit(m5, "Yards");
        assertEquals(4.0 * 1760, m5.getLength());
        assertEquals("Yards", m5.getUnit());
    }
    @Test
    void testMilesToMiles() {
        m5 = Measurement.mileToUnit(m5, "Miles");
        assertEquals(4.0 , m5.getLength());
        assertEquals("Miles", m5.getUnit());
    }
    @Test
    void testMilesToCentimeters() {
        m5 = Measurement.mileToUnit(m5, "Centimeters");
        assertEquals(4.0 * 160900, m5.getLength());
        assertEquals("Centimeters", m5.getUnit());
    }
    @Test
    void testMilesToMeters() {
        m5 = Measurement.mileToUnit(m5, "Meters");
        assertEquals(4.0 * 1609, m5.getLength());
        assertEquals("Meters", m5.getUnit());
    }
    @Test
    void testMilesToKilometers() {
        m5 = Measurement.mileToUnit(m5, "Kilometers");
        assertEquals(4.0 * 1.609, m5.getLength());
        assertEquals("Kilometers", m5.getUnit());
    }
    @Test
    void testMilesToInches() {
        m5 = Measurement.mileToUnit(m5, "Inches");
        assertEquals(4.0 * 63360, m5.getLength());
        assertEquals("Inches", m5.getUnit());
    }
    // end miles test
    // start centimeters test
    @Test
    void testCentimetersToFeet() {
        m6 = Measurement.centimeterToUnit(m6, "Feet");
        assertEquals(4.0 / 30.48, m6.getLength());
        assertEquals("Feet", m6.getUnit());
    }
    @Test
    void testCentimetersToYards() {
        m6 = Measurement.centimeterToUnit(m6, "Yards");
        assertEquals(4.0 / 91.44, m6.getLength());
        assertEquals("Yards", m6.getUnit());
    }
    @Test
    void testCentimetersToMiles() {
        m6 = Measurement.centimeterToUnit(m6, "Miles");
        assertEquals(4.0 / 160900, m6.getLength());
        assertEquals("Miles", m6.getUnit());
    }
    @Test
    void testCentimetersToCentimeters() {
        m6 = Measurement.centimeterToUnit(m6, "Centimeters");
        assertEquals(4.0 , m6.getLength());
        assertEquals("Centimeters", m6.getUnit());
    }
    @Test
    void testCentimetersToMeters() {
        m6 = Measurement.centimeterToUnit(m6, "Meters");
        assertEquals(4.0 / 100, m6.getLength());
        assertEquals("Meters", m6.getUnit());
    }
    @Test
    void testCentimetersToKilometers() {
        m6 = Measurement.centimeterToUnit(m6, "Kilometers");
        assertEquals(4.0 / 100000, m6.getLength());
        assertEquals("Kilometers", m6.getUnit());
    }
    @Test
    void testCentimetersToInches() {
        m6 = Measurement.centimeterToUnit(m6, "Inches");
        assertEquals(4.0 / 2.54, m6.getLength());
        assertEquals("Inches", m6.getUnit());
    }
    // end centimeters test
    // start kilometers test
    @Test
    void testKilometersToFeet() {
        m7 = Measurement.kilometerToUnit(m7, "Feet");
        assertEquals(4.0 * 3281, m7.getLength());
        assertEquals("Feet", m7.getUnit());
    }
    @Test
    void testKilometersToYards() {
        m7 = Measurement.kilometerToUnit(m7, "Yards");
        assertEquals(4.0 * 1094, m7.getLength());
        assertEquals("Yards", m7.getUnit());
    }
    @Test
    void testKilometersToMiles() {
        m7 = Measurement.kilometerToUnit(m7, "Miles");
        assertEquals(4.0 / 1.609, m7.getLength());
        assertEquals("Miles", m7.getUnit());
    }
    @Test
    void testKilometersToCentimeters() {
        m7 = Measurement.kilometerToUnit(m7, "Centimeters");
        assertEquals(4.0 * 100000, m7.getLength());
        assertEquals("Centimeters", m7.getUnit());
    }
    @Test
    void testKilometersToMeters() {
        m7 = Measurement.kilometerToUnit(m7, "Meters");
        assertEquals(4.0 / 1000, m7.getLength());
        assertEquals("Meters", m7.getUnit());
    }
    @Test
    void testKilometersToKilometers() {
        m7 = Measurement.kilometerToUnit(m7, "Kilometers");
        assertEquals(4.0 , m7.getLength());
        assertEquals("Kilometers", m7.getUnit());
    }
    @Test
    void testKilometersToInches() {
        m7 = Measurement.kilometerToUnit(m7, "Inches");
        assertEquals(4.0 * 39370, m7.getLength());
        assertEquals("Inches", m7.getUnit());
    }
    // end kilometers test
}
