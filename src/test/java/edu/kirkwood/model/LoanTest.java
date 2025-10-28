package model;

import edu.kirkwood.model.Loan;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Loan} class.
 * <p>
 * Verifies that getters, setters, constructor validation, and
 * arithmetic methods (periodic rate, total payments, payment,
 * total paid, total interest) behave as expected.
 */
public class LoanTest {
    /** Shared loan instance initialized before each test with minimal valid values. */
    private Loan L1;

    /**
     * Sets up a valid {@link Loan} object before each test.
     * Uses minimal valid values to avoid constructor exceptions.
     */
    @BeforeEach
    void setUp() {
        L1 = new Loan(1, 1, 1, 1);
    }

    /**
     * Demonstrates a "red" (failing) test mindset by asserting the principal
     * is not 100 for the default fixture. Ensures the suite can catch failures.
     */
    @Test
    void redTest() {
        assertNotEquals(100, L1.getPrincipal());
    }

    /**
     * Tests that setPrincipal accepts a positive value and updates the field.
     */
    @Test
    void setPrincipalAcceptsPositive() {
        Loan loan = new Loan(10, 2, 1, 1);
        loan.setPrincipal(250);
        assertEquals(250.0, loan.getPrincipal());
    }

    /**
     * Tests that setPrincipal rejects a value of zero by throwing IllegalArgumentException.
     */
    @Test
    void setPrincipalRejectsZero() {
        Loan loan = new Loan(100, 0, 1, 1);
        assertThrows(IllegalArgumentException.class, () -> loan.setPrincipal(0));
    }

    /**
     * Tests that the constructor correctly assigns values to all fields when valid input is provided.
     * Note: {@code getAPR()} returns the internal decimal form (e.g., 0.05 for 5%).
     */
    @Test
    void constructorSetsFieldsWhenValid() {
        Loan loan = new Loan(10000, 5, 4, 12);
        assertEquals(10000, loan.getPrincipal());
        assertEquals(0.05, loan.getAPR());
        assertEquals(4, loan.getTerm());
        assertEquals(12, loan.getPaymentsPerTerm());
    }

    // ---------- Setter validation tests ----------

    /**
     * Tests that setAPR rejects negative values and throws IllegalArgumentException.
     */
    @Test
    void setAPRRejectsNegatives() {
        Loan loan = new Loan(30, 2, 14, 3);
        assertThrows(IllegalArgumentException.class, () -> loan.setAPR(-12));
    }

    /**
     * Tests that setTerm rejects negative values and throws IllegalArgumentException.
     */
    @Test
    void setTermRejectsNegatives() {
        Loan loan = new Loan(1400, 1, 6, 1);
        assertThrows(IllegalArgumentException.class, () -> loan.setTerm(-64));
    }

    /**
     * Tests that setPaymentsPerTerm rejects negative values and throws IllegalArgumentException.
     */
    @Test
    void setPaymentsPerTermRejectsNegatives() {
        Loan loan = new Loan(2300, 4, 2, 24);
        assertThrows(IllegalArgumentException.class, () -> loan.setPaymentsPerTerm(-4));
    }

    /**
     * Tests that setPrincipal rejects negative values and throws IllegalArgumentException.
     */
    @Test
    void setPrincipalRejectsNegatives() {
        Loan loan = new Loan(100, 0, 1, 1);
        assertThrows(IllegalArgumentException.class, () -> loan.setPrincipal(-5));
    }

    // ---------- Arithmetic tests ----------

    /**
     * Tests that totalPayments correctly multiplies term by paymentsPerTerm.
     */
    @Test
    void totalPaymentsMultipliesTermByPaymentsPerTerm() {
        Loan loan = new Loan(1000, 0, 5, 12);
        assertEquals(60, loan.totalPayments());
    }

    /**
     * Tests that periodicRate divides the (decimal) APR by paymentsPerTerm.
     * Example: apr=12% input -> stored as 0.12; 0.12/8 = 0.015.
     */
    @Test
    void periodicRateDividesAPRByPaymentsPerTerm(){
        Loan loan = new Loan(432, 12, 4, 8);
        assertEquals(0.015, loan.periodicRate());
    }

    /**
     * Zero-APR path: payment divides principal evenly across all periods,
     * and totalPaid equals the principal with zero totalInterest.
     */
    @Test
    void paymentWithZeroAprDividesPrincipalEvenly() {
        Loan loan = new Loan(12000, 0, 1, 12);
        assertEquals(1000.0, loan.payment(), 1e-9);
        assertEquals(12000.0, loan.totalPaid(), 1e-9);
        assertEquals(0.0, loan.totalInterest(), 1e-9);
    }

    /**
     * Positive APR path: checks amortization payment against a known reference
     * (approximate monthly mortgage payment for 200k @ 6% over 30 years).
     */
    @Test
    void paymentWithPositiveApr() {
        Loan loan = new Loan(200000, 6, 30, 12);
        assertEquals(1199.10, loan.payment(), 0.1); 
    }

    /**
     * Consistency checks: totalPaid must equal payment * totalPayments,
     * and totalInterest must equal totalPaid - principal.
     */
    @Test
    void totalPaidAndInterestMatchPaymentCalculation() {
        Loan loan = new Loan(200000, 6, 30, 12);
        assertEquals(loan.payment() * loan.totalPayments(), loan.totalPaid(), 1e-9);
        assertEquals(loan.totalPaid() - 200000.0, loan.totalInterest(), 1e-9);
    }
}