import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

//import javax.swing.JOptionPane;

/*1.distance -double
 * 2.speed -int
 * 3.signal strength -int
 * 4.location -string
 * 5.weather/road condition - string
 */

public class client {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 9090;
    private static int singal_strength_std = 20;
    private Random random;

    client() {
        random = new Random();
    }

    public double getRandomDist() {
        /**
         * returns the random
         */
        double[] distancesArray = { 10.0, 35.0, 40.0, 20.0, 50.0 };
        double distance = distancesArray[(int) (Math.random() * distancesArray.length)];

        return distance;
    }

    public int getRandomSpeed() {
        /**
         * returns the random
         */
        int[] speedArray = { 20, 30, 40, 50, 60, 70, 80, 90, 120 };
        int speed = speedArray[(int) (Math.random() * speedArray.length)];

        return speed;
    }

    public int getRandomSignal() {
        /**
         * returns the random
         */
        int[] signalArray = { 30, 40, 50, 60, 70, 80, 90, 100 };
        int signal = signalArray[(int) (Math.random() * signalArray.length)];

        return signal;
    }

    public int getRandomObjectDistance() {
        /**
         * This function is to generate random distance of object from given Platoon
         * 
         * @return
         */
        int[] ObjectDistanceArray = { 5, 10, 15, 20 };
        int signal = ObjectDistanceArray[(int) (Math.random() * ObjectDistanceArray.length)];
        return signal;
    }

    public Location getRandomLocation() {
        /**
         * range from -90 to 90 for latitude and -180 to 180 for longitude.
         * returns the random
         */
        int maxLat = 90;
        int minLat = -90;
        int maxLng = 180;
        int minLng = -180;

        double randomLat = random.nextInt(maxLat + 1 - minLat) + minLat;
        double randomLng = random.nextInt(maxLng + 1 - minLng) + minLng;
        Location randomLocation = new Location(randomLat, randomLng);
        return randomLocation;
    }

    // public String getRandomWeather() {
    // /**
    // * returns the random
    // */
    // String[] weatherArray = { "Rainy", "Normal", "Road_Slippery", "Snowing" };
    // String weather = weatherArray[(int) (Math.random() * weatherArray.length)];

    // return weather;
    // }

    public int monitor_object_distance(int Objdistance) {
        if (Objdistance <= 20 && Objdistance > 15) {
            return 0;
        }
        if (Objdistance <= 15 && Objdistance < 10) {
            return 1;
        }
        if (Objdistance <= 10 && Objdistance > 5) {
            return 2;
        }
        if (Objdistance < 5 && Objdistance >= 1) {
            return 3;
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        double dist = 0;
        int signal = 0;
        int speed = 0;
        Location location;
        Location location_1 = new Location(0, 0);
        int objectDistance = 0;
        int obj_Det = 0;
        System.out.println("----------------------------------" + '\n' + "----------------------------------");
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);
        System.out.println("[CLIENT] Connected to server on port: " + SERVER_PORT + " ip: " + SERVER_IP);
        System.out.println("----------------------------------" + '\n' + "----------------------------------");
        int flag_1 = 0;
        client clientobj = new client();
        parking_space park_assit = new parking_space();
        Location park_spot = new Location(0, 0);
        BufferedReader toreadserverresponse = new BufferedReader(new InputStreamReader((socket.getInputStream())));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println(">Hit Enter to send data to Server-- ");
            String command = keyboard.readLine();
            platoon p = new platoon();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            if (command.equalsIgnoreCase("Q")) {
                System.out.println("Incorrect Input");
                p.setQuit(false);
            } else {
                if (flag_1 == 0) {
                    dist = clientobj.getRandomDist();
                    signal = clientobj.getRandomSignal();
                    speed = clientobj.getRandomSpeed();
                    objectDistance = clientobj.getRandomObjectDistance();
                    location = clientobj.getRandomLocation();
                    location_1 = location;
                    obj_Det = clientobj.monitor_object_distance(objectDistance);
                    flag_1 = 1;
                } else {
                    dist = dist + 10;
                    signal = signal + 10;
                    speed = speed + 5;
                    location = location_1;
                    objectDistance = clientobj.getRandomObjectDistance();
                    obj_Det = clientobj.monitor_object_distance(objectDistance);
                }
                if (obj_Det < 1) {
                    p.setobject_detection(false);
                }
                if (obj_Det >= 1) {
                    p.setobject_detection(true);
                    p.setObject_detected_inmtrs(objectDistance);
                }
                p.setDistance(dist);
                p.setSpeed(speed);
                p.setSignal_strength(signal);
                p.setLocation(location);
                p.setQuit(false);
            }
            if (p.getSignal_strength() < singal_strength_std) {
                park_spot = park_assit.Parking_assitance(p.getLocation());
                System.out.println("Client parked due to communication loss parking spot is latitude " + park_spot.lat
                        + " Longitude is " + park_spot.lng + "\n");
                p.setQuit(true);
            } else {
                System.out.println("Sending details to Server....");
                System.out.println("Distance: " + p.getDistance() + '\n'
                        + "Signal Strength: " + p.getSignal_strength() + '\n'
                        + "Speed: " + p.getSpeed() + '\n'
                        + "Location: " + p.getLocation().lat + " " + p.getLocation().lng + '\n'
                        + "Object Distance: " + p.getObject_detected_inmtrs() + '\n'
                        + "Quit: " + p.getQuit());
                System.out.println("------------------------------- ");

                objectOutputStream.writeObject(p); // sending data to server
                String serverresponse_1 = toreadserverresponse.readLine();
                System.out.println("[CLIENT] Server sent: " + '\n' + serverresponse_1);
                System.out.println("------------------------------- ");
                System.out.println("Waiting for Server response... ");
                String serverresponse = toreadserverresponse.readLine();
                System.out.println("[CLIENT] Server sent: " + '\n' + serverresponse);
            }

            System.out.println("----------------------------------" + '\n' + "----------------------------------");
            if (p.getQuit()) {
                break;
            }

        }
        socket.close();
        System.exit(0);
    }

}
