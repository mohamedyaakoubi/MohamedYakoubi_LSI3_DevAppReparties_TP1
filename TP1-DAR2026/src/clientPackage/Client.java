package clientPackage;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main (String[] args) throws IOException{
        Socket socket = new Socket("192.168.192.1",1234);
        System.out.println("im connected");
        socket.close();

    }
}
