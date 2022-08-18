public class main {
    public static void main(String[] args){
        Scrumboard board = new Scrumboard();
        PetersonLock petersonLock = new PetersonLock();
        Thread thread1 = new ScrumboardThread(board, "Thread 1", petersonLock);
        Thread thread2 = new ScrumboardThread(board, "Thread 2", petersonLock);

        thread1.start();
        thread2.start();
    }
}
