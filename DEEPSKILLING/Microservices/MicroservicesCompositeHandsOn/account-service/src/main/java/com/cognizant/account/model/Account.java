package com.cognizant.account.model;

import java.util.Objects;

/**
 * Internal domain representation of a bank account, as held by the
 * account-service. This is intentionally kept separate from
 * {@link com.cognizant.common.dto.AccountDto} to demonstrate a clean
 * separation between an internal domain model and the DTO exposed at
 * the service boundary; {@link com.cognizant.account.service.AccountService}
 * is responsible for mapping between the two.
 */
public class Account {

    /** Unique account number. */
    private String number;

    /** Type of account, e.g. "savings" or "current". */
    private String type;

    /** Current balance held in the account. */
    private double balance;

    /**
     * Default no-argument constructor.
     */
    public Account() {
    }

    /**
     * Fully parameterized constructor.
     *
     * @param number  the account number
     * @param type    the account type
     * @param balance the current balance
     */
    public Account(String number, String type, double balance) {
        this.number = number;
        this.type = type;
        this.balance = balance;
    }

    /**
     * @return the account number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the account number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the account type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the account type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the current balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0
                && Objects.equals(number, account.number)
                && Objects.equals(type, account.type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(number, type, balance);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Account{"
                + "number='" + number + '\''
                + ", type='" + type + '\''
                + ", balance=" + balance
                + '}';
    }
}
