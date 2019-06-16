package eis.chapter4;

import java.util.ArrayList;
import java.util.List;

/**  
 * A multi-set, optimized for memory footprint.
 *
 *  @author Marco Faella
 *  @version 1.0
 */
public class MultiSet<T> {
    private List<T> data = new ArrayList<>(); 

    public void add(T elem) {
        data.add(elem);
    }

    public long count(T elem) {
        return data.stream().filter(x -> x.equals(elem)).count();
    }

    public long oldFashionedCount(T elem) {
        long count = 0;
        for (T other: data) {
            if (other.equals(elem)) {
                count++;
            }
        }
        return count;
    }
}
