public class main {
    public static void main(String[] args){
        Scrumboard board = new Scrumboard();
        Thread thread1 = new ScrumboardThread(board, "Thread 1");
        Thread thread2 = new ScrumboardThread(board, "Thread 2");

        thread1.start();
        thread2.start();
    }
}
