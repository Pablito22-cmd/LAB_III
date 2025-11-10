public class Node{
        public int val;
        public Node next;
        public Node prev;

        public Node(int val){
            this.val = val;
            this.next = this.prev = null;
        }       
}