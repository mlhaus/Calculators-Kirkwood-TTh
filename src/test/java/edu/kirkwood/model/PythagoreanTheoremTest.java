package edu.kirkwood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static edu.kirkwood.model.PythagoreanTheorem.square;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class PythagoreanTheoremTest {
    private PythagoreanTheorem p1;
    private PythagoreanTheorem p2;

    @BeforeEach
    void setUp() {
        p1 = new PythagoreanTheorem();
        p2 = new PythagoreanTheorem(2, 3,4);
    }

    @Test
    void getA() {
        assertEquals(6, p1.getA());
        assertEquals(2, p2.getA());
    }

    @Test
    void getB() {
        assertEquals(7, p1.getB());
        assertEquals(3, p2.getB());
    }

    @Test
    void setA() {
        p1.setA(2); // Was 7
        assertEquals(2, p1.getA());
        p2.setA(10); // Was 2
        assertEquals(10, p2.getA());
        assertThrows(ArithmeticException.class, () -> p1.setA(-1));
    }

    @Test
    void setB() {
        p1.setB(2); // Was 7
        assertEquals(2, p1.getB());
        p2.setB(10); // Was 2
        assertEquals(10, p2.getB());
        assertThrows(ArithmeticException.class, () ->  p1.setB(-1));
    }

    @Test
    void squareAPositive() {
        assertEquals(36, square(p1.getA()));
        assertEquals(4, square(p2.getA()));
    }

    @Test
    void squareANegative() {
        assertThrows(ArithmeticException.class, () ->  p1.setA(-1));
        assertThrows(ArithmeticException.class, () ->  p1.setA(-7));
    }

    @Test
    void squareAZero() {
        assertThrows(ArithmeticException.class, () ->  p1.setA(0));
    }

    @Test
    void squareBPositive() {
        assertEquals(49, square(p1.getB()), 0.0001);
        assertEquals(9, square(p2.getB()), 0.0001);
    }

    @Test
    void squareBNegative() {
        assertThrows(ArithmeticException.class, () ->  p1.setB(-1));
        assertThrows(ArithmeticException.class, () ->  p1.setB(-7));
    }

    @Test
    void squareBZero() {
        assertThrows(ArithmeticException.class, () ->  p1.setB(0));
    }

    @Test
    void addSquared() {
        p1.setA(4);
        p1.setB(3);
        assertEquals(25, p1.add(square(p1.getA()), square(p1.getB() )),0.0001);

        p2.setA(5);
        p2.setB(6);
        assertEquals(61, p2.add(square(p2.getA()), square(p2.getB())),0.0001);
    }

    @Test
    void takeRoot(){
        p1.setA(4);
        p1.setB(3);
        assertEquals(5, p1.takeRoot(p1.add(square(p1.getA()), square(p1.getB() ))), 0.0001 );
    }

    @Test
    void returnRootToString(){
        assertEquals("Side 1: 6.0\nSide 2: 7.0\nSide 3: 9.22", p1.toString());
        assertEquals("Side 1: 2.0\nSide 2: 3.0\nSide 3: 3.6056", p2.toString());
    }
}