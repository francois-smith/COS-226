import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class VotingStation {
    Lock l;

	public VotingStation() {
		l = new Timeout();
	}

	public void castBallot(int i) throws InterruptedException
	{
		i++;
		//try lock then sleep if got it
		if(l.tryLock(800, TimeUnit.MILLISECONDS)){
			System.out.println(Thread.currentThread().getName() + " with person " + i + " entered the voting station.");
			int sleep = randomNumber(200, 1000);
			Thread.sleep(sleep);
			l.unlock();
			System.out.println(Thread.currentThread().getName() + " with person " + i + " cast a vote.");
			System.out.println(Thread.currentThread().getName() + " with person " + i + " left the voting station.");
		}
		else{
			System.out.println(Thread.currentThread().getName() + " has timed out");
		}
	}

	private int randomNumber(int min, int max){
        return (int)(Math.random() * (max - min)) + min;
    }
}
