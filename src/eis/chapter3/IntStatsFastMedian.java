package eis.chapter3;

import java.util.*;

/** 
 * Summary statistics for a sequence of integers.
 *
 * Version where getMedian and getAverage work in constant time.
 * insert takes linear time.
 *
 * @author Marco Faella
 * @version 1.0
 */
public class IntStatsFastMedian {
    private long sum;
    private List<Integer> numbers = new ArrayList<>();

    /* Linear time insertion in order */
    public void insert(int n) {
        int i = 0;
        for (Integer k: numbers) {
            if (k>=n) break;
            i++;
        }
        numbers.add(i, n);
        sum += n;
    }
    public double getAverage() {
        return sum / (double) numbers.size();
    }
    public double getMedian() {
        final int size = numbers.size();
        if (size==0)
            throw new IllegalStateException("Empty list.");
        if (size % 2 == 1) // odd size
            return numbers.get(size/2); 
        else               // even size
            return (numbers.get(size/2 -1) + numbers.get(size/2)) / 2.0;
    }
    
    public static void main(String ... args) {
        IntStatsFastMedian is = new IntStatsFastMedian();
        is.insert(10);
        is.insert(5);
        is.insert(15);
        System.out.println("Average: " + is.getAverage() + "\t Median: " + is.getMedian());
        is.insert(15);
        System.out.println("Average: " + is.getAverage() + "\t Median: " + is.getMedian());
        is.insert(15);
        System.out.println("Average: " + is.getAverage() + "\t Median: " + is.getMedian());
    }
}
