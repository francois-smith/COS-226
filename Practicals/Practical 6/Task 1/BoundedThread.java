public class BoundedThread extends Thread {
    private BoundedQueue<Integer> queue;
    private boolean isProducer;

    public BoundedThread(BoundedQueue<Integer> queue, boolean isProducer) {
        this.queue = queue;
        this.isProducer = isProducer;
    }

    @Override
    public void run() {
        if (isProducer) {
            for (int i = 0; i < 100; i++) {
                try {
                    queue.enqueue(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            for (int i = 0; i < 100; i++) {
                try {
                    System.out.println("Dequeued: "+queue.dequeue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}