import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.atomic.*;

public class MCSQueue implements Lock
{
    private volatile AtomicReference<QNode> tail;
    ThreadLocal<QNode> node;
    private int[] person = {0,0,0,0,0};

    public MCSQueue()
    {
        tail = new AtomicReference<QNode>(null);
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
        person[Integer.parseInt(node.getMarshal())]++;

        QNode pred = tail.getAndSet(node);
        if (pred != null)
        {
            node.locked = true;
            pred.next = node;
            node.pred = pred;
            while (node.locked){}
        }
    }

    public void unlock()
    {
        QNode node = this.node.get();
        if (node.next == null)
        {
            if (tail.compareAndSet(node, null))
            {
                return;
            }
            while (node.next == null){}
        }

        QNode next = node.next;
        next.locked = false;
        node.next = null;
        node.pred = null;

        //print queue
        QNode temp = tail.get();
        if(temp != null){
            System.out.print("  QUEUE: ");
            //output with arrow except last one
            while(temp.pred != null){
                System.out.print("{" + temp.marshal.getName() + ":Person-" + person[Integer.parseInt(temp.getMarshal())] + "} -> ");
                temp = temp.pred;
            }
            //output last one
            System.out.println("{" + temp.marshal.getName() + ":Person-" + person[Integer.parseInt(temp.getMarshal())] + "}");
        }
        System.out.println();
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
}
