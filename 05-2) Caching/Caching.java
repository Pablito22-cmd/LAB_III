import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Security;

public class Caching{
    public static void main(String[] args){
        Security.setProperty("networkaddress.cache.ttl","0");
        long time1 = System.currentTimeMillis();
        try{
            InetAddress address = InetAddress.getByName(args[0]);
            System.out.println("L'indirizzo di " + args[0] + " è: " + address);
        }catch(UnknownHostException e){
            System.out.println("Indirizzo brutto dio ane!");
        }
        long time2 = System.currentTimeMillis();
        System.out.println("Tempo trascorso senza chaching: " + (time2-time1) + " ms");

        for(int i = 0; i < 10; i++){
            time1 = System.currentTimeMillis();
            try{
                InetAddress address = InetAddress.getByName(args[0]);
                System.out.println("L'indirizzo di " + args[0] + " è: " + address);
            }catch(UnknownHostException e){
                System.out.println("Indirizzo brutto dio ane!");
            }
            time2 = System.currentTimeMillis();
            System.out.println("Tempo trascorso con Caching: " + (time2-time1) + " ms");
        }
    }
}