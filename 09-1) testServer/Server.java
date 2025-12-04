import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

import java.util.Iterator;

public class Server{
    private static boolean running = true;
    private static String exitmsg = "exit";
    public static void main(String[] args){
        try{
            ServerSocketChannel servSock = ServerSocketChannel.open();
            servSock.configureBlocking(false);
            servSock.bind(new InetSocketAddress(8080));
            Selector selector = Selector.open();

            servSock.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("Server listening on 8080");
            try{
                while(running){
                    selector.select();
                    Set<SelectionKey> selKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selKeys.iterator();
                    while(iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        
                        if(key.isAcceptable()){
                            ServerSocketChannel serv = (ServerSocketChannel) key.channel();
                            SocketChannel client = serv.accept();
                            client.configureBlocking(false);

                            client.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                            System.out.println("Connesso...");
                            
                        }else if(key.isReadable()){
                            SocketChannel client = (SocketChannel) key.channel();
                            ByteBuffer input = (ByteBuffer) key.attachment();

                            int byteRead = client.read(input);

                            if(byteRead == -1){
                                System.out.println("Andata male");
                                client.close();
                                continue;
                            }

                            input.flip();

                            byte[] data = new byte[input.remaining()];
                            input.get(data);

                            String arrivato = new String(data);

                            if(arrivato.equals(exitmsg)){
                                System.out.println("Fine del giochino");
                                running = false;
                                break;
                            }

                            String reply = "You wrote: " + arrivato;
                            ByteBuffer output = ByteBuffer.wrap(reply.getBytes());

                            key.interestOps(SelectionKey.OP_WRITE);
                            key.attach(output);

                            System.out.println("Arrivato msg: " + arrivato);
                        }else if(key.isWritable()){
                            SocketChannel client = (SocketChannel) key.channel();
                            ByteBuffer output = (ByteBuffer) key.attachment();

                            client.write(output);

                            System.out.println("Risposta inviata");

                            if(!output.hasRemaining()){
                                key.interestOps(SelectionKey.OP_READ);
                                key.attach(ByteBuffer.allocate(1024));
                            }
                        }

                        iterator.remove();
                    }
                }
            }finally{
                
                    for(SelectionKey key:selector.keys()){
                        key.channel().close();
                    }   

                    selector.close();
                    servSock.close();
                
            }

        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}