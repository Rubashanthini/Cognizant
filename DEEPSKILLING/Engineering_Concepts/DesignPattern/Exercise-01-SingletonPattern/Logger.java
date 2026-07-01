
public class Logger {

    private static Logger instance;

    
    private int logCount;

   
    private Logger() {
        logCount = 0;
        System.out.println("Logger instance created.");
    }

    /**
     * Public static method that returns the single instance of Logger.
     * Uses lazy initialization with synchronized access to make it
     * thread-safe.
     *
     * @return the single Logger instance
     */
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    /**
     * Logs a message to the console and increments the log counter.
     *
     * @param message the message to log
     */
    public void log(String message) {
        logCount++;
        System.out.println("[LOG #" + logCount + "] " + message);
    }

    /**
     * @return the total number of messages logged so far
     */
    public int getLogCount() {
        return logCount;
    }
}
