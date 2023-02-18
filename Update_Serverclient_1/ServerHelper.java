import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

class ServerHelper {
    // private int disconnect_client;
    Socket socket;

    public ServerHelper() {
        _clientCount = 0;
        try {
            _server = new ServerSocket(PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void run() {
        try {
            while (true) {
                socket = _server.accept();

                ServerThread serverThread = new ServerThread(socket, _clientCount);
                _clientCount = _clientCount + 1;
                System.out.println("**********************************" + '\n' + "     New connection Alert" + '\n'
                        + "**********************************");
                System.out.println("Number of client connected: " + _clientCount);
                System.out.println("[SERVER] Active threads:" + ServerThread.activeCount());
                System.out.println("----------------------------------" + '\n' + "----------------------------------");

                serverThread.start();
                if (ServerThread.activeCount() == 0) {
                    socket.close();
                    _server.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    ServerSocket _server;
    int _clientCount;
    private static final int PORT = 9090;

}