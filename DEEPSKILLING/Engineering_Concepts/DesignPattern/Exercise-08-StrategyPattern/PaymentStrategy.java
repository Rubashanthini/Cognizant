
public interface PaymentStrategy {
    /**
     * Pays the given amount using this strategy's payment method.
     *
     * @param amount the amount to pay
     */
    void pay(double amount);
}
