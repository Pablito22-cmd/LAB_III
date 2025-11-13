public class Professore implements Runnable{
    private int pid;
    private Laboratorio l;
    
    public Professore(int pid, Laboratorio l){
        this.pid = pid;
        this.l = l;
    }

    @Override
    public void run(){
        System.out.println("Professore: " + this.pid + " si attiva.");
        for(int i = 0; i < 3; i++){
            l.access(-20);
            System.out.println("Professore: " + this.pid + " accede.");
            
            try{
                Thread.sleep(5000);
            }catch(InterruptedException e){
                e.getStackTrace();
            }
            
            l.leave(-20);
            System.out.println("Professore: " + this.pid + " lascia.");
            
            try{
                Thread.sleep(10000);
            }catch(InterruptedException e){
                e.getStackTrace();
            }
            
            System.out.println("Professore: " + this.pid + " si riattiva.");
        }
    }
}