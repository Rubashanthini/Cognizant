
public class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public PaymentContext() {
    }

    public PaymentContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    
    public void executePayment(double amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("No payment strategy has been set.");
        }
        paymentStrategy.pay(amount);
    }
}
