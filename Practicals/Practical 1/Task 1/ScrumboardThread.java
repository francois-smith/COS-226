class ScrumboardThread extends Thread {
    private Thread thread;
    private String name;
    private Scrumboard scrumboard;

    ScrumboardThread(Scrumboard scrumboard, String name) {
        this.scrumboard = scrumboard;
        this.name = name;
    }

    public void run(){
        for(int i = 0; i < 5; i++) {
            String task = scrumboard.getTask();
            System.out.println(this.name + " Task: "+ task);
            scrumboard.addCompleted(task);
        }
    }

    public void start(){
        if(thread == null){
            thread = new Thread(this, name);
            thread.start();
        }
    }
}