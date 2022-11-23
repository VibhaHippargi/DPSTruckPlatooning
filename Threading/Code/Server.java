package Threading.Code;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
//import java.sql.Date;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.Thread;
import java.util.ArrayList;


class ServerThread extends Thread {
    public ServerThread(Socket socket, int count) {
        this.socket = socket;
        this.count = count;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while(true) {
                String outputString = input.readLine();
                System.out.println("Client["+ count +"] says" + outputString);
                if(outputString.contains("food")){
                    output.println("Hello");
                }
                else {
                    output.println("");
                }
            }
        } catch (Exception e) {
            System.err.println("Something bad happend");
        }
    }


    private Socket socket;
    private int count;
}

class ServerHelper {
    public ServerHelper() {
        _clientCount = 0;
        try {
            _server = new ServerSocket(PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void run() {
        while(true) {

            try {
                Socket socket = _server.accept();
                _clientCount += 1;
                System.out.println(_clientCount + " clients are connected");
                ServerThread serverThread = new ServerThread(socket, _clientCount);
                // _threadList.add(serverThread);
                serverThread.start();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }

    ServerSocket _server;
    private int _clientCount;
    // private ArrayList<ServerThread> _threadList;

    private static final int PORT = 9090;
}

public class Server {

    public static void main(String[] args) {
        System.out.println("Server Started");
        ServerHelper _serverHelper = new ServerHelper();
        _serverHelper.run();
    }
}
