import java.io.IOException;
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