

public class Main{
    public static void main(String[] args){
        Laboratorio lab = new Laboratorio();
        Thread[] cosi = new Thread[20];

        for(int i = 0; i < 20; i++){
            if(i < 10){
                Studente s = new Studente(i, lab);
                cosi[i] = new Thread(s);
            }

            if(i >= 10 && i < 17){
                Tesista t = new Tesista(i-9, (i-9)*2, lab);
                cosi[i] = new Thread(t);
            }
                
            if(i >= 17){
                Professore p = new Professore(i-16, lab);
                cosi[i] = new Thread(p);
            }
        }
        
        for(Thread t:cosi){
            t.start();
        }

        for(Thread t: cosi){
            try{
                t.join();
            }catch(InterruptedException e){
                e.getStackTrace();
            }
        }

        System.out.println("FINE...");
    }
}