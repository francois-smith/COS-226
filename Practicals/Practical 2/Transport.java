public class Transport extends Thread 
{
    private Venue destination;
	private int loadNumber;

	/**
	 * Constuctor for the class.
	 * @param dest - Venue object to drop off the load.
	 * @param loadNumber - The load number to drop off(Thread Member Variable).
	 */
	public Transport(Venue dest)
	{
		this.destination = dest;
	}


	/**
	 * Returns the current Load Number
	 */
	public int getLoadNumber()
	{
		return this.loadNumber;
	}

	/**
	 * The run function of the thread. This function runs necessary code to simulate a drop off.
	 */
	@Override
	public void run()
	{
		for(int i = 0; i < 5; i++)
		{
			try
			{
				this.loadNumber = i+1;
				//Output intentions
				System.out.println("Bus " + currentThread().getName() + " is waiting to drop off: Load " + loadNumber);

				//Drop off passengers
				destination.dropOff();

				//Leaves drop off location
				System.out.println("Bus " + currentThread().getName() + " has left: Load " + loadNumber);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
