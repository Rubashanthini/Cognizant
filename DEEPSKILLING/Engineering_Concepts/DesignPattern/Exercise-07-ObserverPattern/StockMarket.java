import java.util.ArrayList;
import java.util.List;

public class StockMarket implements Stock {
    private final List<Observer> observers = new ArrayList<>();
    private String stockSymbol;
    private double price;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
        System.out.println("StockMarket: Registered a new observer.");
    }

    @Override
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
        System.out.println("StockMarket: Deregistered an observer.");
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockSymbol, price);
        }
    }

    /**
     * Updates the price of a stock and notifies all observers.
     *
     * @param stockSymbol the stock symbol whose price changed
     * @param newPrice    the new price of the stock
     */
    public void setStockPrice(String stockSymbol, double newPrice) {
        this.stockSymbol = stockSymbol;
        this.price = newPrice;
        System.out.println("\nStockMarket: " + stockSymbol + " price updated to $" + newPrice);
        notifyObservers();
    }
}
