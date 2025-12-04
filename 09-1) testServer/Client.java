import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client{
    public static void main(String[] args) {
        try{
            SocketChannel sock = SocketChannel.open();
            sock.connect(new InetSocketAddress("localhost",8080));

            String msgS = args[0] == null ? "hello babe" : args[0];
            ByteBuffer msg = ByteBuffer.wrap(msgS.getBytes());

            sock.write(msg);
            System.out.println("Inviato" + msgS);

            ByteBuffer received = ByteBuffer.allocate(1024);
            int brec = sock.read(received);

            if(brec > 0){
                received.flip();
                byte[] data = new byte[received.remaining()];
                received.get(data);

                System.out.println("Ricevuto: " + new String(data));
            }else{
                System.out.println("nada");
            }

            sock.close();
        }catch(IOException e){
            System.err.println(e.getMessage());
        }

    }
}