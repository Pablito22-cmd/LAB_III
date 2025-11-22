import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipOutputStream;
import java.io.File;

public class Main{
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        ZipOutputStream zos = null;

        try{
            zos = new ZipOutputStream(new FileOutputStream("compressed.zip"));
        }catch(FileNotFoundException e){
            e.getStackTrace();
        }

        File dir = new File ("/home/pablito22/Programmazione/git/LAB_III/00-1) ripasso");
        File[] files = dir.listFiles();

        for(File file:files){
            if(file.isFile()){
                System.out.println("Trovato: " + file.getName());
                Zipper run = new Zipper(file, zos);
                service.execute(run);
            }
        }

        System.out.println("File inviati.");
        service.shutdown();

        try{
            if(!service.awaitTermination(20, TimeUnit.SECONDS)){
                service.shutdownNow();
            }
        }catch(InterruptedException e){
            e.getStackTrace();
        }finally{
            System.out.println("Fine processo.");
            service.shutdownNow();
        }

        try{
            zos.close();
        }catch(IOException e){
            e.getStackTrace();
        }

        System.out.println("Conclusione");
    }
}