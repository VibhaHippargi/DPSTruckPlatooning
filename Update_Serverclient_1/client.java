import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//import javax.swing.JOptionPane;



public class client {

    private static final String SERVER_IP="127.0.0.1";
    private static final int SERVER_PORT  =9090;


    public static void main(String[] args)throws IOException{

        System.out.println("----------------------------------"+'\n'+"----------------------------------");
       // System.console();
        Socket socket=new Socket(SERVER_IP,SERVER_PORT);
        System.out.println("[CLIENT] Connected to server on port: "+SERVER_PORT +" ip: "+SERVER_IP);
        System.out.println("----------------------------------"+'\n'+"----------------------------------");


    BufferedReader toreadserverresponse=new BufferedReader(new InputStreamReader((socket.getInputStream())));

    BufferedReader keyboard= new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out=new PrintWriter(socket.getOutputStream(),true);


    while(true)
    {
        System.out.println("> ");
        String command=keyboard.readLine();

    
        if(command.equals("quit"))
        {   //out.println(command);
            break;
        }
       out.println(command);
    
  
    String serverresponse=toreadserverresponse.readLine();
    //JOptionPane.showMessageDialog(null,serverresponse);
    System.out.println("[CLIENT] Server sent: "+serverresponse);

    System.out.println("----------------------------------"+'\n'+"----------------------------------");
    }
    socket.close();
    System.exit(0);

    }

}
