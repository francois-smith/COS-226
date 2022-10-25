public class Main {
    public static void main(String[] args) throws InterruptedException {
        Roads roads = new Roads();

        Vehicle vehicle = new Vehicle(0, roads);
        roads.addVehicle(vehicle);
        vehicle.start();

        // //create 10 vehicles
        // for(int i = 0; i < 10; i++){
            
        //     vehicle.start();
        //     vehicle.join();
        // }
    }
}
