
public class Calculator extends Thread{
    private final int number;

    public Calculator(int number){
        super();
        this.number = number;
    }

    @Override
    public void run(){
        for(int i = 1; i <=10; i++){
            System.out.printf("%s: %d x %d = %d\n", Thread.currentThread(), this.number, i, i*this.number);
        }
    }
}