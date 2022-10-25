public class Main {
    public static void main(String[] args) throws InterruptedException {
        Roads roads = new Roads();
        System.out.println("================ Starting Road Simulation ================");

        for(int i = 0; i < 10; i++){
            Vehicle vehicle = new Vehicle(i, roads);
            System.out.println("Vehicle " + vehicle.getVehicleId() + " | " + vehicle.brand + " | " + "is now waiting at intersection " + vehicle.path[0]);
            roads.addVehicle(vehicle);
            vehicle.start();
        }

        for(Vehicle vehicle : roads.vehicles){
            vehicle.join();
        }

        roads.stopLights();
    }
}
