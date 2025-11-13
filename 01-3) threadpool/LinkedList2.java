public class LinkedList2{
    public Node head;
    public Node tail;
    private int size;

    public LinkedList2(){
        this.head = this.tail = null;
        this.size = 0;
    }

    public void push(int val){
        Node current = new Node(val);

        if(this.size == 0){
            this.head = this.tail = current;
        }else{
            this.tail.next = current;
            current.prev = this.tail;
            this.tail = current;
        }

        this.size++;
    }

    public void pop(){
        if(this.size == 0) return;

        if(this.size == 1){
            this.head = this.tail = null;
        }else{
            this.tail = this.tail.prev;
            this.tail.next.prev = null;
            this.tail.next = null;
        }

        this.size--; 
    }

    public int size(){return this.size;}
    public Node getHead(){return this.head;}
    public Node getTail(){return this.tail;}

    public void stampa(){
        if(this.size == 0) return;

        System.out.println("Size: " + this.size);
        Node current = this.head;
        while(current != null){
            System.out.println("Val: " + current.val);
            current = current.next;
        }
    }
}