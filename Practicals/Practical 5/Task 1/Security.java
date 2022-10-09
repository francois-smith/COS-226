
public class Security extends Thread {
    private int[] person = new int[5];
    private CoarseList gallery;
    private int id = 0;
    private long times[] = new long[20];
    private String tickets[] = new String[20];
    private volatile int peopleLeft = 10;

    public Security(CoarseList gallery) {
        this.gallery = gallery;
    }

    public void run() {
        while(peopleLeft > 0) {
            try {
                Thread.sleep(200);
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            int ticketNumber = person[getThreadNumber()]++;
            String ticket = "Entrance" + getThreadNumber() + "-" + ticketNumber;
            long time = randomNumber(100, 1000);
            if(peopleLeft > 0 && gallery.add(ticket, time)) {
                times[id] = System.currentTimeMillis() + time;
                tickets[id] = ticket;
                id++;
                System.out.println(Thread.currentThread().getName() + ": added (P-" + ticketNumber + ", " + time + "ms)");
            }
            else{
                return;
            }

            for(int j = 0; j < 10; j++){
                if(times[j] != 0 && times[j] < System.currentTimeMillis()){
                    ticket = tickets[j];
                    if(gallery.remove(ticket)){
                        peopleLeft--;
                        times[j] = 0;
                    }
                }
            }
        }

        //clean rest of items
        for(int j = 0; j < 10; j++){
            if(times[j] != 0){
                String ticket = tickets[j];
                if(gallery.remove(ticket)){
                    times[j] = 0;
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
