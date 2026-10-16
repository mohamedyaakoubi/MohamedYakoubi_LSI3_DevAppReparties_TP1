package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client3 {

    private static final int PORT = 1234;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Connect to server on a different machine using InetAddress / InetSocketAddress
        System.out.print("Enter server IP address: ");
        String serverIP = scanner.next();

        InetAddress address = InetAddress.getByName(serverIP);
        InetSocketAddress socketAddress = new InetSocketAddress(address, PORT);

        Socket socket = new Socket();
        socket.connect(socketAddress);
        System.out.println("Connected to server at " + serverIP + ":" + PORT);

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        // Step 2: Menu-driven calculator loop
        while (true) {
            System.out.println("\n===== Calculator Menu =====");
            System.out.println("1. Addition (x + 5)");
            System.out.println("2. Subtraction (x - 5)");
            System.out.println("3. Multiplication (x * 5)");
            System.out.println("4. Division (x / 5)");
            System.out.println("0. Exit");
            System.out.print("Choose an operation: ");

            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Exiting...");
                dos.writeInt(choice);
                break;
            }

            System.out.print("Enter an integer x: ");
            int x = scanner.nextInt();

            // Send operation choice and value to server
            dos.writeInt(choice);
            dos.writeInt(x);

            // Receive result from server
            double result = dis.readDouble();
            System.out.println("Result: " + result);
        }

        scanner.close();
        dis.close();
        dos.close();
        socket.close();
        System.out.println("Connection closed.");
    }
}
