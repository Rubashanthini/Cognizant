
public class CommandTest {
    public static void main(String[] args) {
        System.out.println("=== Command Pattern Test ===");

        Light livingRoomLight = new Light("Living Room");
        Light bedroomLight = new Light("Bedroom");

        Command livingRoomOn = new LightOnCommand(livingRoomLight);
        Command livingRoomOff = new LightOffCommand(livingRoomLight);
        Command bedroomOn = new LightOnCommand(bedroomLight);
        Command bedroomOff = new LightOffCommand(bedroomLight);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(livingRoomOn);
        remote.pressButton();

        remote.setCommand(bedroomOn);
        remote.pressButton();

        remote.setCommand(livingRoomOff);
        remote.pressButton();

        remote.setCommand(bedroomOff);
        remote.pressButton();
    }
}
