package edu.kirkwood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {

    private Fraction f1;
    private Fraction f2;

    @BeforeEach
    void setUp() {
        f1 = new Fraction();
        f2 = new Fraction(2, 3);
    }

    @Test
    void getNumerator() {
        assertEquals(1, f1.getNumerator());
        assertEquals(2, f2.getNumerator());
    }

    @Test
    void setNumerator() {
        f1.setNumerator(2); // Was 1
        assertEquals(2, f1.getNumerator());
        f2.setNumerator(0); // Was 2
        assertEquals(0, f2.getNumerator());
        f1.setNumerator(-1);
        assertEquals(-1, f1.getNumerator());
    }

    @Test
    void getDenominator() {
        assertEquals(1, f1.getDenominator());
        assertEquals(3, f2.getDenominator());
    }

    @Test
    void setDenominatorPostiveDenominator() {
        f1.setDenominator(2); // Was 1
        assertEquals(1, f1.getNumerator());
        assertEquals(2, f1.getDenominator());
    }

    @Test
    void setDenominatorNegativeDenominator() {
        f1.setDenominator(-1); // Was 1
        assertEquals(-1, f1.getNumerator(), "A negative denominator should be applied to the numerator");
        assertEquals(1, f1.getDenominator());
        f1.setDenominator(-1); // Was 1
        assertEquals(1, f1.getNumerator(), "-1/1 changed to -1/-1 should become 1/1");
        assertEquals(1, f1.getDenominator());
    }

    @Test
    void setDenominatorZeroDenominator() {
        assertThrows(ArithmeticException.class, () -> f1.setDenominator(0));
    }

    @Test
    void testToString() {
        assertEquals("1", f1.toString());
        assertEquals("2/3", f2.toString());
        f2.setNumerator(7);
        assertEquals("2 1/3", f2.toString());
    }

    @Test
    void compareToEqual() {
        f2 = new Fraction();
        assertEquals(0, f1.compareTo(f2), "1/1 == 1/1");
        f2 = new Fraction(2, 2);
        assertEquals(0, f1.compareTo(f2), "1/1 == 2/2");
    }

    @Test
    void compareToGreaterThan() {
        f2 = new Fraction(1,2);
        assertTrue(f1.compareTo(f2) > 0, "1/1 > 1/2");
        f2 = new Fraction(2,4);
        assertTrue(f1.compareTo(f2) > 0, "1/1 > 2/4");
    }

    @Test
    void compareToLessThan() {
        f2 = new Fraction(1,2);
        assertTrue(f2.compareTo(f1) < 0, "1/2 < 1/1");
        f2 = new Fraction(2,4);
        assertTrue(f2.compareTo(f1) < 0, "2/4 < 1/1");
    }

    @Test
    @DisplayName("Test comparison with negative fractions (-1/2 vs 1/4)")
    void compareToWithNegativeFractions() {
        Fraction f1 = new Fraction(-1, 2);
        Fraction f2 = new Fraction(1, 4);
        Fraction f3 = new Fraction(-3, 4);
        assertTrue(f1.compareTo(f2) < 0, "-1/2 is less than 1/4");
        assertTrue(f2.compareTo(f1) > 0, "1/4 is greater than -1/2");
        assertTrue(f3.compareTo(f1) < 0, "-3/4 is less than -1/2");
    }

    @Test
    void equals() {
        f2 = new Fraction();
        assertTrue(f1.equals(f2), "1/1 == 1/1");
    }

    @Test
    void equalsNotSimplified() {
        f2 = new Fraction(2, 2);
        assertTrue(f1.equals(f2), "1/1 == 2/2");
    }

    @Test
    void gcd() {
        assertEquals(15, Fraction.gcd(75, 45));
        assertEquals(2, Fraction.gcd(2, 4));
        assertEquals(1, Fraction.gcd(5, 7));
    }

    @Test
    void gcdWithNegatives() {
        assertEquals(3, Fraction.gcd(15, 6));
        assertEquals(3, Fraction.gcd(-15, 6));
        assertEquals(3, Fraction.gcd(15, -6));
        assertEquals(3, Fraction.gcd(-15, -6));
    }

    @Test
    @DisplayName("Test LCM with two positive integers")
    void testLcmWithPositiveIntegers() {
        assertEquals(24, Fraction.lcm(6, 8));
    }

    @Test
    @DisplayName("Test LCM where one number is a multiple of the other")
    void testLcmWithMultiple() {
        assertEquals(12, Fraction.lcm(4, 12));
    }

    @Test
    @DisplayName("Test LCM with two prime numbers")
    void testLcmWithPrimes() {
        // The lcm of two prime numbers is their product.
        assertEquals(77, Fraction.lcm(7, 11));
    }

    @Test
    @DisplayName("Test LCM with the number 1")
    void testLcmWithOne() {
        assertEquals(9, Fraction.lcm(1, 9));
        assertEquals(9, Fraction.lcm(9, 1));
    }

    @Test
    @DisplayName("Test LCM with identical numbers")
    void testLcmWithIdenticalNumbers() {
        assertEquals(5, Fraction.lcm(5, 5));
    }

    @Test
    @DisplayName("Test LCM where one of the inputs is zero")
    void testLcmWithZero() {
        assertEquals(0, Fraction.lcm(10, 0));
        assertEquals(0, Fraction.lcm(0, 10));
        assertEquals(0, Fraction.lcm(0, 0));
    }

    @Test
    @DisplayName("4/6 simplifies to 2/3")
    void simplify() {
        Fraction f4 = new Fraction(4,6);
        f4.simplify();
        assertEquals(2, f4.getNumerator());
        assertEquals(3, f4.getDenominator());
    }

    @Test
    void simplifyNegative() {
        Fraction f4 = new Fraction(-4,-6);
        f4.simplify();
        assertEquals(2, f4.getNumerator());
        assertEquals(3, f4.getDenominator());
        Fraction f5 = new Fraction(4, -6);
        f5.simplify();
        assertEquals(-2, f5.getNumerator());
        assertEquals(3, f5.getDenominator());
    }

    @Test
    void simplifyTwoNegatives() {
        Fraction f4 = new Fraction(-6,-25);
        f4.simplify();
        assertEquals(6, f4.getNumerator());
        assertEquals(25, f4.getDenominator());
    }

    @Test
    @DisplayName("Test 1/4 + 1/4 = 1/2")
    void addFractionsThatNeedSimplification2() {
        f1 = new Fraction (1,4);
        f2 = new Fraction (1, 4);
        Fraction f3 = f1.add(f2);
        f3.simplify();
        assertEquals(1, f3.getNumerator());
        assertEquals(2, f3.getDenominator());
    }


    @Test
    @DisplayName("Test 11/9 = 1 2/9, 3/3 = 1, 9/3 = 3")
    void toMixedNumber() {
        assertEquals("1 2/9", Fraction.toMixedNumber(11, 9));
        assertEquals("1", Fraction.toMixedNumber(3, 3));
        assertEquals("3", Fraction.toMixedNumber(9, 3));
    }

    @Test
    void toMixedNumberNoWhole() {
        assertEquals("2/3", Fraction.toMixedNumber(2, 3));
    }

    @Test
    @DisplayName("Test 1/1 + 2/3 = 5/3")
    void addWholeNumberToFraction() {
        Fraction f3 = f1.add(f2);
        assertEquals(5, f3.getNumerator());
        assertEquals(3, f3.getDenominator());
    }

    @Test
    @DisplayName("Test =1/4 + 2/3 = 5/12")
    void addNegativeFractionToPositive() {
        f1 = new Fraction(-1, 4);
        Fraction f3 = f1.add(f2);
        assertEquals(5, f3.getNumerator());
        assertEquals(12, f3.getDenominator());
    }

    @Test
    @DisplayName("Test 1/4 + 1/4 = 1/2")
    void addFractionsThatNeedSimplification() {
        f1 = new Fraction(1, 4);
        f2 = new Fraction(1, 4);
        Fraction f3 = f1.add(f2);
        assertEquals(1, f3.getNumerator());
        assertEquals(2, f3.getDenominator());
    }

    @Test
    @DisplayName("Test 1/1 - 1/4 = 3/4")
    void subtractFromWholeNumberToFraction() {
        f1.setNumerator(1);
        f1.setDenominator(1);
        f2.setNumerator(1);
        f2.setDenominator(4);

        Fraction f3 = f1.subtract(f2);
        f3.simplify();
        assertEquals(3, f3.getNumerator());
        assertEquals(4, f3.getDenominator());
    }

    @Test
    @DisplayName("Test 1/2 - -3/4 = 5/4")
    void subtractFromFractionByNegativeNumber() {
        f1.setNumerator(1);
        f1.setDenominator(2);
        f2.setNumerator(-3);
        f2.setDenominator(4);

        Fraction f3 = f1.subtract(f2);
        f3.simplify();
        assertEquals(5, f3.getNumerator());
        assertEquals(4, f3.getDenominator());
    }

    @Test
    @DisplayName("Test 10/1 - 15/4 = 25/4")
    void subtractFromWholeNunberByFraction() {
        f1.setNumerator(10);
        f1.setDenominator(1);
        f2.setNumerator(15);
        f2.setDenominator(4);

        Fraction f3 = f1.subtract(f2);
        f3.simplify();
        assertEquals(25, f3.getNumerator());
        assertEquals(4, f3.getDenominator());
    }

    @Test
    void multiplyWholeNumberToFraction() {
        f1 = new Fraction(1,4);
        f2 = new Fraction(1,1);
        Fraction f3 = f1.multiply(f2);
        assertEquals(1, f3.getNumerator());
        assertEquals(4, f3.getDenominator());
    }

    @Test
    void multiplyNegativeFractionToPositiveFraction() {
        f1 = new Fraction(1,4);
        f2 = new Fraction(-1,1);
        Fraction f3 = f1.multiply(f2);
        assertEquals(-1, f3.getNumerator());
        assertEquals(4, f3.getDenominator());
    }

    @Test
    void multiplyZero() {
        f1 = new Fraction(0,4);
        f2 = new Fraction(1,1);
        Fraction f3 = f1.multiply(f2);
        assertEquals(0, f3.getNumerator());
        assertEquals(1, f3.getDenominator(), "0/4 simplified is 0/1");
    }

    @Test
    void multiplyPositiveWithSimplification() {
        f1 = new Fraction(2,4);
        f2 = new Fraction(3,5);
        Fraction f3 = f1.multiply(f2);
        assertEquals(3, f3.getNumerator());
        assertEquals(10, f3.getDenominator());


    }

    @Test
    void multiplyNegativeWithSimplification() {
        f1 = new Fraction(-2,4);
        f2 = new Fraction(3,5);
        Fraction f3 = f1.multiply(f2);
        assertEquals(-3, f3.getNumerator());
        assertEquals(10, f3.getDenominator());
    }

    @Test
    @DisplayName("Test 1/1 * 2/3 = 1/2")
    void multiplyPositives() {
        Fraction f3 = f1.multiply(f2);
        assertEquals(2, f3.getNumerator());
        assertEquals(3, f3.getDenominator());
    }

    @Test
    @DisplayName("Test -2/3 * 3/4 = -1/2")
    void multiplyOneNegative() {
        f1 = new Fraction(-2,3);
        f2 = new Fraction(3,4);
        Fraction f3 = f1.multiply(f2);
        assertEquals(-1, f3.getNumerator());
        assertEquals(2, f3.getDenominator());
    }

    @Test
    @DisplayName("Test -2/3 * -3/4 = 1/2")
    void multiplyTwoNegatives() {
        f1 = new Fraction(-2,3);
        f2 = new Fraction(-3,4);
        Fraction f3 = f1.multiply(f2);
        assertEquals(1, f3.getNumerator());
        assertEquals(2, f3.getDenominator());
    }

    @Test
    void multiplyByNegativeDenominator() {
        f1 = new Fraction(3,-4);
        f2 = new Fraction(4,5);
        Fraction f3 = f1.multiply(f2);
        assertEquals(-3, f3.getNumerator());
        assertEquals(5, f3.getDenominator());
    }

    @Test
    void basicDivide() {
        f1.setNumerator(2);
        f1.setDenominator(5);
        f2.setNumerator(5);
        f2.setDenominator(3);
        Fraction f3 = f1.divide(f2);
        assertEquals(25, f3.getDenominator());
        assertEquals(6, f3.getNumerator());
    }

    @Test
    void basicDivideNegToNeg() {
        f1.setNumerator(-2);
        f1.setDenominator(5);
        f2.setNumerator(5);
        f2.setDenominator(-3);
        Fraction f3 = f1.divide(f2);
        assertEquals(6, f3.getNumerator());
        assertEquals(25, f3.getDenominator());
    }

    @Test
    void basicDivideNegToPos() {
        f1.setNumerator(-2);
        f1.setDenominator(5);
        f2.setNumerator(5);
        f2.setDenominator(3);
        Fraction f4 = f1.divide(f2);
        assertEquals(-6, f4.getNumerator());
        assertEquals(25, f4.getDenominator());
    }
}