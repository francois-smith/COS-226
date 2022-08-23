// Name: Francois Smith	
// Student Number: u21649988

public class Developer extends Thread{
    Bakery lock;
    Project project;

    public Developer(Bakery lock, Project project){
        this.lock = lock;
        this.project = project;
    }

    @Override
    public void run(){
        //While still components to develop
        while(project.development()){
            //Attempt to obtain the lock.
            System.out.println(this.getName() + " is ready to develop a component.");
            lock.lock();
            try{
                //Knowing there are still components to develop, get one.
                Component component = project.getNextComponent();

                //Chance that we got empty component.
                if(component != null){

                    //Develop component.
                    System.out.println(this.getName() + " is developing a component: " + component.name);
                    
                    //Generate a random development time
                    int devTime = randomNumber(100, 500);
                    if(component.developTime - devTime > 0){
                        //If still time left to develop after time spent, subract time from total time
                        component.developTime -= devTime;
                        System.out.println(this.getName() + " developed "+ component.name +" for "+ devTime +" ms. Time remaining: "+ component.developTime);
                    }
                    else{
                        //If no time left to develop, remove component from queue and add it to testing.
                        System.out.println(this.getName() + " finished developing "+ component.name +".");
                        project.finishDevelopment(component);
                    }

                    //Generate break time
                    int breakTime = randomNumber(50, 100);
                    System.out.println(this.getName() + " is taking a break.");
                    try{
                        Thread.sleep(breakTime);
                    }
                    catch(InterruptedException e){
                        System.out.println("Thread " + this.getName() +  " interrupted");
                    }
                }
            }
            finally{
                lock.unlock();
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
