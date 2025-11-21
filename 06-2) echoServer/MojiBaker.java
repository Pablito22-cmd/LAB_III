import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class MojiBaker{
    public static void main(String[] args){
        String host = "tcpbin.com";
        int port = 4242;

        BufferedReader br = null;
        BufferedWriter bw = null;
        Socket sock = null;

        try{
            sock = new Socket(host,port);
            System.out.println("Connessione con " + host + " " + port + " riuscita!");

            bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream(), StandardCharsets.ISO_8859_1));
            br = new BufferedReader(new InputStreamReader(sock.getInputStream(), StandardCharsets.UTF_8));

            String message = "Buongiorno! ☕�  Caffè e fiori rendono la giornata migliore.\n";
            bw.write(message);
            System.out.println("Inviato: " + message);

            String response = br.readLine();
            System.out.println("Ricevuto: " + response);
        }catch(UnknownHostException e){
            System.out.println("Connessione andata male.");
        }catch(IOException e){
            e.getStackTrace();
        }finally{
            try{
                bw.close();
                br.close();
                sock.close();
            }catch(IOException e){
                e.getStackTrace();
            }
        }
    }
}