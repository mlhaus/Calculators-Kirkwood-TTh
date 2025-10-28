package edu.kirkwood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperatureEntryTest {
    private TemperatureEntry entry1;
    private TemperatureEntry entry2;

    @BeforeEach
    void setUp() {
        entry1 = new TemperatureEntry();
        entry2 = new TemperatureEntry(25,"C");
    }

    @Test
    void getValue() {
        assertEquals(77, entry1.getValue());
        assertEquals(25, entry2.getValue());
    }

    @Test
    void setValue() {
        entry1.setValue(25); // was 77
        assertEquals(25, entry1.getValue());
        entry2.setValue(100); // was 25
        assertEquals(100, entry2.getValue());
        entry1.setValue(-25);
        assertEquals(-25, entry1.getValue());
    }

    @Test
    void getScale() {
        assertEquals("F", entry1.getScale());
        assertEquals("C", entry2.getScale());
    }

    @Test
    void setScale() {
        entry1.setScale("C"); // was F
        assertEquals("C", entry1.getScale());
        entry2.setScale("F"); // was C
        assertEquals("F", entry2.getScale());
    }

    @Test
    void toStringTest() {
        entry2.setValue(25);
        entry2.setScale("C");
        assertEquals("TemperatureEntry{value=77.0, scale='F'}", entry1.toString());
        assertEquals("TemperatureEntry{value=25.0, scale='C'}", entry2.toString());
    }

    @Test
    void addTest() {
        TemperatureEntry entry3 = entry1.add(entry2);
        assertEquals(entry1.getValue() + entry2.getValue(), entry3.getValue());
    }

    @Test
    void subtractTest() {
        TemperatureEntry entry3 = entry1.sub(entry2);
        assertEquals(entry1.getValue() - entry2.getValue(), entry3.getValue());
    }

    @Test
    void changeScaleTest(){
        String newScale = "C";
        String oldScale = "F";
        entry1.changeScale(newScale, oldScale);
        assertEquals(newScale, entry1.getScale());
        entry2.changeScale(newScale, oldScale);
        assertEquals(newScale, entry2.getScale());
    }

    @Test
    void convertTest() {
       entry1.convertTo("F", "C");
       assertEquals(25, entry1.getValue());
    }

    @Test
    void compareScaleTest() {
        entry1.compareScale("F", "C");
    }


}