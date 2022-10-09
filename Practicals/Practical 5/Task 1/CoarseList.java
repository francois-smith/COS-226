import java.util.concurrent.locks.ReentrantLock;

public class CoarseList {
    private Node head;
    private ReentrantLock lock = new ReentrantLock();

    public CoarseList() {
        head = new Node(Integer.MIN_VALUE, "head", 0);
        head.next = new Node(Integer.MAX_VALUE, "tail", 0);
    }

    public boolean add(String ticket, long time) {
        Node pred, curr;
        int key = ticket.hashCode();
        lock.lock();
        try{
            pred = head;
            curr = pred.next;
            while(curr.key < key){
                pred = curr;
                curr = curr.next;
            }

            if(curr.key == key){
                return false;
            }
            else{
                Node node = new Node(key, ticket, time);
                node.next = curr;
                pred.next = node;
                return true;
            }
        }
        finally {
            lock.unlock();
        }
    }

    public boolean remove(String ticket){
        Node pred, curr;
        int key = ticket.hashCode();
        lock.lock();
        try{
            pred = head;
            curr = pred.next;
            while(curr.key < key){
                pred = curr;
                curr = curr.next;
            }

            if(curr.key == key){
                pred.next = curr.next;
                return true;
            }
            else{
                return false;
            }
        }
        finally {
            Node temp = head.next;
            String list = "";
            while(temp.next != null){
                if(getThreadNumber().equals(temp.getEntrance())){
                    long timeLeft = System.currentTimeMillis() - temp.startTime;
                    list += "(P-" + temp.getPerson() + "," + timeLeft + "ms), ";
                } 
                temp = temp.next;
            }
            //remove last comma
            if(list.length() > 0){
                list = list.substring(0, list.length() - 2);
            }
            System.out.println(Thread.currentThread().getName() + ":" + list);
            lock.unlock();
        }
    }

    public static long clamp(long val, long min, long max) {
        return Math.max(min, Math.min(max, val));
    }

    private String getThreadNumber(){
        return Thread.currentThread().getName().substring(Thread.currentThread().getName().length() - 1);
    }
}
