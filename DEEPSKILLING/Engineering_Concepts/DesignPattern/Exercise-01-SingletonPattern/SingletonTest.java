
public class SingletonTest {
    public static void main(String[] args) {
        System.out.println("=== Singleton Pattern Test ===");

        
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("Application started.");
        logger2.log("Fetching user data.");
        logger1.log("Processing complete.");

        
        if (logger1 == logger2) {
            System.out.println("SUCCESS: logger1 and logger2 refer to the SAME instance.");
        } else {
            System.out.println("FAILURE: logger1 and logger2 are DIFFERENT instances.");
        }

        System.out.println("Total log entries recorded: " + logger1.getLogCount());
    }
}
