import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicInteger;

public class BoundedQueue {
    ReentrantLock enqLock, deqLock;
    Condition notEmptyCondition, notFullCondition;
    AtomicInteger size;
    int capacity;
    int[] array;
    volatile int head, tail;

    public BoundedQueue(int capacity) {
        this.capacity = capacity;
        head = tail = 0;
        size = new AtomicInteger(0);
        array = new int[capacity];
        enqLock = new ReentrantLock();
        deqLock = new ReentrantLock();
        notEmptyCondition = deqLock.newCondition();
        notFullCondition = enqLock.newCondition();
    }

    public void enqueue(int x) throws InterruptedException {
        boolean mustWakeDequeuers = false;
        enqLock.lock(); 
        try{
            while (size.get() == capacity) notFullCondition.await();
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
            
            if (size.getAndIncrement() == 0) mustWakeDequeuers = true;
        }
        finally{
            enqLock.unlock();
        }

        if (mustWakeDequeuers) {
            deqLock.lock();
            try{
                notEmptyCondition.signalAll();
            }
            finally{
                deqLock.unlock();
            }
        }
    }

    public int dequeue() throws InterruptedException {
        int result;
        boolean mustWakeEnqueuers = false;
        deqLock.lock();
        try{
            while (size.get() == 0) notEmptyCondition.await();
            result = array[head];
            head = (head + 1) % capacity;
            if (size.getAndDecrement() == capacity) mustWakeEnqueuers = true;
            synchronized (System.out){
                System.out.println("Dequeued " + result);
                if(size.get() > 0){
                    //[item] ->
                    System.out.print("Queue: ");
                    String output = "";
                    for (int i = 0; i < size.get(); i++) {
                        output += " ["+array[(head + i) % capacity] + "] ->";
                    }
                    output = output.substring(0, output.length() - 2);
                    System.out.println(output);
                }
            }
        }
        finally{
            deqLock.unlock();
        }

        if (mustWakeEnqueuers) {
            enqLock.lock();
            try{
                notFullCondition.signalAll();
            }
            finally{
                enqLock.unlock();
            }
        }
        return result;
    }
}
