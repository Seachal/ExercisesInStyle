package eis.threadsafe.exercises;

public class AtomicPair<S,T> {
    private S first;
    private T second;
    private final Object lock = new Object();

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
