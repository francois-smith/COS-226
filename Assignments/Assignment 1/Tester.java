// Name: Francois Smith	
// Student Number: u21649988

public class Tester extends Thread{
    Bakery lock;
    Project project;

    public Tester(Bakery lock, Project project){
        this.lock = lock;
        this.project = project;
    }

    @Override
    public void run(){
        //To determine if both queues are empty.
        boolean finished = false;
        while(!finished){
            System.out.println(this.getName() + " is ready to test a component.");

            //Attempt to obtain the lock.
            lock.lock();
            try{
                //Try and get a component to test.
                Component component = project.getNextTest();

                //Check if queue was empty.
                while(component == null){
                    //If it was check if both are empty(i.e. all task tested and developed)
                    finished = project.finished();
                    if(project.finished()){
                        //If true then no work to do. Release lock and exit.
                        lock.unlock();
                        return;
                    }
                    else{
                        //Otherwise try again until a component is in need of testing.
                        component = project.getNextTest();
                    }
                }

                //When a component is found, test it.
                System.out.println(this.getName() + " is testing a component: " + component.name); 
                
                //Generate a random testing time
                int testTime = randomNumber(100, 500);
                component.testTime -= testTime;

                //If tstill time left to test just continue
                if(component.testTime > 0){
                    System.out.println(this.getName() + " tested "+ component.name +" for "+ testTime +" ms. Time remaining: "+ component.testTime);
                }
                //Else if test time resulted in no time left, remove component from queue.
                else{
                    System.out.println(this.getName() + " finished testing "+ component.name +".");
                    project.finishTesting(component);
                }

                //Generate break time
                int breakTime = randomNumber(50, 100);
                System.out.println(this.getName() + " is taking a break.");
                try{
                    Thread.sleep(breakTime);
                }
                catch(InterruptedException e){
                    System.out.println(this.getName() + " was interrupted.");
                }
            }
            finally{
                lock.unlock();
                finished = project.finished();
            }
        }
    }

    /**
     * Generates a random number between min and max.
     * @param min - Minimum value.
     * @param max - Maximum value.
     * @return - Random number between min and max.
     */
    private int randomNumber(int low, int high){
        return (int)(Math.random() * (high - low)) + low;
    }
}
