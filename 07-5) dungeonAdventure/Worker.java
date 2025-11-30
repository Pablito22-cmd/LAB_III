import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Worker implements Runnable{
    private Socket socket;
    private Status status = Status.PLAYING;
    private int PlayerHealth = 100;
    private int MonsterHealth = 500;
    private int Potion = 50;

    public Worker(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(),true);

            System.out.println("Partita attivata");
            while(true){
                this.game(in,out); 

                out.write("Would you like to play again? (y/n)\n");

                String msg = in.readLine().toLowerCase();
                if(msg.equals("n")) break;

                this.reset();
            }

            in.close(); out.close(); this.socket.close();
        }catch(IOException e){
            System.err.println(e.getMessage());
        }finally{
            try{
                in.close(); out.close(); this.socket.close();
            }catch(IOException e){
                System.err.println(e.getMessage());
            }
        }
    }

    private enum Status{
        PLAYING,
        INTERRUPTED,
        WIN,
        LOSE,
        DRAW
    }

    private void game(BufferedReader in, PrintWriter out){
        try{
            while(this.status == Status.PLAYING){
                out.write("Make your move: attack - drink - leave\n"); 
                out.flush();

                String msg = in.readLine();
                System.out.println(msg);

                switch (msg) {
                    case "attack":
                        this.fight();
                        break;
                
                    case "drink":
                        this.drink();
                        break;

                    case "leave":
                        this.status = Status.INTERRUPTED;
                        break;
                        
                    default:
                        break;
                }

                out.write(this.status.toString() + ", Health: " + this.PlayerHealth + " Monster: " + this.MonsterHealth + " potion: " + this.Potion + "\n");
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    private void reset(){
        this.status = Status.PLAYING;
        this.MonsterHealth = 500;
        this.PlayerHealth = 100;
        this.Potion = 50;
    }

    private void drink(){
        int pot = (int) Math.random()*this.Potion;
            this.PlayerHealth += pot;
            this.Potion -= pot;
    }

    private void fight(){
        int plAttack = (int) Math.random()*100;
        int mAttack = (int) Math.random()*50;
        this.MonsterHealth -= plAttack;
        this.PlayerHealth -= mAttack;

        if(this.PlayerHealth <= 0){
            if(this.MonsterHealth <= 0){
                this.status = Status.DRAW;
            }else{
                this.status = Status.LOSE;
            }
        }
    }
}