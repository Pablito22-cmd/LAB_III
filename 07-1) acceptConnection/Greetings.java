import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Greetings{
    public static void main(String[] args) {
        ServerSocket socket = null;
        
        try{
            socket = new ServerSocket(2500);

            System.out.println("Socket Server inizializzata!\n");

            Socket clientSock = socket.accept();

            System.out.println("Socket Server, Connessione Avvenuta!");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSock.getOutputStream()));
            String msg = in.readLine();
            System.out.println(msg);
            out.write("Ciao anche a te!");
            out.flush();

            

            clientSock.close();
            socket.close();

            System.out.println("Socket Server Conclusione!");
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}