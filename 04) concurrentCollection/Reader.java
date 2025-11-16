import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Reader implements Runnable{
    String inputFile;
    String outputFile;
    Map<Character,Integer> map;
    public Reader(String input, String output, Map<Character,Integer> map){
        this.inputFile = input;
        this.outputFile=output;
        this.map = map;
    }

    @Override
    public void run(){
        BufferedReader br = null;
        String line;

        try{
            br = new BufferedReader(new FileReader(this.inputFile));

            while((line = br.readLine()) != null){
                for(char c:line.toLowerCase().toCharArray()){
                    if(Character.isLetter(c)) this.map.put(c, this.map.getOrDefault(c,0)+1);
                }
            }
        }catch(IOException e){
            e.getStackTrace();
        }finally{
            if(br != null){
                try{
                    br.close();
                }catch(IOException e){
                    e.getStackTrace();
                }
            }
        }
    }
}