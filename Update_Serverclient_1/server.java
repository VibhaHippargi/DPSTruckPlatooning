import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.lang.Thread;
import java.lang.String;

public class server {

    public static void main(String[] args) throws IOException {

        System.out.println("----------------------------------" + '\n' + "----------------------------------");
        System.out.println(Messages.START.getMessage());
        System.out.println("----------------------------------" + '\n' + "----------------------------------");

        ServerHelper _serverHelper = new ServerHelper();
        _serverHelper.run();

    }

}
