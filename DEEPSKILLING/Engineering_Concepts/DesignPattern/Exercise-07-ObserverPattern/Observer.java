
public interface Observer {
    /**
     * Called by the subject whenever the observed state changes.
     *
     * @param stockSymbol the symbol of the stock that changed
     * @param price       the new price of the stock
     */
    void update(String stockSymbol, double price);
}
