import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Streamer{
    public static void main(String[] args){
        String host = "djxmmx.net";
        int port = 17;
        Socket sock = null;
        try{
            sock = new Socket(host,port);
            BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
            
            System.out.println("Fine citazione.");
            sock.close();
        }catch(UnknownHostException e){
            System.out.println("Problema con l'host");
        }catch(IOException e){
            e.getStackTrace();
        }
    }
}