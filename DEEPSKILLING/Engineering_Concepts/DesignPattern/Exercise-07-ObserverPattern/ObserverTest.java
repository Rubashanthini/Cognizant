
public class ObserverTest {
    public static void main(String[] args) {
        System.out.println("=== Observer Pattern Test ===");

        StockMarket stockMarket = new StockMarket();

        Observer mobileApp = new MobileApp("StockTrackerMobile");
        Observer webApp = new WebApp("StockTrackerWeb");

        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        stockMarket.setStockPrice("AAPL", 195.32);
        stockMarket.setStockPrice("GOOG", 2830.15);

        stockMarket.deregisterObserver(webApp);

        stockMarket.setStockPrice("AAPL", 197.10);
    }
}
