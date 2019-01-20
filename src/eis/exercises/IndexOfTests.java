package eis.exercises;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

// To run these tests from the command line, from folder /src:
//   javac -cp .:../libs/junit-4.12.jar eis/exercises/IndexOfTests.java
//   java  -cp .:../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar org.junit.runner.JUnitCore eis.exercises.IndexOfTests

public class IndexOfTests {
    private final static String TESTME = "test me"; 

    @Test
    public void testNominal() { 
	int result = TESTME.indexOf((int)'t', 2);
	assertEquals("test with nominal arguments", 3, result);
    }

    @Test
    public void testNegativeIndex() { 
	int result = TESTME.indexOf((int)'t', -2);
	assertEquals("negative index should be treated as 0", 0, result);
    }

    @Test
    public void testZeroCharAndOverflowingIndex() { 
	int result = TESTME.indexOf(0, TESTME.length() + 10);
	assertEquals("testing index beyond upper bound", -1, result);
    }

    @Test
    public void testNegativeCharAndZeroIndex() { 
	int result = TESTME.indexOf(-1, 0);
	assertEquals("testing negative character (invalid)", -1, result);
    }

    @Test
    public void testEmptyString() { 
	int result = "".indexOf((int)'t', 0);
	assertEquals(null, -1, result);
    }
}
