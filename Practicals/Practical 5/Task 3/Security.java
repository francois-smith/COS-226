
public class Security extends Thread {
    
    private OptimisticList gallery;
    private int id = 0;
    private long times[] = new long[10];
    private String tickets[] = new String[10];


    private volatile int peopleLeft = 0;
    private volatile int peopleIn = 0;

    public Security(OptimisticList gallery) {
        this.gallery = gallery;
    }

    public void run() {
        long time = System.currentTimeMillis();

        while(peopleLeft < 10) {
            if(System.currentTimeMillis() - time > 200) {
                String ticket = "Entrance" + getThreadNumber() + "-" + id;
                long personTime = randomNumber(100, 1000);
                if(gallery.add(ticket, personTime)) {
                    peopleIn++;
                    peopleLeft++;
                    times[id] = System.currentTimeMillis() + personTime;
                    tickets[id] = ticket;
                    id++;
                    System.out.println(Thread.currentThread().getName() + ": added (P-" + id + ", " + personTime + "ms)");
                }
                else{
                    return;
                }
                time = System.currentTimeMillis();
            }
            else{
                for(int j = 0; j < 10; j++){
                    if(times[j] != 0 && times[j] < System.currentTimeMillis()){
                        String ticket = tickets[j];
                        if(gallery.remove(ticket)){
                            peopleIn--;
                            times[j] = 0;
                        }
                    }
                }
            }
        }

        while(peopleIn > 0){
            for(int j = 0; j < 10; j++){
                if(times[j] != 0 && times[j] < System.currentTimeMillis()){
                    String ticket = tickets[j];
                    if(gallery.remove(ticket)){
                        peopleIn--;
                        times[j] = 0;
                    }
                }
            }
        }
    }

    private int getThreadNumber(){
        return Integer.parseInt(this.getName().substring(this.getName().length() - 1));
    }

    private long randomNumber(int min, int max){
        return (int)(Math.random() * (max - min)) + min;
    }
}
