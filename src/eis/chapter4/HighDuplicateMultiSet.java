package eis.chapter4;

import java.util.ArrayList;
import java.util.List;

/**  
 * A multi-set, optimized for memory footprint,
 * assuming a high number of repeated elements.
 *
 *  @author Marco Faella
 *  @version 1.0
 */
public class HighDuplicateMultiSet<T> {
    private List<T> elements = new ArrayList<>(); 
    private List<Long> repetitions = new ArrayList<>(); 

    public void add(T elem) {
        for (int i=0; i<elements.size(); i++) {
            if (elements.get(i).equals(elem)) {
                repetitions.set(i, repetitions.get(i) + 1);
                return;
            }
        }
        elements.add(elem);
        repetitions.add(1L);
    }

    public long count(T elem) {
        for (int i=0; i<elements.size(); i++) {
            if (elements.get(i).equals(elem)) {
                return repetitions.get(i);
            }
        }
        return 0;
    }
}
