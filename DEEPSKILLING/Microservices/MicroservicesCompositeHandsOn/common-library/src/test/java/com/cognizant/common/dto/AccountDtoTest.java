package com.cognizant.common.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * JUnit 5 test class validating the behaviour of {@link AccountDto},
 * {@link LoanDto} and {@link GreetingDto}.
 */
class AccountDtoTest {

    /**
     * Verifies that the all-args constructor correctly assigns every field
     * and that the corresponding getters return the same values.
     */
    @Test
    void testAccountDtoConstructorAndGetters() {
        AccountDto account = new AccountDto("00987987973432", "savings", 234343.0);

        assertEquals("00987987973432", account.getNumber());
        assertEquals("savings", account.getType());
        assertEquals(234343.0, account.getBalance());
    }

    /**
     * Verifies that setters correctly mutate an object created via the
     * no-argument constructor.
     */
    @Test
    void testAccountDtoSetters() {
        AccountDto account = new AccountDto();
        account.setNumber("00987987973432");
        account.setType("savings");
        account.setBalance(234343.0);

        assertEquals("00987987973432", account.getNumber());
        assertEquals("savings", account.getType());
        assertEquals(234343.0, account.getBalance());
    }

    /**
     * Verifies value based equality between two {@link AccountDto}
     * instances with identical field values, and inequality when a
     * field differs.
     */
    @Test
    void testAccountDtoEqualsAndHashCode() {
        AccountDto first = new AccountDto("00987987973432", "savings", 234343.0);
        AccountDto second = new AccountDto("00987987973432", "savings", 234343.0);
        AccountDto different = new AccountDto("00987987973432", "current", 100.0);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
        assertNotEquals(first, different);
    }

    /**
     * Verifies that {@link LoanDto} correctly stores and returns all of
     * its fields.
     */
    @Test
    void testLoanDtoConstructorAndGetters() {
        LoanDto loan = new LoanDto("H00987987972342", "car", 400000, 3258, 18);

        assertEquals("H00987987972342", loan.getNumber());
        assertEquals("car", loan.getType());
        assertEquals(400000, loan.getLoan());
        assertEquals(3258, loan.getEmi());
        assertEquals(18, loan.getTenure());
    }

    /**
     * Verifies that {@link GreetingDto} correctly stores and returns its
     * message field.
     */
    @Test
    void testGreetingDto() {
        GreetingDto greeting = new GreetingDto("Hello World");
        assertEquals("Hello World", greeting.getMessage());
    }
}
