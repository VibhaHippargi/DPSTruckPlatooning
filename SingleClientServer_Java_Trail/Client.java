import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client {
    public static final String ServerIP = "127.0.0.1";
    public static final int ServerOP = 9090;

    public static void main(String[] args) throws IOException {
        // this line establishes the connection with the server
        Socket socket = new Socket(ServerIP, ServerOP);

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String serverResponse = input.readLine();

        JOptionPane.showMessageDialog(null, serverResponse);

        socket.close();
        System.exit(0);

    }
}
