// Name: Francois Smith	
// Student Number: u21649988

public class Main {
    public static void main(String[] args) {
        Project project = new Project();
        Bakery developersLock = new Bakery(5), testersLock = new Bakery(5);

        Tester[] testers = new Tester[5];
        for(int i = 0; i < 5; i++)
        {
            testers[i] = new Tester(testersLock, project);
        }

        Developer[] developers = new Developer[5];
        for(int i = 0; i < 5; i++)
        {
            developers[i] = new Developer(developersLock, project);
        }

        for(int i = 0; i < 5; i++)
        {
            developers[i].start();
            testers[i].start();
        }
    }
}
