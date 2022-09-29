public class QNode
{
    volatile QNode next = null;
    volatile QNode pred = null;
    volatile boolean locked = false;
    Thread marshal;

    QNode(){
        this.marshal = Thread.currentThread();
    }

    public String getMarshal(){
        return this.marshal.getName().substring(this.marshal.getName().length() - 1);
    }
}