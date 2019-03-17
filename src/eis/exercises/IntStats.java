package eis.exercises;

/* Chapter 4, exercise 3.
 *
 * Version where insert and getAverage work in constant time.
 * getMedian takes quasilinear time.
 */

import java.util.*;

public class IntStats {
    private int sum;
    private ArrayList<Integer> numbers = new ArrayList<>();

    public void insert(int n) { 
        numbers.add(n);
        sum += n;
    }
    public double getAverage() {
        return sum / (double) numbers.size();
    }
    public double getMedian() {
        Collections.sort(numbers);
        final int size = numbers.size();
        if (size % 2 == 1) // odd size
            return numbers.get(size/2); 
        else               // even size
            return (numbers.get(size/2 -1) + numbers.get(size/2)) / 2.0;
    }

    public static void main(String ... args) {
        IntStats is = new IntStats();
        is.insert(10);
        is.insert(5);
        is.insert(15);
        System.out.println("Average: " + is.getAverage() + "\t Median: " + is.getMedian());
        is.insert(15);
        System.out.println("Average: " + is.getAverage() + "\t Median: " + is.getMedian());
    }
}
