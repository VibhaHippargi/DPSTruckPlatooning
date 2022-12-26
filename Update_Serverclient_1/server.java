import java.net.ServerSocket;
import java.net.Socket;

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
        this.count = count+1;
        
    }

    @Override
    public void run() {
       
        while(true)
        {

        
        try {
            // BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ObjectInputStream clientInput = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream clientOuput = new ObjectOutputStream(socket.getOutputStream());
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
           
            platoon clientPlatoon;
            
            try {
                clientPlatoon = (platoon) clientInput.readObject();
                System.out.println("Client["+ count +"] has sent : ");
                //System.out.println("Distance is " + clientPlatoon.getDistance());

                System.out.println("Distance: " + clientPlatoon.getDistance()+'\n'
                 +"Signal Strength: " + clientPlatoon.getSignal_strength() +'\n'
                 + "Speed: " +clientPlatoon.getSpeed()+'\n'
                 +"Location: "+ clientPlatoon.getLocation().lat
                 +" " + clientPlatoon.getLocation().lng +'\n'
                 +"Weather: " + clientPlatoon.getWeather());

                  System.out.println("------------------------------- ");

            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // System.out.println(clientInput.);
        } 
       
        catch (IOException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
        };
         } 
        }

       // finally{
    
           

   // }
   
 
}

class ServerHelper {
    //private int disconnect_client;

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
        while(true) {

            try {
                Socket socket = _server.accept();
             
                ServerThread serverThread = new ServerThread(socket, _clientCount);
                _clientCount=_clientCount+1;
                System.out.println("**********************************"+'\n'+"     New connection Alert"+'\n'+"**********************************");
                System.out.println("Number of client connected: "+_clientCount );
                System.out.println("[SERVER] Active threads:"+ ServerThread.activeCount());
                System.out.println("----------------------------------"+'\n'+"----------------------------------");
                //ServerThread serverThread = new ServerThread(socket, _clientCount);
                // _threadList.add(serverThread);
                serverThread.start();

                if((disconnect_client==0) && (flag==true))
                {
                    socket.close();
                    _server.close();
                }
               

                
            } catch (IOException e)
            
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            
        }
        
    }

    ServerSocket _server;
    int _clientCount;
    //int disconnect_client;
    // private ArrayList<ServerThread> _threadList;
   
    private static final int PORT = 9090;

  
}

public class server {

    public static void main(String[] args) throws IOException {
        
        System.out.println("----------------------------------"+'\n'+"----------------------------------");
        System.out.println("Server Started");
        System.out.println("----------------------------------"+'\n'+"----------------------------------");
        ServerHelper _serverHelper = new ServerHelper();
        _serverHelper.run();
       
      
    }

   
}
