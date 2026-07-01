
public class RealImage implements Image {
    private final String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromRemoteServer();
    }

    private void loadFromRemoteServer() {
        System.out.println("RealImage: Loading \"" + fileName + "\" from remote server...");
        try {
            Thread.sleep(200); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("RealImage: \"" + fileName + "\" loaded successfully.");
    }

    @Override
    public void display() {
        System.out.println("RealImage: Displaying \"" + fileName + "\".");
    }
}
