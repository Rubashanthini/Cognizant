public class InventoryManagementTest {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        inventory.addProduct(new Product(101, "Laptop", 10, 55000.00));
        inventory.addProduct(new Product(102, "Mouse", 50, 500.00));
        inventory.addProduct(new Product(103, "Keyboard", 30, 1200.00));

        System.out.println("All Products After Adding:");
        inventory.displayAllProducts();

        System.out.println("\nUpdating Product 102...");
        inventory.updateProduct(102, "Wireless Mouse", 40, 750.00);

        System.out.println("\nAll Products After Update:");
        inventory.displayAllProducts();

        System.out.println("\nDeleting Product 103...");
        inventory.deleteProduct(103);

        System.out.println("\nAll Products After Deletion:");
        inventory.displayAllProducts();

        System.out.println("\nTotal Products Remaining: " + inventory.getTotalProducts());
    }
}
