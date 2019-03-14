package din;

public class Barrier {


    volatile int barrierLimit;

    Barrier(int limit){
        this.barrierLimit=limit;

    }

    public synchronized void resetBarrier(int limit){
       if(barrierLimit == 0)
        this.barrierLimit=limit;
    }

    public synchronized void arriveAndWait(){
       /* if(barrierLimit==0)
        {
            System.out.println("Last Thread Arrived Breaking barrier and notifying all waiting threads--- "+ Thread.currentThread().getName());
            notifyAll();
        }
        else
        {
            System.out.println("barrier limit before decrementing --> "+ barrierLimit);
            barrierLimit--;
            try {
                System.out.println("Barrier Limit now is ---> "+ barrierLimit);
                System.out.println("Decrementing the barrier Limit and waiting ----" + Thread.currentThread().getName());
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }
}



