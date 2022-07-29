public class main {
    public static void main(String[] args) {
        ThreadDemo t1 = new ThreadDemo("John");
        t1.start();
        
        ThreadDemo t2 = new ThreadDemo("Mary");
        t2.start();

        ThreadDemo t3 = new ThreadDemo("Eliot");
        t3.start();

        ThreadDemo t4 = new ThreadDemo("Nick");
        t4.start();

        ThreadDemo t5 = new ThreadDemo("Jim");
        t5.start();
    }   
}