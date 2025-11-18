import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyAdress{
    public static void main(String[] args){
        try{
            InetAddress locHost = InetAddress.getLocalHost();
            System.out.println("Local Host: " + locHost);

            InetAddress adress = InetAddress.getByName(args[0]);
            System.out.println("Indirizzo di "+ args[0] + ": " + adress);

            InetAddress[] adresses = InetAddress.getAllByName(args[0]);
            System.out.println("Ecco tutti gli indirizzi di " + args[0]);
            for(int i = 0; i < adresses.length; i++){
                System.out.println(i + "> " + adresses[i]);
            }            

        }catch(UnknownHostException e){
            System.out.println("Eh no! indirizzo brutooooo!");
            e.getStackTrace();
        }
    }
}