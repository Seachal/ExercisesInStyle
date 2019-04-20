package eis.threadsafe.exercises;

import java.util.*;

/**
 * Exercise 4: a thread-safe generic indexed container.
 */
public class Repository<T> {
    private final ArrayList<T> list;
    private final Object[] monitor;

    public Repository(int size) {
        list = new ArrayList<>(size); 
        monitor = new Object[size];
        for (int i=0; i<size; i++) {
            list.add(null);
            monitor[i] = new Object();
        }
        // or: Arrays.setAll(monitor, i -> new Object());
    }

    public T set(int i, T elem) {
        synchronized (monitor[i]) {
            return list.set(i, elem);
        }
    }

    public void swap(int i, int j) {
        if (i == j) return;
        if (i > j) {
            int temp = i;
            i = j;
            j = temp;
        }
        synchronized (monitor[i]) {
            synchronized (monitor[j]) {
                list.set(i, list.set(j, list.get(i)));
            }
        }
    }
}
