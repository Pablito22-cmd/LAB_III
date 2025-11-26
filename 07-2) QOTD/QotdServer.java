import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class QotdServer{
    private static int port = 2000;
    
    public static void main(String[] args) {
        String[] quotes = {"no", "o la va o la spacca", "portorio"};
        System.out.println("Server avviato.");
        for(int i = 0; i < 3; i++){
            try(ServerSocket servSock = new ServerSocket(port)){
                System.out.println("Server in attesa.");
                Socket clientSocket = servSock.accept();

                System.out.println("Server connesso.");
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                String quote = quotes[new Random().nextInt(quotes.length)];

                out.write(quote+"\n");
                out.flush();
                
                System.out.println("Inviato: " + quote);
            }catch(IOException e){
                e.getStackTrace();
                System.out.println(e.getLocalizedMessage());
            }
        }
        System.out.println("Server terminato.");
    }
}