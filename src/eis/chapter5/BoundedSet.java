package eis.chapter5;

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
    
    public void add(T elem) {
        if (elem==null)
            throw new NullPointerException();
        data.remove(elem);
        if (data.size() == capacity) {
            data.removeFirst();
        }
        data.addLast(elem);
    }
    
    public boolean contains(T elem) {
        return data.contains(elem);
    }
}
