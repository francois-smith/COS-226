public class ConsensusThread extends Thread
{
	private Consensus<Integer> consensus;

	ConsensusThread(Consensus<Integer> consensusObject)
	{
		consensus = consensusObject;
	}

	public void run()
	{
		for (int i = 0; i < 5; i++)
		{
			// propose a value
			int proposition = randomNumber(100, 200);
			consensus.propose(proposition);
			
			// wait for a random time
			int sleepTime = randomNumber(50, 100);
			try{
				Thread.sleep(sleepTime);
			}
			catch (InterruptedException e){
				e.printStackTrace();
			}

			// decide on a value
			consensus.decide();
			System.out.println(Thread.currentThread().getName() + " decided on " + decision);

			// wait for a random time again
			sleepTime = randomNumber(50, 100);
			try{
				Thread.sleep(sleepTime);
			}
			catch (InterruptedException e){
				e.printStackTrace();
			}
		}
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
