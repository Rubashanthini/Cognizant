import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Integer, Product> products;

    public Inventory() {
        products = new HashMap<>();
    }

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    public boolean updateProduct(int productId, String productName, int quantity, double price) {
        Product product = products.get(productId);
        if (product == null) {
            return false;
        }
        product.setProductName(productName);
        product.setQuantity(quantity);
        product.setPrice(price);
        return true;
    }

    public boolean deleteProduct(int productId) {
        return products.remove(productId) != null;
    }

    public Product getProduct(int productId) {
        return products.get(productId);
    }

    public void displayAllProducts() {
        for (Product product : products.values()) {
            System.out.println(product);
        }
    }

    public int getTotalProducts() {
        return products.size();
    }
}
