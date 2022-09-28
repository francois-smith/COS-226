import java.util.concurrent.locks.Lock;

public class VotingStation {
	Lock l;

	public VotingStation() {
		l = new MCSQueue();
	}

	public void castBallot()
	{
		l.lock();
		try{
			try{
				int sleep = randomNumber(200, 1000);
				Thread.sleep(sleep);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		finally{
			l.unlock();
		}
	}

	private int randomNumber(int min, int max){
        return (int)(Math.random() * (max - min)) + min;
    }
}
