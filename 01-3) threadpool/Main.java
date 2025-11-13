import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main{
    public static void main (String[] args){
        int thrdNum = Integer.parseInt(args[0]);
        ExecutorService service = Executors.newCachedThreadPool();

        long tstart = System.currentTimeMillis();

        for(int i = 0; i < thrdNum; i++){
            Task primo = new Task();
            service.execute(primo);
        }

        service.shutdown();

        try{
            if(!service.awaitTermination(60, TimeUnit.SECONDS)){
                service.shutdownNow();
            }
        }catch(InterruptedException e){
            System.out.println("Errore: " + e.getMessage());
            service.shutdownNow();
        }

        long tend = System.currentTimeMillis();
        System.out.println("Tempo trascorso: " + (tend - tstart));
    }
}