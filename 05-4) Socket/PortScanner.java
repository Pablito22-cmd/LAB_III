import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class PortScanner{
    public static void main(String[] args){
        String host = args.length > 0 ? args[0] : "localHost";
        try{
            InetAddress address = InetAddress.getByName(host);
            for(int i = 1; i < 1024; i++){
                try{
                    Socket sock = new Socket(address,i);
                    System.out.println("Connessione avvenuta all'host " + host + " sulla porta " + i);
                    sock.close();
                }catch(IOException e){
                    e.getStackTrace();
                }
            }
        }catch(UnknownHostException e){
            System.out.println();
        }

        System.out.println("Fine.");
    }
}