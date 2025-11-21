import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class TestData{
    public static void main(String[] args) {
        DataInputStream in = null;
        String inFile = "prova.txt";

        try{
            in = new DataInputStream(new BufferedInputStream(new FileInputStream(inFile)));
            
            System.out.println("byte:    " + in.readByte());
            System.out.println("short:   " + in.readShort());
            System.out.println("int:     " + in.readInt());
            System.out.println("long:    " + in.readLong());
            System.out.println("float:   " + in.readFloat());
            System.out.println("double:  " + in.readDouble());
            System.out.println("boolean: " + in.readBoolean());
        }catch(Exception e){
            e.getStackTrace();
        }finally{
            try{
                in.close();
            }catch(IOException e){
                e.getStackTrace();
            }
        }
    }
}