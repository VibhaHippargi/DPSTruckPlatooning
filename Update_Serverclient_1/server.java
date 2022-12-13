package Threading.Code;

import java.net.ServerSocket;
import java.net.Socket;


//import Threading.Code.ServerThread.Disconnectcounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    /* (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
       
        
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            int prev;
           // int prev_dis_value=disconnected_client;

            while(true) {
                String outputString = input.readLine();
                String sent="";
                
                System.out.println("Client["+ count +"] says : " + outputString);
                
                 if(outputString==null)
                 {
                   
                    System.out.println("[SERVER] Disconnecting Client["+ count +"]");
                    disconnected_client+=1;
                    flag=true;
                    System.out.println("[SERVER] current disconnected client count: "+disconnected_client);
                
              
                   System.out.println("----------------------------------"+'\n'+"----------------------------------");
                   break;
                 }
                
             


                switch (outputString)
                {
                        
                    case "object":
                     sent=Objectdetected();
                     output.println(sent);
                    
                     System.out.println("[SERVER] sent data to Client["+ count + "] "+sent);
                     System.out.println("----------------------------------"+'\n'+"----------------------------------");
                        break;

                        case "food":
                     sent=getRandomFood();
                     output.println(sent);
                    
                     System.out.println("[SERVER] sent data to Client["+ count + "] "+sent);
                     System.out.println("----------------------------------"+'\n'+"----------------------------------");
                        break;

                        case "low":
                        sent=signalStrength();
                        output.println(sent);
                       
                        System.out.println("[SERVER] sent data to Client["+ count + "] "+sent);
                        System.out.println("----------------------------------"+'\n'+"----------------------------------");
                           break;

                   default:
                   String when_not_food= "Say food";
                    output.println(when_not_food);
                    System.out.println("[SERVER] sent data to Client["+ count + "] "+when_not_food);
                    System.out.println("----------------------------------"+'\n'+"----------------------------------");
                    //output.println("");
                        break;
                }
               



                /*if(outputString==null)
                 {
                   break;
                 }

                 else if(outputString.contains("object"))
                 {
                    String sent=Objectdetected();
                    output.println(sent);
                  //System.out.println("[SERVER] Server received from client :"+outputString);
                  System.out.println("[SERVER] sent data to Client["+ count + "] "+sent);
                  System.out.println("----------------------------------"+'\n'+"----------------------------------");
                 }


                else if(outputString.contains("food"))
                {
                    //output.println("Hello");
                    String sent=getRandomFood();
                   output.println(sent);
                  //System.out.println("[SERVER] Server received from client :"+outputString);
                  System.out.println("[SERVER] sent data to Client["+ count + "] "+sent);
                  System.out.println("----------------------------------"+'\n'+"----------------------------------");

                }
                else {
                  //  System.out.println("[SERVER] Server received from client "+outputString);
                    String when_not_food= "Say food";
                    output.println(when_not_food);
                    System.out.println("[SERVER] sent data to Client:"+when_not_food);
                    System.out.println("----------------------------------"+'\n'+"----------------------------------");
                    //output.println("");
                }*/
            }
            prev=disconnected_client;
        } 
       
        catch (IOException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
        };
         } 

       // finally{
    
           

   // }


    private void println(String string) {
    }

    private String getRandomFood()
    {
        String[] food ={"Frankie","Pasta","Noodles","Rice"};
        String[] adj ={"Tomato","Creamy","Spicy","Hot"};
       String name = food[(int) (Math.random()*food.length)];
       String adjective = adj[(int) (Math.random()*adj.length)];
       return adjective+" "+ name;
   }

   private String Objectdetected()
   {
      
      String server_command = "DECELERATE";
      return server_command;
      
  }

  private String signalStrength()
  {
     
     String server_command = "ACCELERATE";
     return server_command;
     
 }

 

    /*private Socket socket;
    private int count;
    public int disconnect_counter;*/
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
