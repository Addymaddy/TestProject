package din2;

public class SemaphoreBarrierTest implements Runnable {

    semaphoreBarrier sb = new semaphoreBarrier(3);
    public static void main(String[] args) throws InterruptedException {
        //Create Three threads and start them here
        MonitorBarrierTest runnable = new MonitorBarrierTest();
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        Thread t4 = new Thread(runnable);


        t1.start();
        t2.start();
        t3.start();
        t4.start();



        Thread.sleep(2000);

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Exiting main thread");
    }

    @Override
    public void run() {
        System.out.println("Starting thread ---> "+ Thread.currentThread().getName());
        //mybarrier.arriveAndWait();
        sb.arriveAndWait();
        System.out.println("Exiting ----> "+ Thread.currentThread().getName());
    }
}
