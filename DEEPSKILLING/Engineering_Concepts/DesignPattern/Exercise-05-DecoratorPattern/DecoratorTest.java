
public class DecoratorTest {
    public static void main(String[] args) {
        System.out.println("=== Decorator Pattern Test ===");

        System.out.println("-- Plain Email Notifier --");
        Notifier emailOnly = new EmailNotifier();
        emailOnly.send("Server maintenance scheduled tonight.");

        System.out.println("\n-- Email + SMS Notifier --");
        Notifier emailAndSms = new SMSNotifierDecorator(new EmailNotifier());
        emailAndSms.send("Your order has shipped.");

        System.out.println("\n-- Email + SMS + Slack Notifier --");
        Notifier fullyDecorated = new SlackNotifierDecorator(
                new SMSNotifierDecorator(
                        new EmailNotifier()));
        fullyDecorated.send("Production deployment completed successfully.");
    }
}
