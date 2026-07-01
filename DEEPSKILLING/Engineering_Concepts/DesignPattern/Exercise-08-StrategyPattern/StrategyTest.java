
public class StrategyTest {
    public static void main(String[] args) {
        System.out.println("=== Strategy Pattern Test ===");

        PaymentContext context = new PaymentContext();

        context.setPaymentStrategy(new CreditCardPayment("4111222233334444"));
        context.executePayment(250.75);

        context.setPaymentStrategy(new PayPalPayment("customer@example.com"));
        context.executePayment(89.99);
    }
}
