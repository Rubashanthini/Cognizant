import java.util.Arrays;
import java.util.Comparator;

public class SearchTest {
    public static void main(String[] args) {
        Product[] products = {
            new Product(101, "Laptop", "Electronics"),
            new Product(102, "Headphones", "Electronics"),
            new Product(103, "Blender", "Home Appliances"),
            new Product(104, "Sneakers", "Footwear"),
            new Product(105, "Backpack", "Accessories")
        };

        System.out.println("Linear Search for 'Blender':");
        Product linearResult = LinearSearch.search(products, "Blender");
        System.out.println(linearResult != null ? linearResult : "Product not found");

        Product[] sortedProducts = Arrays.copyOf(products, products.length);
        Arrays.sort(sortedProducts, Comparator.comparing(Product::getProductName));

        System.out.println("\nSorted Products for Binary Search:");
        for (Product product : sortedProducts) {
            System.out.println(product);
        }

        System.out.println("\nBinary Search for 'Sneakers':");
        Product binaryResult = BinarySearch.search(sortedProducts, "Sneakers");
        System.out.println(binaryResult != null ? binaryResult : "Product not found");

        System.out.println("\nBinary Search for 'Tablet' (not present):");
        Product notFoundResult = BinarySearch.search(sortedProducts, "Tablet");
        System.out.println(notFoundResult != null ? notFoundResult : "Product not found");
    }
}
