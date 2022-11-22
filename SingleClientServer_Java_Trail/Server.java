import java.net.ServerSocket;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class Server {
    private static final int port = 9090;

    public static void main(String[] arg) throws IOException {
        ServerSocket listener = new ServerSocket(port);

        System.out.println("[Server] waiting for client");
        Socket client = listener.accept();
        System.out.println("[Server] connected");

        // this line is responsible to send the message to the client
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        out.println((new Date()).toString());

        System.out.println("[Server] Sent date to Client");
        client.close();
        listener.close();

    }
}
