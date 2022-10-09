import java.util.concurrent.locks.ReentrantLock;

public class Node {
    String ticket;
    long startTime;
    long totalTime;
    int key;
    Node next;
    ReentrantLock lock = new ReentrantLock();

    public Node(int key, String ticket, long totalTime) {
        this.key = key;
        this.ticket = ticket;
        this.totalTime = startTime;
        startTime = System.currentTimeMillis();
        lock = new ReentrantLock();
    }

    public String getPerson(){
        return ticket.substring(ticket.indexOf("-") + 1);
    }

    public String getEntrance(){
        return ticket.substring(ticket.indexOf("e") + 1, ticket.indexOf("-"));
    }

    public void lock (){
        lock.lock();
    }

    public void unlock (){
        lock.unlock();
    }
}
