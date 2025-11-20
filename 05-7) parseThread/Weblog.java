import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;

public class Weblog implements Runnable{
    String inFile;
    File outFile;

    public Weblog(String file1, File file2){
        this.inFile = file1;
        this.outFile = file2;
    }

    @Override
    public void run(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(this.inFile));
            String line;
            while((line = br.readLine())!=null){
                String[] parts = line.split("-", 2);
                String host = InetAddress.getByName(parts[0].trim()).getHostName();
                String toWrite = host + " - " + parts[1] + "\n";

                System.out.println(Thread.currentThread().getName() + " prova a scrivere: " + toWrite);
                this.outFile.write(toWrite);
            }

            br.close();
        }catch(IOException e){
            e.getStackTrace();
        }

        System.out.println("Thread " + Thread.currentThread().getName() + " completato.");
    }
}