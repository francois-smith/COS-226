public class FineList {
    private Node head;
    
    public FineList() {
        head = new Node(Integer.MIN_VALUE, "head", 0);
        head.next = new Node(Integer.MAX_VALUE, "tail", 0);
    }

    public boolean add(String ticket, long time) {
        int key = ticket.hashCode();
        head.lock();
        Node pred = head;
        try{
            Node curr = pred.next;
            curr.lock();
            try{
                while(curr.key < key){
                    pred.unlock();
                    pred = curr;
                    curr = curr.next;
                    curr.lock();
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
            finally{
                curr.unlock();
            }
        }
        finally{
            pred.unlock();
        }
    }

    public boolean remove(String ticket){
        Node pred = null, curr = null;
        int key = ticket.hashCode();
        head.lock();
        try{
            pred = head;
            curr = pred.next;
            curr.lock();
            try{
                while(curr.key < key){
                    pred.unlock();
                    pred = curr;
                    curr = curr.next;
                    curr.lock();
                }
                if(curr.key == key){
                    pred.next = curr.next;
                    return true;
                }
                return false;
            }
            finally{
                curr.unlock();
            }
        }
        finally {
            Node temp = head.next;
            String list = "";
            while(temp.next != null){
                if(getThreadNumber().equals(temp.getEntrance())){
                    long timeLeft =  System.currentTimeMillis() - temp.startTime;
                    list += "(P-" + temp.getPerson() + "," + timeLeft + "ms), ";
                } 
                temp = temp.next;
            }
            if(list.length() > 0){
                list = list.substring(0, list.length() - 2);
                System.out.println(Thread.currentThread().getName() + ":" + list);
            }
            
            pred.unlock();
        }
    }

    public static long clamp(long val, long min, long max) {
        return Math.max(min, Math.min(max, val));
    }

    private String getThreadNumber(){
        return Thread.currentThread().getName().substring(Thread.currentThread().getName().length() - 1);
    }
}
