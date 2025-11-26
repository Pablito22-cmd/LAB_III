import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.UnknownHostException;


public class Client{
    public static void main(String[] args) {
        Socket sock = null;
        String msg = "ciao!\n";
        try{
            InetAddress server = InetAddress.getLocalHost();
            int port = 2500;
            sock = new Socket(server,port);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));

            out.write(msg);
            out.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String receive = in.readLine();
            System.out.println(receive);

            sock.close();
        }catch(UnknownHostException e){
            e.getStackTrace();
            System.out.println("Porco");
        }catch(IOException e){
            e.getStackTrace();
            System.out.println("Dio");
        }
    }
}