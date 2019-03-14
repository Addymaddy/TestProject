package din2;


import java.util.concurrent.atomic.AtomicInteger;

public class monitorBarrier implements Barrier
{

	int barrierWaitingThreadcount;
	volatile AtomicInteger  barrierLimit = new AtomicInteger();
	Monitor monitor = new Monitor();
	int initLimit;

	public monitorBarrier(int N) {
		initLimit=N;
		barrierLimit.set(N);

	}

	void reset(){
		barrierLimit.set(initLimit);
	}

	public void arriveAndWait()
	{
		monitor.enterCriticalSection();
		if(barrierLimit.get()==1)
		{
			synchronized (this){
				System.out.println("Notyfing all threads waiting on monitor --- "+ Thread.currentThread().getName());
				notifyAll();
				barrierLimit.set(initLimit);
				monitor.leaveCriticalSection();

			}

		}
		else
		{
			barrierLimit.decrementAndGet();
			try {
				monitor.leaveCriticalSection();
				synchronized (this){
					System.out.println("Thread waiting on barrier --- "+ Thread.currentThread().getName());
				wait();
					System.out.println("Thread released from waiting  on barrier --- " + Thread.currentThread().getName());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


abstract class Mutex {    public Mutex(boolean isManual, boolean isSignalled) {
	this.isSignalled = isSignalled;
	this.isAuto = !isManual;
}
	protected synchronized int waitForSingleObject(long timeout) {
		if (isSignalled) {
			if (isAuto)
				reset();
			return Blockable.SUCCESS;
		}
		long t1, t2;
		try {

			t1 = System.currentTimeMillis();

			waitingCount++;
			System.out.println("Thread waiting on Mutex --- "+ Thread.currentThread().getName());

			wait();

			System.out.println("Thread released from waiting  on mutex --- " + Thread.currentThread().getName());

			waitingCount--;
			t2 = System.currentTimeMillis() - t1;
		} catch (InterruptedException e) {
			return Blockable.INTERRUPT;
		}
		if (t2 >= timeout){
			return Blockable.TIMEOUT;

		}
		if (isAuto)
			reset();

		return Blockable.SUCCESS;
	}
	protected synchronized void signal() {
		if (isAuto)
		{
			if(waitingCount ==0)
			{
				isSignalled = true;
			}
			else {
				notify();
			}
		}
		else{
			notifyAll();
		}

	}


	protected synchronized void reset() {
		isSignalled = false;
	}
	volatile boolean isSignalled;
	volatile boolean isAuto;
	volatile int waitingCount=0;
}

class Monitor extends Mutex {    public Monitor() {
	super(false, true);
}
	public synchronized void enterCriticalSection() {
		while (waitForSingleObject(Blockable.INFINITE) != Blockable.SUCCESS) ;
	}
	public synchronized void leaveCriticalSection() {
		signal();
	}

}