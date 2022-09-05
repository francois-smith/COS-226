public class Main {
    public static void main(String[] args) {
        Consensus<Integer> consensus = new RMWConsensus<Integer>(2);

        ConsensusThread thread1 = new ConsensusThread(consensus);
        ConsensusThread thread2 = new ConsensusThread(consensus);

        thread1.start();
        thread2.start();
    }
}
