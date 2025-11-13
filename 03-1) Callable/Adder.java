import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Adder{
    public static void main(String[] args){
        ArrayList<Future<Integer>> list = new ArrayList<Future<Integer>>();
        ExecutorService service = Executors.newFixedThreadPool(11);
        for(int i = 0; i < 11; i++){
            Calcolo c = new Calcolo(i,i+1);
            list.add(service.submit(c));
        }

        for(Future<Integer> i : list){
            try{    
                System.out.println("Elemento: " + i.get());
            }catch(Exception e){
                e.getStackTrace();
            }
        }

        service.shutdownNow();
    }
}