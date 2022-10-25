public class TrafficLight extends Thread{
    public enum State{
        RED, 
        GREEN, 
        YELLOW
    }

    public State state;
    private int time;
    private boolean stopLight = false;

    public TrafficLight(){
        this.state = State.values()[(int)(Math.random() * State.values().length)];
        this.time = (int)(Math.random() * 300) + 200;
    }

    public void run(){
        while(true && !stopLight){
            try{
                Thread.sleep(time);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            if(state == State.RED){
                state = State.GREEN;
            }
            else if(state == State.GREEN){
                state = State.YELLOW;
            }
            else if(state == State.YELLOW){
                state = State.RED;
            }
        }
    }

    public void stopLight(){
        this.stopLight = true;
    }
}
