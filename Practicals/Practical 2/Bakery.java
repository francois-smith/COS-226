import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// Name: Francois Smith	
// Student Number: u21649988

public class Bakery implements Lock
{
	//member variables
	private volatile int n;
	private volatile int[] label;
	private volatile boolean[] flag;

	/**
	 * Constructor for the Bakery lock.
	 * @param n - Amount of levels in the lock.
	 */
	public Bakery(int n) {
		this.n = n;
		flag = new boolean[n];
		label = new int[n];

		for (int i = 0; i < n; i++) 
		{
			flag[i] = false;
			label[i] = 0;
		}
	}

	/*
	 * Locks the bakery lock.
	 */
	@Override
	public void lock()
	{
		int thread = Integer.parseInt(Thread.currentThread().getName().substring(7));

		flag[thread] = true;
		label[thread] = Largest(label);

		for (int k = 0; k < n; k++) {
			while ((k != thread) && flag[k] && ((label[k] < label[thread]) || ((label[k] == label[thread]) && k < thread))) {}
		}
	}

	/**
	 * Helper method to find the largest label in array.
	 * @param arr - Array to find largest label in.
	 * @return - The index of the largest label.
	 */
	 */
	private int Largest(int[] arr)
	{
        int i;
        int max = arr[0];
         
        // Traverse array elements from second and compare every element with current max
        for (i = 1; i < arr.length; i++)
		{
			if (arr[i] > max)
			{
				max = arr[i];
			}  
		}
             
        return max;
	}

	/**
	 * Get thread trying to unlock the filter and reset variable.
	 */
	@Override
	public void unlock()
	{
		int thread = Integer.parseInt(Thread.currentThread().getName().substring(7));
		flag[thread] = false;
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
