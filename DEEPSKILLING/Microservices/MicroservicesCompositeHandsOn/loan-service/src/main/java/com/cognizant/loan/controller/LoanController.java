package com.cognizant.loan.controller;

import com.cognizant.common.dto.LoanDto;
import com.cognizant.loan.service.LoanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller exposing loan related endpoints.
 * <p>
 * Endpoint: {@code GET /loans/{number}}
 * </p>
 * <p>
 * Example: a request to
 * {@code http://localhost:8081/loans/H00987987972342}, or, via the
 * API Gateway, {@code http://localhost:9090/loan-service/loans/H00987987972342}.
 * </p>
 */
@RestController
public class LoanController {

    private final LoanService loanService;

    /**
     * Constructs the controller with its required {@link LoanService}
     * collaborator (constructor injection).
     *
     * @param loanService the service used to resolve loan details
     */
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    /**
     * Retrieves loan details for the given loan account number.
     *
     * @param number the loan account number path variable
     * @return a {@link LoanDto} describing the requested loan account
     */
    @GetMapping("/loans/{number}")
    public LoanDto getLoan(@PathVariable("number") String number) {
        return loanService.getLoanDetails(number);
    }
}
