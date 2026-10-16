package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server3 {

    private static final int PORT = 1234;

    public static void main(String[] args) throws IOException {
        // Bind to the machine's real IP address for cross-machine connections
        InetAddress hostAddress = InetAddress.getLocalHost();
        InetSocketAddress socketAddress = new InetSocketAddress(hostAddress.getHostAddress(), PORT);

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(socketAddress);
        System.out.println("Server listening on " + hostAddress.getHostAddress() + ":" + PORT);
        System.out.println("Waiting for a client to connect...");

        Socket socket = serverSocket.accept();
        System.out.println("Client connected from: " + socket.getInetAddress().getHostAddress());

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        while (true) {
            int choice = dis.readInt();

            if (choice == 0) {
                System.out.println("Client chose to exit. Stopping...");
                break;
            }

            int x = dis.readInt();
            double result = 0.0;

            switch (choice) {
                case 1:
                    result = x + 5;
                    System.out.println("Operation: Addition | " + x + " + 5 = " + result);
                    break;
                case 2:
                    result = x - 5;
                    System.out.println("Operation: Subtraction | " + x + " - 5 = " + result);
                    break;
                case 3:
                    result = x * 5;
                    System.out.println("Operation: Multiplication | " + x + " * 5 = " + result);
                    break;
                case 4:
                    result = (double) x / 5;
                    System.out.println("Operation: Division | " + x + " / 5 = " + result);
                    break;
                default:
                    System.out.println("Unknown operation: " + choice);
                    result = Double.NaN;
                    break;
            }

            dos.writeDouble(result);
        }

        dis.close();
        dos.close();
        socket.close();
        serverSocket.close();
        System.out.println("Connection closed.");
    }
}
