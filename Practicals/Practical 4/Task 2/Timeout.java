import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.atomic.*;

public class Timeout implements Lock 
{
    static class QNode
    {
        volatile public QNode pred = null;
        public Thread marshal;

        QNode(){
            this.marshal = Thread.currentThread();
        }

        public String getMarshal(){
            return this.marshal.getName().substring(this.marshal.getName().length() - 1);
        }
    }

    static QNode available = new QNode();
    AtomicReference<QNode> tail;
    ThreadLocal<QNode> myNode;

    private int[] person = {0,0,0,0,0};

    public Timeout()
    {
        tail = new AtomicReference<QNode>(null);
        myNode = new ThreadLocal<QNode>()
        {
            protected QNode initialValue()
            {
                return new QNode();
            }
        };
    }

    public void lock(){}

    public void unlock()
    {
        QNode qnode = myNode.get();
        if(!tail.compareAndSet(qnode, null))
        {
            qnode.pred = available;
        }
        myNode.set(null);

        //print queue
        QNode temp = tail.get();
        if(temp != null){
            System.out.print("  QUEUE: ");
            //output with arrow except last one

            while(temp.pred != null && temp.pred != available){
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
		long startTime = System.currentTimeMillis();
        long patience =  TimeUnit.MILLISECONDS.convert(time, unit);
        QNode node = new QNode();
        myNode.set(node);
        person[Integer.parseInt(node.getMarshal())]++;

        QNode pred = tail.getAndSet(node); 
        node.pred = pred;

        if(pred == null || pred.pred == available)
        {
            return true;
        }

        while(System.currentTimeMillis() - startTime < patience)
        {
            QNode predPred = pred.pred;
            if(predPred == available)
            {
                predPred = null;
                return true;
            }
            else if(predPred != null)
            {
                pred = predPred;
            }
        }

        if(!tail.compareAndSet(node, pred))
        {
            node.pred = pred;
        }

        return false;
	}

	public Condition newCondition()
	{
		throw new UnsupportedOperationException();
	}
}
