
public class RazorpayAdapter implements PaymentProcessor {
    private final RazorpayGateway razorpayGateway;

    public RazorpayAdapter(RazorpayGateway razorpayGateway) {
        this.razorpayGateway = razorpayGateway;
    }

    @Override
    public void processPayment(double amount) {
       
        razorpayGateway.pay(String.valueOf(amount));
    }
}
