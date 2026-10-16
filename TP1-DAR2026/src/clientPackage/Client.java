package clientPackage;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main (String[] args) throws IOException{
        Socket socket = new Socket("172.24.71.205",1234);
        System.out.println("im connected");
        socket.close();

    }
}
