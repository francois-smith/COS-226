import java.util.concurrent.locks.Lock;

//Imports needed to implement functions of Lock interface
import java.util.concurrent.locks.Condition;
import java.util.concurrent.TimeUnit;

class PetersonLock implements Lock {
    private volatile boolean[] flag = new boolean[2];
    private volatile int victim;

    public PetersonLock(){}

    public void lock(){
        System.out.println(Thread.currentThread().getName()+ " is locking:");
        int i = Integer.parseInt(Thread.currentThread().getName().substring(7)) - 1;
        int j = 1 - i;
        flag[j] = true;
        victim = i;
        while(flag[j] && victim == i){};
    }

    public void unlock(){
        System.out.println(Thread.currentThread().getName()+ " is unlocking:");
        int i = Integer.parseInt(Thread.currentThread().getName().substring(7)) - 1;
        flag[i] = false;
    }

    //---------------------------------Methods needed for implementing Lock interface-------------------------------------------
    public void lockInterruptibly(){} 
    public boolean tryLock() {return false;}
    public boolean tryLock(long time, TimeUnit unit){return false;}  
    public Condition newCondition(){return newCondition();} 
}