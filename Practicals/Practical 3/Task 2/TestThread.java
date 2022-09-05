public class TestThread extends Thread{
    TTASlock ttasLock;
    TASlock tasLock;
    Backoff backoffLock;
    int numAccesses;
    int[] time;

    public TestThread(TTASlock ttasLock, TASlock tasLock, Backoff backoffLock, int numAccesses){
        this.ttasLock = ttasLock;
        this.tasLock = tasLock;
        this.backoffLock = backoffLock;
        this.numAccesses = numAccesses;
        this.time = new int[3];
    }

    public int[] getTime(){
        return time;
    }

    public void run(){
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < numAccesses; i++){
            tasLock.lock();
            tasLock.unlock();
        }
        time[0] = (int)(System.currentTimeMillis() - startTime);

        startTime = System.currentTimeMillis();
        for(int i = 0; i < numAccesses; i++){
            ttasLock.lock();
            ttasLock.unlock();
        }
        time[1] = (int)(System.currentTimeMillis() - startTime);

        startTime = System.currentTimeMillis();
        for(int i = 0; i < numAccesses; i++){
            backoffLock.lock();
            backoffLock.unlock();
        }
        time[2] = (int)(System.currentTimeMillis() - startTime);
    }
}