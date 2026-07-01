public class SortingTest {
    public static void main(String[] args) {
        Order[] bubbleOrders = {
            new Order(1, "Alice", 2500.00),
            new Order(2, "Bob", 900.50),
            new Order(3, "Charlie", 4200.75),
            new Order(4, "David", 150.00),
            new Order(5, "Eva", 3300.20)
        };

        System.out.println("Orders Before Bubble Sort:");
        printOrders(bubbleOrders);

        BubbleSort.sort(bubbleOrders);

        System.out.println("\nOrders After Bubble Sort (by Total Price):");
        printOrders(bubbleOrders);

        Order[] quickOrders = {
            new Order(1, "Alice", 2500.00),
            new Order(2, "Bob", 900.50),
            new Order(3, "Charlie", 4200.75),
            new Order(4, "David", 150.00),
            new Order(5, "Eva", 3300.20)
        };

        System.out.println("\nOrders Before Quick Sort:");
        printOrders(quickOrders);

        QuickSort.sort(quickOrders, 0, quickOrders.length - 1);

        System.out.println("\nOrders After Quick Sort (by Total Price):");
        printOrders(quickOrders);
    }

    private static void printOrders(Order[] orders) {
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
