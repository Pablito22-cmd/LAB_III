import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class CliHandler implements Runnable{
    private Socket cliSock;
    private String[] quotes;

    public CliHandler(Socket cliSock, String[] quotes){
        this.cliSock = cliSock;
        this.quotes = quotes;
    }

    @Override
    public void run(){
        try{
            System.out.println(Thread.currentThread().getName() + " Connesso.");
            PrintWriter out = new PrintWriter(this.cliSock.getOutputStream());
                    
            String quote = this.quotes[new Random().nextInt(quotes.length)];
            out.write(quote + "\n");
            out.flush();

            System.out.println(Thread.currentThread().getName() + " Inviato: " + quote);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                cliSock.close();
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
        
        System.out.println(Thread.currentThread().getName() + " Fine task.");
    }
}