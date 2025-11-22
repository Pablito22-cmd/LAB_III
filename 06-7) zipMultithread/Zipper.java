import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.File;

public class Zipper implements Runnable{
    private File file;
    private ZipOutputStream zos;

    public Zipper(File file, ZipOutputStream zos){
        this.file = file;
        this.zos = zos;
    }

    @Override
    public void run(){
        String fileName = this.file.getName();
        FileInputStream fis = null;

        System.out.println(Thread.currentThread().getName() + " File ricevuto: " + fileName);

        try{
            fis = new FileInputStream(this.file);
            ZipEntry entry = new ZipEntry(fileName);
            this.zos.putNextEntry(entry);

            byte[] data = new byte[1024];
            int length;
            while((length = fis.read(data)) > 0){
                zos.write(data, 0, length);
            }

            System.out.println(Thread.currentThread().getName() + " file: " + fileName + " scritto.");
        }catch(FileNotFoundException e){
            e.getStackTrace();
        }catch(IOException e){
            e.getStackTrace();
        }finally{
            try{
                fis.close();
            }catch(IOException e){
                e.getStackTrace();
            }
        }
    }
}