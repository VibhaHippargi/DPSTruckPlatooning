import java.net.Socket;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.lang.Thread;
import java.lang.String;

class ServerThread extends Thread {

    private Socket socket;
    private int count;

    public ServerThread(Socket socket, int count) {
        this.socket = socket;
        this.count = count + 1;

    }

    @Override
    public void run() {

        while (true) {

            try {
                ObjectInputStream clientInput = new ObjectInputStream(socket.getInputStream());
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                monitorPlatoonData check = new monitorPlatoonData();
                platoon clientPlatoon;
                String object_detect = "Road Safe no object";
                leader lead1 = new leader();

                try {

                    String weather_con = check.Weather_monitoring(lead1.getWeather());
                    // output.println(weather_con);
                    System.out.println("Server Status:  " +
                            "Server Speed: " + lead1.getSpeed() + "\n" +
                            "Server Location: " + lead1.getLocation() + "\n");

                    clientPlatoon = (platoon) clientInput.readObject();
                    System.out.println("Client[" + count + "] has sent : ");
                    System.out.println("Distance: " + clientPlatoon.getDistance() + '\n'
                            + "Signal Strength: " + clientPlatoon.getSignal_strength() + '\n'
                            + "Speed: " + clientPlatoon.getSpeed() + '\n'
                            + "Location: " + clientPlatoon.getLocation().lat
                            + " " + clientPlatoon.getLocation().lng + '\n'
                            + "Object Status " + clientPlatoon.getobject_detection() + "\n"
                            + "Quit: " + clientPlatoon.getQuit());

                    // Object detection,change of command
                    if (clientPlatoon.getobject_detection() == true) {
                        object_detect = check.Object_detection_status(clientPlatoon.getObject_detected_inmtrs(),
                                clientPlatoon.getSpeed());
                    }
                    // call monitor methods
                    // String weather_con = check.Weather_monitoring(lead1.getWeather());
                    String dist_result = check.monitor_distance(clientPlatoon.getDistance());
                    String signal_status = check.monitor_signal_strength(clientPlatoon.getSignal_strength());
                    String speed_status = check.monitor_speed(clientPlatoon.getDistance(),
                            clientPlatoon.getSpeed());
                    output.println("[SERVER] information to Client[" + count + "]   \t " + "Server Speed: "
                            + lead1.getSpeed() + "\t" + weather_con);
                    output.println("[SERVER] instructions to Client[" + count + "]   \t " + dist_result + "   \t"
                            + signal_status + "   \t" + speed_status + "   \t" + object_detect);

                    System.out.println("[SERVER] instructions to Client[" + count + "]\n " + dist_result + "\n"
                            + signal_status + "\n" + speed_status + "\n" + weather_con + "\n" + object_detect);

                    System.out.println("------------------------------- ");
                } catch (ClassNotFoundException e) {

                    e.printStackTrace();
                    // Catch also all other exceptions.
                } catch (Exception e) {
                    // Print what exception has been thrown.
                    System.out.println(e);
                    break;
                }
            }
            // When the client disconnects then the server experiences EOF (End-Of-File).
            catch (EOFException e) {
                System.out.println(e);
                break;
            }
            // any other exceptions
            catch (IOException e) {
                // e.printStackTrace();
                System.out.println(e);
                break;
            }

        }
    }

}