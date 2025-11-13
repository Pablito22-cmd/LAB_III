
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Laboratorio{
    private boolean[] computers = new boolean[20];
    private int profWait;
    private Set<Integer> waitList = new HashSet<>();

    public Laboratorio(){
        for(int i = 0; i < 20; i++){
            this.computers[i] = false;
        }
        this.profWait = 0;
    }

    private boolean isEmpty(){
        for(int i = 0; i < 20; i++){
            if(this.computers[i] == true) return false;
        }
        return true;
    }

    private synchronized void fill(int val){
        for(int i = 0; i < 20; i++){
            this.computers[i] = (val == 1)? true : false;
        }
    }

    private int get(){
        for(int i = 0; i < 20; i++){
            if(!this.computers[i] && !this.waitList.contains(i)){
                this.computers[i] = true;   
                return i;
            }

        }
        return -1;
    }

    public synchronized int access(int i){
        System.out.println("Accesso con: " + i);
        if(i == -1){
            System.out.println("Studente accede ");
            int res = -1;
            while(this.profWait > 0 || (res = this.get()) == -1){
                try{
                    wait();
                }catch(InterruptedException e){
                    e.getStackTrace();
                }                
            }
            return res;
        }else if(i == -20){
            System.out.println("Proff accede");
            this.profWait++;
            while(!this.isEmpty()){
                try{
                    wait();
                }catch(InterruptedException e){
                    e.getStackTrace();
                }
            }
            this.fill(1);
            this.profWait--;
        }else if(i >= 0 && i < 20){
            System.out.println("Tesista accede");
            while(this.profWait > 0 || this.computers[i] == true){
                this.waitList.add(i);
                try{
                    wait();
                }catch(InterruptedException e){
                    e.getStackTrace();
                }    
            }
            this.computers[i] = true;
            this.waitList.remove(i);
        }else{
            System.out.println("Indice invalido! " + i);
        }

        return i;
    }

    public synchronized void leave(int i){
        if(i == -20){
            this.fill(0);
        }else if(i >= 0 && i < 20){
            this.computers[i] = false;
        }else{
            System.out.println("Indice invalido! " + i);
        }

        notifyAll();
    }
}