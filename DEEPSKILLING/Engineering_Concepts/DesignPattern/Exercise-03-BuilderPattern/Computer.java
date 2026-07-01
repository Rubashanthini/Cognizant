
public class Computer {

    
    private final String cpu;

   
    private final String ram;
    private final String storage;
    private final String gpu;
    private final boolean hasWifi;
    private final boolean hasBluetooth;

    /**
     * Private constructor - a Computer can only be created through
     * its Builder.
     *
     * @param builder the Builder instance holding the configuration
     */
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.gpu = builder.gpu;
        this.hasWifi = builder.hasWifi;
        this.hasBluetooth = builder.hasBluetooth;
    }

    @Override
    public String toString() {
        return "Computer Configuration:\n" +
                "  CPU        : " + cpu + "\n" +
                "  RAM        : " + ram + "\n" +
                "  Storage    : " + storage + "\n" +
                "  GPU        : " + (gpu == null ? "Integrated" : gpu) + "\n" +
                "  WiFi       : " + (hasWifi ? "Yes" : "No") + "\n" +
                "  Bluetooth  : " + (hasBluetooth ? "Yes" : "No");
    }

  
    public static class Builder {
        private String cpu;
        private String ram = "8GB";        
        private String storage = "256GB SSD"; 
        private String gpu;
        private boolean hasWifi;
        private boolean hasBluetooth;

        /**
         * @param cpu the processor of the computer (required)
         */
        public Builder(String cpu) {
            this.cpu = cpu;
        }

        public Builder ram(String ram) {
            this.ram = ram;
            return this;
        }

        public Builder storage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder wifi(boolean hasWifi) {
            this.hasWifi = hasWifi;
            return this;
        }

        public Builder bluetooth(boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }

        /**
         * Builds and returns an immutable Computer instance.
         *
         * @return a fully configured Computer object
         */
        public Computer build() {
            return new Computer(this);
        }
    }
}
