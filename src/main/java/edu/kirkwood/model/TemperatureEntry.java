package edu.kirkwood.model;

/**
 * Represents a Temperature with a float value and string scale.
 * This class provides methods for temperature conversion.
 */
public class TemperatureEntry {

    private float value;
    private String scale;

    /***
     * default constructor that initializes a new temperature at 77 F.
     */
    public TemperatureEntry(){
        this.value = 77;
        this.scale = "F";
    }

    /**
     * Constructs a new {@code TemperatureEntry} with the specified value and scale.
     * <p>
     * The scale is automatically converted to uppercase to ensure consistency.
     * Valid scales typically include "C" for Celsius and "F" for Fahrenheit.
     *
     * @param value the temperature value
     * @param scale the scale of the temperature (e.g., "C", "F")
     * @throws IllegalArgumentException if the scale is unsupported by {@code setScale}
     */
    public TemperatureEntry(float value, String scale){
        setValue(value);
        setScale(scale.toUpperCase());
    }

    /**
     * Converts the current temperature value from one scale to another.
     * <p>
     * Supported conversions include Celsius ("C") to Fahrenheit ("F") and vice versa.
     * The method updates the internal {@code value} based on the conversion formula.
     * If the scales are the same, the value is doubled (note: this may be unintended behavior).
     *
     * @param old      the original scale of the temperature ("C" or "F")
     * @param newScale the target scale to convert to ("C" or "F")
     * @throws IllegalArgumentException if the target scale is unsupported
     */
    public void convertTo(String old, String newScale){
        if (old.equals(newScale)){
            value += value;
        } else if (old.equals("F") && newScale.equals("C")) {
            value = ((value - 32) * 5 / 9);
        }else if(old.equals("C") && newScale.equals("F")){
            value = (( (value * 9) / 5) + 32);
        }else{
            throw new IllegalArgumentException("Unsupported scale " + newScale);
        }
    }

    /**
     * Returns the current value stored in this object.
     *
     * @return the value as a float
     */
    public float getValue() {
        return value;
    }

    /**
     * Sets the value for this object.
     *
     * @param value the new value to assign
     */
    public void setValue(float value) {
        this.value = value;
    }


    public String getScale() {
        return scale;
    }

    /**
     * Returns the scale associated with this value.
     * <p>
     * This could represent units such as "Celsius" or "Fahrenheit".
     * depending on the context of the class.
     * //@return the scale as a String
     */
    public void setScale(String scale) {
        this.scale = scale;
    }

    /**
     * Changes the scale of the current value based on the provided source and target scales.
     * <p>
     * Supported scales are "C" for Celsius and "F" for Fahrenheit. The method ensures both
     * input scales are case-insensitive by converting them to uppercase. If the source and
     * target scales are the same, the current scale remains unchanged. Otherwise, it updates
     * the scale accordingly.
     *
     * @param firstScale the original scale (e.g., "C" or "F")
     * @param secScale   the target scale to convert to (e.g., "F" or "C")
     * @return the updated scale as a String
     * @throws IllegalArgumentException if the target scale is not supported
     */
    public String changeScale(String firstScale,String secScale){
        firstScale = firstScale.toUpperCase();
        secScale = secScale.toUpperCase();
        if (firstScale.equals(secScale)){
            return scale;
        } else if (firstScale.equals("C") && secScale.equals("F")) {
            return scale = "C";
        }else if (firstScale.equals("F") && secScale.equals("C")) {
            return scale = "F";
        }else{
            throw new IllegalArgumentException("Unsupported scale " + secScale);
        }
    }

    /**
     * Adds the value of another {@code TemperatureEntry} to this one and returns a new entry.
     * <p>
     * The method sums the temperature values and determines the resulting scale using
     * {@code changeScale}. If the scales differ, it adjusts the scale based on predefined rules.
     *
     * @param other the {@code TemperatureEntry} to add
     * @return a new {@code TemperatureEntry} representing the combined value and resolved scale
     * @throws IllegalArgumentException if the scale conversion is unsupported
     */
    public TemperatureEntry add(TemperatureEntry other) {
        float newValue = this.value + other.value;
        String newScale = changeScale(this.scale, other.scale);
        return new TemperatureEntry(newValue, newScale);
    }

    /**
     * Subtracts the value of another {@code TemperatureEntry} from this one and returns a new entry.
     * <p>
     * The method computes the difference between the temperature values and determines the resulting
     * scale using {@code changeScale}. If the scales differ, it adjusts the scale based on predefined rules.
     *
     * @param other the {@code TemperatureEntry} to subtract
     * @return a new {@code TemperatureEntry} representing the resulting value and resolved scale
     * @throws IllegalArgumentException if the scale conversion is unsupported
     */
    public TemperatureEntry sub(TemperatureEntry other) {
        float newValue = this.value - other.value;
        String newScale = changeScale(this.scale, other.scale);
        return new TemperatureEntry(newValue, newScale);
    }

    /**
     * Compares two temperature scales and determines the appropriate scale to use.
     * <p>
     * The method normalizes both input scales to uppercase for consistency. If the scales are equal,
     * it returns the current scale unchanged. If converting from Celsius to Fahrenheit, it sets the scale to "C".
     * In all other cases, it defaults to "F".
     *
     * @param firstScale  the original temperature scale (e.g., "C" or "F")
     * @param secondScale the target temperature scale (e.g., "C" or "F")
     * @return the resolved scale as a {@code String}
     */
    public String compareScale(String firstScale,String secondScale){
        firstScale = firstScale.toUpperCase();
        secondScale = secondScale.toUpperCase();
        if (firstScale.equals(secondScale)){
            return scale;
        }
        else if (firstScale.equals("C") && secondScale.equals("F")) {
            return scale = "C";
        }
        else{
            return scale = "F";
        }
    }




    /**
     * Returns a string representation of this {@code TemperatureEntry}.
     * <p>
     * The format includes the temperature value and its scale, useful for logging
     * or debugging purposes.
     *
     * @return a string in the format {@code TemperatureEntry{value=..., scale='...'}}
     */
    @Override
    public String toString() {
        return "TemperatureEntry{" +
                "value=" + value +
                ", scale='" + scale + '\'' +
                '}';
    }

}
