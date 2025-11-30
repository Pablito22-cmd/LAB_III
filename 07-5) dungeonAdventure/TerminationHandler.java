import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class TerminationHandler extends Thread{
    private int maxDelay;
    private ExecutorService pool;
    private ServerSocket socket;

    public TerminationHandler(int delay, ExecutorService pool, ServerSocket socket){
        this.maxDelay = delay;
        this.pool = pool;
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            this.socket.close();
        }catch(IOException e){
            System.err.println(e.getMessage());
        }

        this.pool.shutdown();

        try{
            if(!this.pool.awaitTermination(this.maxDelay, TimeUnit.SECONDS)){
                this.pool.shutdownNow();
            }
        }catch(InterruptedException e){
            System.err.println(e.getMessage());
            e.getStackTrace();
        }
    }
}