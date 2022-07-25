class ThreadDemo extends Thread {
    private Thread t;
    private String name;

    ThreadDemo(String name){
        this.name = name;
        System.out.println("Creating thread:" + name);
    }

    public void run(){
        System.out.println("Running thread:" + name);
        try{
            int sleepTime = (int)(Math.random() * ((5000 - 2000) + 1)) + 2000;
            System.out.println("Thread: "+name+" sleeping for "+sleepTime+"ms");
            Thread.sleep(sleepTime);
        }
        catch(InterruptedException e){
            System.out.println("Thread " + name +  " interrupted");
        }
        System.out.println("Thread " + name + " exiting");
    }

    public void start(){
        System.out.println("Starting thread:" + name);
        if(t == null){
            t = new Thread(this, name);
            t.start();
        }
    }
}