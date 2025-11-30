import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server{
    private static int port = 2500;
    private static ServerSocket servSock = null;
    private static ExecutorService pool = null;


    public static void main(String[] args) {
        try{
            servSock = new ServerSocket(port);
            pool = Executors.newFixedThreadPool(10);

            Runtime.getRuntime().addShutdownHook(new TerminationHandler(60, pool, servSock));

            while(true){
                Socket cliSock = servSock.accept();
                pool.execute(new Worker(cliSock));
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}