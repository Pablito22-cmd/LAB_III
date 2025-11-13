import java.util.concurrent.ThreadLocalRandom;

public class Persona implements Runnable{
    private int id;
    private int time;

    public Persona(int id){
        this.id = id;
        this.time = ThreadLocalRandom.current().nextInt(1,10);
    }

    public void sleep(){
        try{
            Thread.sleep(this.time);
        }catch(InterruptedException e){
            System.out.println("Errore: " + e.getMessage());
            e.getStackTrace();
        }
    }

    public int getId(){return this.id;}

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + " Cliente " + this.getId() + " Esegue allo sportello...");
        this.sleep();
        System.out.println(Thread.currentThread().getName() + " Cliente " + this.getId() + " Finito.");
    }
}