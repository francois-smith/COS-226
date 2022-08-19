import java.util.concurrent.locks.Lock;

public class Venue 
{
    Lock l;

	/**
	 * Allows threads to drop off thier loads and wait for a random amount of time.
	 */
	public void dropOff()
	{
		//Lock the lock to disallow other busses
		l.lock();

		//Output drop off message and wait for a random amount of time
		try
		{
			Transport currentThread = (Transport)Thread.currentThread();
			System.out.println("Bus " + Thread.currentThread().getName() + " is dropping-off: Load " + currentThread.getLoadNumber());

			//wait random amount of time
			int sleepTime = (int)(Math.random() * ((1000 - 200) + 1)) + 200;
			Thread.sleep(sleepTime);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//When done waiting, unlock the lock
			l.unlock();
		}
	}
}
