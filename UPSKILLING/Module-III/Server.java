import java.io.*;
import java.net.*;

class Server {
    public static void main(String[] args) {

        try {

            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Server Waiting...");

            Socket socket = serverSocket.accept();

            BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            String message = br.readLine();

            System.out.println("Client: " + message);

            pw.println("Hello Client");

            socket.close();
            serverSocket.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}