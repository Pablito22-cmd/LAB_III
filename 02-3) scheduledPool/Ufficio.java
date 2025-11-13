import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Ufficio{
    private static void uff(ArrayBlockingQueue<Persona> pers, ExecutorService ufficio){
        for(int i = 0; i < 20; i++){
            Persona current = pers.peek();
            try{
                ufficio.execute(current);
                pers.poll();
            }catch(RejectedExecutionException e){
                System.out.println("Ufficio pieno...");
                try{
                    Thread.sleep(5000);
                }catch(InterruptedException ex){
                    ex.getStackTrace();
                }
                i--;
            }
        }

        System.out.println("Inizio shutdown...");
        ufficio.shutdown();

        try{
            if(!ufficio.awaitTermination(10,TimeUnit.SECONDS)){
                ufficio.shutdownNow();
            }
        }catch(InterruptedException e){
            e.getStackTrace();
        }
    }

    public static void main(String[] args){
        ArrayBlockingQueue<Persona> pers = new ArrayBlockingQueue<Persona>(20);
        for(int i = 0; i < 20; i++){
            pers.add(new Persona(i));
        }

        ExecutorService ufficio =   new ThreadPoolExecutor(4, 4, 30, TimeUnit.SECONDS, 
                                    new ArrayBlockingQueue<Runnable>(Integer.parseInt(args[0])), 
                                    new ThreadPoolExecutor.AbortPolicy());
        
        uff(pers,ufficio);
    }
}