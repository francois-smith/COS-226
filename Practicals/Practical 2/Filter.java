import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// Name: Francois Smith
// Student Number: u21649988

public class Filter implements Lock
{
	//Member variables
	private volatile int levels;
	private volatile int[] level;
	private volatile int[] victim;

	/**
	 * Constructor for the Filter class.
	 * @param n - Amount of levels in the filter.
	 */
	public Filter(int n) 
	{
		//Sets member variables
		this.levels = n;
		level = new int[n];
		victim = new int[n];

		//Sets intial values
		for (int i = 0; i < n; i++) 
		{
			level[i] = 0;
		}
	}

	/**
	 * Locks the filter class. 
	 */
	@Override
	public void lock()
	{
		//Get thread that called the lock
		int thread = Integer.parseInt(Thread.currentThread().getName().substring(7));

		//Move through the levels
		for (int i = 1; i < levels; i++) {

            level[thread] = i;
            victim[i] = thread;
            for (int k = 0; k < levels; k++) {
                while ((k != thread) && (level[k] >= i && victim[i] == thread)){}
            }
        }
	}

	/**
	 * Get thread trying to unlock the filter and reset variable.
	 */
	@Override
	public void unlock()
	{
		int thread = Integer.parseInt(Thread.currentThread().getName().substring(7));
		level[thread] = 0;
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
}