import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.atomic.*;

public class MCSQueue implements Lock
{
    public class QNode
    {
        volatile QNode next = null;
        volatile QNode pred = null;
        volatile boolean locked = false;
        volatile int person;
        Thread marshal;

        public void setPerson(int i){
            person = i;
        }

        public void setMarshal(Thread t){
            marshal = t;
        }
    }

    private volatile AtomicReference<QNode> tail = new AtomicReference<>(null);
    ThreadLocal<QNode> node;
    private int person = 0;

    public MCSQueue()
    {
        node = new ThreadLocal<QNode>()
        {
            protected QNode initialValue()
            {
                return new QNode();
            }
        };
    }

    public void lock()
    {
        QNode node = this.node.get();
        node.setPerson(person++);
        node.setMarshal(Thread.currentThread());
        System.out.println(node.marshal.getName() + " with person " + node.person + " entered the voting station.");

        QNode pred = tail.getAndSet(node);
        if (pred != null)
        {
            node.locked = true;
            pred.next = node;
            node.pred = pred;
            while (node.locked){}
        }

        System.out.println(node.marshal.getName() + " with person " + node.person + " cast a vote.");
    }

    public void unlock()
    {
        QNode node = tail.get();
        person--;
        if (node.next == null)
        {
            if (tail.compareAndSet(node, null))
            {
                printQueue();
                return;
            }
            while (node.next == null){}
        }
        node.next.locked = false;
        node.next = null;
        node.pred = null;
        
        System.out.println(node.marshal.getName() + " with person " + node.person + " left the voting station.");
        printQueue();
    }

    public void lockInterruptibly() throws InterruptedException
    {
		throw new UnsupportedOperationException();
	}

	public boolean tryLock()
	{
		throw new UnsupportedOperationException();
	}

	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException
	{
		throw new UnsupportedOperationException();
	}

	public Condition newCondition()
	{
		throw new UnsupportedOperationException();
	}

    private void printQueue(){
        System.out.print("Queue: ");
        QNode node = tail.get();
        if(node != null){
            while(node.next != null){
                System.out.println("{"+node.marshal.getName() + " : Person " + node.person+"} ->");
                node = node.next;
            }
            System.out.println("{"+node.marshal.getName() + " : " + node.person+"}");
        }
    }
}
