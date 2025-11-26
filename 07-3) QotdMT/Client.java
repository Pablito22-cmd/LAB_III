import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client{
    public static void main(String[] args) {
        int port = 2000;
        InetAddress host = null;
        try{
            host = InetAddress.getLocalHost();
        }catch(UnknownHostException e){System.out.println(e.getLocalizedMessage());}

        try(Socket sock = new Socket(host, port)){
            System.out.println("connesso");
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String msg = in.readLine();
            System.out.println(msg);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Finito");
    }
}