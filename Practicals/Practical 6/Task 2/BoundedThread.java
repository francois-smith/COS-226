public class BoundedThread extends Thread {
    private BoundedQueue queue;
    private boolean isProducer;

    public BoundedThread(BoundedQueue queue, boolean isProducer){
        this.queue = queue;
        this.isProducer = isProducer;
    }

    @Override
    public void run() {
        if (isProducer){
            for(int i = 0; i < 50; i++){
                try{
                    queue.enqueue(i);
                } 
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        } 
        else{
            for(int i = 0; i < 50; i++){
                try{
                    queue.dequeue();
                } 
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}