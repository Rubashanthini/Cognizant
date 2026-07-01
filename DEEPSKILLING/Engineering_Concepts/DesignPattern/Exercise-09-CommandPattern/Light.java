
public class Light {
    private final String location;
    private boolean on;

    public Light(String location) {
        this.location = location;
    }

    public void turnOn() {
        on = true;
        System.out.println(location + " light is now ON.");
    }

    public void turnOff() {
        on = false;
        System.out.println(location + " light is now OFF.");
    }

    public boolean isOn() {
        return on;
    }
}
