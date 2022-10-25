public class Main {
    public static void main(String[] args) throws InterruptedException {
        Roads roads = new Roads();

        //create 10 vehicles
        for(int i = 0; i < 10; i++){
            Vehicle vehicle = new Vehicle(i, roads);
            roads.addVehicle(vehicle);
            vehicle.start();
            vehicle.join();
        }
    }
}
