package com.cognizant.common.dto;

import java.util.Objects;

/**
 * Data Transfer Object representing a bank account.
 * <p>
 * This DTO is shared between the {@code account-service} module (which
 * produces it) and any other module that may need to consume account
 * information in a strongly typed way.
 * </p>
 */
public class AccountDto {

    /** Unique account number, e.g. "00987987973432". */
    private String number;

    /** Type of account, e.g. "savings" or "current". */
    private String type;

    /** Current balance held in the account. */
    private double balance;

    /**
     * Default no-argument constructor.
     * Required for JSON (de)serialization frameworks such as Jackson.
     */
    public AccountDto() {
    }

    /**
     * Fully parameterized constructor.
     *
     * @param number  the account number
     * @param type    the account type
     * @param balance the current balance
     */
    public AccountDto(String number, String type, double balance) {
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
     * @return the account balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the account balance to set
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
        if (!(o instanceof AccountDto)) {
            return false;
        }
        AccountDto that = (AccountDto) o;
        return Double.compare(that.balance, balance) == 0
                && Objects.equals(number, that.number)
                && Objects.equals(type, that.type);
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
        return "AccountDto{"
                + "number='" + number + '\''
                + ", type='" + type + '\''
                + ", balance=" + balance
                + '}';
    }
}
