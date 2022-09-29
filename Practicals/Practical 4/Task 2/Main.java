public class Main {
    public static void main(String args[])
    {
        VotingStation vs = new VotingStation();
        Marshal[] marshals = new Marshal[5];

        for(int i = 0; i < 5; i++){
            marshals[i] = new Marshal(vs);
            marshals[i].start();
        }

    }
}
