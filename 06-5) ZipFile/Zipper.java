import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipper{
    public static void main(String[] args) throws IOException {
        String source = "test.txt";
        FileInputStream fis = new FileInputStream(source);

        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File("compresso.zip")));

        ZipEntry entry = new ZipEntry(source);

        zos.putNextEntry(entry);

        byte[] data = new byte[1024];
        int length;
        while((length = fis.read(data)) > 0){
            zos.write(data,0,length);
        }

        fis.close();
        zos.close();
    }
}