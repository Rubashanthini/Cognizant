package com.cognizant.loan.service;

import com.cognizant.loan.model.Loan;
import com.cognizant.common.dto.LoanDto;
import org.springframework.stereotype.Service;

/**
 * Service layer responsible for producing loan account information.
 * <p>
 * This hands-on exercise does not wire up a real backend/database;
 * instead, a deterministic dummy {@link Loan} is generated for any
 * loan account number requested, mirroring the sample response used
 * in the original specification:
 * </p>
 * <pre>
 * { number: "H00987987972342", type: "car", loan: 400000, emi: 3258, tenure: 18 }
 * </pre>
 */
@Service
public class LoanService {

    /** Default loan type returned for the dummy lookup. */
    private static final String DEFAULT_TYPE = "car";

    /** Default sanctioned loan amount returned for the dummy lookup. */
    private static final double DEFAULT_LOAN_AMOUNT = 400000.0;

    /** Default equated monthly installment returned for the dummy lookup. */
    private static final double DEFAULT_EMI = 3258.0;

    /** Default tenure (in months) returned for the dummy lookup. */
    private static final int DEFAULT_TENURE = 18;

    /**
     * Builds a dummy {@link LoanDto} for the given loan account number.
     * <p>
     * In a production system this method would query a persistence
     * layer or another downstream system; here it simply echoes the
     * requested loan number back with fixed sample data, matching the
     * behaviour described in the hands-on specification.
     * </p>
     *
     * @param loanNumber the loan account number supplied by the caller
     * @return a populated {@link LoanDto} for the requested loan number
     */
    public LoanDto getLoanDetails(String loanNumber) {
        Loan loan = new Loan(loanNumber, DEFAULT_TYPE, DEFAULT_LOAN_AMOUNT, DEFAULT_EMI, DEFAULT_TENURE);
        return toDto(loan);
    }

    /**
     * Maps the internal {@link Loan} domain object to the externally
     * exposed {@link LoanDto}.
     *
     * @param loan the internal domain object to convert
     * @return the corresponding {@link LoanDto}
     */
    private LoanDto toDto(Loan loan) {
        return new LoanDto(loan.getNumber(), loan.getType(), loan.getLoanAmount(), loan.getEmi(), loan.getTenure());
    }
}
