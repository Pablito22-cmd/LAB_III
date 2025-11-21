import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFile{
    public static void main(String[] args){
        String inFile = "Caballo.jpg", outFile = "DoppleGanger.jpg";
        InputStream in; OutputStream out;
        
        try{
            in = new FileInputStream(inFile);
            out = new FileOutputStream(outFile);
            byte[] byteRead = new byte[100];

            while(in.read(byteRead) != -1){
                out.write(byteRead);
            }

            in.close();
            out.close();
        }catch(IOException e){
            e.getStackTrace();
        }
    }
}