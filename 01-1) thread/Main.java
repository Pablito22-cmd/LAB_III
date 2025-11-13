public class Main{
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++){
            Calculator calc = new Calculator(i);
            Thread thrd = new Thread(calc);
            thrd.start();
        }
        System.out.println("Avviato Calcolo Tabelline");
    }
}