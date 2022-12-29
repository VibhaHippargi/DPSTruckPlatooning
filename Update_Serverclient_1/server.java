import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.lang.Thread;
import java.lang.String;

class ServerThread extends Thread {

    private Socket socket;
    private int count;
    private int disconnected_client;
    private boolean flag;

    public ServerThread(Socket socket, int count) {
        this.socket = socket;
        this.count = count + 1;

    }

    @Override
    public void run() {

        while (true) {

            try {
                // BufferedReader input = new BufferedReader(new
                // InputStreamReader(socket.getInputStream()));
                ObjectInputStream clientInput = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream clientOuput = new ObjectOutputStream(socket.getOutputStream());
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                monitorPlatoonData check = new monitorPlatoonData();

                platoon clientPlatoon;

                try {
                    clientPlatoon = (platoon) clientInput.readObject();

                    if (clientPlatoon.getQuit()) {
                        System.out.println("Client[" + count + "] has requested to quit" + '\n' +
                                "Disconnecting Client[" + count + "]");
                        output.println("Disconnecting Client[" + count + "]"); // send data to client
                    } else {
                        System.out.println("Client[" + count + "] has sent : ");
                        System.out.println("Distance: " + clientPlatoon.getDistance() + '\n'
                                + "Signal Strength: " + clientPlatoon.getSignal_strength() + '\n'
                                + "Speed: " + clientPlatoon.getSpeed() + '\n'
                                + "Location: " + clientPlatoon.getLocation().lat
                                + " " + clientPlatoon.getLocation().lng + '\n'
                                + "Weather: " + clientPlatoon.getWeather() + '\n'
                                + "Object Status " + clientPlatoon.getobject_detection() + "\n"
                                + "Quit: " + clientPlatoon.getQuit());

                        ArrayList<String> result_List = new ArrayList<>();
                        // Object detection,change of command
                        if (clientPlatoon.getobject_detection() == true) {
                            String s = check.monitor_speed(clientPlatoon.getObject_detected_inmtrs(),
                                    clientPlatoon.getSpeed());
                            result_List.add(("Object Detected by given Platoon Slow DOWN!!!") + s);
                        }
                        // call monitor methods

                        result_List.add(check.monitor_distance(clientPlatoon.getDistance()));
                        result_List.add(check.monitor_signal_strength(clientPlatoon.getSignal_strength()));
                        result_List.add(
                                check.monitor_speed(clientPlatoon.getDistance(), clientPlatoon.getSpeed()));

                        output.println("[SERVER] sent data to Client[" + count + "] " +
                                result_List);
                        System.out.println(result_List);

                    }

                    System.out.println("------------------------------- ");

                } catch (ClassNotFoundException e) {

                    e.printStackTrace();
>>>>>>> Stashed changes
                }

            }

            catch (IOException e) {

                e.printStackTrace();
            }
            ;
        }
    }

}

class ServerHelper {
    // private int disconnect_client;

    private int disconnect_client;
    private boolean flag;

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
                Socket socket = _server.accept();

                ServerThread serverThread = new ServerThread(socket, _clientCount);
                _clientCount = _clientCount + 1;
                System.out.println("**********************************" + '\n' + "     New connection Alert" + '\n'
                        + "**********************************");
                System.out.println("Number of client connected: " + _clientCount);
                System.out.println("[SERVER] Active threads:" + ServerThread.activeCount());
                System.out.println("----------------------------------" + '\n' + "----------------------------------");
                // ServerThread serverThread = new ServerThread(socket, _clientCount);
                // _threadList.add(serverThread);
                serverThread.start();

                if ((disconnect_client == 0) && (flag == true)) {
                    socket.close();
                    _server.close();
                }
            }
        } catch (IOException e)

        {

            e.printStackTrace();
        }

    }

    ServerSocket _server;
    int _clientCount;
    // int disconnect_client;
    // private ArrayList<ServerThread> _threadList;

    private static final int PORT = 9090;

}

public class server {

    public static void main(String[] args) throws IOException {

        System.out.println("----------------------------------" + '\n' + "----------------------------------");
        System.out.println(Messages.START.getMessage());
        System.out.println("----------------------------------" + '\n' + "----------------------------------");

        ServerHelper _serverHelper = new ServerHelper();
        _serverHelper.run();

    }

}
