public class Main {
    public static void main(String[] args) {
        // Create a new instances of the locks.
        TASlock tasLock = new TASlock();
        TTASlock ttasLock = new TTASlock();
        Backoff backoff = new Backoff();

        //Arrays for holding execution time based on thread count
        int index = 0;
        int[] numThreads = new int[6];
        int[] TASTime = new int[6];
        int[] TTASTime = new int[6];
        int[] BackoffTime = new int[6];

        //Loop through the number of threads increasing gradualy
        for(int i = 5; i < 34; i*=1.5){
            numThreads[index] = i;

            //Create an array of threads
            TestThread[] threads = new TestThread[i];
            for(int j = 0; j < i; j++){
                threads[j] = new TestThread(ttasLock, tasLock, backoff, 1);
            }

            //Start the threads
            for(int j = 0; j < i; j++){
                threads[j].start();
            }

            //Wait for the threads to finish
            for(int j = 0; j < i; j++){
                try {
                    threads[j].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //Get Execution time based on thread count
            for(int j = 0; j < i; j++){
                TASTime[index] += threads[j].getTime()[0];
                TTASTime[index] += threads[j].getTime()[1];
                BackoffTime[index] += threads[j].getTime()[2];
            }
            index++;
        }

        System.out.print("Number of threads: "); printArray(numThreads);
        System.out.println("--------------------------------");
        System.out.print("TASLock: "); printArray(TASTime);
        System.out.print("TTASLock: "); printArray(TTASTime);
        System.out.print("BackoffLock: "); printArray(BackoffTime);
    }

    public static void printArray(int[] array){
        System.out.print("[");
        for(int i = 0; i < array.length; i++){
            if(i != array.length - 1){
                System.out.print(array[i] + ", ");
            }
            else{
                System.out.print(array[i]);
            }
        }
        System.out.println("]");
    }
}
