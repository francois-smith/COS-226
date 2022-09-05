public class RMWConsensus<T> extends ConsensusProtocol<T> {
    public RMWConsensus(int threadCount) {
        super(threadCount);
    }

    public void decide() {
        int i = Integer.parseInt(Thread.currentThread().getName().substring(7));
        int j = (i + 1) % 2;
        if(proposed[i] != proposed[j]) {
            proposed[j] = proposed[i];
        } 
        else{
            
        }
        
        System.out.println(Thread.currentThread().getName() + " decides " + proposed[i]);
    }
    
}
