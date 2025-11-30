import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client{
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        try{
            InetAddress host = InetAddress.getLocalHost();
            Socket sock = new Socket(host,2500);
            try{
                BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                PrintWriter out = new PrintWriter(sock.getOutputStream(),true);
                String msg;

                System.out.println("gioco iniziato");

                while(true){
                    msg = in.readLine();
                    System.out.println(msg);

                    String command = scanner.nextLine();
                    out.write(command+"\n");
                    

                    String[] parts = in.readLine().split(",",1);
                    System.out.println(parts[1]);

                    if(!parts[0].equals("PLAYING")){
                        msg = in.readLine();
                        System.out.println(msg);

                        command = scanner.nextLine();
                        out.write(command+"\n");
                        if(command.equals("n")) break;
                    }
                }

                in.close(); out.close(); sock.close();
            }catch(IOException e){
                System.err.println(e.getMessage());
            }

        }catch(UnknownHostException e){
            System.err.println(e.getMessage());
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}