public class Marshal extends Thread {

    private VotingStation vs;

	Marshal(VotingStation _vs)
	{
		vs = _vs ;
	}

	@Override
	public void run()
	{
		for(int i = 0; i < 5; i++)
		{
			try{
				vs.castBallot(i);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
