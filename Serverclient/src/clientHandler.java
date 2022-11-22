import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/*public class clientHandler implements Runnable{

private Socket client;
private BufferedReader in;
private PrintWriter out;

public clientHandler (Socket clientSocket) throws IOException
{
    this.client=clientSocket;
    in=new BufferedReader(new InputStreamReader(client.getInputStream()));
    out=new PrintWriter(client.getOutputStream());
}

//@override
public void run()
{
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
        
         } catch (IOException e){

            System.err.println("IO Exception in client handler");
            System.err.println(e.getStackTrace());
         }
        
  finally{
            
        out.close();
        
            in.close();
        
        
            client.close();
            listener.close();
            System.out.println("[SERVER] Closing!!");
        
            System.out.println("----------------------------------"+'\n'+"----------------------------------");
        
        }
}

}*/