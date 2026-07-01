import java.io.*;
import java.net.*;

class Client {
    public static void main(String[] args) {

        try {

            Socket socket = new Socket("localhost", 5000);

            BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            pw.println("Hello Server");

            String response = br.readLine();

            System.out.println("Server: " + response);

            socket.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}