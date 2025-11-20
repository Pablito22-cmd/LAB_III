import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main{
    public static void main(String[] args){
        ExecutorService service = Executors.newCachedThreadPool();
        File output = new File(args[args.length-1]);

        for(int i = 0; i < args.length-1; i++){
            Weblog readWrite = new Weblog(args[i],output);
            service.execute(readWrite);
        }

        service.shutdown();

        try{
            if(!service.awaitTermination(30, TimeUnit.SECONDS)){
                service.shutdownNow();
            }
        }catch(InterruptedException e){
            e.getStackTrace();
            service.shutdownNow();
        }

        output.close();

        System.out.println("fin.");
    }
}