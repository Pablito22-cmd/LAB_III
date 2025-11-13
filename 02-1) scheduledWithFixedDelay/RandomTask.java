import java.util.concurrent.ThreadLocalRandom;

public class RandomTask implements Runnable{
    private long tstart;
    public RandomTask(long tstart){this.tstart = tstart;}

    private long time(){
        return (System.currentTimeMillis()-this.tstart)/1000;
    }

    @Override
    public void run(){
        int delay = ThreadLocalRandom.current().nextInt(1,10);

        try{
            System.out.println(Thread.currentThread().getName() + " Inizio task: " + this.time());
            Thread.sleep(1000*delay);
        }catch(InterruptedException e){
            System.out.println(Thread.currentThread().getName() + " MORTO!!!!!");
            System.out.println("Errore: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " Fine task: " + this.time());
    }
}