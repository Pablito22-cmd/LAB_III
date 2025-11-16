import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main{
    public static void main(String[] args){
        ConcurrentHashMap<Character,Integer> salva = new ConcurrentHashMap<Character,Integer>();
        BufferedWriter bw = null;

        try {
            Files.deleteIfExists(Paths.get("output.txt"));
            bw = new BufferedWriter(new FileWriter("output.txt",true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread prova1 = new Thread(new Reader("prova1.txt", "output.txt", salva));
        prova1.start();

        Thread prova2 = new Thread(new Reader("prova2.txt", "output.txt", salva));
        prova2.start();

        try{
            prova1.join();
            prova2.join();
        }catch(InterruptedException e){
            e.getStackTrace();
        }

        try{
            for(Map.Entry<Character,Integer> entry : salva.entrySet()){
                bw.write(entry.getKey()); bw.write(": "); bw.write(String.valueOf(entry.getValue())); 
                bw.newLine();
            }
        }catch(IOException e){
                e.getStackTrace();   
        }finally{
            if(bw != null){
                try{
                    bw.close();
                }catch(IOException e){
                    e.getStackTrace();
                }
            }
        }
        
        System.out.println("FINEEEEE");
    }
}