public class Main {
    public static void main(String[] args) throws InterruptedException {
        BoundedQueue queue = new BoundedQueue(10);
        BoundedThread producer = new BoundedThread(queue, true);
        BoundedThread consumer = new BoundedThread(queue, false);
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}
