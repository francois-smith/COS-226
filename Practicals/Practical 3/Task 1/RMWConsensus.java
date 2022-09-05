public class RMWConsensus<T> extends ConsensusProtocol<T> {
    private volatile T value;
    
    public RMWConsensus(int threadCount) {
        super(threadCount);
    }

    public T get() {
        return value;
    }

    @SuppressWarnings("unchecked")
    public void decide() {
        int i = 0;
        while (i < proposed.length){
            if(proposed[i] == null){
                i = 0;
            } 
            else{
                i++;
            }
        }
        
        value = (T)proposed[i];
        System.out.println("Decided: " + proposed[0]);
    }
    
}
