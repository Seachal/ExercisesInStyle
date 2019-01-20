package eis.exercises;

/* Chapter 6 */

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

import java.util.*;

// To run these tests from the command line, in folder /src:
//   javac -cp .:../libs/junit-4.12.jar eis/exercises/InterleaveTests.java
//   java  -cp .:../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar org.junit.runner.JUnitCore eis.exercises.InterleaveTests

public class InterleaveTests {

    private List<Integer> a, b, result;

    @Before
    public void setUp() {
	a = List.of(1, 2, 3);
	b = List.of(4, 5, 6);
	result = List.of(1, 4, 2, 5, 3, 6);
    }

    @Test(expected = NullPointerException.class)
    public void testFirstNull() { // 1
	InterleaveLists.interleaveLists(null, b);
    }

    @Test(expected = NullPointerException.class)
    public void testSecondNull() { // 2
	InterleaveLists.interleaveLists(a, null);
    }

    @Test
    public void testBothEmpty() { // 3
	a = List.of();
	b = List.of();
	List<Integer> c = InterleaveLists.interleaveLists(a, b);
	assertTrue("should be empty", c.isEmpty()); 
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFirstEmpty() { // 4
	a = List.of();
	InterleaveLists.interleaveLists(a, b);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSecondEmpty() { // 5
	b = List.of();
	InterleaveLists.interleaveLists(a, b);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBothNonEmptyDifferentLength() { // 6
	b = List.of(4, 5);
	InterleaveLists.interleaveLists(a, b);
    }

    @Test
    public void testBothNonEmptySameLength() { // 7
	List<Integer> c = InterleaveLists.interleaveLists(a, b);
	assertEquals("should be " + result, c, result);
    }
}
