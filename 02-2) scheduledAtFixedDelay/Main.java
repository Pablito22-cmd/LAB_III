import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main{
    public static void prova(){
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        long tstart = System.currentTimeMillis();
        RandomTask ru = new RandomTask(tstart);

        System.out.println("Inizio Servizio: 0");
        service.scheduleAtFixedRate(ru, 2, 6, TimeUnit.SECONDS);

        try{
            if(!service.awaitTermination(60, TimeUnit.SECONDS)){
                service.shutdownNow();
            }
        }catch (InterruptedException e){
            service.shutdownNow();
            System.out.println("Errore: " + e.getMessage());
            e.printStackTrace();
        }

        long tend = (System.currentTimeMillis() - tstart )/1000;
        System.out.println("Fine Servizio: " + tend);
        System.out.println("Shutdown...");
    }

    public static void main(String[] args){
        prova();
    }
}