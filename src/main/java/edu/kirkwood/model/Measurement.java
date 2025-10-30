package edu.kirkwood.model;


/**
 * Represents a measurement with an integer for length and a string for the unit
 * This class provides methods for unit conversion, simplification, and comparison
 * 
 */
public class Measurement {
    private double length; 
    private String unit;
    
    
    
    /**
     * Default constructor
     * Initializes a new measurement to 1 Meter
     */
    public Measurement() {
        this.length = 1;
        this.unit = "Meters";
    }

    /**
     * Constructs a measurement with a specific length and unit
     * @param length The value representing the length
     * @param unit The value representing the unit of length
     */
    public Measurement(double length, String unit) {
            this.length = length;
            this.unit = unit;
        }

    /**
     * Gets the length of the measurement
     * @return the length
     */
    public double getLength() {
        return length;
    }


    /**
     * Set the length of the measurement
     * @param length the new length
     * @throws ArithmeticException if length is less than or equal to 0
     */

    public void setLength(double length) {
        if (length == 0){
            throw new ArithmeticException("Length cannot be 0.");

        } else if (length < 0){
            throw new ArithmeticException("Length cannot be less than 0."); 
        } else {
            this.length = length;
        }
    }

    /**
     * Gets the unit of measurement
     * @return the unit of measure
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the unit of measure
     * @param unit the new unit of measure
     */

    public void setUnit(String unit) {
        this.unit = unit;
    }


    @Override
    public String toString() {
        return String.format("[%.3f %s]", length, unit);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Double.hashCode(length);
        result = prime * result + ((unit == null) ? 0 : unit.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Measurement other = (Measurement) obj;
        if (Double.doubleToLongBits(length) != Double.doubleToLongBits(other.length))
            return false;
        if (unit == null) {
            if (other.unit != null)
                return false;
        } else if (!unit.equals(other.unit))
            return false;
        return true;
    }

    /**
     * Takes two measurements and a unit, converts both measures to the given unit. Then adds the length values of the two measurements and
     * sets them to the measureFinal.length and sets the measureFinal.unit to the given unit and returns measureFinal.
     * @param measurement1 The first measurement to add
     * @param measurement2 The second measurement to add
     * @param unit The unit to combine the two lengths too
     * @return The combined length of measure 1 and 2 with the correct unit
     */
    public static Measurement addMeasurements(Measurement measurement1, Measurement measurement2, String unit) {
        Measurement m1 = measurement1;
        Measurement m2 = measurement2;
        Measurement measureFinal = new Measurement();
        if (m1.getUnit().equals(unit) && m2.getUnit().equals(unit)) {
            measureFinal.setLength(m1.getLength() + m2.getLength());
            measureFinal.setUnit(m1.unit);
        } else {
            // change m1 to unit param
            switch (m1.unit) {
                case "Inches":
                    m1 = Measurement.inchToUnit(m1, unit);
                    break;
                case "Feet":
                    m1 = Measurement.feetToUnit(m1, unit);
                    break;
                case "Yards":
                    m1 = Measurement.yardToUnit(m1, unit);
                    break;
                case "Miles":
                    m1 = Measurement.mileToUnit(m1, unit);
                    break;
                case "Centimeters":
                    m1 = Measurement.centimeterToUnit(m1, unit);
                    break;
                case "Meters":
                    m1 = Measurement.meterToUnit(m1, unit);
                    break;
                case "Kilometers":
                    m1 = Measurement.kilometerToUnit(m1, unit);
                    break;
            }
            // change m2 to unit param
            switch (m2.unit) {
                case "Inches":
                    m2 = Measurement.inchToUnit(m2, unit);
                    break;
                case "Feet":
                    m2 = Measurement.feetToUnit(m2, unit);
                    break;
                case "Yards":
                    m2 = Measurement.yardToUnit(m2, unit);
                    break;
                case "Miles":
                    m2 = Measurement.mileToUnit(m2, unit);
                    break;
                case "Centimeters":
                    m2 = Measurement.centimeterToUnit(m2, unit);
                    break;
                case "Meters":
                    m2 = Measurement.meterToUnit(m2, unit);
                    break;
                case "Kilometers":
                    m2 = Measurement.kilometerToUnit(m2, unit);
                    break;
            }
        }
        //add lengths of m1 and m2 as the finalMeasure length
        measureFinal.setLength(m1.getLength() + m2.getLength());
        // set finalMeasure unit to unit param
        measureFinal.setUnit(unit);
        // return measureFinal with the length and unit set properly
        return measureFinal;
    }

    /**
     * Takes two measurements and a unit, converts both measures to the given unit. Then subtracts the length of the lesser length from the greater length and
     * sets them to the measureFinal.length and sets the measureFinal.unit to the given unit and returns measureFinal.
     * @param measurement1 The first measurement to add
     * @param measurement2 The second measurement to add
     * @param unit The unit to combine the two lengths too
     * @return The combined length of measure 1 and 2 with the correct unit
     */
    public static Measurement subtractMeasurements(Measurement measurement1, Measurement measurement2, String unit) {
        Measurement m1 = measurement1;
        Measurement m2 = measurement2;
        Measurement measureFinal = new Measurement();
        if ((m1.getUnit().equals(m2.getUnit())) && m1.getLength() == m2.getLength()) {
            System.out.println("Subtracting measurements of the same length and unit will result in zero");
            System.out.println("A length with a measurement of zero cannot be saved.");
            measureFinal.setLength(1.0);
            measureFinal.setUnit("null");
            return measureFinal;
        } else if (m1.getUnit().equals(unit) && m2.getUnit().equals(unit)) {
            if (m1.getLength() >= m2.getLength()) {
                measureFinal.setLength(m1.getLength() - m2.getLength());
            }else {
                measureFinal.setLength(m2.getLength() - m1.getLength());
            }
            measureFinal.setUnit(m1.unit);
            return measureFinal;
        } else {
            // change m1 to unit param
            switch (m1.unit) {
                case "Inches":
                    m1 = Measurement.inchToUnit(m1, unit);
                    break;
                case "Feet":
                    m1 = Measurement.feetToUnit(m1, unit);
                    break;
                case "Yards":
                    m1 = Measurement.yardToUnit(m1, unit);
                    break;
                case "Miles":
                    m1 = Measurement.mileToUnit(m1, unit);
                    break;
                case "Centimeters":
                    m1 = Measurement.centimeterToUnit(m1, unit);
                    break;
                case "Meters":
                    m1 = Measurement.meterToUnit(m1, unit);
                    break;
                case "Kilometers":
                    m1 = Measurement.kilometerToUnit(m1, unit);
                    break;
            }
            // change m2 to unit param
            switch (m2.unit) {
                case "Inches":
                    m2 = Measurement.inchToUnit(m2, unit);
                    break;
                case "Feet":
                    m2 = Measurement.feetToUnit(m2, unit);
                    break;
                case "Yards":
                    m2 = Measurement.yardToUnit(m2, unit);
                    break;
                case "Miles":
                    m2 = Measurement.mileToUnit(m2, unit);
                    break;
                case "Centimeters":
                    m2 = Measurement.centimeterToUnit(m2, unit);
                    break;
                case "Meters":
                    m2 = Measurement.meterToUnit(m2, unit);
                    break;
                case "Kilometers":
                    m2 = Measurement.kilometerToUnit(m2, unit);
                    break;
            }
        }
        //subtract lengths of the lesser length from the greater length and sets result to finalMeasure.length
        if (m1.length > m2.length) {
            measureFinal.setLength(m1.getLength() - m2.getLength());
        } else if (m1.length <= m2.length) {
            measureFinal.setLength(m2.getLength() - m1.getLength());
        }
        // set finalMeasure unit to unit param
        measureFinal.setUnit(unit);
        // return measureFinal with the length and unit set properly
        return measureFinal;
    }

    /**
     * Converts a measurement listed in units feet and converts it to the selected unit
     * 
     * @param measurement the measurement in feet
     * @param unit the selected unit
     * @return the measurement with the appropriate length in the new unit
     */
    public static Measurement feetToUnit(Measurement measurement, String unit) {
        Measurement result = new Measurement(); 
        result.length = 0; 
        result.unit = unit;
        double length = measurement.length;


        if( unit.equals("Feet") ){
            result.length = measurement.length;
        }else if (unit.equals("Inches")) {
            result.length = length * 12;
        }else if (unit.equals("Yards")) {
            result.length = length / 3;
        }else if (unit.equals("Miles") ) {
            result.length = length / 5280;
        }else if (unit.equals("Centimeters")) {
            result.length = length * 30.48;
        }else if (unit.equals("Meters") ) {
            result.length = length / 3.281;
        }else if (unit.equals("Kilometers")) {
            result.length = length / 3281;
        }   
        return result;
    }
    /**
     * Converts a measurement listed in units yards and converts it to the selected unit
     * @param measurement the measurement in yards
     * @param unit the selected unit
     * @return the measurement with the appropriate length in the new unit
     */
    public static Measurement yardToUnit(Measurement measurement, String unit) {
        Measurement result = new Measurement();
        result.length = 0;
        result.unit = unit;
        double length = measurement.length;

        if(unit.equals("Yards")) {
            result.length = measurement.length;
        } else if (unit.equals("Inches")) {
            result.length = length * 36;
        } else if (unit.equals("Feet")) {
            result.length = length * 3;
        } else if (unit.equals("Miles")) {
            result.length = length / 1760;
        } else if (unit.equals("Centimeters")) {
            result.length = length * 91.44;
        } else if (unit.equals("Meters")) {
            result.length = length / 1.094;
        } else if (unit.equals("Kilometers")) {
            result.length = length / 1094;
        }
        return result;
    }

    /**
     * Converts a measurement listed in units miles and converts it to the selected unit
     * @param measurement the measurement in miles
     * @param unit the selected unit
     * @return the measurement with the appropriate length in the new unit
     */
    public static Measurement mileToUnit(Measurement measurement, String unit) {
        Measurement result = new Measurement();
        result.length = 0;
        result.unit = unit;
        double length = measurement.length;

        if(unit.equals("Miles")) {
            result.length = measurement.length;
        } else if (unit.equals("Inches")) {
            result.length = length * 63360;
        } else if (unit.equals("Feet")) {
            result.length = length * 5280;
        } else if (unit.equals("Yards")) {
            result.length = length * 1760;
        } else if (unit.equals("Centimeters")) {
            result.length = length * 160900;
        } else if (unit.equals("Meters")) {
            result.length = length * 1609;
        } else if (unit.equals("Kilometers")) {
            result.length = length * 1.609;
        }
        return result;
    }

    /**
     * Converts a measurement listed in units centimeters and converts it to the selected unit
     * @param measurement the measurement in centimeters
     * @param unit the selected unit
     * @return the measurement with the appropriate length in the new unit
     */
    public static Measurement centimeterToUnit(Measurement measurement, String unit) {
        Measurement result = new Measurement();
        result.length = 0;
        result.unit = unit;
        double length = measurement.length;

        if(unit.equals("Centimeters")) {
            result.length = measurement.length;
        } else if (unit.equals("Inches")) {
            result.length = length / 2.54;
        } else if (unit.equals("Feet")) {
            result.length = length / 30.48;
        } else if (unit.equals("Yards")) {
            result.length = length / 91.44;
        } else if (unit.equals("Miles")) {
            result.length = length / 160900;
        } else if (unit.equals("Meters")) {
            result.length = length / 100;
        } else if (unit.equals("Kilometers")) {
            result.length = length / 100000;
        }
        return result;
    }

    /**
     * Converts a measurement listed in units meters and converts it to the selected unit
     * @param measurement the measurement in meters
     * @param unit the selected unit
     * @return the measurement with the appropriate length in the new unit
     */
    public static Measurement meterToUnit(Measurement measurement, String unit) {
        Measurement result = new Measurement();
        result.length = 0;
        result.unit = unit;
        double length = measurement.length;

        if(unit.equals("Meters")) {
            result.length = measurement.length;
        } else if (unit.equals("Inches")) {
            result.length = length * 39.37;
        } else if (unit.equals("Feet")) {
            result.length = length * 3.281;
        } else if (unit.equals("Yards")) {
            result.length = length * 1.094;
        } else if (unit.equals("Miles")) {
            result.length = length / 1609;
        } else if (unit.equals("Centimeters")) {
            result.length = length * 100;
        } else if (unit.equals("Kilometers")) {
            result.length = length / 1000;
        }
        return result;
    }

    /**
     * Converts a measurement listed in units kilometers and converts it to the selected unit
     * @param measurement the measurement in kilometers
     * @param unit the selected unit
     * @return the measurement with the appropriate length in the new unit
     */
    public static Measurement kilometerToUnit(Measurement measurement, String unit) {
        Measurement result = new Measurement();
        result.length = 0;
        result.unit = unit;
        double length = measurement.length;

        if(unit.equals("Kilometers")) {
            result.length = measurement.length;
        } else if (unit.equals("Inches")) {
            result.length = length * 39370;
        } else if (unit.equals("Feet")) {
            result.length = length * 3281;
        } else if (unit.equals("Yards")) {
            result.length = length * 1094;
        } else if (unit.equals("Miles")) {
            result.length = length / 1.609;
        } else if (unit.equals("Centimeters")) {
            result.length = length * 100000;
        } else if (unit.equals("Meters")) {
            result.length = length / 1000;
        }
        return result;
    }

    /**
     * Converts a measurement listed in units inches and converts it to the selected unit
     * @param measurement the measurement in inches
     * @param unit the selected unit
     * @return the measurement with the appropriate length in the new unit
     */
    public static Measurement inchToUnit(Measurement measurement, String unit) {
        Measurement result = new Measurement();
        result.length = 0;
        result.unit = unit;
        double length = measurement.length;

        if(unit.equals("Inches")) {
            result.length = measurement.length;
        } else if (unit.equals("Feet")) {
            result.length = length / 12;
        } else if (unit.equals("Yards")) {
            result.length = length / 36;
        } else if (unit.equals("Miles")) {
            result.length = length / 63360;
        } else if (unit.equals("Centimeters")) {
            result.length = length * 2.54;
        } else if (unit.equals("Meters")) {
            result.length = length / 39.37;
        } else if (unit.equals("Kilometers")) {
            result.length = length / 39370;
        }
        return result;
    }

}
