import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

public class Weblog{
    public static void main(String[] args){
        String inFile = args[0];
        
        try{
            BufferedReader br = new BufferedReader(new FileReader(inFile));
            PrintWriter pw = new PrintWriter(args[1]);
            String line, host;
            
            while((line = br.readLine())!=null){
                String[] parts = line.split("-",2);
                host = parts[0].trim();
                host = InetAddress.getByName(host).getHostName();
                pw.printf("%s - %s\n", host, parts[1]);
            }

            br.close();
            pw.close();
        }catch(FileNotFoundException e){
            System.out.println("File non trovato: " + e.getMessage());
        }catch(IOException e){
            e.getStackTrace();
        }

        System.out.println("Fine.");
    }
}