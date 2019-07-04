package eis.chapter8;

import java.util.*;

/**
 * A thread-safe, fixed-size, indexed container.
 */
public class Repository<T> {
    private final List<T> elements;
    private final List<Object> monitors;

    public Repository(int size) {
        elements = new ArrayList<>(size); 
        monitors = new ArrayList<>(size);
        for (int i=0; i<size; i++) {
            elements.add(null);
            monitors.add(new Object());
        }
    }

    public T set(int i, T elem) {
        synchronized (monitors.get(i)) {
            return elements.set(i, elem);
        }
    }

    public void swap(int i, int j) {
        if (i == j) return;
        if (i > j) {
            int temp = i;
            i = j;
            j = temp;
        }
        synchronized (monitors.get(i)) {
            synchronized (monitors.get(j)) {
                elements.set(i, elements.set(j, elements.get(i)));
            }
        }
    }
}
