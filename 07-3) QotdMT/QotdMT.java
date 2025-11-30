import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QotdMT{
    public static void main(String[] args) {
        int port = 2000;
        ExecutorService service = Executors.newFixedThreadPool(10);
        String[] quotes = {"<Brutto>", "<Tu ma è brutta>", "<ma esplodi>","<Culoh!>","<La vita è un'insegna al neon che brilla da lontano>"};

        try(ServerSocket servSock = new ServerSocket(port)){
            System.out.println("Server Qotd in ascolto sulla porta " + port);
            while(true){
                Socket cliSocket = servSock.accept();
                service.execute(new CliHandler(cliSocket,quotes));
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}