package com.cognizant.account.service;

import com.cognizant.account.model.Account;
import com.cognizant.common.dto.AccountDto;
import org.springframework.stereotype.Service;

/**
 * Service layer responsible for producing account information.
 * <p>
 * This hands-on exercise does not wire up a real backend/database;
 * instead, a deterministic dummy {@link Account} is generated for any
 * account number requested, mirroring the sample response used in the
 * original specification:
 * </p>
 * <pre>
 * { number: "00987987973432", type: "savings", balance: 234343 }
 * </pre>
 */
@Service
public class AccountService {

    /** Default account type returned for the dummy lookup. */
    private static final String DEFAULT_TYPE = "savings";

    /** Default balance returned for the dummy lookup. */
    private static final double DEFAULT_BALANCE = 234343.0;

    /**
     * Builds a dummy {@link AccountDto} for the given account number.
     * <p>
     * In a production system this method would query a persistence
     * layer or another downstream system; here it simply echoes the
     * requested account number back with fixed sample data, matching
     * the behaviour described in the hands-on specification.
     * </p>
     *
     * @param accountNumber the account number supplied by the caller
     * @return a populated {@link AccountDto} for the requested account number
     */
    public AccountDto getAccountDetails(String accountNumber) {
        Account account = new Account(accountNumber, DEFAULT_TYPE, DEFAULT_BALANCE);
        return toDto(account);
    }

    /**
     * Maps the internal {@link Account} domain object to the
     * externally exposed {@link AccountDto}.
     *
     * @param account the internal domain object to convert
     * @return the corresponding {@link AccountDto}
     */
    private AccountDto toDto(Account account) {
        return new AccountDto(account.getNumber(), account.getType(), account.getBalance());
    }
}
