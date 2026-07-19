package com.cognizant.common.dto;

import java.util.Objects;

/**
 * Data Transfer Object representing a bank loan account.
 * <p>
 * This DTO is shared between the {@code loan-service} module (which
 * produces it) and any other module that may need to consume loan
 * information in a strongly typed way.
 * </p>
 */
public class LoanDto {

    /** Unique loan account number, e.g. "H00987987972342". */
    private String number;

    /** Type of loan, e.g. "car" or "home". */
    private String type;

    /** Total sanctioned loan amount. */
    private double loan;

    /** Equated Monthly Installment amount. */
    private double emi;

    /** Tenure of the loan, expressed in months. */
    private int tenure;

    /**
     * Default no-argument constructor.
     * Required for JSON (de)serialization frameworks such as Jackson.
     */
    public LoanDto() {
    }

    /**
     * Fully parameterized constructor.
     *
     * @param number the loan account number
     * @param type   the loan type
     * @param loan   the total loan amount
     * @param emi    the equated monthly installment
     * @param tenure the tenure in months
     */
    public LoanDto(String number, String type, double loan, double emi, int tenure) {
        this.number = number;
        this.type = type;
        this.loan = loan;
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
    public double getLoan() {
        return loan;
    }

    /**
     * @param loan the total loan amount to set
     */
    public void setLoan(double loan) {
        this.loan = loan;
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
        if (!(o instanceof LoanDto)) {
            return false;
        }
        LoanDto loanDto = (LoanDto) o;
        return Double.compare(loanDto.loan, loan) == 0
                && Double.compare(loanDto.emi, emi) == 0
                && tenure == loanDto.tenure
                && Objects.equals(number, loanDto.number)
                && Objects.equals(type, loanDto.type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(number, type, loan, emi, tenure);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "LoanDto{"
                + "number='" + number + '\''
                + ", type='" + type + '\''
                + ", loan=" + loan
                + ", emi=" + emi
                + ", tenure=" + tenure
                + '}';
    }
}
