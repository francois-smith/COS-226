import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;

public class Roads{
    private Map<Intersection, List<Intersection>> roads = new HashMap<>();

    public Roads(){
        //random capacity for each intersection
        int capacity = (int)(Math.random() * 6) + 1;
        Intersection intersection1 = new Intersection(1, capacity);

        capacity = (int)(Math.random() * 6) + 1;
        Intersection intersection2 = new Intersection(2, capacity);

        capacity = (int)(Math.random() * 6) + 1;
        Intersection intersection3 = new Intersection(3, capacity);

        capacity = (int)(Math.random() * 6) + 1;
        Intersection intersection4 = new Intersection(4, capacity);

        addRoad(intersection1, intersection2);
        addRoad(intersection2, intersection3);
        addRoad(intersection3, intersection4);
        addRoad(intersection4, intersection1);
    }

    public void addVehicle(Vehicle vehicle){
        Intersection intersection = getIntersection(vehicle.path[0]);
        try{
            intersection.enqueue(vehicle);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
    public void addRoad(Intersection source, Intersection destination){
        if(!roads.containsKey(source)){
            roads.put(source, new LinkedList<>());
        }
        if(!roads.containsKey(destination)){
            roads.put(destination, new LinkedList<>());
        }
        roads.get(source).add(destination);
        roads.get(destination).add(source);
    }
    
    public List<Intersection> getNeighbours(int id){
        return roads.get(getIntersection(id));
    }

    public Intersection getIntersection(int id){
        for(Intersection intersection : roads.keySet()){
            if(intersection.id == id){
                return intersection;
            }
        }
        return null;
    }
    
    
    public void printGraph(){
        synchronized(System.out){
            for(Intersection source : roads.keySet()){
                List<Intersection> neighbours = roads.get(source);
                for(Intersection destination : neighbours){
                    System.out.println(source + " is connected to " + destination);
                }
            }
        }
    }
}