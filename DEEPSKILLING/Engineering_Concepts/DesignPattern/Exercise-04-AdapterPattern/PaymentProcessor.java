
public interface PaymentProcessor {
    /**
     * Processes a payment of the given amount.
     *
     * @param amount the amount to charge
     */
    void processPayment(double amount);
}
