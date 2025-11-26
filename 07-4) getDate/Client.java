import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client{
    public static void main(String[] args) {
        InetAddress host = null;
        try{host = InetAddress.getLocalHost();}catch(UnknownHostException e){System.out.println(e.getMessage());}
        
        int port = 2000;
        try(DatagramSocket socket = new DatagramSocket(0)){
            socket.setSoTimeout(2500);

            System.out.println("Client preparato.");

            DatagramPacket dp = new DatagramPacket(new byte[1], 1, host, port);
            socket.send(dp);

            System.out.println("Client richiesta inviata");
            
            DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
            socket.receive(response);

            System.out.println("Client risposta ricevuta.");

            String msg = new String(response.getData(),0,response.getLength(),"Us-ASCII");
            System.out.println("Data: " + msg);
        }catch(SocketException e){
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}