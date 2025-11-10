public class Task implements Runnable{
    private LinkedList2 lista;
    public Task(){
        this.lista = new LinkedList2();
        this.lista.push(2);
    }

    @Override
    public void run(){
        for(int i = 3; i < 1000000; i++){
            System.out.println(Thread.currentThread() + " Analizzo: " + i);
            Node current = this.lista.getHead();
            boolean isPrime = true;
            while(current != null){
                if(i % current.val == 0){
                    isPrime = false;
                    break;
                }
                current = current.next;
            }
            if(isPrime){ 
                this.lista.push(i);
            }
        }
    }
}