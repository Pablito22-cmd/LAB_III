import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.File;

public class MultiZipper{
    public static void main(String[] args) {
        File dir = new File("/home/pablito22/Programmazione/git/LAB_III/00-1) ripasso");
        ZipOutputStream zos = null;

        try{
            zos = new ZipOutputStream(new FileOutputStream("compressed.zip"));

            File[] files = dir.listFiles();
            for(File file:files){
                if(file.isDirectory()) continue;

                insert(zos, file);
            }
        }catch(FileNotFoundException e){
            e.getStackTrace();
        }finally{
            try{
                zos.close();
            }catch(IOException e){e.getStackTrace();}
        }
    }

    public static void insert(ZipOutputStream zos, File file){
        FileInputStream fis = null;    
        try{
            fis = new FileInputStream(file);
            ZipEntry entry = new ZipEntry(file.getName());
            zos.putNextEntry(entry);
            
            byte[] data = new byte[1024];
            int length;
            while((length = fis.read(data)) > 0){
                zos.write(data,0,length);
            }
        }catch(FileNotFoundException e){
            e.getStackTrace();
        }catch(IOException e){
            e.getStackTrace();
        }finally{
            try{
                fis.close();
            }catch(IOException e){e.getStackTrace();}
        }
    }
}