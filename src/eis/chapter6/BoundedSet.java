package eis.chapter6;

import java.util.*;

/** A set with a fixed capacity.
 *  An insertions on a full set evicts the oldest element from the set. 
 *  
 *  @author Marco Faella
 *  @version 1.0
 */
public class BoundedSet<T> {
    
    private LinkedList<T> data = new LinkedList<>();
    private int capacity;
    
    public BoundedSet(int capacity) {
        this.capacity = capacity;
    }

    public int size() {
        return data.size();
    }
    
    public T add(T elem) {
        if (elem==null)
            throw new NullPointerException();
        T removed = null;
        data.remove(elem);
        if (data.size() == capacity) {
            removed = data.removeFirst();
        }
        data.addLast(elem);
        return removed;
    }
    
    public boolean contains(T elem) {
        return data.contains(elem);
    }
    
    public List<T> content() {
        return Collections.unmodifiableList(data);
    }
}