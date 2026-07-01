
public class MobileApp implements Observer {
    private final String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String stockSymbol, double price) {
        System.out.println("[MobileApp:" + appName + "] Received update -> "
                + stockSymbol + " = $" + price);
    }
}
