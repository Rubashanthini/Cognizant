
public class ProxyImage implements Image {
    private final String fileName;
    private RealImage realImage; 

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            System.out.println("ProxyImage: No cached image found for \"" + fileName + "\".");
            realImage = new RealImage(fileName); 
        } else {
            System.out.println("ProxyImage: Using cached image for \"" + fileName + "\".");
        }
        realImage.display();
    }
}
