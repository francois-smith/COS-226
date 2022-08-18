public class Main 
{
    public static void main(String[] args) 
    {
	    Transport[] buses = new Transport[5];
        Venue destination = new Venue();

        //Creating busses
        for(int i = 0; i < 5; i++)
        {
            buses[i] = new Transport(destination);
        }

        // //===================== Filter Lock Implementation ======================
        // Filter filterLock = new Filter(5);
        // destination.l = filterLock;

        // System.out.println("========= Filter Lock ==========");
        // for(Transport bus: buses)
        // {
        //     bus.start();
        // }

        
        //===================== Bakery Lock Implementation ======================
        Bakery bakeryLock = new Bakery(5);
        destination.l = bakeryLock;

        System.out.println("========= Bakery Lock ==========");
        for(Transport bus: buses)
        {
            bus.start();
        }

        
    }
}
