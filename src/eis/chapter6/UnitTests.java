package eis.chapter6;


import eis.chapter2.reference.Container;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;


/* To run these tests from the command line, from folder /src:
 *
 *   javac -cp .:../libs/junit-4.12.jar eis/tests/UnitTests.java
 *   java  -cp .:../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar org.junit.runner.JUnitCore eis.tests.UnitTests
 */

/* Obtaining code coverage with JaCoCo
 *  
 * Collecting data:
 *
 *   java -javaagent:../libs/jacocoagent.jar -cp .:../libs/junit-4.12.jar:../libs/hamcrest-core-1.3.jar org.junit.runner.JUnitCore eis.tests.UnitTests
 *
 * The previous step generates the file src/jacoco.exec.
 * If the previous doesn't work, consider updating the version of JaCoCo.
 * Then, generate the report in the "coverage" folder:
 *
 *   java -jar ../libs/jacococli.jar report jacoco.exec --classfiles . --sourcefiles . --html ../coverage/
 */


/** Unit tests for water containers.
 *
 *  You can run them on a different version of Container by changing the first import line.
 *
 *  @author Marco Faella
 *  @version 1.0
 */
public class UnitTests {

    // Test fixtures
    private Container a, b;

    @Before
    public void setUp() {
        a = new Container();
        b = new Container();
    }

    // Constructor
        
    @Test
    public void testNewContainerIsEmpty() {
        assertTrue("new container is not empty", a.getAmount() == 0);
    }

    private static final double DELTA = 0;

    @Test
    public void testNewContainerIsEmptyWithEquals() {
        assertEquals("new container is not empty", 0, a.getAmount(), DELTA);
    }
    
    @Test
    public void testNewContainerIsEmptyWithHamcrest() {
        assertThat("new container is not empty", a.getAmount(), closeTo(0, DELTA));
    }

    
    // AddWater
    
    @Test
    public void testAddPositiveToIsolated() {
        a.addWater(1);
        assertTrue("should be 1.0", a.getAmount() == 1);
    }

    @Test
    public void testAddZeroToIsolated() {
        a.addWater(0);
        assertTrue("should be 0", a.getAmount() == 0);
    }

    @Test
    public void testAddValidNegativeToIsolated() {
        a.addWater(10.5);
        a.addWater(-2.5);
        assertTrue("should be 8", a.getAmount() == 8);
    }
        
    @Test(expected = IllegalArgumentException.class)
    public void testAddInvalidNegativeToIsolated() {
        a.addWater(-1);
    }

    @Test
    public void testAddPositiveToConnected() {
        a.connectTo(b);
        a.addWater(2);
        assertTrue("should be 1.0", a.getAmount() == 1);
    }

    @Test
    public void testAddZeroToConnected() {
        a.connectTo(b);
        a.addWater(0);
        assertTrue("should be 0", a.getAmount() == 0);
    }

    @Test
    public void testAddValidNegativeToConnected() {
        a.connectTo(b);
        a.addWater(10);
        a.addWater(-4);
        assertTrue("should be 3", a.getAmount() == 3);
    }
        
    @Test(expected = IllegalArgumentException.class)
    public void testAddInvalidNegativeToConnected() {
        a.connectTo(b);
        a.addWater(-1);
    }

    // Connect

    @Test
    public void testConnectOtherOneOne() { // #1
        a.connectTo(b);
        a.addWater(2);
        assertTrue("should be 1.0", a.getAmount() == 1);
    }

    @Test
    public void testConnectOtherTwoOne() { // #2
        Container c = new Container();
        a.connectTo(b);
        a.connectTo(c);
        a.addWater(3);
        assertTrue("should be 1.0", a.getAmount() == 1);
    }

    @Test
    public void testConnectOtherOneTwo() { // #3
        Container c = new Container();
        b.connectTo(c);
        a.connectTo(b);
        a.addWater(3);
        assertTrue("should be 1.0", a.getAmount() == 1);
    }

    @Test
    public void testConnectOtherTwoTwo() { // #4
        Container c = new Container(), d = new Container();
        a.connectTo(b);
        c.connectTo(d);
        a.connectTo(c);
        a.addWater(4);
        assertTrue("should be 1.0", a.getAmount() == 1);
        assertTrue("should be 1.0", c.getAmount() == 1);
    }

    @Test
    public void testConnectOtherAlreadyConnected() { // #5
        Container c = new Container();
        a.connectTo(b);
        b.connectTo(c);
        a.addWater(3);
        a.connectTo(c);
        assertTrue("should be 1.0", a.getAmount() == 1);
        assertTrue("should be 1.0", c.getAmount() == 1);
    }

    @Test
    public void testConnectSelfOne() { // #6
        a.addWater(1);
        a.connectTo(a);
        assertTrue("should be 1.0", a.getAmount() == 1);
    }

    @Test
    public void testConnectSelfTwo() { // #7
        a.addWater(2);
        a.connectTo(b);
        a.connectTo(a);
        assertTrue("should be 1.0", a.getAmount() == 1);
    }

    @Test(expected = NullPointerException.class)
    public void testConnectNull() { // #8
        a.connectTo(null);
    }

    // Extra

    @Test
    public void testTransitivity() {
        Container c1 = new Container(), c2 = new Container(),
                  c3 = new Container(), c4 = new Container();
        c1.connectTo(c2);
        c2.addWater(6);
        c3.connectTo(c4);
        c3.addWater(12);
        c4.connectTo(c1);
        assertTrue("should be 4.5", c1.getAmount() == 4.5);
        assertTrue("should be 4.5", c2.getAmount() == 4.5);
        assertTrue("should be 4.5", c3.getAmount() == 4.5);
        assertTrue("should be 4.5", c4.getAmount() == 4.5);
    }

    @Test
    public void testCorrectPickupFromGroup() {
        a.connectTo(b);
        a.addWater(4);
        assertTrue("should be 2", a.getAmount() == 2);
        a.addWater(-3);
        assertTrue("should be 0.5", a.getAmount() == 0.5);
    }
}
