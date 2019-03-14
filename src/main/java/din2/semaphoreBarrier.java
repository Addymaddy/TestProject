package din2;


public class semaphoreBarrier implements Barrier
{
	 volatile int barrierLimit;
	int initLimit;

	Semaphore semaphore = new Semaphore(0,1);
	public semaphoreBarrier(int N)
	{
		this.barrierLimit = N;
		this.initLimit =N ;
	}


	void reset(){
		barrierLimit = initLimit;
	}

	public void arriveAndWait()
	{
		semaphore.waitForSingleObject();

		if(barrierLimit==1)
		{
		//	System.out.println("Last Thread Arrived Breaking barrier and notifying all waiting threads--- "+ Thread.currentThread().getName());
			synchronized (this){
				notifyAll();
				reset();
				semaphore.releaseSemaphore(1);
			}
		}
		else
		{
		//	System.out.println("barrier limit before decrementing --> "+ barrierLimit);
			barrierLimit--;
			try {
		//		System.out.println("Barrier Limit now is ---> "+ barrierLimit);
		//		System.out.println("Decrementing the barrier Limit and waiting ----" + Thread.currentThread().getName());
				semaphore.releaseSemaphore(1);
				synchronized (this){
					wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		//semaphore.releaseSemaphore(1);
	}
}


class Semaphore  {    public static final int INVALID_INCREMENT = -1;

	public Semaphore(int initialValue, int maxValue) {
		currentLocks = initialValue;
		maxLocks = maxValue;
	}
	public int waitForSingleObject() {
		return this.waitForSingleObject(Blockable.INFINITE);
	}
	public synchronized int waitForSingleObject(long timeout) {
		long t1, t2;
		if (currentLocks == maxLocks) {
			try {
				t1 = System.currentTimeMillis();
				wait();
				t2 = System.currentTimeMillis() - t1;
				if (t2 >= timeout)
					return Blockable.TIMEOUT;
			} catch (InterruptedException e) {
				return Blockable.INTERRUPT;
			}
		}
		currentLocks++;
		return Blockable.SUCCESS;
	}
	public synchronized int releaseSemaphore(int increment) {
		if (currentLocks - increment >= 0) {
			currentLocks -= increment;
			for (int i=0; i < increment; i++)
				notify();
			return Blockable.SUCCESS;
		}
		return INVALID_INCREMENT;
	}
	// private data members
	 private int currentLocks;
	 private int maxLocks;
}

 class Blockable {
	static int SUCCESS = 0;
	static int INTERRUPT = 1;

	static int TIMEOUT = 2 ;
	static int INFINITE = 10;
}