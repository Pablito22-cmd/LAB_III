import java.net.InetAddress;
import java.net.UnknownHostException;

public class ByteAdress{
    public static boolean isSpam(InetAddress address){
        try{
            String query = "";
            byte[] ad = address.getAddress();
            
            for(byte b:ad){
                int unsign = b < 0 ? b+256 : b;
                query = unsign + "." + query;
            }
            query = query + ".sbl.spamhaus.org";

            InetAddress.getByName(query);
            return true;
        }catch(UnknownHostException e){
            return false;
        }
    }
    public static void main(String[] args){
        try{
            InetAddress ipAd = InetAddress.getByName(args[0]);
            System.out.println("Indirizzo di " + args[0] + ": " + ipAd);

            byte[] address = ipAd.getAddress();
            System.out.println("Indirizzo in byte: ");
            for(byte ipByte:address){
                System.out.println("> " + (ipByte & 0xFF));
            }

            if(isSpam(ipAd)) System.out.println("Ed è pure spam.");
        }catch(UnknownHostException e){
            System.out.println("Indirizzo no!!!!");
        }

        System.out.println("The End.");
    }
}