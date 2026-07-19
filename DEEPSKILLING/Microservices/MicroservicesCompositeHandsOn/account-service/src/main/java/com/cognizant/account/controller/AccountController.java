package com.cognizant.account.controller;

import com.cognizant.common.dto.AccountDto;
import com.cognizant.account.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller exposing account related endpoints.
 * <p>
 * Endpoint: {@code GET /accounts/{number}}
 * </p>
 * <p>
 * Example: a request to
 * {@code http://localhost:8080/accounts/00987987973432}, or, via the
 * API Gateway, {@code http://localhost:9090/account-service/accounts/00987987973432}.
 * </p>
 */
@RestController
public class AccountController {

    private final AccountService accountService;

    /**
     * Constructs the controller with its required {@link AccountService}
     * collaborator (constructor injection).
     *
     * @param accountService the service used to resolve account details
     */
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Retrieves account details for the given account number.
     *
     * @param number the account number path variable
     * @return an {@link AccountDto} describing the requested account
     */
    @GetMapping("/accounts/{number}")
    public AccountDto getAccount(@PathVariable("number") String number) {
        return accountService.getAccountDetails(number);
    }
}
