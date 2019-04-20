package eis.threadsafe;

public class AtomicPair1<S,T> {
    private S first;
    private T second;
    private Object lock = new Object();

    public void setBoth(S first, T second) {
        synchronized (lock) {
            this.first = first;
            this.second = second;
        }
    }
    public S getFirst() {
        synchronized (lock) {
            return first;
        }
    }
    public T getSecond() {
        synchronized (lock) {
            return second;
        }
    }
}
