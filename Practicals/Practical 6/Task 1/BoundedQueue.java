//Design a bounded lock-based queue implementation using an array instead of a linked list.
// Allow parallelism by using two separate locks for head and tail.

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedQueue<T> {
    private final Lock headLock = new ReentrantLock();
    private final Lock tailLock = new ReentrantLock();
    private final T[] items;
    private int head, tail, count;

    public BoundedQueue(int capacity) {
        items = (T[]) new Object[capacity];
    }

    public void enqueue(T item) throws InterruptedException {
        tailLock.lock();
        try{
            while (count == items.length) {
                tailLock.unlock();
                Thread.sleep(100);
                tailLock.lock();
            }
            items[tail] = item;
            tail = (tail + 1) % items.length;
            ++count;
            System.out.println("Enqueued: "+item);
        }
        finally {
            tailLock.unlock();
        }
    }

    public T dequeue() throws InterruptedException {
        T item;
        headLock.lock();
        try {
            while (count == 0) {
                headLock.unlock();
                Thread.sleep(10);
                headLock.lock();
            }
            item = items[head];
            head = (head + 1) % items.length;
            --count;
        } 
        finally {
            headLock.unlock();
        }
        return item;
    }
}
