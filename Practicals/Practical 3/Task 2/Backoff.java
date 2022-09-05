import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.atomic.AtomicBoolean;

public class Backoff implements Lock {
    private AtomicBoolean state = new AtomicBoolean(false);

    @Override
    public void lock(){
        int MIN_DELAY = randomNumber(10, 100);
        int MAX_DELAY = randomNumber(100, 1000);
        int delay = MIN_DELAY;
        while (true) {
            while (state.get()) {}
            if (!state.getAndSet(true)){
                try{
                    Thread.sleep(100);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                
                return;
            }
            try{
                Thread.sleep((long)(Math.random() % delay));
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            if (delay < MAX_DELAY) delay = 2 * delay;
        }
    }

	@Override
	public void unlock()
	{
		state.set(false);
	}

    public void lockInterruptibly() throws InterruptedException
	{
		throw new UnsupportedOperationException();
	}

	public boolean tryLock()
	{
		throw new UnsupportedOperationException();
	}

	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException
	{
		throw new UnsupportedOperationException();
	}

	public Condition newCondition()
	{
		throw new UnsupportedOperationException();
	}

    /**
     * Generates a random number between min and max.
     * @param min - Minimum value.
     * @param max - Maximum value.
     * @return - Random number between min and max.
     */
    private int randomNumber(int min, int max){
        return (int)(Math.random() * (max - min)) + min;
    }
}
