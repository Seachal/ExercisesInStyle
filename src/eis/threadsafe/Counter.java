package eis.threadsafe;

public class Counter {
    private int n;
    public void increment() { n++; }
    @Override
    public String toString() { return "" + n; }

    public static void main(String ... args) throws InterruptedException {
        final int NTHREADS = 5, NINCREMENTS = 1000;
        final Counter counter = new Counter();
        final Thread[] thread = new Thread[NTHREADS];

        for (int i=0; i<NTHREADS; i++) {
            thread[i] = new Thread() {
                    public void run() {
                        for (int j=0; j<NINCREMENTS; j++)
                            counter.increment();
                    }
                };
            thread[i].start();
        }
        for (Thread t: thread)
            t.join();
        System.out.println(counter + " vs " + NTHREADS*NINCREMENTS);
    }
}
