//Basic bounded queue
//Need a traffic light class variable

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.atomic.AtomicInteger;

public class Intersection {
    ReentrantLock enqLock, deqLock;
    Condition notEmptyCondition, notFullCondition;
    AtomicInteger size;
    volatile Node head, tail;
    int capacity;
    public int id;
    public TrafficLight trafficLight;

    public Intersection(int id, int capacity) {
        this.id = id;
        this.trafficLight = new TrafficLight();
        this.capacity = capacity;
        this.size = new AtomicInteger(0);
        this.enqLock = new ReentrantLock();
        this.deqLock = new ReentrantLock();
        this.notEmptyCondition = deqLock.newCondition();
        this.notFullCondition = enqLock.newCondition();
        head = new Node(null);
        tail = head;
    }

    public void enqueue(Vehicle vehicle) throws InterruptedException {
        boolean mustWakeDequeuers = false;
        enqLock.lock();
        try {
            while(size.get() == capacity){
                notFullCondition.await();
            }
            
            Node node = new Node(vehicle);
            tail.next = tail; tail = node;

            if(size.getAndIncrement() == 0) mustWakeDequeuers = true;
        } 
        finally {
            enqLock.unlock();
        }
        if(mustWakeDequeuers) {
            deqLock.lock();
            try {
                notEmptyCondition.signalAll();
            } 
            finally {
                deqLock.unlock();
            }
        }
    }

    public Vehicle dequeue() throws InterruptedException {
        boolean mustWakeEnqueuers = false;
        Vehicle vehicle = null;
        deqLock.lock();
        try {
            while(size.get() == 0) {
                notEmptyCondition.await();
            }

            vehicle = head.next.vehicle;
            head = head.next;
            if (size.getAndDecrement() == capacity) {
                mustWakeEnqueuers = true;
            }
        } 
        finally {
            deqLock.unlock();
        }
        if(mustWakeEnqueuers) {
            enqLock.lock();
            try{
                notFullCondition.signalAll();
            } 
            finally {
                enqLock.unlock();
            }
        }
        return vehicle;
    }

    public boolean peek(int id){
        if(head.next == null){
            return false;
        }
        else{
            return head.next.vehicle.getVehicleId() == id;
        }
    }

    public int getId() {
        return id;
    }

    public TrafficLight getTrafficLight() {
        return trafficLight;
    }

    protected class Node {
        public Vehicle vehicle;
        public volatile Node next;

        public Node(Vehicle x) {
            vehicle = x;
            next = null;
        }
    }
}