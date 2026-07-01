
public class ProxyTest {
    public static void main(String[] args) {
        System.out.println("=== Proxy Pattern Test ===");

        Image image1 = new ProxyImage("landscape.jpg");
        Image image2 = new ProxyImage("portrait.jpg");

        System.out.println("\n-- First display of landscape.jpg (loads from server) --");
        image1.display();

        System.out.println("\n-- Second display of landscape.jpg (uses cache) --");
        image1.display();

        System.out.println("\n-- First display of portrait.jpg (loads from server) --");
        image2.display();
    }
}
