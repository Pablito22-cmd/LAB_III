public class Tesista implements Runnable{
    private int tid;
    private int pc;
    private Laboratorio l;

    public Tesista(int id, int pc, Laboratorio l){
        this.tid = id;
        this.pc = pc;
        this.l = l;
    }
    
    @Override
    public void run(){
        System.out.println("Tesista: " + this.tid + " si attiva.");
        for(int i = 0; i < 5; i++){
            this.l.access(this.pc);
            System.out.println("Tesista: " + this.tid + " accede...");
           
            try{
                Thread.sleep(3000);
            }catch(InterruptedException e){
                e.getStackTrace();
            }
           
            this.l.leave(this.pc);
            System.out.println("Tesista: " + this.tid + " lascia.");
           
            try{
                Thread.sleep(2000);
            }catch(InterruptedException e){
                e.getStackTrace();
            }
           
            System.out.println("Tesista: " + this.tid + " si riattiva.");
        }
    }
}