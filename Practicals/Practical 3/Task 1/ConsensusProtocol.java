public abstract class ConsensusProtocol<T> implements Consensus<T>
{
	public volatile Object[] proposed;

	public ConsensusProtocol(int threadCount)	{
		proposed = new Object[threadCount];
	}

	public void propose(T value){
		System.out.println(Thread.currentThread().getName() + " proposed " + value);
		proposed[(int)Thread.currentThread().getId()] = value;
	}

	abstract public void decide();
}
