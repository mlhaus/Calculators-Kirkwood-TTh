package edu.kirkwood.model;
/*
 * INFO
 * ChatGPT 5.0 was used solely for the purpose of adding javadoc comments
 * to this documentation, as well as some minor error recognition on the part
 * of some of my bad syntax.
 * 
 * 
 */
/**
 * Represents a simple fixed-rate loan with a principal,
 * annual percentage rate (APR), term, and number of payments per term.
 * <p>
 * Usage note: {@link #setAPR(double)} converts a whole-number percent
 * entered by a user (e.g., 6 for 6%) into a decimal stored internally
 * (e.g., 0.06). Methods such as {@link #periodicRate()}, {@link #payment()},
 * {@link #totalPaid()}, and {@link #totalInterest()} operate on that decimal form.
 * <p>
 * Provides methods for getting and setting loan details,
 * as well as basic arithmetic calculations such as total payments
 * and the periodic interest rate.
 */
public class Loan {
    /** Amount borrowed (currency units). Must be &gt; 0. */
    private double principal;       // amount borrowed
    /** Annual percentage rate stored as a decimal after setAPR (e.g., 0.06 for 6%). */
    private double apr;             // annual percentage rate
    /** Number of terms (could be years, months, or weeks depending on use). Must be &gt; 0. */
    private int term;               // number of terms (could be years, months, weeks)
    /** Number of payments occurring within each term. Must be &gt; 0. */
    private int paymentsPerTerm;    // how many payments happen per term

    /**
     * Creates a new Loan object with the given parameters.
     * Calls setters to apply validation and to normalize APR
     * (i.e., whole-number percent is converted to decimal).
     *
     * @param principal       the amount borrowed; must be greater than 0
     * @param apr             the annual percentage rate as a whole-number percent (e.g., 5.0 for 5%)
     * @param term            the number of terms (years, months, or weeks depending on use); must be &gt; 0
     * @param paymentsPerTerm how many payments occur in each term; must be &gt; 0
     * @throws IllegalArgumentException if any validated parameter is out of range
     */
    public Loan(double principal, double apr, int term, int paymentsPerTerm) {
        setPrincipal(principal);
        setAPR(apr);
        setTerm(term);
        setPaymentsPerTerm(paymentsPerTerm);
    }

    /**
     * Gets the principal amount of the loan.
     *
     * @return the principal (currency units)
     */
    public double getPrincipal() {
        return principal;
    }

    /**
     * Sets the principal amount of the loan.
     *
     * @param principal the new principal; must be greater than 0
     * @throws IllegalArgumentException if principal is less than or equal to 0
     */
    public void setPrincipal(double principal) {
        if (principal <= 0) {
            throw new IllegalArgumentException("Principal cannot be 0 or less!");
        } else {
            this.principal = principal;
        }
    }

    /**
     * Gets the APR of the loan.
     * <p>
     * Note: This returns the internal decimal form (e.g., 0.06 for 6%),
     * because {@link #setAPR(double)} converts whole-number input to decimal.
     *
     * @return the APR as a decimal fraction (e.g., 0.06)
     */
    public double getAPR() {
        return apr;
    }

    /**
     * Sets the APR of the loan.
     * <p>
     * This method expects a whole-number percentage (e.g., pass 6 for 6%)
     * and stores it internally as a decimal (0.06).
     *
     * @param apr the new APR as a whole-number percent (e.g., 6.0 for 6%)
     * @throws IllegalArgumentException if {@code apr} is negative
     */
    public void setAPR(double apr) {
        if (apr < 0) {
            throw new IllegalArgumentException("APR cannot be negative!");
        } else {
            this.apr = apr / 100; 
            // converts a whole numbered percentage from the user into a percentage
        }
    }

    /**
     * Gets the term of the loan.
     *
     * @return the term (number of terms; unit depends on use)
     */
    public int getTerm() {
        return term;
    }

    /**
     * Sets the term of the loan.
     *
     * @param term the new term (number of years, months, or weeks)
     * @throws IllegalArgumentException if {@code term} is less than or equal to 0
     */
    public void setTerm(int term) {
        if (term <= 0) {
            throw new IllegalArgumentException("Term amount cannot be 0 or less!");
        } else {
            this.term = term;
        }
    }

    /**
     * Gets the number of payments made per term.
     *
     * @return the number of payments per term
     */
    public int getPaymentsPerTerm() {
        return paymentsPerTerm;
    }

    /**
     * Sets the number of payments made per term.
     *
     * @param paymentsPerTerm the number of payments per term
     * @throws IllegalArgumentException if {@code paymentsPerTerm} is less than or equal to 0
     */
    public void setPaymentsPerTerm(int paymentsPerTerm) {
       if (paymentsPerTerm <= 0) {
            throw new IllegalArgumentException("Payments per term cannot be 0 or less!");
        } else {
            this.paymentsPerTerm = paymentsPerTerm;
        }
    }

    /**
     * Calculates the periodic interest rate based on the internal APR (decimal)
     * and the number of payments per term.
     * <p>
     * For example, if APR is stored as 0.06 (6%) and there are 12 payments per term,
     * this returns 0.06 / 12.
     *
     * @return the periodic interest rate as a decimal
     */
    public double periodicRate() {
        return (apr / paymentsPerTerm);
    }

    /**
     * Calculates the total number of payments over the entire loan.
     *
     * @return the total number of payments (term × paymentsPerTerm)
     */
    public int totalPayments() {
        return (term * paymentsPerTerm);
    }

    /**
     * Calculates the level payment amount per period using standard amortization.
     * <pre>
     * n = totalPayments()
     * r = periodicRate()
     * if r == 0:
     *     payment = principal / n
     * else:
     *     payment = principal * r / (1 - (1 + r)^(-n))
     * </pre>
     *
     * @return the payment amount per period
     */
    public double payment(){
        // shorthand fields
        int n = totalPayments();
        double r = periodicRate();

        if(r == 0){
            return principal / n; 
            // if there isn't an interest rate this formula gives an accurate payment amount
        }
        else{
            return principal * r / (1 - Math.pow(1 + r, -n)); // formula found on google
        }
    }

    /**
     * Calculates the total of all payments made over the life of the loan.
     *
     * @return total paid = {@code payment() * totalPayments()}
     */
    public double totalPaid(){
       return payment() * totalPayments();
    }

    /**
     * Calculates the total interest paid over the life of the loan.
     *
     * @return total interest = {@code totalPaid() - principal}
     */
    public double totalInterest(){
       return totalPaid() - principal;
    }
}