import java.util.concurrent.atomic.AtomicInteger;
//lock free
public class BoundedQueue {
    AtomicInteger size;
    int capacity;
    int[] array;
    volatile int head, tail;

    public BoundedQueue(int capacity) {
        this.capacity = capacity;
        head = tail = 0;
        size = new AtomicInteger(0);
        array = new int[capacity];
    }

    public void enqueue(int x) throws InterruptedException {
        while (size.get() == capacity) {};
        array[tail] = x;
        tail = (tail + 1) % capacity;

        synchronized (System.out){
            System.out.println("Enqueued " + x);
            if(size.get() > 0){
                System.out.print("Queue: ");
                String output = "";
                for (int i = 0; i < size.get(); i++) {
                    output += " ["+array[(head + i) % capacity] + "] ->";
                }
                output = output.substring(0, output.length() - 2);
                System.out.println(output);
            }
        }
        
        size.getAndIncrement();
    }

    public int dequeue() throws InterruptedException {
        int x;
        while (size.get() == 0) {};
        x = array[head];
        head = (head + 1) % capacity;

        synchronized (System.out){
            System.out.println("Dequeued " + x);
            if(size.get() > 0){
                System.out.print("Queue: ");
                String output = "";
                for (int i = 0; i < size.get(); i++) {
                    output += " ["+array[(head + i) % capacity] + "] ->";
                }
                output = output.substring(0, output.length() - 2);
                System.out.println(output);
            }
        }
        
        size.getAndDecrement();
        return x;
    }
}