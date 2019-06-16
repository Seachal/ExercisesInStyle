package eis.chapter8.exercises;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.Lock;

public class AtomicPair2<S,T> {
    private S first;
    private T second;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock(), writeLock = lock.writeLock();

    public void setBoth(S first, T second) {
        writeLock.lock();
        this.first = first;
        this.second = second;
        writeLock.unlock();
    }
    public S getFirst() {
        readLock.lock();
        return first;
    }
    public T getSecond() {
        T result = second;
        readLock.unlock();
        return result;
    }
}
