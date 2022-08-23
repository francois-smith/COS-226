import java.util.LinkedList;
import java.util.Queue;

public class Project
{
	private volatile Queue<Component> develop = new LinkedList<>(), testing = new LinkedList<>();
	
	public Project(){
		develop.add(new Component('s', "Calculator"));
		develop.add(new Component('m', "Calendar"));
		develop.add(new Component('s', "Billing"));
		develop.add(new Component('l', "Timetable"));
		develop.add(new Component('m', "Phonebook"));
		develop.add(new Component('l', "User Manager"));
		develop.add(new Component('s', "Api"));
	}

	/**
	 * Check if there are still items to develop
	 * @return - If queue is empty
	 */
	public boolean development(){
		return !develop.isEmpty();
	}

	/**
	 * Get the next component to develop.
	 * @return -Reference to item in front of queue
	 */
	public Component getNextComponent(){
		return develop.peek();
	}

	/**
	 * Remove component from queue and add it to testing.
	 * @param c - Component to add to testing.
	 */
	public void finishDevelopment(Component c){
		develop.remove(c);
		testing.add(c);
	}

	/**
	 * Check if there are still items to test
	 * @return - If queue is empty
	 */
	public boolean testing(){
		return !develop.isEmpty();
	}

	/**
	 * Get the next component to test.
	 * @return -Reference to item in front of queue
	 */
	public Component getNextTest(){
		return testing.peek();
	}

	/**
	 * Remove component from queue.
	 * @param c - Component to remove.
	 */
	public void finishTesting(Component c){
		testing.remove(c);
	}

	/**
	 * Checks the status of the project.
	 * @return - If project is finished.
	 */
	public boolean finished(){
		return develop.isEmpty() && testing.isEmpty();
	}
}
