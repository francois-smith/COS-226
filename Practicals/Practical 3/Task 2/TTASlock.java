import java.util.concurrent.atomic.AtomicBoolean;

public class TTASlock {
    private AtomicBoolean state = new AtomicBoolean(false);

    public void lock() {
        while (true) {
            while (state.get()){}
            if(!state.getAndSet(true)){
                try{
                    Thread.sleep(100);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                
                return;
            } 
        }
    }

    public void unlock() {
        state.set(false);
    }
}
