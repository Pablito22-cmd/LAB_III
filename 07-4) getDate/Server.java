import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Date;

public class Server{
    public static void main(String[] args) {
        int port = 2000;
        try(DatagramSocket socket = new DatagramSocket(port)){
            System.out.println("Servere in attesa.");
            DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
            socket.receive(request);

            System.out.println("Servere ricevuto richiesta.");

            String daytime = new Date().toString();
            byte[] data = daytime.getBytes("Us-ASCII");

            DatagramPacket response = new DatagramPacket(data, data.length, request.getSocketAddress());

            socket.send(response);

            System.out.println("Servere inviato risposta.");
        }catch(SocketException e){
            System.out.println(e.getMessage());
        }catch(UnsupportedEncodingException e){
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}