import java.util.concurrent.locks.Lock;

public class VotingStation {
	Lock l;

	public VotingStation() {
		l = new MCSQueue();
	}

	public void castBallot(int i)
	{      
		i++;
		l.lock();
		try{
			try{
				int sleep = randomNumber(200, 1000);
				Thread.sleep(sleep);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " with person " + i + " entered the voting station.");

		}
		finally{
			l.unlock();
			System.out.println(Thread.currentThread().getName() + " with person " + i + " cast a vote.");
			System.out.println(Thread.currentThread().getName() + " with person " + i + " left the voting station.");
		}
	}

	private int randomNumber(int min, int max){
        return (int)(Math.random() * (max - min)) + min;
    }
}
