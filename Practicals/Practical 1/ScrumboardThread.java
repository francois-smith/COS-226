class ScrumboardThread extends Thread {
    private Thread thread;
    private String name;
    private Scrumboard scrumboard;
    private PetersonLock lock;

    ScrumboardThread(Scrumboard scrumboard, String name, PetersonLock lock) {
        this.scrumboard = scrumboard;
        this.name = name;
        this.lock = lock;
    }

    public void run(){
        for(int i = 0; i < 5; i++) {
            lock.lock();
            try {
                String task = scrumboard.getTask();
                System.out.println(this.name + " Task: "+ task);
                scrumboard.addCompleted(task);
            }
            finally {
                lock.unlock();
            }
        }
    }

    public void start(){
        if(thread == null){
            thread = new Thread(this, name);
            thread.start();
        }
    }
}