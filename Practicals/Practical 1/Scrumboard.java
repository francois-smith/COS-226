import java.util.ArrayList;

public class Scrumboard {
    private ArrayList<String> Todo;
    private ArrayList<String> Completed;

    public Scrumboard() {
        Todo = new ArrayList<String>();
        for (char c = 'A'; c <= 'J'; c++) {
            Todo.add(String.valueOf(c));
        }
        Completed = new ArrayList<>();
    }

    public void addCompleted(String task) {
        Completed.add(task);
    }

    public String getTask() {
        String returnTask = Todo.get(0);
        Todo.remove(0);
        return returnTask;
    }
}
