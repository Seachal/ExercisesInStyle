package eis.chapter6.exercises;

import java.util.*;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/* To run these tests from the command line, from folder /src:
 *
 *   javac -cp .:../libs/junit-4.12.jar eis/exercises/DivisorTests.java
 *   java  -cp .:../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar org.junit.runner.JUnitCore eis.exercises.DivisorTests
 */

/** Unit tests for a method returning the list of divisors of a specified integer.
 *
 *  Contains a stub implementation of the method that fails all tests except one.
 *
 *  @author Marco Faella
 *  @version 1.0
 */
public class DivisorTests {

    /* Stub */
    public static List<Integer> getDivisors(int n) {
        return new ArrayList<>();
    }

    // C1 = C2 = zero
    @Test
    public void testZero() {
        List<Integer> divisors = getDivisors(0);
        assertTrue("Divisors of zero should be the empty list", divisors.isEmpty());
    }

    // C1 = negative, C2 = one
    @Test
    public void testMinusOne() {
        List<Integer> divisors = getDivisors(-1);
        List<Integer> expected = List.of(1);
        assertEquals("Wrong divisors of -1", expected, divisors);
    }

    // C1 = negative, C2 = two
    @Test
    public void testMinusPrime() {
        List<Integer> divisors = getDivisors(-11);
        List<Integer> expected = List.of(1, 11);
        assertEquals("Wrong divisors of negative prime", expected, divisors);
    }

    // C1 = negative, C2 = more than two
    @Test
    public void testMinusNonPrime() {
        List<Integer> divisors = getDivisors(-12);
        List<Integer> expected = List.of(1, 2, 3, 4, 6, 12);
        assertEquals("Wrong divisors of negative prime", expected, divisors);
    }

    // C1 = positive, C2 = one
    @Test
    public void testOne() {
        List<Integer> divisors = getDivisors(1);
        List<Integer> expected = List.of(1);
        assertEquals("Wrong divisors of 1", expected, divisors);
    }

    // C1 = positive, C2 = two
    @Test
    public void testPrime() {
        List<Integer> divisors = getDivisors(17);
        List<Integer> expected = List.of(1, 17);
        assertEquals("Wrong divisors of positive prime", expected, divisors);
    }

    // C1 = positive, C2 = more than two
    @Test
    public void testNonPrime() {
        List<Integer> divisors = getDivisors(14);
        List<Integer> expected = List.of(1, 2, 7, 14);
        assertEquals("Wrong divisors of positive prime", expected, divisors);
    }
}
