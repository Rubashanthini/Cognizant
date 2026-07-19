package com.cognizant.loan.model;

import java.util.Objects;

/**
 * Internal domain representation of a bank loan, as held by the
 * loan-service. This is intentionally kept separate from
 * {@link com.cognizant.common.dto.LoanDto} to demonstrate a clean
 * separation between an internal domain model and the DTO exposed at
 * the service boundary; {@link com.cognizant.loan.service.LoanService}
 * is responsible for mapping between the two.
 */
public class Loan {

    /** Unique loan account number. */
    private String number;

    /** Type of loan, e.g. "car" or "home". */
    private String type;

    /** Total sanctioned loan amount. */
    private double loanAmount;

    /** Equated Monthly Installment amount. */
    private double emi;

    /** Tenure of the loan, expressed in months. */
    private int tenure;

    /**
     * Default no-argument constructor.
     */
    public Loan() {
    }

    /**
     * Fully parameterized constructor.
     *
     * @param number     the loan account number
     * @param type       the loan type
     * @param loanAmount the total loan amount
     * @param emi        the equated monthly installment
     * @param tenure     the tenure, in months
     */
    public Loan(String number, String type, double loanAmount, double emi, int tenure) {
        this.number = number;
        this.type = type;
        this.loanAmount = loanAmount;
        this.emi = emi;
        this.tenure = tenure;
    }

    /**
     * @return the loan account number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the loan account number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the loan type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the loan type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the total loan amount
     */
    public double getLoanAmount() {
        return loanAmount;
    }

    /**
     * @param loanAmount the total loan amount to set
     */
    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    /**
     * @return the equated monthly installment
     */
    public double getEmi() {
        return emi;
    }

    /**
     * @param emi the equated monthly installment to set
     */
    public void setEmi(double emi) {
        this.emi = emi;
    }

    /**
     * @return the tenure of the loan, in months
     */
    public int getTenure() {
        return tenure;
    }

    /**
     * @param tenure the tenure, in months, to set
     */
    public void setTenure(int tenure) {
        this.tenure = tenure;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Loan)) {
            return false;
        }
        Loan loan = (Loan) o;
        return Double.compare(loan.loanAmount, loanAmount) == 0
                && Double.compare(loan.emi, emi) == 0
                && tenure == loan.tenure
                && Objects.equals(number, loan.number)
                && Objects.equals(type, loan.type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(number, type, loanAmount, emi, tenure);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Loan{"
                + "number='" + number + '\''
                + ", type='" + type + '\''
                + ", loanAmount=" + loanAmount
                + ", emi=" + emi
                + ", tenure=" + tenure
                + '}';
    }
}
