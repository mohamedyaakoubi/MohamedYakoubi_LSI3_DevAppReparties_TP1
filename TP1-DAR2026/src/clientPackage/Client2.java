package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("172.24.71.205", 1234);
        System.out.println("Connected to server");

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter an integer (0 to stop): ");
            int x = scanner.nextInt();

            if (x == 0) {
                System.out.println("Sending 0 to stop communication...");
                dos.writeInt(x);
                break;
            }

            dos.writeInt(x);

            int result = dis.readInt();
            System.out.println("Server result (x * 5): " + result);
        }

        scanner.close();
        dis.close();
        dos.close();
        socket.close();
        System.out.println("Connection closed.");
    }
}
