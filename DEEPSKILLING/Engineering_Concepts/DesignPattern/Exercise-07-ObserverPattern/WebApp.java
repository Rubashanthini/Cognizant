
public class WebApp implements Observer {
    private final String appName;

    public WebApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String stockSymbol, double price) {
        System.out.println("[WebApp:" + appName + "] Received update -> "
                + stockSymbol + " = $" + price);
    }
}
