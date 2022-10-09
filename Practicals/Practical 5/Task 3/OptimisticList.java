public class OptimisticList {
    private Node head;
    
    public OptimisticList() {
        head = new Node(Integer.MIN_VALUE, "head", 0);
        head.next = new Node(Integer.MAX_VALUE, "tail", 0);
    }

    public boolean add(String ticket, long time) {
        int key = ticket.hashCode();
        while(true){
            Node pred = head;
            Node curr = pred.next;
            while(curr.key < key){
                pred = curr;
                curr = curr.next;
            }

            pred.lock();
            curr.lock();
            try{
                if(validate(pred, curr)){
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
            }
            finally{
                pred.unlock();
                curr.unlock();
            }
        }
    }

    public boolean remove(String ticket){
        int key = ticket.hashCode();
        while(true){
            Node pred = head;
            Node curr = pred.next;
            while(curr.key < key){
                pred = curr;
                curr = curr.next;
            }

            pred.lock();
            curr.lock();
            try{
                if(validate(pred, curr)){
                    if(curr.key != key){
                        return false;
                    }
                    else{
                        pred.next = curr.next;
                        return true;
                    }
                }
            }
            finally{
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
                curr.unlock();
            }
        }
    }

    public boolean validate(Node pred, Node curr){
        Node node = head;
        while(node.key <= pred.key){
            if(node == pred){
                return pred.next == curr;
            }
            node = node.next;
        }
        return false;
    }

    public boolean contains(String ticket){
        int key = ticket.hashCode();
        while(true){
            Node pred = head;
            Node curr = pred.next;
            while(curr.key < key){
                pred = curr;
                curr = curr.next;
            }

            pred.lock();
            curr.lock();
            try{
                if(validate(pred, curr)){
                    return curr.key == key;
                }
            }
            finally{
                pred.unlock();
                curr.unlock();
            }
        }
    }

    public static long clamp(long val, long min, long max) {
        return Math.max(min, Math.min(max, val));
    }

    private String getThreadNumber(){
        return Thread.currentThread().getName().substring(Thread.currentThread().getName().length() - 1);
    }
}
