import java.util.concurrent.atomic.AtomicBoolean;

class TASlock {
    private AtomicBoolean state = new AtomicBoolean(false);

    public void lock() {
        while (state.getAndSet(true)){}
        try{
            Thread.sleep(100);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void unlock() {
        state.set(false);
    }
}