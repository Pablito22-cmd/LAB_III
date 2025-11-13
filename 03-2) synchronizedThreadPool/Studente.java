public class Studente implements Runnable{
    private int sid;
    private Laboratorio l;

    public Studente(int id, Laboratorio l){
        this.sid = id;
        this.l = l;
    }

    @Override
    public void run(){
        System.out.println("Studente: " + this.sid + " si attiva.");
        int aux = -1;
        for(int i = 0; i < 6; i++){
            aux = this.l.access(-1);
            System.out.println("Studente: " + this.sid + " accede...");
           
            try{
                Thread.sleep(2000);
            }catch(InterruptedException e){
                e.getStackTrace();
            }
           
            this.l.leave(aux);
            System.out.println("Studente: " + this.sid + " lascia.");
           
            try{
                Thread.sleep(2000);
            }catch(InterruptedException e){
                e.getStackTrace();
            }
           
            System.out.println("Studente: " + this.sid + " si riattiva.");
        }
    }
}