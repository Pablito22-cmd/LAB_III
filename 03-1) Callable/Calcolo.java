import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class Calcolo implements Callable<Integer>{
    private int a;
    private int b;

    public Calcolo(int a, int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " Inizia...");
        Thread.sleep(ThreadLocalRandom.current().nextLong(1,10)*1000);
        int c = this.a + this.b; 
        System.out.println(Thread.currentThread().getName() + " Fine con: " + c);
        return c;
    }
}