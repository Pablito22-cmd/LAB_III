import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class File{
    private BufferedWriter br = null;
    
    public File(String file){
        try{
            this.br = new BufferedWriter(new FileWriter(file));
        }catch(IOException e){
            e.getStackTrace();
        }
    }

    public synchronized void write(String line){
        System.out.println(Thread.currentThread().getName() + " Scrive: " + line);
        
        try{
            br.write(line);
        }catch(IOException e){
            e.getStackTrace();
        }
    }

    public void close(){
        try{
            this.br.close();
        }catch(IOException e){
            e.getStackTrace();
        }
    }
}