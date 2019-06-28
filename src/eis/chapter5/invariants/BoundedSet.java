package eis.chapter5.invariants;

import java.util.*;

/** A set with a fixed capacity.
 *  An insertions on a full set evicts the oldest element from the set. 
 *  
 *  When assertions are enabled, method <code>add</code> checks the class invariants.
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
        assert checkInvariants() : "add broke an invariant!";
    }
    
    public boolean contains(T elem) {
        return data.contains(elem);
    }
    
    private boolean checkInvariants() {
        if (data.size() > capacity)
            return false;
        // Check for duplicates
        Set<T> elements = new HashSet<>();
        for (T element: data) {
            if (!elements.add(element))
                return false;
        }
        return true;
    }
}
