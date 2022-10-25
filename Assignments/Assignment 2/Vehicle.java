import java.util.List;

public class Vehicle extends Thread{
    private int id;
    private int pathIndex = 0;
    public int[] path;
    private Roads roads;
    private Brand brand;

    //enum of car brands
    public enum Brand {
        BMW, 
        Audi, 
        Mercedes, 
        Volkswagen,
        Ford, 
        Toyota, 
        Honda, 
        Nissan, 
        Hyundai, 
        Kia
    }

    public Vehicle(int id, Roads roads){
        this.id = id;
        this.roads = roads;
        this.path = generatePath();
        this.brand = Brand.values()[(int)(Math.random() * Brand.values().length)];
        run();
    }

    public void run(){
        while(true){
            Intersection intersection = roads.getIntersection(path[pathIndex]);
            //System.out.println(intersection.peek(0));
            if(intersection.peek(this.getVehicleId()) && intersection.trafficLight.state == TrafficLight.State.GREEN){
                try{
                    intersection.dequeue();
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                pathIndex++;
                System.out.println("Vehicle " + id + " of type " + brand + " has moved to intersection " + path[pathIndex]);
                if(pathIndex == path.length){
                    break;
                }
            }
        }
    }

    public int[] generatePath(){
        int[] path = new int[3];
        int intitialIntersection = (int)(Math.random() * 4) + 1;
        path[0] = intitialIntersection;

        for(int i = 1; i < path.length; i++){
            List<Intersection> neighbours = roads.getNeighbours(path[i - 1]);
            int randomIndex = (int)(Math.random() * neighbours.size());
            path[i] = neighbours.get(randomIndex).id;
        }

        return path;
    }

    public int getVehicleId(){
        return id;
    }

    public String toString(){
        return "Vehicle " + id;
    }
}
