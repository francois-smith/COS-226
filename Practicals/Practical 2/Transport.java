public class Transport extends Thread 
{
    private Venue destination;
	private String loadNumber;

	/**
	 * Constuctor for the class.
	 * @param dest - Venue object to drop off the load.
	 * @param loadNumber - The load number to drop off(Thread Member Variable).
	 */
	public Transport(Venue dest, String loadNumber)
	{
		this.loadNumber = loadNumber;
		this.destination = dest;
	}

	/**
	 * Helper function to return the load number of the thread.
	 * @return - The load number of the thread.
	 */
	public String getLoadNumber()
	{
		return loadNumber;
	}

	/**
	 * The run function of the thread. This function runs necessary code to simulate a drop off.
	 */
	@Override
	public void run()
	{
		try
		{
			//Output intentions
			System.out.println("Bus " + currentThread().getName() + " is waiting to drop off: " + loadNumber);

			//Drop off passengers
			destination.dropOff();

			//Leaves drop off location
			System.out.println("Bus " + currentThread().getName() + " has left: " + loadNumber);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
