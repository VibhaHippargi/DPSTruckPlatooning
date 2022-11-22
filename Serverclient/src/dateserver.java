import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
//import java.sql.Date;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class dateserver {

    private static String[] food ={"Frankie","Pasta","Noodles","Rice"};
    private static String[] adj ={"Tomato","Creamy","Spicy","Hot"};

    private static final int PORT  =9090;

    //private ArrayList <clientHandler> clients=new ArrayList<>();

    public static void main(String[] args)  throws IOException{

       
        ServerSocket listener=new ServerSocket(PORT);
        

        System.out.println("----------------------------------"+'\n'+"----------------------------------");

        System.out.println("[SERVER] Server is waiting for Client connection...");

        System.out.println("----------------------------------"+'\n'+"----------------------------------");

        Socket client=listener.accept();
        System.out.println("[SERVER] Connected to Client");

        System.out.println("----------------------------------"+'\n'+"----------------------------------");

        //sending date
        PrintWriter out=new PrintWriter(client.getOutputStream(),true);
       // String date=(new Date()).toString();
       // System.out.println("[SERVER] sending date "+date);
      //  out.println(date);

      //out.println(getRandomFood())

      BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));
//boolean flag=false;
 try{    
while(true)
{
    String request=in.readLine();
if(request==null)
{
    break;
}

    else if(request.contains("food"))
        {
          String sent=getRandomFood();
           out.println(sent);
            System.out.println("[SERVER] Server received from client :"+request);
            System.out.println("[SERVER] sent data to Client:"+sent);
            System.out.println("----------------------------------"+'\n'+"----------------------------------");
            
        }


        else 
          {
            System.out.println("[SERVER] Server received from client :"+request);
           out.println("Say food");
           System.out.println("----------------------------------");
           
          }

         
        }

 }
 
 finally{
    
out.close();

    in.close();


    client.close();
    listener.close();
    System.out.println("[SERVER] Closing!!");

    System.out.println("----------------------------------"+'\n'+"----------------------------------");

}


     


    
        /* *System.out.println("[SERVER] sent data to Client. Closing!!");

        System.out.println("----------------------------------"+'\n'+"----------------------------------");

        client.close();
        listener.close();*/
    
}

    public static String getRandomFood() {
        String name = food[(int) (Math.random()*food.length)];
        String adjective = adj[(int) (Math.random()*adj.length)];
        return adjective+" "+ name;

    }
}
