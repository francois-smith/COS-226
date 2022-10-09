
public class Main {
    public static void main(String[] args) {
        //create linked list queue
        FineList gallery = new FineList();
        //create 5 security threads
        Security[] security = new Security[5];
        for(int i = 0; i < 5; i++){
            security[i] = new Security(gallery);
            security[i].start();
        }

        //wait for threads to finish
        for(int i = 0; i < 5; i++){
            try {
                security[i].join();
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
