package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server waiting for a client to connect...");

        Socket socket = serverSocket.accept();
        System.out.println("Client connected");

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        while (true) {
            int x = dis.readInt();

            if (x == 0) {
                System.out.println("Received 0. Stopping communication...");
                break;
            }

            int result = x * 5;
            System.out.println("Received: " + x + " | Sending result: " + result);
            dos.writeInt(result);
        }

        dis.close();
        dos.close();
        socket.close();
        serverSocket.close();
        System.out.println("Connection closed.");
    }
}
