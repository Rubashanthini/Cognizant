
public class AdapterTest {
    public static void main(String[] args) {
        System.out.println("=== Adapter Pattern Test ===");

        PaymentProcessor payPal = new PayPalAdapter(new PayPalGateway());
        PaymentProcessor stripe = new StripeAdapter(new StripeGateway());
        PaymentProcessor razorpay = new RazorpayAdapter(new RazorpayGateway());

        payPal.processPayment(150.00);
        stripe.processPayment(75.50);
        razorpay.processPayment(999.99);
    }
}
