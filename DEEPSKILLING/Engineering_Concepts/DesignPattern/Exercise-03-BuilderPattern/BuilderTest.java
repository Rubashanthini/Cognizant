
public class BuilderTest {
    public static void main(String[] args) {
        System.out.println("=== Builder Pattern Test ===");

        
        Computer officeComputer = new Computer.Builder("Intel i3")
                .build();

        
        Computer gamingComputer = new Computer.Builder("Intel i9")
                .ram("32GB")
                .storage("2TB NVMe SSD")
                .gpu("NVIDIA RTX 4090")
                .wifi(true)
                .bluetooth(true)
                .build();

        System.out.println("--- Office PC ---");
        System.out.println(officeComputer);

        System.out.println("\n--- Gaming PC ---");
        System.out.println(gamingComputer);
    }
}
